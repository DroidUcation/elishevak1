package com.perfecto.optimizer.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by elishevak on 7/15/2016.
 */
public class DAL extends AsyncTask<Void, Integer, Void> {

    HttpClient client = new HttpClient();

    SharedPreferences preferences;
    Context context;
    Set<String> deviceType = new HashSet<>();
    Set<String> os = new HashSet<>();
    Set<String> countries = new HashSet<>();

    SQLHelper helper;

    public DAL(SharedPreferences preferences, Context context) {
        this.preferences = preferences;
        this.context = context;
        helper = new SQLHelper(context);
    }

    public URL getReports() throws IOException {
        deviceType = preferences.getStringSet(Consts.DEVICE_KEY, deviceType);
        os = preferences.getStringSet(Consts.OS_KEY, os);
        countries = preferences.getStringSet(Consts.COUNTRIES_KEY, countries);


        String osParams = getParams(Consts.OS_KEY, os);
        String deviceTypeParams = getParams(Consts.DEVICE_KEY, deviceType);
        String countriesParams = getParams(Consts.COUNTRIES_KEY, countries);
        URL url = new URL(Consts.URL_PREFIX + Consts.REPOERT_REQ +
                osParams +
                deviceTypeParams +
                countriesParams);
        return url;
    }

    // &os=Android,iOS
    private String getParams(String paramsKey, Set<String> paramsSet) {
        if (paramsSet.isEmpty()) {
            return "";
        }

        String params = Consts.URL_PARAMS_DELIMITER + paramsKey + "=";
        int i = 0;
        for (String filter : paramsSet) {
            if (i != 0) {
                params += Consts.URL_PARAMS_VALUES_DELIMITER;
            }
            params += filter;
            i++;
        }
        return params;
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            if (isCancelled()) return null;
            URL url = getReports();
            String res = client.run(url.toString());

            if (!res.isEmpty()) {
                JSONObject jsonObject = new JSONObject(res);

                JSONArray report = jsonObject.getJSONArray(Consts.REPORT_KEY);

                helper.deleteAllRecords();

                for (int i = 0; i < report.length(); i++) {
                    JSONObject record = report.getJSONObject(i);

                    helper.insertReportRecord(
                            record.getDouble(Consts.Report.COLUMN_SHARE),
                            record.getString(Consts.Report.COLUMN_IMAGE),
                            record.getString(Consts.Report.COLUMN_NAME)
                    );
                }

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(Consts.SHARE_KEY,
                        jsonObject.getString(Consts.SHARE_KEY));
                editor.apply();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

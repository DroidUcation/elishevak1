package com.perfecto.optimizer.activities.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.perfecto.optimizer.utils.Consts;
import com.perfecto.optimizer.utils.DAL;

public class BaseFilterActivity extends AppCompatActivity {
    protected SharedPreferences preferences;

    protected DAL dal;
    Context context;
    protected SharedPreferences.OnSharedPreferenceChangeListener getReport =
            new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences preferences, String key) {
                    dal = new DAL(preferences, context);
                    if (!dal.getStatus().equals(AsyncTask.Status.RUNNING) && !key.equals(Consts.SHARE_KEY)) {
                        dal.execute();
                    }

                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences(Consts.SHARED_PREF_KEY, MODE_PRIVATE);
        context = this.getBaseContext();
//        dal = new DAL(preferences, this.getBaseContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        preferences.registerOnSharedPreferenceChangeListener(getReport);
    }

    @Override
    protected void onPause() {
        super.onPause();
        preferences.unregisterOnSharedPreferenceChangeListener(getReport);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

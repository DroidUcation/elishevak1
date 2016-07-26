package com.perfecto.optimizer.utils;

import android.provider.BaseColumns;

/**
 * Created by elishevak on 7/15/2016.
 */
public class Consts {
    public static final String SHARED_PREF_KEY = "com.perfecto.optimizer";
    public static final String DEVICE_KEY = "type";
    public static final String OS_KEY = "os";
    public static final String COUNTRIES_KEY = "Countries";

    public static final String SHARE_KEY = "shareKey";
    public static final String REPORT_KEY = "report";


    public static final String REPOERT_REQ = "rest/report?";
    public static final String URL_PARAMS_DELIMITER = "&";
    public static final String URL_PARAMS_VALUES_DELIMITER = ",";

    public static final String URL_PREFIX = "http://optimizer-beat-test.perfectomobile.com/";
    public static final String DATABASE_NAME = "optimizerreport.db";

    public static final int DATABASE_VERSION = 1;
    public static final String SQL_CREATION = "CREATE TABLE " +
            Report.TABLE_NAME + " ( " +
            Report._ID + " INTEGER PRIMARY KEY, " +
            Report.COLUMN_SHARE + " REAL, " +
            Report.COLUMN_IMAGE + " TEXT, " +
            Report.COLUMN_NAME + " TEXT" +
            " ) ";


    public class Report implements BaseColumns {
        public static final String TABLE_NAME = "Report";
        public static final String COLUMN_SHARE = "share";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_NAME = "name";

    }

}

package com.perfecto.optimizer.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by elishevak on 7/17/2016.
 */
public class SQLHelper extends SQLiteOpenHelper {

    public SQLHelper(Context context) {
        super(context, Consts.DATABASE_NAME, null, Consts.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Consts.SQL_CREATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Consts.Report.TABLE_NAME);
        onCreate(db);
    }

    public void deleteAllRecords() {
        this.getWritableDatabase().execSQL("delete from "+ Consts.Report.TABLE_NAME);
    }

    public void insertReportRecord(double share, String image, String name) {
        ContentValues values = new ContentValues();
        values.put(Consts.Report.COLUMN_SHARE, share);
        values.put(Consts.Report.COLUMN_IMAGE, image);
        values.put(Consts.Report.COLUMN_NAME, name);
        this.getWritableDatabase().insert(Consts.Report.TABLE_NAME, null, values);
    }
    public Cursor getPartialReport(int top) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "select * from " + Consts.Report.TABLE_NAME + " LIMIT " + top, null);
    }
    public Cursor getReport() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "select * from " + Consts.Report.TABLE_NAME, null);
    }
}

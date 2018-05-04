package com.csci448.vgirkins.setlist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.csci448.vgirkins.setlist.Performance;
import com.csci448.vgirkins.setlist.database.SetlistDbSchema.*;

public class SetlistBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "setlistBase.db";

    public SetlistBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + SetlistDbSchema.SongTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                SongTable.Cols.UUID + ", " +
                SongTable.Cols.TITLE + ", " +
                SongTable.Cols.ARTIST  + ", " +
                SongTable.Cols.KEY + ", " +
                SongTable.Cols.SHARP + ", " +
                SongTable.Cols.FLAT + ", " +
                SongTable.Cols.MINOR + ", " +
                SongTable.Cols.VIDEO + ", " +
                SongTable.Cols.CHORDS + ", " +
                SongTable.Cols.DESCRIPTION +
                ")"
        );



        db.execSQL("create table " + SetlistDbSchema.PerformanceTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                PerformanceTable.Cols.UUID + ", " +
                PerformanceTable.Cols.NAME + ", " +
                PerformanceTable.Cols.BAND_NAME + ", " +
                PerformanceTable.Cols.DATE + ", " +
                PerformanceTable.Cols.TIME + ", " +
                PerformanceTable.Cols.AM + ", " +
                PerformanceTable.Cols.LOCATION + ", " +
                PerformanceTable.Cols.DESCRIPTION +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

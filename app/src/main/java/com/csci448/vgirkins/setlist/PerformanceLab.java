package com.csci448.vgirkins.setlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.csci448.vgirkins.setlist.database.SetlistBaseHelper;
import com.csci448.vgirkins.setlist.database.SetlistCursorWrapper;
import com.csci448.vgirkins.setlist.database.SetlistDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PerformanceLab {
    private static PerformanceLab mPerformanceLab;

    private List<Performance> mPerformances;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static PerformanceLab get(Context context) {
        if (mPerformanceLab == null) {
            mPerformanceLab = new PerformanceLab(context);
        }
        return mPerformanceLab;
    }

    private PerformanceLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new SetlistBaseHelper(mContext).getWritableDatabase();
    }

    public void addPerformance(Performance p) {
        ContentValues values = getContentValues(p);

        mDatabase.insert(SetlistDbSchema.PerformanceTable.NAME, null, values);
    }

    public List<Performance> getPerformances() {
        List<Performance> performances = new ArrayList<>();

        SetlistCursorWrapper cursor = queryPerformances(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                performances.add(cursor.getPerformance());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return performances;
    }

    public Performance getPerformance(UUID id) {
        SetlistCursorWrapper cursor = queryPerformances(
                SetlistDbSchema.PerformanceTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getPerformance();
        } finally {
            cursor.close();
        }
    }

    public void updatePerformance(Performance performance) {
        String uuidString = performance.getId().toString();
        ContentValues values = getContentValues(performance);

        mDatabase.update(SetlistDbSchema.PerformanceTable.NAME, values,
                SetlistDbSchema.PerformanceTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private SetlistCursorWrapper queryPerformances(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                SetlistDbSchema.PerformanceTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new SetlistCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Performance performance) {
        ContentValues values = new ContentValues();
        values.put(SetlistDbSchema.PerformanceTable.Cols.UUID, performance.getId().toString());
        values.put(SetlistDbSchema.PerformanceTable.Cols.PRACTICE, performance.isPractice() ? 1 : 0);   // FIXME not recognizing this column name
        values.put(SetlistDbSchema.PerformanceTable.Cols.NAME, performance.getName());
        values.put(SetlistDbSchema.PerformanceTable.Cols.BAND_NAME, performance.getBandName());
        values.put(SetlistDbSchema.PerformanceTable.Cols.DATE, performance.getDate());
        values.put(SetlistDbSchema.PerformanceTable.Cols.TIME, performance.getTime());
        values.put(SetlistDbSchema.PerformanceTable.Cols.AM, performance.isTimeAM() ? 1 : 0);
        values.put(SetlistDbSchema.PerformanceTable.Cols.LOCATION, performance.getLocation());
        values.put(SetlistDbSchema.PerformanceTable.Cols.DESCRIPTION, performance.getDescription());


        return values;
    }


}

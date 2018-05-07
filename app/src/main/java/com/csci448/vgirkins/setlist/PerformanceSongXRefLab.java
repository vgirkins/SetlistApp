package com.csci448.vgirkins.setlist;

import android.content.ContentValues;

import com.csci448.vgirkins.setlist.database.SetlistBaseHelper;
import com.csci448.vgirkins.setlist.database.SetlistDbSchema;
import com.csci448.vgirkins.setlist.database.SetlistDbSchema.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.UUID;

public class PerformanceSongXRefLab {
    private static PerformanceSongXRefLab mLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static PerformanceSongXRefLab get(Context context) {
        if (mLab == null) {
            mLab = new PerformanceSongXRefLab(context);
        }
        return mLab;
    }

    private PerformanceSongXRefLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new SetlistBaseHelper(mContext).getWritableDatabase();
    }

    public void associateSong(UUID performanceId, UUID songId) {   // Add song to performance
        ContentValues values = getContentValues(performanceId, songId);

        mDatabase.insert(PerformanceSongXRefTable.NAME, null, values);
    }

    public void dissociateSong(UUID performanceId, UUID songId) {    // Remove song from performance
        mDatabase.delete(PerformanceSongXRefTable.NAME,
                PerformanceSongXRefTable.Cols.PERF_UUID
                        + "=\"" + performanceId.toString() + "\" AND "
                        + PerformanceSongXRefTable.Cols.SONG_UUID
                        + "=\"" + songId.toString() + "\"",
                        null);
    }

    public void onDeleteSong(UUID songId) { // Remove all references to song
        mDatabase.delete(PerformanceSongXRefTable.NAME,
                PerformanceSongXRefTable.Cols.SONG_UUID
                + "=\"" + songId.toString() + "\"",
                null);
    }

    public void onDeletePerformance(UUID performanceId) { // Remove all references to performance
        mDatabase.delete(PerformanceSongXRefTable.NAME,
                PerformanceSongXRefTable.Cols.PERF_UUID
                        + "=\"" + performanceId.toString() + "\"",
                null);
    }
    private static ContentValues getContentValues(UUID performanceId, UUID songId) {
        ContentValues values = new ContentValues();
        values.put(PerformanceSongXRefTable.Cols.PERF_UUID, performanceId.toString());
        values.put(PerformanceSongXRefTable.Cols.SONG_UUID, songId.toString());

        return values;
    }
}

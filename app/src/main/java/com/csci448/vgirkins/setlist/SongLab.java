package com.csci448.vgirkins.setlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.csci448.vgirkins.setlist.database.SetlistBaseHelper;
import com.csci448.vgirkins.setlist.database.SetlistCursorWrapper;
import com.csci448.vgirkins.setlist.database.SetlistDbSchema;
import com.csci448.vgirkins.setlist.database.SetlistDbSchema.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by hunter on 3/15/2018.
 */

public class SongLab {
    private static SongLab sSongLab;

    private List<Song> mSongs;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static SongLab get(Context context) {
        if (sSongLab == null) {
            sSongLab = new SongLab(context);
        }
        return sSongLab;
    }

    private SongLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new SetlistBaseHelper(mContext).getWritableDatabase();
    }

    public void addSong(Song s) {
        ContentValues values = getContentValues(s);

        mDatabase.insert(SongTable.NAME, null, values);
    }

    public List<Song> getSongs(UUID performanceId) {

        List<Song> songs = new ArrayList<>();
        SetlistCursorWrapper cursor;
        if (performanceId == null) {
            cursor = querySongs(null, null);
        }
        else {
            cursor = querySongsFromPerformance(performanceId.toString());
        }
        /*
        String whereClause;
        String[] whereArgs;
        if (performanceId == null) {
            whereClause = null;
            whereArgs = null;
        }
        else {
            whereClause = SongTable.Cols.PERFORMANCE + " = ?";
            whereArgs = new String[] { performanceId.toString() };
        }

        SetlistCursorWrapper cursor = querySongs(whereClause, whereArgs);
         */

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                songs.add(cursor.getSong());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }


        return songs;
    }

    public Song getSong(UUID id) {
        SetlistCursorWrapper cursor = querySongs(
                SongTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getSong();
        } finally {
            cursor.close();
        }
    }

    public void updateSong(Song song) {
        String uuidString = song.getId().toString();
        ContentValues values = getContentValues(song);

        mDatabase.update(SongTable.NAME, values,
                SongTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private SetlistCursorWrapper querySongs(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                SongTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new SetlistCursorWrapper(cursor);
    }

    private SetlistCursorWrapper querySongsFromPerformance(String performanceId) {
        String rawQuery =   "SELECT " + SongTable.NAME + ".* FROM " + SongTable.NAME + " JOIN " + PerformanceSongXRefTable.NAME
                            + " ON (" + SongTable.Cols.UUID + " = " + PerformanceSongXRefTable.Cols.SONG_UUID
                            + " AND " + PerformanceSongXRefTable.Cols.PERF_UUID + " = " + "\"" + performanceId +"\")";
        Cursor c = mDatabase.rawQuery(
                rawQuery,
                null
        );
        return new SetlistCursorWrapper(c);
    }

    private static ContentValues getContentValues(Song song) {
        ContentValues values = new ContentValues();
        values.put(SongTable.Cols.UUID, song.getId().toString());
        values.put(SongTable.Cols.PERFORMANCE, song.getPerfId() == null ? null : song.getPerfId().toString());  // Song may not be associated with a performance
        values.put(SongTable.Cols.TITLE, song.getTitle());
        values.put(SongTable.Cols.ARTIST, song.getArtist());
        values.put(SongTable.Cols.KEY, Character.toString(song.getKey()));
        values.put(SongTable.Cols.SHARP, song.isSharpKey() ? 1 : 0);
        values.put(SongTable.Cols.FLAT, song.isFlatKey() ? 1 : 0);
        values.put(SongTable.Cols.MINOR, song.isMinorKey() ? 1 : 0);
        values.put(SongTable.Cols.VIDEO, song.getLinkToVid());
        values.put(SongTable.Cols.CHORDS, song.getLinkToChordCharts());
        values.put(SongTable.Cols.DESCRIPTION, song.getDescription());


        return values;
    }

    public void deleteSong(UUID id) {
        mDatabase.delete(SongTable.NAME, SongTable.Cols.UUID + "=\"" + id.toString() + "\"", null);
    }
}

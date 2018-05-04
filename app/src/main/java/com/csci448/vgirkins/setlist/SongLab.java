package com.csci448.vgirkins.setlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.csci448.vgirkins.setlist.database.SetlistBaseHelper;
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

    public List<Song> getSongs() {
        return new ArrayList<>();
    }

    public Song getSong(UUID id) {
        return null;
    }

    public void updateSong(Song song) {
        String uuidString = song.getId().toString();
        ContentValues values = getContentValues(song);

        mDatabase.update(SongTable.NAME, values,
                SongTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private static ContentValues getContentValues(Song song) {
        ContentValues values = new ContentValues();
        values.put(SongTable.Cols.UUID, song.getId().toString());
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

    public void deleteSong(UUID id) {   // FIXME isn't working properly
        for (Song song : mSongs) {
            if (song.getId() == id) {
                mSongs.remove(song);
                break;
            }
        }
    }
}

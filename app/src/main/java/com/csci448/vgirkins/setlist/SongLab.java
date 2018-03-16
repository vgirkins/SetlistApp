package com.csci448.vgirkins.setlist;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by hunti on 3/15/2018.
 */

public class SongLab {
    private static SongLab sSongLab;

    private List<Song> mSongs;

    public static SongLab get(Context context) {
        if (sSongLab == null) {
            sSongLab = new SongLab(context);
        }
        return sSongLab;
    }

    private SongLab(Context context) {
        mSongs = new ArrayList<>();
    }

    public List<Song> getSongs() {
        return mSongs;
    }

    public Song getSong(UUID id) {
        for (Song song : mSongs) {
            if (song.getId().equals(id)) {
                return song;
            }
        }

        return null;
    }
}

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
        for (int i = 0; i < 100; i++) {
            Song song = new Song("TestTitle", "TestArtist", 'A', true, false, false, "https://www.youtube.com/watch?v=dQw4w9WgXcQ", "https://tabs.ultimate-guitar.com/tab/rick_astley/never_gonna_give_you_up_chords_521741", "He's never gonna give you up.");
            mSongs.add(song);
        }
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

package com.csci448.vgirkins.setlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by hunti on 3/15/2018.
 */

public class SongFragment extends Fragment{

    private static final String ARG_SONG_ID = "song_id";

    public static SongFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SONG_ID, crimeId);

        SongFragment fragment = new SongFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

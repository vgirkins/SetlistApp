package com.csci448.vgirkins.setlist;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by hunti on 3/15/2018.
 */

public class SongActivity extends SingleFragmentActivity{
    private static final String EXTRA_SONG_ID =
            "com.csci448.vgirkins.setlist.song_id";

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, SongActivity.class);
        intent.putExtra(EXTRA_SONG_ID, crimeId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_SONG_ID);
        return SongFragment.newInstance(crimeId);
    }
}

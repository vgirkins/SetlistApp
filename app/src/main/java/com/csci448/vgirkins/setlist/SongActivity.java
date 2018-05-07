package com.csci448.vgirkins.setlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.UUID;

/**
 * Created by hunti on 3/15/2018.
 */

public class SongActivity extends SingleFragmentActivity{
    public static final String EXTRA_SONG_ID = "com.csci448.vgirkins.setlist.song_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new SongFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    // TODO check if we really need this
    /*
    public static Intent newIntent(Context packageContext, UUID songId) {
        Intent intent = new Intent(packageContext, SongActivity.class);
        intent.putExtra(EXTRA_SONG_ID, songId);
        return intent;
    }
    */

    @Override
    protected Fragment createFragment() {
        UUID songId = (UUID) getIntent().getSerializableExtra(EXTRA_SONG_ID);
        return SongFragment.newInstance(songId, null);
    }
}

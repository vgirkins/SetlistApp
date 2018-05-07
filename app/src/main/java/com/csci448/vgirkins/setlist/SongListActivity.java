package com.csci448.vgirkins.setlist;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by hunti on 3/15/2018.
 */

public class SongListActivity extends SingleFragmentActivity {
    public static final String EXTRA_PERFORMANCE_ID = "com.csci448.vgirkins.setlist.performance_id";
    public static final String EXTRA_USER_ADDING_SONGS_ID = "com.csci448.vgirkins.setlist.user_adding_songs_id";    // True if this was called when user
                                                                                                                    // selected "Add songs" on performance

    @Override
    public Fragment createFragment() {
        UUID performanceId = (UUID) getIntent().getSerializableExtra(EXTRA_PERFORMANCE_ID);
        boolean userAddingSongs = (boolean) getIntent().getSerializableExtra(EXTRA_USER_ADDING_SONGS_ID);
        return SongListFragment.newInstance(performanceId, userAddingSongs);
    }

    public static Intent newIntent(Context packageContext, UUID performanceId, boolean userAddingSongs) {
        Intent intent = new Intent(packageContext, SongListActivity.class);
        intent.putExtra(EXTRA_PERFORMANCE_ID, performanceId);
        intent.putExtra(EXTRA_USER_ADDING_SONGS_ID, userAddingSongs);
        return intent;
    }
}

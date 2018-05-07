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

    @Override
    public Fragment createFragment() {
        UUID performanceId = (UUID) getIntent().getSerializableExtra(EXTRA_PERFORMANCE_ID);
        return SongListFragment.newInstance(performanceId);
    }

    public static Intent newIntent(Context packageContext, UUID performanceId) {
        Intent intent = new Intent(packageContext, SongListActivity.class);
        intent.putExtra(EXTRA_PERFORMANCE_ID, performanceId);
        return intent;
    }
}

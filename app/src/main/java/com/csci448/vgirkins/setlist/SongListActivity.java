package com.csci448.vgirkins.setlist;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by hunti on 3/15/2018.
 */

public class SongListActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return new SongListFragment();
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, SongListActivity.class);
        return intent;
    }
}

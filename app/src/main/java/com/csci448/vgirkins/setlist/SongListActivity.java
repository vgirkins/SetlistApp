package com.csci448.vgirkins.setlist;

import android.support.v4.app.Fragment;

/**
 * Created by hunti on 3/15/2018.
 */

public class SongListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new SongListFragment();
    }
}

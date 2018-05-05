package com.csci448.vgirkins.setlist;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class PerformanceListActivity extends  SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return new PerformanceListFragment();
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, PerformanceListActivity.class);
        return intent;
    }

}

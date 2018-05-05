package com.csci448.vgirkins.setlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.UUID;

public class PerformanceActivity extends SingleFragmentActivity{
    public static final String EXTRA_PERFORMANCE_ID = "com.csci448.vgirkins.setlist.performance_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new PerformanceFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    public static Intent newIntent(Context packageContext, UUID performanceId) {
        Intent intent = new Intent(packageContext, PerformanceActivity.class);
        intent.putExtra(EXTRA_PERFORMANCE_ID, performanceId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID performanceId = (UUID) getIntent().getSerializableExtra(EXTRA_PERFORMANCE_ID);
        return PerformanceFragment.newInstance(performanceId);
    }
}

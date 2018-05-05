package com.csci448.vgirkins.setlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

public class PerformancePagerActivity extends AppCompatActivity {
        private static final String EXTRA_PERFORMANCE_ID = "com.csci448.vgirkins.setlist.performance_id";

        private ViewPager mViewPager;
        private List<Performance> mPerformances;

        public static Intent newIntent(Context packageContext, UUID performanceId) {
        Intent intent = new Intent(packageContext, PerformancePagerActivity.class);
        intent.putExtra(EXTRA_PERFORMANCE_ID, performanceId);
        return intent;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pager);

            UUID performanceId = (UUID) getIntent().getSerializableExtra(EXTRA_PERFORMANCE_ID);

            mViewPager = findViewById(R.id.view_pager);

            mPerformances = PerformanceLab.get(this).getPerformances();
            FragmentManager fragmentManager = getSupportFragmentManager();
            mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
                @Override
                public Fragment getItem(int position) {
                    Performance performance = mPerformances.get(position);
                    return PerformanceFragment.newInstance(performance.getId());
                }

                @Override
                public int getCount() {
                    return mPerformances.size();
                }
            });

            for (int i = 0; i < mPerformances.size(); i++) {
                if (mPerformances.get(i).getId().equals(performanceId)) {
                    mViewPager.setCurrentItem(i);
                    break;
                }
            }
        }
    }

package com.csci448.vgirkins.setlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Tori on 3/14/2018.
 */

public class CreateActivity extends AppCompatActivity {
    // Extras for performance creation
    private static final String EXTRA_IS_PRACTICE = "com.csci448.vgirkins.setlist.is_practice";
    private static final String EXTRA_PERFORMANCE_NAME = "com.csci448.vgirkins.setlist.performance_name";
    private static final String EXTRA_PERFORMANCE_BAND_NAME = "com.csci448.vgirkins.setlist.band_name";
    private static final String EXTRA_PERFORMANCE_DATE = "com.csci448.vgirkins.setlist.peformance_date";
    private static final String EXTRA_PERFORMANCE_TIME = "com.csci448.vgirkins.setlist.performance_time";
    private static final String EXTRA_PERFORMANCE_TIME_IS_AM = "com.csci448.vgirkins.setlist.performance_time_is_am";   // For AM vs PM performance times
    private static final String EXTRA_PERFORMANCE_LOCATION = "com.csci448.vgirkins.setlist.peformance_location";
    private static final String EXTRA_PERFORMANCE_DESCRIPTION = "com.csci448.vgirkins.setlist.performance_description";

    // Extras for song creation
        // TODO

    private boolean isPractice;
    private String performanceName;
    private String performanceBandName;
    private String performanceDate;
    private String performanceTime;
    private  boolean performanceTimeIsAM;
    private String performanceLocation;
    private String performanceDescription;

    private static final String EXTRA_CREATE_CODE = "com.csci448.vgirkins.setlist.create_code";
    private static final int SONG_CODE = 0;
    private static final int PERFORMANCE_CODE = 1;

    public static Intent newIntent(Context packageContext, int createCode) {
        Intent intent = new Intent(packageContext, CreateActivity.class);
        intent.putExtra(EXTRA_CREATE_CODE, createCode);
        return intent;
    }

    protected Fragment createFragment() {
        int createCode = getIntent().getIntExtra(EXTRA_CREATE_CODE, 0);
        if (createCode == SONG_CODE) {
            return CreateSongFragment.newInstance();
        }
        else if (createCode == PERFORMANCE_CODE) {
            return CreatePerformanceFragment.newInstance();
        }

        // We need a return no matter what
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container_create);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container_create, fragment)
                    .commit();
        }

        isPractice = getIntent().getBooleanExtra(EXTRA_IS_PRACTICE, false);
        performanceName = getIntent().getStringExtra(EXTRA_PERFORMANCE_NAME);
        performanceBandName = getIntent().getStringExtra(EXTRA_PERFORMANCE_BAND_NAME);
        performanceDate = getIntent().getStringExtra(EXTRA_PERFORMANCE_DATE);
        performanceTime = getIntent().getStringExtra(EXTRA_PERFORMANCE_TIME);
        performanceTimeIsAM = getIntent().getBooleanExtra(EXTRA_PERFORMANCE_TIME_IS_AM, false);
        performanceLocation = getIntent().getStringExtra(EXTRA_PERFORMANCE_LOCATION);
        // TODO description
    }

    public static Performance getPerformance(Intent result) {
        boolean isPractice = result.getBooleanExtra(EXTRA_IS_PRACTICE, false);
        String performanceName = result.getStringExtra(EXTRA_PERFORMANCE_NAME);
        String performanceBandName = result.getStringExtra(EXTRA_PERFORMANCE_BAND_NAME);
        String performanceDate = result.getStringExtra(EXTRA_PERFORMANCE_DATE);
        String performanceTime = result.getStringExtra(EXTRA_PERFORMANCE_TIME);
        boolean performanceTimeIsAM = result.getBooleanExtra(EXTRA_PERFORMANCE_TIME_IS_AM, false);
        String performanceLocation = result.getStringExtra(EXTRA_PERFORMANCE_LOCATION);
        // FIXME String performanceDescription = result.getStringExtra(EXTRA_PERFORMANCE_DESCRIPTION);
        String performanceDescription = "Dummy description";

        return new Performance(isPractice, performanceName, performanceBandName, performanceDate, performanceTime, performanceTimeIsAM, performanceLocation, performanceDescription);
    }

}

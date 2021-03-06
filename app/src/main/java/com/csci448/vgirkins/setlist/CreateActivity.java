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
    private static final String EXTRA_SONG_NAME = "com.csci448.vgirkins.setlist.song_name";
    private static final String EXTRA_SONG_ARTIST = "com.csci448.vgirkins.setlist.song_artist";
    private static final String EXTRA_SONG_KEY = "com.csci448.vgirkins.setlist.song_key";
    private static final String EXTRA_SONG_KEY_IS_SHARP = "com.csci448.vgirkins.setlist.song_key_is_sharp";
    private static final String EXTRA_SONG_KEY_IS_FLAT = "com.csci448.vgirkins.setlist.song_key_is_flat";
    private static final String EXTRA_SONG_KEY_IS_MINOR = "com.csci448.vgirkins.setlist.song_key_is_minor";
    private static final String EXTRA_SONG_CHORDS = "com.csci448.vgirkins.setlist.song_chords";
    private static final String EXTRA_SONG_VIDEO = "com.csci448.vgirkins.setlist.song_video";
    private static final String EXTRA_SONG_DESCRIPTION = "com.csci448.vgirkins.setlist.song_description";

    /*
    // Variables for performance
    private boolean isPractice;
    private String performanceName;
    private String performanceBandName;
    private String performanceDate;
    private String performanceTime;
    private  boolean performanceTimeIsAM;
    private String performanceLocation;
    private String performanceDescription;

    // Variables for song
    private String songName;
    private String songArtist;
    private char songKey;
    private boolean songKeyIsSharp;
    private boolean songKeyIsFlat;
    private  boolean songKeyIsMinor;
    private String songChords;
    private String songVideo;
    private String sonDescription;
    */


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

        if (createCode == PERFORMANCE_CODE) {
            return CreatePerformanceFragment.newInstance();
        }
        else if (createCode == SONG_CODE) {
            return CreateSongFragment.newInstance();
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
    }

    public static Performance getPerformance(Intent result) {
        boolean isPractice = result.getBooleanExtra(EXTRA_IS_PRACTICE, false);
        String performanceName = result.getStringExtra(EXTRA_PERFORMANCE_NAME);
        String performanceBandName = result.getStringExtra(EXTRA_PERFORMANCE_BAND_NAME);
        String performanceDate = result.getStringExtra(EXTRA_PERFORMANCE_DATE);
        String performanceTime = result.getStringExtra(EXTRA_PERFORMANCE_TIME);
        boolean performanceTimeIsAM = result.getBooleanExtra(EXTRA_PERFORMANCE_TIME_IS_AM, false);
        String performanceLocation = result.getStringExtra(EXTRA_PERFORMANCE_LOCATION);
        String performanceDescription = result.getStringExtra(EXTRA_PERFORMANCE_DESCRIPTION);

        return new Performance(isPractice, performanceName, performanceBandName, performanceDate, performanceTime, performanceTimeIsAM, performanceLocation, performanceDescription);
    }

    public static Song getSong(Intent result) {
        String songName = result.getStringExtra(EXTRA_SONG_NAME);
        String songArtist = result.getStringExtra(EXTRA_SONG_ARTIST);
        char songKey = result.getCharExtra(EXTRA_SONG_KEY, 'C');
        boolean songKeyIsSharp = result.getBooleanExtra(EXTRA_SONG_KEY_IS_SHARP, false);
        boolean songKeyIsFlat = result.getBooleanExtra(EXTRA_SONG_KEY_IS_FLAT, false);
        boolean songKeyIsMinor = result.getBooleanExtra(EXTRA_SONG_KEY_IS_MINOR, false);
        String songChords = result.getStringExtra(EXTRA_SONG_CHORDS);
        String songVideo = result.getStringExtra(EXTRA_SONG_VIDEO);
        String songDescription = result.getStringExtra(EXTRA_SONG_DESCRIPTION);

        return new Song(songName, songArtist, songKey, songKeyIsSharp, songKeyIsFlat, songKeyIsMinor, songChords, songVideo, songDescription);
    }

}

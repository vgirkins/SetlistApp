package com.csci448.vgirkins.setlist;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Tori on 3/14/2018.
 */

public class CreateActivity extends AppCompatActivity {

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

}

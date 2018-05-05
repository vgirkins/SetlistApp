package com.csci448.vgirkins.setlist;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;
import java.util.UUID;

public class SongPagerActivity extends AppCompatActivity {
    private static final String TAG = "SongPagerActivity";
    private static final String EXTRA_SONG_ID = "com.csci448.vgirkins.setlist.song_id";

    private ViewPager mViewPager;
    private List<Song> mSongs;

    public static Intent newIntent(Context packageContext, UUID songId) {
        Intent intent = new Intent(packageContext, SongPagerActivity.class);
        intent.putExtra(EXTRA_SONG_ID, songId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        UUID songId = (UUID) getIntent().getSerializableExtra(EXTRA_SONG_ID);

        mViewPager = findViewById(R.id.view_pager);



        mSongs = SongLab.get(this).getSongs();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Song song = mSongs.get(position);
                return SongFragment.newInstance(song.getId());
            }

            @Override
            public int getCount() {
                return mSongs.size();
            }
        });

        for (int i = 0; i < mSongs.size(); i++) {
            if (mSongs.get(i).getId().equals(songId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }
}

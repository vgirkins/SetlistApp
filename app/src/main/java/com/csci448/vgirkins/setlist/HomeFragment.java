package com.csci448.vgirkins.setlist;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.csci448.vgirkins.setlist.database.SetlistDbSchema;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Tori on 3/14/2018.
 */

public class HomeFragment extends Fragment {
    // Could add to these if we end up giving the user the option to create other things. The extra "create code" is compared to these to see what the user launched.
    private static final int SONG_CODE = 0;
    private static final int PERFORMANCE_CODE = 1;

    // All songs and performances in the system
    private List<Song> allSongs;    // To distinguish from songs associated with a single performance
    private List<Performance> performances;

    private Button mPerformancesButton;
    private Button mSongLibraryButton;
    private Button mCreateButton;

    // For distinguishing an activity result from a song vs performance creation
    private static final int REQUEST_CODE_CREATE_SONG = 0;
    private static final int REQUEST_CODE_CREATE_PERFORMANCE = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (allSongs == null) {
            allSongs = new ArrayList<>();
        }
        if (performances == null) {
            performances = new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // TODO do we need this?


        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mPerformancesButton = view.findViewById(R.id.hPerformButton);
        mPerformancesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPerformanceList();
            }
        });

        mSongLibraryButton = view.findViewById(R.id.hSongsButton);
        mSongLibraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSongList();
            }
        });

        mCreateButton = view.findViewById(R.id.hCreateButton);
        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCreateDialogue();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE_CREATE_SONG) {
            Song newSong = CreateActivity.getSong(data);
            SongLab.get(getActivity()).addSong(newSong);
        }

        else if (requestCode == REQUEST_CODE_CREATE_PERFORMANCE) {
            Performance newPerformance = CreateActivity.getPerformance(data);
            PerformanceLab.get(getActivity()).addPerformance(newPerformance);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

    }

    public void showCreateDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.hCreateButton)
                .setPositiveButton(R.string.hCreateSongButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = CreateActivity.newIntent(getActivity(), SONG_CODE);
                        startActivityForResult(intent, REQUEST_CODE_CREATE_SONG);
                    }
                })
                .setNeutralButton(R.string.hCreatePerformanceButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = CreateActivity.newIntent(getActivity(), PERFORMANCE_CODE);
                        startActivityForResult(intent, REQUEST_CODE_CREATE_PERFORMANCE);
                    }
                });
        builder.show();
    }

    public void showSongList() {
        Intent intent = SongListActivity.newIntent(getActivity());
        startActivity(intent);
    }

    private void showPerformanceList() {
        Intent intent = PerformanceListActivity.newIntent(getActivity());
        startActivity(intent);
    }

}
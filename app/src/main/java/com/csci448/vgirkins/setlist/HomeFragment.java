package com.csci448.vgirkins.setlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;


/**
 * Created by Tori on 3/14/2018.
 */

public class HomeFragment extends Fragment {

    private List<Song> allSongs;
    private List<Performance> performances;

    private static final String EXTRA_CREATE_CODE = "com.csci448.vgirkins.setlist.create_code"; // This extra tells the activity which "create" fragment to host
    // Could add to these if we end up giving the user the option to create other things. The extra "create code" is compared to these to see what the user launched.
    private static final int SONG_CODE = 0;
    private static final int PERFORMANCE_CODE = 1;

    private Button mPerformancesButton;
    private Button mSongLibraryButton;
    private Button mCreateButton;

    private static final int REQUEST_CODE_CREATE_SONG = 0;
    private static final int REQUEST_CODE_CREATE_PERFORMANCE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // TODO do we need this?

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mPerformancesButton = view.findViewById(R.id.hPerformButton);
        mPerformancesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
            }
        });

        mSongLibraryButton = view.findViewById(R.id.hSongsButton);
        mSongLibraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
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

}

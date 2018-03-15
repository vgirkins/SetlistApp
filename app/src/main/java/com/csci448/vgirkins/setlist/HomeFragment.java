package com.csci448.vgirkins.setlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by Tori on 3/14/2018.
 */

public class HomeFragment extends Fragment {

    private static final String EXTRA_CREATE_CODE = "com.csci448.vgirkins.setlist.create_code"; // This extra tells the activity which "create" fragment to host
    // Could add to these if we end up giving the user the option to create other things. The extra "create code" is compared to these to see what the user launched.
    private static final int SONG_CODE = 0;
    private static final int PERFORMANCE_CODE = 1;

    private Button mPerformancesButton;
    private Button mSongLibraryButton;
    private Button mCreateButton;

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
                // Intent intent = new intent stuff
                // startActivityForResult
            }
        });

        mSongLibraryButton = view.findViewById(R.id.hSongsButton);
        mSongLibraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent = new intent stuff
                // startActivityForResult
            }
        });

        mCreateButton = view.findViewById(R.id.hCreateButton);
        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent = new intent stuff
                // startActivityForResult
            }
        });

        return view;
    }

}

package com.csci448.vgirkins.setlist;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Tori on 3/14/2018.
 */

public class CreateSongFragment extends Fragment {
    private static final String EXTRA_SONG_NAME = "com.csci448.vgirkins.setlist.song_name";
    private static final String EXTRA_SONG_ARTIST = "com.csci448.vgirkins.setlist.song_artist";
    private static final String EXTRA_SONG_KEY = "com.csci448.vgirkins.setlist.song_key";
    private static final String EXTRA_SONG_KEY_IS_SHARP = "com.csci448.vgirkins.setlist.song_key_is_sharp";
    private static final String EXTRA_SONG_KEY_IS_FLAT = "com.csci448.vgirkins.setlist.song_key_is_flat";
    private static final String EXTRA_SONG_KEY_IS_MINOR = "com.csci448.vgirkins.setlist.song_key_is_minor";
    private static final String EXTRA_SONG_CHORDS = "com.csci448.vgirkins.setlist.song_chords";
    private static final String EXTRA_SONG_VIDEO = "com.csci448.vgirkins.setlist.song_video";
    private static final String EXTRA_SONG_DESCRIPTION = "com.csci448.vgirkins.setlist.song_description";

    private String songName;
    private String songArtist;
    private char songKey;
    private boolean songKeyIsSharp;
    private boolean songKeyIsFlat;
    private  boolean songKeyIsMinor;
    private String songChords;
    private String songVideo;
    private String sonDescription;

    private EditText songTitleField;
    private EditText artistTitleField;
    private Spinner keyLetterDropdown;
    private Spinner keyIntervalDropdown;
    private EditText chordsLinkField;
    private EditText videoLinkField;
    private EditText descriptionField;
    private Button createButton;
    private Button cancelButton;

    public static CreateSongFragment newInstance() {
        Bundle args = new Bundle();

        // Any arguments needed can be added here
        // FIXME if we don't need any arguments we don't need the Bundle

        CreateSongFragment frag = new CreateSongFragment();
        frag.setArguments(args);
        return frag;
    }

    public void setReturnResult() {
        Intent resultIntent = new Intent();

        songName = songTitleField.getText().toString();
        songArtist = artistTitleField.getText().toString();
        //songKey = keyLetterDropdown.get
        //songKeyI
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            // For rotation later. TODO recover needed values and store in instance variables
        }
        else {
            // TODO load in values from calling activity
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // FIXME is this needed?

        View view = inflater.inflate(R.layout.fragment_csong, container, false);

        songTitleField = view.findViewById(R.id.csTitle);

        artistTitleField = view.findViewById(R.id.csArtist);

        keyLetterDropdown = view.findViewById(R.id.csKeyLetter);

        keyIntervalDropdown = view.findViewById(R.id.csKeyInterval);

        chordsLinkField = view.findViewById(R.id.csChords);

        videoLinkField = view.findViewById(R.id.csVideo);

        descriptionField = view.findViewById(R.id.csDescript);

        createButton = view.findViewById(R.id.csCreateButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
            }
        });

        cancelButton = view.findViewById(R.id.csCancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }



}

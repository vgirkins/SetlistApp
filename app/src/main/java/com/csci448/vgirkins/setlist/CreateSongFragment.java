package com.csci448.vgirkins.setlist;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import javax.xml.parsers.SAXParser;

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
    private String songDescription;

    private EditText songTitleField;
    private EditText artistTitleField;
    private Spinner keyLetterDropdown;
    private Spinner keyIntervalDropdown;
    private CheckBox isMinorCheckbox;
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
        // TODO
        // songKey = keyLetterDropdown.get
        // songKeyIsSharp = keyIntervalDropdown.
        // songKeyIsFlat = keyIntervalDropdown.
        songKeyIsMinor = isMinorCheckbox.isChecked();
        songChords = chordsLinkField.getText().toString();
        songVideo = videoLinkField.getText().toString();
        songDescription = descriptionField.getText().toString();

        resultIntent.putExtra(EXTRA_SONG_NAME, songName == null ? "My Song" : songName);
        resultIntent.putExtra(EXTRA_SONG_ARTIST, songArtist == null ? "" : songArtist);

        // FIXME hardcoded vals
        resultIntent.putExtra(EXTRA_SONG_KEY, 'C');
        resultIntent.putExtra(EXTRA_SONG_KEY_IS_SHARP, false);
        resultIntent.putExtra(EXTRA_SONG_KEY_IS_FLAT, false);

        resultIntent.putExtra(EXTRA_SONG_KEY_IS_MINOR, songKeyIsMinor);
        resultIntent.putExtra(EXTRA_SONG_CHORDS, songChords == null ? "" : songChords);
        resultIntent.putExtra(EXTRA_SONG_VIDEO, songVideo == null ? "" : songVideo);
        resultIntent.putExtra(EXTRA_SONG_DESCRIPTION, songDescription == null ? "" : songDescription);

        getActivity().setResult(Activity.RESULT_OK, resultIntent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            // For rotation later. TODO recover needed values and store in instance variables
        }
        else {
            songName = getArguments().getString(EXTRA_SONG_NAME);
            songArtist = getArguments().getString(EXTRA_SONG_ARTIST);
            songKey = getArguments().getChar(EXTRA_SONG_KEY);
            songKeyIsSharp = getArguments().getBoolean(EXTRA_SONG_KEY_IS_SHARP);
            songKeyIsFlat = getArguments().getBoolean(EXTRA_SONG_KEY_IS_FLAT);
            songKeyIsMinor = getArguments().getBoolean(EXTRA_SONG_KEY_IS_MINOR);
            songChords = getArguments().getString(EXTRA_SONG_CHORDS);
            songVideo = getArguments().getString(EXTRA_SONG_VIDEO);
            songDescription = getArguments().getString(EXTRA_SONG_DESCRIPTION);
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

        isMinorCheckbox = view.findViewById(R.id.csIsMinorKey);

        chordsLinkField = view.findViewById(R.id.csChords);

        videoLinkField = view.findViewById(R.id.csVideo);

        descriptionField = view.findViewById(R.id.csDescript);

        createButton = view.findViewById(R.id.csCreateButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setReturnResult();
                getActivity().onBackPressed();
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
        // FIXME not sure if this is right

        songName = songTitleField.getText().toString();
        songArtist = artistTitleField.getText().toString();
        // TODO
        // songKey = keyLetterDropdown.get
        // songKeyIsSharp = keyIntervalDropdown.
        // songKeyIsFlat = keyIntervalDropdown.
        songKeyIsMinor = isMinorCheckbox.isChecked();
        songChords = chordsLinkField.getText().toString();
        songVideo = videoLinkField.getText().toString();
        songDescription = descriptionField.getText().toString();
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(EXTRA_SONG_NAME, songName);
        savedInstanceState.putString(EXTRA_SONG_ARTIST, songArtist);

        // FIXME hardcoded vals
        savedInstanceState.putChar(EXTRA_SONG_KEY, 'C');
        savedInstanceState.putBoolean(EXTRA_SONG_KEY_IS_SHARP, false);
        savedInstanceState.putBoolean(EXTRA_SONG_KEY_IS_FLAT, false);

        savedInstanceState.putBoolean(EXTRA_SONG_KEY_IS_MINOR, songKeyIsMinor);
        savedInstanceState.putString(EXTRA_SONG_CHORDS, songChords);
        savedInstanceState.putString(EXTRA_SONG_VIDEO, songVideo);
        savedInstanceState.putString(EXTRA_SONG_DESCRIPTION, songDescription);
    }



}

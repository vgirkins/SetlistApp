// https://developer.android.com/guide/topics/ui/controls/spinner.html
// https://stackoverflow.com/questions/23449270/setonitemselectedlistener-for-spinner-in-fragment#23449489

package com.csci448.vgirkins.setlist;


import android.app.Activity;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
    private char songKey = 'C';
    private boolean songKeyIsSharp = false;
    private boolean songKeyIsFlat = false;
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

        CreateSongFragment frag = new CreateSongFragment();
        frag.setArguments(args);
        return frag;
    }

    public void setReturnResult() {
        Intent resultIntent = new Intent();

        songName = songTitleField.getText().toString();
        songArtist = artistTitleField.getText().toString();

        songKeyIsMinor = isMinorCheckbox.isChecked();
        songChords = chordsLinkField.getText().toString();
        songVideo = videoLinkField.getText().toString();
        songDescription = descriptionField.getText().toString();

        resultIntent.putExtra(EXTRA_SONG_NAME, songName == null ? "My Song" : songName);
        resultIntent.putExtra(EXTRA_SONG_ARTIST, songArtist == null ? "" : songArtist);
        resultIntent.putExtra(EXTRA_SONG_KEY, songKey);
        resultIntent.putExtra(EXTRA_SONG_KEY_IS_SHARP, songKeyIsSharp);
        resultIntent.putExtra(EXTRA_SONG_KEY_IS_FLAT, songKeyIsFlat);
        resultIntent.putExtra(EXTRA_SONG_KEY_IS_MINOR, songKeyIsMinor);
        resultIntent.putExtra(EXTRA_SONG_CHORDS, songChords == null ? "" : songChords);
        resultIntent.putExtra(EXTRA_SONG_VIDEO, songVideo == null ? "" : songVideo);
        resultIntent.putExtra(EXTRA_SONG_DESCRIPTION, songDescription == null ? "" : songDescription);

        getActivity().setResult(Activity.RESULT_OK, resultIntent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_csong, container, false);

        songTitleField = view.findViewById(R.id.csTitle);

        artistTitleField = view.findViewById(R.id.csArtist);

        keyLetterDropdown = view.findViewById(R.id.csKeyLetter);
        keyLetterDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                songKey = (char)('A' + pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<CharSequence> keyLetterAdapter = ArrayAdapter.createFromResource(getContext(), R.array.csKeys_array, android.R.layout.simple_spinner_item);
        keyLetterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        keyLetterDropdown.setAdapter(keyLetterAdapter);

        keyIntervalDropdown = view.findViewById(R.id.csKeyInterval);
        keyIntervalDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                songKeyIsSharp = (pos == 1);
                songKeyIsFlat = (pos == 2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<CharSequence> keyIntervalAdapter = ArrayAdapter.createFromResource(getContext(), R.array.csIntervalArray, android.R.layout.simple_spinner_item);
        keyIntervalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        keyIntervalDropdown.setAdapter(keyIntervalAdapter);

        isMinorCheckbox = view.findViewById(R.id.csIsMinorKey);

        chordsLinkField = view.findViewById(R.id.csChords);

        videoLinkField = view.findViewById(R.id.csVideo);

        descriptionField = view.findViewById(R.id.csDescript);

        createButton = view.findViewById(R.id.csCreateButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setReturnResult();
                Toast.makeText(getActivity(), songTitleField.getText() + " created!", Toast.LENGTH_SHORT).show();
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
        songKeyIsMinor = isMinorCheckbox.isChecked();
        songChords = chordsLinkField.getText().toString();
        songVideo = videoLinkField.getText().toString();
        songDescription = descriptionField.getText().toString();
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(EXTRA_SONG_NAME, songName);
        savedInstanceState.putString(EXTRA_SONG_ARTIST, songArtist);
        savedInstanceState.putChar(EXTRA_SONG_KEY, songKey);
        savedInstanceState.putBoolean(EXTRA_SONG_KEY_IS_SHARP, songKeyIsSharp);
        savedInstanceState.putBoolean(EXTRA_SONG_KEY_IS_FLAT, songKeyIsFlat);

        savedInstanceState.putBoolean(EXTRA_SONG_KEY_IS_MINOR, songKeyIsMinor);
        savedInstanceState.putString(EXTRA_SONG_CHORDS, songChords);
        savedInstanceState.putString(EXTRA_SONG_VIDEO, songVideo);
        savedInstanceState.putString(EXTRA_SONG_DESCRIPTION, songDescription);
    }


}

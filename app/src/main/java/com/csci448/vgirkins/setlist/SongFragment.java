package com.csci448.vgirkins.setlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by hunti on 3/15/2018.
 */

public class SongFragment extends Fragment{
    private Song mSong;
    private UUID mPerformanceId;    // The id of the performance, if any, we opened this song from. If none, null.

    private EditText mTitleEditText;
    private EditText mArtistEditText;
    private EditText mKeyEditText;
    private EditText mKeyLetterEditText;
    private CheckBox mMinorKeyCheckBox;
    private EditText mChordChartsEditText;
    private EditText mVideoEditText;
    private EditText mDescriptionEditText;
    private TextView mPerformanceTextView;
    private Button mDeleteButton;
    private Button mRemoveFromPerformanceButton;
    private static final String ARG_SONG_ID = "song_id";
    private static final String ARG_PERFORMANCE_ID = "performance_id";

    public static SongFragment newInstance(UUID songId, UUID performanceId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SONG_ID, songId);
        args.putSerializable(ARG_PERFORMANCE_ID, performanceId);

        SongFragment fragment = new SongFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onPause() {
        super.onPause();

        SongLab.get(getActivity()).updateSong(mSong);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID songId = (UUID) getArguments().getSerializable(ARG_SONG_ID);
        mPerformanceId = (UUID) getArguments().getSerializable(ARG_PERFORMANCE_ID);
        mSong = SongLab.get(getActivity()).getSong(songId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dsong, container, false);

        mTitleEditText = v.findViewById(R.id.dsTitle);
        mTitleEditText.setText(mSong.getTitle());
        mTitleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mSong.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Intentionally left blank
            }
        });

        mArtistEditText = v.findViewById(R.id.dsArtist);
        mArtistEditText.setText(mSong.getArtist());
        mArtistEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mSong.setArtist(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Intentionally left blank
            }
        });

        mKeyEditText = v.findViewById(R.id.dsKey);
        mKeyEditText.setText(Character.toString(mSong.getKey()));  // Cast to string
        mKeyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mSong.setKey(charSequence.length() == 0 ? 'C' : charSequence.charAt(0));   // FIXME should learn to expect only 1 character
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Intentionally left blank
            }
        });

        // TODO allow editing of key
        mKeyLetterEditText = v.findViewById(R.id.dsKeyLetter);
        mKeyLetterEditText.setText(mSong.isSharpKey() ? "♯" : "");
        mKeyLetterEditText.setText(mSong.isFlatKey() ? "♭" : "");

        mMinorKeyCheckBox = v.findViewById(R.id.dsIsMinorKey);
        mMinorKeyCheckBox.setChecked(mSong.isMinorKey());
        mMinorKeyCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mSong.setIsMinorKey(b);
            }
        });

        mChordChartsEditText = v.findViewById(R.id.dsChords);
        mChordChartsEditText.setText(mSong.getLinkToChordCharts());
        mChordChartsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mSong.setLinkToChordCharts(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Intentionally left blank
            }
        });

        mVideoEditText = v.findViewById(R.id.dsVideo);
        mVideoEditText.setText(mSong.getLinkToVid());
        mVideoEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mSong.setLinkToVid(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Intentionally left blank
            }
        });

        mDescriptionEditText = v.findViewById(R.id.dsDescript);
        mDescriptionEditText.setText(mSong.getDescription());
        mDescriptionEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mSong.setDescription(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Intentionally left blank
            }
        });

        mPerformanceTextView = v.findViewById(R.id.dsPerformance);
        if (mSong.getPerfId() != null) mPerformanceTextView.setText((PerformanceLab.get(getActivity()).getPerformance(mSong.getPerfId())).getName());
        else mPerformanceTextView.setText("This song does not belong to a performance");    // FIXME hardcoded string

        mDeleteButton = v.findViewById(R.id.dsDeleteButton);
        mDeleteButton.setVisibility(mPerformanceId == null ? View.VISIBLE : View.INVISIBLE);    // Should not be able to delete if just viewing from performance
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SongLab.get(getActivity()).deleteSong(mSong.getId());
                getActivity().onBackPressed();
            }
        });

        mRemoveFromPerformanceButton = v.findViewById(R.id.dsRemoveButton);
        mRemoveFromPerformanceButton.setVisibility(mPerformanceId == null ? View.INVISIBLE : View.VISIBLE);  // Can only remove songs from performance list
        mRemoveFromPerformanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSong.setPerfId(null);  // Remove from performance
                SongLab.get(getActivity()).updateSong(mSong);
                getActivity().onBackPressed();
            }
        });


        return v;
    }
}

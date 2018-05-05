package com.csci448.vgirkins.setlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
    private static final String ARG_SONG_ID = "song_id";

    public static SongFragment newInstance(UUID songId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SONG_ID, songId);

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
        UUID songId = (UUID) getActivity().getIntent().getSerializableExtra(SongActivity.EXTRA_SONG_ID);
        mSong = SongLab.get(getActivity()).getSong(songId);
        if (mSong == null) {
            mSong = new Song(UUID.randomUUID());  // FIXME hopefully this never happens
        }
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
        else mPerformanceTextView.setText("This song does not belong to a performance");

        mDeleteButton = v.findViewById(R.id.dsDeleteButton);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SongLab.get(getActivity()).deleteSong(mSong.getId());
                getActivity().onBackPressed();
            }
        });


        return v;
    }
}

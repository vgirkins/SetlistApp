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
    private TextView mKeyTextView;
    private TextView mKeyLetterTextView;
    private CheckBox mMinorKeyCheckBox;
    private EditText mChordChartsEditText;
    private EditText mVideoEditText;
    private EditText mDescriptionEditText;
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

        mKeyTextView = v.findViewById(R.id.dsKey);
        mKeyTextView.setText(Character.toString(mSong.getKey()));  // Cast to string
        mKeyTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mSong.setKey(charSequence.length() == 0 ? 'C' : charSequence.charAt(0));
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Intentionally left blank
            }
        });

        mKeyLetterTextView = v.findViewById(R.id.dsKeyLetter);
        if (mSong.isSharpKey()) {
            mKeyLetterTextView.setText("♯");
        }
        else if (mSong.isFlatKey()) {
            mKeyLetterTextView.setText("♭");
        }

        mMinorKeyCheckBox = v.findViewById(R.id.dsIsMinorKey);
        mMinorKeyCheckBox.setChecked(mSong.isMinorKey());

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
                //mSong.setPerfId(null);  // Remove from performance
                //SongLab.get(getActivity()).updateSong(mSong);
                PerformanceSongXRefLab.get(getActivity()).dissociateSong(mPerformanceId, mSong.getId());
                getActivity().onBackPressed();
            }
        });


        return v;
    }
}

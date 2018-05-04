package com.csci448.vgirkins.setlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.UUID;

/**
 * Created by hunti on 3/15/2018.
 */

public class SongFragment extends Fragment{
    private Song mSong;

    private TextView mTitleTextView;
    private TextView mArtistTextView;
    private TextView mKeyTextView;
    private TextView mKeyLetterTextView;
    private CheckBox mMinorKeyCheckBox;
    private TextView mChordChartsTextView;
    private TextView mVideoTextView;
    private TextView mDescriptionTextView;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID songId = (UUID) getArguments().getSerializable(ARG_SONG_ID);
        mSong = SongLab.get(getActivity()).getSong(songId);
        if (mSong == null) mSong = new Song();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dsong, container, false);

        mTitleTextView = v.findViewById(R.id.dsTitle);
        mTitleTextView.setText(mSong.getTitle());

        mArtistTextView = v.findViewById(R.id.dsArtist);
        mArtistTextView.setText(mSong.getArtist());

        mKeyTextView = v.findViewById(R.id.dsKey);
        mKeyTextView.setText(Character.toString(mSong.getKey()));  // Cast to string

        mKeyLetterTextView = v.findViewById(R.id.dsKeyLetter);
        mKeyLetterTextView.setText(mSong.isSharpKey() ? "#" : "");
        mKeyLetterTextView.setText(mSong.isFlatKey() ? "b" : "");

        mMinorKeyCheckBox = v.findViewById(R.id.dsIsMinorKey);
        mMinorKeyCheckBox.setChecked(mSong.isMinorKey());

        mChordChartsTextView = v.findViewById(R.id.dsChords);
        mChordChartsTextView.setText(mSong.getLinkToChordCharts());

        mVideoTextView = v.findViewById(R.id.dsVideo);
        mVideoTextView.setText(mSong.getLinkToVid());

        mDescriptionTextView = v.findViewById(R.id.dsDescript);
        mDescriptionTextView.setText(mSong.getDescription());

        mDeleteButton = v.findViewById(R.id.dsDeleteButton);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO delete song
            }
        });


        return v;
    }
}

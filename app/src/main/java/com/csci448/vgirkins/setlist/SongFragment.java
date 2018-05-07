// citations:   https://dzone.com/articles/playing-sounds-android
//              https://www.youraccompanist.com/free-scales-and-warm-ups/reference-scales

package com.csci448.vgirkins.setlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.UUID;

/**
 * Created by hunti on 3/15/2018.
 */

public class SongFragment extends Fragment{
    private Song mSong;
    private UUID mPerformanceId;    // The id of the performance, if any, we opened this song from. If none, null.

    private EditText mTitleEditText;
    private EditText mArtistEditText;
    private Spinner mKeyLetterDropdown;
    private Spinner mKeyIntervalDropdown;
    private CheckBox mMinorKeyCheckBox;
    private Button mDemoKeyButton;
    private EditText mChordChartsEditText;
    private EditText mVideoEditText;
    private EditText mDescriptionEditText;
    private Button mDeleteButton;
    private Button mRemoveFromPerformanceButton;

    // MP3 file ids
    public static final int A = R.raw.a;
    public static final int Bf = R.raw.bf;
    public static final int B = R.raw.b;
    public static final int C = R.raw.c;
    public static final int Df = R.raw.df;
    public static final int D = R.raw.d;
    public static final int Ef = R.raw.ef;
    public static final int E = R.raw.e;
    public static final int F = R.raw.f;
    public static final int Gf = R.raw.gf;
    public static final int G = R.raw.g;
    public static final int Af = R.raw.af;

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
        boolean viewingFromPerformance = mPerformanceId != null;    // If viewing from performance, song should not be editable

        mTitleEditText = v.findViewById(R.id.dsTitle);
        mTitleEditText.setText(mSong.getTitle());
        mTitleEditText.setEnabled(!viewingFromPerformance);
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
        mArtistEditText.setEnabled(!viewingFromPerformance);
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



        mKeyLetterDropdown = v.findViewById(R.id.dsKeyLetter);
        ArrayAdapter<CharSequence> keyLetterAdapter = ArrayAdapter.createFromResource(getContext(), R.array.songKeys_array, android.R.layout.simple_spinner_item);
        keyLetterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mKeyLetterDropdown.setAdapter(keyLetterAdapter);
        mKeyLetterDropdown.setSelection(mSong.getKey() - 'A');  // Find the correct key
        mKeyLetterDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                mSong.setKey((char)('A' + pos));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mKeyIntervalDropdown = v.findViewById(R.id.dsKeyInterval);
        ArrayAdapter<CharSequence> keyIntervalAdapter = ArrayAdapter.createFromResource(getContext(), R.array.songIntervalArray, android.R.layout.simple_spinner_item);
        keyIntervalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mKeyIntervalDropdown.setAdapter(keyIntervalAdapter);
        // Find the correct interval
        if (mSong.isSharpKey()) mKeyIntervalDropdown.setSelection(1);
        else if (mSong.isFlatKey()) mKeyIntervalDropdown.setSelection(2);
        else mKeyIntervalDropdown.setSelection(0);
        mKeyIntervalDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                mSong.setIsSharpKey(pos == 1);
                mSong.setIsFlatKey(pos == 2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        /*mKeyTextView = v.findViewById(R.id.dsKeyLetter);
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

        mKeyLetterTextView = v.findViewById(R.id.dsKeyInterval);
        if (mSong.isSharpKey()) {
            mKeyLetterTextView.setText("♯");
        }
        else if (mSong.isFlatKey()) {
            mKeyLetterTextView.setText("♭");
        }*/

        mMinorKeyCheckBox = v.findViewById(R.id.dsIsMinorKey);
        mMinorKeyCheckBox.setChecked(mSong.isMinorKey());
        mMinorKeyCheckBox.setEnabled(!viewingFromPerformance);
        mMinorKeyCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mSong.setIsMinorKey(mMinorKeyCheckBox.isChecked());
            }
        });

        mDemoKeyButton = v.findViewById(R.id.dsDemoKey);
        mDemoKeyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int note = 0;
                // Play the correct note
                switch (mSong.getKey()) {
                    case 'A':
                        if (mSong.isFlatKey()) {
                            note = Af;
                        }
                        else if (mSong.isSharpKey()) {
                            note = Bf;
                        }
                        else {
                            note = A;
                        }
                        break;
                    case 'B':
                        if (mSong.isFlatKey()) {
                            note = Bf;
                        }
                        else if (mSong.isSharpKey()) {
                            note = C;
                        }
                        else {
                            note = B;
                        }
                        break;
                    case 'C':
                        if (mSong.isFlatKey()) {
                            note = B;
                        }
                        else if (mSong.isSharpKey()) {
                            note = Df;
                        }
                        else {
                            note = C;
                        }
                        break;
                    case 'D':
                        if (mSong.isFlatKey()) {
                            note = Df;
                        }
                        else if (mSong.isSharpKey()) {
                            note = Ef;
                        }
                        else {
                            note = D;
                        }
                        break;
                    case 'E':
                        if (mSong.isFlatKey()) {
                            note = Ef;
                        }
                        else if (mSong.isSharpKey()) {
                            note = F;
                        }
                        else {
                            note = E;
                        }
                        break;
                    case 'F':
                        if (mSong.isFlatKey()) {
                            note = E;
                        }
                        else if (mSong.isSharpKey()) {
                            note = Gf;
                        }
                        else {
                            note = F;
                        }
                        break;
                    case 'G':
                        if (mSong.isFlatKey()) {
                            note = Gf;
                        }
                        else if (mSong.isSharpKey()) {
                            note = Af;
                        }
                        else {
                            note = G;
                        }
                        break;
                }
                SoundPlayer.playSound(getActivity(), note);
            }
        });

        mChordChartsEditText = v.findViewById(R.id.dsChords);
        mChordChartsEditText.setText(mSong.getLinkToChordCharts());
        mChordChartsEditText.setEnabled(!viewingFromPerformance);
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
        mVideoEditText.setEnabled(!viewingFromPerformance);
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
        mDescriptionEditText.setEnabled(!viewingFromPerformance);
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
        mDeleteButton.setVisibility(viewingFromPerformance ? View.INVISIBLE : View.VISIBLE);    // Should not be able to delete if just viewing from performance
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SongLab.get(getActivity()).deleteSong(mSong.getId());
                PerformanceSongXRefLab.get(getActivity()).onDeleteSong(mSong.getId());
                Toast.makeText(getActivity(), mSong.getTitle() + " deleted", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }
        });

        mRemoveFromPerformanceButton = v.findViewById(R.id.dsRemoveButton);
        mRemoveFromPerformanceButton.setVisibility(viewingFromPerformance ? View.VISIBLE : View.INVISIBLE);  // Can only remove songs from performance list
        mRemoveFromPerformanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mSong.setPerfId(null);  // Remove from performance
                //SongLab.get(getActivity()).updateSong(mSong);
                PerformanceSongXRefLab.get(getActivity()).dissociateSong(mPerformanceId, mSong.getId());
                Toast.makeText(getActivity(), "Removed song from performance", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }
        });


        return v;
    }

}

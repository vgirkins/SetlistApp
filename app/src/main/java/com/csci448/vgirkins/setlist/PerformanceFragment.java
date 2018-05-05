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
import android.widget.RadioButton;

import java.util.UUID;

public class PerformanceFragment extends Fragment {
    private Performance mPerformance;

    private RadioButton mPracticeRadio;
    private RadioButton mPerformanceRadio;
    private EditText mNameField;
    private EditText mBandField;
    private EditText mDateField;
    private EditText mTimeField;
    private RadioButton mAmRadio;
    private RadioButton mPmRadio;
    private EditText mLocationField;
    private EditText mDescriptionField;
    private Button mDeleteButton;


    private static final String ARG_PERFORMANCE_ID = "performance_id";

    public static PerformanceFragment newInstance(UUID performanceId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PERFORMANCE_ID, performanceId);

        PerformanceFragment fragment = new PerformanceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onPause() {
        super.onPause();

        PerformanceLab.get(getActivity()).updatePerformance(mPerformance);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID performanceId = (UUID) getActivity().getIntent().getSerializableExtra(PerformanceActivity.EXTRA_PERFORMANCE_ID);
        mPerformance = PerformanceLab.get(getActivity()).getPerformance(performanceId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dperf, container, false);

        mPracticeRadio = v.findViewById(R.id.dpPracRadio);
        mPracticeRadio.setChecked(mPerformance.isPractice());
        mPracticeRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mPerformanceRadio.setChecked(!mPracticeRadio.isChecked());
                mPerformance.setIsPractice(mPracticeRadio.isChecked());
            }
        });

        mPerformanceRadio = v.findViewById(R.id.dpPerfRadio);
        mPerformanceRadio.setChecked(!mPerformance.isPractice());
        mPerformanceRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mPracticeRadio.setChecked(!mPerformanceRadio.isChecked());
                mPerformance.setIsPractice(!mPerformanceRadio.isChecked());
            }
        });

        mNameField = v.findViewById(R.id.dpName);
        mNameField.setText(mPerformance.getName());
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPerformance.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Intentionally left blank
            }
        });

        mBandField = v.findViewById(R.id.dpBand);
        mBandField.setText(mPerformance.getBandName());
        mBandField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPerformance.setBandName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Intentionally left blank
            }
        });

        mDateField = v.findViewById(R.id.dpDate);
        mDateField.setText(mPerformance.getDate());
        mDateField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPerformance.setDate(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Intentionally left blank
            }
        });

        mTimeField = v.findViewById(R.id.dpTime);
        mTimeField.setText(mPerformance.getTime());
        mTimeField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPerformance.setTime(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Intentionally left blank
            }
        });

        mAmRadio = v.findViewById(R.id.dpAMRadio);
        mAmRadio.setChecked(mPerformance.isTimeAM());
        mAmRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mPmRadio.setChecked(!mAmRadio.isChecked());
                mPerformance.setTimeIsAM(mAmRadio.isChecked());
            }
        });

        mPmRadio = v.findViewById(R.id.dpPMRadio);
        mPmRadio.setChecked(!mPerformance.isTimeAM());
        mPmRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mAmRadio.setChecked(!mPmRadio.isChecked());
                mPerformance.setTimeIsAM(!mPmRadio.isChecked());
            }
        });

        mLocationField = v.findViewById(R.id.dpLocation);
        mLocationField.setText(mPerformance.getLocation());
        mLocationField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPerformance.setLocation(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Intentionally left blank
            }
        });

        mDescriptionField = v.findViewById(R.id.dpDescription);
        mDescriptionField.setText(mPerformance.getDescription());
        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPerformance.setDescription(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Intentionally left blank
            }
        });

        mDeleteButton = v.findViewById(R.id.dpDeleteButton);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformanceLab.get(getActivity()).deletePerformance(mPerformance.getId());
            }
        });



        return v;
    }
}

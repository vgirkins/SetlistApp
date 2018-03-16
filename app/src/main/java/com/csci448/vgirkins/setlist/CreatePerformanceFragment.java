package com.csci448.vgirkins.setlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Tori on 3/14/2018.
 */

public class CreatePerformanceFragment extends Fragment {
    private static final String EXTRA_IS_PRACTICE = "com.csci448.vgirkins.setlist.is_practice";
    private static final String EXTRA_PERFORMANCE_NAME = "com.csci448.vgirkins.setlist.performance_name";
    private static final String EXTRA_PERFORMANCE_BAND_NAME = "com.csci448.vgirkins.setlist.band_name";
    private static final String EXTRA_PERFORMANCE_DATE = "com.csci448.vgirkins.setlist.peformance_date";
    private static final String EXTRA_PERFORMANCE_TIME = "com.csci448.vgirkins.setlist.performance_time";
    private static final String EXTRA_PERFORMANCE_TIME_IS_AM = "com.csci448.vgirkins.setlist.performance_time_is_am";   // For AM vs PM performance times
    private static final String EXTRA_PERFORMANCE_LOCATION = "com.csci448.vgirkins.setlist.peformance_location";
    private static final String EXTRA_PERFORMANCE_DESCRIPTION = "com.csci448.vgirkins.setlist.performance_description";

    private boolean isPractice;
    private String performanceName;
    private String bandName;
    private String performanceDate;
    private String performanceTime;
    private boolean timeIsAM;
    private String location;
    private String description;

    private RadioButton practiceRadio;
    private RadioButton performanceRadio;
    private EditText nameField;
    private EditText bandField;
    private EditText dateField;
    private EditText timeField;
    private RadioButton amRadio;
    private RadioButton pmRadio;
    private EditText locationField;
    private EditText descriptionField;
    private Button createButton;
    private Button cancelButton;

    public static CreatePerformanceFragment newInstance() {
        Bundle args = new Bundle();

        // Any arguments needed can be added here
        // FIXME if we don't need any arguments we don't need the Bundle

        CreatePerformanceFragment frag = new CreatePerformanceFragment();
        frag.setArguments(args);
        return frag;
    }

    public void setReturnResult() {
        Intent resultIntent = new Intent();

        isPractice = practiceRadio.isChecked();
        performanceName = nameField.getText().toString();
        bandName = bandField.getText().toString();
        performanceDate = dateField.getText().toString();
        performanceTime = timeField.getText().toString();
        timeIsAM = amRadio.isChecked();
        location = locationField.getText().toString();
        description = descriptionField.getText().toString();

        resultIntent.putExtra(EXTRA_IS_PRACTICE, isPractice);
        resultIntent.putExtra(EXTRA_PERFORMANCE_NAME, performanceName == null ? "My Performance" : performanceName);
        resultIntent.putExtra(EXTRA_PERFORMANCE_BAND_NAME, bandName == null ? "My Band" : bandName);
        resultIntent.putExtra(EXTRA_PERFORMANCE_DATE, performanceDate == null ? "No date set" : performanceDate);
        resultIntent.putExtra(EXTRA_PERFORMANCE_TIME, performanceTime == null ? "No time set" : performanceTime);
        resultIntent.putExtra(EXTRA_PERFORMANCE_TIME_IS_AM, timeIsAM);
        resultIntent.putExtra(EXTRA_PERFORMANCE_LOCATION, location == null ? "No location set" : location);
        resultIntent.putExtra(EXTRA_PERFORMANCE_DESCRIPTION, description == null ? "" : description);

        getActivity().setResult(Activity.RESULT_OK, resultIntent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            // For rotation later. TODO recover needed values and store in instance variables
        }
        else {
            isPractice = getArguments().getBoolean(EXTRA_IS_PRACTICE);
            performanceName = getArguments().getString(EXTRA_PERFORMANCE_NAME);
            bandName = getArguments().getString(EXTRA_PERFORMANCE_BAND_NAME);
            performanceDate = getArguments().getString(EXTRA_PERFORMANCE_DATE);
            performanceTime = getArguments().getString(EXTRA_PERFORMANCE_TIME);
            timeIsAM = getArguments().getBoolean(EXTRA_PERFORMANCE_TIME_IS_AM);
            location = getArguments().getString(EXTRA_PERFORMANCE_LOCATION);
            description = getArguments().getString(EXTRA_PERFORMANCE_DESCRIPTION);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // FIXME is this needed?

        View view = inflater.inflate(R.layout.fragment_cperf, container, false);

        practiceRadio = view.findViewById(R.id.cpPracRadio);
        performanceRadio = view.findViewById(R.id.cpPerfRadio);

        // Exactly one of these should be checked at any given time.
        // Logic is not scalable if another radio button is added.
        practiceRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                performanceRadio.setChecked(!practiceRadio.isChecked());
            }
        });
        performanceRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                practiceRadio.setChecked(!performanceRadio.isChecked());
            }
        });

        nameField = view.findViewById(R.id.cpName);

        bandField = view.findViewById(R.id.cpBand);

        dateField = view.findViewById(R.id.cpDate);

        timeField = view.findViewById(R.id.cpTime);

        amRadio = view.findViewById(R.id.cpAMRadio);
        pmRadio = view.findViewById(R.id.cpPMRadio);

        // Exactly one of these should be checked at any given time.
        // Logic is not scalable if another radio button is added.
        amRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                pmRadio.setChecked(!amRadio.isChecked());
            }
        });
        pmRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                amRadio.setChecked(!pmRadio.isChecked());
            }
        });

        locationField = view.findViewById(R.id.cpLocation);

        descriptionField = view.findViewById(R.id.cpDescription);

        createButton = view.findViewById(R.id.cpCreateButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setReturnResult();
                getActivity().onBackPressed();
            }
        });

        cancelButton = view.findViewById(R.id.cpCancelButton);
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
        isPractice = practiceRadio.isChecked();
        performanceName = nameField.getText().toString();
        bandName = bandField.getText().toString();
        performanceDate = dateField.getText().toString();
        performanceTime = timeField.getText().toString();
        timeIsAM = amRadio.isChecked();
        location = locationField.getText().toString();
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(EXTRA_IS_PRACTICE, isPractice);
        savedInstanceState.putString(EXTRA_PERFORMANCE_NAME, performanceName);
        savedInstanceState.putString(EXTRA_PERFORMANCE_BAND_NAME, bandName);
        savedInstanceState.putString(EXTRA_PERFORMANCE_DATE, performanceDate);
        savedInstanceState.putString(EXTRA_PERFORMANCE_TIME, performanceTime);
        savedInstanceState.putBoolean(EXTRA_PERFORMANCE_TIME_IS_AM, timeIsAM);
        savedInstanceState.putString(EXTRA_PERFORMANCE_LOCATION, location);
        savedInstanceState.putString(EXTRA_PERFORMANCE_DESCRIPTION, description);
    }

}

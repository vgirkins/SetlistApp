package com.csci448.vgirkins.setlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Created by Tori on 3/14/2018.
 */

public class CreatePerformanceFragment extends Fragment {

    private RadioButton practiceRadio;
    private RadioButton performanceRadio;
    private EditText nameField;
    private EditText bandField;
    private EditText dateField;
    private EditText timeField;
    private RadioButton amRadio;
    private RadioButton pmRadio;
    private EditText locationField;
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

        View view = inflater.inflate(R.layout.fragment_cperf, container, false);

        practiceRadio = view.findViewById(R.id.cpPracRadio);

        performanceRadio = view.findViewById(R.id.cpPerfRadio);

        nameField = view.findViewById(R.id.cpName);

        bandField = view.findViewById(R.id.cpBand);

        timeField = view.findViewById(R.id.cpTime);

        amRadio = view.findViewById(R.id.cpAMRadio);

        pmRadio = view.findViewById(R.id.cpPMRadio);

        locationField = view.findViewById(R.id.cpLocation);

        createButton = view.findViewById(R.id.cpCreateButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
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
        super.onSaveInstanceState(savedInstanceState);
    }

}

package com.csci448.vgirkins.setlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.UUID;

public class PerformanceFragment extends Fragment {
    private Performance mPerformance;

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
    private Button deleteButton;


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
        if (mPerformance == null) {
            mPerformance = new Performance(UUID.randomUUID());  // FIXME hopefully this never happens
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dperf, container, false);

        // TODO add listeners

        return v;
    }
}

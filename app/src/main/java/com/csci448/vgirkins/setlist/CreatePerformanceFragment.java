package com.csci448.vgirkins.setlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Tori on 3/14/2018.
 */

public class CreatePerformanceFragment extends Fragment {

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
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // FIXME is this needed?

        View view = inflater.inflate(R.layout.fragment_cperf, container, false);

        // TODO get references to fields

        return view;
    }


}

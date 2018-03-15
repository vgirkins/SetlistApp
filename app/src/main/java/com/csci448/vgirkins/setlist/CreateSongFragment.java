package com.csci448.vgirkins.setlist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Tori on 3/14/2018.
 */

public class CreateSongFragment extends Fragment {

    private TextView songTitleField;
    private TextView artistTitleField;
    private TextView keyField;
    private Spinner keyLetterDropdown;
    private Spinner keyIntervalDropdown;
    private TextView chordsLinkField;
    private TextView videoLinkField;
    private TextView descriptionField;
    private Button createButton;
    private Button cancelButton;

    public static CreateSongFragment newInstance() {
        Bundle args = new Bundle();

        // Any arguments needed can be added here
        // FIXME if we don't need any arguments we don't need the Bundle

        CreateSongFragment frag = new CreateSongFragment();
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

        View view = inflater.inflate(R.layout.fragment_csong, container, false);

        // TODO get references to fields

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

}

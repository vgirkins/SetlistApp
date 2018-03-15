package com.csci448.vgirkins.setlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;

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
}

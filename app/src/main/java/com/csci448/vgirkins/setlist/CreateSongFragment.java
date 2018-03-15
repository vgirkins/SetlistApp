package com.csci448.vgirkins.setlist;


import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Tori on 3/14/2018.
 */

public class CreateSongFragment extends Fragment {

    public static CreateSongFragment newInstance() {
        Bundle args = new Bundle();

        // Any arguments needed can be added here
        // FIXME if we don't need any arguments we don't need the Bundle

        CreateSongFragment frag = new CreateSongFragment();
        frag.setArguments(args);
        return frag;
    }

}

package kklions.mazesolver.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kklions.mazesolver.R;

/**
 * Options Menu
 *
 * Created by kliok002 on 11/18/17.
 */

public class OptionsFragment extends Fragment {

    private View fragmentView;

    public static OptionsFragment newInstance() {
        return new OptionsFragment();
    }

    public OptionsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.options_menu, container, false);
        return fragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}

package kklions.mazesolver.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kklions.mazesolver.R;

/**
 * This fragment exists because I'm too lazy to make a proper good looking loading wheel right now
 *
 * Created by Kevin Klions on 1/22/18.
 */

public class LoadingFragment extends Fragment {
    private View view;

    public static LoadingFragment newInstance() {
        return new LoadingFragment();
    }

    public LoadingFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.loading_screen_layout, container, false);
        return view;
    }

    public interface LoadingNavigationListnener {
        void navigateToSolveScreen();
    }
}

package kklions.mazesolver.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kklions.mazesolver.R;

/**
 * Fragment for displaying the maze
 * Currently Unused
 *
 * Created by kliok002 on 11/18/17.
 */

public class MazeDisplayFragment extends Fragment {
    private View fragmentView;

    public MazeDisplayFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.maze_view, container);
        return fragmentView;
    }
}

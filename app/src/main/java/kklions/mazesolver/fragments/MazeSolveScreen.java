package kklions.mazesolver.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kklions.mazesolver.R;
import kklions.mazesolver.enums.Algorithm;

/**
 * Maze Solving screen
 *
 * Created by kliok002 on 11/18/17.
 */

public class MazeSolveScreen extends Fragment {

    private static final String methodKey = "method";
    private View fragmentView;
    private String solveMethod;

    public static MazeSolveScreen newInstance(String method) {
        MazeSolveScreen fragment = new MazeSolveScreen();
        Bundle bundle = new Bundle();
        bundle.putString(methodKey, method);
        fragment.setArguments(bundle);
        return fragment;
    }

    public MazeSolveScreen() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        solveMethod = savedInstanceState.getString(methodKey);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.solve_maze_screen, container);
        return fragmentView;
    }

//    public interface Navigation
}

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

    private View fragmentView;
    private Algorithm solveMethod;

    public static MazeSolveScreen newInstance(Algorithm method) {
        return new MazeSolveScreen(method);
    }

    public MazeSolveScreen() {

    }

    public MazeSolveScreen(Algorithm method) {
        solveMethod = method;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.solve_maze_screen, container);
        return fragmentView;
    }

//    public interface Navigation
}

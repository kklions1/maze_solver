package kklions.mazesolver.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import kklions.mazesolver.R;
import kklions.mazesolver.controller.MazeController;
import kklions.mazesolver.manager.MazeSolverDataManager;
import kklions.mazesolver.manager.accessors.DataManagerAccessor;
import kklions.mazesolver.model.MazeConfiguration;

/**
 * Maze Solving screen
 *
 * Created by kliok002 on 11/18/17.
 */

public class MazeSolveFragment extends Fragment {

    private static final String configurationKey = "configuration";
    private View fragmentView;
    private MazeConfiguration configuration;
    private MazeSolverDataManager dataManager;
    private MazeController mazeController;

    public static MazeSolveFragment newInstance(MazeConfiguration configuration) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(configurationKey, configuration);
        MazeSolveFragment fragment = new MazeSolveFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public MazeSolveFragment() {
        // No-op
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            configuration = (MazeConfiguration) savedInstanceState.getSerializable(configurationKey);
        } else {
            configuration = (MazeConfiguration) getArguments().getSerializable(configurationKey);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            dataManager = ((DataManagerAccessor) context).provideDataManager();
        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new IllegalStateException("The Activity does not implement the correct provider");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.maze_view, container, false);
        GridLayout mazeDisplay = fragmentView.findViewById(R.id.maze_view);
        mazeDisplay.setColumnCount(configuration.getWidth());
        mazeDisplay.setRowCount(configuration.getHeight());

        mazeController = new MazeController(configuration, dataManager, fragmentView, getContext());
        mazeController.initMazeView();
        return fragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mazeController.solveMaze();
    }
}

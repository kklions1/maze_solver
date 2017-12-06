package kklions.mazesolver.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import kklions.mazesolver.R;
import kklions.mazesolver.adapters.MazeAdapter;
import kklions.mazesolver.managers.MazeSolverDataManager;
import kklions.mazesolver.managers.accessors.DataManagerAccessor;
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
    private MazeAdapter mazeAdapter;

    public static MazeSolveFragment newInstance(MazeConfiguration configuration) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(configurationKey, configuration);
        MazeSolveFragment fragment = new MazeSolveFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public MazeSolveFragment() { }

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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            dataManager = ((DataManagerAccessor) getContext()).provideDataManager();
        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new IllegalStateException("The Data manager does not implement the correct data accessor");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.solve_maze_screen, container, false);
        GridLayout mazeDisplay = fragmentView.findViewById(R.id.maze_display_view);
        mazeDisplay.setColumnCount(configuration.getWidth());
        mazeDisplay.setRowCount(configuration.getHeight());
        mazeAdapter = new MazeAdapter(configuration, dataManager, fragmentView);
        mazeAdapter.initMaze();
        return fragmentView;
    }
}

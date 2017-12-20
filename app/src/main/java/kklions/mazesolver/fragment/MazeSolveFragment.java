package kklions.mazesolver.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kklions.mazesolver.R;
import kklions.mazesolver.presenter.MazePresenter;
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
    private MazePresenter mazePresenter;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.maze_view_layout, container, false);
        mazePresenter = new MazePresenter(configuration, fragmentView, getContext());
        mazePresenter.initMazeView();
        return fragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mazePresenter.solveMaze();
    }
}

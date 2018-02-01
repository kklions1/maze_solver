package kklions.mazesolver.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kklions.mazesolver.R;
import kklions.mazesolver.presenter.MazePresenter;
import kklions.mazesolver.model.MazeConfiguration;

/**
 * Maze Solving screen
 *
 * Created by Kevin Klions on 11/18/17.
 */

public class MazeFragment extends BaseFragment {

//    private static final String configurationKey = "configuration";
//    private MazeConfiguration configuration;
    private MazePresenter mazePresenter;

    public static MazeFragment newInstance(MazeConfiguration configuration) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(configurationKey, configuration);
        MazeFragment fragment = new MazeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public MazeFragment() {
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
        View fragmentView = inflater.inflate(R.layout.maze_view_layout, container, false);
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

package kklions.mazesolver.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;


import com.google.gson.Gson;

import kklions.mazesolver.R;
import kklions.mazesolver.adapters.MazeAdapter;
import kklions.mazesolver.managers.accessors.DataManagerAccessor;
import kklions.mazesolver.managers.accessors.MazeGenerator;
import kklions.mazesolver.managers.accessors.MazeSolvingAccessor;
import kklions.mazesolver.model.MazeConfiguration;

/**
 * Maze Solving screen
 *
 * Created by kliok002 on 11/18/17.
 */

public class MazeSolveScreen extends Fragment {

    private static final String configurationKey = "configuration";
    private View fragmentView;
    private MazeConfiguration configuration;
    private MazeGenerator mazeGenerator;
    private MazeAdapter mazeAdapter;

    public static MazeSolveScreen newInstance(MazeConfiguration configuration) {
        MazeSolveScreen fragment = new MazeSolveScreen();
        Bundle bundle = new Bundle();
        String configurationJson = new Gson().toJson(configuration);
        bundle.putString(configurationKey, configurationJson);
        fragment.setArguments(bundle);
        return fragment;
    }

    public MazeSolveScreen() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            String configurationJson = savedInstanceState.getString(configurationKey);
            configuration = new Gson().fromJson(configurationJson, MazeConfiguration.class);
        }
        mazeAdapter = new MazeAdapter(configuration, getContext(), fragmentView);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            mazeGenerator = ((DataManagerAccessor) getContext()).provideDataManager();
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
        mazeAdapter = new MazeAdapter(configuration, getContext(), fragmentView);
        mazeGenerator.generateMaze(configuration.getHeight(), configuration.getWidth(), configuration.getPercentMissing());
        return fragmentView;
    }
}

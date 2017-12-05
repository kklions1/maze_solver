package kklions.mazesolver.adapters;

import android.content.Context;
import android.view.View;

import kklions.mazesolver.managers.MazeSolverDataManagerAccessor;
import kklions.mazesolver.model.MazeConfiguration;

/**
 * class responsible for dynamically updating the UI for solving the maze
 *
 * Created by Kevin Klions on 11/18/17.
 */

public class MazeAdapter {

    private MazeSolverDataManagerAccessor dataManager;
    private MazeConfiguration configuration;
    private Context context;
    private View fragmentView;


    public MazeAdapter(MazeConfiguration configuration, Context context, View fragmentView) {
        this.configuration = configuration;
        this.context = context;
        this.fragmentView = fragmentView;
        dataManager
    }

    public void runAlgorithmAsync() {

    }
}

package kklions.mazesolver.adapters;

import android.content.Context;
import android.view.View;

import java.util.Map;

import kklions.mazesolver.managers.MazeSolverDataManager;
import kklions.mazesolver.managers.accessors.DataManagerAccessor;
import kklions.mazesolver.model.Cell;
import kklions.mazesolver.model.MazeConfiguration;

/**
 * class responsible for dynamically updating the UI for solving the maze
 *
 * Created by Kevin Klions on 11/18/17.
 */

public class MazeAdapter {

    private MazeSolverDataManager dataManager;
    private MazeConfiguration configuration;
    private View fragmentView;
    private Cell[][] maze;


    public MazeAdapter(MazeConfiguration configuration, MazeSolverDataManager dataManager, View fragmentView) {
        this.configuration = configuration;
        this.fragmentView = fragmentView;
        this.dataManager = dataManager;
    }

    /**
     * Initializes the maze, and then draws it to the fragment's layout
     */
    public void initMaze() {
        maze = dataManager.generateMaze(configuration.getHeight(), configuration.getWidth(), configuration.getPercentMissing());
    }

    protected Map<String, Integer> getHeightAndWidth() {
        Map<String, Integer>
    }
}

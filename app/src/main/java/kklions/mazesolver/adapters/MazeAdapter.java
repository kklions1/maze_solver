package kklions.mazesolver.adapters;

import javax.inject.Inject;

import kklions.mazesolver.managers.MazeSolverDataManager;

/**
 * class responsible for dynamically updating the UI for solving the maze
 *
 * Created by Kevin Klions on 11/18/17.
 */

public class MazeAdapter {
    // Is this where we put the async task for actually running the maze?  so this class would
    // need an instance of the data manager injected

    @Inject
    MazeSolverDataManager dataManager;

    MazeAdapter() {

    }

    public void runAlgorithmAsync() {

    }
}

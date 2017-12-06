package kklions.mazesolver.adapters;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.GridLayout;

import kklions.mazesolver.R;
import kklions.mazesolver.enums.Algorithm;
import kklions.mazesolver.managers.MazeSolverDataManager;
import kklions.mazesolver.model.MazeConfiguration;

/**
 * class responsible for dynamically updating the UI for solving the maze
 *
 * Created by Kevin Klions on 11/18/17.
 */

public class MazeAdapter {

    private final MazeSolverDataManager dataManager;
    private MazeConfiguration configuration;
    private View fragmentView;
    private GridLayout mazeLayout;
    private View[][] mazeColors;


    public MazeAdapter(@NonNull MazeConfiguration configuration, @NonNull MazeSolverDataManager dataManager, @NonNull View fragmentView) {
        this.configuration = configuration;
        this.fragmentView = fragmentView;
        this.dataManager = dataManager;
    }

    /**
     * gets the data manager to generate the maze, and then draws the maze to the UI
     */
    public void initMaze() {
        dataManager.generateMaze(configuration.getHeight(), configuration.getWidth(), configuration.getPercentMissing());
        mazeLayout = fragmentView.findViewById(R.id.maze_display_view);
        // A reference to each element in the grid layout is needed, so this will get each element in the layout and assign it to an array for easy access
        int index = 0;
        for (int row = 0; row < configuration.getHeight(); row++) {
            for (int col = 0; col < configuration.getWidth(); col++) {
                mazeColors[row][col] = mazeLayout.getChildAt(index);
                index++;
            }
        }
    }

    private void setMazeColors(int row, int col) {

    }

    public void solveMaze() {
        switch (configuration.getMethod()) {
            case Algorithm.BFS:

                break;
            case Algorithm.DFS:

                break;
            case Algorithm.ASTAR:

                break;
            default:

                break;
        }
    }
        // TODO solve the maze
}


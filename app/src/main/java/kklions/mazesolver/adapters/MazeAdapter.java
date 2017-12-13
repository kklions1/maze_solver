package kklions.mazesolver.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

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
    private Context context;
    private GridLayout mazeLayout;
    private TextView[][] mazeColors;


    public MazeAdapter(@NonNull MazeConfiguration configuration, @NonNull MazeSolverDataManager dataManager, @NonNull View fragmentView,
                       @NonNull Context context) {
        this.configuration = configuration;
        this.fragmentView = fragmentView;
        this.dataManager = dataManager;
        this.context = context;
        mazeColors = new TextView[configuration.getHeight()][configuration.getWidth()];
    }

    /**
     *  data manager generates the maze, and then draws the maze to the UI
     */
    public void initMaze() {
        dataManager.generateMaze(configuration.getHeight(), configuration.getWidth(), configuration.getPercentMissing());
        mazeLayout = fragmentView.findViewById(R.id.maze_view);

        initializeGridLayout();
        drawMaze();
    }

    private void initializeGridLayout() {
        for (int row = 0; row < configuration.getHeight(); row++) {
            for (int col = 0; col < configuration.getWidth(); col++) {
                mazeColors[row][col] = new TextView(context);
                mazeColors[row][col].setLayoutParams(new ViewGroup.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT,
                        GridLayout.LayoutParams.MATCH_PARENT));
                mazeLayout.addView(mazeColors[row][col], 50, 50);
            }
        }
    }

    private void drawMaze() {
        for (int row = 0; row < configuration.getHeight(); row++) {
            for (int col = 0; col < configuration.getWidth(); col++) {
                if (row % 2 == 0 && col % 2 == 0) {
                    mazeColors[row][col].setBackgroundColor(Color.BLACK);
                } else {
                    mazeColors[row][col].setBackgroundColor(Color.WHITE);
                }
            }
        }
    }

    private void solveMaze() {
        // TODO figure out how to run different methods on the async tasks
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
}


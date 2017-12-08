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
     *
     *  data manager generates the maze, and then draws the maze to the UI
     */
    public void initMaze() {
        dataManager.generateMaze(configuration.getHeight(), configuration.getWidth(), configuration.getPercentMissing());
        mazeLayout = fragmentView.findViewById(R.id.maze_display_view);

        for (int row = 0; row < configuration.getHeight(); row++) {
            for (int col = 0; col < configuration.getWidth(); col++) {
                mazeColors[row][col] = new TextView(context);
              //  mazeColors[row][col].setLayoutParams(new ViewGroup.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT,
                 //               GridLayout.LayoutParams.MATCH_PARENT));
                mazeLayout.addView(mazeColors[row][col], row, col);
            }
        }

        for (int row = 0; row < configuration.getHeight(); row++) {
            for (int col = 0; col < configuration.getWidth(); col++) {
                if (col == 0) {
                    if (!dataManager.getMazeCell(row, col).right) {
                        mazeColors[row][col + 1].setBackgroundColor(Color.BLACK);
                        //mazeColors[row][col + 1].setVisibility(View.VISIBLE);
                    }
                } else if (!dataManager.getMazeCell(row, col).left) {
                    mazeColors[row][col - 1].setBackgroundColor(Color.BLACK);
                   // mazeColors[row][col - 1].setVisibility(View.VISIBLE);
                }
            }
        }

        mazeLayout.setVisibility(View.VISIBLE);
    }

    public void solveMaze() {
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


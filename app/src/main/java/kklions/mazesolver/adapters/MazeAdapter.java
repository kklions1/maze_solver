package kklions.mazesolver.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
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
       // drawMaze();
        drawMazeThree();
//        drawMazeLikeDavin();
    }

    private void initializeGridLayout() {

        // Get Screen Size and calculate the size of a cell to fill the screen
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;
        int screenWidth = displayMetrics.widthPixels;
        int cellHeight = screenHeight / configuration.getHeight();
        int cellWidth = screenWidth / configuration.getWidth();

        // Initialize the layout with empty TextViews
        for (int row = 0; row < configuration.getHeight(); row++) {
            for (int col = 0; col < configuration.getWidth(); col++) {
                mazeColors[row][col] = new TextView(context);
                mazeColors[row][col].setLayoutParams(new ViewGroup.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT,
                        GridLayout.LayoutParams.MATCH_PARENT));
                mazeLayout.addView(mazeColors[row][col], cellWidth, cellHeight);

                // Set the default color of the border to black
                if (row == 0 || col == 0 || row == configuration.getHeight() - 1 || col == configuration.getWidth() - 1) {
                    mazeColors[row][col].setBackgroundColor(Color.BLACK);
                }
            }
        }
    }

    private void drawMaze() {
        dataManager.findStartAndEnd();
        for (int row = 0; row < configuration.getHeight(); row++) {
            for (int col = 0; col < configuration.getWidth(); col++) {
                if (col == 0) {
                    if (!dataManager.getMazeCell(row, col).right) {
                        mazeColors[row][col + 1].setBackgroundColor(Color.BLACK);
                    }
                } else {
                    if (!dataManager.getMazeCell(row, col).left) {
                        mazeColors[row][col - 1].setBackgroundColor(Color.BLACK);
                    }
                }
            }
        }
    }

    private void drawMazeLikeDavin() {
        // find entrance
        int y = 0;
        int x;
        for (x = 0; x < configuration.getWidth(); x++) {
            if (dataManager.getMazeCell(x, y).top) {
                break;
            }
        }

        for (int row = 0; row < configuration.getHeight(); row++) {
            // Top
            for (int col = 0; col < configuration.getWidth(); col++) {
                mazeColors[row][col].setBackgroundColor(Color.BLACK);
                // Why did I write it this way?
                if (dataManager.getMazeCell(row, col).top) {
                    mazeColors[row][col].setBackgroundColor(Color.WHITE);
                }
            }

            // Side
            for (int col = 1; col < configuration.getWidth(); col++) {
                if (!dataManager.getMazeCell(row, col).left) {
                    mazeColors[row][col].setBackgroundColor(Color.BLACK);
                }
            }

        }
    }

    private void drawMazeThree() {
        // Draw the border


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


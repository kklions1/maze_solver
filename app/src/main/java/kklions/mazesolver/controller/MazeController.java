package kklions.mazesolver.controller;

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
import kklions.mazesolver.manager.MazeSolverDataManager;
import kklions.mazesolver.model.MazeConfiguration;

/**
 * class responsible for dynamically updating the UI for solving the maze
 *
 * Created by Kevin Klions on 11/18/17.
 */

public class MazeController {

    private final MazeSolverDataManager dataManager;
    private MazeConfiguration configuration;
    private View fragmentView;
    private Context context;
    private GridLayout mazeLayout;
    private TextView[][] mazeColors;


    public MazeController(@NonNull MazeConfiguration configuration, @NonNull MazeSolverDataManager dataManager, @NonNull View fragmentView,
                          @NonNull Context context) {
        this.configuration = configuration;
        this.fragmentView = fragmentView;
        this.dataManager = dataManager;
        this.context = context;
        mazeColors = new TextView[configuration.getHeight()][configuration.getWidth()];
    }
    
    public void initMazeView() {
        initDataManager();
        initFragmentView();
        initGridLayout();
        drawMaze();
    }

    private void initFragmentView() {
        mazeLayout = fragmentView.findViewById(R.id.maze_view);
        mazeLayout.setColumnCount(configuration.getWidth());
        mazeLayout.setRowCount(configuration.getHeight());
    }

    private void initDataManager() {
        dataManager.setMazeDimensions(configuration.getHeight(), configuration.getWidth());
        dataManager.generateMaze(configuration.getPercentMissing());
        dataManager.findStartAndEnd();
    }

    private void initGridLayout() {

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
        // set the colors for the start and end
        mazeColors[0][dataManager.getStart().getCol()].setBackgroundColor(Color.WHITE);
        mazeColors[configuration.getHeight() - 1][dataManager.getEnd().getCol()].setBackgroundColor(Color.WHITE);

        // TODO draw the actual damn maze
        // Indices start at 1 to account for the border drawn around the maze
        for (int row = 1; row < configuration.getHeight() - 1; row++) {
            for (int col = 1; col < configuration.getWidth() - 1; col++) {
                if (dataManager.getMazeCell(row - 1, col - 1).left) {
                    mazeColors[row][col - 1].setBackgroundColor(Color.BLACK);
                }
            }
        }
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


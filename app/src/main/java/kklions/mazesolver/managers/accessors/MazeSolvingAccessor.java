package kklions.mazesolver.managers.accessors;

import java.util.List;

import kklions.mazesolver.enums.Direction;
import kklions.mazesolver.model.Cell;

/**
 * Interface for all the algorithms used to solve the mazes
 *
 * Created by kliok002 on 11/18/17.
 */

public interface MazeSolvingAccessor {

    /**
     * Simple breadth first search
     */
    List<Direction> solveBreadthFirstSearch(Cell[][] maze, int height, int width);

    /**
     * Simple depth first search, will return suboptimal paths on imperfect mazes
     */
    List<Direction> solveDepthFirstSearch(Cell[][] maze, int height, int width);

    /**
     * A*, uses absolute distance as the heuristic
     */
    List<Direction> solveAStar(Cell[][] maze, int height, int width);

    /**
     * My first attempt at implementing A*, finds a path very fast in imperfect mazes,
     * but the path is usually not the shortest possible
     * After some research, I found out this is called "Best First Search"
     */
    List<Direction> solveBestFirstSearch(Cell[][] maze, int height, int width);
}

package kklions.mazesolver.managers.accessors;

import kklions.mazesolver.model.Cell;

/**
 * API for generating mazes
 *
 * Created by kliok002 on 11/18/17.
 */

public interface MazeGenerator {
    Cell[][] generateMaze(int height, int width, float percentageMissing);
}

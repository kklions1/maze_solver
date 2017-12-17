package kklions.mazesolver.manager.accessors;

import kklions.mazesolver.manager.MazeSolverDataManager;

/**
 * Interface to provide data manager to fragments from activities
 *
 * Created by kliok002 on 12/4/17.
 */

public interface DataManagerAccessor {
    MazeSolverDataManager provideDataManager();
}

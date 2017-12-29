package kklions.mazesolver.manager.accessors;

import kklions.mazesolver.manager.MazeSolverDataManager;

/**
 * Interface to provide data manager to fragments from the activity
 *
 * Created by Kevin Klions on 12/4/17.
 */

public interface DataManagerAccessor {
    MazeSolverDataManager provideDataManager();
}

package kklions.mazesolver.injection;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kklions.mazesolver.managers.MazeSolverDataManager;

/**
 * Module for maze solving data manager
 *
 * Created by Kevin Klions on 11/25/17.
 */

@Module
public class DataManagerModule {
    private MazeSolverDataManager dataManager;

    @Provides
    @Singleton
    MazeSolverDataManager providesDataManager() {
        return dataManager;
    }
}

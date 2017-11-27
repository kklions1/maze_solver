package kklions.mazesolver.injection;

import javax.inject.Singleton;

import dagger.Component;
import kklions.mazesolver.activity.MazeSolverActivity;
import kklions.mazesolver.adapters.MazeAdapter;
import kklions.mazesolver.injection.AppModule;
import kklions.mazesolver.injection.DataManagerModule;
import kklions.mazesolver.managers.MazeSolverDataManager;

/**
 * Component class for data manager
 *
 * Created by kliok002 on 11/25/17.
 */

@Singleton
@Component(modules = {AppModule.class, DataManagerModule.class})
public interface DataManagerComponent {

    MazeSolverDataManager getDataManager();

    void inject(MazeSolverActivity activity);

    void inject(MazeAdapter adapter);
}

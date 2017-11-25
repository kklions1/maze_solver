package kklions.mazesolver.injection.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Application Module class
 *
 * Created by kliok002 on 11/25/17.
 */

@Module
public class AppModule {
    private Application mazeSolverApplication;

    public AppModule(Application application) {
        mazeSolverApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mazeSolverApplication;
    }
}

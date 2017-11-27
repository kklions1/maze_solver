package kklions.mazesolver.injection;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Application Module class
 *
 * Created by Kevin Klions on 11/25/17.
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

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mazeSolverApplication.getApplicationContext();
    }
}

package kklions.mazesolver.application;

import android.app.Application;

import kklions.mazesolver.injection.DaggerDataManagerComponent;
import kklions.mazesolver.injection.DataManagerComponent;
import kklions.mazesolver.injection.AppModule;
import kklions.mazesolver.injection.DataManagerModule;

/**
 * Application class
 *
 * Created by Kevin Klions on 11/25/17.
 */

public class MazeApplication extends Application {
    private DataManagerComponent dataManagerComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        dataManagerComponent = DaggerDataManagerComponent.builder()
                .appModule(new AppModule(this))
                .dataManagerModule(new DataManagerModule())
                .build();

    }

    public DataManagerComponent getDataManagerComponent() {
        return dataManagerComponent;
    }
}

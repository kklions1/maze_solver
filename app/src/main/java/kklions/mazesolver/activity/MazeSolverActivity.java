package kklions.mazesolver.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import kklions.mazesolver.R;
import kklions.mazesolver.fragment.LoadingFragment;
import kklions.mazesolver.fragment.MazeFragment;
import kklions.mazesolver.fragment.OptionsFragment;
import kklions.mazesolver.manager.MazeSolverDataManager;
import kklions.mazesolver.manager.accessors.DataManagerAccessor;
import kklions.mazesolver.model.MazeConfiguration;

/**
 * Activity responsible for navigation between fragments
 *
 * Created by Kevin Klions on 11/18/17.
 */

public class MazeSolverActivity extends Activity implements OptionsFragment.OptionsNavigationListener,
        LoadingFragment.LoadingNavigationListnener, DataManagerAccessor {

    private MazeSolverDataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        FragmentManager manager = getFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_layout_holder, OptionsFragment.newInstance());
        transaction.addToBackStack(null);
        transaction.commit();

        dataManager = new MazeSolverDataManager();
    }


    @Override
    public void navigateToLoadingScreen(MazeConfiguration configuration) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_layout_holder, MazeFragment.newInstance(configuration));
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void navigateToSolveScreen() {
    }

    @Override
    public MazeSolverDataManager provideDataManager() {
        return dataManager;
    }
}

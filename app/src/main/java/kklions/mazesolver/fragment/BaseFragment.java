package kklions.mazesolver.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import kklions.mazesolver.manager.MazeSolverDataManager;
import kklions.mazesolver.manager.accessors.DataManagerAccessor;
import kklions.mazesolver.model.MazeConfiguration;

/**
 * Base Fragment from which all other fragments in this application extend
 *
 * Created by kevin on 1/31/18.
 */

public abstract class BaseFragment extends Fragment {
    protected MazeSolverDataManager dataManager;
    protected View view;
    protected MazeConfiguration configuration;
    protected static final String configurationKey = "configuration";

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dataManager = ((DataManagerAccessor)getContext()).provideDataManager();
    }
}

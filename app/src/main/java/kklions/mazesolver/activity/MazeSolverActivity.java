package kklions.mazesolver.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Toast;

import kklions.mazesolver.R;
import kklions.mazesolver.fragments.MazeSolveScreen;
import kklions.mazesolver.fragments.MazeSolverLandingFragment;
import kklions.mazesolver.fragments.OptionsFragment;

public class MazeSolverActivity extends Activity implements MazeSolverLandingFragment.NavigationListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        FragmentManager manager = getFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_layout_holder, MazeSolverLandingFragment.newInstance());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void navigateToOptionsMenu() {
        FragmentManager manager = getFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_layout_holder, OptionsFragment.newInstance());
        // TODO add landing fragment to backstack
        transaction.commit();
    }

    @Override
    public void navigateToSolveScreen() {
        FragmentManager manager = getFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_layout_holder, MazeSolveScreen.newInstance());
        transaction.commit();
        // TODO add landing fragment to backstack
    }


}

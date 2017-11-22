package kklions.mazesolver.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import kklions.mazesolver.R;
import kklions.mazesolver.enums.Algorithm;

/**
 * A placeholder fragment containing a simple view.
 */
public class MazeSolverLandingFragment extends Fragment {

    private NavigationListener navigationListener;

    private View fragmentView;

    public MazeSolverLandingFragment() {
    }

    public static MazeSolverLandingFragment newInstance() {
        return new MazeSolverLandingFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Does having "attachToRoot" set to false mean that onAttach is never called?
        fragmentView = inflater.inflate(R.layout.maze_solver_landing, container, false);
        return fragmentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(!(context instanceof NavigationListener)) {
            throw new ClassCastException("The Activity has to implement NavigationListener interface");
        }

        navigationListener = (NavigationListener) context;
    }

    @Override
    public void onStart() {
        super.onStart();
        Button optionsMenu = (Button) fragmentView.findViewById(R.id.options_button);
        optionsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationListener.navigateToOptionsMenu();
            }
        });

        Button solveBFS = (Button) fragmentView.findViewById(R.id.button_solve_breath_first);
        solveBFS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationListener.navigateToSolveScreen(Algorithm.BFS);
            }
        });

        Button solveDFS = (Button) fragmentView.findViewById(R.id.button_solve_depth_first);
        solveDFS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationListener.navigateToSolveScreen(Algorithm.DFS);
            }
        });

        Button solveAStar = (Button) fragmentView.findViewById(R.id.button_solve_a_star);
        solveAStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationListener.navigateToSolveScreen(Algorithm.ASTAR);
            }
        });
    }

    /**
     * Interface for navigation to all screens this fragment can reach
     */
    public interface NavigationListener {
        void navigateToOptionsMenu();

        void navigateToSolveScreen(Algorithm method);
    }
}

package kklions.mazesolver.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import kklions.mazesolver.R;
import kklions.mazesolver.enums.Algorithm;
import kklions.mazesolver.managers.MazeSolverDataManager;
import kklions.mazesolver.managers.accessors.DataManagerAccessor;
import kklions.mazesolver.model.MazeConfiguration;

/**
 * Landing screen for application, configure maze
 *
 * Created by kliok002 on 11/18/17.
 */

public class OptionsFragment extends Fragment {

    private View fragmentView;
    private NavigationListener navigationListener;

    public static OptionsFragment newInstance() {
        return new OptionsFragment();
    }

    public OptionsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.options_menu, container, false);
        return fragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Button confirmButton = fragmentView.findViewById(R.id.confirm_settings);
        confirmButton.setOnClickListener((view) -> {
            EditText mazeHeight = fragmentView.findViewById(R.id.maze_height_entry);
            EditText mazeWidth = fragmentView.findViewById(R.id.maze_width_entry);
            EditText timeInterval = fragmentView.findViewById(R.id.solve_time_delay);

            MazeConfiguration configuration = new MazeConfiguration.Builder()
                    .setHeight(Integer.parseInt(mazeHeight.getText().toString()))
                    .setWidth(Integer.parseInt(mazeWidth.getText().toString()))
                    .setInterval(Integer.parseInt(timeInterval.getText().toString()))
                    .setMethod(Algorithm.BFS)
                    .build();
            navigationListener.navigateToSolveScreen(configuration);
        });
    }

    public interface NavigationListener {
        void navigateToSolveScreen(MazeConfiguration configuration);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        navigationListener = (NavigationListener) context;
    }
}

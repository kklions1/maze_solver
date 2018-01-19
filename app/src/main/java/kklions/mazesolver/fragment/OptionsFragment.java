package kklions.mazesolver.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import kklions.mazesolver.R;
import kklions.mazesolver.enums.Algorithm;
import kklions.mazesolver.model.MazeConfiguration;

/**
 * Landing screen for application, configure maze
 *
 * Created by Kevin Klions on 11/18/17.
 */

public class OptionsFragment extends Fragment {

    private View fragmentView;
    private NavigationListener navigationListener;
    private String solveMethod;

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
                    // adding two so that there will be extra space for a black border around the maze
                    .setHeight(Integer.parseInt(mazeHeight.getText().toString()) + 2)
                    .setWidth(Integer.parseInt(mazeWidth.getText().toString()) + 2)
                    .setInterval(Integer.parseInt(timeInterval.getText().toString()))
                    .setMethod(Algorithm.BFS)
                    .setPercentMissing(0)
                    .build();
            if (configuration.getMethod() == null) {
                Toast.makeText(getContext(), "Please select a solve method", Toast.LENGTH_SHORT).show();
            } else {
                navigationListener.navigateToSolveScreen(configuration);
            }
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

    private void initMethodPicker() {
        Spinner methodPicker = fragmentView.findViewById(R.id.method_picker);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.solve_methods, R.layout.method_picker_layout);

        methodPicker.setOnItemClickListener((parentAdapterView, view, position, id) -> {
            // TODO implement event listener, it should send the selected method to the MazeConfiguration

        });
    }
}

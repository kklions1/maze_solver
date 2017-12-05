package kklions.mazesolver.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.google.gson.Gson;

import org.json.JSONObject;

import kklions.mazesolver.R;
import kklions.mazesolver.managers.accessors.DataManagerAccessor;
import kklions.mazesolver.managers.accessors.MazeSolvingAccessor;
import kklions.mazesolver.model.MazeConfiguration;

/**
 * Maze Solving screen
 *
 * Created by kliok002 on 11/18/17.
 */

public class MazeSolveScreen extends Fragment {

    private static final String configurationKey = "configuration";
    private View fragmentView;
    private MazeConfiguration configuration;
    private GridLayout.LayoutParams params;
    private MazeSolvingAccessor mazeSolvingAccessor;

    public static MazeSolveScreen newInstance(MazeConfiguration configuration) {
        MazeSolveScreen fragment = new MazeSolveScreen();
        Bundle bundle = new Bundle();
        String configurationJson = new Gson().toJson(configuration);
        bundle.putString(configurationKey, configurationJson);
        fragment.setArguments(bundle);
        return fragment;
    }

    public MazeSolveScreen() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            String configurationJson = savedInstanceState.getString(configurationKey);
            configuration = new Gson().fromJson(configurationJson, MazeConfiguration.class);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            mazeSolvingAccessor = ((DataManagerAccessor) getContext()).provideDataManager();
        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new IllegalStateException("The Data manager does not implement the correct data accessor");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.solve_maze_screen, container, false);

        return fragmentView;
    }
}

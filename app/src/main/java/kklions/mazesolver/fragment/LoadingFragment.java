package kklions.mazesolver.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kklions.mazesolver.R;
import kklions.mazesolver.model.MazeConfiguration;

/**
 * This fragment exists because I'm too lazy to make a proper good looking loading wheel right now
 *
 * Created by Kevin Klions on 1/22/18.
 */

public class LoadingFragment extends BaseFragment {
//    private View view;
//    private MazeConfiguration configuration;
//    private MazeSolverDataManager dataManager;
    private static String configurationKey = "configuration";

    public static LoadingFragment newInstance(MazeConfiguration configuration) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(configurationKey, configuration);
        LoadingFragment fragment = new LoadingFragment();
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            configuration = (MazeConfiguration) savedInstanceState.getSerializable(configurationKey);
        } else {
            configuration = (MazeConfiguration) getArguments().getSerializable(configurationKey);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        GenerateMazeAsync generateMazeAsync = new GenerateMazeAsync();
        generateMazeAsync.execute(configuration);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.loading_screen_layout, container, false);
        return view;
    }

    // TODO figure out if this causes memory leaks and devise a better way to do this
    private class GenerateMazeAsync extends AsyncTask<MazeConfiguration, String, Void> {
        @Override
        public Void doInBackground(MazeConfiguration... configurations) {
            MazeConfiguration configuration = configurations[0];
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            dataManager.generateMaze(configuration.getPercentMissing());
            publishProgress("Solving Maze...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            dataManager.solveBreadthFirstSearch(configuration.getHeight(), configuration.getWidth());
            return null;
        }

        @Override
        public void onProgressUpdate(String... params) {
            TextView loadingScreenText = view.findViewById(R.id.current_status);
            loadingScreenText.setText(params[0]);
        }

        @Override
        public void onPostExecute(Void result) {
            ((LoadingNavigationListener) getContext()).navigateToSolveScreen(configuration);
        }
    }


    public interface LoadingNavigationListener {
        void navigateToSolveScreen(MazeConfiguration configuration);
    }
}

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
import kklions.mazesolver.model.MazeConfiguration;

/**
 * Landing screen for application, configure maze
 *
 * Created by kliok002 on 11/18/17.
 */

public class OptionsFragment extends Fragment {

    private View fragmentView;
    private NavigationListner navigationListner;

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
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO migrate to Java 8 so this can be a lambda

                

                MazeConfiguration configuration = new MazeConfiguration.Builder()
                        .setHeight(40)
                        .setWidth(30)
                        .withMethod(Algorithm.BFS)
                        .build();
                navigationListner.navigateToSolveScreen(configuration);
            }
        });
    }

    public interface NavigationListner {
        void navigateToSolveScreen(MazeConfiguration configuration);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        navigationListner = (NavigationListner) context;
    }
}

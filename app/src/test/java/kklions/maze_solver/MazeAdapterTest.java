package kklions.maze_solver;

import org.junit.Test;

import kklions.mazesolver.model.MazeConfiguration;

/**
 * Quick Unit test for my sanity
 * TODO write this in kotlin
 *
 * Created by kliok002 on 12/5/17.
 */

public class MazeAdapterTest {

    @Test
    public void testHeightAndWidth() {
        MazeConfiguration configuration = new MazeConfiguration.Builder()
                .setHeight(30)
                .setWidth(40)
                .build();


    }
}

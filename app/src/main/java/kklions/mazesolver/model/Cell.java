package kklions.mazesolver.model;

import java.io.Serializable;

/**
 * Space in the maze
 *
 * Created by kliok002 on 11/18/17.
 */

public class Cell implements Serializable {
    public boolean top;
    public boolean bottom;
    public boolean left;
    public boolean right;

    // TODO possibly refactor this so that I can incorperate the Point class to reduce code

    public Cell() { }
}

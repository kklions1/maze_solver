package kklions.mazesolver.enums;

/**
 * Enum for what algorithm to use to solve the maze
 *
 * Created by Kevin Klions on 11/14/17.
 */

public abstract class Algorithm {
    public static final String BFS = "breadthFirstSearch";
    public static final String DFS = "depthFirstSearch";
    public static final String ASTAR = "aStar";
    public static final String BESTFIRST = "kStar";
}

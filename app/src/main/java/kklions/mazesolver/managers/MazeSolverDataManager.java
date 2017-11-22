package kklions.mazesolver.managers;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import kklions.mazesolver.enums.Direction;
import kklions.mazesolver.managers.accessors.MazeGenerator;
import kklions.mazesolver.managers.accessors.MazeSolving;
import kklions.mazesolver.model.Cell;
import kklions.mazesolver.model.Edge;
import kklions.mazesolver.model.Point;

/**
 * Data Manager for maze solver application
 *
 * Created by kliok002 on 11/14/17.
 */

public class MazeSolverDataManager implements MazeSolving, MazeGenerator {

    // Maze Generation
    @Override
    public Cell[][] generateMaze(int height, int width, float percentageMissing) {

        Cell[][] maze = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[i][j] = new Cell();
            }
        }

        List<Edge> minSpanningTree = createMinSpanningTree(height, width);
        maze = removeWalls(maze, minSpanningTree, width);
        //remove extra walls
        maze = removeExtraWalls(maze, height, width, percentageMissing);
        //create start and end
        maze = entryAndExit(maze, height, width);
        return maze;
    }

    private List<Edge> createMinSpanningTree(int height, int width) {
        //create base graph

        List<Edge> minSpanningTree = Lists.newArrayList();
        Map<Integer, Set<Integer>> vertexSets = Maps.newHashMap();
        Set<Integer> goalSet = Sets.newHashSet();
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        int vertex = 0;
        int previousVertex;
        int randomWeight;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                vertexSets.put(vertex, Sets.newHashSet(vertex));
                goalSet.add(vertex);
                if (j > 0) {
                    previousVertex = vertex - 1;
                    randomWeight = ThreadLocalRandom.current().nextInt(1, 50);
                    edges.add(new Edge(previousVertex, vertex, randomWeight));
                }
                if (i > 0) {
                    previousVertex = vertex - width;
                    randomWeight = ThreadLocalRandom.current().nextInt(1, 50);
                    edges.add(new Edge(previousVertex, vertex, randomWeight));
                }
                vertex++;
            }
        }

        while (!vertexSets.get(0).equals(goalSet)) {
            Edge currentEdge = edges.remove();
            Set<Integer> currentSet = vertexSets.get(currentEdge.v1);
            if (!(currentSet.contains(currentEdge.v2))) {
                minSpanningTree.add(currentEdge);
                currentSet.addAll(vertexSets.get(currentEdge.v2));
                for (int vert : currentSet) {
                    vertexSets.put(vert, currentSet);
                }
            }
        }

        return minSpanningTree;
    }

    private Cell[][] removeWalls(Cell[][] maze, List<Edge> minSpanningTree, int width) {

        for (Edge edge : minSpanningTree) {
            Cell cell1 = maze[edge.v1 / width][edge.v1 % width];
            Cell cell2 = maze[edge.v2 / width][edge.v2 % width];
            if ((edge.v1 / width) == (edge.v2 / width)) {
                cell1.right = true;
                cell2.left = true;
            } else {
                cell1.bottom = true;
                cell2.top = true;
            }
        }
        return maze;
    }

    private Cell[][] removeExtraWalls(Cell[][] maze, int height, int width, float percentageMissing) {
        int numberToRemove = (int) ((height * width) * percentageMissing);
        int i = 0;
        int randomVertical;
        int randomHorizontal;
        int randomWall;
        while (i < numberToRemove) {
            randomVertical = ThreadLocalRandom.current().nextInt(1, height-2);
            randomHorizontal = ThreadLocalRandom.current().nextInt(1, width-2);
            Cell cell = maze[randomVertical][randomHorizontal];
            randomWall = ThreadLocalRandom.current().nextInt(1, 4);
            switch (randomWall) {
                case 1:
                    if (!cell.top) {
                        cell.top = true;
                        maze[randomVertical-1][randomHorizontal].bottom = true;
                        i++;
                    }
                    break;
                case 2:
                    if (!cell.right) {
                        cell.right = true;
                        maze[randomVertical][randomHorizontal+1].left = true;
                        i++;
                    }
                    break;
                case 3:
                    if (!cell.bottom) {
                        cell.bottom = true;
                        maze[randomVertical+1][randomHorizontal].top = true;
                        i++;
                    }
                    break;
                case 4:
                    if (!cell.left) {
                        cell.left = true;
                        maze[randomVertical][randomHorizontal-1].right = true;
                        i++;
                    }

            }
        }
        return maze;
    }

    private Cell[][] entryAndExit(Cell[][] maze, int height, int width) {

        int randomHorizontal = ThreadLocalRandom.current().nextInt(0, width-1);
        maze[0][randomHorizontal].top = true;
        randomHorizontal = ThreadLocalRandom.current().nextInt(0, width-1);
        maze[height-1][randomHorizontal].bottom = true;
        return maze;
    }
    // endregion

    // Solving algorithms

    @Override
    public List<Direction> solveBreadthFirstSearch(Cell[][] maze, int height, int width) {
        Point start = null;
        Point end = null;
        boolean[][] visited = new boolean[height][width];
//        int[][] distance = new int[height][width];
        Point[][] predecessor = new Point[height][width];
        LinkedList<Point> queue = Lists.newLinkedList();

        // setup arrays for search
        for (int row = 0; row < height; ++row) {
            for (int col = 0; col < width; ++col) {
                visited[row][col] = false;
//                distance[row][col] = Integer.MAX_VALUE;
            }
        }

        try {
            // Find the start of the maze
            for (int i = 0; i < width; ++i) {
                if (maze[0][i].top) {
                    start = new Point(0, i);
                    break;
                }
            }

            // Find the end of the maze
            for (int i = 0; i < width; ++i) {
                if (maze[height - 1][i].bottom) {
                    end = new Point(height - 1, i);
                    break;
                }
            }

            // If no start or end was found, throw an exception
            if (start == null) {
                throw new Exception("no start found");
            }
            if (end == null) {
                throw new Exception("no end found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        // Set up the information for the starting point
        Point currentPoint = start;
        visited[currentPoint.getRow()][currentPoint.getCol()] = true;
//        distance[currentPoint.getRow()][currentPoint.getCol()] = 0;
        Cell currentCell = maze[currentPoint.getRow()][currentPoint.getCol()];
        predecessor[currentPoint.getRow()][currentPoint.getCol()] = null;

        if (currentCell.bottom) {
            queue.add(new Point(currentPoint.getRow() + 1, currentPoint.getCol()));
            predecessor[currentPoint.getRow() + 1][currentPoint.getCol()] = currentPoint;
        }
        if (currentCell.left) {
            queue.add(new Point(currentPoint.getRow(), currentPoint.getCol() - 1));
            predecessor[currentPoint.getRow()][currentPoint.getCol() - 1] = currentPoint;
        }
        if (currentCell.right) {
            queue.add(new Point(currentPoint.getRow(), currentPoint.getCol() + 1));
            predecessor[currentPoint.getRow()][currentPoint.getCol() + 1] = currentPoint;
        }

        while (!queue.isEmpty()) {
            currentPoint = queue.remove();
            currentCell = maze[currentPoint.getRow()][currentPoint.getCol()];
            visited[currentPoint.getRow()][currentPoint.getCol()] = true;
//            distance[currentPoint.getRow()][currentPoint.getCol()] = distance[previousPoint.getRow()][previousPoint.getCol()] + 1;

            // the end of the maze has been reached
            if (currentPoint.equals(end)) {
                List<Direction> solution = Lists.newArrayList();
                Point nextPoint; // the next point in the path

                while (!currentPoint.equals(start)) {
                    //currentCell = maze[currentPoint.getRow()][currentPoint.getCol()];
                    nextPoint = predecessor[currentPoint.getRow()][currentPoint.getCol()];
                    // the direction added to the list,
                    if (nextPoint.isAbove(currentPoint)) {
                        solution.add(Direction.UP);
                    }
                    if (nextPoint.isBelow(currentPoint)) {
                        solution.add(Direction.DOWN);
                    }
                    if (nextPoint.isToLeft(currentPoint)) {
                        solution.add(Direction.LEFT);
                    }
                    if (nextPoint.isToRight(currentPoint)) {
                        solution.add(Direction.RIGHT);
                    }
                    // Move to the next point
                    currentPoint = nextPoint;
                }

                System.out.println(String.format("Solution was %d steps long", solution.size()));

                return Lists.reverse(solution);
            }
            // endregion


            if (currentCell.top && !queue.contains(currentPoint) && !visited[currentPoint.getRow() - 1][currentPoint.getCol()]) {
                queue.add(new Point(currentPoint.getRow() - 1, currentPoint.getCol()));
                predecessor[currentPoint.getRow() - 1][currentPoint.getCol()] = currentPoint;
            }
            if (currentCell.bottom && !queue.contains(currentPoint) && !visited[currentPoint.getRow() + 1][currentPoint.getCol()]) {
                queue.add(new Point(currentPoint.getRow() + 1, currentPoint.getCol()));
                predecessor[currentPoint.getRow() + 1][currentPoint.getCol()] = currentPoint;
            }
            if (currentCell.left && !queue.contains(currentPoint) && !visited[currentPoint.getRow()][currentPoint.getCol() - 1]) {
                queue.add(new Point(currentPoint.getRow(), currentPoint.getCol() - 1));
                predecessor[currentPoint.getRow()][currentPoint.getCol() - 1] = currentPoint;
            }
            if (currentCell.right && !queue.contains(currentPoint) && !visited[currentPoint.getRow()][currentPoint.getCol() + 1]) {
                queue.add(new Point(currentPoint.getRow(), currentPoint.getCol() + 1));
                predecessor[currentPoint.getRow()][currentPoint.getCol() + 1] = currentPoint;
            }
        }

        return null;    }

    @Override
    public List<Direction> solveDepthFirstSearch(Cell[][] maze, int height, int width) {
        // TODO implement breadth first search using a stack instead of a queue
        return null;
    }

    @Override
    public List<Direction> solveAStar(Cell[][] maze, int height, int width) {
        // TODO fix the comparator used in the first attempt A* implementation
        // TODO implement a fix to ensure the shortest path is always found
        return null;
    }

    @Override
    public List<Direction> solveAStarFirstAttempt(Cell[][] maze, int height, int width) {
        Point start = null;
        Point end = null;
        boolean[][] visited = new boolean[height][width];
        final double[][] absoluteDistance = new double[height][width];
        int[][] distance = new int[height][width];
        Point[][] predecessor = new Point[height][width];

        PriorityQueue<Point> queue = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if (absoluteDistance[p1.getRow()][p1.getCol()] > absoluteDistance[p2.getRow()][p2.getCol()]) {
                    return 1;
                } else if (absoluteDistance[p1.getRow()][p1.getCol()] < absoluteDistance[p2.getRow()][p2.getCol()]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        try {
            // Find the start of the maze
            for (int i = 0; i < width; ++i) {
                if (maze[0][i].top) {
                    start = new Point(0, i);
                    break;
                }
            }

            // Find the end of the maze
            for (int i = 0; i < width; ++i) {
                if (maze[height - 1][i].bottom) {
                    end = new Point(height - 1, i);
                    break;
                }
            }

            // If no start or end was found, throw an exception
            if (start == null) {
                throw new Exception("no start found");
            }
            if (end == null) {
                throw new Exception("no end found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        // Inititalize visited, the heuristic, and initial distances
        for (int row = 0; row < height; ++row) {
            for (int col = 0; col < width; ++col) {
                visited[row][col] = false;
                absoluteDistance[row][col] = Math.sqrt(Math.pow(end.getRow() - row, 2) + Math.pow(end.getCol() - col, 2));
                distance[row][col] = Integer.MAX_VALUE;
            }
        }

        // Start the search
        Point currentPoint = start;
        Cell currentCell = maze[currentPoint.getRow()][currentPoint.getCol()];
        predecessor[currentPoint.getRow()][currentPoint.getCol()] = null;
        visited[currentPoint.getRow()][currentPoint.getCol()] = true;

        if (currentCell.left) {
            predecessor[currentPoint.getRow()][currentPoint.getCol() - 1] = currentPoint;
            queue.add(new Point(currentPoint.getRow(), currentPoint.getCol() - 1));
        }
        if (currentCell.right) {
            predecessor[currentPoint.getRow()][currentPoint.getCol() + 1] = currentPoint;
            queue.add(new Point(currentPoint.getRow(), currentPoint.getCol() + 1));
        }
        if (currentCell.bottom) {
            predecessor[currentPoint.getRow() + 1][currentPoint.getCol()] = currentPoint;
            queue.add(new Point(currentPoint.getRow() + 1, currentPoint.getCol()));
        }

        while (!queue.isEmpty()) {
            currentPoint = queue.poll();
            currentCell = maze[currentPoint.getRow()][currentPoint.getCol()];
            visited[currentPoint.getRow()][currentPoint.getCol()] = true;

            if (currentPoint.equals(end)) {
                List<Direction> solution = Lists.newArrayList();
                Point nextPoint; // the next point in the path

                while (!currentPoint.equals(start)) {
                    //currentCell = maze[currentPoint.getRow()][currentPoint.getCol()];
                    nextPoint = predecessor[currentPoint.getRow()][currentPoint.getCol()];
                    // the direction added to the list,
                    if (nextPoint.isAbove(currentPoint)) {
                        solution.add(Direction.UP);
                    }
                    if (nextPoint.isBelow(currentPoint)) {
                        solution.add(Direction.DOWN);
                    }
                    if (nextPoint.isToLeft(currentPoint)) {
                        solution.add(Direction.LEFT);
                    }
                    if (nextPoint.isToRight(currentPoint)) {
                        solution.add(Direction.RIGHT);
                    }
                    // Move to the next point
                    currentPoint = nextPoint;
                }

                return Lists.reverse(solution);
            }

            if (currentCell.top && !queue.contains(currentPoint) && !visited[currentPoint.getRow() - 1][currentPoint.getCol()]) {
                queue.add(new Point(currentPoint.getRow() - 1, currentPoint.getCol()));
                predecessor[currentPoint.getRow() - 1][currentPoint.getCol()] = currentPoint;
            }
            if (currentCell.bottom && !queue.contains(currentPoint) && !visited[currentPoint.getRow() + 1][currentPoint.getCol()]) {
                queue.add(new Point(currentPoint.getRow() + 1, currentPoint.getCol()));
                predecessor[currentPoint.getRow() + 1][currentPoint.getCol()] = currentPoint;
            }
            if (currentCell.left && !queue.contains(currentPoint) && !visited[currentPoint.getRow()][currentPoint.getCol() - 1]) {
                queue.add(new Point(currentPoint.getRow(), currentPoint.getCol() - 1));
                predecessor[currentPoint.getRow()][currentPoint.getCol() - 1] = currentPoint;
            }
            if (currentCell.right && !queue.contains(currentPoint) && !visited[currentPoint.getRow()][currentPoint.getCol() + 1]) {
                queue.add(new Point(currentPoint.getRow(), currentPoint.getCol() + 1));
                predecessor[currentPoint.getRow()][currentPoint.getCol() + 1] = currentPoint;
            }

        }
        return null;
    }
    // endregion

}
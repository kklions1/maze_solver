package kklions.mazesolver.model;

/**
 * Location in the maze, holds the ordered pair
 *
 * Created by kliok002 on 11/18/17.
 */

public class Point {
    private int row;
    private int col;

    public int getRow() { return row; }

    public int getCol() { return col; }

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Determines if the point given is above, below, or to either side of the current point
     * @param point an argument
     * @return if that point is above, below, or
     */
    public boolean isAbove(Point point) {
        return this.getRow() == point.getRow() + 1 && this.getCol() == point.getCol();
    }

    public boolean isBelow(Point point) {
        return this.getRow() == point.getRow() - 1 && this.getCol() == point.getCol();
    }

    public boolean isToLeft(Point point) {
        return this.getRow() == point.getRow() && this.getCol() == point.getCol() + 1;
    }

    public boolean isToRight(Point point) {
        return this.getRow() == point.getRow() && this.getCol() == point.getCol() - 1;
    }

    @Override
    public boolean equals(Object o) {
        Point compare = (Point) o;
        if (compare.getCol() == this.getCol() && compare.getRow() == this.getRow()) {
            return true;
        }
        return false;
    }
}

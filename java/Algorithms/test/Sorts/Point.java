package Sorts;

import java.util.Comparator;

/**
 * Created by jianming.xiao on 10/30/14.
 */
public class Point implements Comparable<Point> {
    private int x;
    private int y;

    /**
     * Initializes a new point (x, y).
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Compares two points by x-coordinate.
     */
    public static final Comparator<Point> X_ORDER = new XOrder();

    /**
     * Compares two points by y-coordinate.
     */
    public static final Comparator<Point> Y_ORDER = new YOrder();

    /**
     * Compares this point to that point by y-coordinate, breaking ties by x-coordinate.
     *
     * @param that the other point
     * @return { a negative integer, zero, a positive integer } if this point is
     * { less than, equal to, greater than } that point
     */
    @Override
    public int compareTo(Point that) {
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        return 0;
    }

    private static class XOrder implements Comparator<Point> {
        /**
         * compare points according to their x-coordinate
         */
        @Override
        public int compare(Point p, Point q) {
            if (p.x < q.x) return -1;
            if (p.x > q.x) return +1;
            return 0;
        }
    }

    private static class YOrder implements Comparator<Point> {
        /**
         * compare points according to their y-coordinate
         */
        @Override
        public int compare(Point p, Point q) {
            if (p.y < q.y) return -1;
            if (p.y > q.y) return +1;
            return 0;
        }
    }
}

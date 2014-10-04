import java.util.Comparator;

/**
 * Created by jianming.xiao on 10/4/14.
 */

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();

    private final int x;
    private final int y;

    // create the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (this.x == that.x) {
            if (this.y == that.y) return Double.NEGATIVE_INFINITY;
            return Double.POSITIVE_INFINITY;
        }
        if (this.y == that.y) return 0.0;
        return (that.y - this.y) / (that.x - this.x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y < that.y) return -1;
        if (this.y == that.y) {
            if (this.x < that.x) return -1;
            if (this.x == that.x) return 0;
        }
        return 1;
    }

    // return string representation of this point
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    private class SlopeOrder implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            double slope1 = slopeTo(p1);
            double slope2 = slopeTo(p2);
            if (slope1 < slope2) return -1;
            else if (slope1 > slope2) return 1;
            else return 0;
        }
    }

    // unit test
    public static void main(String[] args) {
        assert new Point(1, 1).compareTo(new Point(1, 2)) < 0;
        assert new Point(1, 2).compareTo(new Point(1, 1)) > 0;
        assert new Point(1, 2).compareTo(new Point(1, 2)) == 0;
        assert new Point(1, 2).compareTo(new Point(2, 2)) < 0;
        assert new Point(2, 2).compareTo(new Point(1, 2)) > 0;

        assert new Point(1, 1).slopeTo(new Point(1, 1)) == Double.NEGATIVE_INFINITY;
        assert new Point(1, 1).slopeTo(new Point(2, 1)) == 0.0;
        assert new Point(1, 1).slopeTo(new Point(1, 2)) == Double.POSITIVE_INFINITY;
        assert new Point(1, 1).slopeTo(new Point(2, 2)) == 1.0;
    }
}
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.ArrayList;

public class BruteCollinearPoints {
    private final LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points)   {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        }

        Point[] copyPoints = points.clone();
        Arrays.sort(copyPoints);
        for (int i = 0; i < copyPoints.length - 1; i++) {
            if (copyPoints[i].compareTo(copyPoints[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        ArrayList<LineSegment> lines = new ArrayList<LineSegment>();
        for (int i = 0; i < copyPoints.length; i++) {
            Point p = copyPoints[i];
            for (int j = i + 1; j < copyPoints.length; j++) {
                Point q = copyPoints[j];
                for (int k = j + 1; k < copyPoints.length; k++) {
                    Point r = copyPoints[k];

                    if (Double.compare(p.slopeTo(q), p.slopeTo(r)) == 0) {
                        for (int m = k + 1; m < copyPoints.length; m++) {
                            Point s = copyPoints[m];

                            if (Double.compare(p.slopeTo(q), p.slopeTo(s)) == 0) {
                                lines.add(new LineSegment(p, s));
                            }
                        }
                    }
                }
            }
        }
        this.segments = lines.toArray(new LineSegment[0]);
    }

    // the number of line segments
    public int numberOfSegments() {
        return this.segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return this.segments.clone();
    }


    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

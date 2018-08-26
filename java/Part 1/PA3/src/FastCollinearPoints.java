import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final LineSegment[] segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
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

            Point[] aux = Arrays.copyOfRange(copyPoints, i, copyPoints.length);
            Arrays.sort(aux, p.slopeOrder());

            ArrayList<Point> inLinePoints = new ArrayList<Point>();
            inLinePoints.add(p);
            for (int j = 1; j < aux.length; j++) {
                if (inLinePoints.size() > 1 && Double.compare(p.slopeTo(aux[j]), p.slopeTo(inLinePoints.get(inLinePoints.size() - 1))) != 0) {
                    if (inLinePoints.size() >= 4) {
                        lines.add(new LineSegment(inLinePoints.get(0), inLinePoints.get(inLinePoints.size() - 1)));
                    }
                    inLinePoints.clear();
                    inLinePoints.add(p);
                }
                inLinePoints.add(aux[j]);
            }
            if (inLinePoints.size() >= 4) {
                lines.add(new LineSegment(inLinePoints.get(0), inLinePoints.get(inLinePoints.size() - 1)));
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
            if (p != null) {
                p.draw();
            }
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

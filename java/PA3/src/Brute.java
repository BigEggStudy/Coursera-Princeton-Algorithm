import java.util.Arrays;

/**
 * Created by jianming.xiao on 10/4/14.
 */
public class Brute {
    public static void main(String[] args) {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger

        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            p.draw();
            points[i] = p;
        }

        Arrays.sort(points);
        processing(points, N);

        // display to screen all at once
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
    }

    private static void processing(Point[] points, int N) {
        for (int i = 0; i < N; i++) {
            Point p = points[i];

            for (int j = i + 1; j < N; j++) {
                Point q = points[j];

                for (int k = j + 1; k < N; k++) {
                    Point r = points[k];

                    double slope = p.slopeTo(q);
                    if (slope == p.slopeTo(r)) {
                        for (int l = k + 1; l < N; l++) {
                            Point s = points[l];

                            if (slope == p.slopeTo(s)) {
                                // print
                                StdOut.println(p.toString() + " -> " +
                                                q.toString() + " -> " +
                                                r.toString() + " -> " +
                                                s.toString()
                                );

                                // draw
                                p.drawTo(s);
                            }
                        }
                    }
                }
            }
        }
    }
}

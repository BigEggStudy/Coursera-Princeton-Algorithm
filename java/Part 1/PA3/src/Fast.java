import java.util.Arrays;

/**
 * Created by jianming.xiao on 10/4/14.
 */
public class Fast {
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
        // display to screen all at once
        StdDraw.show(0);
        // reset the pen radius
        StdDraw.setPenRadius();

        Arrays.sort(points);
        processing(points, N);
        // display to screen all at once
        StdDraw.show(0);
    }

    private static void processing(Point[] points, int N) {
        for (int i = 0; i < N; i++) {
            Point p = points[i];

            Point[] aux = points.clone();
            Arrays.sort(aux, p.SLOPE_ORDER);

            Point[] output = new Point[N];
            int outputCount = 0;
            output[outputCount++] = p;
            for (int j = 1; j < N; j++) {
                if (outputCount != 1 && p.slopeTo(aux[j]) != p.slopeTo(output[outputCount - 1])) {
                    tryOutput(p, output, outputCount);
                    output = new Point[N];
                    outputCount = 0;
                    output[outputCount++] = p;
                }
                output[outputCount++] = aux[j];
            }
            tryOutput(p, output, outputCount);
        }
    }

    private static void tryOutput(Point p, Point[] output, int outputCount) {
        if (outputCount >= 4) {
            Arrays.sort(output, 0, outputCount - 1);
            if (output[0] == p) {
                outputPoint(output, outputCount);
            }
        }
    }

    private static void outputPoint(Point[] output, int outputCount) {
        // print
        for (int i = 0; i < outputCount; i++) {
            StdOut.print(output[i].toString());
            if (i != outputCount - 1) StdOut.print(" -> ");
        }
        StdOut.println();

        // draw
        output[0].drawTo(output[outputCount - 1]);
    }
}

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

            Point[] aux = new Point[N - i];
            for (int j = 0; j < N - i; j++) {
                aux[j] = points[j + i];
            }
            Arrays.sort(aux, p.SLOPE_ORDER);

            Point[] output = new Point[N - i];
            int outputCount = 0;
            for (int j = 1; j < N - i; j++) {
                if (outputCount == 0) {
                    output[outputCount++] = aux[j];
                } else if (p.slopeTo(aux[j]) == p.slopeTo(output[outputCount - 1])) {
                    output[outputCount++] = aux[j];
                } else {
                    if (outputCount >= 3) {
                        outputPoint(p, output, outputCount);
                    }
                    output = new Point[N - i];
                    outputCount = 0;
                    output[outputCount++] = aux[j];
                }
            }
            if (outputCount >= 3) {
                outputPoint(p, output, outputCount);
            }
        }
    }

    private static void outputPoint(Point p, Point[] output, int outputCount) {
        StdOut.print(p.toString());
        // print
        for (int i = 0; i < outputCount; i++) {
            StdOut.print(" -> ");
            StdOut.print(output[i].toString());
        }
        StdOut.println();

        // draw
        p.drawTo(output[outputCount - 1]);
    }
}

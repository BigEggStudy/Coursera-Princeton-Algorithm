/**
 * Created by jianming.xiao on 10/6/14.
 */
public class PercolationStats {
    private double[] openSiteCounts;
    private int T;

    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0) throw new IllegalArgumentException();
        if (T <= 0) throw new IllegalArgumentException();

        this.T = T;
        openSiteCounts = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation percolation = new Percolation(N);
            int count = 0;
            while (!percolation.percolates()) {
                int x = StdRandom.uniform(1, N + 1);
                int y = StdRandom.uniform(1, N + 1);

                if (!percolation.isOpen(x, y)) {
                    percolation.open(x, y);
                    count++;
                }
            }
            openSiteCounts[i] = 1.0 * count / (N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(openSiteCounts);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        if (T == 1) {
            return Double.NaN;
        } else {
            return StdStats.stddev(openSiteCounts);
        }
    }

    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

    // test client, described below
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(N, T);
        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
    }
}

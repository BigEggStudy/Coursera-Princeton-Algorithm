import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    private final double _mean;
    private final double _stddev;
    private final double _confidenceLo;
    private final double _confidenceHi;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) throw new IllegalArgumentException();
        if (trials <= 0) throw new IllegalArgumentException();

        double[] openSiteCounts = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int x = StdRandom.uniform(1, n + 1);
                int y = StdRandom.uniform(1, n + 1);

                if (!percolation.isOpen(x, y)) {
                    percolation.open(x, y);
                }
            }
            openSiteCounts[i] = 1.0 * percolation.numberOfOpenSites() / (n * n);
        }

        this._mean = StdStats.mean(openSiteCounts);
        this._stddev = StdStats.stddev(openSiteCounts);
        this._confidenceLo = this._mean - (1.96 * this._stddev) / Math.sqrt(trials);
        this._confidenceHi = this._mean + (1.96 * this._stddev) / Math.sqrt(trials);
    }

    // sample mean of percolation threshold
    public double mean() {
        return this._mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return this._stddev;
    }

    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return this._confidenceLo;
    }

    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return this._confidenceHi;
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

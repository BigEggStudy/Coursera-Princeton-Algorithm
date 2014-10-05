/**
 * Created by jianming.xiao on 10/4/14.
 */
public class Percolation {
    private final int virtualTopSiteIndex;
    private final int virtualBottomSiteIndex;
    private final int N;
    private boolean[] sites;
    private WeightedQuickUnionUF weightedQuickUnionUF;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException();

        this.N = N;
        this.virtualTopSiteIndex = N * N;
        this.virtualBottomSiteIndex = N * N + 1;
        sites = new boolean[N * N];
        for (int i = 0; i < N * N; i++) {
            sites[i] = false;
        }
        weightedQuickUnionUF = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 1; i <= N; i++) {
            weightedQuickUnionUF.union(xyTo1D(1, i), virtualTopSiteIndex);
            weightedQuickUnionUF.union(xyTo1D(N, i), virtualBottomSiteIndex);
        }
    }

    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        validation(i, j);

        int index = xyTo1D(i, j);
        sites[index] = true;
        if (i > 1) {
            int upIndex = xyTo1D(i - 1, j);
            if (sites[upIndex]) weightedQuickUnionUF.union(index, upIndex);
        }
        if (i < N) {
            int downIndex = xyTo1D(i + 1, j);
            if (sites[downIndex]) weightedQuickUnionUF.union(index, downIndex);
        }
        if (j > 1) {
            int leftIndex = xyTo1D(i, j - 1);
            if (sites[leftIndex]) weightedQuickUnionUF.union(index, leftIndex);
        }
        if (j < N) {
            int rightIndex = xyTo1D(i, j + 1);
            if (sites[rightIndex]) weightedQuickUnionUF.union(index, rightIndex);
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        validation(i, j);

        int index = xyTo1D(i, j);
        return sites[index];
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        validation(i, j);

        if (N == 1) {
            return isOpen(1, 1);
        } else {
            int index = xyTo1D(i, j);
            return weightedQuickUnionUF.connected(index, virtualTopSiteIndex);
        }
    }

    // does the system percolate?
    public boolean percolates() {
        if (N == 1) {
            return isOpen(1, 1);
        } else {
            return weightedQuickUnionUF.connected(virtualTopSiteIndex, virtualBottomSiteIndex);
        }
    }

    private void validation(int i, int j) {
        if (i <= 0 || i > N) throw new IndexOutOfBoundsException();
        if (j <= 0 || j > N) throw new IndexOutOfBoundsException();
    }

    private int xyTo1D(int i, int j) {
        return (i - 1) * N + (j - 1);
    }

    // test client, optional
    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        percolation.open(1, 2);
        percolation.open(3, 2);
        percolation.open(2, 1);
        percolation.open(2, 3);
        assert !percolation.isFull(3, 2);
        assert !percolation.percolates();

        percolation.open(2, 2);
        assert percolation.isFull(3, 2);
        assert percolation.isFull(2, 1);
        assert percolation.isFull(2, 3);
        assert !percolation.percolates();

        percolation.open(4, 2);
        percolation.open(5, 2);
        assert percolation.percolates();

        percolation = new Percolation(1);
        assert !percolation.isFull(1, 1);
        assert !percolation.percolates();
        percolation.open(1, 1);
        assert percolation.isFull(1, 1);
        assert percolation.percolates();
    }
}

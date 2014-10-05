/**
 * Created by jianming.xiao on 10/4/14.
 */
public class Percolation {
    private int N;
    private boolean[] sites;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException();
        this.N = N;
        sites = new boolean[N * N];
        for (int i = 0; i < N * N; i++) {
            sites[i] = false;
        }
    }

    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        int index = xyTo1D(i, j);

    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        int index = xyTo1D(i, j);

    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        int index = xyTo1D(i, j);

    }

    // does the system percolate?
    public boolean percolates() {

    }

    private int xyTo1D(int i, int j) {
        if (i <= 0 || i > N) throw new IndexOutOfBoundsException();
        if (j <= 0 || j > N) throw new IndexOutOfBoundsException();

        return (i - 1) * N + j - 1;
    }

    // test client, optional
    public static void main(String[] args) {

    }
}

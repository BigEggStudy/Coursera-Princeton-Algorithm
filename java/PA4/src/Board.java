import java.util.Arrays;

/**
 * Created by jianming.xiao on 10/12/14.
 */
public class Board {
    private int N;
    private char[] a;
    private int blankIndex;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        N = blocks.length;
        a = new char[N * N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                a[i * N + j] = (char) blocks[i][j];
                if (blocks[i][j] == 0) blankIndex = i * N + j;
            }
        }
    }

    // board dimension N
    public int dimension() {
        return N;
    }

    // number of blocks out of place
    public int hamming() {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != i + 1) count++;
        }
        return count;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            int currentRow = i / N;
            int currentCol = i % N;
            int goalRow = a[i] / N;
            int goalCol = a[i] % N;

            count += Math.abs(currentCol - goalCol) + Math.abs(currentRow - goalRow);
        }
        return count;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < a.length; i++) {
            if (i == a.length - 1) {
                if (a[i] == 0) return true;
            }
            if (a[i] != (char) (i + 1)) return false;
        }
        return true;
    }

    // a boadr that is obtained by exchanging two adjacent blocks in the same row
    public Board twin() {
        int blankRow = blankIndex / N;
        int exchRow = -1;
        while (++exchRow < N) {
            if (exchRow != blankRow) break;
        }

        return exch(exchRow, 0, exchRow, 1);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        return Arrays.equals(this.a, that.a);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int blankRow = blankIndex / N;
        int blankCol = blankIndex % N;
        Stack<Board> stack = new Stack<Board>();
        if (blankCol > 0) {
            stack.push(exch(blankRow, blankCol - 1, blankRow, blankCol));
        }
        if (blankCol < N - 1) {
            stack.push(exch(blankRow, blankCol + 1, blankRow, blankCol));
        }
        if (blankRow > 0) {
            stack.push(exch(blankRow - 1, blankCol, blankRow, blankCol));
        }
        if (blankRow < N - 1) {
            stack.push(exch(blankRow + 1, blankCol, blankRow, blankCol));
        }
        return stack;
    }

    private Board exch(int row1, int col1, int row2, int col2) {
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = a[i * N + j];
            }
        }

        int exch = temp[row1][col1];
        temp[row1][col1] = temp[row2][col2];
        temp[row2][col2] = exch;
        return new Board(temp);
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N);
        for (int i = 0; i < a.length; i++) {
            if (i % N == 0) s.append("\n");
            s.append(String.format("%2c ", a[i]));
        }
        return s.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {
    }
}

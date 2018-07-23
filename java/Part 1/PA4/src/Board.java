import java.util.Arrays;

/**
 * Created by jianming.xiao on 10/12/14.
 */
public class Board {
    private int N;
    private char[] a;
    private int blankRow;
    private int blankCol;
    private int manhattanSum;
    private int hammingSum;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        N = blocks.length;
        a = new char[N * N];
        manhattanSum = 0;
        hammingSum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int index = i * N + j;
                a[index] = (char) blocks[i][j];

                if (blocks[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                    continue;
                }

                int goalRow = (blocks[i][j] - 1) / N;
                int goalCol = (blocks[i][j] - 1) % N;
                manhattanSum += Math.abs(j - goalCol) + Math.abs(i - goalRow);

                if (blocks[i][j] != index + 1) hammingSum++;
            }
        }
    }

    private Board(char[] a, int N, int hammingSub, int manhattanSum, int blankRow, int blankCol) {
        this.N = N;
        this.a = a;
        this.blankRow = blankRow;
        this.blankCol = blankCol;
        this.hammingSum = hammingSub;
        this.manhattanSum = manhattanSum;
    }

    // board dimension N
    public int dimension() {
        return N;
    }

    // number of blocks out of place
    public int hamming() {
        return hammingSum;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        return manhattanSum;
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
        int exchRow = -1;
        while (++exchRow < N) {
            if (exchRow != blankRow) break;
        }

        int index1 = exchRow * N;
        char block1 = a[index1];
        int goalRow1 = (block1 - 1) / N;
        int goalCol1 = (block1 - 1) % N;
        int deltaManhattan1 = (Math.abs(0 - goalCol1) + Math.abs(exchRow - goalRow1)) -
                (Math.abs(1 - goalCol1) + Math.abs(exchRow - goalRow1));
        int deltaHamming1 = block1 != index1 + 1 ? 1 : 0;
        deltaHamming1 -= block1 != index1 + 2 ? 1 : 0;

        int index2 = exchRow * N + 1;
        char block2 = a[index2];
        int goalRow2 = (block2 - 1) / N;
        int goalCol2 = (block2 - 1) % N;
        int deltaManhattan2 = (Math.abs(1 - goalCol2) + Math.abs(exchRow - goalRow2)) -
                (Math.abs(0 - goalCol2) + Math.abs(exchRow - goalRow2));
        int deltaHamming2 = block1 != index1 + 1 ? 1 : 0;
        deltaHamming2 -= block1 != index1 ? 1 : 0;

        return new Board(exch(exchRow, 0, exchRow, 1), N, hammingSum - deltaHamming1 - deltaHamming2, manhattanSum - deltaManhattan1 - deltaManhattan2, blankRow, blankCol);
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
        Stack<Board> stack = new Stack<Board>();
        if (blankCol > 0) {
            stack.push(getNeighbor(0, -1));
        }
        if (blankCol < N - 1) {
            stack.push(getNeighbor(0, 1));
        }
        if (blankRow > 0) {
            stack.push(getNeighbor(-1, 0));
        }
        if (blankRow < N - 1) {
            stack.push(getNeighbor(1, 0));
        }
        return stack;
    }

    private Board getNeighbor(int deltaRow, int deltaCol) {
        int index = (blankRow + deltaRow) * N + (blankCol + deltaCol);
        char block = a[index];
        int goalRow = (block - 1) / N;
        int goalCol = (block - 1) % N;
        int deltaManhattan = (Math.abs(blankCol + deltaCol - goalCol) + Math.abs(blankRow + deltaRow - goalRow)) -
                (Math.abs(blankCol - goalCol) + Math.abs(blankRow - goalRow));
        int deltaHamming = block != index + 1 ? 1 : 0;
        deltaHamming -= block != index + 1 - deltaCol - N * deltaRow ? 1 : 0;
        return new Board(exch(blankRow + deltaRow, blankCol + deltaCol, blankRow, blankCol), N, hammingSum - deltaHamming, manhattanSum - deltaManhattan, blankRow + deltaRow, blankCol + deltaCol);
    }

    private char[] exch(int row1, int col1, int row2, int col2) {
        char[] temp = new char[a.length];
        for (int i = 0; i < a.length; i++) {
            temp[i] = a[i];
        }

        char exch = temp[row1 * N + col1];
        temp[row1 * N + col1] = temp[row2 * N + col2];
        temp[row2 * N + col2] = exch;
        return temp;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N);
        for (int i = 0; i < a.length; i++) {
            if (i % N == 0) s.append("\n");
            s.append(String.format("%2d ", (int) a[i]));
        }
        return s.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {
    }
}

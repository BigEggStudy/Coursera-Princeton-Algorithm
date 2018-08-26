import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by jianming.xiao on 10/12/14.
 */
public class Solver {
    private SearchNode goalNode;

    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final int manhattan;
        private final int hamming;
        private final int moves;
        private final SearchNode previousNode;

        public SearchNode(Board board, int moves) {
            this.board = board;
            this.manhattan = board.manhattan();
            this.hamming = board.hamming();
            this.moves = moves;
            this.previousNode = null;
        }

        public SearchNode(Board board, int moves, SearchNode previousNode) {
            this.board = board;
            this.manhattan = board.manhattan();
            this.hamming = board.hamming();
            this.moves = moves;
            this.previousNode = previousNode;
        }

        @Override
        public int compareTo(SearchNode that) {
            if ((this.manhattan + this.moves) > (that.manhattan + that.moves)) return 1;
            if ((this.manhattan + this.moves) < (that.manhattan + that.moves)) return -1;
            if (this.manhattan > that.manhattan) return 1;
            if (this.manhattan < that.manhattan) return -1;
            if (this.hamming > that.hamming) return 1;
            if (this.hamming < that.hamming) return -1;
            return 0;
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
        MinPQ<SearchNode> twinPQ = new MinPQ<SearchNode>();
        Board initialBoard = initial;

        SearchNode initialNode = new SearchNode(initial, 0);
        pq.insert(initialNode);

        SearchNode twinInitial = new SearchNode(initialBoard.twin(), 0);
        twinPQ.insert(twinInitial);

        while (true) {
            SearchNode result = produce(pq);
            if (result != null) {
                goalNode = result;
                break;
            }
            result = produce(twinPQ);
            if (result != null) {
                break;
            }
        }
    }

    private SearchNode produce(MinPQ<SearchNode> pq) {
        SearchNode node = pq.delMin();
        if (node.board.isGoal()) {
            return node;
        }
        for (Board neighbor : node.board.neighbors()) {
            if (node.previousNode != null && neighbor.equals(node.previousNode.board)) {
                continue;
            }
            SearchNode newNode = new SearchNode(neighbor, node.moves + 1, node);
            pq.insert(newNode);
        }
        return null;
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return goalNode != null;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable()) {
            return -1;
        } else {
            return goalNode.moves;
        }
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;

        Stack<Board> result = new Stack<Board>();
        SearchNode current = goalNode;
        while (current != null) {
            result.push(current.board);
            current = current.previousNode;
        }
        return result;
    }

    // solve a slider puzzle
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}

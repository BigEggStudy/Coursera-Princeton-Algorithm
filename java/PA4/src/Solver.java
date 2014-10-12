/**
 * Created by jianming.xiao on 10/12/14.
 */
public class Solver {
    private MinPQ<SearchNode> pq;
    private SearchNode goalNode;
    private Board initialBoard;

    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private int moves;
        private SearchNode previousNode;

        private int priority() {
            return this.board.manhattan() + this.moves;
        }

        @Override
        public int compareTo(SearchNode that) {
            if ((this.priority()) > (that.priority())) return 1;
            if ((this.priority()) < (that.priority())) return -1;
            if ((this.board.manhattan()) > (that.board.manhattan())) return 1;
            if ((this.board.manhattan()) < (that.board.manhattan())) return -1;
            if ((this.board.hamming()) > (that.board.hamming())) return 1;
            if ((this.board.hamming()) < (that.board.hamming())) return -1;
            return 0;
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        pq = new MinPQ<SearchNode>();
        initialBoard = initial;

        SearchNode initialNode = new SearchNode();
        initialNode.board = initial;
        initialNode.moves = 0;
        pq.insert(initialNode);

        SearchNode twinNode = new SearchNode();
        twinNode.board = initialBoard.twin();
        twinNode.moves = 0;
        pq.insert(twinNode);

        while (!pq.isEmpty()) {
            SearchNode node = pq.delMin();
            if (node.board.isGoal()) {
                goalNode = node;
                break;
            }
            for (Board neighbor : node.board.neighbors()) {
                if (node.previousNode != null && neighbor.equals(node.previousNode.board)) {
                    continue;
                }
                SearchNode newNode = new SearchNode();
                newNode.board = neighbor;
                newNode.moves = node.moves + 1;
                newNode.previousNode = node;
                pq.insert(newNode);
            }
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        SearchNode current = goalNode;
        while (current.previousNode != null) {
            current = current.previousNode;
        }

        if (current.board.equals(initialBoard))
            return true;
        else
            return false;
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
        while (goalNode != null) {
            result.push(goalNode.board);
            goalNode = goalNode.previousNode;
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

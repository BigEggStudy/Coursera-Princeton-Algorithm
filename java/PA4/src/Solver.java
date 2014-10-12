/**
 * Created by jianming.xiao on 10/12/14.
 */
public class Solver {
    private MinPQ<SearchNode> pq;
    private MinPQ<SearchNode> results;

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
        results = new MinPQ<SearchNode>();

        SearchNode initialNode = new SearchNode();
        initialNode.board = initial;
        initialNode.moves = 0;
        pq.insert(initialNode);

        while (!pq.isEmpty()) {
            SearchNode node = pq.delMin();
            if (node.board.isGoal()) {
                results.insert(node);
                continue;
            }
            for (Board neighbors : node.board.neighbors()) {
                if (node.previousNode != null && neighbors == node.previousNode.board) {
                    continue;
                }
                SearchNode newNode = new SearchNode();
                newNode.board = neighbors;
                newNode.moves = node.moves + 1;
                newNode.previousNode = node;
                pq.insert(newNode);
            }
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return !results.isEmpty();
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (results.isEmpty()) {
            return -1;
        } else {
            return results.min().moves;
        }
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        Stack<Board> result = new Stack<Board>();
        SearchNode minMove = results.delMin();
        while (minMove != null) {
            result.push(minMove.board);
            minMove = minMove.previousNode;
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

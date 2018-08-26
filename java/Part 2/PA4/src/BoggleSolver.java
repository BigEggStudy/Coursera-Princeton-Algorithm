import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SET;

public class BoggleSolver
{
    private static final int R = 26;        // extended ASCII
    private Node root;      // root of trie
    // R-way trie node
    private static class Node {
        private Node[] next = new Node[R];
        private boolean isString;
    }

    private void add(String key) {
        if (key == null) throw new IllegalArgumentException("argument to add() is null");
        root = add(root, key, 0);
    }

    private Node add(Node x, String key, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.isString = true;
        }
        else {
            int c = key.charAt(d) - 'A';
            x.next[c] = add(x.next[c], key, d+1);
        }
        return x;
    }

    private boolean contains(String key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        Node x = get(root, key, 0);
        if (x == null) return false;
        return x.isString;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        int c = key.charAt(d) - 'A';
        return get(x.next[c], key, d + 1);
    }


    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        for (String s : dictionary) {
            this.add(s);
        }
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        SET<String> validWords = new SET<String>();
        boolean[][] visited = new boolean[board.rows()][board.cols()];

        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                getValidWords(board, i, j, visited, this.root, "", validWords);
            }
        }

        return validWords;
    }

    private void getValidWords(BoggleBoard board, int row, int col, boolean[][] visited, Node newRoot, String prefix, SET<String> validWordsSet) {
        if (row < 0 || row >= board.rows() || col < 0 || col >= board.cols()) return;
        if (visited[row][col]) return;
        if (newRoot == null) return;

        char letter = board.getLetter(row, col);
        String word = prefix + (letter == 'Q' ? "QU" : letter);
        if (letter == 'Q') {
            newRoot = newRoot.next[letter - 'A'];
            if (newRoot != null) {
                newRoot = newRoot.next['U' - 'A'];
                if (newRoot != null && newRoot.isString && word.length() >= 3 && !validWordsSet.contains(word)) {
                    validWordsSet.add(word);
                }
            }
        } else {
            newRoot = newRoot.next[letter - 'A'];
            if (newRoot != null && newRoot.isString && word.length() >= 3 && !validWordsSet.contains(word)) {
                validWordsSet.add(word);
            }
        }
        if (newRoot == null) {
            return;
        }

        visited[row][col] = true;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                getValidWords(board, row + i, col + j, visited, newRoot, word, validWordsSet);
            }
        }

        visited[row][col] = false;
    }


    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (!this.contains(word)) {
            return 0;
        }

        int length = word.length();
        if (length <= 2)      return 0;
        else if (length <= 4) return 1;
        else if (length <= 5) return 2;
        else if (length <= 6) return 3;
        else if (length <= 7) return 5;
        else                  return 11;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }
}

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
    private static final int R = 256;

    // apply Burrows-Wheeler transform, reading from standard input and writing to standard output
    public static void transform() {
        String s = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(s);

        for (int i = 0; i < csa.length(); i++) {
            if (csa.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }

        for (int i = 0; i < s.length(); i++) {
            BinaryStdOut.write(s.charAt((csa.index(i) - 1 + s.length()) % s.length()));
        }
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output
    public static void inverseTransform() {
        int first =  BinaryStdIn.readInt();
        char[] t = BinaryStdIn.readString().toCharArray();

        int[] count = new int[R + 1];
        int[] next = new int[t.length];

        for (int i = 0; i < t.length; i++)
            count[t[i] + 1]++;
        for (int r = 0; r < R; r++)
            count[r + 1] += count[r];
        for (int i = 0; i < t.length; i++)
            next[count[t[i]]++] = i;
        for (int i = next[first], c = 0; c < t.length; i = next[i], c++)
            BinaryStdOut.write(t[i]);

        BinaryStdOut.close();
    }

    // if args[0] is '-', apply Burrows-Wheeler transform
    // if args[0] is '+', apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException();
        }

        if (args[0].equals("-"))      transform();
        else if (args[0].equals("+")) inverseTransform();
        else                          throw new IllegalArgumentException();
    }
}
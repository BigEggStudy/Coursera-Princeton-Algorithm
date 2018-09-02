import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private final static int R = 256;

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        char[] chars = new char[R];
        for (char i = 0; i < R; i++) {
            chars[i] = i;
        }

        while (!BinaryStdIn.isEmpty()) {
            char ch = BinaryStdIn.readChar();

            char temp1 = chars[0];
            char temp2;
            char count = 0;
            for (count = 0; chars[count] != ch; count++) {
                temp2 = chars[count];
                chars[count] = temp1;
                temp1 = temp2;
            }

            chars[count] = temp1;
            BinaryStdOut.write(count);
            chars[0] = ch;
        }
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        char[] chars = new char[R];
        for (char i = 0; i < R; i++) {
            chars[i] = i;
        }

        while (!BinaryStdIn.isEmpty()) {
            char index = BinaryStdIn.readChar();
            char ch = chars[index];
            BinaryStdOut.write(ch);

            while (index > 0) {
                chars[index] = chars[--index];
            }
            chars[0] = ch;
        }
        BinaryStdOut.close();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException();
        }

        if (args[0].equals("-"))      encode();
        else if (args[0].equals("+")) decode();
        else                          throw new IllegalArgumentException();
    }
}

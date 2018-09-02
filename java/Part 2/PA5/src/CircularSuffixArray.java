import java.util.Arrays;

public class CircularSuffixArray {
    private class SuffixString implements Comparable<SuffixString> {
        private String k;
        private int offset;

        public SuffixString(String key, int offset)
        {
            k = key;
            this.offset = offset;
        }

        public int compareTo(SuffixString that) {
            int len = k.length();
            for (int i = 0; i < len; i++) {
                char a = k.charAt((i + this.offset) % len);
                char b = k.charAt((i + that.offset) % len);
                if (a > b) return 1;
                else if (a < b) return -1;
            }
            return 0;
        }

        public int getOffset() {
            return offset;
        }
    }

    private final int N;
    private final int[] indexs;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null) throw new IllegalArgumentException();

        this.N = s.length();
        this.indexs = new int[this.N];

        SuffixString[] substrings = new SuffixString[this.N];
        for (int i = 0; i < this.N; i++)
            substrings[i] = new SuffixString(s, i);

        Arrays.sort(substrings);

        for (int i = 0; i < substrings.length; i++) {
            indexs[i] = substrings[i].getOffset();
        }
    }

    // length of s
    public int length() {
        return N;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= N) throw new IllegalArgumentException();

        return this.indexs[i];
    }

    // unit testing (required)
    public static void main(String[] args) {
        CircularSuffixArray csa = new CircularSuffixArray("ABRACADABRA!");
        for (int i = 0; i < csa.length(); i++) {
            System.out.println(csa.index(i));
        }
    }
}
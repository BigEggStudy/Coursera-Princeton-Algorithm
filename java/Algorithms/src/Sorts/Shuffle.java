package Sorts;

import static Sorts.SortHelper.exch;

/**
 * Created by jianming.xiao on 10/25/14.
 */
public final class Shuffle {
    /**
     * This class should not be instantiated
     */
    private Shuffle() {
    }

    /**
     * Rearranges an array of objects in uniformly random order
     * (under the assumption that <tt>Math.random()</tt> generates independent
     * and uniformly distributed numbers between 0 and 1).
     *
     * @param a the array to be shuffled
     */
    public static void shuffle(Object[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = (int) (Math.random() * (i + 1));
            exch(a, i, r);
        }
    }
}

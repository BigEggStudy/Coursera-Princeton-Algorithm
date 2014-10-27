package Sorts;

import java.util.Random;

import static Sorts.SortHelper.exch;

/**
 * Created by jianming.xiao on 10/25/14.
 */
public final class Shuffle {
    private static Random random;

    static {
        random.setSeed(System.currentTimeMillis());
    }

    public static void shuffle(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = random.nextInt(i + 1);
            exch(a, i, r);
        }
    }
}

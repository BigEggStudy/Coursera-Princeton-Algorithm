package Sorts;

import static Sorts.SortHelper.*;

/**
 * Created by jianming.xiao on 10/25/14.
 */
public class ShellSort extends Sort {
    @Override
    protected void sortASE(Comparable[] a) {
        int N = a.length;

        int h = 1;
        while (h < N / 3) h = 3 * h + 1;

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h /= 3;
        }
    }

    @Override
    protected void sortDESC(Comparable[] a) {
        int N = a.length;

        int h = 1;
        while (h < N / 3) h = 3 * h + 1;

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && greater(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h /= 3;
        }
    }
}

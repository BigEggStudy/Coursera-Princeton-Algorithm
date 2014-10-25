package Sorts;

import static Sorts.SortHelper.*;

/**
 * Created by jianming.xiao on 10/25/14.
 */
public class InsertionSort extends Sort {
    @Override
    protected void sortASE(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1]))
                    exch(a, j, j - 1);
                else
                    break;
                ;
            }
        }
    }

    @Override
    protected void sortDESC(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (greater(a[j], a[j - 1]))
                    exch(a, j, j - 1);
                else
                    break;
                ;
            }
        }
    }
}

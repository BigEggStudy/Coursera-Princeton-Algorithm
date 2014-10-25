package Sorts;

import static Sorts.SortHelper.*;

/**
 * Created by jianming.xiao on 10/25/14.
 */
public class SelectionSort extends Sort {
    @Override
    public void sort(Comparable[] a, SortOrder order) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int exchIndex = i;
            for (int j = i + 1; j < N; j++) {
                if (order == SortOrder.ASC)
                    if (less(a[j], a[exchIndex]))
                        exchIndex = j;
                    else if (greater(a[j], a[exchIndex]))
                        exchIndex = j;
            }
            exch(a, i, exchIndex);
        }
    }
}

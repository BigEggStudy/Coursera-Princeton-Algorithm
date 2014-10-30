package Sorts;

import static Sorts.SortHelper.*;

/**
 * Created by jianming.xiao on 10/25/14.
 */
public class SelectionSort {
    /**
     * This class should not be instantiated.
     */
    private SelectionSort() {
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     *
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        sort(a, SortOrder.ASC);
    }

    /**
     * Rearranges the array in ascending order
     *
     * @param a     the array to be sorted
     * @param order the sort order
     */
    public static void sort(Comparable[] a, SortOrder order) {
        if (a == null) throw new IllegalArgumentException();

        if (order == SortOrder.ASC)
            sortASC(a);
        else
            sortDESC(a);
    }

    /**
     * Rearranges the array in ascending order, using the ASC order.
     *
     * @param a the array to be sorted
     */
    protected static void sortASC(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min]))
                    min = j;
            }
            exch(a, i, min);
        }
    }

    /**
     * Rearranges the array in ascending order, using the DESC order.
     *
     * @param a the array to be sorted
     */
    protected static void sortDESC(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int max = i;
            for (int j = i + 1; j < N; j++) {
                if (greater(a[j], a[max]))
                    max = j;
            }
            exch(a, i, max);
        }
    }
}

package Sorts;

import static Sorts.SortHelper.*;

/**
 * Created by jianming.xiao on 10/25/14.
 */
public class InsertionSort {
    /**
     * This class should not be instantiated.
     */
    private InsertionSort() {
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
     * Rearranges the array in specific order, using the natural order.
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
     * Rearranges the array in ascending order, using the natural order.
     *
     * @param a the array to be sorted
     */
    protected static void sortASC(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1]))
                    exch(a, j, j - 1);
                else
                    break;
            }
        }
    }

    /**
     * Rearranges the array in descending order, using the natural order.
     *
     * @param a the array to be sorted
     */
    protected static void sortDESC(Comparable[] a) {
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

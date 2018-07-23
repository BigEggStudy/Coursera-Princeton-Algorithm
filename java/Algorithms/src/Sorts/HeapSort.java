package Sorts;

import java.util.Comparator;

/**
 * Created by jianming.xiao on 11/9/14.
 */
public class HeapSort {
    /**
     * This class should not be instantiated.
     */
    private HeapSort() {
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
     * Rearranges the array in ascending order, using a comparator.
     *
     * @param a the array to be sorted
     * @param c the comparator that specifying the order
     */
    public static void sort(Object[] a, Comparator c) {
        sort(a, c, SortOrder.ASC);
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
     * Rearranges the array in specific order, using a comparator.
     *
     * @param a     the array to be sorted
     * @param c     the comparator that specifying the order
     * @param order the sort order
     */
    public static void sort(Object[] a, Comparator c, SortOrder order) {
        if (a == null) throw new IllegalArgumentException();
        if (c == null) throw new IllegalArgumentException();

        if (order == SortOrder.ASC) {
            sortASC(a, c);
        } else {
            sortDESC(a, c);
        }
    }

    protected static void sortASC(Comparable[] pq) {
        int N = pq.length;
        for (int k = N / 2; k >= 1; k--)
            sinkASC(pq, k, N);
        while (N > 1) {
            exch(pq, 1, N--);
            sinkASC(pq, 1, N);
        }
    }

    private static void sinkASC(Comparable[] pq, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(pq, j, j + 1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

    protected static void sortDESC(Comparable[] pq) {
        int N = pq.length;
        for (int k = N / 2; k >= 1; k--)
            sinkDESC(pq, k, N);
        while (N > 1) {
            exch(pq, 1, N--);
            sinkDESC(pq, 1, N);
        }
    }

    private static void sinkDESC(Comparable[] pq, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && greater(pq, j, j + 1)) j++;
            if (!greater(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

    protected static void sortASC(Object[] pq, Comparator comparator) {
        int N = pq.length;
        for (int k = N / 2; k >= 1; k--)
            sinkASC(pq, comparator, k, N);
        while (N > 1) {
            exch(pq, 1, N--);
            sinkASC(pq, comparator, 1, N);
        }
    }

    private static void sinkASC(Object[] pq, Comparator comparator, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(pq, comparator, j, j + 1)) j++;
            if (!less(pq, comparator, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

    protected static void sortDESC(Object[] pq, Comparator comparator) {
        int N = pq.length;
        for (int k = N / 2; k >= 1; k--)
            sinkDESC(pq, comparator, k, N);
        while (N > 1) {
            exch(pq, 1, N--);
            sinkDESC(pq, comparator, 1, N);
        }
    }

    private static void sinkDESC(Object[] pq, Comparator comparator, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && greater(pq, comparator, j, j + 1)) j++;
            if (!greater(pq, comparator, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }


    private static boolean less(Comparable[] pq, int i, int j) {
        return SortHelper.less(pq[i - 1], pq[j - 1]);
    }

    private static boolean greater(Comparable[] pq, int i, int j) {
        return SortHelper.greater(pq[i - 1], pq[j - 1]);
    }

    private static boolean less(Object[] pq, Comparator comparator, int i, int j) {
        return SortHelper.less(comparator, pq[i - 1], pq[j - 1]);
    }

    private static boolean greater(Object[] pq, Comparator comparator, int i, int j) {
        return SortHelper.greater(comparator, pq[i - 1], pq[j - 1]);
    }

    private static void exch(Object[] a, int i, int j) {
        SortHelper.exch(a, i - 1, j - 1);
    }
}

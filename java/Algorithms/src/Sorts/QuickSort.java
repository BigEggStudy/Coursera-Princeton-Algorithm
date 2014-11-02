package Sorts;

import java.util.Comparator;

import static Sorts.Shuffle.shuffle;
import static Sorts.SortHelper.*;

/**
 * Created by jianming.xiao on 11/2/14.
 */
public class QuickSort {
    /**
     * This class should not be instantiated.
     */
    private QuickSort() {
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

    protected static void sortASC(Comparable[] a) {
        shuffle(a);
        sortASC(a, 0, a.length - 1);
    }

    private static int partitionASC(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v))
                if (i == hi) break;
            while (less(v, a[--j]))
                if (j == lo) break;
            if (i >= j) break;
            ;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static void sortASC(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partitionASC(a, lo, hi);
        sortASC(a, lo, j - 1);
        sortASC(a, j + 1, hi);
    }

    protected static void sortDESC(Comparable[] a) {
        shuffle(a);
        sortDESC(a, 0, a.length - 1);
    }

    private static int partitionDESC(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (greater(a[++i], v))
                if (i == hi) break;
            while (greater(v, a[--j]))
                if (j == lo) break;
            if (i >= j) break;
            ;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static void sortDESC(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partitionDESC(a, lo, hi);
        sortDESC(a, lo, j - 1);
        sortDESC(a, j + 1, hi);
    }

    protected static void sortASC(Object[] a, Comparator c) {
        shuffle(a);
        sortASC(a, c, 0, a.length - 1);
    }

    private static int partitionASC(Object[] a, Comparator c, int lo, int hi) {
        int i = lo, j = hi + 1;
        Object v = a[lo];
        while (true) {
            while (less(c, a[++i], v))
                if (i == hi) break;
            while (less(c, v, a[--j]))
                if (j == lo) break;
            if (i >= j) break;
            ;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static void sortASC(Object[] a, Comparator c, int lo, int hi) {
        if (hi <= lo) return;
        int j = partitionASC(a, c, lo, hi);
        sortASC(a, c, lo, j - 1);
        sortASC(a, c, j + 1, hi);
    }

    protected static void sortDESC(Object[] a, Comparator c) {
        shuffle(a);
        sortDESC(a, c, 0, a.length - 1);
    }

    private static int partitionDESC(Object[] a, Comparator c, int lo, int hi) {
        int i = lo, j = hi + 1;
        Object v = a[lo];
        while (true) {
            while (greater(c, a[++i], v))
                if (i == hi) break;
            while (greater(c, v, a[--j]))
                if (j == lo) break;
            if (i >= j) break;
            ;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static void sortDESC(Object[] a, Comparator c, int lo, int hi) {
        if (hi <= lo) return;
        int j = partitionDESC(a, c, lo, hi);
        sortDESC(a, c, lo, j - 1);
        sortDESC(a, c, j + 1, hi);
    }
}

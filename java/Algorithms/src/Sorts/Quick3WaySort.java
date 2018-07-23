package Sorts;

import java.util.Comparator;

import static Sorts.Shuffle.shuffle;
import static Sorts.SortHelper.exch;

/**
 * Created by jianming.xiao on 11/2/14.
 */
public class Quick3WaySort {
    /**
     * This class should not be instantiated.
     */
    private Quick3WaySort() {
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

    private static void sortASC(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        int i = lo;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        }
        sortASC(a, lo, lt - 1);
        sortASC(a, gt + 1, hi);
    }

    protected static void sortDESC(Comparable[] a) {
        shuffle(a);
        sortDESC(a, 0, a.length - 1);
    }

    private static void sortDESC(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        int i = lo;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp > 0) exch(a, lt++, i++);
            else if (cmp < 0) exch(a, i, gt--);
            else i++;
        }
        sortDESC(a, lo, lt - 1);
        sortDESC(a, gt + 1, hi);
    }

    protected static void sortASC(Object[] a, Comparator c) {
        shuffle(a);
        sortASC(a, c, 0, a.length - 1);
    }

    private static void sortASC(Object[] a, Comparator c, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        int i = lo;
        Object v = a[lo];
        while (i <= gt) {
            int cmp = c.compare(a[i], v);
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        }
        sortASC(a, c, lo, lt - 1);
        sortASC(a, c, gt + 1, hi);
    }

    protected static void sortDESC(Object[] a, Comparator c) {
        shuffle(a);
        sortDESC(a, c, 0, a.length - 1);
    }

    private static void sortDESC(Object[] a, Comparator c, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        int i = lo;
        Object v = a[lo];
        while (i <= gt) {
            int cmp = c.compare(a[i], v);
            if (cmp > 0) exch(a, lt++, i++);
            else if (cmp < 0) exch(a, i, gt--);
            else i++;
        }
        sortDESC(a, c, lo, lt - 1);
        sortDESC(a, c, gt + 1, hi);
    }
}

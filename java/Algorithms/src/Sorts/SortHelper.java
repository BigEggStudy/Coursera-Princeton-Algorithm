package Sorts;

import java.util.Comparator;

/**
 * Created by jianming.xiao on 10/12/14.
 */
public class SortHelper {
    /**
     * This class should not be instantiated.
     */
    private SortHelper() {
    }

    /**
     * Is v < w ?
     *
     * @param v
     * @param w
     * @return true if v < w, otherwise false
     */
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * Is v < w ?
     *
     * @param c the comparator
     * @param v
     * @param w
     * @return true if v < w, otherwise false
     */
    public static boolean less(Comparator c, Object v, Object w) {
        return c.compare(v, w) < 0;
    }

    /**
     * Is v > w ?
     *
     * @param v
     * @param w
     * @return true if v > w, otherwise false
     */
    public static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    /**
     * Is v > w ?
     *
     * @param c the comparator
     * @param v
     * @param w
     * @return true if v > w, otherwise false
     */
    public static boolean greater(Comparator c, Object v, Object w) {
        return c.compare(v, w) > 0;
    }

    /**
     * Exchange a[i] and a[j]  (for indirect sort)
     *
     * @param a the array
     * @param i
     * @param j
     */
    public static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /**
     * Check if array is sorted, using the natural order.
     *
     * @param a the array
     * @return true if array is sorted, otherwise false.
     */
    public static boolean isSorted(Comparable[] a) {
        return isSorted(a, SortOrder.ASC);
    }

    /**
     * Check if array is sorted
     *
     * @param a     the array
     * @param order The sort order
     * @return true if array is sorted, otherwise false.
     */
    public static boolean isSorted(Comparable[] a, SortOrder order) {
        return isSorted(a, 0, a.length - 1, order);
    }

    /**
     * Is the array sorted from a[lo] to a[hi]
     *
     * @param a     the array
     * @param lo    the low index
     * @param hi    the high index
     * @param order the sort order
     * @return true if array is sorted, otherwise false.
     */
    public static boolean isSorted(Comparable[] a, int lo, int hi, SortOrder order) {
        if (order == SortOrder.ASC) {
            for (int i = lo + 1; i <= hi; i++)
                if (less(a[i], a[i - 1])) return false;
        } else {
            for (int i = lo + 1; i <= hi; i++)
                if (greater(a[i], a[i - 1])) return false;
        }
        return true;
    }

    /**
     * Check if array is sorted, using the natural order.
     *
     * @param a the array
     * @param c the comparator
     * @return true if array is sorted, otherwise false.
     */
    public static boolean isSorted(Object[] a, Comparator c) {
        return isSorted(a, c, SortOrder.ASC);
    }

    /**
     * Check if array is sorted
     *
     * @param a     the array
     * @param c     the comparator
     * @param order The sort order
     * @return true if array is sorted, otherwise false.
     */
    public static boolean isSorted(Object[] a, Comparator c, SortOrder order) {
        return isSorted(a, c, 0, a.length - 1, order);
    }

    /**
     * Is the array sorted from a[lo] to a[hi]
     *
     * @param a     the array
     * @param c     the comparator
     * @param lo    the low index
     * @param hi    the high index
     * @param order the sort order
     * @return true if array is sorted, otherwise false.
     */
    public static boolean isSorted(Object[] a, Comparator c, int lo, int hi, SortOrder order) {
        if (order == SortOrder.ASC) {
            for (int i = lo + 1; i <= hi; i++)
                if (less(c, a[i], a[i - 1])) return false;
        } else {
            for (int i = lo + 1; i <= hi; i++)
                if (greater(c, a[i], a[i - 1])) return false;
        }
        return true;
    }
}

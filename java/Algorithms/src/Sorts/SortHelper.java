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

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static boolean less(Comparator c, Object v, Object w) {
        return c.compare(v, w) < 0;
    }

    public static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    public static boolean greater(Comparator c, Object v, Object w) {
        return c.compare(v, w) > 0;
    }

    public static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static boolean isSorted(Comparable[] a) {
        return isSorted(a, SortOrder.ASC);
    }

    public static boolean isSorted(Comparable[] a, SortOrder order) {
        return isSorted(a, 0, a.length - 1, order);
    }

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

    public static boolean isSorted(Object[] a, Comparator c) {
        return isSorted(a, c, SortOrder.ASC);
    }

    public static boolean isSorted(Object[] a, Comparator c, SortOrder order) {
        return isSorted(a, c, 0, a.length - 1, order);
    }

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

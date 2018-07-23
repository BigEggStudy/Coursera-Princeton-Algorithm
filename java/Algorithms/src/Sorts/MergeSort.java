package Sorts;

import java.util.Comparator;

import static Sorts.SortHelper.greater;
import static Sorts.SortHelper.less;

/**
 * Created by jianming.xiao on 11/1/14.
 */
public class MergeSort {
    private static final int CUT_OFF = 7;

    /**
     * This class should not be instantiated.
     */
    private MergeSort() {
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
        Comparable[] aux = new Comparable[a.length];
        sortASC(a, aux, 0, a.length - 1);
    }

    protected static void sortASC(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo + CUT_OFF - 1) {
            //   InsertionSort.sort(a, lo, hi, SortOrder.ASC);
        }

        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sortASC(a, aux, lo, mid);
        sortASC(a, aux, mid + 1, hi);
        mergeASC(a, aux, lo, mid, hi);
    }

    protected static void mergeASC(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        if (!less(a[mid + 1], a[mid])) return;

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    protected static void sortDESC(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sortDESC(a, aux, 0, a.length - 1);
    }

    protected static void sortDESC(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sortDESC(a, aux, lo, mid);
        sortDESC(a, aux, mid + 1, hi);
        mergeDESC(a, aux, lo, mid, hi);
    }

    protected static void mergeDESC(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        if (!greater(a[mid + 1], a[mid])) return;

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (greater(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    protected static void sortASC(Object[] a, Comparator c) {
        Object[] aux = new Object[a.length];
        sortASC(a, aux, c, 0, a.length - 1);
    }

    protected static void sortASC(Object[] a, Object[] aux, Comparator c, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sortASC(a, aux, c, lo, mid);
        sortASC(a, aux, c, mid + 1, hi);
        mergeASC(a, aux, c, lo, mid, hi);
    }

    protected static void mergeASC(Object[] a, Object[] aux, Comparator c, int lo, int mid, int hi) {
        if (!less(c, a[mid + 1], a[mid])) return;

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(c, aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    protected static void sortDESC(Object[] a, Comparator c) {
        Object[] aux = new Object[a.length];
        sortDESC(a, aux, c, 0, a.length - 1);
    }

    protected static void sortDESC(Object[] a, Object[] aux, Comparator c, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sortDESC(a, aux, c, lo, mid);
        sortDESC(a, aux, c, mid + 1, hi);
        mergeDESC(a, aux, c, lo, mid, hi);
    }

    protected static void mergeDESC(Object[] a, Object[] aux, Comparator c, int lo, int mid, int hi) {
        if (!greater(c, a[mid + 1], a[mid])) return;

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (greater(c, aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }
}

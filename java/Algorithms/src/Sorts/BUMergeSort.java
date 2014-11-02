package Sorts;

import java.util.Comparator;

import static Sorts.SortHelper.greater;
import static Sorts.SortHelper.less;

/**
 * Created by jianming.xiao on 11/2/14.
 */
public class BUMergeSort {
    /**
     * This class should not be instantiated.
     */
    private BUMergeSort() {
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
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                mergeASC(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
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
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                mergeDESC(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
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
        int N = a.length;
        Object[] aux = new Object[N];
        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                mergeASC(a, aux, c, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
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
        int N = a.length;
        Object[] aux = new Object[N];
        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                mergeDESC(a, aux, c, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
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

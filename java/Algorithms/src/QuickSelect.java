import java.util.Comparator;

import static Sorts.SortHelper.exch;
import static Sorts.SortHelper.less;

/**
 * Created by jianming.xiao on 11/2/14.
 */
public class QuickSelect {
    /**
     * This class should not be instantiated.
     */
    private QuickSelect() {
    }

    /**
     * Rearranges the array so that a[k] contains the kth smallest key;
     * a[0] through a[k-1] are less than (or equal to) a[k]; and
     * a[k+1] through a[N-1] are greater than (or equal to) a[k].
     *
     * @param a the array
     * @param k find the kth smallest
     */
    public static Comparable select(Comparable[] a, int k) {
        if (k < 0 || k >= a.length) throw new IndexOutOfBoundsException();
        int lo = 0, hi = a.length - 1;
        while (true) {
            int j = partition(a, lo, hi);
            if (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else return a[j];
        }
    }

    /**
     * Rearranges the array so that a[k] contains the kth smallest key;
     * a[0] through a[k-1] are less than (or equal to) a[k]; and
     * a[k+1] through a[N-1] are greater than (or equal to) a[k].
     *
     * @param a the array
     * @param k find the kth smallest
     */
    public static Object select(Object[] a, Comparator c, int k) {
        if (k < 0 || k >= a.length) throw new IndexOutOfBoundsException();
        int lo = 0, hi = a.length - 1;
        while (true) {
            int j = partition(a, c, lo, hi);
            if (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else return a[j];
        }
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v))
                if (i == hi) break;
            while (less(v, a[++j]))
                if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static int partition(Object[] a, Comparator c, int lo, int hi) {
        int i = lo, j = hi + 1;
        Object v = a[lo];
        while (true) {
            while (less(c, a[++i], v))
                if (i == hi) break;
            while (less(c, v, a[++j]))
                if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
}

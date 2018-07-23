/**
 * Created by jianming.xiao on 10/6/14.
 */
public class BinarySearch {
    /**
     * This class should not be instantiated.
     */
    private BinarySearch() {
    }

    /**
     * Searches for the integer key in the sorted array a[].
     *
     * @param a   the array of integers, must be sorted in ascending order
     * @param key the search key
     * @return index of key in array a[] if present; -1 if not present
     */
    public static int rank(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
}

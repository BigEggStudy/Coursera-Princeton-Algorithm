package Sorts;

/**
 * Created by jianming.xiao on 10/25/14.
 */
public abstract class Sort implements ISort {
    @Override
    public void sort(Comparable[] a) {
        sort(a, SortOrder.ASC);
    }
}

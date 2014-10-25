package Sorts;

/**
 * Created by jianming.xiao on 10/25/14.
 */
public interface ISort {
    void sort(Comparable[] a);

    void sort(Comparable[] a, SortOrder order);
}

package Sorts;

/**
 * Created by jianming.xiao on 10/25/14.
 */
public abstract class Sort implements ISort {
    @Override
    public void sort(Comparable[] a) {
        sort(a, SortOrder.ASC);
    }

    @Override
    public void sort(Comparable[] a, SortOrder order) {
        if (a == null) throw new IllegalArgumentException();

        if (order == SortOrder.ASC)
            sortASE(a);
        else
            sortDESC(a);
    }

    protected abstract void sortASE(Comparable[] a);

    protected abstract void sortDESC(Comparable[] a);
}

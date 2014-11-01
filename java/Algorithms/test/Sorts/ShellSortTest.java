package Sorts;

import org.junit.Test;

import static Sorts.SortHelper.isSorted;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ShellSortTest {
    @Test(expected = IllegalArgumentException.class)
    public void testSort_NullArray() throws Exception {
        ShellSort.sort(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSort_ASCOrder_NullArray() throws Exception {
        ShellSort.sort(null, SortOrder.ASC);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSort_DESCOrder_NullArray() throws Exception {
        ShellSort.sort(null, SortOrder.DESC);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSortWithComparator_NullArray() throws Exception {
        ShellSort.sort(null, Point.X_ORDER);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSortWithComparator_NullComparator() throws Exception {
        Point[] points = new Point[10];
        for (int i = 0; i < 10; i++) {
            points[i] = new Point(i, 10 - i);
        }
        ShellSort.sort(points, null, SortOrder.ASC);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSortWithComparator_ASCOrder_NullArray() throws Exception {
        ShellSort.sort(null, Point.X_ORDER, SortOrder.ASC);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSortWithComparator_ASCOrder_NullComparator() throws Exception {
        Point[] points = new Point[10];
        for (int i = 0; i < 10; i++) {
            points[i] = new Point(i, 10 - i);
        }
        ShellSort.sort(points, null, SortOrder.ASC);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSortWithComparator_DESCOrder_NullArray() throws Exception {
        ShellSort.sort(null, Point.X_ORDER, SortOrder.DESC);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSortWithComparator_DESCOrder_NullComparator() throws Exception {
        Point[] points = new Point[10];
        for (int i = 0; i < 10; i++) {
            points[i] = new Point(i, 10 - i);
        }
        ShellSort.sort(points, null, SortOrder.DESC);
    }

    @Test
    public void testSort() throws Exception {
        Integer[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Shuffle.shuffle(a);

        assertThat(isSorted(a), equalTo(false));
        ShellSort.sort(a);
        assertThat(isSorted(a), equalTo(true));
    }

    @Test
    public void testSort_ASC() throws Exception {
        Integer[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Shuffle.shuffle(a);

        assertThat(isSorted(a, SortOrder.ASC), equalTo(false));
        ShellSort.sort(a, SortOrder.ASC);
        assertThat(isSorted(a, SortOrder.ASC), equalTo(true));
    }

    @Test
    public void testSort_DESC() throws Exception {
        Integer[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Shuffle.shuffle(a);

        assertThat(isSorted(a, SortOrder.DESC), equalTo(false));
        ShellSort.sort(a, SortOrder.DESC);
        assertThat(isSorted(a, SortOrder.DESC), equalTo(true));
    }


    @Test
    public void testSortWithComparator() throws Exception {
        Point[] points = new Point[10];
        for (int i = 0; i < 10; i++) {
            points[i] = new Point(i, 10 - i);
        }
        Shuffle.shuffle(points);

        assertThat(isSorted(points, Point.X_ORDER), equalTo(false));
        ShellSort.sort(points, Point.X_ORDER);
        assertThat(isSorted(points, Point.X_ORDER), equalTo(true));
    }

    @Test
    public void testSortWithComparator_ASC() throws Exception {
        Point[] points = new Point[10];
        for (int i = 0; i < 10; i++) {
            points[i] = new Point(i, 10 - i);
        }
        Shuffle.shuffle(points);

        assertThat(isSorted(points, Point.X_ORDER, SortOrder.ASC), equalTo(false));
        ShellSort.sort(points, Point.X_ORDER, SortOrder.ASC);
        assertThat(isSorted(points, Point.X_ORDER, SortOrder.ASC), equalTo(true));
    }

    @Test
    public void testSortWithComparator_DESC() throws Exception {
        Point[] points = new Point[10];
        for (int i = 0; i < 10; i++) {
            points[i] = new Point(i, 10 - i);
        }
        Shuffle.shuffle(points);

        assertThat(isSorted(points, Point.X_ORDER, SortOrder.DESC), equalTo(false));
        ShellSort.sort(points, Point.X_ORDER, SortOrder.DESC);
        assertThat(isSorted(points, Point.X_ORDER, SortOrder.DESC), equalTo(true));
    }
}
import Sorts.Point;
import org.junit.Test;

import static Sorts.Shuffle.shuffle;
import static Sorts.SortHelper.less;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class QuickSelectTest {
    @Test(expected = IllegalArgumentException.class)
    public void testSelect_NullArray() throws Exception {
        QuickSelect.select(null, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSelect_KLess0() {
        Integer[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        QuickSelect.select(a, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSelect_KGreaterOrEqualThanN() {
        Integer[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        QuickSelect.select(a, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectWithComparator_NullArray() throws Exception {
        QuickSelect.select(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectWithComparator_NullComparator() {
        Point[] points = new Point[10];
        for (int i = 0; i < 10; i++) {
            points[i] = new Point(i, 10 - i);
        }
        QuickSelect.select(points, null, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSelectWithComparator_KLess0() {
        Point[] points = new Point[10];
        for (int i = 0; i < 10; i++) {
            points[i] = new Point(i, 10 - i);
        }
        QuickSelect.select(points, Point.X_ORDER, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSelectWithComparator_KGreaterOrEqualThanN() {
        Point[] points = new Point[10];
        for (int i = 0; i < 10; i++) {
            points[i] = new Point(i, 10 - i);
        }
        QuickSelect.select(points, Point.X_ORDER, 10);
    }

    @Test
    public void testSelection() throws Exception {
        Integer[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        shuffle(a);
        Comparable v = QuickSelect.select(a, 3);
        assertThat(v, equalTo((Comparable) 3));
        for (int i = 0; i < 3; i++) {
            assertThat(less(a[i], v), equalTo(true));
        }
        assertThat(a[3], equalTo(v));
    }

    @Test
    public void testSelectionWithComparator() throws Exception {
        Point[] points = new Point[10];
        for (int i = 0; i < 10; i++) {
            points[i] = new Point(i, 10 - i);
        }
        shuffle(points);
        Object v = QuickSelect.select(points, Point.X_ORDER, 3);
        assertThat(((Point) v).getX(), equalTo(3));
        assertThat(((Point) v).getY(), equalTo(7));
        for (int i = 0; i < 3; i++) {
            assertThat(less(Point.X_ORDER, points[i], v), equalTo(true));
        }
        assertThat(points[3], equalTo(v));
    }
}
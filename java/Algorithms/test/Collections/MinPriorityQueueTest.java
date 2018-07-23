package Collections;

import Sorts.HeapSort;
import Sorts.Point;
import Sorts.Shuffle;
import Sorts.SortOrder;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class MinPriorityQueueTest {
    @Test
    public void testMinPriorityQueue_InitWithKey() throws Exception {
        Integer[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Shuffle.shuffle(a);

        MinPriorityQueue<Integer> pq = new MinPriorityQueue<Integer>(a);
        assertThat(pq.isEmpty(), equalTo(false));
        assertThat(pq.size(), equalTo(10));

        for (int i = 0; i < 10; i++) {
            Integer result = pq.min();
            assertThat(result, equalTo(i));
            result = pq.delMin();
            assertThat(result, equalTo(i));
        }
        assertThat(pq.isEmpty(), equalTo(true));
        assertThat(pq.size(), equalTo(0));
    }

    @Test
    public void testMinPriorityQueue_Insert() throws Exception {
        Integer[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Shuffle.shuffle(a);

        MinPriorityQueue<Integer> pq = new MinPriorityQueue<Integer>();
        for (Integer value : a) {
            pq.insert(value);
        }
        assertThat(pq.isEmpty(), equalTo(false));
        assertThat(pq.size(), equalTo(10));

        for (int i = 0; i < 10; i++) {
            Integer result = pq.min();
            assertThat(result, equalTo(i));
            result = pq.delMin();
            assertThat(result, equalTo(i));
        }
        assertThat(pq.isEmpty(), equalTo(true));
        assertThat(pq.size(), equalTo(0));
    }

    @Test
    public void testMinPriorityQueue_Iterator() throws Exception {
        Integer[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Shuffle.shuffle(a);

        MinPriorityQueue<Integer> pq = new MinPriorityQueue<Integer>();
        for (Integer value : a) {
            pq.insert(value);
        }
        assertThat(pq.isEmpty(), equalTo(false));
        assertThat(pq.size(), equalTo(10));

        Integer expectValue = 0;
        for (Integer value : pq) {
            assertThat(value, equalTo(expectValue++));
        }
        assertThat(pq.isEmpty(), equalTo(false));
        assertThat(pq.size(), equalTo(10));
    }

    @Test
    public void testMinPriorityQueue_Comparator() throws Exception {
        Point[] points = new Point[10];
        for (int i = 0; i < 10; i++) {
            points[i] = new Point(i, 10 - i);
        }
        Shuffle.shuffle(points);

        MinPriorityQueue<Point> pq = new MinPriorityQueue<Point>(Point.X_ORDER);
        for (Point value : points) {
            pq.insert(value);
        }
        assertThat(pq.isEmpty(), equalTo(false));
        assertThat(pq.size(), equalTo(10));

        Point[] expectPoints = new Point[10];
        for (int i = 0; i < 10; i++) {
            expectPoints[i] = new Point(i, 10 - i);
        }
        Shuffle.shuffle(expectPoints);
        HeapSort.sort(expectPoints, Point.X_ORDER, SortOrder.ASC);

        for (int i = 0; i < 10; i++) {
            Point result = pq.min();
            assertThat(result.getX(), equalTo(expectPoints[i].getX()));
            assertThat(result.getY(), equalTo(expectPoints[i].getY()));
            result = pq.delMin();
            assertThat(result.getX(), equalTo(expectPoints[i].getX()));
            assertThat(result.getY(), equalTo(expectPoints[i].getY()));
        }
        assertThat(pq.isEmpty(), equalTo(true));
        assertThat(pq.size(), equalTo(0));
    }

    @Test
    public void testMinPriorityQueue_Comparator_Iterator() throws Exception {
        Point[] points = new Point[10];
        for (int i = 0; i < 10; i++) {
            points[i] = new Point(i, 10 - i);
        }
        Shuffle.shuffle(points);

        MinPriorityQueue<Point> pq = new MinPriorityQueue<Point>(Point.X_ORDER);
        for (Point value : points) {
            pq.insert(value);
        }
        assertThat(pq.isEmpty(), equalTo(false));
        assertThat(pq.size(), equalTo(10));

        Point[] expectPoints = new Point[10];
        for (int i = 0; i < 10; i++) {
            expectPoints[i] = new Point(i, 10 - i);
        }
        Shuffle.shuffle(expectPoints);
        HeapSort.sort(expectPoints, Point.X_ORDER, SortOrder.ASC);

        Integer expectIndex = 0;
        for (Point value : pq) {
            assertThat(value.getX(), equalTo(expectPoints[expectIndex].getX()));
            assertThat(value.getY(), equalTo(expectPoints[expectIndex].getY()));
            expectIndex++;

        }
        assertThat(pq.isEmpty(), equalTo(false));
        assertThat(pq.size(), equalTo(10));
    }
}
package Collections;

import Sorts.HeapSort;
import Sorts.Point;
import Sorts.Shuffle;
import Sorts.SortOrder;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class MaxPriorityQueueTest {
    @Test
    public void testMaxPriorityQueue_InitWithKey() throws Exception {
        Integer[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Shuffle.shuffle(a);

        MaxPriorityQueue<Integer> pq = new MaxPriorityQueue<Integer>(a);
        assertThat(pq.isEmpty(), equalTo(false));
        assertThat(pq.size(), equalTo(10));

        for (int i = 0; i < 10; i++) {
            Integer result = pq.max();
            assertThat(result, equalTo(10 - i - 1));
            result = pq.delMax();
            assertThat(result, equalTo(10 - i - 1));
        }
        assertThat(pq.isEmpty(), equalTo(true));
        assertThat(pq.size(), equalTo(0));
    }

    @Test
    public void testMaxPriorityQueue_Insert() throws Exception {
        Integer[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Shuffle.shuffle(a);

        MaxPriorityQueue<Integer> pq = new MaxPriorityQueue<Integer>();
        for (Integer value : a) {
            pq.insert(value);
        }
        assertThat(pq.isEmpty(), equalTo(false));
        assertThat(pq.size(), equalTo(10));

        for (int i = 0; i < 10; i++) {
            Integer result = pq.max();
            assertThat(result, equalTo(10 - i - 1));
            result = pq.delMax();
            assertThat(result, equalTo(10 - i - 1));
        }
        assertThat(pq.isEmpty(), equalTo(true));
        assertThat(pq.size(), equalTo(0));
    }

    @Test
    public void testMaxPriorityQueue_Iterator() throws Exception {
        Integer[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Shuffle.shuffle(a);

        MaxPriorityQueue<Integer> pq = new MaxPriorityQueue<Integer>();
        for (Integer value : a) {
            pq.insert(value);
        }
        assertThat(pq.isEmpty(), equalTo(false));
        assertThat(pq.size(), equalTo(10));

        Integer expectValue = 9;
        for (Integer value : pq) {
            assertThat(value, equalTo(expectValue--));
        }
        assertThat(pq.isEmpty(), equalTo(false));
        assertThat(pq.size(), equalTo(10));
    }

    @Test
    public void testMaxPriorityQueue_Comparator() throws Exception {
        Point[] points = new Point[10];
        for (int i = 0; i < 10; i++) {
            points[i] = new Point(i, 10 - i);
        }
        Shuffle.shuffle(points);

        MaxPriorityQueue<Point> pq = new MaxPriorityQueue<Point>(Point.X_ORDER);
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
        HeapSort.sort(expectPoints, Point.X_ORDER, SortOrder.DESC);

        for (int i = 0; i < 10; i++) {
            Point result = pq.max();
            assertThat(result.getX(), equalTo(expectPoints[i].getX()));
            assertThat(result.getY(), equalTo(expectPoints[i].getY()));
            result = pq.delMax();
            assertThat(result.getX(), equalTo(expectPoints[i].getX()));
            assertThat(result.getY(), equalTo(expectPoints[i].getY()));
        }
        assertThat(pq.isEmpty(), equalTo(true));
        assertThat(pq.size(), equalTo(0));
    }

    @Test
    public void testMaxPriorityQueue_Comparator_Iterator() throws Exception {
        Point[] points = new Point[10];
        for (int i = 0; i < 10; i++) {
            points[i] = new Point(i, 10 - i);
        }
        Shuffle.shuffle(points);

        MaxPriorityQueue<Point> pq = new MaxPriorityQueue<Point>(Point.X_ORDER);
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
        HeapSort.sort(expectPoints, Point.X_ORDER, SortOrder.DESC);

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
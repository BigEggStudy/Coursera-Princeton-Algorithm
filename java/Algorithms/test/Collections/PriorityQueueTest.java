package Collections;

import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class PriorityQueueTest {
    @Test
    public void testConstructor() throws Exception {
        MockPriorityQueue pq = new MockPriorityQueue();
        assertThat(pq.isEmpty(), equalTo(true));
        assertThat(pq.size(), equalTo(0));
    }

    @Test
    public void testConstructor_WithKeys() throws Exception {
        Integer[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        MockPriorityQueue<Integer> pq = new MockPriorityQueue<Integer>(a);
        assertThat(pq.isEmpty(), equalTo(false));
        assertThat(pq.size(), equalTo(10));
    }

    @Test(expected = NullPointerException.class)
    public void testInsert_NullItem() throws Exception {
        MockPriorityQueue pq = new MockPriorityQueue();
        pq.insert(null);
    }

    @Test
    public void testInsert() throws Exception {
        MockPriorityQueue<Integer> pq = new MockPriorityQueue<Integer>();
        pq.insert(1);
        assertThat(pq.isEmpty(), equalTo(false));
        assertThat(pq.size(), equalTo(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void testFirst_NullItem() throws Exception {
        MockPriorityQueue pq = new MockPriorityQueue();
        pq.first();
    }

    @Test
    public void testFirst() throws Exception {
        MockPriorityQueue<Integer> pq = new MockPriorityQueue<Integer>();
        pq.insert(1);
        assertThat(pq.isEmpty(), equalTo(false));
        assertThat(pq.size(), equalTo(1));
        Integer result = pq.first();
        assertThat(result, equalTo(1));
        assertThat(pq.isEmpty(), equalTo(false));
        assertThat(pq.size(), equalTo(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void testDelete_NullItem() throws Exception {
        MockPriorityQueue pq = new MockPriorityQueue();
        pq.delete();
    }

    @Test
    public void testDelete() throws Exception {
        MockPriorityQueue<Integer> pq = new MockPriorityQueue<Integer>();
        pq.insert(1);
        assertThat(pq.isEmpty(), equalTo(false));
        assertThat(pq.size(), equalTo(1));
        Integer result = pq.delete();
        assertThat(result, equalTo(1));
        assertThat(pq.isEmpty(), equalTo(true));
        assertThat(pq.size(), equalTo(0));
    }

    private class MockPriorityQueue<Key> extends PriorityQueue<Key> {
        public MockPriorityQueue() {
            super();
        }

        private MockPriorityQueue(int initCapacity) {
            super(initCapacity);
        }

        private MockPriorityQueue(Comparator<Key> comparator) {
            super(comparator);
        }

        private MockPriorityQueue(int initCapacity, Comparator<Key> comparator) {
            super(initCapacity, comparator);
        }

        private MockPriorityQueue(Key[] keys) {
            super(keys);
        }

        @Override
        protected void sink(int k) {

        }

        @Override
        protected void swim(int k) {

        }

        @Override
        public Iterator<Key> iterator() {
            throw new NotImplementedException();
        }
    }
}
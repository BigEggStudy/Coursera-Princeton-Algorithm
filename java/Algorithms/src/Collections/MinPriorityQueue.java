package Collections;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by jianming.xiao on 11/9/14.
 */
public class MinPriorityQueue<Key> extends PriorityQueue<Key> {
    /**
     * Initializes an empty priority queue.
     */
    public MinPriorityQueue() {
        super();
    }

    /**
     * Initializes an empty priority queue with the given initial capacity.
     *
     * @param initCapacity the initial capacity of the priority queue
     */
    public MinPriorityQueue(int initCapacity) {
        super(initCapacity);
    }

    /**
     * Initializes an empty priority queue using the given comparator.
     *
     * @param comparator the order to use when comparing keys
     */
    public MinPriorityQueue(Comparator<Key> comparator) {
        super(comparator);
    }

    /**
     * Initializes an empty priority queue with the given initial capacity,
     * using the given comparator.
     *
     * @param initCapacity the initial capacity of the priority queue
     * @param comparator   the order to use when comparing keys
     */
    public MinPriorityQueue(int initCapacity, Comparator<Key> comparator) {
        super(initCapacity, comparator);
    }

    /**
     * Initializes a priority queue from the array of keys.
     * Takes time proportional to the number of keys, using sink-based heap construction.
     *
     * @param keys the array of keys
     */
    public MinPriorityQueue(Key[] keys) {
        super(keys);
    }


    /**
     * Returns the smallest key on the priority queue.
     *
     * @return the smallest key on the priority queue
     * @throws java.util.NoSuchElementException if priority queue is empty
     */
    public Key min() {
        return first();
    }

    /**
     * Removes and returns the smallest key on the priority queue.
     *
     * @return the smallest key on the priority queue
     * @throws java.util.NoSuchElementException if the priority queue is empty
     */
    public Key delMin() {
        return delete();
    }

    /**
     * Returns an iterator that iterates over the keys on the priority queue
     * in ascending order.
     * The iterator doesn't implement <tt>remove()</tt> since it's optional.
     *
     * @return an iterator that iterates over the keys in ascending order
     */
    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }


    @Override
    protected void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    @Override
    protected void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private boolean greater(int i, int j) {
        if (comparator == null)
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
        else
            return comparator.compare(pq[i], pq[j]) > 0;
    }


    private class HeapIterator implements Iterator<Key> {
        private MinPriorityQueue<Key> copy;

        private HeapIterator() {
            if (comparator == null)
                copy = new MinPriorityQueue<Key>(size());
            else
                copy = new MinPriorityQueue<Key>(size(), comparator);
            for (int i = 1; i <= N; i++)
                copy.insert(pq[i]);
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

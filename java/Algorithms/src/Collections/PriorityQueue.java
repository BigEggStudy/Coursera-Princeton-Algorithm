package Collections;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Created by jianming.xiao on 11/8/14.
 */
public abstract class PriorityQueue<Key> implements IPriorityQueue<Key> {
    protected Key[] pq;
    protected int N;
    protected Comparator<Key> comparator;


    /**
     * Initializes an empty priority queue.
     */
    public PriorityQueue() {
        this(1);
    }

    /**
     * Initializes an empty priority queue with the given initial capacity.
     *
     * @param initCapacity the initial capacity of the priority queue
     */
    public PriorityQueue(int initCapacity) {
        this(initCapacity, null);
    }

    /**
     * Initializes an empty priority queue using the given comparator.
     *
     * @param comparator the order in which to compare the keys
     */
    public PriorityQueue(Comparator<Key> comparator) {
        this(1, comparator);
    }

    /**
     * Initializes an empty priority queue with the given initial capacity,
     * using the given comparator.
     *
     * @param initCapacity the initial capacity of the priority queue
     * @param comparator   the order in which to compare the keys
     */
    public PriorityQueue(int initCapacity, Comparator<Key> comparator) {
        this.comparator = comparator;
        this.pq = (Key[]) new Object[initCapacity + 1];
        this.N = 0;
    }

    /**
     * Initializes a priority queue from the array of keys.
     * Takes time proportional to the number of keys, using sink-based heap construction.
     *
     * @param keys the array of keys
     */
    public PriorityQueue(Key[] keys) {
        this.N = keys.length;
        this.pq = (Key[]) new Object[N + 1];
        for (int i = 0; i < N; i++)
            pq[i + 1] = keys[i];
        for (int k = N / 2; k >= 1; k--)
            sink(k);
    }


    /**
     * Is the priority queue empty?
     *
     * @return true if the priority queue is empty; false otherwise
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Returns the number of keys on the priority queue.
     *
     * @return the number of keys on the priority queue
     */
    public int size() {
        return N;
    }

    /**
     * Returns the first key on the priority queue.
     *
     * @return the first key on the priority queue
     * @throws java.util.NoSuchElementException if the priority queue is empty
     */
    public Key first() {
        if (isEmpty()) throw new NoSuchElementException();
        return pq[1];
    }

    /**
     * Adds a new key to the priority queue.
     *
     * @param x the new key to add to the priority queue
     * @throws java.lang.NullPointerException if the <tt>x</tt> is null
     */
    public void insert(Key x) {
        if (x == null) throw new NullPointerException();

        if (N >= pq.length - 1) resize(pq.length * 2);
        pq[++N] = x;
        swim(N);
    }

    /**
     * Removes and returns a first key on the priority queue.
     *
     * @return the first key on the priority queue
     * @throws java.util.NoSuchElementException if priority queue is empty.
     */
    public Key delete() {
        if (isEmpty()) throw new NoSuchElementException();
        Key result = pq[1];
        exch(1, N--);
        sink(1);
        pq[N + 1] = null;
        if (N > 0 && N == (pq.length - 1) / 4) resize(pq.length / 2);
        return result;
    }


    protected abstract void sink(int k);

    protected abstract void swim(int k);

    protected void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }


    private void resize(int capacity) {
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= N; i++)
            temp[i] = pq[i];
        pq = temp;
    }
}

package Collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by jianming.xiao on 10/8/14.
 */
public class ResizingArrayQueue<Item> implements IQueue<Item> {
    private Item[] q;
    private int N = 0;
    private int first = 0;
    private int last = 0;

    /**
     * Initializes an empty queue.
     */
    public ResizingArrayQueue() {
        this.q = (Item[]) new Object[2];
    }

    /**
     * Is this queue empty?
     *
     * @return true if this queue is empty; false otherwise
     */
    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    @Override
    public int size() {
        return N;
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last = N;
    }

    /**
     * Adds the item to this queue.
     *
     * @param item the item to add
     * @throws NullPointerException if the <tt>item</tt> is null;
     */
    @Override
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (N == q.length) resize(q.length * 2);
        q[last++] = item;
        if (last == q.length) last = 0;
        N++;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    @Override
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        Item result = q[first];
        q[first] = null;
        N--;
        first++;
        if (first == q.length) first = 0;
        if (N == q.length / 4) resize(q.length / 2);
        return result;
    }

    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    @Override
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return q[first];
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Item item : this) {
            stringBuilder.append(item.toString()).append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    @Override
    public Iterator<Item> iterator() {
        return new ResizingArrayQueueIterator();
    }

    private class ResizingArrayQueueIterator implements Iterator<Item> {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < N;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return q[(first + i) % q.length];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

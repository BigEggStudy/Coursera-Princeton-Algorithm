package Collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by jianming.xiao on 10/7/14.
 */
public class LinkedQueue<Item> implements IQueue<Item> {
    private int N;
    private Node first;
    private Node last;

    private class Node {
        private Item item;
        private Node next;
    }

    public LinkedQueue() {
        N = 0;
        first = null;
        last = null;
    }

    /**
     * Is this queue empty?
     *
     * @return true if this queue is empty; false otherwise
     */
    @Override
    public boolean isEmpty() {
        return first == null;
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

    /**
     * Adds the item to this queue.
     *
     * @param item the item to add
     * @throws NullPointerException if the <tt>item</tt> is null;
     */
    @Override
    public void enqueue(Item item) throws NullPointerException {
        if (item == null) throw new NullPointerException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldLast.next = last;
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
        Item result = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        N--;
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
        return first.item;
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
        return new LinkedQueueIterator();
    }

    private class LinkedQueueIterator implements Iterator<Item>{
        private Node current;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item result = current.item;
            current = current.next;
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

package Collections;

import java.util.Iterator;

/**
 * Created by jianming.xiao on 10/7/14.
 */
public interface IQueue<Item> extends Iterable<Item> {
    /**
     * Is this queue empty?
     *
     * @return true if this queue is empty; false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    int size();

    /**
     * Adds the item to this queue.
     *
     * @param item the item to add
     * @throws java.lang.NullPointerException if the <tt>item</tt> is null;
     */
    void enqueue(Item item);

    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    Item dequeue();

    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    Item peek();

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    String toString();

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    Iterator<Item> iterator();
}

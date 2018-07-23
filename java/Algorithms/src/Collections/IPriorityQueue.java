package Collections;

/**
 * Created by jianming.xiao on 11/8/14.
 */
public interface IPriorityQueue<Key> extends Iterable<Key> {
    /**
     * Is the priority queue empty?
     *
     * @return true if the priority queue is empty; false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of keys on the priority queue.
     *
     * @return the number of keys on the priority queue
     */
    int size();

    /**
     * Returns the first key on the priority queue.
     *
     * @return the first key on the priority queue
     * @throws java.util.NoSuchElementException if the priority queue is empty
     */
    Key first();

    /**
     * Adds a new key to the priority queue.
     *
     * @param x the new key to add to the priority queue
     */
    void insert(Key x);

    /**
     * Removes and returns a first key on the priority queue.
     *
     * @return the first key on the priority queue
     * @throws java.util.NoSuchElementException if priority queue is empty.
     */
    Key delete();
}

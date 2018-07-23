package Collections;

import java.util.Iterator;

/**
 * Created by jianming.xiao on 10/7/14.
 */
public interface IStack<Item> extends Iterable<Item> {
    /**
     * Is this stack empty?
     *
     * @return true if this stack is empty; false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of items in the stack.
     *
     * @return the number of items in the stack
     */
    int size();

    /**
     * Adds the item to this stack.
     *
     * @param item the item to add
     * @throws java.lang.NullPointerException if the <tt>item</tt> is null;
     */
    void push(Item item);

    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    Item pop();

    /**
     * Returns (but does not remove) the item most recently added to this stack.
     *
     * @return the item most recently added to this stack
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    Item peek();

    /**
     * Returns a string representation of this stack.
     *
     * @return the sequence of items in the stack in LIFO order, separated by spaces
     */
    String toString();

    /**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     *
     * @return an iterator to this stack that iterates through the items in LIFO order.
     */
    Iterator<Item> iterator();
}

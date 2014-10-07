package Collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by jianming.xiao on 10/7/14.
 */
public class ResizingArrayStack<Item> implements IStack<Item> {
    private Item[] a;
    private int N;

    /**
     * Initializes an empty stack.
     */
    public ResizingArrayStack() {
        a = (Item[]) new Object[2];
        N = 0;
    }

    /**
     * Is this stack empty?
     *
     * @return true if this stack is empty; false otherwise
     */
    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Returns the number of items in the stack.
     *
     * @return the number of items in the stack
     */
    @Override
    public int size() {
        return N;
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    /**
     * Adds the item to this stack.
     *
     * @param item the item to add
     * @throws NullPointerException if the <tt>item</tt> is null;
     */
    @Override
    public void push(Item item) throws NullPointerException {
        if (item == null) throw new NullPointerException();
        if (N == a.length) resize(2 * a.length);
        a[N++] = item;
    }

    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    @Override
    public Item pop() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item result = a[N - 1];
        a[N - 1] = null;
        N--;
        if (N == a.length / 4) resize(a.length / 2);
        return result;
    }

    /**
     * Returns (but does not remove) the item most recently added to this stack.
     *
     * @return the item most recently added to this stack
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    @Override
    public Item peek() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return a[N - 1];
    }

    /**
     * Returns a string representation of this stack.
     *
     * @return the sequence of items in the stack in LIFO order, separated by spaces
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
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     *
     * @return an iterator to this stack that iterates through the items in LIFO order.
     */
    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[--i];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

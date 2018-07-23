package Collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by jianming.xiao on 10/12/14.
 */
public class ResizingArrayBag<Item> implements IBag<Item> {
    private Item[] a;
    private int N;

    public ResizingArrayBag() {
        a = (Item[]) new Object[2];
        N = 0;
    }

    /**
     * Is this bag empty?
     *
     * @return true if this bag is empty; false otherwise
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Returns the number of items in this bag.
     *
     * @return the number of items in this bag
     */
    public int size() {
        return N;
    }

    /**
     * Resize the underlying array holding the elements
     *
     * @param capacity the new capacity of the bag
     */
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    /**
     * Adds the item to this bag.
     *
     * @param item the item to add to this bag
     * @throws NullPointerException if the <tt>item</tt> is null;
     */
    public void add(Item item) {
        if (item == null) throw new NullPointerException();
        if (N == a.length) resize(a.length * 2);
        a[N++] = item;
    }

    /**
     * Returns a string representation of this bag.
     *
     * @return the sequence of items, separated by spaces
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
     * Returns an iterator that iterates over the items in this bag.
     *
     * @return an iterator that iterates over the items in this bag
     */
    public Iterator<Item> iterator() {
        return new ResizingArrayBagIterator();
    }

    private class ResizingArrayBagIterator implements Iterator<Item> {
        private int currnet = 0;

        public boolean hasNext() {
            return currnet < N;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[currnet++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

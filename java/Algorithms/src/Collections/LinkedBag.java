package Collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by jianming.xiao on 10/11/14.
 */
public class LinkedBag<Item> implements IBag<Item> {
    private int N;
    private Node first;

    private class Node {
        private Item item;
        private Node next;
    }

    public LinkedBag() {
        N = 0;
        first = null;
    }

    /**
     * Is this bag empty?
     *
     * @return true if this bag is empty; false otherwise
     */
    public boolean isEmpty() {
        return first == null;
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
     * Adds the item to this bag.
     *
     * @param item the item to add to this bag
     * @throws NullPointerException if the <tt>item</tt> is null;
     */
    public void add(Item item) {
        if (item == null) throw new NullPointerException();
        Node oldFisrt = first;
        first = new Node();
        first.item = item;
        first.next = oldFisrt;
        N++;
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
        return new LinkedBagIterator();
    }

    private class LinkedBagIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item result = current.item;
            current = current.next;
            return result;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

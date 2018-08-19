import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int first, end, size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
        first = 0;
        end = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the queue
    public int size() {
        return size;
    }

    private void resize(int capacity) {
        if (capacity < size()) throw new IllegalArgumentException("The capacity cannot less than queue size.");

        if (end <= first) end = end + items.length;

        Item[] temp = (Item[]) new Object[capacity];
        int newID = 0;
        for (int i = first; i < end; i++) {
            temp[newID++] = items[fixedSize(i)];
        }
        items = temp;
        first = 0;
        end = newID;
    }

    private int fixedSize(int number) {
        return (number + items.length) % items.length;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size() == items.length) {
            resize(2 * items.length);
        }

        end = fixedSize(end);
        if (size() == 0) {
            items[end] = item;
        } else {
            int index = fixedSize(StdRandom.uniform(size() + 1) + first);
            if (index == end) {
                items[end] = item;
            } else {
                Item randomItem = items[index];
                items[index] = item;
                items[end] = randomItem;
            }
        }

        end++;
        size++;
    }

    // delete and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        first = fixedSize(first);
        Item result = items[first];
        items[first] = null;

        first++;
        size--;

        if (size > 0 && size == items.length / 4) resize(items.length / 2);
        return result;
    }

    // return (but do not delete) a random item
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException ();

        int randomIndex = StdRandom.uniform(size);
        int index = fixedSize(randomIndex + first);
        return items[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {
        private Item[] randomItems;
        private int currentIndex = 0;

        private RandomizedIterator() {
            randomItems = (Item[]) new Object[size()];

            if (!isEmpty()) {
                int tempEnd = end;
                if (tempEnd <= first) tempEnd = tempEnd + items.length;

                int newID = 0;
                for (int i = first; i < tempEnd; i++) {
                    randomItems[newID++] = items[fixedSize(i)];
                }

                StdRandom.shuffle(randomItems);
            }
        }

        @Override
        public boolean hasNext() {
            return currentIndex != randomItems.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            return randomItems[currentIndex++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int value : queue) {
            StdOut.print(value);
        }

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);


        int result = queue.sample();
        result = queue.sample();
        result = queue.sample();
        result = queue.sample();
        result = queue.sample();
        result = queue.sample();

        queue.dequeue();
        queue.dequeue();
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
        queue.enqueue(11);

        result = queue.sample();
        result = queue.sample();
        result = queue.sample();

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        queue.enqueue(12);
        queue.enqueue(13);
        queue.enqueue(14);
        queue.enqueue(15);
        queue.dequeue();
        queue.dequeue();

        for (int value : queue) {
            StdOut.print(value);
        }
    }
}

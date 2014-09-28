import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by jianming.xiao on 9/26/14.
 */
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
        if (item == null) throw new NullPointerException("item cannot be null.");
        if (size() == items.length) {
            resize(2 * items.length);
        }

        end = fixedSize(end);
        if (size() == 0) {
            items[end] = item;
        } else {
            int index = fixedSize(StdRandom.uniform(size()) + first);
            Item randomItem = items[index];
            items[index] = item;
            items[end] = randomItem;
        }

        end++;
        size++;
    }

    // delete and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("The queue is empty.");

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
        if (isEmpty()) throw new NoSuchElementException("The queue is empty.");

        return items[fixedSize(StdRandom.uniform(size()) + first)];
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
            int tempEnd = end;

            if (tempEnd <= first) tempEnd = tempEnd + items.length;

            int newID = 0;
            for (int i = first; i < tempEnd; i++) {
                randomItems[newID++] = items[fixedSize(i)];
            }

            StdRandom.shuffle(randomItems);
        }

        @Override
        public boolean hasNext() {
            return currentIndex == randomItems.length;
        }

        @Override
        public Item next() {
            if (hasNext()) throw new NoSuchElementException();

            return randomItems[currentIndex++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not support the remove operation in iterator");
        }
    }

    // unit testing
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by jianming.xiao on 9/23/14.
 */
public final class Deque<Item> implements Iterable<Item> {
    private int nodeCount;
    private Node firstNode;
    private Node lastNode;

    private class Node {
        private Item item;
        private Node next;
        private Node before;
    }


    // construct an empty deque
    public Deque() {
        firstNode = lastNode = null;
        nodeCount = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return firstNode == null;
    }

    // return the number of items on the deque
    public int size() {
        return nodeCount;
    }

    // insert the item at the front
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException("item cannot be null.");

        Node oldFirst = firstNode;
        firstNode = new Node();
        firstNode.item = item;
        firstNode.next = oldFirst;
        if (oldFirst != null) {
            oldFirst.before = firstNode;
        } else {
            lastNode = firstNode;
        }

        nodeCount++;
    }

    // insert the item at the end
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException("item cannot be null.");

        Node oldLast = lastNode;
        lastNode = new Node();
        lastNode.item = item;
        lastNode.before = oldLast;
        if (oldLast != null) {
            oldLast.next = lastNode;
        } else {
            firstNode = lastNode;
        }

        nodeCount++;
    }

    // delete and return the item at the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("The deque is emtpy.");

        Node oldFirst = firstNode;
        Item result = oldFirst.item;
        firstNode = oldFirst.next;
        if (firstNode != null) {
            firstNode.before = null;
        } else {
            lastNode = null;
        }

        nodeCount--;
        return result;
    }

    // delete and return the item at the end
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("The deque is empty.");

        Node oldLast = lastNode;
        Item result = oldLast.item;
        lastNode = lastNode.before;
        if (lastNode != null) {
            lastNode.next = null;
        } else {
            firstNode = null;
        }

        nodeCount--;
        return result;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = firstNode;

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
            throw new UnsupportedOperationException("Not support the remove operation in iterator.");
        }
    }

    // unit testing
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();

        assert deque.isEmpty();
        assert deque.size() == 0;
        assert deque.lastNode == null;
        assert deque.firstNode == null;

        deque.addFirst(1);
        assert !deque.isEmpty();
        assert deque.size() == 1;
        assert deque.lastNode != null;
        assert deque.lastNode.item == 1;
        assert deque.lastNode.before == null;
        assert deque.lastNode.next == null;
        assert deque.firstNode.item == 1;
        assert deque.firstNode != null;
        assert deque.firstNode.before == null;
        assert deque.firstNode.next == null;

        Integer result = deque.removeFirst();
        assert result == 1;
        assert deque.isEmpty();
        assert deque.size() == 0;
        assert deque.lastNode == null;
        assert deque.firstNode == null;


        deque.addLast(2);
        assert !deque.isEmpty();
        assert deque.size() == 1;
        assert deque.lastNode != null;
        assert deque.lastNode.item == 2;
        assert deque.lastNode.before == null;
        assert deque.lastNode.next == null;
        assert deque.firstNode.item == 2;
        assert deque.firstNode != null;
        assert deque.firstNode.before == null;
        assert deque.firstNode.next == null;

        result = deque.removeLast();
        assert result == 2;
        assert deque.isEmpty();
        assert deque.size() == 0;
        assert deque.lastNode == null;
        assert deque.firstNode == null;


        deque.addFirst(1);
        assert !deque.isEmpty();
        assert deque.size() == 1;
        assert deque.lastNode != null;
        assert deque.lastNode.item == 1;
        assert deque.lastNode.before == null;
        assert deque.lastNode.next == null;
        assert deque.firstNode.item == 1;
        assert deque.firstNode != null;
        assert deque.firstNode.before == null;
        assert deque.firstNode.next == null;

        result = deque.removeLast();
        assert result == 1;
        assert deque.isEmpty();
        assert deque.size() == 0;
        assert deque.lastNode == null;
        assert deque.firstNode == null;


        deque.addLast(2);
        assert !deque.isEmpty();
        assert deque.size() == 1;
        assert deque.lastNode != null;
        assert deque.lastNode.item == 2;
        assert deque.lastNode.before == null;
        assert deque.lastNode.next == null;
        assert deque.firstNode.item == 2;
        assert deque.firstNode != null;
        assert deque.firstNode.before == null;
        assert deque.firstNode.next == null;

        result = deque.removeFirst();
        assert result == 2;
        assert deque.isEmpty();
        assert deque.size() == 0;
        assert deque.lastNode == null;
        assert deque.firstNode == null;

        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        assert !deque.isEmpty();
        assert deque.size() == 3;
        assert deque.lastNode != null;
        assert deque.lastNode.item == 2;
        assert deque.lastNode.before != null;
        assert deque.lastNode.before.item == 1;
        assert deque.lastNode.next == null;
        assert deque.firstNode.item == 3;
        assert deque.firstNode != null;
        assert deque.firstNode.before == null;
        assert deque.firstNode.next != null;
        assert deque.firstNode.next.item == 1;

        deque.removeFirst();
        deque.removeLast();
        deque.removeFirst();
    }
}
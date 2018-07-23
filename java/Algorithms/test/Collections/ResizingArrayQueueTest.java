package Collections;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.*;

public class ResizingArrayQueueTest {
    @Test
    public void testConstructor() throws Exception {
        IQueue<Integer> queue = new ResizingArrayQueue<Integer>();
        assertThat(queue.isEmpty(), is(true));
        assertThat(queue.size(), is(0));
        assertThat(queue.toString(), isEmptyString());
    }

    @Test
    public void testEnqueueAndDequeue() {
        ResizingArrayQueue<Integer> queue = new ResizingArrayQueue<Integer>();
        queue.enqueue(1);
        assertThat(queue.isEmpty(), is(false));
        assertThat(queue.size(), is(1));
        assertThat(queue.peek(), is(1));
        int value = queue.dequeue();
        assertThat(queue.isEmpty(), is(true));
        assertThat(queue.size(), is(0));
        assertThat(value, is(1));
    }

    @Test(expected = NullPointerException.class)
    public void testEnqueue_NullItem() {
        IQueue<Integer> queue = new ResizingArrayQueue<Integer>();
        queue.enqueue(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeue_EmptyStack() {
        IQueue<Integer> queue = new ResizingArrayQueue<Integer>();
        queue.dequeue();
    }

    @Test
    public void testManyEnqueue() {
        IQueue<Integer> queue = new ResizingArrayQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            assertThat(queue.isEmpty(), is(false));
            assertThat(queue.size(), is(i + 1));
            assertThat(queue.peek(), is(0));
        }
    }

    @Test
    public void testManyDequeue() {
        IQueue<Integer> queue = new ResizingArrayQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < 10; i++) {
            int value = queue.dequeue();
            assertThat(queue.size(), is(10 - i - 1));
            assertThat(value, is(i));
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void testPeek_EmptyStack() {
        IQueue<Integer> queue = new ResizingArrayQueue<Integer>();
        queue.peek();
    }

    @Test
    public void testToString() {
        IQueue<Integer> queue = new ResizingArrayQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        assertThat(queue.toString(), is("0 1 2 3 4 5 6 7 8 9 "));
    }
}
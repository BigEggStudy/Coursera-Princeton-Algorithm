package Collections;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertThat;

public class LinkedStackTest {
    @Test
    public void testConstructor() throws Exception {
        IStack<Integer> stack = new LinkedStack<Integer>();
        assertThat(stack.isEmpty(), is(true));
        assertThat(stack.size(), is(0));
        assertThat(stack.toString(), isEmptyString());
    }

    @Test
    public void testPushAndPop() {
        IStack<Integer> stack = new LinkedStack<Integer>();
        stack.push(1);
        assertThat(stack.isEmpty(), is(false));
        assertThat(stack.size(), is(1));
        assertThat(stack.peek(), is(1));
        int value = stack.pop();
        assertThat(stack.isEmpty(), is(true));
        assertThat(stack.size(), is(0));
        assertThat(value, is(1));
    }

    @Test(expected = NullPointerException.class)
    public void testPush_NullItem() {
        IStack<Integer> stack = new LinkedStack<Integer>();
        stack.push(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testPop_EmptyStack() {
        IStack<Integer> stack = new LinkedStack<Integer>();
        stack.pop();
    }

    @Test
    public void testManyPush() {
        IStack<Integer> stack = new LinkedStack<Integer>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
            assertThat(stack.isEmpty(), is(false));
            assertThat(stack.size(), is(i + 1));
            assertThat(stack.peek(), is(i));
        }
    }

    @Test
    public void testManyPop() {
        IStack<Integer> stack = new LinkedStack<Integer>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        for (int i = 0; i < 10; i++) {
            int value = stack.pop();
            assertThat(stack.size(), is(10 - i - 1));
            assertThat(value, is(10 - i - 1));
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void testPeek_EmptyStack() {
        IStack<Integer> stack = new LinkedStack<Integer>();
        stack.peek();
    }

    @Test
    public void testToString() {
        IStack<Integer> stack = new LinkedStack<Integer>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        assertThat(stack.toString(), is("9 8 7 6 5 4 3 2 1 0 "));
    }
}
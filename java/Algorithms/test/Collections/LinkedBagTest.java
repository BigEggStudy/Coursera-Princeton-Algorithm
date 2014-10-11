package Collections;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.*;

public class LinkedBagTest {
    @Test
    public void testConstructor() throws Exception {
        IBag<Integer> bag = new LinkedBag<Integer>();
        assertThat(bag.isEmpty(), is(true));
        assertThat(bag.size(), is(0));
        assertThat(bag.toString(), isEmptyString());
    }

    @Test
    public void testAdd() {
        IBag<Integer> bag = new LinkedBag<Integer>();
        bag.add(1);
        assertThat(bag.isEmpty(), is(false));
        assertThat(bag.size(), is(1));
    }

    @Test(expected = NullPointerException.class)
    public void testAdd_NullItem() {
        IBag<Integer> bag = new LinkedBag<Integer>();
        bag.add(null);
    }

    @Test
    public void testManyAdd() {
        IBag<Integer> bag = new LinkedBag<Integer>();
        for (int i = 0; i < 10; i++) {
            bag.add(i);
            assertThat(bag.isEmpty(), is(false));
            assertThat(bag.size(), is(i + 1));
        }
    }


    @Test
    public void testToString() {
        IBag<Integer> bag = new LinkedBag<Integer>();
        for (int i = 0; i < 10; i++) {
            bag.add(i);
        }
        assertThat(bag.toString(), is("9 8 7 6 5 4 3 2 1 0 "));
    }
}
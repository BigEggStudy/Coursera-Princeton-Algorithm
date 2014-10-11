package Collections;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertThat;

public class ResizingArrayBagTest {
    @Test
    public void testConstructor() throws Exception {
        IBag<Integer> bag = new ResizingArrayBag<Integer>();
        assertThat(bag.isEmpty(), is(true));
        assertThat(bag.size(), is(0));
        assertThat(bag.toString(), isEmptyString());
    }

    @Test
    public void testAdd() {
        ResizingArrayBag<Integer> bag = new ResizingArrayBag<Integer>();
        bag.add(1);
        assertThat(bag.isEmpty(), is(false));
        assertThat(bag.size(), is(1));
    }

    @Test(expected = NullPointerException.class)
    public void testAdd_NullItem() {
        IBag<Integer> bag = new ResizingArrayBag<Integer>();
        bag.add(null);
    }

    @Test
    public void testManyAdd() {
        IBag<Integer> bag = new ResizingArrayBag<Integer>();
        for (int i = 0; i < 10; i++) {
            bag.add(i);
            assertThat(bag.isEmpty(), is(false));
            assertThat(bag.size(), is(i + 1));
        }
    }

    @Test
    public void testToString() {
        IBag<Integer> bag = new ResizingArrayBag<Integer>();
        for (int i = 0; i < 10; i++) {
            bag.add(i);
        }
        assertThat(bag.toString(), is("0 1 2 3 4 5 6 7 8 9 "));
    }
}
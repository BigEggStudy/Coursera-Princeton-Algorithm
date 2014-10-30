package Sorts;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ShuffleTest {
    @Test
    public void testShuffle() throws Exception {
        Integer[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertThat(SortHelper.isSorted(a), equalTo(true));
        Shuffle.shuffle(a);
        assertThat(SortHelper.isSorted(a), equalTo(false));
    }
}
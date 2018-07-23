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

    @Test
    public void testShuffle_Different() throws Exception {
        Integer[] a1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer[] a2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        Shuffle.shuffle(a1);
        Shuffle.shuffle(a2);
        assertThat(SortHelper.isSorted(a1), equalTo(false));
        assertThat(SortHelper.isSorted(a2), equalTo(false));

        boolean same = true;
        for (int i = 0; i < 10; i++)
            if (a1[i] != a2[i]) {
                same = false;
                break;
            }

        assertThat(same, equalTo(false));
    }

    @Test
    public void testShuffle_Different_Multi() throws Exception {
        for (int i = 0; i < 10; i++) {
            testShuffle_Different();
        }
    }
}
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BinarySearchTest {
    @Test
    public void testRank_KeySmaller() throws Exception {
        int[] data = new int[5];
        for (int i = 0; i < 5; i++) {
            data[i] = i;
        }

        int rank = BinarySearch.rank(data, -1);
        assertThat(rank, is(-1));
    }

    @Test
    public void testRank_KeyLarger() throws Exception {
        int[] data = new int[5];
        for (int i = 0; i < 5; i++) {
            data[i] = i;
        }

        int rank = BinarySearch.rank(data, 5);
        assertThat(rank, is(-1));
    }

    @Test
    public void testRank_KeyExist() throws Exception {
        int[] data = {1, 5, 10, 12, 37};

        int rank = BinarySearch.rank(data, 1);
        assertThat(rank, is(0));
        rank = BinarySearch.rank(data, 5);
        assertThat(rank, is(1));
        rank = BinarySearch.rank(data, 10);
        assertThat(rank, is(2));
        rank = BinarySearch.rank(data, 12);
        assertThat(rank, is(3));
        rank = BinarySearch.rank(data, 37);
        assertThat(rank, is(4));
    }

    @Test
    public void testRank_KeyNotExist() throws Exception {
        int[] data = {1, 5, 10, 12, 37};

        int rank = BinarySearch.rank(data, 3);
        assertThat(rank, is(-1));
        rank = BinarySearch.rank(data, 20);
        assertThat(rank, is(-1));
    }
}
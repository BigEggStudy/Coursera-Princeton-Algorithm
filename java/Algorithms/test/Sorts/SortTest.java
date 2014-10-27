package Sorts;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class SortTest {
    @Test(expected = IllegalArgumentException.class)
    public void testSort_NullArray() throws Exception {
        MockSort sort = new MockSort();
        sort.sort(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSortASC_NullArray() throws Exception {
        MockSort sort = new MockSort();
        sort.sort(null, SortOrder.ASC);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSortDESC_NullArray() throws Exception {
        MockSort sort = new MockSort();
        sort.sort(null, SortOrder.DESC);
    }

    @Test
    public void testSort() throws Exception {
        MockSort sort = new MockSort();
        Comparable[] a = new Comparable[5];
        sort.sort(a);

        assertThat(sort.isASESortCalled, equalTo(true));
    }

    @Test
    public void testSortASC() throws Exception {
        MockSort sort = new MockSort();
        Comparable[] a = new Comparable[5];
        sort.sort(a, SortOrder.ASC);

        assertThat(sort.isASESortCalled, equalTo(true));
    }

    @Test
    public void testSortDESC() throws Exception {
        MockSort sort = new MockSort();
        Comparable[] a = new Comparable[5];
        sort.sort(a, SortOrder.DESC);

        assertThat(sort.isDESCSortCalled, equalTo(true));
    }


    private class MockSort extends Sort {
        public boolean isASESortCalled = false;
        public boolean isDESCSortCalled = false;

        public void reset() {
            isASESortCalled = false;
            isDESCSortCalled = false;
        }

        @Override
        protected void sortASE(Comparable[] a) {
            isASESortCalled = true;
        }

        @Override
        protected void sortDESC(Comparable[] a) {
            isDESCSortCalled = true;
        }
    }
}
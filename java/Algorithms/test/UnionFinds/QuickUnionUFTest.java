package UnionFinds;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class QuickUnionUFTest {
    @Test(expected = IndexOutOfBoundsException.class)
    public void testUnion_ParameterValidation_PLessThan0() throws Exception {
        QuickUnionUF quickUnionUF = new QuickUnionUF(10);
        quickUnionUF.union(-1, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testUnion_ParameterValidation_PEqualN() throws Exception {
        QuickUnionUF quickUnionUF = new QuickUnionUF(10);
        quickUnionUF.union(10, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testUnion_ParameterValidation_PLargerThanN() throws Exception {
        QuickUnionUF quickUnionUF = new QuickUnionUF(10);
        quickUnionUF.union(11, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testUnion_ParameterValidation_QLessThan0() throws Exception {
        QuickUnionUF quickUnionUF = new QuickUnionUF(10);
        quickUnionUF.union(4, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testUnion_ParameterValidation_QEqualN() throws Exception {
        QuickUnionUF quickUnionUF = new QuickUnionUF(10);
        quickUnionUF.union(4, 10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testUnion_ParameterValidation_QLargerThanN() throws Exception {
        QuickUnionUF quickUnionUF = new QuickUnionUF(10);
        quickUnionUF.union(4, 11);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testConnected_ParameterValidation_PLessThan0() throws Exception {
        QuickUnionUF quickUnionUF = new QuickUnionUF(10);
        quickUnionUF.connected(-1, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testConnected_ParameterValidation_PEqualN() throws Exception {
        QuickUnionUF quickUnionUF = new QuickUnionUF(10);
        quickUnionUF.connected(10, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testConnected_ParameterValidation_PLargerThanN() throws Exception {
        QuickUnionUF quickUnionUF = new QuickUnionUF(10);
        quickUnionUF.connected(11, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testConnected_ParameterValidation_QLessThan0() throws Exception {
        QuickUnionUF quickUnionUF = new QuickUnionUF(10);
        quickUnionUF.connected(4, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testConnected_ParameterValidation_QEqualN() throws Exception {
        QuickUnionUF quickUnionUF = new QuickUnionUF(10);
        quickUnionUF.connected(4, 10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testConnected_ParameterValidation_QLargerThanN() throws Exception {
        QuickUnionUF quickUnionUF = new QuickUnionUF(10);
        quickUnionUF.connected(4, 11);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFind_ParameterValidation_PLessThan0() throws Exception {
        QuickUnionUF quickUnionUF = new QuickUnionUF(10);
        quickUnionUF.find(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFind_ParameterValidation_PEqualN() throws Exception {
        QuickUnionUF quickUnionUF = new QuickUnionUF(10);
        quickUnionUF.find(10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFind_ParameterValidation_PLargerThanN() throws Exception {
        QuickUnionUF quickUnionUF = new QuickUnionUF(10);
        quickUnionUF.find(11);
    }


    @Test
    public void testUnionAndConnected_NewConnection() throws Exception {
        QuickUnionUF quickUnionUF = new QuickUnionUF(10);
        assertThat(quickUnionUF.connected(0, 1), is(false));
        assertThat(quickUnionUF.count(), is(10));
        quickUnionUF.union(0, 1);
        assertThat(quickUnionUF.connected(0, 1), is(true));
        assertThat(quickUnionUF.count(), is(9));
        assertThat(quickUnionUF.find(1), is(1));
        assertThat(quickUnionUF.find(0), is(1));
    }

    @Test
    public void testUnionAndConnected_AlreadyConnected() throws Exception {
        QuickUnionUF quickUnionUF = new QuickUnionUF(10);
        quickUnionUF.union(0, 1);
        assertThat(quickUnionUF.connected(0, 1), is(true));
        assertThat(quickUnionUF.count(), is(9));
        quickUnionUF.union(0, 1);
        assertThat(quickUnionUF.connected(0, 1), is(true));
        assertThat(quickUnionUF.count(), is(9));
        assertThat(quickUnionUF.find(1), is(1));
        assertThat(quickUnionUF.find(0), is(1));
    }

    @Test
    public void testUnionAndConnected_MultiConnection() throws Exception {
        QuickUnionUF quickUnionUF = new QuickUnionUF(10);
        quickUnionUF.union(0, 1);
        quickUnionUF.union(2, 1);
        assertThat(quickUnionUF.connected(0, 1), is(true));
        assertThat(quickUnionUF.connected(2, 1), is(true));
        assertThat(quickUnionUF.connected(2, 0), is(true));
        assertThat(quickUnionUF.count(), is(8));
        assertThat(quickUnionUF.find(1), is(1));
        assertThat(quickUnionUF.find(0), is(1));
        assertThat(quickUnionUF.find(2), is(1));

        quickUnionUF.union(2, 5);
        assertThat(quickUnionUF.connected(0, 1), is(true));
        assertThat(quickUnionUF.connected(2, 1), is(true));
        assertThat(quickUnionUF.connected(2, 0), is(true));
        assertThat(quickUnionUF.connected(5, 0), is(true));
        assertThat(quickUnionUF.connected(5, 1), is(true));
        assertThat(quickUnionUF.connected(5, 2), is(true));
        assertThat(quickUnionUF.count(), is(7));
        assertThat(quickUnionUF.find(1), is(5));
        assertThat(quickUnionUF.find(0), is(5));
        assertThat(quickUnionUF.find(2), is(5));
        assertThat(quickUnionUF.find(5), is(5));
    }

    @Test
    public void testFind() throws Exception {
        QuickUnionUF quickUnionUF = new QuickUnionUF(10);
        for (int i = 0; i < 10; i++) {
            assertThat(quickUnionUF.find(i), is(i));
        }
    }
}
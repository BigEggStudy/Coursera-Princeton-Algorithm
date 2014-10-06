package UnionFinds;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class WeightedQuickUnionUFTest {
    @Test(expected = IndexOutOfBoundsException.class)
    public void testUnion_ParameterValidation_PLessThan0() throws Exception {
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
        weightedQuickUnionUF.union(-1, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testUnion_ParameterValidation_PEqualN() throws Exception {
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
        weightedQuickUnionUF.union(10, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testUnion_ParameterValidation_PLargerThanN() throws Exception {
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
        weightedQuickUnionUF.union(11, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testUnion_ParameterValidation_QLessThan0() throws Exception {
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
        weightedQuickUnionUF.union(4, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testUnion_ParameterValidation_QEqualN() throws Exception {
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
        weightedQuickUnionUF.union(4, 10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testUnion_ParameterValidation_QLargerThanN() throws Exception {
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
        weightedQuickUnionUF.union(4, 11);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testConnected_ParameterValidation_PLessThan0() throws Exception {
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
        weightedQuickUnionUF.connected(-1, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testConnected_ParameterValidation_PEqualN() throws Exception {
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
        weightedQuickUnionUF.connected(10, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testConnected_ParameterValidation_PLargerThanN() throws Exception {
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
        weightedQuickUnionUF.connected(11, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testConnected_ParameterValidation_QLessThan0() throws Exception {
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
        weightedQuickUnionUF.connected(4, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testConnected_ParameterValidation_QEqualN() throws Exception {
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
        weightedQuickUnionUF.connected(4, 10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testConnected_ParameterValidation_QLargerThanN() throws Exception {
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
        weightedQuickUnionUF.connected(4, 11);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFind_ParameterValidation_PLessThan0() throws Exception {
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
        weightedQuickUnionUF.find(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFind_ParameterValidation_PEqualN() throws Exception {
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
        weightedQuickUnionUF.find(10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFind_ParameterValidation_PLargerThanN() throws Exception {
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
        weightedQuickUnionUF.find(11);
    }


    @Test
    public void testUnionAndConnected_NewConnection() throws Exception {
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
        assertThat(weightedQuickUnionUF.connected(0, 1), is(false));
        assertThat(weightedQuickUnionUF.count(), is(10));
        weightedQuickUnionUF.union(0, 1);
        assertThat(weightedQuickUnionUF.connected(0, 1), is(true));
        assertThat(weightedQuickUnionUF.count(), is(9));
        assertThat(weightedQuickUnionUF.find(1), is(0));
        assertThat(weightedQuickUnionUF.find(0), is(0));
    }

    @Test
    public void testUnionAndConnected_AlreadyConnected() throws Exception {
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
        weightedQuickUnionUF.union(0, 1);
        assertThat(weightedQuickUnionUF.connected(0, 1), is(true));
        assertThat(weightedQuickUnionUF.count(), is(9));
        weightedQuickUnionUF.union(0, 1);
        assertThat(weightedQuickUnionUF.connected(0, 1), is(true));
        assertThat(weightedQuickUnionUF.count(), is(9));
        assertThat(weightedQuickUnionUF.find(1), is(0));
        assertThat(weightedQuickUnionUF.find(0), is(0));
    }

    @Test
    public void testUnionAndConnected_MultiConnection() throws Exception {
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
        weightedQuickUnionUF.union(0, 1);
        weightedQuickUnionUF.union(2, 1);
        assertThat(weightedQuickUnionUF.connected(0, 1), is(true));
        assertThat(weightedQuickUnionUF.connected(2, 1), is(true));
        assertThat(weightedQuickUnionUF.connected(2, 0), is(true));
        assertThat(weightedQuickUnionUF.count(), is(8));
        assertThat(weightedQuickUnionUF.find(1), is(0));
        assertThat(weightedQuickUnionUF.find(0), is(0));
        assertThat(weightedQuickUnionUF.find(2), is(0));

        weightedQuickUnionUF.union(2, 5);
        assertThat(weightedQuickUnionUF.connected(0, 1), is(true));
        assertThat(weightedQuickUnionUF.connected(2, 1), is(true));
        assertThat(weightedQuickUnionUF.connected(2, 0), is(true));
        assertThat(weightedQuickUnionUF.connected(5, 0), is(true));
        assertThat(weightedQuickUnionUF.connected(5, 1), is(true));
        assertThat(weightedQuickUnionUF.connected(5, 2), is(true));
        assertThat(weightedQuickUnionUF.count(), is(7));
        assertThat(weightedQuickUnionUF.find(1), is(0));
        assertThat(weightedQuickUnionUF.find(0), is(0));
        assertThat(weightedQuickUnionUF.find(2), is(0));
        assertThat(weightedQuickUnionUF.find(5), is(0));
    }

    @Test
    public void testFind() throws Exception {
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
        for (int i = 0; i < 10; i++) {
            assertThat(weightedQuickUnionUF.find(i), is(i));
        }
    }
}
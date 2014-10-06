package UnionFinds;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class QuickFindUFTest {
    @Test(expected = IndexOutOfBoundsException.class)
    public void testUnion_ParameterValidation_PLessThan0() throws Exception {
        QuickFindUF quickFindUF = new QuickFindUF(10);
        quickFindUF.union(-1, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testUnion_ParameterValidation_PEqualN() throws Exception {
        QuickFindUF quickFindUF = new QuickFindUF(10);
        quickFindUF.union(10, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testUnion_ParameterValidation_PLargerThanN() throws Exception {
        QuickFindUF quickFindUF = new QuickFindUF(10);
        quickFindUF.union(11, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testUnion_ParameterValidation_QLessThan0() throws Exception {
        QuickFindUF quickFindUF = new QuickFindUF(10);
        quickFindUF.union(4, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testUnion_ParameterValidation_QEqualN() throws Exception {
        QuickFindUF quickFindUF = new QuickFindUF(10);
        quickFindUF.union(4, 10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testUnion_ParameterValidation_QLargerThanN() throws Exception {
        QuickFindUF quickFindUF = new QuickFindUF(10);
        quickFindUF.union(4, 11);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testConnected_ParameterValidation_PLessThan0() throws Exception {
        QuickFindUF quickFindUF = new QuickFindUF(10);
        quickFindUF.connected(-1, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testConnected_ParameterValidation_PEqualN() throws Exception {
        QuickFindUF quickFindUF = new QuickFindUF(10);
        quickFindUF.connected(10, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testConnected_ParameterValidation_PLargerThanN() throws Exception {
        QuickFindUF quickFindUF = new QuickFindUF(10);
        quickFindUF.connected(11, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testConnected_ParameterValidation_QLessThan0() throws Exception {
        QuickFindUF quickFindUF = new QuickFindUF(10);
        quickFindUF.connected(4, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testConnected_ParameterValidation_QEqualN() throws Exception {
        QuickFindUF quickFindUF = new QuickFindUF(10);
        quickFindUF.connected(4, 10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testConnected_ParameterValidation_QLargerThanN() throws Exception {
        QuickFindUF quickFindUF = new QuickFindUF(10);
        quickFindUF.connected(4, 11);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFind_ParameterValidation_PLessThan0() throws Exception {
        QuickFindUF quickFindUF = new QuickFindUF(10);
        quickFindUF.find(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFind_ParameterValidation_PEqualN() throws Exception {
        QuickFindUF quickFindUF = new QuickFindUF(10);
        quickFindUF.find(10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFind_ParameterValidation_PLargerThanN() throws Exception {
        QuickFindUF quickFindUF = new QuickFindUF(10);
        quickFindUF.find(11);
    }


    @Test
    public void testUnionAndConnected_NewConnection() throws Exception {
        QuickFindUF quickFindUF = new QuickFindUF(10);
        assertThat(quickFindUF.connected(0, 1), is(false));
        assertThat(quickFindUF.count(), is(10));
        quickFindUF.union(0, 1);
        assertThat(quickFindUF.connected(0, 1), is(true));
        assertThat(quickFindUF.count(), is(9));
        assertThat(quickFindUF.find(1), is(1));
        assertThat(quickFindUF.find(0), is(1));
    }

    @Test
    public void testUnionAndConnected_AlreadyConnected() throws Exception {
        QuickFindUF quickFindUF = new QuickFindUF(10);
        quickFindUF.union(0, 1);
        assertThat(quickFindUF.connected(0, 1), is(true));
        assertThat(quickFindUF.count(), is(9));
        quickFindUF.union(0, 1);
        assertThat(quickFindUF.connected(0, 1), is(true));
        assertThat(quickFindUF.count(), is(9));
        assertThat(quickFindUF.find(1), is(1));
        assertThat(quickFindUF.find(0), is(1));
    }

    @Test
    public void testUnionAndConnected_MultiConnection() throws Exception {
        QuickFindUF quickFindUF = new QuickFindUF(10);
        quickFindUF.union(0, 1);
        quickFindUF.union(2, 1);
        assertThat(quickFindUF.connected(0, 1), is(true));
        assertThat(quickFindUF.connected(2, 1), is(true));
        assertThat(quickFindUF.connected(2, 0), is(true));
        assertThat(quickFindUF.count(), is(8));
        assertThat(quickFindUF.find(1), is(1));
        assertThat(quickFindUF.find(0), is(1));
        assertThat(quickFindUF.find(2), is(1));

        quickFindUF.union(2, 5);
        assertThat(quickFindUF.connected(0, 1), is(true));
        assertThat(quickFindUF.connected(2, 1), is(true));
        assertThat(quickFindUF.connected(2, 0), is(true));
        assertThat(quickFindUF.connected(5, 0), is(true));
        assertThat(quickFindUF.connected(5, 1), is(true));
        assertThat(quickFindUF.connected(5, 2), is(true));
        assertThat(quickFindUF.count(), is(7));
        assertThat(quickFindUF.find(1), is(5));
        assertThat(quickFindUF.find(0), is(5));
        assertThat(quickFindUF.find(2), is(5));
        assertThat(quickFindUF.find(5), is(5));
    }

    @Test
    public void testFind() throws Exception {
        QuickFindUF quickFindUF = new QuickFindUF(10);
        for (int i = 0; i < 10; i++) {
            assertThat(quickFindUF.find(i), is(i));
        }
    }
}
package UnionFinds;

import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UnionFindTest {
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_ParameterValidation_NEqual0() throws Exception {
        new MockUnionFind(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_ParameterValidation_NLessThan0() throws Exception {
        new MockUnionFind(-1);
    }

    @Test
    public void testConstructor() throws Exception {
        MockUnionFind unionFind = new MockUnionFind(5);
        assertThat(unionFind.count(), is(5));
    }

    private class MockUnionFind extends UnionFind {
        public MockUnionFind(int N) throws IllegalArgumentException {
            super(N);
        }

        @Override
        public void union(int p, int q) throws IndexOutOfBoundsException {
            throw new NotImplementedException();
        }

        @Override
        public boolean connected(int p, int q) throws IndexOutOfBoundsException {
            throw new NotImplementedException();
        }

        @Override
        public int find(int p) throws IndexOutOfBoundsException {
            throw new NotImplementedException();
        }
    }
}
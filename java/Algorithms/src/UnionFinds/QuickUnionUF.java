package UnionFinds;

/**
 * Created by jianming.xiao on 10/5/14.
 */
public class QuickUnionUF extends UnionFind {
    /**
     * Initializes an empty union-find data structure with N isolated components 0 through N-1.
     *
     * @param N the number of objects
     * @throws java.lang.IllegalArgumentException if N < 0
     */
    public QuickUnionUF(int N) {
        super(N);
    }

    /**
     * Merges the component containing site<tt>p</tt> with the component
     * containing site <tt>q</tt>.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @throws java.lang.IndexOutOfBoundsException unless both 0 <= p < N and 0 <= q < N
     */
    @Override
    public void union(int p, int q) throws IndexOutOfBoundsException {
        if (p < 0 || p >= id.length) throw new IndexOutOfBoundsException();
        if (q < 0 || q >= id.length) throw new IndexOutOfBoundsException();

        if (connected(p, q)) return;;

        int pRoot = find(p);
        int qRoot = find(q);
        id[pRoot] = qRoot;
        count--;
    }

    /**
     * Are the two sites <tt>p</tt> and <tt>q</tt> in the same component?
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return <tt>true</tt> if the two sites <tt>p</tt> and <tt>q</tt>
     * are in the same component, and <tt>false</tt> otherwise
     * @throws java.lang.IndexOutOfBoundsException unless both 0 <= p < N and 0 <= q < N
     */
    @Override
    public boolean connected(int p, int q) throws IndexOutOfBoundsException {
        if (p < 0 || p >= id.length) throw new IndexOutOfBoundsException();
        if (q < 0 || q >= id.length) throw new IndexOutOfBoundsException();

        return find(p) == find(q);
    }

    /**
     * Returns the component identifier for the component containing site <tt>p</tt>.
     *
     * @param p the integer representing one site
     * @return the component identifier for the component containing site <tt>p</tt>
     * @throws java.lang.IndexOutOfBoundsException unless 0 <= p < N
     */
    @Override
    public int find(int p) throws IndexOutOfBoundsException {
        while (p != id[p]) {
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }
}

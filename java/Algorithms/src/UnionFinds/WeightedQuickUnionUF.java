package UnionFinds;

/**
 * Created by jianming.xiao on 10/5/14.
 */
public class WeightedQuickUnionUF extends QuickUnionUF {
    private int[] sz;           // sz[i] = number of objects in subtree rooted at i

    /**
     * Initializes an empty union-find data structure with N isolated components 0 through N-1.
     *
     * @param N the number of objects
     * @throws java.lang.IllegalArgumentException if N < 0
     */
    public WeightedQuickUnionUF(int N) {
        super(N);
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
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
    public void union(int p, int q) throws IndexOutOfBoundsException{
        if (p < 0 || p >= id.length) throw new IndexOutOfBoundsException();
        if (q < 0 || q >= id.length) throw new IndexOutOfBoundsException();

        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        if (sz[pRoot] < sz[qRoot]) {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        count--;
    }
}

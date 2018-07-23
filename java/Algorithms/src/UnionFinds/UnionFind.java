package UnionFinds;

/**
 * Created by jianming.xiao on 10/5/14.
 */
public abstract class UnionFind implements IUnionFind {
    protected int[] id;         // id[i] = component identifier of i
    protected int count;        // number of components

    /**
     * Initializes an empty union-find data structure with N isolated components 0 through N-1.
     *
     * @param N the number of objects
     * @throws java.lang.IllegalArgumentException if N < 0
     */
    public UnionFind(int N) throws IllegalArgumentException {
        if (N <= 0) throw new IllegalArgumentException();

        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        this.count = N;
    }

    /**
     * Returns the number of components.
     *
     * @return the number of components (between 1 and N)
     */
    public int count() {
        return count;
    }
}

package UnionFinds;

/**
 * Created by jianming.xiao on 10/5/14.
 */
public class QuickUnionUF extends UnionFind {
    public QuickUnionUF(int N) {
        super(N);
    }

    @Override
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }

    @Override
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    protected int root(int i) {
        while (i != id[i]) i = id[i];
        return i;
    }
}

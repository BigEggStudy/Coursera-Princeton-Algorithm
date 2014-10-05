package UnionFinds;

/**
 * Created by jianming.xiao on 10/5/14.
 */
public abstract class UnionFind implements IUnionFind {
    protected int[] id;

    public UnionFind(int N) {
        if (N <= 0) throw new IllegalArgumentException();

        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }
}

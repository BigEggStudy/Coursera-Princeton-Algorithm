package UnionFinds;

/**
 * Created by jianming.xiao on 10/5/14.
 */
public interface IUnionFind {
    void union(int p, int q);

    boolean connected(int p, int q);
}

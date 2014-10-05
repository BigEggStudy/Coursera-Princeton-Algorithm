package UnionFinds;

/**
 * Created by jianming.xiao on 10/5/14.
 */
public class QuickFindUF extends UnionFind {
    public QuickFindUF(int N) {
        super(N);
    }

    @Override
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];

        for (int i = 0; i < id.length; i++)
            if (id[i] == pid) id[i] = qid;
    }

    @Override
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }
}

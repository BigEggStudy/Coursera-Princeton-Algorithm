package Graph;

import Collections.ResizingArrayStack;

public class DepthFirstPaths extends Paths {
    private boolean[] marked;
    private int[] edgeTo;

    /**
     * Find paths in g from source s by using Depth-first search
     * @param G graph
     * @param s source
     */
    public DepthFirstPaths(IGraph G, int s) {
        super(G, s);

        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];

        dfs(G, s);
    }

    /**
     * Is there a path from s to v?
     * @param v vetex
     * @return True if there has a path from s to v, otherwise false.
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * Path from s to v; null if no such path
     * @param v vertex
     * @return Path from s to v; null if no such path
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        ResizingArrayStack<Integer> path = new ResizingArrayStack<Integer>();
        for (int x = v; x != this.source; x = edgeTo[x])
            path.push(x);
        path.push(this.source);
        return path;
    }

    private void dfs(IGraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w])
            {
                dfs(G, w);
                edgeTo[w] = v;
            }
    }
}

package Graph;

public class CC {
    private boolean marked[];
    private int[] id;
    private int count;

    /**
     * Find connected components in G
     * @param G graph
     */
    public CC(Graph G) {
        this.marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!this.marked[v]) {
                dfs(G, v);
                count++;
            }
    }

    /**
     * Check is v and w connected.
     * @param v vertex 1
     * @param w vertex 2
     * @return True if v and w is connected, otherwise false.
     */
    public boolean connected(int v, int w) {
        return this.id(v) == this.id(w);
    }

    /**
     * Get the number of connected components
     * @return The number of connected components
     */
    public int count() {
        return this.count;
    }

    /**
     * Get the component identifier for v
     * @param v Vertex v
     * @return Component identifier for v
     */
    public int id(int v) {
        return this.id[v];
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = this.count;
        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w);
    }
}

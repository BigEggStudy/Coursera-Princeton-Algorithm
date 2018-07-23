package Graph;

import Collections.ResizingArrayStack;

public class DepthFirstOrder {
    private boolean[] marked;
    private ResizingArrayStack<Integer> reversePost;

    /**
     * Find the Topological Sort order by using Depth-first search
     * @param G graph
     */
    public DepthFirstOrder(IGraph G, int s) {
        this.marked = new boolean[G.V()];
        this.reversePost = new ResizingArrayStack<Integer>();
        for (int v = 0; v < G.V(); v++)
            if (!marked[v])
                dfs(G, s);
    }

    /**
     * Get the topological sort order
     * @return The topological sort order
     */
    public Iterable<Integer> reversePost() {
        return reversePost;
    }

    private void dfs(IGraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w);
        reversePost.push(v);
    }
}

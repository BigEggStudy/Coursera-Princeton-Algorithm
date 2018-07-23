package Graph;

import Collections.ResizingArrayBag;
import com.sun.istack.internal.NotNull;

public class Digraph implements IGraph {
    private final int V;
    private ResizingArrayBag<Integer>[] adj;

    /**
     * Create an empty digraph with V vertices
     * @param V the number of vertices
     * @throws java.lang.IllegalArgumentException if V < 0
     */
    public Digraph(int V) throws IllegalArgumentException {
        if (V <= 0) throw new IllegalArgumentException();

        this.V = V;
        this.adj = (ResizingArrayBag<Integer>[]) new Object[V];
        for (int v = 0; v < V; v++) {
            this.adj[v] = new ResizingArrayBag<Integer>();
        }
    }

    /**
     * Add an edge v-w
     * @param v start vertex
     * @param w end vertex
     */
    public void addEdge(int v, int w) {
        this.adj[v].add(w);
    }

    /**
     * Get the vertices adjacent to v
     * @param v vertex
     * @return Vertices adjacent to v
     */
    public Iterable<Integer> adj(int v) {
        return this.adj[v];
    }

    /**
     * Get the number of vertices
     * @return Number of vertices
     */
    public int V() {
        return V;
    }

    /**
     * Get the number of edges
     * @return Number of edges
     */
    public int E() {
        int count = 0;
        for (ResizingArrayBag<Integer> v_adj : this.adj)
            count += v_adj.size();
        return count;
    }

    /**
     * Reverse the current graph
     * @return the reverse graph
     */
    public Digraph reverse() {
        Digraph reverse = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    /**
     * Compute the degree of v in graph
     * @param G Graph
     * @param v vertex
     * @return The degree of v in graph
     */
    public static int degree(@NotNull Graph G, int v) {
        int degree = 0;
        for (int w: G.adj(v)) degree++;

        return degree;
    }

    /**
     * Compute maximum degree in graph
     * @param G Graph
     * @return Maximum degree in graph
     */
    public static int maxDefree(@NotNull Graph G) {
        int max = 0;
        for (int v = 0; v < G.V(); v++)
            if (degree(G, v) > max)
                max = degree(G, v);
        return max;
    }

    /**
     * Compute average degree in graph
     * @param G Graph
     * @return Average degree in graph
     */
    public static double averageDegree(@NotNull Graph G) {
        return G.E() / G.V();
    }

    /**
     * Count self-loops
     * @param G Graph
     * @return Self-loops count
     */
    public static int numberOfSelfLoops(@NotNull Graph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++)
            for (int w : G.adj(v))
                if (v == w) count++;
        return count;
    }
}

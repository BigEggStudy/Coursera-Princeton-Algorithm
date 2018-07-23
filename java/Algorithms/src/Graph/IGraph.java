package Graph;

public interface IGraph {
    /**
     * Add an edge v-w
     * @param v start vertex
     * @param w end vertex
     */
    void addEdge(int v, int w);

    /**
     * Get the vertices adjacent to v
     * @param v vertex
     * @return Vertices adjacent to v
     */
    Iterable<Integer> adj(int v);

    /**
     * Get the number of vertices
     * @return Number of vertices
     */
    int V();

    /**
     * Get the number of edges
     * @return Number of edges
     */
    int E();
}

package Graph;

public abstract class Paths {
    protected final IGraph G;
    protected final int source;

    /**
     * Find paths in g from source s
     * @param G graph
     * @param s source
     */
    public Paths(IGraph G, int s) {
        this.G = G;
        this.source = s;
    }

    /**
     * Is there a path from s to v?
     * @param v vetex
     * @return True if there has a path from s to v, otherwise false.
     */
    public abstract boolean hasPathTo(int v);

    /**
     * Path from s to v; null if no such path
     * @param v vertex
     * @return Path from s to v; null if no such path
     */
    public abstract Iterable<Integer> pathTo(int v);
}

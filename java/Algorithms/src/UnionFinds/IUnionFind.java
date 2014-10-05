package UnionFinds;

/**
 * Created by jianming.xiao on 10/5/14.
 */
public interface IUnionFind {
    /**
     * Merges the component containing site<tt>p</tt> with the component
     * containing site <tt>q</tt>.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @throws java.lang.IndexOutOfBoundsException unless both 0 <= p < N and 0 <= q < N
     */
    void union(int p, int q) throws IndexOutOfBoundsException;

    /**
     * Are the two sites <tt>p</tt> and <tt>q</tt> in the same component?
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return <tt>true</tt> if the two sites <tt>p</tt> and <tt>q</tt>
     * are in the same component, and <tt>false</tt> otherwise
     * @throws java.lang.IndexOutOfBoundsException unless both 0 <= p < N and 0 <= q < N
     */
    boolean connected(int p, int q) throws IndexOutOfBoundsException;

    /**
     * Returns the number of components.
     *
     * @return the number of components (between 1 and N)
     */
    int count();

    /**
     * Returns the component identifier for the component containing site <tt>p</tt>.
     *
     * @param p the integer representing one site
     * @return the component identifier for the component containing site <tt>p</tt>
     * @throws java.lang.IndexOutOfBoundsException unless 0 <= p < N
     */
    int find(int p) throws IndexOutOfBoundsException;
}

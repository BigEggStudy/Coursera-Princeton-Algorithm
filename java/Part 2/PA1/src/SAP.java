import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class SAP {
    private Digraph graph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.graph = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (v < 0 || w < 0 || v >= graph.V() || w >= graph.V()) {
            throw new java.lang.IllegalArgumentException();
        }

        if (v == w) {
            return 0;
        }

        BreadthFirstDirectedPaths bfsForV = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths bfsForW = new BreadthFirstDirectedPaths(graph, w);

        int shortestLength = Integer.MAX_VALUE;
        for (int i = 0; i < graph.V(); i++) {
            if (bfsForV.hasPathTo(i) && bfsForW.hasPathTo(i) && bfsForV.distTo(i) + bfsForW.distTo(i) < shortestLength) {
                shortestLength = bfsForV.distTo(i) + bfsForW.distTo(i);
            }
        }

        return shortestLength == Integer.MAX_VALUE ? -1 : shortestLength;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        if (v < 0 || w < 0 || v >= graph.V() || w >= graph.V()) {
            throw new java.lang.IllegalArgumentException();
        }

        if (v == w) {
            return v;
        }

        BreadthFirstDirectedPaths bfsForV = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths bfsForW = new BreadthFirstDirectedPaths(graph, w);

        int shortestLength = Integer.MAX_VALUE;
        int ancestor = -1;
        for (int i = 0; i < graph.V(); i++) {
            if (bfsForV.hasPathTo(i) && bfsForW.hasPathTo(i) && bfsForV.distTo(i) + bfsForW.distTo(i) < shortestLength) {
                shortestLength = bfsForV.distTo(i) + bfsForW.distTo(i);
                ancestor = i;
            }
        }

        return ancestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new IllegalArgumentException();
        }
        for (int vi : v) {
            if (vi < 0 || vi >= graph.V()) {
                throw new java.lang.IllegalArgumentException();
            }
        }
        for (int wi : w) {
            if (wi < 0 || wi >= graph.V()) {
                throw new java.lang.IllegalArgumentException();
            }
        }

        BreadthFirstDirectedPaths bfsForV = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths bfsForW = new BreadthFirstDirectedPaths(graph, w);

        int shortestLength = Integer.MAX_VALUE;
        for (int i = 0; i < graph.V(); i++) {
            if (bfsForV.hasPathTo(i) && bfsForW.hasPathTo(i) && bfsForV.distTo(i) + bfsForW.distTo(i) < shortestLength) {
                shortestLength = bfsForV.distTo(i) + bfsForW.distTo(i);
            }
        }

        return shortestLength == Integer.MAX_VALUE ? -1 : shortestLength;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new IllegalArgumentException();
        }
        for (int vi : v) {
            if (vi < 0 || vi >= graph.V()) {
                throw new java.lang.IllegalArgumentException();
            }
        }
        for (int wi : w) {
            if (wi < 0 || wi >= graph.V()) {
                throw new java.lang.IllegalArgumentException();
            }
        }

        BreadthFirstDirectedPaths bfsForV = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths bfsForW = new BreadthFirstDirectedPaths(graph, w);

        int shortestLength = Integer.MAX_VALUE;
        int ancestor = -1;
        for (int i = 0; i < graph.V(); i++) {
            if (bfsForV.hasPathTo(i) && bfsForW.hasPathTo(i) && bfsForV.distTo(i) + bfsForW.distTo(i) < shortestLength) {
                shortestLength = bfsForV.distTo(i) + bfsForW.distTo(i);
                ancestor = i;
            }
        }

        return ancestor;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}

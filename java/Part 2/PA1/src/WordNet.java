import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

import java.util.ArrayList;
import java.util.List;

public class WordNet {
    private final ST<String, List<Integer>> nounIdSet;
    private final ST<Integer, String> idNounSet;

    private final Digraph graph;
    private final SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        this.nounIdSet = new ST<String, List<Integer>>();
        this.idNounSet = new ST<Integer, String>();

        In synsetsInput = new In(synsets);
        int size = 0;
        while (synsetsInput.hasNextLine()) {
            size++;
            String str = synsetsInput.readLine();
            String[] strArray = str.split(",");

            int id = Integer.parseInt(strArray[0]);
            String synset = strArray[1];
            this.idNounSet.put(id, synset);

            String[] nouns = synset.split(" ");
            for (String noun: nouns) {
                if (this.nounIdSet.contains(noun)) {
                    List<Integer> data = this.nounIdSet.get(noun);
                    data.add(id);
                    this.nounIdSet.put(noun, data);
                } else {
                    List<Integer> data = new ArrayList<Integer>();
                    data.add(id);
                    this.nounIdSet.put(noun, data);
                }
            }
        }

        this.graph = new Digraph(size);
        In hypernymsInput = new In(hypernyms);
        while (hypernymsInput.hasNextLine()) {
            String str = hypernymsInput.readLine();
            String[] strArray = str.split(",");

            int id = Integer.parseInt(strArray[0]);
            for (int i = 1; i < strArray.length; i++) {
                int relatedId = Integer.parseInt(strArray[i]);
                if (relatedId < 0 || relatedId >= size || relatedId == id) {
                    throw new IllegalArgumentException("hypernyms");
                }

                graph.addEdge(id, relatedId);
            }
        }

        if (!isRootedDAG()) {
            throw new IllegalArgumentException();
        }

        this.sap = new SAP(this.graph);
    }

    private boolean isRootedDAG() {
        DirectedCycle cycleFinder = new DirectedCycle(this.graph);
        if (cycleFinder.hasCycle()) {
            return false;
        }

        int zeroOutCount = 0;
        for (int i = 0; i < this.graph.V(); i++) {
            int adjCount = 0;
            for (int ignored : this.graph.adj(i)) {
                adjCount++;
            }

            if (adjCount == 0) {
                zeroOutCount++;
            }
        }

        return zeroOutCount == 1;
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return this.nounIdSet.keys();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return this.nounIdSet.contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA)) {
            throw new IllegalArgumentException("nounA");
        }
        if (!isNoun(nounB)) {
            throw new IllegalArgumentException("nounB");
        }

        return sap.length(this.nounIdSet.get(nounA), this.nounIdSet.get(nounB));
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA)) {
            throw new IllegalArgumentException("nounA");
        }
        if (!isNoun(nounB)) {
            throw new IllegalArgumentException("nounB");
        }

        int ancestorId = sap.ancestor(this.nounIdSet.get(nounA), this.nounIdSet.get(nounB));
        return this.idNounSet.get(ancestorId);
    }

    // do unit testing of this class
    public static void main(String[] args) {
        WordNet wordNet = new WordNet(args[0], args[1]);
        while (!StdIn.isEmpty()) {
            String nounA = StdIn.readString();
            String nounB = StdIn.readString();
            int distance = wordNet.distance(nounA, nounB);
            String ancestor = wordNet.sap(nounA, nounB);

            StdOut.printf("length = %d, ancestor = %s\n", distance, ancestor);
        }
    }
}

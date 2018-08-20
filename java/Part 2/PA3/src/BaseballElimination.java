import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FordFulkerson;

public class BaseballElimination {
    private final int numberOfTeams;
    private final String[] teams;
    private final ST<String, Integer> teamsST;
    private final int[] w;
    private final int[] l;
    private final int[] r;
    private final int[][] g;
    private final boolean[] isEliminated;
    private final SET<String>[] certificateOfElimination;

    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        In in = new In(filename);
        this.numberOfTeams = in.readInt();

        this.teams = new String[numberOfTeams];
        this.teamsST = new ST<String, Integer>();
        this.w = new int[numberOfTeams];
        this.l = new int[numberOfTeams];
        this.r = new int[numberOfTeams];
        this.g = new int[numberOfTeams][numberOfTeams];

        for (int i = 0; i < numberOfTeams; i++) {
            this.teams[i] = in.readString();
            this.teamsST.put(this.teams[i], i);
            this.w[i] = in.readInt();
            this.l[i] = in.readInt();
            this.r[i] = in.readInt();

            for (int j = 0; j < numberOfTeams; j++) {
                this.g[i][j] = in.readInt();
            }
        }


        this.isEliminated = new boolean[numberOfTeams];
        this.certificateOfElimination = (SET<String>[]) new SET[numberOfTeams];

        trivialElimination();
        nonTrivialElimination();
    }

    private void trivialElimination() {
        for (int i = 0; i < this.numberOfTeams; i++) {
            for (int j = 0; j < this.numberOfTeams; j++) {
                if (i == j) {
                    continue;
                }

                if (this.w[i] + this.r[i] < this.w[j]) {
                    this.isEliminated[i] = true;
                    if (this.certificateOfElimination[i] == null) {
                        certificateOfElimination[i] = new SET<String>();
                    }
                    this.certificateOfElimination[i].add(this.teams[j]);
                }
            }
        }
    }

    private void nonTrivialElimination() {
        final int numberOfGameVertices = (this.numberOfTeams * (this.numberOfTeams - 1)) / 2;
        final int numberOfFlowNetworkVertices = 1
                + numberOfGameVertices
                + this.numberOfTeams
                + 1;

        for (int teamId = 0; teamId < this.numberOfTeams; teamId++) {
            if (this.isEliminated[teamId]) {
                continue;
            }

            FlowNetwork G = new FlowNetwork(numberOfFlowNetworkVertices);

            // Add vertices to flow network.
            int vertexNumber = 1;
            for (int i = 0; i < numberOfTeams - 1; i++) {
                if (i == teamId) {
                    continue;
                }

                for (int j = i + 1; j < numberOfTeams; j++) {
                    if (j == teamId) {
                        continue;
                    }

                    G.addEdge(new FlowEdge(0, vertexNumber, g[i][j]));
                    G.addEdge(new FlowEdge(vertexNumber, i + numberOfGameVertices + 1, Double.POSITIVE_INFINITY));
                    G.addEdge(new FlowEdge(vertexNumber, j + numberOfGameVertices + 1, Double.POSITIVE_INFINITY));

                    vertexNumber++;
                }
            }

            // Add edges from team vertices to target.
            for (int i = 0; i < this.numberOfTeams; i++) {
                if (i == teamId) {
                    continue;
                }

                G.addEdge(new FlowEdge(i + numberOfGameVertices + 1, G.V() - 1, w[teamId] + r[teamId] - w[i]));
            }

            FordFulkerson maxflow = new FordFulkerson(G, 0, G.V() - 1);
            for (FlowEdge e : G.adj(0)) {
                if (e.flow() != e.capacity()) {
                    this.isEliminated[teamId] = true;
                    break;
                }
            }

            // Find certificate of elimination.
            if (isEliminated[teamId]) {
                for (int i = 0; i < numberOfTeams; i++) {
                    if (i == teamId) {
                        continue;
                    }

                    if (maxflow.inCut(i + numberOfGameVertices + 1)) {
                        if (this.certificateOfElimination[teamId] == null) {
                            certificateOfElimination[teamId] = new SET<String>();
                        }
                        certificateOfElimination[teamId].add(teams[i]);
                    }
                }
            }
        }
    }

    // number of teams
    public int numberOfTeams() {
        return this.numberOfTeams;
    }

    // all teams
    public Iterable<String> teams() {
        return this.teamsST.keys();
    }

    // number of w for given team
    public int wins(String team) {
        if (team == null || !this.teamsST.contains(team)) {
            throw new IllegalArgumentException();
        }

        return this.w[this.teamsST.get(team)];
    }

    // number of l for given team
    public int losses(String team) {
        if (team == null || !this.teamsST.contains(team)) {
            throw new IllegalArgumentException();
        }

        return this.l[this.teamsST.get(team)];
    }

    // number of remaining games for given team
    public int remaining(String team) {
        if (team == null || !this.teamsST.contains(team)) {
            throw new IllegalArgumentException();
        }

        return this.r[this.teamsST.get(team)];
    }

    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        if (team1 == null || !this.teamsST.contains(team1) || team2 == null || !this.teamsST.contains(team2)) {
            throw new IllegalArgumentException();
        }

        return this.g[this.teamsST.get(team1)][this.teamsST.get(team2)];
    }

    // is given team eliminated?
    public boolean isEliminated(String team) {
        if (team == null || !this.teamsST.contains(team)) {
            throw new IllegalArgumentException();
        }

        return this.isEliminated[this.teamsST.get(team)];
    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        if (team == null || !this.teamsST.contains(team)) {
            throw new IllegalArgumentException();
        }

        return this.certificateOfElimination[this.teamsST.get(team)];
    }

    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            }
            else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}

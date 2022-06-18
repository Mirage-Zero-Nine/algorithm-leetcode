package solution.graph;

import java.util.*;

/**
 * Consider a directed graph, with nodes labelled 0, 1, ..., n-1.  In this graph, each edge is either red or blue, and there could be self-edges or parallel edges.
 * Each [i, j] in red_edges denotes a red directed edge from node i to node j.
 * Similarly, each [i, j] in blue_edges denotes a blue directed edge from node i to node j.
 * Return an array answer of length n.
 * Each answer[X] is the length of the shortest path from node 0 to node X, that edge colors alternate along the path.
 * Return -1 if such a path doesn't exist).
 *
 * @author BorisMirage
 * Time: 2019/09/21 14:48
 * Created with IntelliJ IDEA
 */

public class ShortestAlternatingPaths_1129 {
    /**
     * Use BFS to find the shortest path.
     * During BFS, mark nodes connected via red edge to positive id, mark node connected via blue edges as negative id.
     * Then push the upcoming node into queue to continue BFS.
     * If a node can not be connected through alternate colored edge, leave it.
     * Use two hash maps to record nodes and its connected edges, to save time.
     *
     * @param n          number of nodes
     * @param red_edges  red edges
     * @param blue_edges blue edges
     * @return from 0 to each node's shortest path
     */
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {

        HashMap<Integer, List<Integer>> b = new HashMap<>();
        HashMap<Integer, List<Integer>> r = new HashMap<>();

        for (int[] e : red_edges) {
            List<Integer> list = r.getOrDefault(e[0], new ArrayList<>());
            list.add(e[1]);
            r.put(e[0], list);
        }
        for (int[] e : blue_edges) {
            List<Integer> list = b.getOrDefault(e[0], new ArrayList<>());
            list.add(e[1]);
            b.put(e[0], list);
        }

        int[] out = new int[n];
        Arrays.fill(out, -1);
        out[0] = 0;
        Queue<Integer> q = new LinkedList<>();
        boolean[] isVisited = new boolean[2 * n];
        q.add(0);
        q.add(null);
        int level = 1;          // distance from 0 to other nodes

        while (!q.isEmpty()) {
            Integer current = q.poll();
            if (current == null) {
                if (!q.isEmpty()) {      // reaches the end of level
                    q.add(null);
                    level++;
                }
            } else if (!isVisited[current + n]) {
                isVisited[current + n] = true;
                if (current >= 0 && r.containsKey(current)) {
                    for (int i : r.get(current)) {
                        if (out[i] == -1) {
                            out[i] = level;
                        }
                        q.add(-i);
                    }
                }
                if (current <= 0 && b.containsKey(-current)) {
                    for (int i : b.get(-current)) {
                        if (out[i] == -1) {
                            out[i] = level;
                        }
                        q.add(i);
                    }
                }
            }
        }

        return out;
    }
}

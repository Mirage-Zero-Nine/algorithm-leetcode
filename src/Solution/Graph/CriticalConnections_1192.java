package Solution.Graph;

import java.util.*;

/**
 * There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network.
 * Any server can reach any other server directly or indirectly through the network.
 * A critical connection is a connection that, if removed, will make some server unable to reach some other server.
 * Return all critical connections in the network in any order.
 *
 * @author BorisMirage
 * Time: 2019/09/14 20:25
 * Created with IntelliJ IDEA
 */
public class CriticalConnections_1192 {
    private int time = 1;       // global time

    /**
     * Tarjan algorithm, use timestamp that records the order of vertices visited to find all cycles on graph.
     * Critical edges are the edges not on cycle (connections between cycle).
     * Use a global timestamp to record the reach order of nodes on graph via DFS.
     * And return the min timestamp that can be reached by one-time DFS traversal.
     * If node is on cycle, the min timestamp it can get is the first reached node on cycle.
     * Otherwise, the min timestamp is the node's initially timestamp itself.
     * Therefore, if minTimestamp >= timestamp[current], then edge (previous - current) is a critical edge.
     *
     * @param n           # of vertices
     * @param connections given list contains connections between nodes in graph
     * @return all critical connections in the network in any order
     */
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (List<Integer> c : connections) {       // build graph
            graph.get(c.get(0)).add(c.get(1));
            graph.get(c.get(1)).add(c.get(0));
        }

        List<List<Integer>> out = new LinkedList<>();

        int[] timestamp = new int[n];

        dfs(out, graph, 0, -1, timestamp);

        return out;
    }

    /**
     * DFS search, if current node is reached before, directly return the timestamp on this node.
     * In this case, there is a cycle in graph.
     * Otherwise, return the min time stamp that can be reached.
     * It will either be the first reached node in cycle, or the current node timestamp itself when there is no cycle.
     *
     * @param out       output list
     * @param graph     graph edges
     * @param current   current node id
     * @param previous  previous node id
     * @param timestamp previous timestamp
     * @return min timestamp can be reached if on a cycle
     */
    private int dfs(List<List<Integer>> out, List<List<Integer>> graph, int current, int previous, int[] timestamp) {

        if (timestamp[current] > 0) {       // avoid add extra edges into output result
            return timestamp[current];
        }

        timestamp[current] = time++;        // set current node timestamp

        int minTimestamp = time;            // the min timestamp current node is original timestamp

        for (int next : graph.get(current)) {
            if (next != previous) {
                int neighborTimestamp = dfs(out, graph, next, current, timestamp);      // obtain min timestamp
                minTimestamp = Math.min(minTimestamp, neighborTimestamp);
            }
        }

        /*
         * Condition of critical edge:
         * If current node min timestamp is larger than current node time stamp, then previous node is not on cycle.
         * Therefore, this edge (previous - current) is a critical edge. */
        if (minTimestamp >= timestamp[current] && previous >= 0) {
            out.add(Arrays.asList(previous, current));
        }

        return Math.min(timestamp[current], minTimestamp);      // if there is a cycle, update return timestamp
    }
}




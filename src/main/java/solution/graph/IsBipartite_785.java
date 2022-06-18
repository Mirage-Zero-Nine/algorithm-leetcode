package solution.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an undirected graph, return true if and only if it is bipartite.
 * A graph is bipartite if it can be split into two independent subsets A and B.
 * Every edge in the graph has one node in A and another node in B.
 * Each node is an integer between 0 and graph.length - 1.
 * There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
 * Note:
 * 1. graph will have length in range [1, 100].
 * 2. graph[i] will contain integers in range [0, graph.length - 1].
 * 3. graph[i] will not contain i or duplicate values.
 * 4. The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
 *
 * @author BorisMirage
 * Time: 2019/09/18 14:12
 * Created with IntelliJ IDEA
 */

public class IsBipartite_785 {
    /**
     * DFS to color the graph.
     * If the graph can be colored by only 2 colors and each adjacent node has different color, then it is bipartite.
     *
     * @param graph given edges list
     * @return return true if and only if it is bipartite
     */
    public boolean isBipartite(int[][] graph) {

        /* Corner case */
        if (graph == null || graph.length == 0 || graph[0] == null) {
            return false;
        }

        int[] nodeColor = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (nodeColor[i] == 0 && !dfs(graph, nodeColor, i, 1)) {
                return false;
            }
        }

        return true;
    }

    /**
     * DFS search to find if the graph can be colored by only using two colors.
     *
     * @param graph given edges list
     * @param node  current node
     * @param color color to be used in current node
     * @return return true if and only if it can be colored by only 2 colors
     */
    private boolean dfs(int[][] graph, int[] nodeColor, int node, int color) {

        if (nodeColor[node] != 0) {
            /*
             * If current node has color, then it should be the same color.
             * If not, the other color would be used, which is conflicted to not using same color in adjacent nodes. */
            return nodeColor[node] == color;
        }

        nodeColor[node] = color;
        for (int n : graph[node]) {
            if (!dfs(graph, nodeColor, n, -color)) {
                return false;
            }
        }

        return true;
    }

    /**
     * BFS approach. Use a queue to record the node to be colored.
     *
     * @param graph given edges list
     * @return return true if and only if it is bipartite
     */
    public boolean isBipartiteBfs(int[][] graph) {
        int[] color = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (color[i] == 0) {
                Queue<Integer> q = new LinkedList<>();
                q.offer(i);
                color[i] = 1;

                while (!q.isEmpty()) {
                    int n = q.poll();
                    for (int j : graph[n]) {
                        if (color[j] == 0) {
                            color[j] = -color[n];
                            q.offer(j);
                        }
                        if (color[j] != -color[n]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        System.out.println(new IsBipartite_785().isBipartite(graph));
    }
}

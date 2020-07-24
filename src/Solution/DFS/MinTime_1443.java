package Solution.DFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices.
 * You spend 1 second to walk over one edge of the tree.
 * Return the minimum time in order to collect all apples in the tree starting at vertex 0 and back to this vertex.
 * The edges of the undirected tree are given in the array edges.
 * edges[i] = [from, to] means that exists an edge connecting the vertices from and to.
 * Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple.
 * Otherwise, it does not have any apple.
 *
 * @author BorisMirage
 * Time: 2020/05/10 15:08
 * Created with IntelliJ IDEA
 */

public class MinTime_1443 {
    private int min = 0;

    /**
     * Each node contains apple needs to find a shortest path to root, or the closest visited node.
     * The total time cost is actually 2 times of total number of edges that connects all apples to the root.
     * Each node contains at most two child, each child has an edge.
     * Therefore, do DFS on each node. If one subtree contains an apple, add 2 to the total cost.
     * The tree is given in a list, and its bi-directional tree.
     * Hence, build a bi-directional graph first and then start DFS.
     *
     * @param n        n nodes
     * @param edges    list of edges
     * @param hasApple whether the ith node contains apple
     * @return the minimum time in order to collect all apples in the tree starting at vertex 0 and back to this vertex
     */
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        List<List<Integer>> graph = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] e : edges) {         // build bi-direction graph
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        dfs(graph, 0, hasApple, new boolean[n]);

        return min;
    }

    /**
     * DFS to search the child of current node.
     * If one child has an apple, add 2 to final output. Only counting on the edge that connect to current node.
     *
     * @param graph       connection of nodes
     * @param currentNode # of current node
     * @param hasApple    whether the ith node contains apple
     * @param isVisited   is the ith node has been visited
     * @return whether current branch has an apple
     */
    private boolean dfs(List<List<Integer>> graph, int currentNode, List<Boolean> hasApple, boolean[] isVisited) {

        if (isVisited[currentNode]) {
            return false;
        }

        isVisited[currentNode] = true;
        boolean containsApple = hasApple.get(currentNode);

        for (int i = 0; i < graph.get(currentNode).size(); i++) {
            if (dfs(graph, graph.get(currentNode).get(i), hasApple, isVisited)) {
                min += 2;
                containsApple = true;
            }
        }

        return containsApple;
    }
}

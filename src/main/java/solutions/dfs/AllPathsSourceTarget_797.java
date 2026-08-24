package solutions.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Solves LeetCode 797, "All Paths From Source to Target".
 *
 * <p>The input is a directed acyclic graph whose nodes are numbered from {@code 0} to
 * {@code graph.length - 1}. This class returns every path from node {@code 0} to the
 * last node.
 *
 * <p>The search uses depth-first traversal with backtracking. A single mutable path is
 * reused during traversal, and a copy is stored only when the target is reached. Since
 * the result itself contains every path, the running time is output-sensitive. More
 * precisely, it is {@code O(Q + S)}, where {@code Q} is the number of explored path
 * prefixes and {@code S} is the total length of all returned paths. When every explored
 * branch reaches the target, this is the familiar {@code O(P * V)} bound for {@code P}
 * paths of maximum length {@code V}. The auxiliary recursion/path space is {@code O(V)},
 * excluding the output.
 *
 * @author BorisMirage
 * Time: 2026/08/23 23:09
 * Created with IntelliJ IDEA
 */

public class AllPathsSourceTarget_797 {
    /**
     * Returns all paths from node {@code 0} to node {@code graph.length - 1}.
     *
     * <p>The graph is guaranteed to be acyclic by the problem statement, so a visited
     * set is not required. The returned paths may be in any order.
     *
     * @param graph adjacency list representation of a directed acyclic graph
     * @return every path from the source node to the target node, or an empty list when
     * the graph is empty or the target is unreachable
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        if (graph.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> output = new ArrayList<>();
        dfs(0, new ArrayList<>(), output, graph);
        return output;
    }

    /**
     * Extends one candidate path from {@code current} and backtracks after exploring it.
     *
     * @param current current node being explored
     * @param path    mutable path shared by the recursive calls
     * @param output  destination for completed path copies
     * @param graph   adjacency list representation of the graph
     */
    private void dfs(int current, List<Integer> path, List<List<Integer>> output, int[][] graph) {
        path.add(current);

        if (current == graph.length - 1) {
            output.add(new ArrayList<>(path));
        } else {
            for (int next : graph[current]) {
                dfs(next, path, output, graph);
            }
        }

        // remove current node after all children are looped to avoid duplicated path
        path.removeLast();
    }
}

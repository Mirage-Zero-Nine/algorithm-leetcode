package Solution.UnionFind;

/**
 * Alice and Bob have an undirected graph of n nodes and 3 types of edges:
 * Type 1: Can be traversed by Alice only.
 * Type 2: Can be traversed by Bob only.
 * Type 3: Can by traversed by both Alice and Bob.
 * Given an array edges where edges[i] = [typei, ui, vi] represents a bidirectional edge of type between nodes.
 * Find the max number of edges can be removed, after removing the graph can still be fully traversed by Alice and Bob.
 * The graph is fully traversed by Alice and Bob if starting from any node, they can reach all other nodes.
 * Return the maximum number of edges you can remove, or return -1 if it's impossible for it to be fully traversed.
 *
 * @author BorisMirage
 * Time: 2020/10/10 10:22
 * Created with IntelliJ IDEA
 */

public class MaxNumEdgesToRemove_1579 {
    /**
     * Union find. When union two nodes and they have same root, then there is a non-critical edge connects two nodes.
     * Type 3 can be both accessed by A and B, hence, union the nodes that can be connected by type 3 first.
     * If there are two nodes have same root when union, there is a non-critical edge.
     * The number of non-critical edges are the max number of edges can be removed.
     * Then union all type 1 and 2 edges, keep counting the the non-critical edges.
     * Also, counting the critical edges when doing the union for type 1 and type 2.
     * If the number type 1 or type 2 critical edge are not equal to n - 1, then there are nodes not connected.
     *
     * @param n     number of nodes in graph
     * @param edges given edges with type and connection
     * @return maximum number of edges can be removed and still keep graph being fully traversable
     */
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int[] parent = new int[n + 1];
        int count1 = 0, count2 = 0, out = 0;
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] e : edges) {
            if (e[0] == 3) {
                if (!union(e[1], e[2], parent)) {
                    count1++;
                    count2++;
                } else {
                    out++;
                }
            }
        }
        int[] parent1 = parent.clone();
        for (int[] e : edges) {
            if (e[0] == 1) {
                if (!union(e[1], e[2], parent)) {
                    count1++;
                } else {
                    out++;
                }
            }
        }
        parent = parent1;       // exclude type 1 edge, since it can not be accessed by B
        for (int[] e : edges) {
            if (e[0] == 2) {
                if (!union(e[1], e[2], parent)) {
                    count2++;
                } else {
                    out++;
                }
            }
        }

        return (count1 == count2 && count2 == (n - 1)) ? out : -1;
    }

    /**
     * Union method.
     * It returns whether there exists non-critical edge between two nodes.
     * If two nodes have same root, then there is a non-critical edge between them.
     *
     * @param x      first node
     * @param y      second node
     * @param parent int array stores each node's parent
     * @return if there exists non-critical edge between two nodes
     */
    private boolean union(int x, int y, int[] parent) {
        int root1 = find(x, parent);
        int root2 = find(y, parent);

        if (root1 == root2) {
            return true;       // there are redundant edge can be removed
        }

        parent[root1] = root2;

        return false;        // no redundant edge
    }


    /**
     * Find the root of current node.
     *
     * @param node   given node
     * @param parent each node's parent
     * @return root of current node
     */
    private int find(int node, int[] parent) {
        parent[node] = node == parent[node] ? node : find(parent[node], parent);
        return parent[node];
    }
}

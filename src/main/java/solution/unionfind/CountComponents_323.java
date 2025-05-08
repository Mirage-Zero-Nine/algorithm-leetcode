package solution.unionfind;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes).
 * Write a function to find the number of connected components in an undirected graph.
 * Note:
 * 1. No duplicate edges will appear in edges.
 * 2. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 *
 * @author BorisMirage
 * Time: 2019/06/17 16:36
 * Created with IntelliJ IDEA
 */

public class CountComponents_323 {
    /**
     * Counts the number of connected components in an undirected graph using the Union-Find data structure.
     * A connected component is a subgraph where all nodes are connected to each other via some path.
     *
     * @param n     the number of nodes in the graph
     * @param edges a 2D array where each element is an edge represented by a pair of integers (u, v)
     * @return the number of connected components in the graph
     */
    public int countComponents(int n, int[][] edges) {

        // corner cases
        if (n == 0) {
            return 0;
        }
        if (edges == null || edges.length == 0 || edges[0].length == 0) {
            return n;
        }

        int[] root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }

        for (int[] edge : edges) {

            // find the root for both of the elements
            int root1 = find(edge[0], root), root2 = find(edge[1], root);

            // if they are not under the same root, connect them
            if (root1 != root2) {
                root[root1] = root[root2];
                n--;
            }
        }

        return n;
    }

    /**
     * Finds the root of a given node with path compression.
     * When the value in root array equals the current value, the deepest root is found for this value.
     *
     * @param i    the node whose root is to be found
     * @param root the parent array used for Union-Find operations
     * @return the root of the given value
     */
    private int find(int i, int[] root) {

        // the loop walks up the parent chain until it reaches the root (i == root[i]).
        while (i != root[i]) {
            // updates root[i] to point to its grandparent (root[root[i]]), until reaches the actual root
            root[i] = root[root[i]]; // compress path
            i = root[i];
        }

        return i;
    }
}

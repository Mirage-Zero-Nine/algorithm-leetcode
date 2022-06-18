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
     * Union find.
     * Initially, consider all nodes as individual nodes.
     * Then traverse all given edges. Union two vertices that connected by edge.
     *
     * @param n     # of nodes
     * @param edges edges connect nodes
     * @return # of connected components in an undirected graph
     */
    public int countComponents(int n, int[][] edges) {
        int[] union = new int[n];

        for (int i = 0; i < union.length; i++) {
            union[i] = i;       // count each node as individual node
        }
        for (int[] e : edges) {
            int r1 = find(e[0], union);
            int r2 = find(e[1], union);
            if (r1 != r2) {
                union[r1] = union[r2];      // union two nodes as connected
                n--;
            }
        }
        return n;
    }

    /**
     * Find operation.
     *
     * @param i   given node
     * @param arr array of union situation
     * @return root of current node
     */
    private int find(int i, int[] arr) {
        while (i != arr[i]) {
            arr[i] = arr[arr[i]];       // update root if current node is not root
            i = arr[i];
        }
        return i;
    }
}

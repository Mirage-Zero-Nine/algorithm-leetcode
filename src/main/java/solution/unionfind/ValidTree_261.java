package solution.unionfind;

import java.util.Arrays;

/**
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes).
 * Write a function to check whether these edges make up a valid tree.
 *
 * @author BorisMirage
 * Time: 2019/06/16 19:10
 * Created with IntelliJ IDEA
 */

public class ValidTree_261 {
    /**
     * Union find.
     * Find the newest son of nodes in each edge.
     * If two vertices in the same edge have the same newest son, then there is a loop in graph, which is invalid.
     * If they have different newest son, union the son together.
     *
     * @param n     n nodes
     * @param edges edges that connect vertices
     * @return whether these edges make up a valid tree
     */
    public boolean validTree(int n, int[][] edges) {

        /* Corner case */
        if (edges.length != n - 1) {
            return false;       // if edges are more than nodes - 1, then it will definitely create a loop
        }

        int[] root = new int[n];    // save connections of each node
        Arrays.fill(root, -1); // all nodes are independent at beginning

        for (int[] edge : edges) {

            int r1 = find(edge[0], root), r2 = find(edge[1], root);     // find their newest son
            if (r1 == r2) {     // if two nodes has same newest son, then the union operation will create a loop
                return false;
            }
            root[r1] = r2; // union two nodes
        }

        return true;
    }

    /**
     * Find newest son of given node.
     * If current node is -1 (initial value), then current node itself is the newest son.
     *
     * @param vertex current node
     * @param root   array store root of each node
     * @return newest son of node
     */
    private int find(int vertex, int[] root) {
        return (root[vertex] == -1) ? vertex : find(root[vertex], root);
    }

    public static void main(String[] args) {
        ValidTree_261 test = new ValidTree_261();
        System.out.println(test.validTree(5, new int[][]{{0, 1}, {0, 4}, {1, 4}, {2, 3}}));
        System.out.println(test.validTree(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}}));
    }
}

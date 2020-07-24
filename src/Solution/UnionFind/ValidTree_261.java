package Solution.UnionFind;

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
     * Find first, if the vertices have same newest son, then they will create a loop in graph, which is invalid.
     * Then, if two nodes have different newest son, union it to newest son.
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

        int[] connection = new int[n];        // save connections of each node
        Arrays.fill(connection, -1);      // all nodes are independent at beginning

        for (int[] edge : edges) {

            int r1 = find(edge[0], connection), r2 = find(edge[1], connection);     // find their newest son
            if (r1 == r2) {     // if two nodes has same newest son, then the union operation will create a loop
                return false;
            }
            connection[r1] = r2;        // union
        }
        return true;
    }

    /**
     * Find newest son of each node.
     * If current node is -1 (init value), then current node is newest son.
     *
     * @param i    current node
     * @param root array store root of each node
     * @return newest son of node
     */
    private int find(int i, int[] root) {

        return (root[i] == -1) ? i : find(root[i], root);
    }

    public static void main(String[] args) {
        ValidTree_261 test = new ValidTree_261();
        System.out.println(test.validTree(5, new int[][]{{0, 1}, {0, 4}, {1, 4}, {2, 3}}));
        System.out.println(test.validTree(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}}));
    }
}

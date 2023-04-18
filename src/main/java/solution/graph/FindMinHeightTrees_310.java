package solution.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * For an undirected graph with tree characteristics, we can choose any node as the root.
 * The result graph is then a rooted tree.
 * Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs).
 * Given such a graph, write a function to find all the MHTs and return a list of their root labels.
 * Format:
 * The graph contains n nodes which are labeled from 0 to n - 1.
 * You will be given the number n and a list of undirected edges (each edge is a pair of labels).
 * No duplicate edges will appear in edges.
 * Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 * Note:
 * 1. A tree is an undirected graph in which any two vertices are connected by exactly one path.
 * 2. The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 *
 * @author BorisMirage
 * Time: 2019/05/30 10:07
 * Created with IntelliJ IDEA
 */

public class FindMinHeightTrees_310 {
    /**
     * Find leaf node and remove all leafs, since leaf can not be the root node of MHT.
     * Keep find and remove leaf until there are less than 3 nodes in the tree (2 or 1).
     *
     * @param n     number of nodes
     * @param edges all edges in the tree
     * @return list of MHT root
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();

        /* Corner case */
        if (n < 3) {
            for (int i = 0; i < n; i++) {
                result.add(i);
            }
            return result;
        }

        int[] degree = new int[n];
        List<List<Integer>> graph = new ArrayList<>();      // save connections between vertex
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);      // add connection
            graph.get(e[1]).add(e[0]);      // add connection
            degree[e[0]]++;
            degree[e[1]]++;
        }

        Queue<Integer> leaf = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                leaf.add(i);        // Add leaf
            }
        }
        while (!leaf.isEmpty()) {
            int size = leaf.size();
            result = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int l = leaf.remove();      // get leaf node
                result.add(l);      // root of MHT must be a final layer of leaf
                degree[l]--;
                for (int j = 0; j < graph.get(l).size(); j++) {
                    int next = graph.get(l).get(j);     // find leaf's connection
                    if (degree[next] == 2) {
                        leaf.add(next);     // if leaf connect to node has only one connection, then it is next leaf
                    }
                    degree[next]--;
                }
            }
        }

        return result;
    }
}

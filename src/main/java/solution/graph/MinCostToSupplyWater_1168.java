package solution.graph;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.
 * For each house i, it can either build a well inside with cost wells[i], or pipe in water from another well to it.
 * The costs to lay pipes between houses are given by the array pipes.
 * Each pipes[i] = [house1, house2, cost] represents the cost to connect house1 and house2 together using a pipe.
 * Connections are bidirectional.
 * Find the minimum total cost to supply water to all houses.
 *
 * @author BorisMirage
 * Time: 2019/08/26 19:57
 * Created with IntelliJ IDEA
 */

public class MinCostToSupplyWater_1168 {
    /**
     * Add a "hidden house" that connect with each house with weight of cost for building well inside it.
     * Then find the minimum spanning tree in this graph.
     * Sort weight first, then use Kruskal's algorithm to find MST with union find to check if all nodes are connected.
     * Kruskal's algorithm: sort edges by weight first, then poll out min edge until all nodes are connected.
     *
     * @param n     n houses
     * @param wells cost of build a well inside house
     * @param pipes cost of connect with house and well with pipe, pipes[i] = [house1, house2, cost]
     * @return minimum total cost to supply water to all houses
     */
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        UnionFind uf = new UnionFind(n + 1);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

        for (int i = 0; i < n; i++) {
            pq.add(new int[]{0, i + 1, wells[i]});
        }

        pq.addAll(Arrays.asList(pipes));

        int total = 0;
        while (uf.count != 0 && !pq.isEmpty()) {
            int[] edge = pq.poll();

            if (uf.find(edge[0]) != uf.find(edge[1])) {     // if they are not connected into MST
                uf.union(edge[0], edge[1]);
                total += edge[2];
            }
        }

        return total;
    }

    /**
     * Union find.
     */
    static class UnionFind {
        int[] uf;
        int count;

        /**
         * Init of class.
         *
         * @param n # of houses
         */
        private UnionFind(int n) {
            uf = new int[n];
            for (int i = 1; i < n; i++) {
                uf[i] = i;
            }
            count = n;
        }

        /**
         * Find parent of given node.
         *
         * @param x given node
         * @return parent of given node
         */
        private int find(int x) {
            if (uf[x] == x) {
                return x;
            }

            uf[x] = find(uf[x]);

            return uf[x];
        }

        /**
         * Union two nodes.
         *
         * @param a first node
         * @param b second node
         */
        private void union(int a, int b) {
            int find1 = find(a);
            int find2 = find(b);

            if (find1 != find2) {
                uf[find1] = find2;
                count -= 1;
            }
        }
    }
}

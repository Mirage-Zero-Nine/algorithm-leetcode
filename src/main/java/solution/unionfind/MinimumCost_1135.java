package solution.unionfind;

import java.util.Arrays;
import java.util.Comparator;

/**
 * There are N cities numbered from 1 to N.
 * You are given connections, each with [city1, city2, cost] represents the cost to connect city1 and city2 together.
 * A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.
 * Return the minimum cost so that for every pair of cities, there exists a path of connections that connects those two cities together.
 * The cost is the sum of the connection costs used. If the task is impossible, return -1.
 *
 * @author BorisMirage
 * Time: 2019/08/14 15:04
 * Created with IntelliJ IDEA
 */

public class MinimumCost_1135 {

    /**
     * Union find.
     */
    static class UnionFind {
        int[] father;
        int count;

        /**
         * Init of class.
         *
         * @param n # of cities
         */
        public UnionFind(int n) {
            father = new int[n];
            for (int i = 0; i < father.length; i++) {
                father[i] = i;
            }
            count = n;
        }

        /**
         * Union two nodes in map.
         *
         * @param a first node
         * @param b second node
         */
        public void union(int a, int b) {
            int find1 = find(a);
            int find2 = find(b);

            if (find1 != find2) {
                father[find1] = find2;
                count--;
            }
        }

        /**
         * Find parent of given node.
         *
         * @param x given node
         * @return parent of given node
         */
        public int find(int x) {
            if (father[x] == x) {
                return x;
            }
            father[x] = find(father[x]);        // path compression

            return father[x];
        }
    }

    /**
     * Sort connections by distance first, then union each node with sorted distance.
     * If there is a node that not connected into graph, return -1.
     * Otherwise, return the min distance.
     *
     * @param N           given # of cities
     * @param connections connections between cities with cost
     * @return minimum cost for every pair of cities, there exists a path of connections connects two cities together
     */
    public int minimumCost(int N, int[][] connections) {
        Arrays.sort(connections, Comparator.comparingInt(o -> o[2]));     // sort connections between each city with min cost
        int min = 0;
        UnionFind uf = new UnionFind(N);

        for (int[] c : connections) {
            int a = c[0], b = c[1];
            if (uf.find(a) != uf.find(b)) {
                uf.union(a, b);
                min += c[2];
            }
        }

        return (uf.count == 1) ? -1 : min;
    }
}

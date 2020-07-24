package Solution.Heap;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M.
 * Each worker and bike is a 2D coordinate on this grid.
 * We assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and their assigned bike is minimized.
 * The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
 * Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.
 *
 * @author BorisMirage
 * Time: 2019/07/17 10:20
 * Created with IntelliJ IDEA
 */

public class AssignBikes_1066 {
    /**
     * Dijkstra's algorithm.
     *
     * @param workers given works array
     * @param bikes   given bikes array
     * @return minimum possible sum of Manhattan distances between each worker and their assigned bike
     */
    public int assignBikes(int[][] workers, int[][] bikes) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(1, (a, b) -> (a.totalDistance - b.totalDistance));
        Set<String> seen = new HashSet<>();
        pq.offer(new Pair(0, 0, 0));
        while (!pq.isEmpty()) {
            Pair curr = pq.poll();

            String key = curr.worker + " " + curr.mask;     // mark selected bikes

            if (seen.contains(key)) {
                continue;
            }
            seen.add(key);

            if (curr.worker == workers.length) {
                return curr.totalDistance;       // all workers have a bike
            }


            for (int j = 0; j < bikes.length; j++) {        // scan all bikes and create new nodes into the PQ for next worker
                if ((curr.mask & (1 << j)) == 0) {      // if current bike is not selected
                    pq.offer(new Pair(curr.worker + 1, curr.mask | (1 << j), curr.totalDistance + distance(bikes[j], workers[curr.worker])));
                }
            }
        }
        return -1;
    }

    /**
     * Calculate Manhattan distance for two points.
     *
     * @param b bike coord
     * @param w worker coord
     * @return Manhattan distance between bike and worker
     */
    private int distance(int[] b, int[] w) {
        return Math.abs(b[0] - w[0]) + Math.abs(b[1] - w[1]);
    }

    /**
     * Worker - selected bikes pair
     */
    class Pair {
        int worker;              // # of worker, from 0 to workers.length
        int mask;                // bikes have been selected
        int totalDistance;       // total totalDistance under current worker

        /**
         * @param w             # of worker
         * @param m             bikes have been selected
         * @param totalDistance // total totalDistance under current worker
         */
        private Pair(int w, int m, int totalDistance) {
            this.worker = w;
            this.mask = m;
            this.totalDistance = totalDistance;
        }
    }
}

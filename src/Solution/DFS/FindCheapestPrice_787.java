package Solution.DFS;

import java.util.*;

/**
 * There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.
 * Now given all the cities and flights, together with starting city src and the destination dst.
 * Your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.
 *
 * @author BorisMirage
 * Time: 2020/03/17 15:57
 * Created with IntelliJ IDEA
 */

public class FindCheapestPrice_787 {
    /**
     * BFS with priority queue.
     * Use a min heap to maintain cheapest price.
     * Each time, poll out the cheapest element from heap and find all available next stop.
     * Add all next available stop to the heap and keep this process, until destination is reached.
     *
     * @param n       n cities
     * @param flights flight lists with departure city and destination city and price
     * @param src     initially departure city
     * @param dst     destination city
     * @param k       at most k stop city
     * @return the cheapest price from src to dst with up to k stops, return -1 if no such path
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> m = new HashMap<>();

        for (int[] f : flights) {
            if (!m.containsKey(f[0])) {
                m.put(f[0], new ArrayList<>());
            }

            m.get(f[0]).add(new int[]{f[1], f[2]});     // src -> dest, cost
        }

        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        pq.add(new int[]{0, src, k + 1});     // current cost, departure, destination

        while (!pq.isEmpty()) {
            int[] currentMin = pq.poll();
            int cost = currentMin[0], departure = currentMin[1], remainingStop = currentMin[2];

            if (departure == dst) {
                return cost;
            }
            if (remainingStop > 0 && m.containsKey(departure)) {
                for (int[] f : m.get(departure)) {
                    int next = f[0], nextCost = f[1];
                    pq.add(new int[]{cost + nextCost, next, remainingStop - 1});
                }
            }
        }

        return -1;
    }

    /**
     * Naive DFS solution.
     *
     * @param n       n cities
     * @param flights flight lists with departure city and destination city and price
     * @param src     initially departure city
     * @param dst     destination city
     * @param k       at most k stop city
     * @return the cheapest price from src to dst with up to k stops, return -1 if no such path
     */
    public int findCheapestPriceDFS(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> m = new HashMap<>();

        for (int[] f : flights) {
            if (!m.containsKey(f[0])) {
                m.put(f[0], new ArrayList<>());
            }

            m.get(f[0]).add(new int[]{f[1], f[2]});     // src -> dest, cost
        }

        int[] minCost = new int[]{Integer.MAX_VALUE};

        dfs(m, src, dst, k + 1, 0, minCost);     // k stop means at most k middle city can reach

        return minCost[0] == Integer.MAX_VALUE ? -1 : minCost[0];
    }

    /**
     * DFS to find the cheapest price.
     *
     * @param m       graph with departure location and destination location and price
     * @param src     current departure city
     * @param dst     destination city
     * @param k       remaining stop
     * @param cost    current cost
     * @param minCost min cost
     */
    private void dfs(Map<Integer, List<int[]>> m, int src, int dst, int k, int cost, int[] minCost) {

        if (k < 0 || cost > minCost[0]) {
            return;
        }

        if (src == dst) {
            minCost[0] = cost;
            return;
        }

        if (!m.containsKey(src)) {
            return;
        }

        for (int[] f : m.get(src)) {
            if (cost + f[1] < minCost[0]) {
                dfs(m, f[0], dst, k - 1, cost + f[1], minCost);
            }
        }
    }
}

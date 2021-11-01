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
     * Use a min heap to maintain the cheapest price.
     * Each time, poll out the cheapest element from heap and find all available next stop.
     * Add all next available stop to the heap and keep this process, until destination is reached.
     * To avoid cycling in the graph, when current city was visited with fewer stops, skip it.
     *
     * @param n       n cities
     * @param flights flight lists with departure city and destination city and price
     * @param src     initially departure city
     * @param dst     destination city
     * @param k       at most k stop
     * @return the cheapest price from src to dst with up to k stops, return -1 if no such path
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> map = new HashMap<>(); // departure city -> list of destination and price

        for (int[] f : flights) {
            if (!map.containsKey(f[0])) {
                map.put(f[0], new ArrayList<>());
            }

            map.get(f[0]).add(new int[]{f[1], f[2]});     // src -> dest, cost
        }

        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        pq.add(new int[]{0, src, k + 1});     // current cost, departure, destination

        int[] stopsForCity = new int[n]; // save remaining available stops for each city
        Arrays.fill(stopsForCity, -1);

        while (!pq.isEmpty()) {
            int[] currentMin = pq.poll();
            int cost = currentMin[0], departure = currentMin[1], remainingStop = currentMin[2];

            if (departure == dst) {  // reaches the destination
                return cost;
            }

            /*
             * Modification of Dijkstra's algorithm: avoid cycling in graph.
             * If stopsForCity[departure] == -1, then this city was not visited before.
             * If stopsForCity[departure] >= remainingStop, it's actually a cycle.
             * The top of the priority queue is the lowest cost available at previous city.
             * If taking more steps but reached at same city, it's actually a cycle in the graph.
             * For example: src = 1, dst = 4
             * 1 -> 2: 100
             * 2 -> 3: 50
             * 3 -> 1: 10
             * 3 -> 4: 100
             * If the stops were not count, then it will loop in 1 -> 2 -> 3 -> 1, since 3 -> 1 cost 100, smaller than 400. */
            if (stopsForCity[departure] == -1 || stopsForCity[departure] < remainingStop) {
                stopsForCity[departure] = remainingStop;

                if (remainingStop > 0 && map.containsKey(departure)) {
                    for (int[] f : map.get(departure)) {
                        int next = f[0], nextCost = f[1];
                        pq.add(new int[]{cost + nextCost, next, remainingStop - 1});
                    }
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

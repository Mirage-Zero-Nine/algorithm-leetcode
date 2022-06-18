package solution.orderedmap;

import java.util.*;

/**
 * You have k servers numbered from 0 to k-1 that are being used to handle multiple requests simultaneously.
 * Each server has infinite computational capacity but cannot handle more than one request at a time.
 * The requests are assigned to servers according to a specific algorithm:
 * The ith (0-indexed) request arrives.
 * - If all servers are busy, the request is dropped (not handled at all).
 * - If the (i % k)th server is available, assign the request to that server.
 * - Otherwise, assign the request to the next available server (wrapping around the list of servers from 0).
 * - For example, if the ith server is busy, try to assign the request to the (i+1)th server, then the (i+2)th server, and so on.
 * - You are given a strictly increasing array arrival of positive integers.
 * - arrival[i] represents the arrival time of the ith request.
 * - load[i] represents the load of the ith request (the time it takes to complete).
 * Your goal is to find the busiest server(s).
 * A server is considered busiest if it handled the most number of requests successfully among all the servers.
 * Return a list containing the IDs (0-indexed) of the busiest server(s). You may return the IDs in any order.
 *
 * @author BorisMirage
 * Time: 2020/10/17 13:02
 * Created with IntelliJ IDEA
 */

public class BusiestServers_1606 {
    /**
     * Keep a int array to track the total number of requests that the ith server handled.
     * Use a TreeMap to store the servers at the number of requests.
     * Use a priority queue to save all the servers that are busy.
     * Use a TreeSet to store all the available servers.
     * Each time, find all the available servers in priority queue, if there is no available server, skip current round.
     * Then find if ceiling of i % k exist in available servers, if not, assign the first available server in set.
     * Remove the assigned server and add the server and new end time into priority queue.
     * Keep doing this until all request has been handled.
     *
     * @param k       k servers
     * @param arrival arrival time
     * @param load    each request's process time
     * @return a list containing the IDs (0-indexed) of the busiest server(s)
     */
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] requestCount = new int[k];
        TreeMap<Integer, HashSet<Integer>> map = new TreeMap<>((o1, o2) -> o2 - o1);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));  // server index, end time
        TreeSet<Integer> available = new TreeSet<>();

        map.put(0, new HashSet<>());

        for (int i = 0; i < k; i++) {
            available.add(i);
            map.get(0).add(i);
        }

        for (int i = 0; i < arrival.length; i++) {
            int current = arrival[i];
            int endTime = current + load[i];
            while (!pq.isEmpty() && pq.peek()[1] <= current) {      // find all available server in pq
                available.add(pq.poll()[0]);
            }
            if (available.size() != 0) {
                Integer next = available.ceiling(i % k);

                if (next == null) {
                    next = available.first();
                }

                pq.add(new int[]{next, endTime});       // add to busy server queue
                available.remove(next);                 // remove the used server
                map.get(requestCount[next]).remove(next);
                requestCount[next]++;
                HashSet<Integer> set = map.getOrDefault(requestCount[next], new HashSet<>());
                set.add(next);
                map.put(requestCount[next], set);
            }
        }

        return new ArrayList<>(map.firstEntry().getValue());
    }
}

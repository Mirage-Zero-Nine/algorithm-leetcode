package solutions.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 * You are given a network of n nodes, labeled from 1 to n.
 * You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi).
 * ui is the source node, vi is the target node, wi is the time it takes for a signal to travel from source to target.
 * We will send a signal from a given node k.
 * Return the minimum time it takes for all the n nodes to receive the signal.
 * If it is impossible for all the n nodes to receive the signal, return -1.
 *
 * @author BorisMirage
 * Time: 2026/10/21 23:16
 * Created with IntelliJ IDEA
 */

public class NetworkDelayTime_743 {
    /**
     * Uses Dijkstra's algorithm with an adjacency list and a min-heap of node/distance pairs.
     * The distance array stores the best arrival time found so far; the heap expands the smallest
     * candidate first. Once all reachable nodes are processed, the largest shortest distance is
     * the delay, or an unreachable node makes the result -1. Edge weights must be nonnegative.
     * The input is not modified, and no state is retained between calls. As an extension,
     * null or empty edge lists return -1, including when n is 1.
     * <p>
     * Time complexity: O(n + E log(E + 1)), where E is the number of edges.
     * Auxiliary space: O(n + E), including the adjacency list and heap entries.
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        if (times == null || times.length == 0) {
            return -1;
        }
        if (n == 1) {
            return 0;
        }

        // Look up a source node's outgoing connections when expanding it; each entry is [destination, travel time].
        Map<Integer, List<int[]>> outgoingEdges = new HashMap<>();
        for (int[] time : times) {
            outgoingEdges.computeIfAbsent(time[0], _ -> new ArrayList<>()).add(new int[]{time[1], time[2]});
        }

        // Node labels start at 1; index 0 is unused and excluded from the final result.
        int[] distances = IntStream.rangeClosed(0, n).map(i -> i == k ? 0 : Integer.MAX_VALUE).toArray();
        // Entries are [node, total time from k]; prioritize the closest candidate path for expansion.
        Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        minHeap.add(new int[]{k, 0});

        while (!minHeap.isEmpty()) {
            int currentNode = minHeap.peek()[0], currentDistance = minHeap.poll()[1];
            // A node can be queued again when a shorter route is found.
            // Process an entry only if its time still matches the best known time for that node.
            if (currentDistance <= distances[currentNode]) {
                for (int[] edge : outgoingEdges.getOrDefault(currentNode, Collections.emptyList())) {
                    int nextNode = edge[0], edgeWeight = edge[1], nextDistance = currentDistance + edgeWeight;
                    // Strict improvement prevents equal routes and zero-weight cycles from requeuing forever.
                    if (nextDistance < distances[nextNode]) {
                        distances[nextNode] = nextDistance;
                        minHeap.add(new int[]{nextNode, nextDistance});
                    }
                }
            }
        }

        // The last receiver determines the delay; any infinite distance means not everyone is reached.
        return Arrays.stream(distances, 1, n + 1)
                .anyMatch(distance -> distance == Integer.MAX_VALUE) ? -1
                : Arrays.stream(distances, 1, n + 1).max().orElse(-1);
    }

    /**
     * Uses Dijkstra's algorithm with an adjacency list, a shortest-distance array, and a flag
     * marking nodes whose shortest arrival times are final. Each round scans the remaining nodes
     * for the smallest distance, fixes that arrival time, and updates neighbors reached sooner
     * through it. The largest final arrival time is the delay; if the scan cannot select a
     * reachable node, at least one receiver is unreachable.
     * Edge weights must be nonnegative. Scanning avoids heap overhead on dense graphs.
     * The input is not modified, and no state is retained between calls. As an extension,
     * null or empty edge lists return -1, including when n is 1.
     * <p>
     * Time complexity: O(n^2 + E), where E is the number of edges.
     * Auxiliary space: O(n + E), including the adjacency list.
     */
    public int networkDelayTimeLinearScan(int[][] times, int n, int k) {
        if (times == null || times.length == 0) {
            return -1;
        }

        // Look up a source node's outgoing connections when expanding it; each entry is [destination, travel time].
        Map<Integer, List<int[]>> outgoingEdges = new HashMap<>();
        for (int[] time : times) {
            outgoingEdges.computeIfAbsent(time[0], _ -> new ArrayList<>()).add(new int[]{time[1], time[2]});
        }

        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[k] = 0;
        boolean[] settled = new boolean[n + 1];
        int delay = 0;

        for (int count = 0; count < n; count++) {
            int currentNode = -1;
            for (int node = 1; node <= n; node++) {
                if (!settled[node] && (currentNode == -1 || distances[node] < distances[currentNode])) {
                    currentNode = node;
                }
            }

            // Every remaining node is unreachable. Return before adding to the infinite sentinel.
            if (currentNode == -1 || distances[currentNode] == Integer.MAX_VALUE) {
                return -1;
            }

            // With nonnegative weights, a route through another unprocessed node cannot arrive earlier.
            // Marking one new node per round also guarantees progress through zero-weight cycles.
            settled[currentNode] = true;
            delay = Math.max(delay, distances[currentNode]);
            for (int[] edge : outgoingEdges.getOrDefault(currentNode, Collections.emptyList())) {
                int nextNode = edge[0];
                if (!settled[nextNode]) {
                    distances[nextNode] = Math.min(distances[nextNode], distances[currentNode] + edge[1]);
                }
            }
        }
        return delay;
    }
}

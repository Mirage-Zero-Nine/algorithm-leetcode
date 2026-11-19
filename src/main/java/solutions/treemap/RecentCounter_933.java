package solutions.treemap;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * You have a RecentCounter class which counts the number of recent requests within a certain time frame.
 * Implement the RecentCounter class:
 * RecentCounter() Initializes the counter with zero recent requests.
 * int ping(int t) Adds a new request at time t, and returns the number of requests that have happened in the inclusive range [t - 3000, t].
 * It is guaranteed that every call to ping uses a strictly larger value of t than the previous call.
 *
 * @author BorisMirage
 * Time: 2026/11/18 21:21
 * Created with IntelliJ IDEA
 */

public class RecentCounter_933 {
    private final Deque<Integer> queue;

    /**
     * Creates an empty counter. The chronological deque keeps the oldest request at the front,
     * so expired entries can be removed without scanning newer ones; its retained capacity follows
     * the peak window population {@code W}, at most 3001.
     *
     * <p>Time complexity: O(1).<br>
     * Auxiliary space complexity: O(1) initially and O(W) retained for peak population {@code W}.
     */
    public RecentCounter_933() {
        queue = new ArrayDeque<>();
    }

    /**
     * Appends the request, removes expired times, and returns the count in the inclusive window.
     *
     * <p>Time complexity: O(w) worst case and O(1) amortized, where {@code w} is the deque size
     * including the new request before expiry removal.
     * <br>Auxiliary space complexity: O(1) normally; O(w) temporarily if deque growth copies its entries.
     *
     * @param t request time in [1, 1,000,000,000], strictly greater than the previous time
     * @return the number of requests with times in [t - 3000, t]
     */
    public int ping(int t) {
        queue.offer(t);
        int lowerBound = t - 3000;
        // The queue is chronological, so removing expired entries from its front preserves every
        // request at or after the inclusive lower bound without examining newer timestamps.
        while (!queue.isEmpty() && queue.peek() < lowerBound) {
            queue.poll();
        }
        return queue.size();
    }
}

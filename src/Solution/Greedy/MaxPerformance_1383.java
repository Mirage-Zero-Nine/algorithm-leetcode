package Solution.Greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * There are n engineers and two arrays: speed and efficiency.
 * speed[i] and efficiency[i] represent the speed and efficiency for the i-th engineer respectively.
 * Return the maximum performance of a team composed of at most k engineers.
 * Since the answer can be a huge number, return this modulo 10^9 + 7.
 * The performance of a team is the sum of engineers' speeds multiplied by the minimum efficiency among their engineers.
 *
 * @author BorisMirage
 * Time: 2020/03/16 19:43
 * Created with IntelliJ IDEA
 */

public class MaxPerformance_1383 {
    /**
     * Greedy.
     * Sort the engineers by largest efficiency first.
     * Then use a priority queue with size k to find k engineers with top speed by traversing the sorted array.
     * When adding engineers to heap, since array is sorted by efficiency, the min efficiency is the current engineer.
     * Each time, compare current speed sum in heap and multiply to current min efficiency, find max from them.
     *
     * @param n          n engineers
     * @param speed      speed of each engineer
     * @param efficiency efficiency of each engineer
     * @param k          at most k engineer can select
     * @return maximum performance of a team composed of at most k engineers
     */
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] pair = new int[n][2];

        for (int i = 0; i < n; i++) {
            pair[i] = new int[]{speed[i], efficiency[i]};
        }

        Arrays.sort(pair, (a, b) -> b[1] - a[1]);               // sorted by efficiency

        PriorityQueue<Integer> pq = new PriorityQueue<>(k);     // min heap to compare speed
        long totalSpeed = 0, out = 0;

        for (int[] p : pair) {
            pq.add(p[0]);
            totalSpeed += p[0];
            if (pq.size() > k) {
                totalSpeed -= pq.poll();
            }
            out = Math.max(out, totalSpeed * p[1]);     // compare current total speed and efficiency, find max
        }

        return (int) (out % (long) (1e9 + 7));
    }
}

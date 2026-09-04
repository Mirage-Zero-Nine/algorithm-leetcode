package solutions.heap;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * Note:
 * 1. k is always valid, 1 ≤ k ≤ number of unique elements.
 * 2. Time complexity must be better than O(n log n), where n is the array's size.
 *
 * @author BorisMirage
 * Time: 2019/06/24 16:05
 * Created with IntelliJ IDEA
 */

public class TopKFrequent_347 {
    /**
     * Finds the {@code k} most frequent values with a min-heap limited to {@code k} entries.
     *
     * <p>A min-heap is chosen rather than a max-heap because its root is the least frequent of
     * the currently selected values. When a new candidate causes the heap to exceed {@code k},
     * that root is the exact entry to discard. This keeps only {@code k} candidates. A max-heap
     * exposes the most frequent entry instead, so it normally needs to retain all {@code u}
     * distinct values before removing the top {@code k} entries. The result order is
     * unspecified.</p>
     *
     * <p>Time complexity: {@code O(n + u log k)}. Space complexity: {@code O(u + k)}, where
     * {@code n} is {@code nums.length} and {@code u} is the number of distinct values.</p>
     *
     * @param nums the values whose frequencies are examined
     * @param k    the number of most-frequent values to return; the problem guarantees it is valid
     * @return the {@code k} most frequent values, or an empty array for the explicitly handled
     * invalid inputs
     */
    public int[] topKFrequent(int[] nums, int k) {
        // corner case
        if (nums == null || nums.length == 0 || k > nums.length) {
            return new int[0];
        }

        // Count each distinct value before selecting the k largest counts.
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(nums).forEach(n -> map.put(n, map.getOrDefault(n, 0) + 1));

        // Use a min-heap (not a max-heap): its root is the least frequent retained value and is
        // therefore the exact candidate to discard when the heap grows beyond k.
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        map.entrySet().forEach(e -> {
            pq.add(e);
            if (pq.size() > k) {
                pq.poll();
            }
        });

        return pq.stream().mapToInt(Map.Entry::getKey).toArray();
    }

    /**
     * Finds the {@code k} most frequent values by grouping values into frequency buckets.
     *
     * <p>Bucket {@code i} stores values that occur {@code i + 1} times. Traversing the buckets
     * from highest to lowest frequency yields the requested values. The result order is
     * unspecified for values with equal frequency.</p>
     *
     * <p>Time complexity: {@code O(n)}. Space complexity: {@code O(n)}, where {@code n} is
     * {@code nums.length}.</p>
     *
     * @param nums the values whose frequencies are examined
     * @param k    the number of most-frequent values to return; the problem guarantees it is valid
     * @return the {@code k} most frequent values, or an empty array for the explicitly handled
     * invalid inputs
     */
    @SuppressWarnings("DataFlowIssue")
    public int[] topKFrequentBucketSort(int[] nums, int k) {
        // corner case
        if (nums == null || nums.length == 0 || k > nums.length) {
            return new int[0];
        }

        // Count each distinct value so it can be placed into its frequency bucket.
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(nums).forEach(n -> map.put(n, map.getOrDefault(n, 0) + 1));

        // Index i represents frequency i + 1; all possible frequencies are in [1, nums.length].
        List<ArrayDeque<Integer>> frequency = IntStream.range(0, nums.length)
                .mapToObj(_ -> new ArrayDeque<Integer>())
                .toList();
        map.forEach((key, value) -> frequency.get(value - 1).add(key));

        int[] output = new int[k];
        int index = 0, currentList = frequency.size() - 1;

        // Drain the non-empty buckets from highest to lowest frequency until k values are found.
        while (k > 0) {
            while (frequency.get(currentList).isEmpty()) {
                currentList--;
            }
            while (!frequency.get(currentList).isEmpty() && k-- > 0) {
                output[index++] = frequency.get(currentList).poll();
            }
        }
        return output;
    }
}

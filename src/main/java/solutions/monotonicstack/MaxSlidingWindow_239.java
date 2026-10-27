package solutions.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an array nums, there is a sliding window of size k moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * Return the max sliding window.
 *
 * @author BorisMirage
 * Time: 2019/08/21 21:50
 * Created with IntelliJ IDEA
 */

public class MaxSlidingWindow_239 {
    /**
     * Uses a deque, a queue that allows adding or removing at either end, to keep candidate
     * indices from oldest to newest, with values that never increase from front to back. The
     * front gives the current maximum. For each index {@code i}, remove expired front indices
     * before {@code i - k + 1}, remove smaller back values, then append {@code i}. The deque starts
     * empty, and a result is written once the first full window ends at {@code i = k - 1}. Each
     * index is added once and removed at most once, so the scan takes
     * {@code O(n)} time and {@code O(k)} auxiliary space for an input of length {@code n}; the
     * returned array uses {@code O(n - k + 1)} output space. The input is not modified, and each
     * call has independent local state. A null array or a window size outside {@code 1..n}
     * returns an empty array.
     *
     * @param nums values to scan
     * @param k size of each window
     * @return the maximum value for each window, or an empty array for invalid input
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // Return no windows when the input or requested window size cannot describe a valid window.
        if (nums == null || nums.length < k || k <= 0) {
            return new int[0];
        }

        Deque<Integer> q = new ArrayDeque<>();
        int[] output = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            // An index j expires when i - j + 1 > k; equality means it still belongs to this window.
            while (!q.isEmpty() && i - q.peek() + 1 > k) {
                q.poll();
            }
            // A newer, larger value stays in the window longer, so smaller tail values can never win.
            // Keep equal values; their indices let them expire separately.
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.pollLast();
            }
            q.add(i);
            // Before i reaches k - 1, the input seen so far is not yet a complete window.
            if (!q.isEmpty() && i - k + 1 >= 0) {
                output[i - k + 1] = nums[q.peek()];
            }
        }
        return output;
    }
}

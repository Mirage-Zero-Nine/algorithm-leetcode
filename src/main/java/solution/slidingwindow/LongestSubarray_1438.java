package solution.slidingwindow;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an array of integers nums and an integer limit.
 * Return the size of the longest continuous subarray that the absolute difference between any two elements <= limit.
 * In case there is no subarray satisfying the given condition return 0.
 *
 * @author BorisMirage
 * Time: 2020/05/06 19:58
 * Created with IntelliJ IDEA
 */

public class LongestSubarray_1438 {
    /**
     * Sliding window.
     * The limit defined by absolute difference in subarray.
     * Hence, keep a deque to store subarray's max value, and a min deque to store the min value in subarray.
     * The window will shrink only when absolute difference is larger than difference.
     * Update window by moving the left pointer of window, and update the deque if min or max value is reached.
     *
     * @param nums  given array
     * @param limit limit of absolute difference between any two elements in result
     * @return size of the longest continuous subarray that the absolute difference between any two elements <= limit
     */
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> maxDeque = new LinkedList<>();
        Deque<Integer> minDeque = new LinkedList<>();

        int max = 0, left = 0;

        for (int i = 0; i < nums.length; ++i) {

            while (!maxDeque.isEmpty() && maxDeque.peekLast() < nums[i]) {      // update max deque with current value
                maxDeque.removeLast();
            }
            maxDeque.addLast(nums[i]);

            while (!minDeque.isEmpty() && minDeque.peekLast() > nums[i]) {      // update min deque with current value
                minDeque.removeLast();
            }
            minDeque.addLast(nums[i]);

            while (!maxDeque.isEmpty() && !minDeque.isEmpty() && maxDeque.peekFirst() - minDeque.peekFirst() > limit) {       // update window if exceed the limit
                if (maxDeque.peekFirst() == nums[left]) {       // if current value is max value in subarray
                    maxDeque.pollFirst();                       // poll the value from deque
                }
                if (!minDeque.isEmpty() && minDeque.peekFirst() == nums[left]) {       // if current value is min value in subarray
                    minDeque.pollFirst();                       // poll the value from deque
                }
                left++;  // shrink window
            }

            max = Math.max(max, i - left + 1);
        }

        return max;
    }
}

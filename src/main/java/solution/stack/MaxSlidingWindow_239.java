package solution.stack;

import java.util.Deque;
import java.util.LinkedList;

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
     * Use a deque to save the index of largest value under current sliding window.
     * Largest is saved in head of queue.
     * Each time, if head of queue is out of sliding window, remove it.
     * Remove all elements starts from end of deque that is smaller than current value, and add it to the end of queue.
     * Find value based on index stores in head of queue to obtain current sliding window max.
     *
     * @param nums given nums
     * @param k    size of sliding window
     * @return all max value in each sliding window
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        /* Corner case */
        if (n == 0) {
            return nums;
        }

        Deque<Integer> q = new LinkedList<>();
        int[] out = new int[n - k + 1];

        for (int i = 0; i < n; i++) {

            if (!q.isEmpty() && i - q.peek() + 1 > k) {
                q.poll();
            }

            while (!q.isEmpty() && nums[i] >= nums[q.peekLast()]) {
                q.pollLast();
            }

            q.offer(i);
            if (!q.isEmpty() && i - k + 1 >= 0) {
                out[i - k + 1] = nums[q.peek()];
            }
        }

        return out;
    }
}

package Solution.SlidingWindow;

/**
 * Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
 * Return the length of the longest (contiguous) subarray that contains only 1s.
 *
 * @author BorisMirage
 * Time: 2019/10/22 23:09
 * Created with IntelliJ IDEA
 */

public class LongestOnes_1004 {
    /**
     * Sliding window problem.
     * Each time, if current window contains more than k 0, then shrink the window until there is only at most k 0.
     *
     * @param nums given array
     * @param k    change up to K values from 0 to 1
     * @return maximum number of consecutive 1s in this array if you can flip at most one 0
     */
    public int longestOnes(int[] nums, int k) {
        int left = 0, n = nums.length, max = 0, count = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] != 1) {
                count++;
            }
            while (count > k) {
                if (nums[left] != 1) {
                    count--;
                }
                left++;
            }
            max = Math.max(i - left + 1, max);
        }

        return max;
    }
}

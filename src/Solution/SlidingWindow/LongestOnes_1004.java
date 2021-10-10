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
     * Each time, if current window contains more than k zeros, then shrink it until there is only at most k zeros.
     *
     * @param nums given array
     * @param k    change up to K values from 0 to 1
     * @return maximum number of consecutive 1s in this array if you can flip at most one 0
     */
    public int longestOnes(int[] nums, int k) {
        int start = 0, n = nums.length, max = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] != 1) {
                k--;
            }
            while (k < 0) {
                if (nums[start] != 1) {
                    k++;
                }
                start++;
            }
            max = Math.max(i - start + 1, max);
        }

        return max;
    }
}

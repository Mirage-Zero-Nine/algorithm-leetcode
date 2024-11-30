package solution.slidingwindow;

import java.util.List;

/**
 * You are given an integer array nums and two integers l and r.
 * Find the minimum sum of a subarray whose size is between l and r (inclusive) and whose sum is greater than 0.
 * Return the minimum sum of such a subarray. If no such subarray exists, return -1.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * @author BorisMirage
 * Time: 2024/11/29 11:47
 * Created with IntelliJ IDEA
 */

public class MinimumSumSubarray {
    /**
     * For every window size from l to r, loop the array to find the minimum sum with current window size.
     *
     * @param nums given array
     * @param l    min window size
     * @param r    max window size
     * @return minimum sum of such a subarray that's larger than 0
     * @see LengthOfLongestSubstring_3
     * @see MinWindow_76
     * @see MinSubArrayLen_209
     */
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {

        // corner cases
        if (nums.isEmpty() || l < 1 || r < 1 || r > nums.size() || l > r) {
            return -1;
        }

        int minSum = Integer.MAX_VALUE;

        // different window size
        for (int window = l; window <= r; window++) {
            int currentSum = 0;

            // calculate the initial window sum
            // also can be replaced by prefix sum array
            for (int i = 0; i < window - 1; i++) {
                currentSum += nums.get(i);
            }

            // finding min sum within the window, shrink the window by subtracting the first element of the window
            for (int i = window - 1; i < nums.size(); i++) {
                currentSum += nums.get(i);
                if (currentSum > 0) {
                    minSum = Math.min(minSum, currentSum);
                }
                currentSum -= nums.get(i - window + 1);
            }
        }

        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}

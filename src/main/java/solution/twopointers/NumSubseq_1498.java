package solution.twopointers;

import java.util.Arrays;

/**
 * Given an array of integers nums and an integer target.
 * Return the number of non-empty subsequences of nums such that the sum of the min and max value <= target.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * @author BorisMirage
 * Time: 2020/06/28 15:34
 * Created with IntelliJ IDEA
 */

public class NumSubseq_1498 {
    /**
     * Two pointers. Sort the array first.
     * For each subarray start from left to right, the subarray can be selected if under the condition.
     * If meet the condition, move the left point. If not, move the right pointer.
     *
     * @param nums   given array
     * @param target target value
     * @return the number of non-empty subsequences of nums such that the sum of the min and max value <= target
     */
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);

        int n = nums.length, left = 0, right = n - 1, out = 0, mod = (int) 1e9 + 7;
        int[] powers = new int[n];
        powers[0] = 1;

        for (int i = 1; i < n; i++) {
            powers[i] = powers[i - 1] * 2 % mod;        // number of subsequence with length of n: 2^(n - 1)
        }

        while (left <= right) {
            if (nums[left] + nums[right] > target) {
                right--;
            } else {  // nums[left] + nums[right] <= target: the largest value meets condition
                out = (out + powers[right - left++]) % mod;
            }
        }

        return out;
    }
}

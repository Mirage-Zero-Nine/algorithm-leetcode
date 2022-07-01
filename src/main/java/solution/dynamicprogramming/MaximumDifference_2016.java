package solution.dynamicprogramming;

/**
 * Given a 0-indexed integer array nums of size n, find the maximum difference that 0 <= i < j < n and nums[i] < nums[j].
 * Return the maximum difference. If no such i and j exists, return -1.
 *
 * @author BorisMirage
 * Time: 2022/06/30 20:37
 * Created with IntelliJ IDEA
 */

public class MaximumDifference_2016 {
    /**
     * Same as best time to buy and sell stock.
     *
     * @param nums given array
     * @return maximum difference that 0 <= i < j < n and nums[i] < nums[j]
     */
    public int maximumDifference(int[] nums) {

        /* Corner case */
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int max = -1, min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i] - min);
            min = Math.min(nums[i], min);
        }

        return max == 0 ? -1 : max;
    }
}

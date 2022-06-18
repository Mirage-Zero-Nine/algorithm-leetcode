package solution.dynamicprogramming;

/**
 * Given a list of non-negative integers array.
 * Note that this time, first element and last element is considered as "adjacent".
 * Determine the maximum sum, that each element consist the sum is not adjacent in given array.
 *
 * @author BorisMirage
 * Time: 2019/02/27 23:24
 * Created with IntelliJ IDEA
 */

public class Rob_213 {
    /**
     * Dynamic programming.
     * If first element and last element is considered as "adjacent", then find max(profit(0, l - 2), profit(1, l - 1)).
     * Where l is the length of array.
     * The state transition keeps same.
     * dp(i, j) = max(dp(i, j - 1), dp(i + 1, j))
     *
     * @param nums given array
     * @return maximum sum that is made up by nonadjacent elements
     */
    public int rob(int[] nums) {

        /* Corner case */
        if (nums.length < 2) {
            return nums.length == 1 ? nums[0] : 0;
        }

        int[] dp = new int[nums.length], dp1 = new int[nums.length];

        dp[0] = nums[0];
        dp1[1] = nums[1];

        for (int i = 1; i < nums.length - 1; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + (i - 2 >= 0 ? dp[i - 2] : 0));        // iter array without last element
            dp1[i + 1] = Math.max(dp1[i], nums[i + 1] + dp1[i - 1]);        // iter array without first element
        }

        return Math.max(dp[nums.length - 2], dp1[nums.length - 1]);
    }
}

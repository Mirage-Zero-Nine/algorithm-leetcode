package solution.dynamicprogramming;

/**
 * Given an integer array with all positive numbers and no duplicates.
 * Find the number of possible combinations that add up to a positive integer target.
 *
 * @author BorisMirage
 * Time: 2019/08/19 18:09
 * Created with IntelliJ IDEA
 */

public class CombinationSum4_377 {
    /**
     * This is not a combination problem. This is a dynamic programming problem.
     * State transition:
     * dp[i] = dp[i] + dp[i - nums[j]], iff i - nums[j] >= 0, and j is from 0 to nums.length
     *
     * @param nums   given int array
     * @param target target number
     * @return number of possible combinations that add up to a positive integer target
     */
    public int combinationSum4(int[] nums, int target) {

        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 1; i < dp.length; i++) {
            for (int n : nums) {
                if (i - n >= 0) {
                    dp[i] += dp[i - n];
                }
            }
        }

        return dp[target];
    }
}

package Solution.DynamicProgramming;

/**
 * Given a non-empty array containing only positive integers.
 * Find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 * @author BorisMirage
 * Time: 2019/08/20 22:18
 * Created with IntelliJ IDEA
 */

public class CanPartition_416 {
    /**
     * 0/1 Knapsack problem.
     * State transition:
     * dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j]
     * dp[i - 1][j - nums[i - 1]]: choose nums[i], and if j - nums[i] can make up sum, dp[i][j] can.
     * dp[i - 1][j]: leave nums[i] and if from nums[0] to nums[i-1] can make up, then dp[i][j] can.
     *
     * @param nums given array
     * @return if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal
     */
    public boolean canPartition(int[] nums) {

        /* Corner case */
        if (nums.length < 2) {
            return false;
        }

        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 == 1) {
            return false;       // odd number can not be spilt to equal parts
        }

        sum /= 2;
        boolean[][] dp = new boolean[nums.length + 1][sum + 1];     // dp[i][j]: if 0 to i in nums can reach sum of j

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {

                if (j - nums[i - 1] >= 0) {     // choose current nums[i]
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];
                }
            }
        }

        return dp[nums.length][sum];
    }
}

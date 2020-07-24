package Solution.DynamicProgramming;

/**
 * It takes n steps from bottom to reach to the top.
 * Each time climb 1 or 2 steps can be made.
 * Find how many distinct ways to reach the top.
 * Note: Given n will be a positive integer.
 *
 * @author BorisMirage
 * Time: 2018/08/07 15:23
 * Created with IntelliJ IDEA
 */

public class ClimbStairs_70 {
    /**
     * Dynamic programming.
     * Create a new array that store current n length paths.
     * arr[i] = arr[i - 1] + arr[i - 2]
     *
     * @param n n steps to reach the top
     * @return num of paths
     */
    public int climbStairs(int n) {

        /* Corner case */
        if (n < 3) {
            return n;
        }

        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n - 1];
    }
}

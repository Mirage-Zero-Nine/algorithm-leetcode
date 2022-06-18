package solution.dynamicprogramming;

/**
 * There is a fence with n posts, each post can be painted with one of the k colors.
 * You have to paint all the posts such that no more than two adjacent fence posts have the same color.
 * Return the total number of ways you can paint the fence.
 *
 * @author BorisMirage
 * Time: 2020/03/24 14:15
 * Created with IntelliJ IDEA
 */

public class NumWays_276 {
    /**
     * Basic dynamic programming.
     * At most two posts can be painted at same color.
     * Hence, the number of ways to paint ith post is based on number of ways to with same color and different color.
     * Ways to paint different color is obvious, dp[i - 1] * (k - 1).
     * To paint same color, it should be guarantee that no three consecutive post with same color.
     * Therefore, it is actually the number of ways of painting (i - 1)th post with different color from (i - 2)th post.
     * State transition:
     * dp[i] = dp[i - 1] * (k - 1) + dp[i - 2] * (k - 1)
     * dp[i - 1] * (k - 1): paint ith post with different color from the (i - 1)th post
     * dp[i - 2] * (k - 1): paint (i - 1)th post with different color from the (i - 2)th post
     * Base cases:
     * dp[0] = 0;
     * dp[1] = k;
     * dp[2] = k * k;
     *
     * @param n n posts
     * @param k k colors
     * @return the total number of ways you can paint the fence
     */
    public int numWays(int n, int k) {

        /* Corner case */
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return k;
        }
        if (n == 2) {
            return k * k;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = k;
        dp[2] = k * k;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) * (k - 1);
        }

        return dp[n];
    }
}

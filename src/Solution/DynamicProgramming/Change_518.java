package Solution.DynamicProgramming;

/**
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that amount.
 * You may assume that you have infinite number of each kind of coin.
 *
 * @author BorisMirage
 * Time: 2019/08/18 20:04
 * Created with IntelliJ IDEA
 */

public class Change_518 {
    /**
     * Knapsack problem.
     * dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]], if j - coins[i - 1] >= 0.
     * dp[i - 1][j]: do not choose ith coin
     * dp[i][j - coins[i - 1]]: choose ith coin reaches amount reduce ith coin
     * if j - coins[i - 1]<0, dp[i][j - coins[i - 1]] = 0.
     *
     * @param amount make up amount
     * @param coins  total coins
     * @return number of combinations that make up that amount
     */
    public int change(int amount, int[] coins) {

        /*
         * i -> from 0 to ith coins
         * j -> from 0 to j amount to be made up */
        int[][] dp = new int[coins.length + 1][amount + 1];

        dp[0][0] = 1;

        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = 1;
            for (int j = 1; j < dp[0].length; j++) {
                /*
                 * dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]], if j-coins[i-1] >= 0.
                 * dp[i-1][j]: do not choose ith coin
                 * dp[i][j-coins[i-1]]: choose ith coin reaches amount reduce ith coin
                 * if j-coins[i-1]<0, dp[i][j-coins[i-1]]=0 */
                dp[i][j] = dp[i - 1][j] + ((j - coins[i - 1] >= 0) ? dp[i][j - coins[i - 1]] : 0);
            }
        }

        return dp[coins.length][amount];
    }
}

package Solution.DynamicProgramming;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * Note: You may not engage in multiple transactions at the same time
 * (i.e., you must sell the stock before you buy again).
 *
 * @author BorisMirage
 * Time: 2019/01/22 21:14
 * Created with IntelliJ IDEA
 */
public class MaxProfit_123 {
    /**
     * Dynamic programming.
     * The general state transition to solve this kind of problem is as following:
     * dp[i, j] = max(dp[i, j - 1], prices[j] - prices[j'] + dp[i - 1, j'])
     * i: ith transaction (in this case, i <= 2)
     * j: prices[j], index in int array price
     * j': in range of [0, j-1]
     * => [i, j] = max([i, j - 1], prices[j] + max([i - 1, j'] - prices[j']))  (efficient for looping)
     * Base case:
     * dp[0, j] = 0
     * dp[i, 0] = 0
     *
     * @param prices given price array
     * @return maximum profit based on two transactions
     */
    public int maxProfit(int[] prices) {

        /* Corner case */
        if (prices.length < 2) {
            return 0;
        }

        int n = prices.length;
        int[][] dp = new int[3][n];

        for (int k = 1; k < 3; k++) {
            int min = prices[0];
            for (int i = 1; i < n; i++) {
                min = Math.min(min, prices[i] - dp[k - 1][i - 1]);
                dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min);
            }
        }

        return dp[2][n - 1];
    }

    /**
     * Use a state machine to describes the problem statement.
     * State 0: do nothing or buy stock
     * State 1: sell stock or hold purchased stock
     * State 2: do nothing or buy stock again
     * State 3: sell stock or do nothing
     * Finally, return the final state s3. If no purchase then it will directly return -price[0].
     * If final result is negative, then return 0.
     *
     * @param prices price int array
     * @return max profit
     */
    public int stateMachine(int[] prices) {

        /* Corner case */
        if (prices.length == 0) {
            return 0;
        }

        int s0 = -prices[0], s1 = Integer.MIN_VALUE, s2 = Integer.MIN_VALUE, s3 = Integer.MIN_VALUE;
        for (int i = 1; i < prices.length; i++) {
            s0 = Integer.max(s0, -prices[i]);           // state 0
            s1 = Integer.max(s1, s0 + prices[i]);       // sell stock -> turn to state 1 or do nothing
            s2 = Integer.max(s2, s1 - prices[i]);       // second buy -> turn to state 2 or do nothing
            s3 = Integer.max(s3, s2 + prices[i]);       // final state, sell or do nothing
        }
        return Math.max(s3, 0);      // if the stock price is monotonically decreasing, return 0
    }
}

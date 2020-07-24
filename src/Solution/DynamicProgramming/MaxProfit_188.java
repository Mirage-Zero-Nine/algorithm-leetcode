package Solution.DynamicProgramming;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 *
 * @author BorisMirage
 * Time: 2019/01/31 21:54
 * Created with IntelliJ IDEA
 */

public class MaxProfit_188 {
    /**
     * Dynamic programming with 2D table, optimized from O(n^3) to O(n^2).
     * The original state transition should be:
     * dp[i, j] = max(dp[i - 1][t - 1] + price[j] - price[t])
     * dp[i, j]: maximum profit from at most i transactions using prices 0 to j.
     * i: ith transaction
     * j: sell at price[j]
     * t: buy at price[t]
     * The time complexity will be O(n^2 * k) (three level looping).
     * To optimize it, note that in dp[i][j], only dp[i - 1][t - 1] - price[t] is relating to t.
     * Hence, dp[i][j] = price[j] + max(dp[i - 1][t - 1] - price[t]).
     * max(dp[i - 1][t - 1] - price[t]) can be found by using a int to store during the loop from 0 to j.
     * Base case:
     * dp[i, 0] = 0, 0 <= i <= k
     * dp[0, j] = 0, 0 <= j < n
     *
     * @param k      at most k transactions
     * @param prices int array
     * @return max profit
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;

        /* Corner case */
        if (n < 2) {
            return 0;
        }

        if (k >= n / 2) {       // k is sufficient for comparing each adjacent value in array
            int max = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] - prices[i - 1] > 0) {
                    max += prices[i] - prices[i - 1];
                }
            }
            return max;
        }

        int[][] dp = new int[k + 1][n];     // dp[i][j]: maximum profit from at most i transactions using prices 0 to j

        for (int i = 1; i <= k; i++) {
            int maxTemp = -prices[0];     // max profit from previous round
            for (int j = 1; j < n; j++) {           // each row is one more buying taken based on previous max profit

                /*
                 * dp[i, j] = max(dp[i - 1][t - 1] + price[j] - price[t]), where t is from 0 to j
                 * dp[i][j - 1]: do nothing (or buy) which doesn't change the acquired profit
                 * prices[j] - buyPrice: sell at current price
                 * maxTemp is to find max(dp[i - 1][t - 1] - price[t])
                 * t is from 0 to j. */
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] - maxTemp);
                maxTemp = Math.max(maxTemp, dp[i - 1][j] - prices[j]);
            }
        }

        return dp[k][n - 1];
    }

    /**
     * DP solution with space optimized by rolling array.
     *
     * @param k      k times transaction limit
     * @param prices prices array
     * @return max profit from the array
     */
    public int dpOptimized(int k, int[] prices) {

        /* Corner case */
        if (prices.length < 2) {
            return 0;
        }

        if (k >= prices.length / 2) {       // k is sufficient for comparing each adjacent value in array
            int max = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] - prices[i - 1] > 0) {
                    max += prices[i] - prices[i - 1];
                }
            }
            return max;
        }

        int[] r1 = new int[prices.length];
        int[] r2 = new int[prices.length];

        for (int i = 1; i < k + 1; i++) {
            int base = -prices[0];

            for (int j = 1; j < r1.length; j++) {
                r2[j] = Math.max(r2[j - 1], prices[j] + base);
                base = Math.max(base, r1[j] - prices[j]);
            }

            for (int j = 0; j < prices.length; j++) {       // memory compress
                r1[j] = r2[j];
                r2[j] = 0;
            }
        }

        return r1[prices.length - 1];
    }

    /**
     * Use a state machine to save state.
     * The length of state is 2 * k, since each k representing a buy and sell state.
     *
     * @param k      at most k transactions
     * @param prices given int array
     * @return max profit
     */
    public int stateMachine(int k, int[] prices) {

        /* Corner case */
        if (k == 0 || prices.length < 2) {
            return 0;
        }

        if (k >= prices.length / 2) {
            int max = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] - prices[i - 1] > 0) {
                    max += prices[i] - prices[i - 1];
                }
            }
            return max;
        }

        int[] state = new int[k * 2];
        state[0] = -prices[0];
        for (int i = 1; i < state.length; i++) {
            state[i] = Integer.MIN_VALUE;
        }

        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j < state.length; j++) {

                /* State transition */
                if (j == 0) {
                    state[j] = Integer.max(state[j], -prices[i]);
                } else {
                    state[j] = Integer.max(state[j], (j % 2 == 0 ? state[j - 1] - prices[i] : state[j - 1] + prices[i]));
                }
            }
        }

        return Math.max(0, state[state.length - 1]);
    }
}

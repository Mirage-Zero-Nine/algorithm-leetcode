package Solution.DynamicProgramming;

/**
 * You have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock)
 * Design an algorithm to find the maximum profit.
 * Note that you cannot sell a stock before you buy one.
 *
 * @author BorisMirage
 * Time: 2019/01/22 11:02
 * Created with IntelliJ IDEA
 */

public class MaxProfit_121 {
    /**
     * Dynamic programming. Similar to max subarray problem.
     * There is only one "buy". Hence, the cost of buy is -profit[i].
     * And the profit is -profit[j] + profit[i], where 0 <= j < i, which is finding the min profit[j] from 0 to i - 1.
     * Hence, the translation is dp[i] = Math.max(dp[i - 1], prices[i] - minFrom0Toi).
     *
     * @param prices int array
     * @return max "profit"
     */
    public int maxProfit(int[] prices) {

        /* Corner case */
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int maxProfit = 0, previousMax = 0, minCost = prices[0];

        for (int i = 1; i < prices.length; i++) {
            minCost = Math.min(minCost, prices[i]);
            maxProfit = Math.max(previousMax, prices[i] - minCost);
            previousMax = maxProfit;
        }

        return maxProfit;
    }

    /**
     * Standard DP solution.
     *
     * @param prices given array
     * @return max "profit"
     */
    public int maxProfitStandardDP(int[] prices) {

        /* Corner case */
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int[] dp = new int[prices.length];
        int minCost = prices[0];

        for (int i = 1; i < prices.length; i++) {
            minCost = Math.min(minCost, prices[i]);
            dp[i] = Math.max(dp[i - 1], prices[i] - minCost);
        }

        return dp[dp.length - 1];
    }

    /**
     * Greedy, actually idea is same as dynamic programming solution.
     *
     * @param prices int array
     * @return max "profit"
     */
    public int maxProfitGreedy(int[] prices) {
        int max = 0, profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit = Math.max(0, prices[i] - prices[i - 1] + profit);
            max = Math.max(max, profit);
        }

        return max;
    }
}

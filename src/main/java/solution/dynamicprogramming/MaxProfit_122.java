package solution.dynamicprogramming;

/**
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 * On each day, you may decide to buy and/or sell the stock.
 * You can only hold at most one share of the stock at any time.
 * However, you can buy it then immediately sell it on the same day.
 * Find and return the maximum profit you can achieve.
 *
 * @author BorisMirage
 * Time: 2019/01/22 18:44
 * Created with IntelliJ IDEA
 */

public class MaxProfit_122 {
    /**
     * Dynamic programming.
     * State transition:
     * buy[i] = max(sell[i - 1] - price[i], buy[i - 1])
     * sell[i]= max(sell[i - 1], buy[i] + price[i])
     * Base case:
     * buy[0] = -prices[0]
     * sell[0] = 0
     *
     * @param prices given array
     * @return max profit
     */
    public int maxProfit(int[] prices) {

        /* Corner case */
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int n = prices.length;
        int[] buy = new int[n];
        int[] sell = new int[n];
        buy[0] = -prices[0];
        sell[0] = 0;

        for (int i = 1; i < n; i++) {
            buy[i] = Math.max(sell[i - 1] - prices[i], buy[i - 1]);
            sell[i] = Math.max(sell[i - 1], buy[i] + prices[i]);
        }

        return sell[n - 1];
    }

    /**
     * Greedy.
     * No limit on transaction times, so directly sum up the increase in all the increasing subarray of given array.
     *
     * @param prices given array
     * @return max profit
     */
    public int maxProfitGreedy(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            max = prices[i] > prices[i - 1] ? max + prices[i] - prices[i - 1] : max;
        }

        return max;
    }
}

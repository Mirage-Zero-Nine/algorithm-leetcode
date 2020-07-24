package Solution.DynamicProgramming;

/**
 * Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i.
 * There is a non-negative integer fee representing a transaction fee.
 * You may complete as many transactions as you like. But you need to pay the transaction fee for each transaction.
 * You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
 * Return the maximum profit you can make.
 *
 * @author BorisMirage
 * Time: 2020/03/21 11:00
 * Created with IntelliJ IDEA
 */

public class MaxProfit_714 {
    /**
     * Dynamic programming.
     * State transition:
     * buy[i] = max(sell[i - 1] - price[i], buy[i - 1])
     * sell[i] = max(buy[i - 1] + price[i] - fee, sell[i - 1])
     * Base cases:
     * buy[0] = -price[0]
     * sell[0] = 0
     *
     * @param prices given array
     * @param fee    transaction fee
     * @return max profit
     */
    public int maxProfit(int[] prices, int fee) {

        /* Corner case */
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int n = prices.length;
        int[] buy = new int[n];
        int[] sell = new int[n];
        buy[0] = -prices[0];

        for (int i = 1; i < n; i++) {
            buy[i] = Math.max(sell[i - 1] - prices[i], buy[i - 1]);
            sell[i] = Math.max(buy[i - 1] + prices[i] - fee, sell[i - 1]);
        }

        return sell[n - 1];
    }

    /**
     * Reduce space complexity to O(1).
     *
     * @param prices given int array
     * @return max profit
     */
    public int maxProfitOptimized(int[] prices, int fee) {

        /* Corner case */
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int b0 = -prices[0], s0 = 0;    // state 0, purchase is allowed, but sell is not allowed
        int s1 = 0;                     // at state 0, sell[i - 1] does not exist
        int b1 = b0;                    // buy[i - 1] is buy[0] when i == 1

        for (int i = 1; i < prices.length; i++) {       // starts at day 1
            b0 = Math.max(s1 - prices[i], b1);
            s0 = Math.max(b0 + prices[i] - fee, s1);
            b1 = b0;
            s1 = s0;
        }

        return s0;
    }
}

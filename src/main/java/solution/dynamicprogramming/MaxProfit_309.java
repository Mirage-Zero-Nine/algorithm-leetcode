package solution.dynamicprogramming;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like.
 * ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 * 1. You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * 2. After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 *
 * @author BorisMirage
 * Time: 2020/03/21 10:56
 * Created with IntelliJ IDEA
 */

public class MaxProfit_309 {
    /**
     * Dynamic programming.
     * State transition:
     * buy[i] = max(sell[i - 2] - price, buy[i - 1])
     * sell[i] = max(buy[i - 1] + price, sell[i - 1])
     * Base cases:
     * buy[0] = -price[0]
     * sell[0] = 0
     * buy[1] = Math.max(-prices[0], -prices[1]
     * sell[1] = Math.max(buy[0] + prices[1], sell[0])
     *
     * @param prices given int array
     * @return max profit
     */
    public int maxProfit(int[] prices) {

        /* Corner case */
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int n = prices.length;
        int[] buy = new int[n];     // maximum profit end with buying on day i or end with buying on a day before i
        int[] sell = new int[n];    // maximum profit end with selling on day i or end with selling on a day before i
        buy[0] = -prices[0];
        sell[0] = 0;
        buy[1] = Math.max(-prices[0], -prices[1]);          // either buy first or buy second
        sell[1] = Math.max(buy[0] + prices[1], sell[0]);

        for (int i = 2; i < n; i++) {       // starts at day 2
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }

        return sell[n - 1];
    }

    /**
     * Reduce space complexity to O(1).
     *
     * @param prices given int array
     * @return max profit
     */
    public int maxProfitOptimized(int[] prices) {

        /* Corner case */
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int b0 = -prices[0], s0 = 0;    // state 0, purchase is allowed, but sell is not allowed
        int s1 = 0, s2 = 0;             // at state 0, sell[i - 1], sell[i - 2] does not exist
        int b1 = b0;                    // buy[i - 1] is buy[0] when i == 1

        for (int i = 1; i < prices.length; i++) {       // starts at day 1
            b0 = Math.max(b1, s2 - prices[i]);      // b0: buy[i]
            s0 = Math.max(s1, b1 + prices[i]);      // s0: sell[i]
            b1 = b0;        // b1: buy[i - 1]
            s2 = s1;        // s2: sell[i - 2]
            s1 = s0;        // s1: sell[i - 1]
        }

        return s0;
    }

    public static void main(String[] args) {
        System.out.println(new MaxProfit_309().maxProfit(new int[]{1, 2, 4}));      // 3
        System.out.println(new MaxProfit_309().maxProfit(new int[]{1, 2, 3, 0, 2}));      // 3
        System.out.println(new MaxProfit_309().maxProfit(new int[]{2, 1, 4}));      // 3

    }
}

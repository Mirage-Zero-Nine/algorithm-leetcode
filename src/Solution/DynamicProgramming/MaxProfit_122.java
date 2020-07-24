package Solution.DynamicProgramming;

/**
 * Example 1:
 * Input: [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
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

package Solution.DynamicProgramming;

import java.util.Arrays;

/**
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that needed to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * @author BorisMirage
 * Time: 2019/08/12 16:00
 * Created with IntelliJ IDEA
 */

public class CoinChange_322 {
    /**
     * Dynamic programming.
     * State transition:
     * dp[i] = Math.min(dp[i - j] + 1), if i - coins[k] >= 0 && dp[i - coin] != Integer.MAX_VALUE
     *
     * @param coins  given coins list
     * @param amount amount to reach
     * @return fewest number of coins that needed to make up that amount
     */
    public int coinChange(int[] coins, int amount) {
        if (coins.length == 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);     // set init value
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                }
            }
        }

        return (dp[dp.length - 1] == Integer.MAX_VALUE) ? -1 : dp[dp.length - 1];
    }
}

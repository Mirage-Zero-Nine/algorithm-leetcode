package solutions.dynamicprogramming;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * You are given an array of positive coin denominations and a non-negative target amount.
 * Each denomination can be used any number of times. Compute the fewest number of coins needed
 * to make up the target amount. If no combination can make the target, return -1.
 *
 * @author BorisMirage
 * Time: 2019/08/12 16:00
 * Created with IntelliJ IDEA
 */

public class CoinChange_322 {
    /**
     * Returns the minimum number of unlimited-use coins whose values add up to {@code amount}.
     *
     * <p>For each amount {@code i}, try each coin {@code c} as the last coin.  If {@code i - c} is
     * reachable, the candidate is {@code dp[i - c] + 1}; keep the smallest candidate.  Start with
     * {@code dp[0] = 0} and mark other amounts unreachable, then process amounts in ascending order
     * so smaller results are ready first.  The streams implement the same steps: {@code map}
     * initializes the DP array, the outer {@code range} visits amounts in order, {@code filter}
     * keeps usable coins, and {@code forEach} applies each candidate update.
     *
     * <p>Runs in {@code O(amount * coins.length)} time and uses {@code O(amount)} auxiliary space.
     * The input array is not modified.  A null or empty coin array returns {@code -1}, including
     * when {@code amount} is zero.
     *
     * @param coins available positive coin denominations; each denomination may be used repeatedly
     * @param amount non-negative value to construct
     * @return the minimum number of coins, or {@code -1} when the amount cannot be constructed
     */
    public int coinChange(int[] coins, int amount) {
        // Handle the supported null/empty extension before the amount-zero base case.
        if (coins == null || coins.length == 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }

        // rangeClosed keeps each stream element equal to its final DP index. The map sets the
        // zero-coin base case and marks every other amount unreachable initially.
        int[] dp = IntStream.rangeClosed(0, amount)
                .map(i -> i == 0 ? 0 : Integer.MAX_VALUE)
                .toArray();

        // Process amounts in ascending order so every smaller amount is final before it is used.
        IntStream.range(0, dp.length).forEach(i ->
                Arrays.stream(coins).filter(c -> i - c >= 0 && dp[i - c] != Integer.MAX_VALUE)
                        .forEach(c -> dp[i] = Math.min(dp[i], dp[i - c] + 1))
        );

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}

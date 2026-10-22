package playground;

/**
 * Assume there are n items, each items has a value.
 * Based on a specific knapsack has a max weight limit, find max value this knapsack can contain.
 *
 * @author BorisMirage
 * Time: 2019/07/13 13:36
 * Created with IntelliJ IDEA
 */

public class Knapsack {
    /**
     * Typical dynamic programming problem.
     * dp[i][j] = (j >= weight[i]) ? Math.max(dp[i + 1][j - weight[i]] + value[i], dp[i + 1][j]) : dp[i + 1][j]
     *
     * @param weight    given items weight
     * @param value     given items value
     * @param maxWeight max weight limit of knapsack
     * @return max value under weight limit of knapsack
     */
    public int knapsack(int[] weight, int[] value, int maxWeight) {

        if (weight == null || value == null || weight.length != value.length || maxWeight < 0) {
            throw new IllegalArgumentException("Weight array and value array should be same! ");
        }
        int[][] dp = new int[weight.length + 1][maxWeight + 1];
        for (int i = weight.length - 1; i >= 0; i--) {
            for (int capacity = 0; capacity <= maxWeight; capacity++) {
                dp[i][capacity] = dp[i + 1][capacity];
                if (weight[i] >= 0 && weight[i] <= capacity) {
                    dp[i][capacity] = Math.max(dp[i][capacity], value[i] + dp[i + 1][capacity - weight[i]]);
                }
            }
        }
        return dp[0][maxWeight];
    }

    public static void main(String[] args) {
        System.out.println(new Knapsack().knapsack(new int[]{2, 2, 6, 5, 4}, new int[]{6, 3, 5, 4, 6}, 10));
    }
}

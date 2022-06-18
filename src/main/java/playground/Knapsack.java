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

        if (weight.length == 1) {
            return value[0];
        }
        if (weight.length != value.length) {
            throw new IllegalArgumentException("Weight array and value array should be same! ");
        }

        int[][] dp = new int[weight.length][maxWeight];

        for (int i = 0; i < maxWeight; i++) {
            dp[weight.length - 1][i] = (weight[weight.length - 1] > i) ? 0 : value[value.length - 1];
        }

        for (int i = weight.length - 2; i > -1; i--) {
            for (int j = 0; j < maxWeight; j++) {
                dp[i][j] = (j >= weight[i]) ? Math.max(dp[i + 1][j - weight[i]] + value[i], dp[i + 1][j]) : dp[i + 1][j];
            }
        }

        return dp[0][maxWeight - 1];
    }

    public static void main(String[] args) {
        System.out.println(new Knapsack().knapsack(new int[]{2, 2, 6, 5, 4}, new int[]{6, 3, 5, 4, 6}, 10));
    }
}

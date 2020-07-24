package Solution.DynamicProgramming;

/**
 * There are a row of n houses, each house can be painted with one of the k colors.
 * The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.
 * The cost of painting each house with a certain color is represented by a n x k cost matrix.
 * Find the minimum cost to paint all houses.
 * Note:
 * All costs are positive integers.
 *
 * @author BorisMirage
 * Time: 2019/09/14 09:39
 * Created with IntelliJ IDEA
 */

public class MinCostII_265 {
    /**
     * State transition is same as naive DP solution, which is dp[i][j] = min(dp[i - 1][k]), where k != j
     * However, use two integers to store the min value in previous row to reduce the loop of searching previous row.
     * The reason is that two consecutive houses should use different color.
     * If previous house select current color, select second minimum cost instead.
     * Otherwise, select min cost from previous row.
     * Time complexity: O(nk)
     *
     * @param costs cost of painting
     * @return minimum cost to paint all houses
     */
    public int minCostII(int[][] costs) {

        /* Corner case */
        if (costs.length == 0) {
            return 0;
        }

        /*
         * min1 is the minimum cost in previous row, min2 is the second minimum.
         * Select min value for each house. But if previous house select same color, use second minimum.
         * previousMinIndex is the min value index in previous row */
        int min1 = 0, min2 = 0, previousMinIndex = -1, colors = costs[0].length;

        for (int[] cost : costs) {

            int tmpMin1 = Integer.MAX_VALUE, tmpMin2 = Integer.MAX_VALUE, currentMinIndex = -1;
            for (int j = 0; j < colors; j++) {

                /*
                 * If previous house select same color, select second minimum cost instead.
                 * Otherwise, select minimum value. */
                int tmp = cost[j] + (j != previousMinIndex ? min1 : min2);

                if (tmp < tmpMin1) {     // find new min cost in current row, update min value and index in current row
                    tmpMin2 = tmpMin1;
                    tmpMin1 = tmp;
                    currentMinIndex = j;
                } else if (tmp < tmpMin2) {      // find new second minimum value in current row
                    tmpMin2 = tmp;       // second min cost does not require to update index
                }
            }
            min1 = tmpMin1;
            min2 = tmpMin2;
            previousMinIndex = currentMinIndex;
        }

        return min1;
    }

    /**
     * Naive dynamic programming.
     * State transition:
     * dp[i][j] = min(dp[i - 1][k]), where k != j
     * Time complexity: O(n * k ^ 2)
     *
     * @param costs cost of painting
     * @return minimum cost to paint all houses
     */
    public int naiveDP(int[][] costs) {

        /* Corner case */
        if (costs.length == 0) {
            return 0;
        }

        int colors = costs.length, min;
        int[][] dp = new int[costs.length][costs[0].length];        // do not modify input
        dp[0] = costs[0];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                min = Integer.MAX_VALUE;

                for (int k = 0; k < costs[0].length; k++) {
                    if (k != j) {
                        min = Math.min(dp[i - 1][k] + costs[i][j], min);
                    }
                }

                dp[i][j] = min;
            }
        }

        min = Integer.MAX_VALUE;

        for (int i = 0; i < costs[0].length; i++) {     // find min value in last row
            min = Math.min(dp[colors - 1][i], min);
        }

        return min;
    }
}

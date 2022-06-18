package solution.dynamicprogramming;

/**
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
 * The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
 * Find the minimum cost to paint all houses.
 * Note:
 * All costs are positive integers.
 *
 * @author BorisMirage
 * Time: 2019/09/14 09:49
 * Created with IntelliJ IDEA
 */

public class MinCost_256 {
    /**
     * Dynamic programming.
     * State transition:
     * dp[i][j] = min(dp[i - 1][k]), where k != j
     * Time complexity: O(nk), where k is the number of color (in this problem, it's 3)
     *
     * @param costs cost of painting
     * @return minimum cost to paint all houses
     */
    public int minCost(int[][] costs) {

        /* Corner case */
        if (costs.length == 0) {
            return 0;
        }

        /*
         * min1 is the minimum cost in previous row, min2 is the second minimum.
         * Select min value for each house. But if previous house select same color, use second minimum.
         * previousMinIndex is the min value index in previous row */
        int min1 = 0, min2 = 0, previousMinIndex = -1, house = costs.length, colors = costs[0].length;

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
}

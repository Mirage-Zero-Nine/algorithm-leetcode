package Solution.DynamicProgramming;

/**
 * You are given a rows x cols matrix grid. Initially, you are located at the top-left corner (0, 0).
 * In each step, you can only move right or down in the matrix.
 * Among all possible paths starting from the top-left corner (0, 0) and ending in the bottom-right corner.
 * Find the path with the maximum non-negative product.
 * The product of a path is the product of all integers in the grid cells visited along the path.
 * Return the maximum non-negative product modulo 109 + 7. If the maximum product is negative return -1.
 * Notice that the modulo is performed after getting the maximum product.
 *
 * @author BorisMirage
 * Time: 2020/09/22 19:58
 * Created with IntelliJ IDEA
 */

public class MaxProductPath_1594 {
    /**
     * Dynamic programming.
     * Keep a pseudo-3D array (m * n * 2) to store the value during DP transition.
     * dp[i][j][0] is the max value at grid[i][j], and dp[i][j][1] is the minimum value at grid[i][j].
     * If the minimum value is negative value, then it could become largest value at next position.
     * Each time, it can only move down or move right, hence state transition:
     * v1 = grid[i][j] * Math.max(dp[i - 1][j][0], dp[i][j - 1][0])
     * v2 = grid[i][j] * Math.min(dp[i - 1][j][1], dp[i][j - 1][1])
     * dp[i][j][0] = Math.max(v1, v2)
     * dp[i][j][1] = Math.min(v1, v2)
     * Finally, return dp[m - 1][n - 1][0].
     *
     * @param grid given 2D grid
     * @return the maximum non-negative product modulo 109 + 7, if the maximum product is negative return -1
     */
    public int maxProductPath(int[][] grid) {

        int m = grid.length, n = grid[0].length, mod = 1_000_000_007;
        long[][][] dp = new long[m][n][2];
        dp[0][0][0] = grid[0][0];
        dp[0][0][1] = grid[0][0];

        for (int i = 1; i < m; i++) {     // first column
            dp[i][0][0] = dp[i - 1][0][0] * grid[i][0];
            dp[i][0][1] = dp[i - 1][0][0] * grid[i][0];
        }
        for (int i = 1; i < n; i++) {     // first row
            dp[0][i][0] = dp[0][i - 1][0] * grid[0][i];
            dp[0][i][1] = dp[0][i - 1][0] * grid[0][i];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                long v1 = grid[i][j] * Math.max(dp[i - 1][j][0], dp[i][j - 1][0]);      // largest possible positive
                long v2 = grid[i][j] * Math.min(dp[i - 1][j][1], dp[i][j - 1][1]);      // smallest possible negative

                dp[i][j][0] = Math.max(v1, v2);
                dp[i][j][1] = Math.min(v1, v2);
            }
        }

        return dp[m - 1][n - 1][0] < 0 ? -1 : (int) ((dp[m - 1][n - 1][0]) % mod);
    }
}

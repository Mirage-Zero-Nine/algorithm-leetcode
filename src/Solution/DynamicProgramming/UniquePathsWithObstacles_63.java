package Solution.DynamicProgramming;

/**
 * From top-left corner to bottom-right of a m x n grid .
 * In each move, only either down or right at any point can be made.
 * There may be obstacle in grid, hence it is possible that bottom-right can not be reached.
 * Find total number of possible unique paths.
 *
 * @author BorisMirage
 * Time: 2018/08/03 13:25
 * Created with IntelliJ IDEA
 */

public class UniquePathsWithObstacles_63 {
    /**
     * Similar to find path without obstacle.
     * The only difference is that when searching path the block with obstacle needs to be excluded.
     * Use a new int array to record path number since the obstacle exists.
     * Fill each grid row, simply check left block of current block, if there is obstacle at left, leave it.
     *
     * @param obstacleGrid input grid with obstacle
     * @return total paths number
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        /* Corner case */
        if (n == 0 || obstacleGrid[m - 1][n - 1] == 1 || obstacleGrid[0][0] == 1) {
            return 0;
        }

        int[][] dp = new int[m + 1][n + 1];

        dp[m - 1][n - 1] = 1;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                if ((i != m - 1 || j != n - 1) && obstacleGrid[i][j] != 1) {
                    if (i < m - 1 && obstacleGrid[i + 1][j] != 1) {
                        dp[i][j] += dp[i + 1][j];
                    }
                    if (j < n - 1 && obstacleGrid[i][j + 1] != 1) {
                        dp[i][j] += dp[i][j + 1];
                    }
                }
            }
        }

        return dp[0][0];
    }

    /**
     * A compact dynamic programming solution.
     *
     * @param obstacleGrid input grid with obstacle
     * @return total paths number
     */
    public int compact(int[][] obstacleGrid) {
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];

        dp[0] = 1;      // set Start

        for (int[] row : obstacleGrid) {
            for (int j = 0; j < width; j++) {
                if (row[j] == 1) {
                    dp[j] = 0;
                } else if (j > 0) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[width - 1];
    }
}

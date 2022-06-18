package solution.dynamicprogramming;

/**
 * Given a m x n grid filled with non-negative numbers
 * Find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * i.e., find shortest weight path from top left to bottom right.
 * Note: Only move either down or right at any point in time.
 *
 * @author BorisMirage
 * Time: 2018/08/06 15:46
 * Created with IntelliJ IDEA
 */

public class MinPathSum_64 {
    /**
     * Dynamic programming with 2D table.
     * (i,j) = min((i-1, j), (i, j-1)) + grid(i,j)
     * First row and column can be filled at beginning since only one down or right move can be made each time.
     *
     * @param grid input 2D array grid
     * @return min path length from top left to bottom right
     */
    public int minPathSum(int[][] grid) {

        /* Corner case */
        if (grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                int bottom = (i == m - 1) ? Integer.MAX_VALUE : dp[i + 1][j];
                int right = (j == n - 1) ? Integer.MAX_VALUE : dp[i][j + 1];

                dp[i][j] = ((i == m - 1) && (j == n - 1)) ? grid[i][j] : Math.min(bottom, right) + grid[i][j];
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        MinPathSum_64 test = new MinPathSum_64();
        System.out.println(test.minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
    }
}

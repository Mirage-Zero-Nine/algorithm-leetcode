package solution.dynamicprogramming;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *
 * @author BorisMirage
 * Time: 2020/03/22 09:02
 * Created with IntelliJ IDEA
 */

public class MaximalSquare_221 {
    /**
     * Dynamic programming.
     * The square means the four sides of length should be same.
     * Hence, the minimal side length among three possible way is the side of length at (i, j).
     * State transition:
     * If current cell is 1, the dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1
     * dp[i][j] represents the length of the square which lower-right is at (i, j)
     *
     * @param matrix given matrix
     * @return the area of largest square containing only 1's
     */
    public int maximalSquare(char[][] matrix) {

        /* Corner case */
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        int m = matrix.length, n = matrix[0].length, max = 0;
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {      // if current cell is 1, then the min square size is 1
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    max = Math.max(max, dp[i][j]);      // find max square length
                }
            }
        }

        return max * max;
    }
}

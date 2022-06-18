package solution.dynamicprogramming;

/**
 * From top-left corner to bottom right of a m x n grid .
 * In each move, only either down or right at any point can be made.
 * Find total number of possible unique paths.
 *
 * @author BorisMirage
 * Time: 2018/08/03 12:37
 * Created with IntelliJ IDEA
 */

public class UniquePaths_62 {
    /**
     * Find total paths via fill m x n table.
     * If block is in first row or first column, fill 1.
     * Otherwise, fill the sum of left block and upper block.
     * Start point: (0,0)
     * End point: (m-1, n-1)
     * State transition:
     * 1. dp[m - 1][n - 1]=1
     * 2. dp[i][j] = dp[i + 1][j] + dp[i][j + 1], if (i != m - 1 || j != n - 1)
     *
     * @param m m rows
     * @param n n columns
     * @return total number of path
     */
    public int uniquePaths(int m, int n) {

        /* Corner case */
        if (m == 0 || n == 0) {
            return 0;
        }

        int[][] dp = new int[m + 1][n + 1];

        dp[m - 1][n - 1] = 1;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i != m - 1 || j != n - 1) {
                    dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
                }
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        UniquePaths_62 test = new UniquePaths_62();
        System.out.println(test.uniquePaths(3, 7));
    }
}

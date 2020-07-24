package Solution.DynamicProgramming;

/**
 * Given a positive integer n, find the least number of perfect square numbers (1, 4, 9, 16, ...) which sum to n.
 *
 * @author BorisMirage
 * Time: 2019/11/08 11:16
 * Created with IntelliJ IDEA
 */

public class NumSquares_279 {
    /**
     * Dynamic programming.
     * dp[i] = min(dp[j] + 1), where i - j^2 >= 0
     * Time complexity: O(n * sqrt(n))
     *
     * @param n given integer
     * @return least number of perfect square numbers (1, 4, 9, 16, ...) which sum to n
     */
    public int numSquares(int n) {

        /* Corner case */
        if (n < 2) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        int min, square;
        for (int i = 1; i <= n; i++) {
            min = i;
            square = 1;
            while (i - square * square >= 0) {      // find all possible square solution
                min = Math.min(dp[i - square * square] + 1, min);
                square++;
            }
            dp[i] = min;
        }

        return dp[n];
    }
}

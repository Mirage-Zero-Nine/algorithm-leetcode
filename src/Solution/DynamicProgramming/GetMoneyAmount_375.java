package Solution.DynamicProgramming;

/**
 * We are playing the Guess Game. The game is as follows:
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
 * However, when you guess a particular number x, and you guess wrong, you pay $x.
 * You win the game when you guess the number I picked.
 * Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
 *
 * @author BorisMirage
 * Time: 2019/09/16 09:40
 * Created with IntelliJ IDEA
 */

public class GetMoneyAmount_375 {
    /**
     * Dynamic programming.
     * dp[i][j]: from i to j, the min money required to guarantee a win
     * dp[i][j] = max(dp[start][i - 1], dp[i + 1][end]), where start <= i <= end, 1 <= start <= end.
     * This solution is top-down version.
     *
     * @param n number from 1 to n
     * @return how much money you need to have to guarantee a win
     */
    public int getMoneyAmount(int n) {

        /* Corner case */
        if (n == 1) {
            return 0;
        }

        int[][] dp = new int[n + 1][n + 1];
        helper(dp, 1, n);
        return dp[1][n];
    }

    /**
     * Dynamic programming with recursion.
     * dp[i][j] = max(dp[start][i - 1], dp[i + 1][end]), where start <= i <= end, 1 <= start <= end.
     *
     * @param dp    dynamic programming array
     * @param start start index
     * @param end   end index
     * @return min money required to guarantee a win
     */
    private int helper(int[][] dp, int start, int end) {

        if (start >= end) {     // correct guessing requires no money
            return 0;
        }

        if (dp[start][end] != 0) {
            return dp[start][end];
        }

        int max = Integer.MAX_VALUE;

        for (int i = start; i <= end; i++) {
            int tmp = Math.max(helper(dp, start, i - 1), helper(dp, i + 1, end));
            max = Math.min(max, tmp);
        }

        return max;
    }

    /**
     * Dynamic programming with same idea, but this is a bottom-up version.
     * dp[i][j]: from i to j, the min money required to guarantee a win
     * dp[i][j] = max(dp[start][i - 1], dp[i + 1][end]), where start <= i <= end, 1 <= start <= end.
     * This solution is top-down version.
     *
     * @param n number from 1 to n
     * @return how much money you need to have to guarantee a win
     */
    public int bottomUp(int n) {

        /* Corner case */
        if (n == 1) {
            return 0;
        }

        int[][] dp = new int[n + 1][n + 1];

        for (int length = 1; length <= n; length++) {       // length of subarray
            for (int i = 1; i <= n - length; i++) {

                int j = i + length;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k <= j; k++) {
                    int left = (k - 1 >= i) ? dp[i][k - 1] : 0;
                    int right = (j >= k + 1) ? dp[k + 1][j] : 0;
                    dp[i][j] = Math.min(dp[i][j], k + Math.max(left, right));
                }
            }
        }

        return dp[1][n];
    }
}

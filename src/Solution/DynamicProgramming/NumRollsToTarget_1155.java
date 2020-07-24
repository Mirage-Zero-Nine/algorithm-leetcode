package Solution.DynamicProgramming;

/**
 * You have d dice, and each die has f faces numbered 1, 2, ..., f.
 * Return # of possible ways (modulo 10^9 + 7) to roll the dice so the sum of the face up numbers equals target.
 *
 * @author BorisMirage
 * Time: 2019/08/10 19:51
 * Created with IntelliJ IDEA
 */

public class NumRollsToTarget_1155 {
    /**
     * Dynamic programming.
     * State transition:
     * If j <= i * f (target # should at least be # of dices)
     * dp[i][j] = dp[i][j] + dp[i - 1][j - k], where k from [1, f] and j - k >= 0.
     *
     * @param d      # of dices
     * @param f      # of faces
     * @param target target #
     * @return # of possible ways (modulo 10^9 + 7) to roll the dice so the sum of the face up numbers equals target
     */
    public int numRollsToTarget(int d, int f, int target) {

        /* Corner case */
        if (d * f < target || target < d) {
            return 0;
        }

        int MOD = 1000000007;
        int[][] dp = new int[d + 1][target + 1];
        dp[0][0] = 1;       // 0 dice sums 0

        for (int i = 1; i <= d; i++) {      // how many possibility can i dices sum up to j;
            for (int j = 1; j <= target; j++) {
                if (j <= i * f) {
                    for (int k = 1; k <= f && k <= j; k++) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % MOD;
                    }
                }
            }
        }

        return dp[d][target];
    }
}

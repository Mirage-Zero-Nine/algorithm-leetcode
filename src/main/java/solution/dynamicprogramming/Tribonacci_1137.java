package solution.dynamicprogramming;

/**
 * The Tribonacci sequence Tn is defined as follows:
 * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
 * Given n, return the value of Tn.
 *
 * @author BorisMirage
 * Time: 2019/07/27 21:22
 * Created with IntelliJ IDEA
 */

public class Tribonacci_1137 {
    /**
     * Basic dynamic programming, dp[i] = dp[i-1] + dp[i-2] + dp[i-3].
     * Do not use recrusion to solve this problem, which will cause TLE.
     *
     * @param n n
     * @return nth Tribonacci sequence
     */
    public int tribonacci(int n) {

        /* Corner case */
        if (n < 3) {
            return (n == 0) ? 0 : 1;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }
}

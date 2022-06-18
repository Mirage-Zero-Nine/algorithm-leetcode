package solution.dynamicprogramming;

/**
 * Write a program to find the n-th ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * Note:
 * 1. 1 is typically treated as an ugly number.
 * 2. n does not exceed 1690.
 *
 * @author BorisMirage
 * Time: 2019/08/22 10:09
 * Created with IntelliJ IDEA
 */

public class NthUglyNumber_264 {
    /**
     * Dynamic programming.
     * Every subsequence is the ugly-sequence itself (1, 2, 3, 4, 5, ...) multiply 2, 3, 5.
     * Each time, choose the smallest one among 3 of the factors.
     * Multiply each with 2/3/5 as next number.
     *
     * @param n request nth ugly number
     * @return nth ugly number
     */
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];

        dp[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        int f2 = 2, f3 = 3, f5 = 5;

        for (int i = 1; i < n; i++) {
            int min = Math.min(Math.min(f2, f3), f5);
            dp[i] = min;
            if (f2 == min) {
                f2 = 2 * dp[++i2];
            }
            if (f3 == min) {
                f3 = 3 * dp[++i3];
            }
            if (f5 == min) {
                f5 = 5 * dp[++i5];
            }
        }

        return dp[n - 1];
    }
}

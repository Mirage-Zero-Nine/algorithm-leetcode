package Solution.DynamicProgramming;

/**
 * Given a string s, find the longest palindromic subsequence's length in s.
 * You may assume that the maximum length of s is 1000.
 *
 * @author BorisMirage
 * Time: 2019/10/16 17:12
 * Created with IntelliJ IDEA
 */

public class LongestPalindromeSubseq_516 {
    /**
     * Dynamic programming.
     * dp(i, j): max palindromic subsequence's length from string i to j (inclusive).
     * dp[i][j] = dp[i + 1][j - 1] + 2, if s.charAt(i) == s.charAt(j)
     * Otherwise, dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]), since no palindromic subsequence update in string.
     *
     * @param s given string
     * @return longest palindromic subsequence's length in s
     */
    public int longestPalindromeSubseq(String s) {

        /* Corner case */
        if (s == null) {
            return 0;
        }
        if (s.length() < 2) {
            return s.length();
        }

        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }
}

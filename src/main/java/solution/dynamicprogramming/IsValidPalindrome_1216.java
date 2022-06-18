package solution.dynamicprogramming;

/**
 * Given a string s and an integer k, find out if the given string is a K-Palindrome or not.
 * A string is K-Palindrome if it can be transformed into a palindrome by removing at most k characters from it.
 *
 * @author BorisMirage
 * Time: 2019/10/16 17:24
 * Created with IntelliJ IDEA
 */

public class IsValidPalindrome_1216 {
    /**
     * Find the longest palindromic subsequence in given string, check if it is longer than s.length() - k.
     *
     * @param s given string
     * @param k most chars can be removed
     * @return if the given string is a K-Palindrome or not
     */
    public boolean isValidPalindrome(String s, int k) {

        /* Corner case */
        if (s.length() < 2) {
            return true;
        }

        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) ? dp[i + 1][j - 1] + 2 : Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }

        return (n - dp[0][n - 1]) <= k;
    }
}

package solution.dynamicprogramming;

/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * Example 1:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * Example 2:
 * Input: s1 = "ab", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 *
 * @author BorisMirage
 * Time: 2019/01/20 22:24
 * Created with IntelliJ IDEA
 */

public class IsInterleave_97 {
    /**
     * DP with n^2 table.
     * Transform formula:
     * dp[i][j] = (dp[i - 1][j] && s3(i + j - 1) == s1(i - 1)) || (dp[i][j - 1] && s3(i + j - 1) == s2(j - 1))
     * dp[i][0] = (dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1))
     * dp[0][i] = (dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1))
     *
     * @param s1 first string
     * @param s2 second string
     * @param s3 test string
     * @return whether s3 is formed by the interleaving of s1 and s2.
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int l1 = s1.length(), l2 = s2.length(), l3 = s3.length();

        /* Corner case */
        if (l1 + l2 != l3) {
            return false;
        }

        boolean[][] dp = new boolean[l1 + 1][l2 + 1];
        dp[0][0] = true;

        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = (dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1));
        }

        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = (dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1));
        }

        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                dp[i][j] = ((dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)));
            }
        }
        return dp[l1][l2];
    }

    public static void main(String[] args) {
        IsInterleave_97 test = new IsInterleave_97();
        System.out.println(test.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(test.isInterleave("", "", ""));

    }
}

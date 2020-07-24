package Solution.DynamicProgramming;

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * (eg, "ace" is a subsequence of "abcde" while "aec" is not).
 * A common subsequence of two strings is a subsequence that is common to both strings.
 * If there is no common subsequence, return 0.
 *
 * @author BorisMirage
 * Time: 2019/08/03 15:42
 * Created with IntelliJ IDEA
 */

public class LongestCommonSubsequence_1143 {
    /**
     * Basic dynamic programming with 2D table.
     * If t1(i)==t2(j), then dp[i][j] = dp[i-1][j-1].
     * Otherwise dp[i][j] = max(dp[i][j-1],dp[i-1][j]).
     *
     * @param text1 first string
     * @param text2 second string
     * @return the length of their longest common subsequence
     */
    public int longestCommonSubsequence(String text1, String text2) {

        /* Corner case */
        if (text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        int l1 = text1.length(), l2 = text2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];

        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                dp[i][j] = (text1.charAt(i - 1) == text2.charAt(j - 1)) ? 1 + dp[i - 1][j - 1] : Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[l1][l2];
    }

    public static void main(String[] args) {
        System.out.println(new LongestCommonSubsequence_1143().longestCommonSubsequence("dhiuhh", "huih"));
        System.out.println(new LongestCommonSubsequence_1143().longestCommonSubsequence("abcde", "ace"));
        System.out.println(new LongestCommonSubsequence_1143().longestCommonSubsequence("ace", "ace"));
    }
}

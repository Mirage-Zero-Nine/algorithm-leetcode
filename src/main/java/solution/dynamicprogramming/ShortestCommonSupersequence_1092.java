package solution.dynamicprogramming;

/**
 * Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.
 * If multiple answers exist, you may return any of them.
 *
 * @author BorisMirage
 * Time: 2019/09/18 19:22
 * Created with IntelliJ IDEA
 */

public class ShortestCommonSupersequence_1092 {
    /**
     * Dynamic programming.
     * Find the LCS of two strings, then find all chars not in the LCS by string's order.
     * Append each char not in LCS to final result.
     *
     * @param str1 first string
     * @param str2 second string
     * @return shortest string that has both str1 and str2 as subsequences
     */
    public String shortestCommonSupersequence(String str1, String str2) {
        String l = lcs(str1, str2);
        System.out.println(l);
        int p1 = 0, p2 = 0;
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < l.length(); i++) {
            char c = l.charAt(i);
            while (str1.charAt(p1) != c) {
                out.append(str1.charAt(p1++));
            }
            while (str2.charAt(p2) != c) {
                out.append(str2.charAt(p2++));
            }

            out.append(c);
            p1++;
            p2++;
        }

        return out + str1.substring(p1) + str2.substring(p2);
    }

    /**
     * Find LCS of two strings by dynamic programming.
     *
     * @param a first string
     * @param b second string
     * @return LCS of two strings
     */
    private String lcs(String a, String b) {
        int n1 = a.length(), n2 = b.length();
        String[][] dp = new String[n1 + 1][n2 + 1];

        for (int i = 0; i <= n1; i++) {
            dp[i][0] = "";
        }

        for (int i = 0; i <= n2; i++) {
            dp[0][i] = "";
        }

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + a.charAt(i - 1);
                } else {
                    dp[i][j] = (dp[i - 1][j].length() > dp[i][j - 1].length()) ? dp[i - 1][j] : dp[i][j - 1];
                }
            }
        }

        return dp[n1][n2];
    }

    public static void main(String[] args) {
        String a = "abac";
        String b = "cab";
        System.out.println(new ShortestCommonSupersequence_1092().shortestCommonSupersequence(a, b));
    }
}

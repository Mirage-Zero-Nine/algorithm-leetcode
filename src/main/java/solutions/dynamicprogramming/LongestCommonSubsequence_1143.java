package solutions.dynamicprogramming;

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
     * Uses dynamic programming: solve the prefixes first, then extend them to
     * solve longer prefixes. Let {@code D(i,j)} be the longest common
     * subsequence length for the first {@code i} characters of {@code text1}
     * and first {@code j} characters of {@code text2}.
     * <pre>
     * D(i,0) = D(0,j) = 0
     * D(i,j) = D(i-1,j-1) + 1                 if the newest characters match
     * D(i,j) = max(D(i-1,j), D(i,j-1))        otherwise
     * </pre>
     * A match extends both prefixes; otherwise, skip either newest character
     * and keep the better result. Null or empty input returns zero. For input
     * lengths {@code m} and {@code n}, time is {@code O(mn)} and extra space
     * is {@code O(min(m,n))}.
     *
     * @param text1 first string
     * @param text2 second string
     * @return the length of their longest common subsequence
     */
    public int longestCommonSubsequence(String text1, String text2) {
        // Null and empty inputs have no non-empty common subsequence.
        if (text1 == null || text1.isEmpty() || text2 == null || text2.isEmpty()) {
            return 0;
        }

        // Keeping the shorter string in the row bounds the auxiliary array.
        if (text1.length() < text2.length()) {
            String shorter = text1;
            text1 = text2;
            text2 = shorter;
        }

        // Zero initialization represents the initial row D(0, j) = 0 for an
        // empty longer prefix; dp[0] separately remains D(i, 0) = 0 for every
        // later row.
        int[] dp = new int[text2.length() + 1];
        for (int i = 1; i <= text1.length(); i++) {
            // At the start of a row, the diagonal for j = 1 is D(i - 1, 0).
            int previousDiagonal = 0;
            for (int j = 1; j <= text2.length(); j++) {
                // Left-to-right updates leave dp[j] in the previous row and
                // dp[j - 1] in the current row. Save the previous-row value
                // before overwriting it.
                int oldValue = dp[j];
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // A match extends the old diagonal, so use it before dp[j]
                    // overwrites the above state.
                    dp[j] = previousDiagonal + 1;
                } else {
                    // A mismatch skips either newest character, so keep the
                    // better of the above and left states.
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                // The old above value is the next column's diagonal. Save it
                // after reading it, because dp[j] has now been overwritten.
                previousDiagonal = oldValue;
            }
        }
        return dp[text2.length()];
    }
}

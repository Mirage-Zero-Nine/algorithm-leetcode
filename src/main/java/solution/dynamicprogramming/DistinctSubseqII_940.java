package solution.dynamicprogramming;

/**
 * Given a string S, count the number of distinct, non-empty subsequences of S.
 * Since the result may be large, return the answer modulo 10^9 + 7.
 *
 * @author BorisMirage
 * Time: 2019/09/11 17:07
 * Created with IntelliJ IDEA
 */

public class DistinctSubseqII_940 {
    /**
     * Dynamic programming.
     * dp[i] represents the count of unique subsequence ends with S[i].
     * dp[i] is initialized to 1 for S[0 ... i].
     * For each dp[i], define j from 0 to i - 1:
     * 1. If s[j] != s[i], then dp[i] += dp[j]
     * 2. If s[j] == s[i], do nothing to avoid duplicates.
     * Finally, result = sum(dp[0], ... dp[n - 1]).
     * As optimization, use a int represent current # of sequence.
     * Each time, count current sum of subsequence minus duplicated # of subsequence.
     *
     * @param S given string
     * @return count the number of distinct, non-empty subsequences of S modulo 10^9 + 7
     */
    public int distinctSubseqII(String S) {
        int mod = (int) 1e9 + 7, n = S.length(), sum = 0;
        int[] count = new int[26];      // count # of subsequence under each character

        for (int i = 0; i < n; i++) {
            int c = S.charAt(i) - 'a';
            int cur = (1 + sum - count[c] + mod) % mod;     // current sum is based on previous sum minus duplicated #
            sum = (sum + cur) % mod;
            count[c] = (count[c] + cur) % mod;
        }

        return sum;
    }
}

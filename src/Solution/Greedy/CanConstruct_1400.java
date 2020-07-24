package Solution.Greedy;

/**
 * Given a string s and an integer k. You should construct k non-empty palindrome strings using all the characters in s.
 * Return true if you can use all the characters in s to construct k palindrome strings or false otherwise.
 *
 * @author BorisMirage
 * Time: 2020/04/04 17:11
 * Created with IntelliJ IDEA
 */

public class CanConstruct_1400 {
    /**
     * Count the number of odd chars.
     * These 2 conditions should both be met:
     * 1. The number of odd chars <= k, then it is possible.
     * 2. k <= n
     * First condition is that, if number of odd chars <= k, then the string is possible to make palindrome subsequence.
     * Second, the string can make at most k palindrome subsequence.
     *
     * @param s given string
     * @param k construct k non-empty palindrome strings
     * @return true if it can construct k palindrome strings or false otherwise
     */
    public boolean canConstruct(String s, int k) {

        /* Corner case */
        if (s == null || s.length() == 0) {
            return false;
        }

        int odd = 0, n = s.length();
        int[] count = new int[26];

        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
            odd = (count[s.charAt(i) - 'a'] % 2 == 1) ? odd + 1 : odd - 1;      // count number of odd char
        }

        return odd <= k && k <= n;
    }
}

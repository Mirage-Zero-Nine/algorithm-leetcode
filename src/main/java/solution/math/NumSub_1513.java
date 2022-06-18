package solution.math;

/**
 * Given a binary string s (a string consisting only of '0' and '1's).
 * Return the number of substrings with all characters 1's.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * @author BorisMirage
 * Time: 2020/07/19 14:40
 * Created with IntelliJ IDEA
 */

public class NumSub_1513 {
    /**
     * Find all the substring that only contains 1.
     * The total number of a substring with length of n is n * (n + 1) / 2.
     *
     * @param s given string
     * @return the number of substrings with all characters 1's
     */
    public int numSub(String s) {
        long out = 0, mod = (int) 1e9 + 7, start = 0, end = 0;      // n * (n + 1) may have overflow

        while (end <= s.length()) {
            if (end == s.length() || s.charAt((int) end) == '0') {
                out = (out + (end - start) * (end - start + 1) / 2) % mod;
                start = end + 1;
            }
            end++;
        }

        return (int) out;
    }

    /**
     * Idea is the same, but optimize the calculation to avoid using long.
     *
     * @param s given string
     * @return the number of substrings with all characters 1's
     */
    public int numSubOptimized(String s) {
        int out = 0, length = 0, mod = (int) 1e9 + 7;

        for (int i = 0; i < s.length(); i++) {
            length = s.charAt(i) == '1' ? length + 1 : 0;

            out = (out + length) % mod;
        }

        return out;
    }
}

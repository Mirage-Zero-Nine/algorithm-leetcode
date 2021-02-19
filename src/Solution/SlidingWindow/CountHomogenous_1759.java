package Solution.SlidingWindow;

/**
 * Given a string s, return the number of homogenous substrings of s.
 * Since the answer may be too large, return it modulo 109 + 7.
 * A string is homogenous if all the characters of the string are the same.
 * A substring is a contiguous sequence of characters within a string.
 *
 * @author BorisMirage
 * Time: 2021/02/18 20:12
 * Created with IntelliJ IDEA
 */

public class CountHomogenous_1759 {
    /**
     * Find all the substring contains same char, add the sum of count respectively.
     *
     * @param s given string
     * @return the number of homogenous substrings of s
     */
    public int countHomogenous(String s) {
        int mod = 1_000_000_007, out = 0, count = 0, current = 0;
        for (int i = 0; i < s.length(); i++) {
            count = (s.charAt(i) == current) ? count + 1 : 1;
            current = s.charAt(i);
            out = (out + count) % mod;
        }

        return out;
    }
}

package Solution.SlidingWindow;

/**
 * Given a string s consisting only of characters a, b and c.
 * Return the number of substrings containing at least one occurrence of all these characters a, b and c.
 *
 * @author BorisMirage
 * Time: 2020/02/26 10:26
 * Created with IntelliJ IDEA
 */

public class NumberOfSubstrings_1358 {
    /**
     * Sliding window.
     * If one substring contains a, b, c is found, then the # of substring depends on most appeared char among a, b, c.
     * Find all window contains a, b, c, and then count all substring among them.
     *
     * @param s given string
     * @return the number of substrings containing at least one occurrence of all these characters a, b and c
     */
    public int numberOfSubstrings(String s) {
        int[] count = new int[]{0, 0, 0};
        int out = 0, index = 0, n = s.length();

        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;     // count current character

            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {      // one substring contains all a, b, c is found
                count[s.charAt(index++) - 'a']--;
            }
            out += index;       // # of substring contains all a, b, c depends on most appeared char among a, b, c
        }

        return out;
    }
}

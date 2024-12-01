package solution.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, return the length of the longest substring that contains at most two distinct characters.
 *
 * @author BorisMirage
 * Time: 2024/12/01 11:08
 * Created with IntelliJ IDEA
 */

public class LengthOfLongestSubstringTwoDistinct_159 {
    /**
     * Simpler version of finding longest substring with K distinct chars. Identical solution.
     *
     * @param s given string
     * @return the length of the longest substring that contains at most two distinct characters.
     * @see LengthOfLongestSubstringKDistinct_340
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        // corner case
        if (s == null || s.length() < 3) {
            return s == null ? 0 : s.length();
        }

        int maxLength = Integer.MIN_VALUE, start = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            if (map.size() > 2) {
                while (start < i && map.size() > 2) {
                    char current = s.charAt(start);
                    map.put(current, map.get(current) - 1);
                    if (map.get(current) == 0) {
                        map.remove(current);
                    }
                    start++;
                }
            }
            maxLength = Math.max(maxLength, i - start + 1);
        }

        return maxLength;
    }
}

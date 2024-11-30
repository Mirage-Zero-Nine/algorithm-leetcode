package solution.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * @author BorisMirage
 * Time: 2018/03/23 12:27
 * Created with IntelliJ IDEA
 */

public class LengthOfLongestSubstring_3 {
    /**
     * Sliding window.
     * The starting point of window is the index of the last non-duplicate char, and the end is current char.
     * If current char is a duplicated char, check its last appearance in given string.
     * If its last appearance is in current substring, then set the start position to the next of duplicated char.
     * Keep a hash map to store the pair of char and its last appearance index.
     *
     * @param s input string
     * @return max sub-string length
     * @see MinWindow_76
     * @see MinimumSumSubarray
     */
    public int lengthOfLongestSubstring(String s) {

        // corner case
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int start = 0, end = 0, maxWindow = Integer.MIN_VALUE;
        Map<Character, Integer> map = new HashMap<>();
        while (end < s.length()) {

            if (map.containsKey(s.charAt(end))) {
                // current window ends at a char that is found before
                // to ensure the window only contains the non-duplicated char
                // either move the window start to the next char of the previous-seeing ending char
                // or keep the window start in place, depends on which one is later
                start = Math.max(start, map.get(s.charAt(end)) + 1);
            }

            maxWindow = Math.max(maxWindow, end - start + 1);
            map.put(s.charAt(end), end++);  // put it to map, and move to next char
        }

        return maxWindow;
    }
}



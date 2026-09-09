package solutions.slidingwindow;

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
     * Computes the maximum length with a last-seen-index sliding window.
     *
     * <p>At each iteration, {@code start} is the index immediately before the
     * current window, and {@code end} is the index being considered. If the
     * character at {@code end} was seen at index {@code previous}, setting
     * {@code start} to {@code max(start, previous)} removes the old occurrence
     * without moving the boundary backward. The resulting window is therefore
     * duplicate-free; comparing its length with the best result seen so far
     * considers every possible right endpoint.</p>
     *
     * <p>The method runs in {@code O(n)} time and uses {@code O(min(n, A))}
     * auxiliary space, where {@code n} is the string length and {@code A} is
     * the number of distinct Java {@code char} values encountered.</p>
     *
     * @param s input string; {@code null} and the empty string produce zero
     * @return the length of the longest substring without repeated characters
     */
    public int lengthOfLongestSubstring(String s) {
        // corner case
        if (s == null || s.isEmpty()) {
            return 0;
        }

        // `start` is the boundary immediately before the valid window, rather
        // than the index of its first character. Therefore the window at `end`
        // is (start, end], its length is end - start, and -1 is the boundary
        // before the first character.
        int start = -1, end = 0, max = 1;
        Map<Character, Integer> map = new HashMap<>(s.length());

        while (end < s.length()) {
            if (map.containsKey(s.charAt(end))) {
                int previous = map.get(s.charAt(end));
                // Move the boundary past the old occurrence while keeping it
                // monotonic when that occurrence is already outside the
                // current window. Do not add 1 to this assignment: that would
                // switch start to an inclusive index while the length below
                // still uses the boundary form. In "abba", the final `a` was
                // last seen at 0 while the valid window starts at 2, so the
                // boundary must remain 2 to retain the window "ba".
                start = Math.max(previous, start);
            }
            // The window is (start, end], so its length is end - start. With
            // an inclusive start instead, the paired formulas would be
            // max(start, previous + 1) and end - start + 1.
            max = Math.max(max, end - start);
            map.put(s.charAt(end), end);
            end++;
        }
        return max;
    }
}

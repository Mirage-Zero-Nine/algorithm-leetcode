package solutions.slidingwindow;

import java.util.stream.IntStream;

/**
 * Solves LeetCode 76, Minimum Window Substring.
 *
 * <p>Given strings {@code s} and {@code t}, returns the shortest contiguous substring of {@code s}
 * containing every character in {@code t} with at least the same multiplicity.  If no such substring
 * exists, or either input is empty (or {@code null}), the empty string is returned.  The problem's
 * input consists of English letters, so the fixed counter array is sufficient.</p>
 *
 * <p>The implementation uses a two-pointer sliding window.  The right pointer expands the window
 * until it satisfies {@code t}; the left pointer then removes unnecessary characters and records
 * each valid candidate.  Each pointer only moves forward, giving linear time.</p>
 *
 * @author BorisMirage
 * Time: 2019/06/18 11:25
 * Created with IntelliJ IDEA
 */

public class MinWindow_76 {
    /**
     * Finds the shortest substring of {@code s} that covers the character multiset in {@code t}.
     *
     * <p>{@code count[c]} is the number of occurrences of {@code c} still needed by the current
     * window.  It may become negative when the window contains extra copies.  {@code restChars}
     * counts the total number of required occurrences still missing, including duplicates.</p>
     *
     * <p>When the right edge sees a character with a positive remaining count, that occurrence
     * fulfills a requirement.  Once no occurrences remain missing, moving the left edge proves
     * minimality for this right edge: every removed surplus character keeps the window valid, and
     * the first removed required occurrence makes it invalid.  The best valid candidate seen over
     * all right edges is therefore the global minimum.</p>
     *
     * @param s source string to search
     * @param t required character multiset
     * @return the shortest covering substring, or {@code ""} when none exists
     * @implNote Runs in {@code O(s.length() + t.length())} time and uses {@code O(1)} auxiliary
     *     space for the fixed English-letter alphabet.
     */
    public String minWindow(String s, String t) {
        // Empty inputs cannot contain a non-empty requirement; null is treated the same way.
        if (s == null || s.isEmpty() || t == null || t.isEmpty()) {
            return "";
        }

        int minWindow = Integer.MAX_VALUE, restChars = t.length(), windowStart = 0, minWindowStart = 0;
        int[] count = new int[256];

        IntStream.range(0, t.length()).forEach(n -> count[t.charAt(n) - 'A']++);

        for (int i = 0; i < s.length(); i++) {
            // Post-decrement tests the old count: only an occurrence that was still needed
            // reduces restChars. Extra occurrences make count negative and are harmless.
            if (count[s.charAt(i) - 'A']-- > 0) {
                restChars--;
            }

            // restChars == 0 is the invariant that the current [windowStart, i] covers t.
            // Remove from the left while preserving that invariant as long as possible.
            while (restChars == 0) {
                // Post-increment tests the old count: zero means this occurrence was the last
                // required copy, so removing it creates a deficit and ends this shrink phase.
                if (count[s.charAt(windowStart) - 'A']++ == 0) {
                    restChars++;
                }
                // The candidate was valid when this loop began and still includes windowStart.
                // The count/rest update models removing that character; if it was required, this
                // is the last valid candidate for this right edge.
                if (i - windowStart + 1 < minWindow) {
                    minWindow = i - windowStart + 1;
                    minWindowStart = windowStart;
                }

                windowStart++;
            }
        }

        return minWindow == Integer.MAX_VALUE ? "" : s.substring(minWindowStart, minWindowStart + minWindow);
    }
}

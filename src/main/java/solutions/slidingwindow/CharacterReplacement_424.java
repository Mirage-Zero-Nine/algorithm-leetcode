package solutions.slidingwindow;

/**
 * Given a string that consists of only uppercase English letters.
 * Replace any letter in the string with another letter at most k times.
 * Find the length of a longest substring containing all repeating letters after performing the above operations.
 *
 * @author BorisMirage
 * Time: 2019/06/18 17:18
 * Created with IntelliJ IDEA
 */

public class CharacterReplacement_424 {
    /**
     * Finds the longest substring that can be made uniform with at most
     * {@code k} replacements.
     *
     * <p>For each right endpoint, the window's most frequent character is the
     * optimal character to keep. Consequently, the remaining characters are
     * exactly the replacements required. When that number exceeds {@code k},
     * moving the left endpoint restores feasibility. The stored maximum
     * frequency is allowed to be historical: it can only make the temporary
     * window bound looser, never cause a returned length that cannot be
     * achieved, because a stale maximum is replaced only when the window grows
     * to that length.
     *
     * @param s uppercase English-letter string to inspect
     * @param k maximum number of replacements allowed
     * @return the maximum feasible substring length, or {@code 0} for an empty
     *         string
     * @implNote Runs in {@code O(s.length())} time and uses {@code O(1)}
     *          auxiliary space (26 counters). The input string is not changed.
     */
    public int characterReplacement(String s, int k) {
        int mostCommonChar = 0, output = 0, left = 0;
        int[] charCount = new int[26];
        for (int i = 0; i < s.length(); i++) {
            mostCommonChar = Math.max(mostCommonChar, ++charCount[s.charAt(i) - 'A']);
            // The window can be uniformized by retaining its most common
            // character; every other character consumes one replacement.
            while (i - left + 1 > k + mostCommonChar) {
                // Discard the oldest character until the replacement budget is
                // sufficient again. The right endpoint never moves backward.
                charCount[s.charAt(left++) - 'A']--;
            }
            // Every feasible window ending at i is represented by the current
            // left endpoint, so retain the largest one seen so far.
            output = Math.max(output, i - left + 1);
        }
        return output;
    }
}

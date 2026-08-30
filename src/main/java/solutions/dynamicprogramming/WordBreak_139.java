package solutions.dynamicprogramming;

import java.util.List;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words
 * Determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * @author BorisMirage
 * Time: 2019/06/07 14:15
 * Created with IntelliJ IDEA
 */

public class WordBreak_139 {
    /**
     * Returns whether {@code s} can be completely segmented using words from {@code wordDict}.
     * A dictionary word may be reused any number of times.
     *
     * <p>This method returns {@code false} for {@code null}, an empty string, a {@code null}
     * dictionary, or an empty dictionary.
     *
     * @param s        the string to segment
     * @param wordDict the dictionary of reusable words
     * @return {@code true} if the complete string can be segmented; otherwise {@code false}
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        // corner case
        if (s == null || s.isEmpty() || wordDict == null || wordDict.isEmpty()) {
            return false;
        }

        return dfs(s, wordDict, new int[s.length()], 0);
    }

    /**
     * Checks whether the suffix beginning at {@code start} can be segmented.
     *
     * <p>{@code memory[start]} uses three states:
     * <ul>
     *     <li>{@code 0}: the suffix has not been evaluated;</li>
     *     <li>{@code 1}: the suffix can be segmented;</li>
     *     <li>{@code -1}: the suffix cannot be segmented.</li>
     * </ul>
     *
     * @param s      the original string
     * @param words  the dictionary words
     * @param memory memoization state indexed by the suffix start position
     * @param start  index of the next character to match
     * @return whether the suffix beginning at {@code start} can be segmented
     */
    private boolean dfs(String s, List<String> words, int[] memory, int start) {
        // Reaching the end means every character has been matched successfully.
        if (start == s.length()) {
            return true;
        }

        // Do not recompute a suffix that has already been evaluated.
        if (memory[start] != 0) {
            return memory[start] == 1;
        }

        String current = s.substring(start);
        for (String word : words) {
            // The word must fit, match the beginning of the suffix, and leave a segmentable rest.
            if (word.length() <= current.length()
                    && current.startsWith(word)
                    && dfs(s, words, memory, start + word.length())) {
                memory[start] = 1;
                return true;
            }
        }
        memory[start] = -1;
        return false;
    }
}

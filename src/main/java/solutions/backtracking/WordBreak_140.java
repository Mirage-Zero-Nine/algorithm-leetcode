package solutions.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words.
 * Add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 * Note:
 * 1. The same word in the dictionary may be reused multiple times in the segmentation.
 * 2. You may assume the dictionary does not contain duplicate words.
 *
 * @author BorisMirage
 * Time: 2019/06/06 16:23
 * Created with IntelliJ IDEA
 */
public class WordBreak_140 {
    /**
     * Returns every possible sentence formed by inserting spaces into {@code s}
     * so that every resulting word belongs to {@code wordDict}.
     *
     * <p>Dictionary words may be reused, and the result may be returned in any
     * order. An empty result means that no complete segmentation exists.
     * Although the problem guarantees non-empty inputs, {@code null} or empty
     * inputs are handled defensively by returning an empty list.
     *
     * @param s        the string to segment
     * @param wordDict the dictionary of permitted, non-empty words
     * @return all valid sentences, or an empty list when {@code s} cannot be segmented
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        // Handle invalid or empty inputs defensively before building the dictionary set.
        if (s == null || s.isEmpty() || wordDict == null || wordDict.isEmpty()) {
            return new ArrayList<>();
        }

        Set<String> words = new HashSet<>(wordDict);
        int maxWordLength = words.stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);

        // An empty dictionary word cannot advance the start index.
        return maxWordLength == 0 ?
                new ArrayList<>() :
                backtracking(s, 0, words, maxWordLength, new HashMap<>());
    }

    /**
     * Builds all valid sentences that start at {@code start}.
     *
     * @param s             the original string
     * @param start         index of the next character to consume
     * @param words         dictionary represented as a set for fast membership checks
     * @param maxWordLength length of the longest dictionary word
     * @param map           cached sentences keyed by their starting index
     * @return all valid sentences from {@code start} to the end of {@code s}
     */
    private List<String> backtracking(
            String s,
            int start,
            Set<String> words,
            int maxWordLength,
            Map<Integer, List<String>> map) {
        // Cache empty lists too: an impossible suffix should not be searched again.
        if (map.containsKey(start)) {
            return map.get(start);
        }

        List<String> output = new ArrayList<>();

        // No dictionary word can extend beyond this bound.
        int endLimit = Math.min(s.length(), start + maxWordLength);
        for (int end = start + 1; end <= endLimit; end++) {
            // The substring is the candidate word beginning at start.
            String word = s.substring(start, end);
            if (!words.contains(word)) {
                continue;
            }

            // A dictionary word reaching the end forms a complete sentence.
            if (end == s.length()) {
                output.add(word);
            } else {
                // Otherwise, prepend this word to every valid sentence for the suffix.
                for (String suffix : backtracking(s, end, words, maxWordLength, map)) {
                    output.add(word + " " + suffix);
                }
            }
        }

        // Store both successful and unsuccessful states.
        map.put(start, output);
        return output;
    }
}

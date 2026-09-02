package solutions.hashmap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides two solutions for LeetCode 242, {@code Valid Anagram}.
 *
 * <p>The array-based solution is specialized for the problem's lowercase
 * English-letter input. The HashMap-based solution is more general and can
 * also be used for the Unicode follow-up, treating Java strings as sequences
 * of UTF-16 {@code char} values.
 *
 * @author BorisMirage
 * @see <a href="https://leetcode.com/problems/valid-anagram/">LeetCode 242: Valid Anagram</a>
 */
public class IsAnagram_242 {

    /**
     * Determines whether {@code t} is an anagram of {@code s} with a fixed
     * frequency array.
     *
     * <p>Each lowercase English letter maps to one of 26 slots. The method
     * counts every character in {@code s}, consumes those counts while reading
     * {@code t}, and rejects immediately if {@code t} uses a character too many
     * times. Because the strings have equal length, no remaining positive count
     * is possible after the second pass when no negative count was found.
     *
     * <p>This approach is valid for the LeetCode constraint that both strings
     * contain only lowercase English letters. It uses {@code O(1)} auxiliary
     * space because the frequency table always has 26 entries.
     *
     * @param s first lowercase English-letter string
     * @param t second lowercase English-letter string
     * @return {@code true} if {@code t} has exactly the same character counts as
     *         {@code s}; otherwise {@code false}
     * @see <a href="https://leetcode.com/problems/valid-anagram/">LeetCode 242: Valid Anagram</a>
     */
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        int[] count = new int[26];

        // Count each lowercase letter in the first string.
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        // Consume the counts with the second string.
        for (int i = 0; i < t.length(); i++) {
            int index = t.charAt(i) - 'a';
            count[index]--;

            // A negative count means t contains this letter too often.
            if (count[index] < 0) {
                return false;
            }
        }

        // Equal lengths and no negative count imply every count is balanced.
        return Arrays.stream(count).sum() == 0;
    }

    /**
     * Determines whether {@code t} is an anagram of {@code s} with a character
     * frequency map.
     *
     * <p>The map records the number of occurrences of each character in
     * {@code s}. While traversing {@code t}, each matching count is decremented;
     * a missing or exhausted entry proves that the strings are not anagrams.
     * The remaining entries must all be zero for the strings to match exactly.
     *
     * <p>Unlike the fixed-array approach, this implementation supports any
     * Java {@code char} value and therefore also covers the problem's Unicode
     * follow-up at the UTF-16 code-unit level.
     *
     * @param s first string
     * @param t second string
     * @return {@code true} if {@code t} has exactly the same character counts as
     *         {@code s}; otherwise {@code false}
     * @see <a href="https://leetcode.com/problems/valid-anagram/">LeetCode 242: Valid Anagram</a>
     */
    public boolean isAnagramWithHashMap(String s, String t) {

        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> counts = new HashMap<>();

        // Build a frequency table from the first string.
        for (char c : s.toCharArray()) {
            counts.merge(c, 1, Integer::sum);
        }

        // Consume one occurrence for every character in the second string.
        for (char c : t.toCharArray()) {
            int count = counts.getOrDefault(c, 0);

            // No remaining occurrence means t cannot be an anagram of s.
            if (count == 0) {
                return false;
            }
            counts.put(c, count - 1);
        }

        // Every character from s must have been consumed exactly once.
        return counts.values().stream().allMatch(i -> i == 0);
    }
}

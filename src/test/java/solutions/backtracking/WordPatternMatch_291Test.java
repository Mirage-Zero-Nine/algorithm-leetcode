package solutions.backtracking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Contract tests for the bijective, non-empty substring matching problem.
 * The official problem page is premium-gated; the published examples and the
 * source contract provide the behavior exercised here.
 */
class WordPatternMatch_291Test {
    private final WordPatternMatch_291 solution = new WordPatternMatch_291();

    @Test
    void officialExamplesAndRepresentativeMappings() {
        assertMatch("abab", "redblueredblue", true);
        assertMatch("aaaa", "asdasdasdasd", true);
        assertMatch("aabb", "xyzabcxzyabc", false);
        assertMatch("abba", "dogcatcatdog", true);
        assertMatch("abba", "dogcatcatfish", false);
        assertMatch("ab", "aa", false);
        assertMatch("a", "x", true);
        assertMatch("a", "test", true);
        // The historical abcabc/redbluegreen fixture exceeded the official 20-character
        // input bound, so this equivalent bounded regression replaces it.
        assertMatch("abcabc", "xyzxyz", true);
        assertMatch("aab", "xyzxyzabc", true);
        assertMatch("aab", "xyzxyzxy", true);
        assertMatch("abc", "onetwothree", true);
        assertMatch("abc", "onetwone", true);
        assertMatch("aba", "redbluered", true);
        assertMatch("aba", "redblueredblue", false);
        assertMatch("abcd", "ab", false);
        assertMatch("abc", "aaaaaa", true);
        assertMatch("abca", "onetwothreefourone", true);
        assertMatch("abca", "onetwothreefourfive", false);
        assertMatch("aabbcc", "onetwothreefourfive", false);
        assertMatch("xyz", "aaa", false);
        assertMatch("xyx", "aaaaaa", true);
        assertMatch("xyx", "aaaaab", false);
        assertMatch("xyxy", "onetwoonetwo", true);
        assertMatch("aabc", "redbluegreenred", false);
        assertMatch("aabc", "redredbluegreen", true);
    }

    @Test
    void exhaustiveSmallAlphabetCasesAgainstIndependentPartitionOracle() {
        int checked = 0;
        for (int patternLength = 1; patternLength <= 4; patternLength++) {
            for (String pattern : words("ab", patternLength)) {
                for (int stringLength = 1; stringLength <= 6; stringLength++) {
                    for (String value : words("ab", stringLength)) {
                        boolean expected = partitionOracle(pattern, value);
                        assertEquals(expected, solution.wordPatternMatch(pattern, value),
                                () -> "pattern=" + pattern + ", string=" + value);
                        checked++;
                    }
                }
            }
        }
        assertTrue(checked > 3000, "the exhaustive matrix should exercise many partitions");
    }

    @Test
    void longInputsAndBacktrackingFailures() {
        assertMatch("abcdefghijklmnopqrst", "abcdefghijklmnopqrst", true);
        assertMatch("abcdefghijklmnopqrst", "abcdefghijklmnopqrs", false);
        assertMatch("aaaaaaaaaaaaaaaaaaaa", "abcdefghijklmnopqrst", false);
        assertMatch("aaaaaaaaaaaaaaaaaaaa", "abcdefghijklmnopqrss", false);
        assertMatch("ababab", "xyxyxy", true);
        assertMatch("ababab", "xyxyxq", false);
        assertMatch("abcabcabcabc", "xyzxyzxyzxyz", true);
        assertMatch("abcabcabcabc", "xyzxyzxyzxyq", false);
        assertMatch("abacaba", "xyxzxyx", true);
        assertMatch("abacaba", "xyxzxya", false);
        assertMatch("abcdabcd", "aabbccddaabbccdd", true);
        assertMatch("abcdabcd", "aabbccddaabbccde", false);
        assertMatch("aabb", "mississippi", false);
        assertMatch("ab", "mississippi", true);
        assertMatch("abc", "aaaa", false);
    }

    @Test
    void implementationDefinedEmptyInputsAndLengthBoundaries() {
        // The LeetCode contract is non-empty, but this implementation reaches a
        // well-defined terminal state for empty inputs; keep that behavior covered.
        assertMatch("", "", true);
        assertMatch("", "a", false);
        assertMatch("a", "", false);

        // The official upper bound is 20 characters for both inputs.
        assertMatch("a", "abcdefghijklmnopqrst", true);
        assertMatch("abcdefghijklmnopqrst", "a", false);
        assertMatch("abcdefghijklmnopqrst", "abcdefghijklmnopqrst", true);
        assertMatch("abababababababababab", "xyxyxyxyxyxyxyxyxyxy", true);
    }

    @Test
    void bijectionCollisionsAndRepeatedCharacterConstraints() {
        assertMatch("ab", "aa", false); // two pattern symbols cannot share a word
        assertMatch("aabb", "aaaa", false); // neither equal-length partition works
        assertMatch("abc", "aaaaa", false); // three symbols need three distinct substrings
        assertMatch("abc", "aaaaaa", true); // lengths 1, 2, and 3 are distinct
        assertMatch("abab", "aaaaaa", true); // overlapping repeated candidates: a=a, b=aa
        assertMatch("ababa", "aaaaaaa", true); // same pair reused across five positions
        assertMatch("aabc", "redredbluegreen", true);
        assertMatch("aabc", "redbluegreenred", false);
        assertMatch("abba", "redblueredblue", false); // final a must be the first word
        assertMatch("abca", "onetwothreeone", true);
    }

    @ParameterizedTest
    @CsvSource({
            "'a','x',true", "'a','xy',true", "'aa','x',false", "'aa','xx',true",
            "'ab','xy',true", "'ab','xx',false", "'aba','xyx',true", "'aba','xyz',false",
            "'abc','xyz',true", "'abc','xyx',false", "'abba','redbluebluered',true",
            "'abba','redbluebluegreen',false", "'abcabc','xyzxyz',true"
    })
    void directBijectionBoundaryCases(String pattern, String value, boolean expected) {
        assertMatch(pattern, value, expected);
    }

    @Test
    void sameInstanceCallsDoNotLeakMappingsOrSearchState() {
        WordPatternMatch_291 reused = new WordPatternMatch_291();

        assertMatch(reused, "abab", "redblueredblue", true);
        assertMatch(reused, "abab", "redblueredgreen", false);
        assertMatch(reused, "a", "x", true);
        assertMatch(reused, "aa", "xy", false);
        assertMatch(reused, "", "", true);
        assertMatch(reused, "abc", "onetwothree", true);
        assertMatch(reused, "abc", "aaaaa", false);
        assertMatch(reused, "a", "abcdefghijklmnopqrst", true);
    }

    @Test
    void exhaustiveTernaryCasesAgainstIndependentPartitionOracle() {
        int checked = 0;
        for (int patternLength = 1; patternLength <= 3; patternLength++) {
            for (String pattern : words("abc", patternLength)) {
                for (int stringLength = 1; stringLength <= 5; stringLength++) {
                    for (String value : words("abc", stringLength)) {
                        boolean expected = partitionOracle(pattern, value);
                        assertEquals(expected, solution.wordPatternMatch(pattern, value),
                                () -> "pattern=" + pattern + ", string=" + value);
                        checked++;
                    }
                }
            }
        }
        assertEquals(14_157, checked);
    }

    private void assertMatch(String pattern, String value, boolean expected) {
        assertMatch(solution, pattern, value, expected);
    }

    private static void assertMatch(WordPatternMatch_291 candidate, String pattern, String value,
                                    boolean expected) {
        assertEquals(expected, candidate.wordPatternMatch(pattern, value),
                () -> "pattern=" + pattern + ", string=" + value);
    }

    private static boolean partitionOracle(String pattern, String value) {
        int separators = value.length() - 1;
        if (pattern.length() > value.length()) {
            return false;
        }
        // Independently enumerate cut positions, then compare the canonical equality
        // pattern of the symbols with the canonical equality pattern of the blocks.
        for (int mask = 0; mask < (1 << separators); mask++) {
            if (Integer.bitCount(mask) != pattern.length() - 1) {
                continue;
            }
            Map<Character, Integer> symbolIds = new HashMap<>();
            Map<String, Integer> blockIds = new HashMap<>();
            int symbolId = 0;
            int blockId = 0;
            int start = 0;
            boolean same = true;
            for (int i = 0; i < pattern.length(); i++) {
                int end = value.length();
                if (i < pattern.length() - 1) {
                    int cutsSeen = 0;
                    for (int cut = 0; cut < value.length() - 1; cut++) {
                        if (((mask >>> cut) & 1) != 0 && cutsSeen++ == i) {
                            end = cut + 1;
                            break;
                        }
                    }
                }
                String block = value.substring(start, end);
                Integer expectedSymbol = symbolIds.get(pattern.charAt(i));
                if (expectedSymbol == null) {
                    expectedSymbol = symbolId++;
                    symbolIds.put(pattern.charAt(i), expectedSymbol);
                }
                Integer actualBlock = blockIds.get(block);
                if (actualBlock == null) {
                    actualBlock = blockId++;
                    blockIds.put(block, actualBlock);
                }
                if (!expectedSymbol.equals(actualBlock)) {
                    same = false;
                    break;
                }
                start = end;
            }
            if (same) {
                return true;
            }
        }
        return false;
    }

    private static Set<String> words(String alphabet, int length) {
        Set<String> result = new HashSet<>();
        buildWords(alphabet, length, new StringBuilder(), result);
        return result;
    }

    private static void buildWords(String alphabet, int remaining, StringBuilder current,
                                   Set<String> result) {
        if (remaining == 0) {
            result.add(current.toString());
            return;
        }
        for (int i = 0; i < alphabet.length(); i++) {
            current.append(alphabet.charAt(i));
            buildWords(alphabet, remaining - 1, current, result);
            current.setLength(current.length() - 1);
        }
    }
}

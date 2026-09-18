package solutions.backtracking;

import org.junit.jupiter.api.Test;

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

    private void assertMatch(String pattern, String value, boolean expected) {
        assertEquals(expected, solution.wordPatternMatch(pattern, value),
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

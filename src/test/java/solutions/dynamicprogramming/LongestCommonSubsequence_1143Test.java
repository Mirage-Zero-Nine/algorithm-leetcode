package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

public class LongestCommonSubsequence_1143Test {

    private final LongestCommonSubsequence_1143 test = new LongestCommonSubsequence_1143();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.longestCommonSubsequence("abcde", "ace"));
        assertEquals(3, test.longestCommonSubsequence("abc", "abc"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.longestCommonSubsequence("abc", "def"));
        assertEquals(0, test.longestCommonSubsequence("aaa", "bbb"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, test.longestCommonSubsequence("abcdefgh", "acdfgh"));
    }

    @Test
    public void testIdenticalStrings() {
        assertEquals(5, test.longestCommonSubsequence("hello", "hello"));
        assertEquals(1, test.longestCommonSubsequence("a", "a"));
    }

    @Test
    public void testSingleCharMatch() {
        assertEquals(1, test.longestCommonSubsequence("a", "ba"));
        assertEquals(1, test.longestCommonSubsequence("xyz", "z"));
    }

    @Test
    public void testNoCommonSubsequence() {
        assertEquals(0, test.longestCommonSubsequence("xyz", "abc"));
    }

    @Test
    public void testNullAndEmptyInputs() {
        assertEquals(0, test.longestCommonSubsequence(null, "abc"));
        assertEquals(0, test.longestCommonSubsequence("abc", null));
        assertEquals(0, test.longestCommonSubsequence(null, null));
        assertEquals(0, test.longestCommonSubsequence("", ""));
    }

    @Test
    public void testShorterStringCanBeEitherInput() {
        assertEquals(3, test.longestCommonSubsequence("abcdefghijkl", "ace"));
        assertEquals(3, test.longestCommonSubsequence("ace", "abcdefghijkl"));
        assertEquals(2, test.longestCommonSubsequence("zzxyzz", "xy"));
        assertEquals(2, test.longestCommonSubsequence("xy", "zzxyzz"));
    }

    @Test
    public void testDiagonalAndLeftStateUpdates() {
        assertEquals(1, test.longestCommonSubsequence("ba", "ab"));
        assertEquals(2, test.longestCommonSubsequence("abc", "bac"));
        assertEquals(4, test.longestCommonSubsequence("bdcaba", "abcbdab"));
        assertEquals(4, test.longestCommonSubsequence("aabbaa", "ababa"));
    }

    @Test
    public void testRepeatedCallsDoNotShareState() {
        assertEquals(3, test.longestCommonSubsequence("abcde", "ace"));
        assertEquals(0, test.longestCommonSubsequence("xxx", "yyy"));
        assertEquals(1, test.longestCommonSubsequence("q", "q"));
        assertEquals(3, test.longestCommonSubsequence("ace", "abcde"));
    }

    @Test
    public void testMaximumLengthIdenticalStrings() {
        String text = "abca".repeat(250);
        assertEquals(1000, test.longestCommonSubsequence(text, text));
    }

    @Test
    public void testMaximumLengthDisjointStrings() {
        assertEquals(0, test.longestCommonSubsequence("a".repeat(1000), "b".repeat(1000)));
    }

    @Test
    public void testMaximumLengthAsymmetricInputsAndBoundaryOverlap() {
        String repeatedA = "a".repeat(1000);
        String almostRepeatedA = "a".repeat(999) + "b";
        assertEquals(1, test.longestCommonSubsequence(repeatedA, "a"));
        assertEquals(1, test.longestCommonSubsequence("a", repeatedA));
        assertEquals(999, test.longestCommonSubsequence(repeatedA, almostRepeatedA));
        assertEquals(999, test.longestCommonSubsequence(almostRepeatedA, repeatedA));
    }

    @Test
    public void testReversedStrings() {
        assertEquals(1, test.longestCommonSubsequence("abc", "cba"));
        assertEquals(5, test.longestCommonSubsequence("abcba", "abcba"));
    }

    @Test
    public void testRepeatedCharacters() {
        assertEquals(3, test.longestCommonSubsequence("aaa", "aaaa"));
        assertEquals(4, test.longestCommonSubsequence("aaaa", "aaaa"));
    }

    @Test
    public void testExhaustiveSmallStrings() {
        Set<String> strings = new HashSet<>();
        for (int length = 1; length <= 6; length++) {
            addBinaryStrings(strings, "", length);
        }

        for (String text1 : strings) {
            for (String text2 : strings) {
                assertEquals(
                        longestCommonSubsequenceByEnumeration(text1, text2),
                        test.longestCommonSubsequence(text1, text2),
                        () -> "text1=" + text1 + ", text2=" + text2);
            }
        }
    }

    private void addBinaryStrings(Set<String> strings, String prefix, int remaining) {
        if (remaining == 0) {
            strings.add(prefix);
            return;
        }
        addBinaryStrings(strings, prefix + "a", remaining - 1);
        addBinaryStrings(strings, prefix + "b", remaining - 1);
    }

    private int longestCommonSubsequenceByEnumeration(String text1, String text2) {
        Set<String> subsequences1 = allSubsequences(text1);
        Set<String> subsequences2 = allSubsequences(text2);
        int longest = 0;
        for (String subsequence : subsequences1) {
            if (subsequences2.contains(subsequence)) {
                longest = Math.max(longest, subsequence.length());
            }
        }
        return longest;
    }

    private Set<String> allSubsequences(String text) {
        Set<String> subsequences = new HashSet<>();
        addSubsequences(subsequences, text, 0, "");
        return subsequences;
    }

    private void addSubsequences(Set<String> subsequences, String text, int index, String current) {
        if (index == text.length()) {
            subsequences.add(current);
            return;
        }
        addSubsequences(subsequences, text, index + 1, current);
        addSubsequences(subsequences, text, index + 1, current + text.charAt(index));
    }

    @org.junit.jupiter.api.TestFactory
    public Stream<DynamicTest> additionalDistinctCases() {
        return Stream.of(
                DynamicTest.dynamicTest("empty left", () -> assertEquals(0, test.longestCommonSubsequence("", "abc"))),
                DynamicTest.dynamicTest("empty right", () -> assertEquals(0, test.longestCommonSubsequence("abc", ""))),
                DynamicTest.dynamicTest("one common middle", () -> assertEquals(1, test.longestCommonSubsequence("ax", "ya"))),
                DynamicTest.dynamicTest("ordered overlap", () -> assertEquals(2, test.longestCommonSubsequence("abc", "bac"))),
                DynamicTest.dynamicTest("alternating overlap", () -> assertEquals(3, test.longestCommonSubsequence("abab", "baba"))),
                DynamicTest.dynamicTest("repeated blocks", () -> assertEquals(4, test.longestCommonSubsequence("aabbcc", "abcabc"))),
                DynamicTest.dynamicTest("longer left", () -> assertEquals(3, test.longestCommonSubsequence("zzabczz", "abc"))),
                DynamicTest.dynamicTest("case-sensitive", () -> assertEquals(0, test.longestCommonSubsequence("ABC", "abc"))),
                DynamicTest.dynamicTest("single repeated match", () -> assertEquals(1, test.longestCommonSubsequence("aaaa", "ba"))),
                DynamicTest.dynamicTest("disjoint suffixes", () -> assertEquals(2, test.longestCommonSubsequence("mnop", "opqr"))),
                DynamicTest.dynamicTest("whole short string", () -> assertEquals(4, test.longestCommonSubsequence("abcd", "abcd"))));
    }
}

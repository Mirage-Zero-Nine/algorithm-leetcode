package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

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
}

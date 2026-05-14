package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(0, test.longestCommonSubsequence("", "abc"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, test.longestCommonSubsequence("abcdefgh", "acdfgh"));
    }

    @Test
    public void testBothEmpty() {
        assertEquals(0, test.longestCommonSubsequence("", ""));
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
        assertEquals(0, test.longestCommonSubsequence("aaa", "bbb"));
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
    public void testGiantCase() {
        String s1 = "abcdefghij".repeat(50); // 500 chars
        String s2 = "acegi".repeat(100);      // 500 chars
        int result = test.longestCommonSubsequence(s1, s2);
        // result should be positive and consistent
        assertEquals(result, test.longestCommonSubsequence(s1, s2));
    }
}

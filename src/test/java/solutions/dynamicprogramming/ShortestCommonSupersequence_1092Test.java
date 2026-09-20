package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ShortestCommonSupersequence_1092Test {

    private final ShortestCommonSupersequence_1092 test = new ShortestCommonSupersequence_1092();

    @Test
    public void testHappyCases() {
        assertEquals(5, test.shortestCommonSupersequence("abac", "cab").length());
        assertEquals(3, test.shortestCommonSupersequence("abc", "abc").length());
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.shortestCommonSupersequence("a", "a").length());
        assertEquals(2, test.shortestCommonSupersequence("a", "b").length());
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.shortestCommonSupersequence("abcde", "ace").length());
    }

    @Test
    public void testIdenticalStrings() {
        String result = test.shortestCommonSupersequence("xyz", "xyz");
        assertEquals(3, result.length());
        assertEquals("xyz", result);
    }

    @Test
    public void testNoCommonChars() {
        String result = test.shortestCommonSupersequence("abc", "def");
        assertEquals(6, result.length());
    }

    @Test
    public void testOneCharOverlap() {
        String result = test.shortestCommonSupersequence("ab", "bc");
        assertEquals(3, result.length());
    }

    @Test
    public void testResultContainsBothSubsequences() {
        String result = test.shortestCommonSupersequence("abac", "cab");
        assertTrue(isSubsequence("abac", result));
        assertTrue(isSubsequence("cab", result));
    }

    @Test
    public void testSingleCharStrings() {
        assertEquals("a", test.shortestCommonSupersequence("a", "a"));
    }

    @Test
    public void testPrefixSuffix() {
        String result = test.shortestCommonSupersequence("abc", "bcd");
        assertEquals(4, result.length());
        assertTrue(isSubsequence("abc", result));
        assertTrue(isSubsequence("bcd", result));
    }

    @Test
    public void testGiantCase() {
        String s1 = "abcbdab";
        String s2 = "bdcaba";
        String result = test.shortestCommonSupersequence(s1, s2);
        assertTrue(isSubsequence(s1, result));
        assertTrue(isSubsequence(s2, result));
        // LCS length is 4 ("bcba"), so SCS = 7 + 6 - 4 = 9
        assertEquals(9, result.length());
    }

    private boolean isSubsequence(String sub, String str) {
        int j = 0;
        for (int i = 0; i < str.length() && j < sub.length(); i++) {
            if (str.charAt(i) == sub.charAt(j)) j++;
        }
        return j == sub.length();
    }

    @ParameterizedTest(name = "SCS {0} and {1}")
    @CsvSource({"a,b,2", "ab,ac,3", "aa,aa,2", "abc,ab,3", "aba,bab,4", "geek,eke,5", "aaaa,aa,4", "xyz,xzy,4", "cab,abac,5", "abca,bcaa,5"})
    public void testAdditionalLengthAndSubsequenceCases(String first, String second, int expectedLength) {
        String result = test.shortestCommonSupersequence(first, second);
        assertEquals(expectedLength, result.length());
        assertTrue(isSubsequence(first, result));
        assertTrue(isSubsequence(second, result));
    }
}

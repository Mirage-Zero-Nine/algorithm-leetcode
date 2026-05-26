package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LongestDupSubstring_1044Test {
    private final LongestDupSubstring_1044 test = new LongestDupSubstring_1044();

    private void assertDuplicateValidity(String source, String dup) {
        if (dup.isEmpty()) {
            return;
        }
        int first = source.indexOf(dup);
        int second = source.indexOf(dup, first + 1);
        assertTrue(first >= 0 && second >= 0);
    }

    @Test
    public void testHappyCases() {
        assertEquals("ana", test.longestDupSubstring("banana"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals("", test.longestDupSubstring("a"));
        assertEquals("", test.longestDupSubstring("abcde"));
    }

    @Test
    public void testLargeCase() {
        String result = test.longestDupSubstring("aaaaaaa");
        assertEquals(6, result.length());
    }

    @Test
    public void testAllSameCharacters() {
        String result = test.longestDupSubstring("zzzz");
        assertEquals(3, result.length());
        assertDuplicateValidity("zzzz", result);
    }

    @Test
    public void testOverlappingDuplicateAllowed() {
        String result = test.longestDupSubstring("ababa");
        assertEquals(3, result.length());
        assertDuplicateValidity("ababa", result);
    }

    @Test
    public void testMultipleCandidatesAnyLongestAccepted() {
        String s = "abcdabc";
        String result = test.longestDupSubstring(s);
        assertEquals(3, result.length());
        assertDuplicateValidity(s, result);
    }

    @Test
    public void testTwoCharacterString() {
        assertEquals("", test.longestDupSubstring("ab"));
    }

    @Test
    public void testRepeatedPrefixSuffixPattern() {
        String s = "abcabcabcx";
        String result = test.longestDupSubstring(s);
        assertEquals(6, result.length());
        assertDuplicateValidity(s, result);
    }

    @Test
    public void testGiantCaseLongRun() {
        String s = "a".repeat(1000);
        String result = test.longestDupSubstring(s);
        assertEquals(999, result.length());
        assertDuplicateValidity(s, result);
    }

    @Test
    public void testNoLongDuplicateBeyondOneCharacter() {
        String s = "abca";
        String result = test.longestDupSubstring(s);
        assertEquals(1, result.length());
        assertDuplicateValidity(s, result);
    }
}

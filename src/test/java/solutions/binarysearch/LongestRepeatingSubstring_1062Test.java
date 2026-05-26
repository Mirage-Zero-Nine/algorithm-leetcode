package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestRepeatingSubstring_1062Test {

    private final LongestRepeatingSubstring_1062 test = new LongestRepeatingSubstring_1062();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.longestRepeatingSubstring("aabcaabdaab"));
        assertEquals(3, test.longestRepeatingSubstring("abcabc"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.longestRepeatingSubstring("a"));
        assertEquals(0, test.longestRepeatingSubstring("abcd"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, test.longestRepeatingSubstring("aaaaaaa"));
    }

    @Test
    public void testTwoSameCharacters() {
        assertEquals(1, test.longestRepeatingSubstring("aa"));
    }

    @Test
    public void testTwoDifferentCharacters() {
        assertEquals(0, test.longestRepeatingSubstring("ab"));
    }

    @Test
    public void testOverlapRepeatingSubstring() {
        assertEquals(3, test.longestRepeatingSubstring("ababa"));
    }

    @Test
    public void testRepeatedPrefix() {
        assertEquals(6, test.longestRepeatingSubstring("abcabcabc"));
    }

    @Test
    public void testNoRepeatBeyondSingleCharacter() {
        assertEquals(1, test.longestRepeatingSubstring("abca"));
    }

    @Test
    public void testExactRepeatBlock() {
        assertEquals(3, test.longestRepeatingSubstring("xyzxyz"));
    }

    @Test
    public void testGiantCase() {
        String s = "a".repeat(1200);
        assertEquals(1199, test.longestRepeatingSubstring(s));
    }
}

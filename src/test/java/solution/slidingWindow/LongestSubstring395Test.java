package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestSubstring395Test {

    private final LongestSubstring_395 test = new LongestSubstring_395();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.longestSubstring("aaabb", 3));
        assertEquals(5, test.longestSubstring("ababbc", 2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.longestSubstring("", 1));
        assertEquals(0, test.longestSubstring("aaabb", 4));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.longestSubstring("aacbbbdc", 2));
    }

    @Test
    public void testSingleChar() {
        assertEquals(1, test.longestSubstring("a", 1));
    }

    @Test
    public void testSingleCharKGreater() {
        assertEquals(0, test.longestSubstring("a", 2));
    }

    @Test
    public void testAllSameChars() {
        assertEquals(5, test.longestSubstring("aaaaa", 1));
    }

    @Test
    public void testNullString() {
        assertEquals(0, test.longestSubstring(null, 1));
    }

    @Test
    public void testKEquals1() {
        assertEquals(6, test.longestSubstring("aaaaaa", 1));
    }

    @Test
    public void testNoValidSubstring() {
        assertEquals(0, test.longestSubstring("abcde", 2));
    }

    @Test
    public void testGiantCase() {
        String s = "a".repeat(5000) + "b".repeat(5000);
        assertEquals(10000, test.longestSubstring(s, 1));
    }
}

package solutions.treemap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LastSubstring_1163Test {

    private final LastSubstring_1163 test = new LastSubstring_1163();

    @Test
    public void testHappyCases() {
        assertEquals("bab", test.lastSubstring("abab"));
        assertEquals("tcode", test.lastSubstring("leetcode"));
    }

    @Test
    public void testNegativeCase() {
        assertEquals("zz", test.lastSubstring("azz"));
    }

    @Test
    public void testInvalidAndEdgeCases() {
        assertEquals("", test.lastSubstring(null));
        assertEquals("", test.lastSubstring(""));
        assertEquals("a", test.lastSubstring("a"));
        assertEquals("aaaa", test.lastSubstring("aaaa"));
    }

    @Test
    public void testLargeCase() {
        String input = "abc".repeat(40) + "z" + "abc".repeat(40);
        assertEquals("z" + "abc".repeat(40), test.lastSubstring(input));
    }

    @Test
    public void testHappyCaseTwoDistinctChars() {
        assertEquals("ba", test.lastSubstring("ba"));
    }

    @Test
    public void testHappyCaseMiddleMax() {
        assertEquals("zab", test.lastSubstring("abzab"));
    }

    @Test
    public void testHappyCaseLastCharMax() {
        assertEquals("z", test.lastSubstring("abcz"));
    }

    @Test
    public void testEdgeCaseAllSameExceptFirst() {
        assertEquals("zzzz", test.lastSubstring("zzzz"));
    }

    @Test
    public void testEdgeCaseDescending() {
        assertEquals("zyxwv", test.lastSubstring("zyxwv"));
    }

    @Test
    public void testEdgeCaseTwoChars() {
        assertEquals("bb", test.lastSubstring("bb"));
    }

    @Test
    public void testGiantCase() {
        String input = "a".repeat(10000) + "z" + "a".repeat(10000);
        assertEquals("z" + "a".repeat(10000), test.lastSubstring(input));
    }
}

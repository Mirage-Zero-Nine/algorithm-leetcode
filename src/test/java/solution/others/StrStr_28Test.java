package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StrStr_28Test {

    private final StrStr_28 test = new StrStr_28();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.strStr("hello", "ll"));
        assertEquals(0, test.strStr("hello", "hello"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.strStr("hello", ""));
        assertEquals(-1, test.strStr("", "a"));
        assertEquals(-1, test.strStr("aaaaa", "bba"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.strStr("mississippi", "issip"));
    }

    @Test
    public void testNeedleLongerThanHaystack() {
        assertEquals(-1, test.strStr("ab", "abc"));
    }

    @Test
    public void testSingleCharMatch() {
        assertEquals(0, test.strStr("a", "a"));
    }

    @Test
    public void testSingleCharNoMatch() {
        assertEquals(-1, test.strStr("a", "b"));
    }

    @Test
    public void testMatchAtEnd() {
        assertEquals(3, test.strStr("abcdef", "def"));
    }

    @Test
    public void testRepeatedPattern() {
        assertEquals(0, test.strStr("aaa", "a"));
    }

    @Test
    public void testBothEmpty() {
        assertEquals(0, test.strStr("", ""));
    }

    @Test
    public void testGiantCase() {
        String haystack = "a".repeat(10000) + "b";
        assertEquals(-1, test.strStr(haystack, "ab" + "b".repeat(100)));
        assertEquals(9999, test.strStr(haystack, "ab"));
    }
}

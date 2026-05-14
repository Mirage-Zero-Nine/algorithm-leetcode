package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestCommonPrefix_14Test {

    private final LongestCommonPrefix_14 test = new LongestCommonPrefix_14();

    @Test
    public void testHappyCases() {
        assertEquals("fl", test.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        assertEquals("", test.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals("", test.longestCommonPrefix(null));
        assertEquals("abc", test.longestCommonPrefix(new String[]{"abc"}));
    }

    @Test
    public void testLargeCase() {
        assertEquals("a", test.longestCommonPrefix(new String[]{"abc", "abcd", "ab", "a"}));
    }

    @Test
    public void testEmptyArray() {
        assertEquals("", test.longestCommonPrefix(new String[]{}));
    }

    @Test
    public void testEmptyStringInArray() {
        assertEquals("", test.longestCommonPrefix(new String[]{"", "abc"}));
    }

    @Test
    public void testAllSameStrings() {
        assertEquals("hello", test.longestCommonPrefix(new String[]{"hello", "hello", "hello"}));
    }

    @Test
    public void testNoCommonPrefix() {
        assertEquals("", test.longestCommonPrefix(new String[]{"abc", "xyz", "mno"}));
    }

    @Test
    public void testSingleCharStrings() {
        assertEquals("a", test.longestCommonPrefix(new String[]{"a", "a", "a"}));
    }

    @Test
    public void testFirstStringEmpty() {
        assertEquals("", test.longestCommonPrefix(new String[]{""}));
    }

    @Test
    public void testGiantCase() {
        String[] strs = new String[1000];
        for (int i = 0; i < 1000; i++) {
            strs[i] = "prefix" + "x".repeat(100) + i;
        }
        assertEquals("prefix" + "x".repeat(100), test.longestCommonPrefix(strs));
    }
}

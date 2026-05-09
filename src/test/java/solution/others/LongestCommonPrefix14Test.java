package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestCommonPrefix14Test {

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
}

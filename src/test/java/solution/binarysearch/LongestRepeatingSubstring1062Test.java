package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestRepeatingSubstring1062Test {

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
}

package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestDupSubstring1044Test {

    @Test
    public void testHappyCases() {
        assertEquals("ana", new LongestDupSubstring_1044().longestDupSubstring("banana"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals("", new LongestDupSubstring_1044().longestDupSubstring("a"));
        assertEquals("", new LongestDupSubstring_1044().longestDupSubstring("abcde"));
    }

    @Test
    public void testLargeCase() {
        String result = new LongestDupSubstring_1044().longestDupSubstring("aaaaaaa");
        assertEquals(6, result.length());
    }
}

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
}

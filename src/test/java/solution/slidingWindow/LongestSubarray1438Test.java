package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestSubarray1438Test {

    private final LongestSubarray_1438 test = new LongestSubarray_1438();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.longestSubarray(new int[]{8, 2, 4, 7}, 4));
        assertEquals(4, test.longestSubarray(new int[]{10, 1, 2, 4, 7, 2}, 5));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(3, test.longestSubarray(new int[]{4, 2, 2, 2, 4, 4, 2, 2}, 0));
        assertEquals(1, test.longestSubarray(new int[]{1}, 0));
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.longestSubarray(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 4));
    }
}

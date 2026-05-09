package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestSubarray1493Test {

    private final LongestSubarray_1493 test = new LongestSubarray_1493();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.longestSubarray(new int[]{1, 1, 0, 1}));
        assertEquals(5, test.longestSubarray(new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.longestSubarray(new int[]{0}));
        assertEquals(0, test.longestSubarray(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(9, test.longestSubarray(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 0}));
    }
}

package solutions.slidingwindow;

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

    @Test
    public void testAllSameElements() {
        assertEquals(5, test.longestSubarray(new int[]{3, 3, 3, 3, 3}, 0));
    }

    @Test
    public void testLimitZero() {
        assertEquals(1, test.longestSubarray(new int[]{1, 2, 3, 4, 5}, 0));
    }

    @Test
    public void testLimitCoversAll() {
        assertEquals(5, test.longestSubarray(new int[]{1, 2, 3, 4, 5}, 4));
    }

    @Test
    public void testTwoElements() {
        assertEquals(2, test.longestSubarray(new int[]{1, 5}, 4));
        assertEquals(1, test.longestSubarray(new int[]{1, 10}, 4));
    }

    @Test
    public void testDecreasingArray() {
        assertEquals(3, test.longestSubarray(new int[]{10, 8, 6, 4, 2}, 4));
    }

    @Test
    public void testLargeLimitValue() {
        assertEquals(6, test.longestSubarray(new int[]{1, 100, 2, 99, 3, 98}, 1000));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[10000];
        for (int i = 0; i < 10000; i++) {
            arr[i] = i % 10;
        }
        assertEquals(10000, test.longestSubarray(arr, 9));
    }
}

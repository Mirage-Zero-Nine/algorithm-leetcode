package solutions.slidingwindow;

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

    @Test
    public void testAllOnes() {
        // must delete one element, so result is length - 1
        assertEquals(4, test.longestSubarray(new int[]{1, 1, 1, 1, 1}));
    }

    @Test
    public void testAllZeros() {
        assertEquals(0, test.longestSubarray(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void testZeroInMiddle() {
        assertEquals(4, test.longestSubarray(new int[]{1, 1, 0, 1, 1}));
    }

    @Test
    public void testMultipleZeros() {
        assertEquals(2, test.longestSubarray(new int[]{1, 0, 1, 0, 1}));
    }

    @Test
    public void testTwoElements10() {
        assertEquals(1, test.longestSubarray(new int[]{1, 0}));
    }

    @Test
    public void testTwoElements11() {
        assertEquals(1, test.longestSubarray(new int[]{1, 1}));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[10000];
        java.util.Arrays.fill(arr, 1);
        arr[5000] = 0;
        assertEquals(9999, test.longestSubarray(arr));
    }

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvSource({"10,1", "01,1", "101,2", "11,1", "111,2", "000,0", "10101,2", "1101,3", "1110,3", "010,1"})
    void additionalBoundaryCases(String values, int expected) {
        int[] nums = values.chars().map(c -> c - '0').toArray();
        assertEquals(expected, test.longestSubarray(nums));
    }
}

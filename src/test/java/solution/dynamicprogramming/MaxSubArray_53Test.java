package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxSubArray_53Test {

    private final MaxSubArray_53 test = new MaxSubArray_53();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        assertEquals(1, test.maxSubArray(new int[]{1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.maxSubArray(new int[]{-2, -1}));
        assertEquals(0, test.maxSubArray(null));
    }

    @Test
    public void testLargeCase() {
        assertEquals(23, test.maxSubArray(new int[]{5, 4, -1, 7, 8}));
    }

    @Test
    public void testAllNegative() {
        assertEquals(-1, test.maxSubArray(new int[]{-3, -2, -1, -4}));
    }

    @Test
    public void testAllPositive() {
        assertEquals(15, test.maxSubArray(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testSingleNegative() {
        assertEquals(-5, test.maxSubArray(new int[]{-5}));
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, test.maxSubArray(new int[]{}));
    }

    @Test
    public void testZeros() {
        assertEquals(0, test.maxSubArray(new int[]{0, 0, 0}));
    }

    @Test
    public void testMixedWithLargeNegative() {
        assertEquals(10, test.maxSubArray(new int[]{-100, 10, -100}));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[10000];
        for (int i = 0; i < 10000; i++) {
            arr[i] = (i % 2 == 0) ? 1 : -1;
        }
        assertEquals(1, test.maxSubArray(arr));
    }
}

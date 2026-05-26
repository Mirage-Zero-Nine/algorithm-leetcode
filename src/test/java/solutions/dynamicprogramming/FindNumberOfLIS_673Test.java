package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindNumberOfLIS_673Test {

    private final FindNumberOfLIS_673 test = new FindNumberOfLIS_673();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.findNumberOfLIS(new int[]{1, 3, 5, 4, 7}));
        assertEquals(5, test.findNumberOfLIS(new int[]{2, 2, 2, 2, 2}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.findNumberOfLIS(new int[]{}));
        assertEquals(1, test.findNumberOfLIS(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.findNumberOfLIS(new int[]{1, 2, 4, 3, 5, 4, 7, 2}));
    }

    @Test
    public void testStrictlyIncreasing() {
        assertEquals(1, test.findNumberOfLIS(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testStrictlyDecreasing() {
        assertEquals(5, test.findNumberOfLIS(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void testTwoElements() {
        assertEquals(1, test.findNumberOfLIS(new int[]{1, 2}));
        assertEquals(2, test.findNumberOfLIS(new int[]{2, 1}));
    }

    @Test
    public void testMultipleLIS() {
        // [1,2,3] and [1,2,3] via different paths
        assertEquals(4, test.findNumberOfLIS(new int[]{1, 2, 3, 1, 2, 3}));
    }

    @Test
    public void testAllEqual() {
        assertEquals(3, test.findNumberOfLIS(new int[]{7, 7, 7}));
    }

    @Test
    public void testNegativeNumbers() {
        assertEquals(1, test.findNumberOfLIS(new int[]{-3, -2, -1, 0, 1}));
        assertEquals(3, test.findNumberOfLIS(new int[]{-1, 0, -1, 0}));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = i;
        }
        // Strictly increasing -> only 1 LIS
        assertEquals(1, test.findNumberOfLIS(arr));
    }
}

package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinSwaps1151Test {

    private final MinSwaps_1151 test = new MinSwaps_1151();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.minSwaps(new int[]{1, 0, 1, 0, 1}));
        assertEquals(3, test.minSwaps(new int[]{1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.minSwaps(new int[]{1}));
        assertEquals(0, test.minSwaps(new int[]{1, 1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(2, test.minSwaps(new int[]{1, 0, 0, 1, 0, 0, 1, 0, 0, 1}));
    }

    @Test
    public void testAllOnes() {
        assertEquals(0, test.minSwaps(new int[]{1, 1, 1, 1, 1}));
    }

    @Test
    public void testAllZeros() {
        assertEquals(0, test.minSwaps(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void testSingleOne() {
        assertEquals(0, test.minSwaps(new int[]{0, 0, 1, 0, 0}));
    }

    @Test
    public void testOnesAlreadyGrouped() {
        assertEquals(0, test.minSwaps(new int[]{0, 0, 1, 1, 1, 0, 0}));
    }

    @Test
    public void testOnesAtEnds() {
        // ones=3, window size=3, best window has at most 2 ones -> swap=1
        assertEquals(1, test.minSwaps(new int[]{1, 0, 0, 0, 1, 0, 1}));
    }

    @Test
    public void testTwoElements() {
        assertEquals(0, test.minSwaps(new int[]{0, 1}));
        assertEquals(0, test.minSwaps(new int[]{1, 0}));
    }

    @Test
    public void testGiantCase() {
        // 100000 elements: first 50000 are 0, last 50000 are 1 -> already grouped -> 0 swaps
        int[] data = new int[100000];
        for (int i = 50000; i < 100000; i++) data[i] = 1;
        assertEquals(0, test.minSwaps(data));
    }
}

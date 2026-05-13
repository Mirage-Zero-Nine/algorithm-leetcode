package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Rob_198Test {

    private final Rob_198 test = new Rob_198();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.rob(new int[]{1, 2, 3, 1}));
        assertEquals(12, test.rob(new int[]{2, 7, 9, 3, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.rob(new int[]{}));
        assertEquals(1, test.rob(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(9, test.rob(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testTwoElements() {
        assertEquals(2, test.rob(new int[]{1, 2}));
        assertEquals(5, test.rob(new int[]{5, 1}));
    }

    @Test
    public void testAllSameValues() {
        assertEquals(6, test.rob(new int[]{3, 3, 3, 3}));
    }

    @Test
    public void testAlternatingHighLow() {
        assertEquals(30, test.rob(new int[]{10, 1, 10, 1, 10}));
    }

    @Test
    public void testSingleZero() {
        assertEquals(0, test.rob(new int[]{0}));
    }

    @Test
    public void testAllZeros() {
        assertEquals(0, test.rob(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void testDecreasingSequence() {
        assertEquals(9, test.rob(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = i + 1;
        }
        // Sum of even-indexed (0-based) elements from index 1: 2+4+6+...+100 = 2550
        // vs odd-indexed: 1+3+5+...+99 = 2500
        assertEquals(2550, test.rob(nums));
    }
}

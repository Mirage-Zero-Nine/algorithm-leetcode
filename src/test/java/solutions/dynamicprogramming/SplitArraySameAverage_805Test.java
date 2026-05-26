package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SplitArraySameAverage_805Test {

    private final SplitArraySameAverage_805 test = new SplitArraySameAverage_805();

    @Test
    public void testHappyCases() {
        assertTrue(test.splitArraySameAverage(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
        assertFalse(test.splitArraySameAverage(new int[]{3, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.splitArraySameAverage(new int[]{1}));
        assertFalse(test.splitArraySameAverage(new int[]{1, 7, 8, 10}));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.splitArraySameAverage(new int[]{4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 5}));
    }

    @Test
    public void testAllSameElements() {
        assertTrue(test.splitArraySameAverage(new int[]{5, 5, 5, 5}));
    }

    @Test
    public void testTwoElementsEqual() {
        assertTrue(test.splitArraySameAverage(new int[]{3, 3}));
    }

    @Test
    public void testTwoElementsDifferent() {
        assertFalse(test.splitArraySameAverage(new int[]{1, 3}));
    }

    @Test
    public void testFalseCase18_10_5_3() {
        assertFalse(test.splitArraySameAverage(new int[]{18, 10, 5, 3}));
    }

    @Test
    public void testLargeFalseCase() {
        assertFalse(test.splitArraySameAverage(new int[]{60, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30}));
    }

    @Test
    public void testConsecutiveNumbers() {
        assertTrue(test.splitArraySameAverage(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }

    @Test
    public void testEmptyLikeArray() {
        assertFalse(test.splitArraySameAverage(new int[]{0}));
    }

    @Test
    public void testZeroElements() {
        assertTrue(test.splitArraySameAverage(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void testGiantCase() {
        // 30 elements all same value - should be true since any split has same average
        int[] arr = new int[30];
        java.util.Arrays.fill(arr, 7);
        assertTrue(test.splitArraySameAverage(arr));
    }
}

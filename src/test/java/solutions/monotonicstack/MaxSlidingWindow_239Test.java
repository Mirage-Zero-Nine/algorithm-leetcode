package solutions.monotonicstack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MaxSlidingWindow_239Test {
    private final MaxSlidingWindow_239 solver = new MaxSlidingWindow_239();

    @Test public void testExample() {
        assertArrayEquals(new int[]{3, 3, 5, 5, 6, 7}, solver.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
    }

    @Test public void testK1() {
        assertArrayEquals(new int[]{1, 2, 3}, solver.maxSlidingWindow(new int[]{1, 2, 3}, 1));
    }

    @Test public void testKEqualsLength() {
        assertArrayEquals(new int[]{5}, solver.maxSlidingWindow(new int[]{1, 5, 3, 2, 4}, 5));
    }

    @Test public void testDecreasing() {
        assertArrayEquals(new int[]{5, 4, 3}, solver.maxSlidingWindow(new int[]{5, 4, 3, 2, 1}, 3));
    }

    @Test public void testEmpty() {
        assertArrayEquals(new int[]{}, solver.maxSlidingWindow(new int[]{}, 0));
    }

    @Test public void testAllSame() {
        assertArrayEquals(new int[]{2, 2, 2}, solver.maxSlidingWindow(new int[]{2, 2, 2, 2}, 2));
    }

    @Test public void testIncreasing() {
        assertArrayEquals(new int[]{3, 4, 5}, solver.maxSlidingWindow(new int[]{1, 2, 3, 4, 5}, 3));
    }

    @Test public void testNegativeValues() {
        assertArrayEquals(new int[]{-1, -1, -1}, solver.maxSlidingWindow(new int[]{-3, -1, -2, -4, -1}, 3));
    }

    @Test public void testSingleElement() {
        assertArrayEquals(new int[]{5}, solver.maxSlidingWindow(new int[]{5}, 1));
    }

    @Test public void testGiantCase() {
        int n = 100000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i;
        int[] result = solver.maxSlidingWindow(arr, 100);
        assertEquals(n - 99, result.length);
        assertEquals(n - 1, result[result.length - 1]);
    }
    @Test public void testWindowTwo() { assertArrayEquals(new int[]{4,4,5,5}, solver.maxSlidingWindow(new int[]{1,4,2,5,3},2)); }
    @Test public void testWindowFour() { assertArrayEquals(new int[]{4,5}, solver.maxSlidingWindow(new int[]{1,3,2,4,5},4)); }
    @Test public void testPeakExpires() { assertArrayEquals(new int[]{9,3,6}, solver.maxSlidingWindow(new int[]{9,1,2,3,6},3)); }
    @Test public void testNegativeMixed() { assertArrayEquals(new int[]{-2,-2,-1}, solver.maxSlidingWindow(new int[]{-5,-2,-3,-1},2)); }
    @Test public void testZeroValues() { assertArrayEquals(new int[]{0,0,1}, solver.maxSlidingWindow(new int[]{0,0,0,1},2)); }
    @Test public void testTwoElements() { assertArrayEquals(new int[]{7}, solver.maxSlidingWindow(new int[]{-1,7},2)); }
    @Test public void testAlternating() { assertArrayEquals(new int[]{5,5,5,5}, solver.maxSlidingWindow(new int[]{5,1,5,1,5},2)); }
    @Test public void testLargeMagnitude() { assertArrayEquals(new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE}, solver.maxSlidingWindow(new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE,0},2)); }
    @Test public void testWindowThreeValley() { assertArrayEquals(new int[]{5,4,5}, solver.maxSlidingWindow(new int[]{5,1,4,2,5},3)); }
    @Test public void testRepeatedCall() { solver.maxSlidingWindow(new int[]{9,8},2); assertArrayEquals(new int[]{1,2}, solver.maxSlidingWindow(new int[]{1,2},1)); }
}

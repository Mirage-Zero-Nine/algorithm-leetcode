package solutions.monotonicstack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxSlidingWindow_239Test {
    private final MaxSlidingWindow_239 solver = new MaxSlidingWindow_239();

    @Test
    public void testExample() {
        assertArrayEquals(new int[]{3, 3, 5, 5, 6, 7}, solver.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
    }

    @Test
    public void testK1() {
        assertArrayEquals(new int[]{1, 2, 3}, solver.maxSlidingWindow(new int[]{1, 2, 3}, 1));
    }

    @Test
    public void testKEqualsLength() {
        assertArrayEquals(new int[]{5}, solver.maxSlidingWindow(new int[]{1, 5, 3, 2, 4}, 5));
    }

    @Test
    public void testDecreasing() {
        assertArrayEquals(new int[]{5, 4, 3}, solver.maxSlidingWindow(new int[]{5, 4, 3, 2, 1}, 3));
    }

    @Test
    public void testEmpty() {
        assertArrayEquals(new int[]{}, solver.maxSlidingWindow(new int[]{}, 0));
    }

    @Test
    public void testAllSame() {
        assertArrayEquals(new int[]{2, 2, 2}, solver.maxSlidingWindow(new int[]{2, 2, 2, 2}, 2));
    }

    @Test
    public void testIncreasing() {
        assertArrayEquals(new int[]{3, 4, 5}, solver.maxSlidingWindow(new int[]{1, 2, 3, 4, 5}, 3));
    }

    @Test
    public void testNegativeValues() {
        assertArrayEquals(new int[]{-1, -1, -1}, solver.maxSlidingWindow(new int[]{-3, -1, -2, -4, -1}, 3));
    }

    @Test
    public void testSingleElement() {
        assertArrayEquals(new int[]{5}, solver.maxSlidingWindow(new int[]{5}, 1));
    }

    @Test
    public void testGiantCase() {
        int n = 100000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i;
        int[] result = solver.maxSlidingWindow(arr, 100);
        assertEquals(n - 99, result.length);
        for (int i = 0; i < result.length; i++) {
            assertEquals(i + 99, result[i]);
        }
    }

    @Test
    public void testMaximumSizeInputWithinValueBounds() {
        int[] input = new int[100000];
        for (int i = 0; i < input.length; i++) {
            input[i] = i % 2 == 0 ? 10000 : -10000;
        }

        int[] result = solver.maxSlidingWindow(input, 100);
        assertEquals(input.length - 99, result.length);
        for (int maximum : result) {
            assertEquals(10000, maximum);
        }
    }

    @Test
    public void testMaximumWindowRetainsEqualCandidates() {
        int[] input = new int[100000];
        java.util.Arrays.fill(input, -10000);

        assertArrayEquals(new int[]{-10000}, solver.maxSlidingWindow(input.clone(), input.length));
    }

    @Test
    public void testWindowTwo() {
        assertArrayEquals(new int[]{4, 4, 5, 5}, solver.maxSlidingWindow(new int[]{1, 4, 2, 5, 3}, 2));
    }

    @Test
    public void testWindowFour() {
        assertArrayEquals(new int[]{4, 5}, solver.maxSlidingWindow(new int[]{1, 3, 2, 4, 5}, 4));
    }

    @Test
    public void testPeakExpires() {
        assertArrayEquals(new int[]{9, 3, 6}, solver.maxSlidingWindow(new int[]{9, 1, 2, 3, 6}, 3));
    }

    @Test
    public void testNegativeMixed() {
        assertArrayEquals(new int[]{-2, -2, -1}, solver.maxSlidingWindow(new int[]{-5, -2, -3, -1}, 2));
    }

    @Test
    public void testZeroValues() {
        assertArrayEquals(new int[]{0, 0, 1}, solver.maxSlidingWindow(new int[]{0, 0, 0, 1}, 2));
    }

    @Test
    public void testTwoElements() {
        assertArrayEquals(new int[]{7}, solver.maxSlidingWindow(new int[]{-1, 7}, 2));
    }

    @Test
    public void testAlternating() {
        assertArrayEquals(new int[]{5, 5, 5, 5}, solver.maxSlidingWindow(new int[]{5, 1, 5, 1, 5}, 2));
    }

    @Test
    public void testLargeMagnitude() {
        assertArrayEquals(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}, solver.maxSlidingWindow(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 0}, 2));
    }

    @Test
    public void testWindowThreeValley() {
        assertArrayEquals(new int[]{5, 4, 5}, solver.maxSlidingWindow(new int[]{5, 1, 4, 2, 5}, 3));
    }

    @Test
    public void testRepeatedCall() {
        solver.maxSlidingWindow(new int[]{9, 8}, 2);
        assertArrayEquals(new int[]{1, 2}, solver.maxSlidingWindow(new int[]{1, 2}, 1));
    }

    @Test
    public void testTailRemovalUsesTheLastCandidate() {
        assertArrayEquals(new int[]{5, 4}, solver.maxSlidingWindow(new int[]{5, 1, 4, 2}, 3));
    }

    @Test
    public void testNullInputReturnsNoWindows() {
        assertArrayEquals(new int[0], solver.maxSlidingWindow(null, 1));
    }

    @Test
    public void testZeroWindowSizeReturnsNoWindows() {
        assertArrayEquals(new int[0], solver.maxSlidingWindow(new int[]{1, 2}, 0));
    }

    @Test
    public void testNegativeWindowSizeReturnsNoWindows() {
        assertArrayEquals(new int[0], solver.maxSlidingWindow(new int[]{1, 2}, -1));
    }

    @Test
    public void testWindowLargerThanInputReturnsNoWindows() {
        assertArrayEquals(new int[0], solver.maxSlidingWindow(new int[]{1, 2}, 3));
    }

    @Test
    public void testInputIsNotModified() {
        int[] input = {4, 1, 7, 3, 2};
        int[] original = input.clone();

        assertArrayEquals(new int[]{7, 7, 7}, solver.maxSlidingWindow(input, 3));
        assertArrayEquals(original, input);
    }

    @Test
    public void testAgainstBruteForceForAllSmallArrays() {
        for (int length = 1; length <= 6; length++) {
            int combinations = 1;
            for (int i = 0; i < length; i++) combinations *= 5;

            for (int encoded = 0; encoded < combinations; encoded++) {
                int[] input = decodeArray(encoded, length);
                for (int k = 1; k <= length; k++) {
                    assertArrayEquals(bruteForceMaxima(input, k), solver.maxSlidingWindow(input.clone(), k),
                            "input=" + java.util.Arrays.toString(input) + ", k=" + k);
                }
            }
        }
    }

    private int[] decodeArray(int encoded, int length) {
        int[] values = new int[length];
        for (int i = 0; i < length; i++) {
            values[i] = encoded % 5 - 2;
            encoded /= 5;
        }
        return values;
    }

    private int[] bruteForceMaxima(int[] input, int k) {
        int[] maxima = new int[input.length - k + 1];
        for (int start = 0; start < maxima.length; start++) {
            int maximum = input[start];
            for (int i = start + 1; i < start + k; i++) {
                maximum = Math.max(maximum, input[i]);
            }
            maxima[start] = maximum;
        }
        return maxima;
    }
}

package solutions.greedy;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MaximumGap_164Test {
    private final MaximumGap_164 solution = new MaximumGap_164();

    @Test
    void testBasic() {
        assertEquals(3, solution.maximumGap(new int[]{3, 6, 9, 1}));
    }

    @Test
    void testNoGap() {
        assertEquals(0, solution.maximumGap(new int[]{10}));
    }

    @Test
    void testTwoElements() {
        assertEquals(5, solution.maximumGap(new int[]{1, 6}));
    }

    @Test
    void testLargeGap() {
        assertEquals(5, solution.maximumGap(new int[]{1, 10, 5}));
    }

    @Test
    void testConsecutive() {
        assertEquals(1, solution.maximumGap(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void testEmptyArray() {
        assertEquals(0, solution.maximumGap(new int[]{}));
    }

    @Test
    void testAllSame() {
        assertEquals(0, solution.maximumGap(new int[]{5, 5, 5, 5}));
    }

    @Test
    void testDuplicates() {
        assertEquals(3, solution.maximumGap(new int[]{1, 1, 1, 4}));
    }

    @Test
    void testLargeValues() {
        assertEquals(999999999, solution.maximumGap(new int[]{1, 1000000000}));
    }

    @Test
    void testNegativeLike() {
        // array contains zeros
        assertEquals(10, solution.maximumGap(new int[]{0, 10, 20, 30}));
    }

    @Test
    void testGiantCase() {
        int[] arr = new int[10000];
        for (int i = 0; i < 10000; i++) arr[i] = i * 2;
        // sorted: 0,2,4,...,19998 => all gaps are 2
        assertEquals(2, solution.maximumGap(arr));
    }

    @Test
    void testReverseSorted() {
        // Implementation must sort; reverse order should still find max gap
        assertEquals(3, solution.maximumGap(new int[]{9, 6, 3, 1}));
    }

    @Test
    void testAlreadySortedAscending() {
        // [2, 5, 8, 20] -> gaps: 3, 3, 12 -> max = 12
        assertEquals(12, solution.maximumGap(new int[]{2, 5, 8, 20}));
    }

    @Test
    void testLargeValuesNearMaxInt() {
        // Values near Integer.MAX_VALUE; subtraction must not overflow
        int max = Integer.MAX_VALUE;
        assertEquals(1, solution.maximumGap(new int[]{max - 2, max - 1, max}));
    }

    @Test
    void testLargeSpreadNearMaxInt() {
        // Large spread: 0 to Integer.MAX_VALUE with two elements
        assertEquals(Integer.MAX_VALUE, solution.maximumGap(new int[]{0, Integer.MAX_VALUE}));
    }

    @Test
    void testMinAndMaxValuesTogether() {
        // 0 and MAX_VALUE with middle value
        int max = Integer.MAX_VALUE;
        // sorted: [0, 1000000000, MAX_VALUE] -> gaps: 1000000000, 1147483647 -> max = 1147483647
        assertEquals(max - 1000000000, solution.maximumGap(new int[]{0, 1000000000, max}));
    }

    @Test
    void testPropertyReversedInputSameResult() {
        int[] input = {15, 3, 8, 22, 1, 50, 7};
        int[] reversed = new int[input.length];
        for (int i = 0; i < input.length; i++) reversed[i] = input[input.length - 1 - i];
        assertEquals(solution.maximumGap(input), solution.maximumGap(reversed));
    }

    @Test
    void testAllIdenticalLargeArray() {
        int[] arr = new int[100];
        Arrays.fill(arr, 42);
        assertEquals(0, solution.maximumGap(arr));
    }

    @Test
    void testTwoElementsLargeGap() {
        assertEquals(Integer.MAX_VALUE - 1, solution.maximumGap(new int[]{1, Integer.MAX_VALUE}));
    }

    @Test
    void testLargeRandomCrossCheck() {
        // 1000-element random array with seed 42L, cross-check against sort-and-scan
        Random rng = new Random(42L);
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) arr[i] = rng.nextInt(Integer.MAX_VALUE);

        // Reference: sort and scan for max consecutive diff
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        int expected = 0;
        for (int i = 1; i < sorted.length; i++) {
            expected = Math.max(expected, sorted[i] - sorted[i - 1]);
        }

        assertEquals(expected, solution.maximumGap(arr));
    }
}

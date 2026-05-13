package solution.array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinSumOfLengths_1477Test {
    private final MinSumOfLengths_1477 solution = new MinSumOfLengths_1477();

    @Test
    void testBasic() {
        assertEquals(2, solution.minSumOfLengths(new int[]{3, 2, 2, 4, 3}, 3));
    }

    @Test
    void testNoSolution() {
        assertEquals(-1, solution.minSumOfLengths(new int[]{1, 6, 1}, 7));
    }

    @Test
    void testMultipleSolutions() {
        assertEquals(-1, solution.minSumOfLengths(new int[]{4, 3, 2, 6, 2, 3, 4}, 6));
    }

    @Test
    void testLongArray() {
        assertEquals(6, solution.minSumOfLengths(new int[]{1, 1, 1, 2, 2, 2, 4, 4}, 6));
    }

    @Test
    void testSingleElement() {
        assertEquals(-1, solution.minSumOfLengths(new int[]{5}, 5));
    }

    @Test
    void testTwoExactElements() {
        assertEquals(2, solution.minSumOfLengths(new int[]{5, 5}, 5));
    }

    @Test
    void testAllOnes() {
        assertEquals(4, solution.minSumOfLengths(new int[]{1, 1, 1, 1, 1, 1}, 2));
    }

    @Test
    void testTargetNotReachable() {
        assertEquals(-1, solution.minSumOfLengths(new int[]{1, 2, 3}, 100));
    }

    @Test
    void testAdjacentSubarrays() {
        assertEquals(4, solution.minSumOfLengths(new int[]{1, 2, 1, 2}, 3));
    }

    @Test
    void testLargerTarget() {
        assertEquals(6, solution.minSumOfLengths(new int[]{1, 1, 1, 2, 2, 2, 4, 4}, 6));
    }

    @Test
    void testGiantCase() {
        int[] arr = new int[10000];
        for (int i = 0; i < 10000; i++) arr[i] = 1;
        // target=1, two subarrays of length 1 each => sum = 2
        assertEquals(2, solution.minSumOfLengths(arr, 1));
    }
}

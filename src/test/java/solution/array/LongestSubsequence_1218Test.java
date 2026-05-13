package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LongestSubsequence_1218Test {
    private final LongestSubsequence_1218 solution = new LongestSubsequence_1218();

    @Test
    void testBasic() {
        assertEquals(4, solution.longestSubsequence(new int[]{1, 2, 3, 4}, 1));
    }

    @Test
    void testNegativeDifference() {
        assertEquals(4, solution.longestSubsequence(new int[]{1, 5, 7, 8, 5, 3, 4, 2, 1}, -2));
    }

    @Test
    void testSingleElement() {
        assertEquals(1, solution.longestSubsequence(new int[]{5}, 2));
    }

    @Test
    void testNoSequence() {
        assertEquals(4, solution.longestSubsequence(new int[]{1, 3, 5, 7}, 2));
    }

    @Test
    void testZeroDifference() {
        assertEquals(3, solution.longestSubsequence(new int[]{1, 1, 1, 2}, 0));
    }

    @Test
    void testAllSameElements() {
        assertEquals(5, solution.longestSubsequence(new int[]{3, 3, 3, 3, 3}, 0));
    }

    @Test
    void testLargeDifference() {
        assertEquals(1, solution.longestSubsequence(new int[]{1, 2, 3, 4}, 100));
    }

    @Test
    void testNegativeElements() {
        assertEquals(4, solution.longestSubsequence(new int[]{-1, -3, -5, -7}, -2));
    }

    @Test
    void testMixedPositiveNegative() {
        assertEquals(4, solution.longestSubsequence(new int[]{-2, 0, 2, 4}, 2));
    }

    @Test
    void testGiantArray() {
        int n = 100000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i;
        assertEquals(n, solution.longestSubsequence(arr, 1));
    }
}

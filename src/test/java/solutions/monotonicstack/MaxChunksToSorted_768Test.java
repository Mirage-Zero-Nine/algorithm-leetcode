package solutions.monotonicstack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MaxChunksToSorted_768Test {
    private final MaxChunksToSorted_768 solution = new MaxChunksToSorted_768();

    @Test
    void testBasic() {
        assertEquals(1, solution.maxChunksToSorted(new int[]{5, 4, 3, 2, 1}));
        assertEquals(4, solution.maxChunksToSorted(new int[]{2, 1, 3, 4, 4}));
    }

    @Test
    void testSorted() {
        assertEquals(5, solution.maxChunksToSorted(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void testSingleElement() {
        assertEquals(1, solution.maxChunksToSorted(new int[]{1}));
    }

    @Test
    void testSortArray() {
        assertEquals(4, solution.sortArray(new int[]{2, 1, 3, 4, 4}));
    }

    @Test
    void testDuplicates() {
        assertEquals(2, solution.maxChunksToSorted(new int[]{1, 1, 0, 0, 1}));
    }

    @Test
    void testAllSame() {
        assertEquals(5, solution.maxChunksToSorted(new int[]{3, 3, 3, 3, 3}));
    }

    @Test
    void testTwoElements() {
        assertEquals(1, solution.maxChunksToSorted(new int[]{2, 1}));
        assertEquals(2, solution.maxChunksToSorted(new int[]{1, 2}));
    }

    @Test
    void testEmpty() {
        assertEquals(0, solution.maxChunksToSorted(new int[]{}));
    }

    @Test
    void testNegativeValues() {
        assertEquals(3, solution.maxChunksToSorted(new int[]{3, -1, 2, 4, 5}));
    }

    @Test
    void testSortArrayNegative() {
        assertEquals(1, solution.sortArray(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    void testGiantCase() {
        int n = 100000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        assertEquals(n, solution.maxChunksToSorted(arr));
    }

    @Test
    void testBothApproachesOnSignedAndDuplicateInputs() {
        int[][] cases = {{}, {-1}, {-1, -1}, {-1, 0, -1, 0}, {2, 0, 1},
                {1, 0, 0, 1}, {4, 3, 2, 1, 0}, {0, 2, 1, 3, 4},
                {2, 2, 1, 1, 2}, {Integer.MAX_VALUE, Integer.MIN_VALUE, 0}};
        for (int[] values : cases) {
            int expected = oracle(values);
            assertEquals(expected, solution.maxChunksToSorted(values.clone()));
            assertEquals(expected, solution.sortArray(values.clone()));
        }
    }

    private int oracle(int[] values) {
        int[] sorted = java.util.Arrays.copyOf(values, values.length);
        java.util.Arrays.sort(sorted);
        int chunks = 0;
        for (int end = 0; end < values.length; end++) {
            int[] left = java.util.Arrays.copyOf(values, end + 1);
            int[] expected = java.util.Arrays.copyOf(sorted, end + 1);
            java.util.Arrays.sort(left);
            if (java.util.Arrays.equals(left, expected)) chunks++;
        }
        return chunks;
    }

    @Test void testSingleNegative() { assertEquals(1,solution.maxChunksToSorted(new int[]{-1})); }
    @Test void testAlreadySortedNegative() { assertEquals(4,solution.maxChunksToSorted(new int[]{-3,-2,-1,0})); }
    @Test void testReverseDuplicates() { assertEquals(1,solution.maxChunksToSorted(new int[]{2,2,1,1})); }
    @Test void testInterleavedDuplicates() { assertEquals(1,solution.maxChunksToSorted(new int[]{1,0,1,0})); }
    @Test void testOneDisplaced() { assertEquals(3,solution.maxChunksToSorted(new int[]{0,2,1,3})); }
    @Test void testSignedExtremes() { assertEquals(1,solution.maxChunksToSorted(new int[]{Integer.MAX_VALUE,Integer.MIN_VALUE})); }
    @Test void testRepeatedCall() { solution.maxChunksToSorted(new int[]{2,1}); assertEquals(3,solution.maxChunksToSorted(new int[]{1,2,3})); }
    @Test void testBothMethodsSmall() { int[] a={3,1,2,0}; assertEquals(1,solution.maxChunksToSorted(a.clone())); assertEquals(1,solution.sortArray(a.clone())); }
}

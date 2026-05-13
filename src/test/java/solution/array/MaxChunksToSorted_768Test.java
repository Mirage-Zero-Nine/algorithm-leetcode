package solution.array;

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
}

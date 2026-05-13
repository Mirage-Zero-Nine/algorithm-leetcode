package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MaxChunksToSorted_769Test {
    private final MaxChunksToSorted_769 solution = new MaxChunksToSorted_769();

    @Test
    void testBasic() {
        assertEquals(1, solution.maxChunksToSorted(new int[]{4, 3, 2, 1, 0}));
        assertEquals(4, solution.maxChunksToSorted(new int[]{1, 0, 2, 3, 4}));
    }

    @Test
    void testSorted() {
        assertEquals(5, solution.maxChunksToSorted(new int[]{0, 1, 2, 3, 4}));
    }

    @Test
    void testSingleElement() {
        assertEquals(1, solution.maxChunksToSorted(new int[]{0}));
    }

    @Test
    void testTwoElements() {
        assertEquals(1, solution.maxChunksToSorted(new int[]{1, 0}));
        assertEquals(2, solution.maxChunksToSorted(new int[]{0, 1}));
    }

    @Test
    void testEmpty() {
        assertEquals(0, solution.maxChunksToSorted(new int[]{}));
    }

    @Test
    void testThreeChunks() {
        assertEquals(4, solution.maxChunksToSorted(new int[]{0, 2, 1, 3, 4}));
    }

    @Test
    void testLastSwap() {
        assertEquals(4, solution.maxChunksToSorted(new int[]{0, 1, 2, 4, 3}));
    }

    @Test
    void testReversedMiddle() {
        assertEquals(6, solution.maxChunksToSorted(new int[]{2, 1, 0, 3, 4, 5, 6, 8, 7}));
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
    void testGiantReversed() {
        int n = 100000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = n - 1 - i;
        }
        assertEquals(1, solution.maxChunksToSorted(arr));
    }
}

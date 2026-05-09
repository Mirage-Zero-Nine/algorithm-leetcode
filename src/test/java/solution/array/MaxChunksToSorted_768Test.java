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
}

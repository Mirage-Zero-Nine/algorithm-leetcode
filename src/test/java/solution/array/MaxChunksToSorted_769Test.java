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
}

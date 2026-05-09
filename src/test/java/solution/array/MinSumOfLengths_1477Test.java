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
}

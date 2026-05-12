package solution.array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinFallingPathSum_931Test {
    private final MinFallingPathSum_931 solution = new MinFallingPathSum_931();

    @Test
    void testBasic() {
        assertEquals(13, solution.minFallingPathSum(new int[][]{{2, 1, 3}, {6, 5, 4}, {7, 8, 9}}));
    }

    @Test
    void testSingleRow() {
        assertEquals(5, solution.minFallingPathSum(new int[][]{{5}}));
    }

    @Test
    void testNegatives() {
        assertEquals(-59, solution.minFallingPathSum(new int[][]{{-19, 57}, {-40, -5}}));
    }

    @Test
    void testTwoRows() {
        assertEquals(5, solution.minFallingPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }

    @Test
    void testAllNegative() {
        assertEquals(-6, solution.minFallingPathSum(new int[][]{{-1, -2}, {-3, -4}}));
    }
}

package solution.backtracking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TotalNQueens_52Test {
    private final TotalNQueens_52 solution = new TotalNQueens_52();

    @Test
    void testFour() {
        assertEquals(2, solution.totalNQueens(4));
    }

    @Test
    void testOne() {
        assertEquals(1, solution.totalNQueens(1));
    }

    @Test
    void testEight() {
        assertEquals(92, solution.totalNQueens(8));
    }

    @Test
    void testTwo() {
        assertEquals(0, solution.totalNQueens(2));
    }

    @Test
    void testThree() {
        assertEquals(0, solution.totalNQueens(3));
    }
}

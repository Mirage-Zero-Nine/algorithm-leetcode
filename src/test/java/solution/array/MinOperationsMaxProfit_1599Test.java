package solution.array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinOperationsMaxProfit_1599Test {
    private final MinOperationsMaxProfit_1599 solution = new MinOperationsMaxProfit_1599();

    @Test
    void testBasic() {
        assertEquals(3, solution.minOperationsMaxProfit(new int[]{8, 3}, 5, 6));
    }

    @Test
    void testNoProfit() {
        assertEquals(7, solution.minOperationsMaxProfit(new int[]{10, 9, 6}, 6, 4));
    }

    @Test
    void testLargeQueue() {
        assertEquals(-1, solution.minOperationsMaxProfit(new int[]{3, 4, 0, 5, 1}, 1, 92));
    }

    @Test
    void testSingleRotation() {
        assertEquals(9, solution.minOperationsMaxProfit(new int[]{10, 10, 6, 4, 7}, 3, 8));
    }

    @Test
    void testMaxProfit() {
        assertEquals(6, solution.minOperationsMaxProfit(new int[]{2, 8, 2, 8, 2}, 5, 6));
    }
}

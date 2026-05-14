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

    @Test
    void testBoardingCostTooLow() {
        // 4 * 1 - 5 = -1 < 0 → always -1
        assertEquals(-1, solution.minOperationsMaxProfit(new int[]{10, 10, 10}, 1, 5));
    }

    @Test
    void testSingleCustomer() {
        // 1 customer, boardingCost=10, runningCost=5 → profit = 10-5=5 > 0, 1 rotation
        assertEquals(1, solution.minOperationsMaxProfit(new int[]{1}, 10, 5));
    }

    @Test
    void testExactlyFourPerRotation() {
        // 4 customers arrive, boardingCost=5, runningCost=10 → profit = 20-10=10 > 0, 1 rotation
        assertEquals(1, solution.minOperationsMaxProfit(new int[]{4}, 5, 10));
    }

    @Test
    void testZeroCustomers() {
        // no customers → profit never positive → -1
        assertEquals(-1, solution.minOperationsMaxProfit(new int[]{0, 0, 0}, 5, 6));
    }

    @Test
    void testGiantCustomerArray() {
        int[] customers = new int[1000];
        for (int i = 0; i < 1000; i++) customers[i] = 4;
        // each rotation: 4*5 - 6 = 14 profit, all served in 1000 rotations
        int result = solution.minOperationsMaxProfit(customers, 5, 6);
        assertEquals(1000, result);
    }
}

package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxProfit_188Test {

    private final MaxProfit_188 test = new MaxProfit_188();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.maxProfit(2, new int[]{2, 4, 1}));
        assertEquals(5, test.maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxProfit(2, new int[]{1}));
        assertEquals(0, test.maxProfit(0, new int[]{1, 2, 3}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(9, test.maxProfit(3, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }

    @Test
    public void testEmptyPrices() {
        assertEquals(0, test.maxProfit(2, new int[]{}));
    }

    @Test
    public void testDecreasingPrices() {
        assertEquals(0, test.maxProfit(2, new int[]{7, 6, 4, 3, 1}));
    }

    @Test
    public void testSingleTransaction() {
        assertEquals(6, test.maxProfit(1, new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testKLargerThanHalfN() {
        assertEquals(15, test.maxProfit(100, new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0}));
    }

    @Test
    public void testTwoTransactions() {
        assertEquals(6, test.maxProfit(2, new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
    }

    @Test
    public void testDpOptimized() {
        assertEquals(2, test.dpOptimized(2, new int[]{2, 4, 1}));
        assertEquals(7, test.dpOptimized(2, new int[]{3, 2, 6, 5, 0, 3}));
    }

    @Test
    public void testStateMachine() {
        assertEquals(2, test.stateMachine(2, new int[]{2, 4, 1}));
        assertEquals(7, test.stateMachine(2, new int[]{3, 2, 6, 5, 0, 3}));
    }

    @Test
    public void testGiantCase() {
        int[] prices = new int[1000];
        for (int i = 0; i < 1000; i++) {
            prices[i] = (i % 3 == 0) ? 1 : (i % 3 == 1) ? 10 : 5;
        }
        int result = test.maxProfit(500, prices);
        assertEquals(result, test.dpOptimized(500, prices));
    }
}

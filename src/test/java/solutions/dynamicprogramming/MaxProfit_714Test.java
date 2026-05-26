package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxProfit_714Test {

    private final MaxProfit_714 test = new MaxProfit_714();

    @Test
    public void testHappyCases() {
        assertEquals(8, test.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
        assertEquals(6, test.maxProfit(new int[]{1, 3, 7, 5, 10, 3}, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxProfit(null, 1));
        assertEquals(0, test.maxProfit(new int[]{1}, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.maxProfit(new int[]{1, 2, 3, 4, 5, 6}, 2));
    }

    @Test
    public void testNoProfit() {
        // Decreasing prices, no transaction is profitable
        assertEquals(0, test.maxProfit(new int[]{9, 8, 7, 6, 5}, 1));
    }

    @Test
    public void testFeeExceedsProfit() {
        // Profit from any single transaction is less than fee
        assertEquals(0, test.maxProfit(new int[]{1, 2, 3, 4}, 5));
    }

    @Test
    public void testZeroFee() {
        // No fee, buy low sell high every time price goes up
        assertEquals(5, test.maxProfit(new int[]{1, 2, 3, 4, 5, 6}, 0));
    }

    @Test
    public void testTwoElements() {
        assertEquals(0, test.maxProfit(new int[]{5, 3}, 1));
        assertEquals(1, test.maxProfit(new int[]{3, 5}, 1));
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, test.maxProfit(new int[]{}, 1));
    }

    @Test
    public void testOptimizedMethod() {
        assertEquals(8, test.maxProfitOptimized(new int[]{1, 3, 2, 8, 4, 9}, 2));
        assertEquals(6, test.maxProfitOptimized(new int[]{1, 3, 7, 5, 10, 3}, 3));
        assertEquals(0, test.maxProfitOptimized(null, 1));
    }

    @Test
    public void testGiantCase() {
        int[] prices = new int[10000];
        for (int i = 0; i < 10000; i++) {
            prices[i] = i % 100; // repeating pattern 0-99
        }
        int result = test.maxProfit(prices, 2);
        // Should be positive and computable
        assertEquals(result, test.maxProfitOptimized(prices, 2));
    }
}

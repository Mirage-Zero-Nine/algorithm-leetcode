package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MaxProfit_309Test {

    private final MaxProfit_309 test = new MaxProfit_309();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.maxProfit(new int[]{1, 2, 3, 0, 2}));
        assertEquals(3, test.maxProfit(new int[]{1, 2, 4}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxProfit(null));
        assertEquals(0, test.maxProfit(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, test.maxProfit(new int[]{6, 1, 3, 2, 4, 7}));
    }

    @Test
    public void testDecreasingPrices() {
        assertEquals(0, test.maxProfit(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void testTwoElements() {
        assertEquals(1, test.maxProfit(new int[]{1, 2}));
    }

    @Test
    public void testTwoElementsNoProfit() {
        assertEquals(0, test.maxProfit(new int[]{2, 1}));
    }

    @Test
    public void testCooldownEffect() {
        // buy day0, sell day1 (+1), cooldown day2, buy day3, sell day4 (+2) = 3
        assertEquals(3, test.maxProfit(new int[]{1, 2, 3, 0, 2}));
    }

    @Test
    public void testOptimizedMethod() {
        assertEquals(3, test.maxProfitOptimized(new int[]{1, 2, 3, 0, 2}));
        assertEquals(0, test.maxProfitOptimized(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, test.maxProfit(new int[]{}));
    }

    @Test
    public void testGiantCase() {
        int[] prices = new int[100];
        for (int i = 0; i < 100; i++) {
            prices[i] = (i % 3 == 0) ? 1 : 5;
        }
        int result = test.maxProfit(prices);
        assertTrue(result > 0);
    }

    @Test
    public void testAllSamePrice() {
        assertEquals(0, test.maxProfit(new int[]{3, 3, 3, 3, 3}));
    }
}

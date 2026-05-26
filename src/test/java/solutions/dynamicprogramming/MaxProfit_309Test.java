package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
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

    @Test
    public void testStrictlyAscending() {
        // With cooldown, one transaction (buy first, sell last) beats multiple sell+cooldown+buy cycles
        // [1,2,3,4,5]: buy@1 sell@5 = 4, vs buy@1 sell@2 cool buy@4 sell@5 = 2
        assertEquals(4, test.maxProfit(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testStrictlyDescending() {
        assertEquals(0, test.maxProfit(new int[]{10, 8, 6, 4, 2}));
    }

    @Test
    public void testManySmallWinsVsOneBigWin() {
        // [1,2,1,2,1,2,1,10]: optimal is 10 (verified by DP)
        assertEquals(10, test.maxProfit(new int[]{1, 2, 1, 2, 1, 2, 1, 10}));
    }

    @Test
    public void testLargeRandom1000Seed42() {
        Random rand = new Random(42L);
        int[] prices = new int[1000];
        for (int i = 0; i < 1000; i++) {
            prices[i] = rand.nextInt(1000);
        }
        int result = test.maxProfit(prices);
        int resultOpt = test.maxProfitOptimized(prices);
        assertTrue(result >= 0);
        assertEquals(result, resultOpt, "Both methods should agree on large random input");
    }

    @Test
    public void testPropertyResultNonNegative() {
        // Property: result is always >= 0 for various inputs
        int[][] inputs = {
            {}, {5}, {3, 1}, {1, 3}, {7, 7, 7}, {10, 1, 10, 1, 10},
            {100, 50, 25, 12, 6, 3, 1}
        };
        for (int[] prices : inputs) {
            assertTrue(test.maxProfit(prices) >= 0, "Profit must be non-negative for: " + java.util.Arrays.toString(prices));
        }
    }

    @Test
    public void testOptimizedMatchesDP() {
        // Verify both implementations agree on multiple cases
        int[][] inputs = {
            {1, 2, 3, 0, 2}, {6, 1, 3, 2, 4, 7}, {1, 2, 3, 4, 5},
            {5, 4, 3, 2, 1}, {3, 3, 3, 3}, {1, 2}, {2, 1}
        };
        for (int[] prices : inputs) {
            assertEquals(test.maxProfit(prices), test.maxProfitOptimized(prices),
                "Methods disagree on: " + java.util.Arrays.toString(prices));
        }
    }

    @Test
    public void testTwoAscendingProfit() {
        // Two prices ascending: profit = diff
        assertEquals(5, test.maxProfit(new int[]{3, 8}));
    }

    @Test
    public void testAlternatingLowHigh() {
        // [1,5,1,5,1,5]: buy@1 sell@5 cool buy@1 sell@5 cool buy@1 sell@5 = 12? No, cooldown blocks.
        // day0 buy@1, day1 sell@5(+4), day2 cool, day3 buy@1, day4 sell@5(+4), day5 cool => can't buy again = 8
        // Actually: buy@1(d0) sell@5(d1) cool(d2) buy@1(d3) sell@5(d4) = 8
        assertEquals(8, test.maxProfit(new int[]{1, 5, 1, 5, 1, 5}));
    }

    @Test
    public void testSingleLargeSpike() {
        // [5,5,5,5,100,5,5]: buy before spike, sell at spike
        assertEquals(95, test.maxProfit(new int[]{5, 5, 5, 5, 100, 5, 5}));
    }
}

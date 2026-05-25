package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.Test;

public class MaxProfit_121Test {

    private final MaxProfit_121 test = new MaxProfit_121();

    @Test
    public void testHappyCases() {
        assertEquals(5, test.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        assertEquals(0, test.maxProfit(new int[]{7, 6, 4, 3, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxProfit(null));
        assertEquals(0, test.maxProfit(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(9, test.maxProfit(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, test.maxProfit(new int[]{}));
    }

    @Test
    public void testTwoElementsProfit() {
        assertEquals(1, test.maxProfit(new int[]{1, 2}));
    }

    @Test
    public void testTwoElementsNoProfit() {
        assertEquals(0, test.maxProfit(new int[]{2, 1}));
    }

    @Test
    public void testAllSamePrice() {
        assertEquals(0, test.maxProfit(new int[]{5, 5, 5, 5, 5}));
    }

    @Test
    public void testProfitAtEnd() {
        assertEquals(99, test.maxProfit(new int[]{1, 1, 1, 1, 100}));
    }

    @Test
    public void testStandardDP() {
        assertEquals(5, test.maxProfitStandardDP(new int[]{7, 1, 5, 3, 6, 4}));
        assertEquals(0, test.maxProfitStandardDP(new int[]{7, 6, 4, 3, 1}));
    }

    @Test
    public void testGiantCase() {
        int[] prices = new int[10000];
        for (int i = 0; i < 10000; i++) prices[i] = i;
        assertEquals(9999, test.maxProfit(prices));
    }

    @Test
    public void testStrictlyDescending() {
        assertEquals(0, test.maxProfit(new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1}));
    }

    @Test
    public void testStrictlyAscending() {
        assertEquals(99, test.maxProfit(new int[]{1, 20, 40, 60, 80, 100}));
    }

    @Test
    public void testMinThenMax() {
        assertEquals(99, test.maxProfit(new int[]{50, 30, 1, 100, 40}));
    }

    @Test
    public void testMaxThenMinThenHigherMax() {
        // Max 90 first, dip to 5, then rise to 95 -> buy at 5, sell at 95 = 90
        assertEquals(90, test.maxProfit(new int[]{90, 50, 5, 20, 95, 10}));
    }

    @Test
    public void testLargePricesNearIntMax() {
        // Subtraction overflow risk: Integer.MAX_VALUE - 1 is still valid
        int high = Integer.MAX_VALUE - 1;
        int low = Integer.MAX_VALUE - 100;
        assertEquals(99, test.maxProfit(new int[]{low, high}));
        assertEquals(0, test.maxProfit(new int[]{high, low}));
    }

    @Test
    public void testLargeRandomCrossCheckBruteForce() {
        Random rand = new Random(42L);
        int[] prices = new int[1000];
        for (int i = 0; i < 1000; i++) prices[i] = rand.nextInt(10000);

        // Brute-force O(n^2) reference
        int expected = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                expected = Math.max(expected, prices[j] - prices[i]);
            }
        }

        assertEquals(expected, test.maxProfit(prices));
        assertEquals(expected, test.maxProfitStandardDP(prices));
    }

    @Test
    public void testPropertyResultAlwaysNonNegative() {
        Random rand = new Random(42L);
        for (int t = 0; t < 50; t++) {
            int[] prices = new int[rand.nextInt(100) + 1];
            for (int i = 0; i < prices.length; i++) prices[i] = rand.nextInt(10000);
            assertTrue(test.maxProfit(prices) >= 0);
        }
    }

    @Test
    public void testBothMethodsAgree() {
        int[][] cases = {
            {7, 1, 5, 3, 6, 4},
            {7, 6, 4, 3, 1},
            {2, 4, 1},
            {3, 3, 5, 0, 0, 3, 1, 4}
        };
        for (int[] prices : cases) {
            assertEquals(test.maxProfit(prices), test.maxProfitStandardDP(prices));
        }
    }
}

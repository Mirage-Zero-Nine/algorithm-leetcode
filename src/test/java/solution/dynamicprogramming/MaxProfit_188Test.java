package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
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

    @Test
    public void testKZero() {
        assertEquals(0, test.maxProfit(0, new int[]{1, 2, 3, 4, 5}));
        assertEquals(0, test.dpOptimized(0, new int[]{1, 2, 3, 4, 5}));
        assertEquals(0, test.stateMachine(0, new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testAllSamePrice() {
        assertEquals(0, test.maxProfit(3, new int[]{5, 5, 5, 5, 5}));
        assertEquals(0, test.dpOptimized(3, new int[]{5, 5, 5, 5, 5}));
        assertEquals(0, test.stateMachine(3, new int[]{5, 5, 5, 5, 5}));
    }

    @Test
    public void testStrictlyAscending() {
        // k >= n/2: sum of all increments = 5
        assertEquals(5, test.maxProfit(100, new int[]{1, 2, 3, 4, 5, 6}));
        assertEquals(5, test.dpOptimized(100, new int[]{1, 2, 3, 4, 5, 6}));
        assertEquals(5, test.stateMachine(100, new int[]{1, 2, 3, 4, 5, 6}));
    }

    @Test
    public void testStrictlyDescending() {
        assertEquals(0, test.maxProfit(3, new int[]{10, 9, 8, 7, 6, 5}));
        assertEquals(0, test.dpOptimized(3, new int[]{10, 9, 8, 7, 6, 5}));
        assertEquals(0, test.stateMachine(3, new int[]{10, 9, 8, 7, 6, 5}));
    }

    @Test
    public void testKOneEquivalent() {
        // Same as problem 121: single best transaction (buy at 1, sell at 6 = 5)
        assertEquals(5, test.dpOptimized(1, new int[]{7, 1, 5, 3, 6, 4}));
        assertEquals(5, test.stateMachine(1, new int[]{7, 1, 5, 3, 6, 4}));
    }

    @Test
    public void testKTwoLeetCodeExamples() {
        // LeetCode examples verified with dpOptimized and stateMachine
        assertEquals(7, test.dpOptimized(2, new int[]{3, 2, 6, 5, 0, 3}));
        assertEquals(2, test.dpOptimized(2, new int[]{2, 4, 1}));
        assertEquals(7, test.stateMachine(2, new int[]{3, 2, 6, 5, 0, 3}));
        assertEquals(2, test.stateMachine(2, new int[]{2, 4, 1}));
    }

    @Test
    public void testLargeKAndPricesSmokeTest() {
        Random rand = new Random(42L);
        int[] prices = new int[500];
        for (int i = 0; i < 500; i++) {
            prices[i] = rand.nextInt(1000);
        }
        // k >= n/2 triggers unlimited-transactions path in all methods
        int r1 = test.maxProfit(250, prices);
        int r2 = test.dpOptimized(250, prices);
        int r3 = test.stateMachine(250, prices);
        assertEquals(r1, r2);
        assertEquals(r1, r3);
        assertTrue(r1 >= 0);
    }

    @Test
    public void testResultNonNegative() {
        int[][] cases = {{2, 1}, {5, 4, 3, 2, 1}, {1}, {3, 3, 3}, {1, 10, 1, 10}};
        for (int[] prices : cases) {
            assertTrue(test.maxProfit(2, prices) >= 0);
            assertTrue(test.dpOptimized(2, prices) >= 0);
            assertTrue(test.stateMachine(2, prices) >= 0);
        }
    }

    @Test
    public void testResultBoundedByMaxMinDiff() {
        int[] prices = {3, 8, 1, 9, 2, 7};
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int p : prices) {
            max = Math.max(max, p);
            min = Math.min(min, p);
        }
        int result = test.dpOptimized(1, prices);
        assertTrue(result <= max - min);
        assertTrue(result >= 0);
    }
}

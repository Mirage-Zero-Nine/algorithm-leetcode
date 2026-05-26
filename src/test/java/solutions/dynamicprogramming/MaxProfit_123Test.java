package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxProfit_123Test {

    private final MaxProfit_123 test = new MaxProfit_123();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
        assertEquals(4, test.maxProfit(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxProfit(new int[]{7, 6, 4, 3, 1}));
        assertEquals(0, test.maxProfit(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(13, test.maxProfit(new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0}));
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, test.maxProfit(new int[]{}));
    }

    @Test
    public void testTwoElements() {
        assertEquals(1, test.maxProfit(new int[]{1, 2}));
        assertEquals(0, test.maxProfit(new int[]{2, 1}));
    }

    @Test
    public void testAllSamePrice() {
        assertEquals(0, test.maxProfit(new int[]{5, 5, 5, 5}));
    }

    @Test
    public void testTwoTransactions() {
        assertEquals(10, test.maxProfit(new int[]{1, 5, 2, 8})); // buy1sell5 + buy2sell8 = 4+6=10
    }

    @Test
    public void testOneTransactionBetter() {
        assertEquals(9, test.maxProfit(new int[]{1, 9, 2, 3})); // buy1sell9 + buy2sell3 = 8+1=9
    }

    @Test
    public void testStateMachine() {
        assertEquals(6, test.stateMachine(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
        assertEquals(4, test.stateMachine(new int[]{1, 2, 3, 4, 5}));
        assertEquals(0, test.stateMachine(new int[]{7, 6, 4, 3, 1}));
    }

    @Test
    public void testGiantCase() {
        int[] prices = new int[10000];
        for (int i = 0; i < 10000; i++) {
            prices[i] = i < 5000 ? i : 10000 - i;
        }
        // Peak at 4999, valley at 0 and 9999
        // Best single transaction: buy at 0, sell at 4999 = 4999
        // Two transactions: buy0 sell4999 + buy5000 sell... decreasing so just one transaction
        int result = test.maxProfit(prices);
        assertEquals(result, test.stateMachine(prices)); // both methods should agree
    }
}

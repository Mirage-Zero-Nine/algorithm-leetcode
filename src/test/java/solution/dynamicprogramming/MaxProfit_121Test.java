package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}

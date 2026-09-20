package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MaxProfit_122Test {

    private final MaxProfit_122 test = new MaxProfit_122();

    @Test
    public void testHappyCases() {
        assertEquals(7, test.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        assertEquals(4, test.maxProfit(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxProfit(new int[]{7, 6, 4, 3, 1}));
        assertEquals(0, test.maxProfit(null));
    }

    @Test
    public void testLargeCase() {
        assertEquals(9, test.maxProfit(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }

    @Test
    public void testSingleElement() {
        assertEquals(0, test.maxProfit(new int[]{5}));
    }

    @Test
    public void testTwoElements() {
        assertEquals(1, test.maxProfit(new int[]{1, 2}));
        assertEquals(0, test.maxProfit(new int[]{2, 1}));
    }

    @Test
    public void testAllSamePrice() {
        assertEquals(0, test.maxProfit(new int[]{3, 3, 3, 3, 3}));
    }

    @Test
    public void testZigZag() {
        assertEquals(4, test.maxProfit(new int[]{1, 3, 1, 3})); // buy1sell3 + buy1sell3 = 4
        assertEquals(6, test.maxProfit(new int[]{1, 4, 2, 5})); // 3 + 3 = 6
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, test.maxProfit(new int[]{}));
    }

    @Test
    public void testSingleDip() {
        assertEquals(6, test.maxProfit(new int[]{3, 1, 7})); // buy at 1, sell at 7
    }

    @Test
    public void testGiantCase() {
        int[] prices = new int[10000];
        for (int i = 0; i < 10000; i++) {
            prices[i] = i % 2 == 0 ? 1 : 100;
        }
        // 5000 transactions of profit 99 each
        assertEquals(495000, test.maxProfit(prices));
    }

    @Test
    public void testGreedyImplementationMatchesDynamicProgramming() {
        int[][] cases = {{7, 1, 5, 3, 6, 4}, {1, 2, 3, 4, 5},
                {7, 6, 4, 3, 1}, {1, 4, 2, 5}, {3, 3, 3}};
        for (int[] prices : cases) {
            assertEquals(test.maxProfit(prices), test.maxProfitGreedy(prices));
        }
    }

    @ParameterizedTest(name = "prices {0}")
    @CsvSource({"'1,3,2,4',4", "'5,1,5,1,5',8", "'2,4,1,7',8", "'10,8,6,4,2',0", "'1,10,1,10,1',18", "'3,5,4,8,2,9',13", "'6,7,8,2,3,10',10", "'4,1,2,1,6',6", "'9,11,3,5,7',6", "'2,2,3,1,4',4"})
    public void testAdditionalTransactionPatterns(String encoded, int expected) {
        String[] values = encoded.split(","); int[] prices = new int[values.length];
        for (int i = 0; i < values.length; i++) prices[i] = Integer.parseInt(values[i]);
        assertEquals(expected, test.maxProfit(prices));
        assertEquals(expected, test.maxProfitGreedy(prices));
    }
}

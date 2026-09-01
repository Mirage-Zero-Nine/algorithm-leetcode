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
    public void testExhaustiveSmallPriceArrays() {
        for (int length = 1; length <= 6; length++) {
            int arrayCount = (int) Math.pow(5, length);
            for (int encodedPrices = 0; encodedPrices < arrayCount; encodedPrices++) {
                int[] prices = decodePrices(encodedPrices, length);
                int expected = bruteForceMaxProfit(prices);

                assertEquals(expected, test.maxProfit(prices),
                        () -> "prices=" + java.util.Arrays.toString(prices));
                assertEquals(expected, test.stateMachine(prices),
                        () -> "prices=" + java.util.Arrays.toString(prices));
            }
        }
    }

    private int[] decodePrices(int encodedPrices, int length) {
        int[] prices = new int[length];
        for (int day = 0; day < length; day++) {
            prices[day] = encodedPrices % 5;
            encodedPrices /= 5;
        }
        return prices;
    }

    /** Enumerates every valid choice of up to two non-overlapping transactions. */
    private int bruteForceMaxProfit(int[] prices) {
        int best = 0;
        for (int firstBuy = 0; firstBuy < prices.length; firstBuy++) {
            for (int firstSell = firstBuy + 1; firstSell < prices.length; firstSell++) {
                best = Math.max(best, prices[firstSell] - prices[firstBuy]);
                for (int secondBuy = firstSell + 1; secondBuy < prices.length; secondBuy++) {
                    for (int secondSell = secondBuy + 1; secondSell < prices.length; secondSell++) {
                        int profit = prices[firstSell] - prices[firstBuy]
                                + prices[secondSell] - prices[secondBuy];
                        best = Math.max(best, profit);
                    }
                }
            }
        }
        return best;
    }
}

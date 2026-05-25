package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CoinChange_322Test {

    private final CoinChange_322 test = new CoinChange_322();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.coinChange(new int[]{1, 5, 10}, 11));
        assertEquals(3, test.coinChange(new int[]{1, 2, 5}, 11));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.coinChange(new int[]{2}, 3));
        assertEquals(0, test.coinChange(new int[]{1}, 0));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.coinChange(new int[]{1, 5, 10, 25}, 100));
    }

    @Test
    public void testImpossible() {
        assertEquals(-1, test.coinChange(new int[]{3, 7}, 5));
    }

    @Test
    public void testEmptyCoins() {
        assertEquals(-1, test.coinChange(new int[]{}, 10));
    }

    @Test
    public void testSingleCoinExact() {
        assertEquals(1, test.coinChange(new int[]{5}, 5));
    }

    @Test
    public void testSingleCoinMultiple() {
        assertEquals(3, test.coinChange(new int[]{3}, 9));
    }

    @Test
    public void testAmountOne() {
        assertEquals(1, test.coinChange(new int[]{1, 2, 5}, 1));
    }

    @Test
    public void testLargeAmount() {
        assertEquals(20, test.coinChange(new int[]{1, 5, 10, 25}, 500));
    }

    @Test
    public void testGiantCase() {
        assertEquals(4, test.coinChange(new int[]{1, 5, 10, 25, 50, 100}, 400));
    }

    @Test
    public void testAmountZeroReturnsZero() {
        assertEquals(0, test.coinChange(new int[]{1, 2, 3}, 0));
    }

    @Test
    public void testEmptyCoinsAmountZero() {
        // Implementation checks empty coins first, returns -1
        assertEquals(-1, test.coinChange(new int[]{}, 0));
    }

    @Test
    public void testExactlyOneCoinDenomination() {
        assertEquals(1, test.coinChange(new int[]{1, 5, 10, 25}, 25));
        assertEquals(1, test.coinChange(new int[]{1, 5, 10, 25}, 10));
    }

    @Test
    public void testSingleDenominationNotDivisible() {
        assertEquals(-1, test.coinChange(new int[]{3}, 10));
    }

    @Test
    public void testGreedyFailsCase() {
        // Greedy would pick 10+1+1+1+1=5 coins, optimal is 7+7=2
        assertEquals(2, test.coinChange(new int[]{1, 5, 7, 10}, 14));
    }

    @Test
    public void testAllSameDenomination() {
        // coins all same value - only multiples solvable
        assertEquals(5, test.coinChange(new int[]{4, 4, 4}, 20));
        assertEquals(-1, test.coinChange(new int[]{4, 4, 4}, 7));
    }

    @Test
    public void testLargeAmountSmallDenominations() {
        // 10000 with coins [1,5,10] - should not TLE
        assertEquals(1000, test.coinChange(new int[]{1, 5, 10}, 10000));
    }

    @Test
    public void testCoinsContainOneAlwaysSolvable() {
        assertEquals(7, test.coinChange(new int[]{1, 7, 13}, 19));
    }

    @Test
    public void testCannotBeMadeLargeDenominationsSmallAmount() {
        assertEquals(-1, test.coinChange(new int[]{5, 10, 25}, 3));
        assertEquals(-1, test.coinChange(new int[]{6, 9}, 11));
    }

    @Test
    public void testAmountRequiresOnlySmallestCoin() {
        // amount=3 with coins [3,7,11] -> needs exactly 1 of the smallest
        assertEquals(1, test.coinChange(new int[]{3, 7, 11}, 3));
        // amount=4 with coins [1,5,10] -> needs 4 of the smallest
        assertEquals(4, test.coinChange(new int[]{1, 5, 10}, 4));
    }
}

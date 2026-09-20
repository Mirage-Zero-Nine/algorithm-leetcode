package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

public class Change_518Test {

    private final Change_518 test = new Change_518();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.change(5, new int[]{1, 2, 5}));
        assertEquals(0, test.change(3, new int[]{2}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.change(0, new int[]{1, 2, 3}));
        assertEquals(1, test.change(1, new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(10, test.change(10, new int[]{1, 2, 5}));
    }

    @Test
    public void testSingleCoin() {
        assertEquals(1, test.change(5, new int[]{5}));
        assertEquals(1, test.change(10, new int[]{5}));
        assertEquals(0, test.change(3, new int[]{5}));
    }

    @Test
    public void testAmountZero() {
        assertEquals(1, test.change(0, new int[]{1}));
        assertEquals(1, test.change(0, new int[]{5, 10, 25}));
    }

    @Test
    public void testTwoCoins() {
        assertEquals(3, test.change(4, new int[]{1, 2})); // 1111, 112, 22
    }

    @Test
    public void testLargeAmount() {
        assertEquals(242, test.change(100, new int[]{1, 5, 10, 25}));
    }

    @Test
    public void testCoinLargerThanAmount() {
        assertEquals(0, test.change(1, new int[]{2}));
        assertEquals(1, test.change(2, new int[]{2, 5}));
    }

    @Test
    public void testExhaustiveSmallValidInputs() {
        int[] denominations = {1, 2, 3, 4, 5, 6};

        // Every nonempty subset is a valid distinct positive coin set.
        for (int mask = 1; mask < (1 << denominations.length); mask++) {
            int[] coins = coinsForMask(denominations, mask);
            for (int amount = 0; amount <= 12; amount++) {
                int expected = countCombinations(amount, coins, 0);
                assertEquals(expected, test.change(amount, coins),
                        "coins=" + Arrays.toString(coins) + ", amount=" + amount);
            }
        }
    }

    private int[] coinsForMask(int[] denominations, int mask) {
        int[] coins = new int[Integer.bitCount(mask)];
        int index = 0;
        for (int bit = 0; bit < denominations.length; bit++) {
            if ((mask & (1 << bit)) != 0) {
                coins[index++] = denominations[bit];
            }
        }
        return coins;
    }

    // Count choices by denomination so different orders of the same coins are one combination.
    private int countCombinations(int remaining, int[] coins, int coinIndex) {
        if (remaining == 0) {
            return 1;
        }
        if (coinIndex == coins.length) {
            return 0;
        }

        int combinations = 0;
        for (int amountAfterCoin = remaining; amountAfterCoin >= 0; amountAfterCoin -= coins[coinIndex]) {
            combinations += countCombinations(amountAfterCoin, coins, coinIndex + 1);
        }
        return combinations;
    }

    @org.junit.jupiter.api.TestFactory
    public Stream<DynamicTest> additionalDistinctCases() {
        return Stream.of(
                DynamicTest.dynamicTest("amount 2 with ones", () -> assertEquals(1, test.change(2, new int[]{1}))),
                DynamicTest.dynamicTest("amount 3 with ones and twos", () -> assertEquals(2, test.change(3, new int[]{1, 2}))),
                DynamicTest.dynamicTest("amount 5 with ones and twos", () -> assertEquals(3, test.change(5, new int[]{1, 2}))),
                DynamicTest.dynamicTest("amount 6 with two and three", () -> assertEquals(2, test.change(6, new int[]{2, 3}))),
                DynamicTest.dynamicTest("amount 7 with two and five", () -> assertEquals(1, test.change(7, new int[]{2, 5}))),
                DynamicTest.dynamicTest("amount 8 with ones and fours", () -> assertEquals(3, test.change(8, new int[]{1, 4}))),
                DynamicTest.dynamicTest("amount 9 with three and six", () -> assertEquals(2, test.change(9, new int[]{3, 6}))),
                DynamicTest.dynamicTest("amount 10 with two and five", () -> assertEquals(2, test.change(10, new int[]{2, 5}))),
                DynamicTest.dynamicTest("amount 12 with three denominations", () -> assertEquals(11, test.change(12, new int[]{1, 3, 4}))),
                DynamicTest.dynamicTest("unreachable odd amount", () -> assertEquals(0, test.change(9, new int[]{2, 4}))),
                DynamicTest.dynamicTest("single denomination exact", () -> assertEquals(1, test.change(24, new int[]{6}))));
    }
}

package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.util.stream.Stream;
import org.junit.jupiter.api.DynamicTest;

public class GetMoneyAmount_375Test {

    private final GetMoneyAmount_375 test = new GetMoneyAmount_375();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.bottomUp(4));
        assertEquals(16, test.bottomUp(10));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.bottomUp(1));
        assertEquals(1, test.bottomUp(2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(49, test.bottomUp(20));
    }

    @Test
    public void testN3() {
        assertEquals(2, test.bottomUp(3));
    }

    @Test
    public void testN5() {
        assertEquals(6, test.bottomUp(5));
    }

    @Test
    public void testTopDownN10() {
        assertEquals(16, test.getMoneyAmount(10));
    }

    @Test
    public void testTopDownN1() {
        assertEquals(0, test.getMoneyAmount(1));
    }

    @Test
    public void testTopDownN2() {
        assertEquals(1, test.getMoneyAmount(2));
    }

    @Test
    public void testGiantCase() {
        int result = test.bottomUp(50);
        assertTrue(result > 0);
    }

    @Test
    public void testExhaustiveSmallRangeAgainstIndependentOracle() {
        // Exhaust every valid n in a small deterministic range. The oracle
        // evaluates each possible first guess and minimizes the worst case,
        // independently of either production implementation.
        for (int n = 1; n <= 30; n++) {
            int expected = oracle(1, n, new int[n + 1][n + 1]);

            assertEquals(expected, test.getMoneyAmount(n), "top-down n=" + n);
            assertEquals(expected, test.bottomUp(n), "bottom-up n=" + n);
        }
    }

    private int oracle(int low, int high, int[][] memo) {
        if (low >= high) {
            return 0;
        }
        if (memo[low][high] != 0) {
            return memo[low][high];
        }

        int minimumWorstCaseCost = Integer.MAX_VALUE;
        for (int guess = low; guess <= high; guess++) {
            int costIfLower = oracle(low, guess - 1, memo);
            int costIfHigher = oracle(guess + 1, high, memo);
            int worstCaseCost = guess + Math.max(costIfLower, costIfHigher);
            minimumWorstCaseCost = Math.min(minimumWorstCaseCost, worstCaseCost);
        }

        memo[low][high] = minimumWorstCaseCost;
        return minimumWorstCaseCost;
    }

    @org.junit.jupiter.api.TestFactory
    public Stream<DynamicTest> additionalDistinctRanges() {
        return Stream.of(
                DynamicTest.dynamicTest("n6", () -> assertEquals(8, test.bottomUp(6))),
                DynamicTest.dynamicTest("n7", () -> assertEquals(10, test.bottomUp(7))),
                DynamicTest.dynamicTest("n8", () -> assertEquals(12, test.bottomUp(8))),
                DynamicTest.dynamicTest("n9", () -> assertEquals(14, test.bottomUp(9))),
                DynamicTest.dynamicTest("n11 top down", () -> assertEquals(18, test.getMoneyAmount(11))),
                DynamicTest.dynamicTest("n11 bottom up", () -> assertEquals(18, test.bottomUp(11))),
                DynamicTest.dynamicTest("n12 top down", () -> assertEquals(21, test.getMoneyAmount(12))),
                DynamicTest.dynamicTest("n12 bottom up", () -> assertEquals(21, test.bottomUp(12))),
                DynamicTest.dynamicTest("n15", () -> assertEquals(30, test.bottomUp(15))),
                DynamicTest.dynamicTest("n25", () -> assertEquals(64, test.bottomUp(25))),
                DynamicTest.dynamicTest("n30 top down", () -> assertEquals(79, test.getMoneyAmount(30))));
    }
}

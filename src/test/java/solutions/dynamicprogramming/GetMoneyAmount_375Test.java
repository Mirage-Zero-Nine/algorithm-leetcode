package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
}

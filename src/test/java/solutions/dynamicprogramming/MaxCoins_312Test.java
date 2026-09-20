package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.util.stream.Stream;
import org.junit.jupiter.api.DynamicTest;

public class MaxCoins_312Test {

    private final MaxCoins_312 test = new MaxCoins_312();

    @Test
    public void testHappyCases() {
        assertEquals(167, test.maxCoins(new int[]{3, 1, 5, 8}));
        assertEquals(10, test.maxCoins(new int[]{1, 5}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.maxCoins(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3630, test.maxCoins(new int[]{8, 2, 6, 8, 9, 8, 1, 4, 1, 5, 3, 0, 7, 7, 0, 4, 2, 2, 5}));
    }

    @Test
    public void testSingleLargeValue() {
        assertEquals(100, test.maxCoins(new int[]{100}));
    }

    @Test
    public void testTwoEqual() {
        assertEquals(12, test.maxCoins(new int[]{3, 3}));
    }

    @Test
    public void testThreeBalloons() {
        assertEquals(88, test.maxCoins(new int[]{2, 4, 8}));
    }

    @Test
    public void testAllOnes() {
        assertEquals(4, test.maxCoins(new int[]{1, 1, 1, 1}));
    }

    @Test
    public void testZeroValues() {
        assertEquals(0, test.maxCoins(new int[]{0, 0, 0}));
    }

    @Test
    public void testIncreasing() {
        assertEquals(110, test.maxCoins(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[50];
        for (int i = 0; i < 50; i++) {
            arr[i] = (i % 5) + 1;
        }
        int expected = bruteForce(arr);
        assertEquals(expected, test.maxCoins(arr));
        assertEquals(expected, test.dp(arr.clone()));
    }

    private int bruteForce(int[] nums) {
        int n = nums.length;
        int[] padded = new int[n + 2];
        padded[0] = padded[n + 1] = 1;
        System.arraycopy(nums, 0, padded, 1, n);
        int[][] dp = new int[n + 2][n + 2];
        for (int length = 2; length <= n + 1; length++) {
            for (int left = 0; left + length <= n + 1; left++) {
                int right = left + length;
                for (int last = left + 1; last < right; last++) {
                    dp[left][right] = Math.max(dp[left][right],
                            dp[left][last] + padded[left] * padded[last] * padded[right] + dp[last][right]);
                }
            }
        }
        return dp[0][n + 1];
    }

    @org.junit.jupiter.api.TestFactory
    public Stream<DynamicTest> additionalDistinctArrays() {
        return Stream.of(
                DynamicTest.dynamicTest("two unequal", () -> assertEquals(8, test.maxCoins(new int[]{4, 1}))),
                DynamicTest.dynamicTest("two ones", () -> assertEquals(2, test.maxCoins(new int[]{1, 1}))),
                DynamicTest.dynamicTest("two and three", () -> assertEquals(9, test.maxCoins(new int[]{2, 3}))),
                DynamicTest.dynamicTest("three equal", () -> assertEquals(14, test.maxCoins(new int[]{2, 2, 2}))),
                DynamicTest.dynamicTest("single zero", () -> assertEquals(0, test.maxCoins(new int[]{0}))),
                DynamicTest.dynamicTest("two zeros and value", () -> assertEquals(4, test.maxCoins(new int[]{0, 4, 0}))),
                DynamicTest.dynamicTest("decreasing", () -> assertEquals(36, test.maxCoins(new int[]{4, 3, 2}))),
                DynamicTest.dynamicTest("small mixed", () -> assertEquals(6, test.maxCoins(new int[]{1, 2, 1}))),
                DynamicTest.dynamicTest("large pair", () -> assertEquals(220, test.maxCoins(new int[]{10, 20}))),
                DynamicTest.dynamicTest("four twos", () -> assertEquals(22, test.maxCoins(new int[]{2, 2, 2, 2}))),
                DynamicTest.dynamicTest("dp agrees", () -> assertEquals(test.maxCoins(new int[]{2, 5, 3}), test.dp(new int[]{2, 5, 3}))));
    }
}

package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class Rob_198Test {

    private final Rob_198 test = new Rob_198();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.rob(new int[]{1, 2, 3, 1}));
        assertEquals(12, test.rob(new int[]{2, 7, 9, 3, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.rob(new int[]{}));
        assertEquals(1, test.rob(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(9, test.rob(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testTwoElements() {
        assertEquals(2, test.rob(new int[]{1, 2}));
        assertEquals(5, test.rob(new int[]{5, 1}));
    }

    @Test
    public void testAllSameValues() {
        assertEquals(6, test.rob(new int[]{3, 3, 3, 3}));
    }

    @Test
    public void testAlternatingHighLow() {
        assertEquals(30, test.rob(new int[]{10, 1, 10, 1, 10}));
    }

    @Test
    public void testSingleZero() {
        assertEquals(0, test.rob(new int[]{0}));
    }

    @Test
    public void testAllZeros() {
        assertEquals(0, test.rob(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void testDecreasingSequence() {
        assertEquals(9, test.rob(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = i + 1;
        }
        // Sum of even-indexed (0-based) elements from index 1: 2+4+6+...+100 = 2550
        // vs odd-indexed: 1+3+5+...+99 = 2500
        assertEquals(2550, test.rob(nums));
    }

    @Test
    public void testStrictlyIncreasing() {
        // [1,2,3,4,5,6,7,8,9,10] -> pick 2+4+6+8+10 = 30
        assertEquals(30, test.rob(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }

    @Test
    public void testAllSameOddCount() {
        // [5,5,5,5,5] -> pick indices 0,2,4 = 15
        assertEquals(15, test.rob(new int[]{5, 5, 5, 5, 5}));
    }

    @Test
    public void testLargeArraySeed42() {
        Random rand = new Random(42L);
        int[] nums = new int[1000];
        for (int i = 0; i < 1000; i++) {
            nums[i] = rand.nextInt(100) + 1;
        }
        int result = test.rob(nums);
        // Property checks: result must be reasonable
        int maxVal = Arrays.stream(nums).max().getAsInt();
        int sum = Arrays.stream(nums).sum();
        assertTrue(result >= maxVal, "Result should be >= max single value");
        assertTrue(result <= sum, "Result should be <= sum of all values");
        assertTrue(result > 0);
    }

    @Test
    public void testPropertyResultGeMaxSingleValue() {
        int[][] cases = {
            {3, 1, 2, 5, 4},
            {100, 1, 1, 1},
            {1, 1, 1, 100},
            {50, 50, 50}
        };
        for (int[] nums : cases) {
            int result = test.rob(nums);
            int maxVal = Arrays.stream(nums).max().getAsInt();
            assertTrue(result >= maxVal,
                "Result " + result + " should be >= max value " + maxVal);
        }
    }

    @Test
    public void testPropertyResultLeSumOfAll() {
        int[][] cases = {
            {1, 2, 3, 1},
            {2, 7, 9, 3, 1},
            {10, 10, 10, 10, 10},
            {1, 100, 1, 100, 1}
        };
        for (int[] nums : cases) {
            int result = test.rob(nums);
            int sum = Arrays.stream(nums).sum();
            assertTrue(result <= sum,
                "Result " + result + " should be <= sum " + sum);
        }
    }

    @Test
    public void testSingleLargeValue() {
        assertEquals(999, test.rob(new int[]{999}));
    }

    @Test
    public void testTwoHousesMaxIsFirst() {
        assertEquals(100, test.rob(new int[]{100, 1}));
    }

    @Test
    public void testAdjacentHighValues() {
        // [100, 100, 1] -> max(100+1, 100) = 101
        assertEquals(101, test.rob(new int[]{100, 100, 1}));
    }

    @Test
    public void testLeetCodeExamplesExplicit() {
        assertEquals(4, test.rob(new int[]{1, 2, 3, 1}));
        assertEquals(12, test.rob(new int[]{2, 7, 9, 3, 1}));
    }
}

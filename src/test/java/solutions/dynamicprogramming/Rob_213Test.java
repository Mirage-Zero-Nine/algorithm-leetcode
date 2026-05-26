package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class Rob_213Test {

    private final Rob_213 test = new Rob_213();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.rob(new int[]{2, 3, 2}));
        assertEquals(4, test.rob(new int[]{1, 2, 3, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.rob(new int[]{}));
        assertEquals(1, test.rob(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(12, test.rob(new int[]{1, 2, 3, 4, 5, 6}));
    }

    @Test
    public void testTwoElements() {
        assertEquals(3, test.rob(new int[]{1, 3}));
        assertEquals(5, test.rob(new int[]{5, 2}));
    }

    @Test
    public void testAllSameValues() {
        assertEquals(4, test.rob(new int[]{2, 2, 2, 2}));
    }

    @Test
    public void testCircularConstraint() {
        // First and last are adjacent, so can't take both
        assertEquals(11, test.rob(new int[]{10, 1, 1, 10}));
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
    public void testThreeElements() {
        assertEquals(5, test.rob(new int[]{5, 1, 1}));
        assertEquals(5, test.rob(new int[]{1, 5, 1}));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = i + 1;
        }
        int result = test.rob(nums);
        // Result should be positive and reasonable
        assertEquals(2550, result);
    }

    @Test
    public void testLeetCodeExamples() {
        assertEquals(3, test.rob(new int[]{2, 3, 2}));
        assertEquals(4, test.rob(new int[]{1, 2, 3, 1}));
        assertEquals(3, test.rob(new int[]{1, 2, 3}));
    }

    @Test
    public void testThreeHousesMaxSingle() {
        // In a circle of 3, all are adjacent to each other, so only one can be robbed
        assertEquals(10, test.rob(new int[]{10, 2, 3}));
        assertEquals(10, test.rob(new int[]{2, 10, 3}));
        assertEquals(10, test.rob(new int[]{2, 3, 10}));
    }

    @Test
    public void testTwoHousesMax() {
        // Two houses in circle are adjacent, pick the max
        assertEquals(100, test.rob(new int[]{100, 1}));
        assertEquals(100, test.rob(new int[]{1, 100}));
        assertEquals(50, test.rob(new int[]{50, 50}));
    }

    @Test
    public void testAllSameAlternating() {
        // All same value: can pick floor(n/2) houses in a circle
        assertEquals(3 * 7, test.rob(new int[]{7, 7, 7, 7, 7, 7})); // 6 houses -> pick 3
        assertEquals(3 * 7, test.rob(new int[]{7, 7, 7, 7, 7, 7, 7})); // 7 houses -> pick 3
        assertEquals(4 * 5, test.rob(new int[]{5, 5, 5, 5, 5, 5, 5, 5})); // 8 houses -> pick 4
    }

    @Test
    public void testLarge1000Seed42CrossCheck() {
        Random rng = new Random(42L);
        int[] nums = new int[1000];
        for (int i = 0; i < 1000; i++) {
            nums[i] = rng.nextInt(100) + 1;
        }
        Rob_198 linear = new Rob_198();
        int[] window1 = Arrays.copyOfRange(nums, 0, 999); // exclude last
        int[] window2 = Arrays.copyOfRange(nums, 1, 1000); // exclude first
        int expected = Math.max(linear.rob(window1), linear.rob(window2));
        assertEquals(expected, test.rob(nums));
    }

    @Test
    public void testPropertyResultGeSubarrayRob() {
        Rob_198 linear = new Rob_198();
        int[][] cases = {{3, 1, 3, 1, 3}, {10, 5, 10, 5}, {1, 2, 3, 4, 5, 6, 7, 8}};
        for (int[] nums : cases) {
            int circularResult = test.rob(nums);
            int[] sub = Arrays.copyOfRange(nums, 0, nums.length - 1);
            assertTrue(circularResult >= linear.rob(sub),
                    "Circular result should be >= rob of subarray [0..n-2]");
        }
    }

    @Test
    public void testPropertyCircularLeLinear() {
        Rob_198 linear = new Rob_198();
        int[][] cases = {{2, 3, 2}, {1, 2, 3, 1}, {5, 1, 1, 5}, {10, 1, 1, 10, 1}};
        for (int[] nums : cases) {
            assertTrue(test.rob(nums) <= linear.rob(nums),
                    "Circular rob should be <= linear rob for " + Arrays.toString(nums));
        }
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, test.rob(new int[]{}));
    }

    @Test
    public void testSingleHouse() {
        assertEquals(42, test.rob(new int[]{42}));
        assertEquals(0, test.rob(new int[]{0}));
    }
}

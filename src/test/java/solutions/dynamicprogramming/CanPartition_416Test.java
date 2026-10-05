package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Contract and edge-case tests for both the dynamic-programming and DFS
 * implementations. Each shared case supplies a fresh array to each approach.
 * Null, short, and zero-valued inputs cover the implementation-defined
 * extensions documented by {@link CanPartition_416}; positive cases follow
 * the original problem contract.
 */

public class CanPartition_416Test {
    private final CanPartition_416 test = new CanPartition_416();

    @Test
    public void test() {
        assertTrue(test.canPartition(new int[]{1, 5, 11, 5}));
    }

    @Test
    public void test1() {
        assertTrue(test.canPartition(new int[]{6, 4, 4, 3, 1}));
        assertTrue(test.canPartitionDFS(new int[]{6, 4, 4, 3, 1}));
    }

    @Test
    public void test2() {
        assertTrue(test.canPartition(new int[]{1, 3, 4, 4, 6}));
        assertTrue(test.canPartitionDFS(new int[]{1, 3, 4, 4, 6}));
    }

    @Test
    public void test3() {
        assertTrue(test.canPartition(new int[]{0, 0, 0, 0}));
        assertTrue(test.canPartitionDFS(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void test4() {
        assertTrue(test.canPartition(new int[]{10, 5, 4, 1}));
        assertTrue(test.canPartitionDFS(new int[]{10, 5, 4, 1}));
    }

    @Test
    public void test5() {
        assertFalse(test.canPartition(new int[]{1, 2, 3, 5}));
        assertFalse(test.canPartitionDFS(new int[]{1, 2, 3, 5}));
    }

    @Test
    public void testEmpty() {
        assertFalse(test.canPartition(new int[]{}));
        assertFalse(test.canPartitionDFS(new int[]{}));
        assertFalse(test.canPartition(null));
        assertFalse(test.canPartitionDFS(null));
    }

    @Test
    public void testSingleElement() {
        assertFalse(test.canPartition(new int[]{5}));
        assertFalse(test.canPartitionDFS(new int[]{5}));
    }

    @Test
    public void testOddSum() {
        assertFalse(test.canPartition(new int[]{1, 2, 4}));
        assertFalse(test.canPartitionDFS(new int[]{1, 2, 4}));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[200];
        for (int i = 0; i < 200; i++) nums[i] = 1;
        assertBoth(nums, true);
    }

    @Test
    public void testTwoEqualElements() {
        assertTrue(test.canPartition(new int[]{3, 3}));
        assertTrue(test.canPartitionDFS(new int[]{3, 3}));
    }

    @Test
    public void testTwoDifferentElements() {
        assertFalse(test.canPartition(new int[]{1, 2}));
        assertFalse(test.canPartitionDFS(new int[]{1, 2}));
    }

    @Test
    public void testAllSameEvenCount() {
        // 6 elements of value 5, sum=30, each half=15 (three 5s)
        assertTrue(test.canPartition(new int[]{5, 5, 5, 5, 5, 5}));
        assertTrue(test.canPartitionDFS(new int[]{5, 5, 5, 5, 5, 5}));
    }

    @Test
    public void testAllSameOddCount() {
        // 5 elements of value 4, sum=20 (even), but need subset summing to 10
        // 10/4 = 2.5 -> cannot pick exact subset summing to 10
        assertFalse(test.canPartition(new int[]{4, 4, 4, 4, 4}));
        assertFalse(test.canPartitionDFS(new int[]{4, 4, 4, 4, 4}));
    }

    @Test
    public void testMaximumSizeConstructedPartition() {
        int[] nums = new int[200];
        // The first 100 ones plus 25 of the 100 twos make one half (150).
        java.util.Arrays.fill(nums, 0, 100, 1);
        java.util.Arrays.fill(nums, 100, 200, 2);
        assertBoth(nums, true);
    }

    @Test
    public void testPropertyOddSumAlwaysFalse() {
        // any array with odd sum must return false
        assertFalse(test.canPartition(new int[]{1, 2, 4}));       // sum=7
        assertFalse(test.canPartition(new int[]{3, 7, 1}));       // sum=11
        assertFalse(test.canPartition(new int[]{1}));             // sum=1
        assertFalse(test.canPartition(new int[]{100, 1}));        // sum=101
    }

    @Test
    public void testPropertyPairKKAlwaysTrue() {
        // [k, k] is always partitionable for any positive k
        for (int k = 1; k <= 50; k++) {
            assertBoth(new int[]{k, k}, true);
        }
    }

    @Test
    public void testLeetCodeExamples() {
        // LeetCode example 1: [1,5,11,5] -> true (subset [1,5,5] and [11])
        assertTrue(test.canPartition(new int[]{1, 5, 11, 5}));
        assertTrue(test.canPartitionDFS(new int[]{1, 5, 11, 5}));
        // LeetCode example 2: [1,2,3,5] -> false
        assertFalse(test.canPartition(new int[]{1, 2, 3, 5}));
        assertFalse(test.canPartitionDFS(new int[]{1, 2, 3, 5}));
    }

    @Test
    public void testAllZeros() {
        // edge: all zeros, sum=0, even, dp[0]=true
        assertTrue(test.canPartition(new int[]{0, 0}));
        assertTrue(test.canPartitionDFS(new int[]{0, 0}));
    }

    @Test
    public void testRepeatedValuesRequireExactSubset() {
        int[] values = {2, 2, 2, 2, 2, 2};
        assertTrue(test.canPartition(values));
        assertTrue(test.canPartitionDFS(values));
        assertFalse(test.canPartition(new int[]{2, 2, 2, 2, 2}));
        assertFalse(test.canPartitionDFS(new int[]{2, 2, 2, 2, 2}));
    }

    @Test
    public void testLargestAllowedValues() {
        assertBoth(new int[]{100, 100}, true);
        assertBoth(new int[]{100, 100, 100, 100, 1, 1}, true);
    }

    @Test
    public void testSingleLargeValueDominates() {
        assertBoth(new int[]{1, 1, 1, 1, 100}, false);
    }

    @Test
    public void testDifferentWaysToReachTarget() {
        assertBoth(new int[]{1, 2, 2, 3, 4, 4}, true);
        assertBoth(new int[]{2, 3, 5, 7, 11, 12}, true);
    }

    @Test
    public void testDfsMemoMustIncludeIndex() {
        // Total is 62; {1, 5, 5, 6, 14} reaches the independent target 31.
        assertBoth(new int[]{1, 4, 5, 5, 5, 6, 8, 14, 14}, true);
    }

    @Test
    public void testZeroAndPositiveValues() {
        assertBoth(new int[]{0, 0, 2, 2}, true);
        assertBoth(new int[]{0, 1, 2, 4, 5}, true);
    }

    @Test
    public void testDfsDoesNotSortCallerInput() {
        int[] nums = {11, 5, 1, 5};
        int[] before = nums.clone();
        assertTrue(test.canPartitionDFS(nums));
        assertArrayEquals(before, nums);
    }

    @Test
    public void testRepeatedCallsOnSameInstance() {
        assertBoth(new int[]{1, 5, 11, 5}, true);
        assertBoth(new int[]{1, 2, 3, 5}, false);
        assertBoth(new int[]{3, 3, 4, 4}, true);
    }

    @Test
    public void testSelectedSmallPositiveCases() {
        int[][] cases = {
                {1, 1}, {1, 2}, {1, 3}, {1, 2, 3}, {1, 2, 4},
                {1, 2, 3, 4}, {1, 1, 2, 2}, {2, 2, 2}, {2, 3, 3},
                {1, 3, 5, 7}, {2, 4, 6, 8}, {1, 1, 1, 1, 1, 1}
        };
        boolean[] expected = {true, false, false, true, false, true, true, false,
                false, true, true, true};
        for (int i = 0; i < cases.length; i++) {
            assertBoth(cases[i], expected[i]);
        }
    }

    private void assertBoth(int[] nums, boolean expected) {
        assertTrue(test.canPartition(nums.clone()) == expected,
                "DP result for " + java.util.Arrays.toString(nums));
        assertTrue(test.canPartitionDFS(nums.clone()) == expected,
                "DFS result for " + java.util.Arrays.toString(nums));
    }
}

package solutions.prefixsum;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SubarraySum_560Test {

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.ValueSource(ints = {0, 1, 2, 7, 19, 42, 97, 211, 2026, 65537})
    void signedArraysAndNegativeTargetsMatchDirectSums(int seed) {
        java.util.Random random = new java.util.Random(seed);
        for (int trial = 0; trial < 100; trial++) {
            int[] nums = random.ints(1 + random.nextInt(30), -3, 4).toArray();
            int target = random.nextInt(13) - 6;
            assertEquals(bruteForce(nums, target), solver.subarraySum(nums, target),
                    java.util.Arrays.toString(nums) + ", target=" + target);
        }
    }

    @Test
    void maximumLengthZerosCountEveryNonemptySubarray() {
        int size = 20_000;
        assertEquals(size * (size + 1) / 2, solver.subarraySum(new int[size], 0));
        assertEquals(0, solver.subarraySum(new int[size], 1));
    }


    private final SubarraySum_560 solver = new SubarraySum_560();

    @Test
    public void testLeetCodeExample1() {
        assertEquals(2, solver.subarraySum(new int[]{1, 1, 1}, 2));
    }

    @Test
    public void testLeetCodeExample2() {
        assertEquals(2, solver.subarraySum(new int[]{1, 2, 3}, 3));
    }

    @Test
    public void testSingleElementMatches() {
        assertEquals(1, solver.subarraySum(new int[]{5}, 5));
        assertEquals(1, solver.subarraySum(new int[]{-5}, -5));
    }

    @Test
    public void testSingleElementDoesNotMatch() {
        assertEquals(0, solver.subarraySum(new int[]{5}, 6));
    }

    @Test
    public void testAllZeros() {
        // Every non-empty subarray has sum zero: 4 + 3 + 2 + 1 = 10.
        assertEquals(10, solver.subarraySum(new int[]{0, 0, 0, 0}, 0));
    }

    @Test
    public void testZeroTargetWithNoZeroSumSubarray() {
        assertEquals(0, solver.subarraySum(new int[]{1, 2, 3}, 0));
    }

    @Test
    public void testNegativeNumbersAndNegativeTarget() {
        // [-1,-1] at 0-1 and [-1,-1] at 1-2.
        assertEquals(2, solver.subarraySum(new int[]{-1, -1, -1}, -2));
    }

    @Test
    public void testNegativeNumbersAndPositiveTarget() {
        // [3] at 0-0 and [-2,5] at 1-2.
        assertEquals(2, solver.subarraySum(new int[]{3, -2, 5, -1}, 3));
    }

    @Test
    public void testPositiveAndNegativeValues() {
        // [1,-1] at 0-1, [-1,1] at 1-2, [1,-1] at 2-3, and the whole array.
        assertEquals(4, solver.subarraySum(new int[]{1, -1, 1, -1}, 0));
    }

    @Test
    public void testZeroTargetWithRepeatedPrefixSums() {
        // Prefix sums are 1, 0, 1; each repeated sum identifies one match.
        assertEquals(2, solver.subarraySum(new int[]{1, -1, 1}, 0));
    }

    @Test
    public void testNegativeTargetIncludingZero() {
        // [-1] and [-1,0] both sum to -1.
        assertEquals(2, solver.subarraySum(new int[]{-1, 0, 1}, -1));
    }

    @Test
    public void testNegativeNumbersWithNoMatchingNegativeTarget() {
        assertEquals(0, solver.subarraySum(new int[]{-5, -2, -3}, -4));
    }

    @Test
    public void testNegativeNumbersWithNoMatchingPositiveTarget() {
        assertEquals(0, solver.subarraySum(new int[]{-1, -2, -3}, 1));
    }

    @Test
    public void testPositiveNumbersWithNoMatchingNegativeTarget() {
        assertEquals(0, solver.subarraySum(new int[]{1, 2, 3}, -1));
    }

    @Test
    public void testTargetSubarrayStartsAtFirstElement() {
        assertEquals(1, solver.subarraySum(new int[]{2, 3, 4}, 5));
    }

    @Test
    public void testTargetSubarrayStartsAfterFirstElement() {
        // [2,1] at 1-2 and [1,2] at 2-3.
        assertEquals(2, solver.subarraySum(new int[]{9, 2, 1, 2}, 3));
    }

    @Test
    public void testTargetSubarrayEndsAtLastElement() {
        assertEquals(1, solver.subarraySum(new int[]{5, 1, 2}, 3));
    }

    @Test
    public void testWholeArrayIsTheOnlyMatch() {
        assertEquals(1, solver.subarraySum(new int[]{1, 2, 3, 4}, 10));
    }

    @Test
    public void testMultipleDisjointMatches() {
        assertEquals(3, solver.subarraySum(new int[]{3, 1, 3, 1, 3}, 3));
    }

    @Test
    public void testOverlappingMatches() {
        // [1,1] at 0-1, 1-2, and 2-3.
        assertEquals(3, solver.subarraySum(new int[]{1, 1, 1, 1}, 2));
    }

    @Test
    public void testRepeatedValuesRequireFrequencyNotPresence() {
        // Prefix sums are 2, 4, 6, 8; k=4 matches three adjacent pairs.
        assertEquals(3, solver.subarraySum(new int[]{2, 2, 2, 2}, 4));
    }

    @Test
    public void testLargeZeroArrayUsesQuadraticNumberOfMatches() {
        int length = 100;
        int[] nums = new int[length];
        assertEquals(length * (length + 1) / 2, solver.subarraySum(nums, 0));
    }

    @Test
    public void testDeterministicRandomInputsAgainstBruteForce() {
        Random random = new Random(560L);

        for (int testCase = 0; testCase < 250; testCase++) {
            // LeetCode requires at least one element.
            int[] nums = new int[1 + random.nextInt(20)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(11) - 5;
            }
            int k = random.nextInt(21) - 10;

            assertEquals(bruteForce(nums, k), solver.subarraySum(nums, k),
                    "Unexpected count for k=" + k);
        }
    }

    private int bruteForce(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}

package solutions.prefixsum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinSumOfLengths_1477Test {

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.ValueSource(ints = {0, 1, 2, 7, 19, 42, 97, 211, 2026, 65537})
    void directDisjointIntervalPairsFindMinimumTotalLength(int seed) {
        java.util.Random random = new java.util.Random(seed);
        for (int trial = 0; trial < 100; trial++) {
            int[] nums = random.ints(2 + random.nextInt(12), 1, 7).toArray();
            int target = 1 + random.nextInt(18);
            java.util.List<int[]> intervals = new java.util.ArrayList<>();
            for (int left = 0; left < nums.length; left++) {
                int sum = 0;
                for (int right = left; right < nums.length; right++) {
                    sum += nums[right];
                    if (sum == target) intervals.add(new int[]{left, right});
                }
            }
            int expected = Integer.MAX_VALUE;
            for (int[] first : intervals) {
                for (int[] second : intervals) {
                    if (first[1] < second[0]) expected = Math.min(expected,
                            first[1] - first[0] + second[1] - second[0] + 2);
                }
            }
            assertEquals(expected == Integer.MAX_VALUE ? -1 : expected,
                    solution.minSumOfLengths(nums, target));
        }
    }

    @Test
    void largeExactHalfLengthLeavesNoRoomForOverlap() {
        int[] nums = new int[100_000];
        java.util.Arrays.fill(nums, 1);
        assertEquals(100_000, solution.minSumOfLengths(nums, 50_000));
        assertEquals(-1, solution.minSumOfLengths(nums, 50_001));
    }

    private final MinSumOfLengths_1477 solution = new MinSumOfLengths_1477();

    @Test
    void testBasic() {
        assertEquals(2, solution.minSumOfLengths(new int[]{3, 2, 2, 4, 3}, 3));
    }

    @Test
    void testNoSolution() {
        assertEquals(-1, solution.minSumOfLengths(new int[]{1, 6, 1}, 7));
    }

    @Test
    void testMultipleSolutions() {
        assertEquals(-1, solution.minSumOfLengths(new int[]{4, 3, 2, 6, 2, 3, 4}, 6));
    }

    @Test
    void testLongArray() {
        assertEquals(6, solution.minSumOfLengths(new int[]{1, 1, 1, 2, 2, 2, 4, 4}, 6));
    }

    @Test
    void testSingleElement() {
        assertEquals(-1, solution.minSumOfLengths(new int[]{5}, 5));
    }

    @Test
    void testTwoExactElements() {
        assertEquals(2, solution.minSumOfLengths(new int[]{5, 5}, 5));
    }

    @Test
    void testAllOnes() {
        assertEquals(4, solution.minSumOfLengths(new int[]{1, 1, 1, 1, 1, 1}, 2));
    }

    @Test
    void testTargetNotReachable() {
        assertEquals(-1, solution.minSumOfLengths(new int[]{1, 2, 3}, 100));
    }

    @Test
    void testAdjacentSubarrays() {
        assertEquals(4, solution.minSumOfLengths(new int[]{1, 2, 1, 2}, 3));
    }

    @Test
    void testLargerTarget() {
        assertEquals(6, solution.minSumOfLengths(new int[]{1, 1, 1, 2, 2, 2, 4, 4}, 6));
    }

    @Test
    void testGiantCase() {
        int[] arr = new int[10000];
        for (int i = 0; i < 10000; i++) arr[i] = 1;
        // target=1, two subarrays of length 1 each => sum = 2
        assertEquals(2, solution.minSumOfLengths(arr, 1));
    }
}

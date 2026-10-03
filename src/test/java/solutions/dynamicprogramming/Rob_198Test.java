package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class Rob_198Test {

    private final Rob_198 solver = new Rob_198();

    @Test
    void handlesEmptyArrayExtension() {
        assertEquals(0, solver.rob(new int[]{}));
    }

    @Test
    void handlesOneHouse() {
        assertEquals(0, solver.rob(new int[]{0}));
        assertEquals(400, solver.rob(new int[]{400}));
    }

    @Test
    void choosesTheLargerOfTwoHouses() {
        assertEquals(2, solver.rob(new int[]{1, 2}));
        assertEquals(5, solver.rob(new int[]{5, 1}));
        assertEquals(100, solver.rob(new int[]{100, 1}));
    }

    @Test
    void matchesLeetCodeExamples() {
        assertEquals(4, solver.rob(new int[]{1, 2, 3, 1}));
        assertEquals(12, solver.rob(new int[]{2, 7, 9, 3, 1}));
    }

    @Test
    void skipsAnExpensiveAdjacentHouseWhenNeeded() {
        assertEquals(101, solver.rob(new int[]{100, 100, 1}));
        assertEquals(9, solver.rob(new int[]{3, 1, 2, 5, 4}));
    }

    @Test
    void handlesAllZeroValues() {
        assertEquals(0, solver.rob(new int[]{0, 0, 0, 0}));
    }

    @Test
    void handlesEqualValuesWithBothParities() {
        assertEquals(6, solver.rob(new int[]{3, 3, 3, 3}));
        assertEquals(15, solver.rob(new int[]{5, 5, 5, 5, 5}));
    }

    @Test
    void handlesIncreasingAndDecreasingValues() {
        assertEquals(9, solver.rob(new int[]{1, 2, 3, 4, 5}));
        assertEquals(30, solver.rob(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
        assertEquals(9, solver.rob(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    void handlesAlternatingHighAndLowValues() {
        assertEquals(30, solver.rob(new int[]{10, 1, 10, 1, 10}));
        assertEquals(200, solver.rob(new int[]{1, 100, 1, 100, 1}));
        assertEquals(101, solver.rob(new int[]{100, 1, 1, 1}));
        assertEquals(101, solver.rob(new int[]{1, 1, 1, 100}));
        assertEquals(100, solver.rob(new int[]{50, 50, 50}));
    }

    @Test
    void handlesMaximumDocumentedValues() {
        int[] nums = new int[100];
        Arrays.fill(nums, 400);

        assertTimeoutPreemptively(Duration.ofSeconds(2),
            () -> assertEquals(20_000, solver.rob(nums)));

        int[] increasing = new int[100];
        for (int i = 0; i < increasing.length; i++) {
            increasing[i] = i + 1;
        }
        assertTimeoutPreemptively(Duration.ofSeconds(2),
            () -> assertEquals(2_550, solver.rob(increasing)));
    }

    @Test
    void doesNotMutateInputAndCanBeReused() {
        int[] nums = {2, 7, 9, 3, 1};
        int[] original = nums.clone();

        assertEquals(12, solver.rob(nums));
        assertArrayEquals(original, nums);
        assertEquals(4, solver.rob(new int[]{1, 2, 3, 1}));
        assertEquals(12, solver.rob(nums));
    }

    @Test
    void matchesIndependentEnumerationForAllSmallArrays() {
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            for (int length = 1; length <= 7; length++) {
                assertAllArrays(new int[length], 0);
            }
        });
    }

    private void assertAllArrays(int[] nums, int index) {
        if (index == nums.length) {
            assertEquals(expectedBySubsetEnumeration(nums), solver.rob(nums),
                    "Unexpected result for " + Arrays.toString(nums));
            return;
        }

        for (int value = 0; value <= 4; value++) {
            nums[index] = value;
            assertAllArrays(nums, index + 1);
        }
    }

    /**
     * Enumerates every subset independently of the rolling DP recurrence.
     * A subset is valid only when it contains no adjacent indices.
     */
    private int expectedBySubsetEnumeration(int[] nums) {
        int best = 0;
        int subsetCount = 1 << nums.length;
        for (int mask = 0; mask < subsetCount; mask++) {
            int total = 0;
            boolean valid = true;
            for (int i = 0; i < nums.length; i++) {
                if ((mask & (1 << i)) != 0) {
                    if (i > 0 && (mask & (1 << (i - 1))) != 0) {
                        valid = false;
                        break;
                    }
                    total += nums[i];
                }
            }
            if (valid) {
                best = Math.max(best, total);
            }
        }
        return best;
    }
}

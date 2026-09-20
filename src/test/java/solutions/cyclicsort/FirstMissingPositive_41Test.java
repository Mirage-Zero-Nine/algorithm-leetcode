package solutions.cyclicsort;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link FirstMissingPositive_41}.
 */
public class FirstMissingPositive_41Test {

    private final FirstMissingPositive_41 solver = new FirstMissingPositive_41();

    @Test
    public void testLeetCodeExample1() {
        // [1,2,0] → missing 3
        int[] nums = {1, 2, 0};
        assertEquals(3, solver.firstMissingPositive(nums));
    }

    @Test
    public void testLeetCodeExample2() {
        // [3,4,-1,1] → missing 2
        int[] nums = {3, 4, -1, 1};
        assertEquals(2, solver.firstMissingPositive(nums));
    }

    @Test
    public void testLeetCodeExample3() {
        // [7,8,9,11,12] → missing 1
        int[] nums = {7, 8, 9, 11, 12};
        assertEquals(1, solver.firstMissingPositive(nums));
    }

    @Test
    public void testEmptyArray() {
        // Edge case: length 0
        int[] nums = {};
        assertEquals(1, solver.firstMissingPositive(nums));
    }

    @Test
    public void testSinglePositive() {
        // [1] → missing 2
        int[] nums = {1};
        assertEquals(2, solver.firstMissingPositive(nums));
    }

    @Test
    public void testSingleNonPositive() {
        // [0] → missing 1
        int[] nums = {0};
        assertEquals(1, solver.firstMissingPositive(nums));
    }

    @Test
    public void testSingleNegative() {
        // [-1] → missing 1
        int[] nums = {-1};
        assertEquals(1, solver.firstMissingPositive(nums));
    }

    @Test
    public void testAllNegative() {
        // [-1,-2,-3] → missing 1
        int[] nums = {-1, -2, -3};
        assertEquals(1, solver.firstMissingPositive(nums));
    }

    @Test
    public void testSequentialFrom1() {
        // [1,2,3] → missing 4
        int[] nums = {1, 2, 3};
        assertEquals(4, solver.firstMissingPositive(nums));
    }

    @Test
    public void testMissingMiddle() {
        // [1,3,4] → missing 2
        int[] nums = {1, 3, 4};
        assertEquals(2, solver.firstMissingPositive(nums));
    }

    @Test
    public void testWithZeros() {
        // [0,0,0] → missing 1
        int[] nums = {0, 0, 0};
        assertEquals(1, solver.firstMissingPositive(nums));
    }

    @Test
    public void testLargeGap() {
        // [1,2,3,100] → missing 4
        int[] nums = {1, 2, 3, 100};
        assertEquals(4, solver.firstMissingPositive(nums));
    }

    @Test
    public void testDuplicateAndNegative() {
        // [1,1,2,-1] → missing 3
        int[] nums = {1, 1, 2, -1};
        assertEquals(3, solver.firstMissingPositive(nums));
    }

    @Test
    public void testSingleElementTwo() {
        // [2] → missing 1
        assertEquals(1, solver.firstMissingPositive(new int[]{2}));
    }

    @Test
    public void testStartingFromThree() {
        // [3,4,5] → missing 1
        assertEquals(1, solver.firstMissingPositive(new int[]{3, 4, 5}));
    }

    @Test
    public void testMissingAtBeginningDespiteLaterConsecutiveValues() {
        assertEquals(1, solver.firstMissingPositive(new int[]{2, 3, 4, 5}));
    }

    @Test
    public void testMissingAtEndAfterCompletePrefix() {
        assertEquals(5, solver.firstMissingPositive(new int[]{4, 2, 1, 3}));
    }

    @Test
    public void testMissingImmediatelyAfterArrayLength() {
        assertEquals(4, solver.firstMissingPositive(new int[]{3, 1, 2}));
    }

    @Test
    public void testDuplicatesConsecutive() {
        // [1,2,2,3] → missing 4
        assertEquals(4, solver.firstMissingPositive(new int[]{1, 2, 2, 3}));
    }

    @Test
    public void testAllZeros() {
        // [0,0,0,0] → missing 1
        assertEquals(1, solver.firstMissingPositive(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void testOnlyNegativeAndZeroValues() {
        assertEquals(1, solver.firstMissingPositive(
                new int[]{Integer.MIN_VALUE, -100, -1, 0, Integer.MIN_VALUE + 1}));
    }

    @Test
    public void testVeryLargeValues() {
        // [Integer.MAX_VALUE] → missing 1
        assertEquals(1, solver.firstMissingPositive(new int[]{Integer.MAX_VALUE}));
        // [Integer.MAX_VALUE, 1] → missing 2
        assertEquals(2, solver.firstMissingPositive(new int[]{Integer.MAX_VALUE, 1}));
    }

    @Test
    public void testMixPositiveZeroNegative() {
        // [-3, 0, 5, -2, 1, 4, 3] → missing 2
        assertEquals(2, solver.firstMissingPositive(new int[]{-3, 0, 5, -2, 1, 4, 3}));
    }

    @Test
    public void testSignedExtremesDoNotOverflowMarking() {
        assertEquals(2, solver.firstMissingPositive(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 1, 0}));
        assertEquals(1, solver.firstMissingPositive(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, -1}));
    }

    @Test
    public void testManyDuplicatesWithInteriorGap() {
        assertEquals(4, solver.firstMissingPositive(new int[]{2, 1, 2, 3, 3, 1, 2}));
    }

    @Test
    public void testLargeRandomCrossCheck() {
        Random rng = new Random(42L);
        int n = 1500;
        int[] nums = new int[n];
        Set<Integer> positives = new HashSet<>();
        for (int i = 0; i < n; i++) {
            nums[i] = rng.nextInt(2001) - 500; // range [-500, 1500]
            if (nums[i] > 0) positives.add(nums[i]);
        }
        // Reference: find first missing positive via HashSet
        int expected = 1;
        while (positives.contains(expected)) expected++;

        int result = solver.firstMissingPositive(nums.clone());
        assertEquals(expected, result);
    }

    @Test
    public void testSeededRandomArraysAgainstIndependentOracle() {
        Random rng = new Random(20260919L);
        for (int trial = 0; trial < 400; trial++) {
            int length = rng.nextInt(40);
            int[] values = new int[length];
            for (int i = 0; i < length; i++) {
                values[i] = rng.nextInt(31) - 10;
            }
            assertEquals(expectedMissingPositive(values), solver.firstMissingPositive(values.clone()),
                    "trial=" + trial);
        }
    }

    @Test
    public void testIndependentOracleCoversAllArraysOfSmallDomain() {
        // Exhaust every length-4 array over {-2,-1,0,1,2,3,4,5}; this includes
        // all combinations of duplicates and every possible first gap through 6.
        int domainSize = 8;
        for (int encoded = 0; encoded < domainSize * domainSize * domainSize * domainSize; encoded++) {
            int remaining = encoded;
            int[] values = new int[4];
            for (int i = 0; i < values.length; i++) {
                values[i] = remaining % domainSize - 2;
                remaining /= domainSize;
            }
            assertEquals(expectedMissingPositive(values), solver.firstMissingPositive(values),
                    "encoded=" + encoded);
        }
    }

    @Test
    public void testPropertyResultBounds() {
        // Property: result >= 1 and result <= n+1 for various inputs
        int[][] inputs = {
            {}, {1}, {2}, {-1, -2}, {1, 2, 3, 4, 5},
            {5, 4, 3, 2, 1}, {100, 200, 300}
        };
        for (int[] nums : inputs) {
            int result = solver.firstMissingPositive(nums.clone());
            assertTrue(result >= 1, "Result must be >= 1, got " + result);
            assertTrue(result <= nums.length + 1, "Result must be <= n+1, got " + result);
        }
    }
    @Test
    public void testEverySmallArrayAgainstPositiveSet() {
        for (int encoded = 0; encoded < 46656; encoded++) {
            int[] values = new int[6];
            Set<Integer> present = new HashSet<>();
            int remaining = encoded;
            for (int i = 0; i < values.length; i++) {
                values[i] = remaining % 6 - 1;
                remaining /= 6;
                present.add(values[i]);
            }
            int expected = 1;
            while (present.contains(expected)) expected++;
            assertEquals(expected, solver.firstMissingPositive(values), "encoded=" + encoded);
        }
    }

    @Test
    public void testIntegerMinimumDoesNotBecomeAPresentIndex() {
        assertEquals(4, solver.firstMissingPositive(
                new int[]{Integer.MIN_VALUE, 3, Integer.MAX_VALUE, 2, 1, 0}));
    }

    @Test
    public void testFreshInputsPreventMutationStateLeakage() {
        int[] first = {1, 2, 0};
        assertEquals(3, solver.firstMissingPositive(first));

        // The first call mutates its own array while marking presence. A later
        // call must depend only on this fresh input, not on prior marker values.
        assertEquals(1, solver.firstMissingPositive(new int[]{2, 2, 3, 4}));
        assertEquals(2, solver.firstMissingPositive(new int[]{1, 3, 1, 4}));
    }

    @Test
    public void testExactMaximumLengthWithMissingInteriorValue() {
        int length = 100_000;
        int missing = 54_321;
        int[] values = new int[length];
        for (int i = 0; i < length; i++) {
            values[i] = i + 1;
        }
        values[missing - 1] = Integer.MIN_VALUE;

        // A deterministic Fisher-Yates shuffle exercises arbitrary ordering
        // while retaining exactly one missing positive in the legal maximum.
        Random rng = new Random(41L);
        for (int i = values.length - 1; i > 0; i--) {
            int swap = rng.nextInt(i + 1);
            int temporary = values[i];
            values[i] = values[swap];
            values[swap] = temporary;
        }
        assertEquals(missing, solver.firstMissingPositive(values));
    }

    @Test
    public void testGiantReversedSequenceWithInteriorGap() {
        int[] values = new int[20000];
        for (int i = 0; i < values.length; i++) values[i] = values.length - i;
        values[7654] = Integer.MIN_VALUE;
        assertEquals(12346, solver.firstMissingPositive(values));
    }

    private int expectedMissingPositive(int[] values) {
        Set<Integer> present = new HashSet<>();
        for (int value : values) {
            if (value > 0) {
                present.add(value);
            }
        }
        int candidate = 1;
        while (present.contains(candidate)) {
            candidate++;
        }
        return candidate;
    }
}

package solutions.bitmanipulation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MajorityElement_169Test {
    private final MajorityElement_169 solver = new MajorityElement_169();

    @Test public void testBasic() {
        assertEquals(3, solver.majorityElement(new int[]{3, 2, 3}));
    }

    @Test public void testMoreElements() {
        assertEquals(2, solver.majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }

    @Test public void testSingle() {
        assertEquals(7, solver.majorityElement(new int[]{7}));
    }

    @Test public void testAllSame() {
        assertEquals(5, solver.majorityElement(new int[]{5, 5, 5, 5, 5}));
    }

    @Test public void testNegative() {
        assertEquals(-1, solver.majorityElement(new int[]{-1, -1, -1, 2, 3}));
    }

    @Test public void testTwoElements() {
        assertEquals(1, solver.majorityElement(new int[]{1, 1}));
    }

    @Test public void testMajorityAtEnd() {
        assertEquals(4, solver.majorityElement(new int[]{1, 4, 4, 4, 4}));
    }

    @Test public void testMajorityAtStart() {
        assertEquals(9, solver.majorityElement(new int[]{9, 9, 9, 1, 2}));
    }

    @Test public void testZeroMajority() {
        assertEquals(0, solver.majorityElement(new int[]{0, 0, 0, 1, 2}));
    }

    @Test public void testLargeNegative() {
        assertEquals(-100, solver.majorityElement(new int[]{-100, -100, -100, 50, 50}));
    }

    @Test public void testGiantCase() {
        int[] arr = new int[1001];
        for (int i = 0; i < 501; i++) arr[i] = 42;
        for (int i = 501; i < 1001; i++) arr[i] = 7;
        assertEquals(42, solver.majorityElement(arr));
    }

    @Test public void testExactMajoritiesAcrossSignedBoundaries() {
        for (int majority : new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, -1, 0, 1, 0x55555555}) {
            int[] nums = {majority, ~majority, majority, ~majority, majority};
            assertEquals(majority, solver.majorityElement(nums));
        }
    }

    @Test public void testMajorityAtEveryPositionAmongMinorities() {
        for (int minorityPosition = 0; minorityPosition < 9; minorityPosition++) {
            int[] nums = new int[9];
            java.util.Arrays.fill(nums, -73);
            nums[minorityPosition] = 73;
            assertEquals(-73, solver.majorityElement(nums));
        }
    }

    @Test public void testLargeExactMajorityWithVaryingMinorityBits() {
        int[] nums = new int[50001];
        java.util.Arrays.fill(nums, 0, 25001, Integer.MIN_VALUE);
        for (int i = 25001; i < nums.length; i++) nums[i] = i - 25001;
        assertEquals(Integer.MIN_VALUE, solver.majorityElement(nums));
    }

    @Test public void testMajorityAtBeginningWithSeveralMinorityValues() {
        assertEquals(17, solver.majorityElement(new int[]{17, 17, 17, 4, -2, 4, 17, 9, 17}));
    }

    @Test public void testMajorityInTheMiddleWithAlternatingMinorities() {
        assertEquals(-8, solver.majorityElement(new int[]{1, -8, 3, -8, -8, -8, 4, -8, 5}));
    }

    @Test public void testMajorityAtEndWithInterleavedMinorities() {
        assertEquals(23, solver.majorityElement(new int[]{23, 7, -1, 7, 23, 7, 23, 23, 23}));
    }

    @Test public void testOddLengthMajorityAtTheExactThreshold() {
        assertEquals(6, solver.majorityElement(new int[]{6, 1, 2, 6, 3, 6, 6}));
    }

    @Test public void testEvenLengthMajorityAtTheExactThreshold() {
        assertEquals(-6, solver.majorityElement(new int[]{-6, 0, -6, 8, -6, -6, 9, -6}));
    }

    @Test public void testDuplicateMinorityValuesDoNotWin() {
        assertEquals(11, solver.majorityElement(new int[]{2, 2, 11, 2, 3, 11, 11, 11, 2, 11, 11}));
    }

    @Test public void testZeroAndNegativeValuesHaveIndependentBitPatterns() {
        assertEquals(-256, solver.majorityElement(new int[]{0, -256, 0, -256, -256, -256, 5}));
    }

    @Test public void testEverySignedBoundaryValueAsMajority() {
        int[] candidates = {Integer.MIN_VALUE, Integer.MAX_VALUE, -1, 0, 1};
        for (int candidate : candidates) {
            int[] nums = {candidate, 123456789, candidate, -987654321, candidate,
                    candidate, 42, candidate, candidate};
            assertEquals(candidate, solver.majorityElement(nums), "candidate=" + candidate);
        }
    }

    @Test public void testBitAlternatingValuesWithNegativeMajority() {
        assertEquals(0xAAAAAAAA, solver.majorityElement(new int[]{0xAAAAAAAA, 0x55555555,
                0xAAAAAAAA, 0, 0xAAAAAAAA, 0x55555555, 0xAAAAAAAA}));
    }

    @Test public void testInputIsNotMutated() {
        int[] nums = {Integer.MAX_VALUE, -3, Integer.MIN_VALUE, Integer.MAX_VALUE, 7,
                Integer.MAX_VALUE, -3};
        int[] original = nums.clone();

        assertEquals(Integer.MAX_VALUE, solver.majorityElement(nums));
        assertArrayEquals(original, nums);
    }

    @Test public void testSameSolverCanBeReusedAcrossIndependentCalls() {
        assertEquals(4, solver.majorityElement(new int[]{4, 1, 4, 2, 4}));
        assertEquals(-9, solver.majorityElement(new int[]{3, -9, 3, -9, -9, -9, 8}));
        assertEquals(Integer.MIN_VALUE,
                solver.majorityElement(new int[]{Integer.MIN_VALUE, 0, Integer.MIN_VALUE}));
    }

    @Test public void testFreshInputAfterEarlierInputMutationByCaller() {
        int[] nums = {8, 8, 1, 8, 2};
        assertEquals(8, solver.majorityElement(nums));
        nums[2] = -100;
        assertEquals(8, solver.majorityElement(nums));
    }

    @Test public void testMaximumLeetCodeLengthAtExactMajorityThreshold() {
        int[] nums = new int[50_000];
        Arrays.fill(nums, 25_000, nums.length, -7);
        Arrays.fill(nums, 0, 25_001, Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, solver.majorityElement(nums));
    }

    @Test public void testMaximumLeetCodeValueMagnitudeWithMinorityAtBothEnds() {
        int[] nums = new int[31];
        Arrays.fill(nums, Integer.MIN_VALUE);
        nums[0] = 0;
        nums[30] = Integer.MAX_VALUE;
        assertEquals(Integer.MIN_VALUE, solver.majorityElement(nums));
    }

    @Test public void testExhaustiveSmallArraysAgainstFrequencyOracle() {
        int[] values = {Integer.MIN_VALUE, -1, 0, Integer.MAX_VALUE};
        int checked = 0;
        for (int length = 1; length <= 6; length++) {
            int combinations = 1;
            for (int i = 0; i < length; i++) combinations *= values.length;
            for (int encoded = 0; encoded < combinations; encoded++) {
                int[] nums = new int[length];
                int value = encoded;
                for (int i = 0; i < length; i++) {
                    nums[i] = values[value % values.length];
                    value /= values.length;
                }
                Integer expected = majorityIfPresent(nums);
                if (expected != null) {
                    assertEquals(expected, solver.majorityElement(nums), Arrays.toString(nums));
                    checked++;
                }
            }
        }
        assertEquals(1_140, checked);
    }

    @Test public void testSeededDistributedMajoritiesAgainstFrequencyOracle() {
        Random random = new Random(169_2026L);
        int[] minorityValues = {Integer.MIN_VALUE, -1_000_000_000, -1, 0, 1, 1_000_000_000,
                Integer.MAX_VALUE};

        for (int caseNumber = 0; caseNumber < 250; caseNumber++) {
            int length = 1 + random.nextInt(500);
            int[] nums = new int[length];
            int majority = random.nextInt();
            int majorityCount = length / 2 + 1 + random.nextInt(length - length / 2);
            for (int i = 0; i < majorityCount; i++) {
                nums[i] = majority;
            }
            for (int i = majorityCount; i < length; i++) {
                int minority;
                do {
                    minority = minorityValues[random.nextInt(minorityValues.length)];
                } while (minority == majority);
                nums[i] = minority;
            }
            for (int i = length - 1; i > 0; i--) {
                int position = random.nextInt(i + 1);
                int swap = nums[i];
                nums[i] = nums[position];
                nums[position] = swap;
            }

            assertEquals(majorityIfPresent(nums), solver.majorityElement(nums),
                    "case=" + caseNumber);
        }
    }

    @Test public void testMajorityCanBeTheOnlyValue() {
        assertEquals(Integer.MIN_VALUE, solver.majorityElement(new int[]{
                Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE}));
    }

    @Test public void testNearThresholdWithManyDistinctMinorities() {
        assertEquals(314159, solver.majorityElement(new int[]{314159, -1, 314159, -2, 314159,
                -3, 314159, -4, 314159, -5, 314159, -6, 314159}));
    }

    @Test public void testRepeatedInvocationDoesNotDependOnPreviousArrayLength() {
        assertEquals(1, solver.majorityElement(new int[]{1, 1, 0}));
        assertEquals(2, solver.majorityElement(new int[]{2, 2, 2, 8, 8, 2, 8}));
        assertEquals(3, solver.majorityElement(new int[]{3}));
    }

    private static Integer majorityIfPresent(int[] nums) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int num : nums) {
            int count = frequencies.merge(num, 1, Integer::sum);
            if (count > nums.length / 2) return num;
        }
        return null;
    }
}

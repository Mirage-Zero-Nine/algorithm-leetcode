package solutions.binarysearch;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MissingNumber_1228Test {
    private final MissingNumber_1228 solution = new MissingNumber_1228();

    @Test
    void officialExampleWithPositiveCommonDifference() {
        assertEquals(9, solution.missingNumber(new int[]{5, 7, 11, 13}));
    }

    @Test
    void officialExampleWithMissingSecondTerm() {
        assertEquals(14, solution.missingNumber(new int[]{15, 13, 12}));
    }

    @Test
    void missingTermAtBeginningOfObservedInterior() {
        assertEquals(3, solution.missingNumber(new int[]{1, 5, 7, 9}));
    }

    @Test
    void missingTermAtEndOfObservedInterior() {
        assertEquals(20, solution.missingNumber(new int[]{0, 5, 10, 15, 25}));
    }

    @Test
    void consecutiveProgression() {
        assertEquals(1, solution.missingNumber(new int[]{0, 2, 3, 4}));
    }

    @Test
    void positiveProgressionWithCommonDifferenceTwo() {
        assertEquals(7, solution.missingNumber(new int[]{1, 3, 5, 9, 11}));
    }

    @Test
    void negativeValuesInIncreasingProgression() {
        assertEquals(-6, solution.missingNumber(new int[]{-10, -8, -4, -2}));
    }

    @Test
    void decreasingProgression() {
        assertEquals(13, solution.missingNumber(new int[]{15, 11, 9, 7, 5, 3}));
    }

    @Test
    void negativeDecreasingProgression() {
        assertEquals(-6, solution.missingNumber(new int[]{0, -2, -4, -8, -10}));
    }

    @Test
    void progressionCrossingZero() {
        assertEquals(3, solution.missingNumber(new int[]{-3, -1, 1, 5}));
    }

    @Test
    void minimumValidObservedLength() {
        assertEquals(103, solution.missingNumber(new int[]{100, 106, 109}));
    }

    @Test
    void minimumValidObservedLengthWithMissingPenultimateTerm() {
        assertEquals(106, solution.missingNumber(new int[]{100, 103, 109}));
    }

    @Test
    void twoRemainingEndpoints() {
        // The class intentionally supports this smaller-than-LeetCode input.
        assertEquals(3, solution.missingNumber(new int[]{1, 5}));
    }

    @Test
    void twoRemainingNegativeEndpoints() {
        assertEquals(-6, solution.missingNumber(new int[]{-9, -3}));
    }

    @Test
    void twoRemainingEndpointsAtIntegerMinimumBoundary() {
        assertEquals(Integer.MIN_VALUE + 1,
                solution.missingNumber(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE + 2}));
    }

    @Test
    void twoRemainingEndpointsAtIntegerMaximumBoundary() {
        assertEquals(Integer.MAX_VALUE - 1,
                solution.missingNumber(new int[]{Integer.MAX_VALUE - 2, Integer.MAX_VALUE}));
    }

    @Test
    void allEqualValuesUseDocumentedFallback() {
        assertEquals(5, solution.missingNumber(new int[]{5, 5, 5, 5}));
    }

    @Test
    void allEqualZeroValuesUseDocumentedFallback() {
        assertEquals(0, solution.missingNumber(new int[]{0, 0, 0}));
    }

    @Test
    void allEqualIntegerMinimumValuesUseDocumentedFallback() {
        assertEquals(Integer.MIN_VALUE,
                solution.missingNumber(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE}));
    }

    @Test
    void singletonUsesDocumentedFallback() {
        assertEquals(42, solution.missingNumber(new int[]{42}));
    }

    @Test
    void emptyArrayUsesDocumentedFallback() {
        assertEquals(0, solution.missingNumber(new int[0]));
    }

    @Test
    void completeIncreasingSequenceUsesDocumentedNoGapFallback() {
        // Invalid under the original problem (no term is missing), but defined by the class Javadoc.
        assertEquals(1, solution.missingNumber(new int[]{1, 3, 5, 7}));
    }

    @Test
    void completeDecreasingSequenceUsesDocumentedNoGapFallback() {
        assertEquals(-1, solution.missingNumber(new int[]{-1, -3, -5, -7}));
    }

    @Test
    void exactIntegerMinimumBoundaryWithIncreasingStep() {
        int[] observed = {Integer.MIN_VALUE, Integer.MIN_VALUE + 2, Integer.MIN_VALUE + 3};
        assertEquals(Integer.MIN_VALUE + 1, solution.missingNumber(observed));
    }

    @Test
    void exactIntegerMaximumBoundaryWithIncreasingStep() {
        int[] observed = {Integer.MAX_VALUE - 3, Integer.MAX_VALUE - 2, Integer.MAX_VALUE};
        assertEquals(Integer.MAX_VALUE - 1, solution.missingNumber(observed));
    }

    @Test
    void exactMaximumLeetCodeObservedLength() {
        int[] observed = progressionWithoutTerm(-100_000, 97, 1_001, 500);
        assertEquals(expectedByIndependentSum(observed), solution.missingNumber(observed));
    }

    @Test
    void maximumLengthDecreasingProgression() {
        int[] observed = progressionWithoutTerm(100_000, -97, 1_001, 500);
        assertEquals(expectedByIndependentSum(observed), solution.missingNumber(observed));
    }

    @Test
    void longProgressionWithMissingFirstInteriorTerm() {
        int[] observed = progressionWithoutTerm(-20_000, 37, 2_001, 1);
        assertEquals(expectedByIndependentSum(observed), solution.missingNumber(observed));
    }

    @Test
    void longProgressionWithMissingLastInteriorTerm() {
        int[] observed = progressionWithoutTerm(20_000, -37, 2_001, 1_999);
        assertEquals(expectedByIndependentSum(observed), solution.missingNumber(observed));
    }

    @Test
    void exhaustiveSmallProgressionsUseIndependentSumOracle() {
        for (int start = -20; start <= 20; start++) {
            for (int step = -7; step <= 7; step++) {
                for (int originalLength = 4; originalLength <= 24; originalLength++) {
                    for (int missing = 1; missing < originalLength - 1; missing++) {
                        int[] observed = progressionWithoutTerm(start, step, originalLength, missing);
                        assertEquals(expectedByIndependentSum(observed), solution.missingNumber(observed),
                                "start=" + start + ", step=" + step + ", length=" + originalLength
                                        + ", missing=" + missing);
                    }
                }
            }
        }
    }

    @Test
    void variedProgressionsUseIndependentSumOracle() {
        int[][] cases = {
                progressionWithoutTerm(7, 11, 11, 2),
                progressionWithoutTerm(-31, 5, 17, 8),
                progressionWithoutTerm(100, -13, 19, 14),
                progressionWithoutTerm(-2_000, 101, 31, 15),
                progressionWithoutTerm(2_000, -101, 31, 15),
                progressionWithoutTerm(0, 1_000, 51, 25),
                progressionWithoutTerm(0, -1_000, 51, 25)
        };
        for (int[] observed : cases) {
            assertEquals(expectedByIndependentSum(observed), solution.missingNumber(observed));
        }
    }

    @Test
    void inputIsNotMutatedAndCallsRemainIndependent() {
        int[] first = {5, 7, 11, 13};
        int[] firstBefore = first.clone();
        int[] second = {-10, -8, -4, -2};
        int[] secondBefore = second.clone();

        assertEquals(9, solution.missingNumber(first));
        assertEquals(-6, solution.missingNumber(second));
        assertEquals(9, solution.missingNumber(first));
        assertArrayEquals(firstBefore, first);
        assertArrayEquals(secondBefore, second);
    }

    private static int[] progressionWithoutTerm(int start, int step, int originalLength, int missingIndex) {
        int[] observed = new int[originalLength - 1];
        int outputIndex = 0;
        for (int i = 0; i < originalLength; i++) {
            if (i != missingIndex) {
                observed[outputIndex++] = Math.toIntExact((long) start + (long) step * i);
            }
        }
        return observed;
    }

    /**
     * Computes the missing term from the arithmetic-series sum, independently of the gap scan under test.
     */
    private static int expectedByIndependentSum(int[] observed) {
        long termCount = observed.length + 1L;
        long completeSum = termCount * ((long) observed[0] + observed[observed.length - 1]) / 2;
        long observedSum = Arrays.stream(observed).asLongStream().sum();
        return Math.toIntExact(completeSum - observedSum);
    }
}

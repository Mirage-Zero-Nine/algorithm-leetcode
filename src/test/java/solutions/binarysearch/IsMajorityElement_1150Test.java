package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

/** Contract and boundary tests for the binary-search solution to LeetCode 1150. */
public class IsMajorityElement_1150Test {

    private final IsMajorityElement_1150 solution = new IsMajorityElement_1150();

    @Test
    public void testOfficialExampleOne() {
        assertExpected(new int[]{2, 4, 5, 5, 5, 5, 5, 6, 6}, 5);
    }

    @Test
    public void testOfficialExampleTwoIsNotMajority() {
        assertExpected(new int[]{10, 100, 101, 101}, 101);
    }

    @Test
    public void testSingletonPresentIsMajority() {
        assertExpected(new int[]{1}, 1);
    }

    @Test
    public void testSingletonAbsentIsNotMajority() {
        assertExpected(new int[]{9}, 8);
    }

    @Test
    public void testTwoElementTargetAppearingTwice() {
        assertExpected(new int[]{7, 7}, 7);
    }

    @Test
    public void testTwoElementTargetAppearingExactlyHalf() {
        assertExpected(new int[]{1, 2}, 1);
        assertExpected(new int[]{1, 2}, 2);
    }

    @Test
    public void testEvenLengthExactlyHalfAtBeginningIsNotMajority() {
        assertExpected(new int[]{1, 1, 2, 2}, 1);
    }

    @Test
    public void testEvenLengthExactlyHalfAtEndIsNotMajority() {
        assertExpected(new int[]{1, 1, 2, 2}, 2);
    }

    @Test
    public void testOddLengthMinimumStrictMajority() {
        assertExpected(new int[]{1, 1, 2, 2, 2}, 2);
        assertExpected(new int[]{1, 1, 1, 2, 2}, 1);
    }

    @Test
    public void testOddLengthNoMajority() {
        assertExpected(new int[]{1, 2, 2, 3, 3}, 2);
        assertExpected(new int[]{1, 1, 2, 2, 3}, 1);
    }

    @Test
    public void testMajorityRunAtArrayStart() {
        assertExpected(new int[]{3, 3, 3, 3, 4, 5, 6}, 3);
    }

    @Test
    public void testMajorityRunAtArrayEnd() {
        assertExpected(new int[]{1, 2, 3, 4, 4, 4, 4}, 4);
    }

    @Test
    public void testMajorityRunInTheInterior() {
        assertExpected(new int[]{1, 2, 2, 2, 2, 3, 4}, 2);
    }

    @Test
    public void testTargetAbsentBelowEveryValue() {
        assertExpected(new int[]{4, 4, 4, 5, 6}, 3);
    }

    @Test
    public void testTargetAbsentBetweenRuns() {
        assertExpected(new int[]{1, 1, 5, 5, 5, 9}, 6);
    }

    @Test
    public void testTargetAbsentAboveEveryValue() {
        assertExpected(new int[]{1, 2, 2, 2, 3}, 10);
    }

    @Test
    public void testAllValuesDistinctHaveNoMajority() {
        int[] values = {1, 2, 3, 4, 5, 6, 7};
        for (int value : values) {
            assertExpected(values, value);
        }
    }

    @Test
    public void testSeveralEqualSizedRunsHaveNoMajority() {
        int[] values = {1, 1, 2, 2, 3, 3, 4, 4};
        for (int value : new int[]{1, 2, 3, 4}) {
            assertExpected(values, value);
        }
    }

    @Test
    public void testAllElementsEqual() {
        assertExpected(new int[]{6, 6, 6, 6, 6, 6}, 6);
    }

    @Test
    public void testOfficialValueBoundaries() {
        assertExpected(new int[]{1, 1, 1, 1, 999999999, 1000000000}, 1);
        assertExpected(new int[]{1, 2, 1000000000, 1000000000, 1000000000}, 1000000000);
        assertExpected(new int[]{1, 1, 1000000000, 1000000000}, 1000000000);
    }

    @Test
    public void testSignedJavaIntBoundariesSupportedByComparisons() {
        int[] values = {Integer.MIN_VALUE, Integer.MIN_VALUE, -1, 0, Integer.MAX_VALUE,
                Integer.MAX_VALUE};
        assertExpected(values, Integer.MIN_VALUE);
        assertExpected(values, Integer.MAX_VALUE);
        assertExpected(values, 1);
    }

    @Test
    public void testMaximumLengthStrictMajorityAtBeginning() {
        int[] values = new int[1000];
        Arrays.fill(values, 0, 501, 7);
        Arrays.fill(values, 501, values.length, 8);
        assertExpected(values, 7);
        assertExpected(values, 8);
    }

    @Test
    public void testMaximumLengthExactlyHalfAtEnd() {
        int[] values = new int[1000];
        Arrays.fill(values, 0, 500, 1);
        Arrays.fill(values, 500, values.length, 1000000000);
        assertExpected(values, 1000000000);
        values[499] = 1000000000;
        assertExpected(values, 1000000000);
    }

    @Test
    public void testMaximumLengthAbsentTarget() {
        int[] values = new int[1000];
        Arrays.fill(values, 0, 500, 1);
        Arrays.fill(values, 500, values.length, 1000000000);
        assertExpected(values, 500000000);
    }

    @Test
    public void testExhaustiveSmallSortedMultiplicityCasesAgainstFrequencyOracle() {
        for (int lower = 0; lower <= 8; lower++) {
            for (int targetCount = 0; targetCount <= 8; targetCount++) {
                for (int upper = 0; upper <= 8; upper++) {
                    int length = lower + targetCount + upper;
                    if (length == 0) {
                        continue;
                    }
                    int[] values = new int[length];
                    Arrays.fill(values, 0, lower, 1);
                    Arrays.fill(values, lower, lower + targetCount, 2);
                    Arrays.fill(values, lower + targetCount, length, 3);
                    assertEquals(frequencyOracle(values, 2),
                            solution.isMajorityElement(values, 2),
                            "lower=" + lower + ", targetCount=" + targetCount
                                    + ", upper=" + upper);
                }
            }
        }
    }

    @Test
    public void testExhaustiveSmallTargetsIncludingAbsentValues() {
        for (int length = 1; length <= 9; length++) {
            for (int firstRun = 0; firstRun <= length; firstRun++) {
                for (int secondRun = 0; secondRun <= length - firstRun; secondRun++) {
                    int[] values = new int[length];
                    Arrays.fill(values, 0, firstRun, -2);
                    Arrays.fill(values, firstRun, firstRun + secondRun, 0);
                    Arrays.fill(values, firstRun + secondRun, length, 2);
                    for (int target : new int[]{-3, -2, 0, 1, 2, 3}) {
                        assertEquals(frequencyOracle(values, target),
                                solution.isMajorityElement(values, target),
                                "values=" + Arrays.toString(values) + ", target=" + target);
                    }
                }
            }
        }
    }

    @Test
    public void testSeededSortedArraysAgainstIndependentFrequencyOracle() {
        Random random = new Random(1150L);
        for (int caseNumber = 0; caseNumber < 250; caseNumber++) {
            int length = 1 + random.nextInt(1000);
            int[] values = new int[length];
            int current = -1000 + random.nextInt(11);
            for (int i = 0; i < length; i++) {
                current += random.nextInt(4);
                values[i] = current;
            }
            int target;
            if (caseNumber % 3 == 0) {
                target = values[random.nextInt(length)];
            } else if (caseNumber % 3 == 1) {
                target = values[0] - 1;
            } else {
                target = values[length - 1] + 1;
            }
            assertEquals(frequencyOracle(values, target),
                    solution.isMajorityElement(values, target),
                    "case=" + caseNumber + ", target=" + target);
        }
    }

    @Test
    public void testInputIsNotMutated() {
        int[] values = {1, 2, 2, 2, 5, 8};
        int[] original = values.clone();
        assertExpected(values, 2);
        assertArrayEquals(original, values);
    }

    @Test
    public void testRepeatedCallsOnOneInstanceAreIndependent() {
        assertExpected(new int[]{1, 1, 1, 2, 3}, 1);
        assertExpected(new int[]{1, 2, 2, 3, 3}, 2);
        assertExpected(new int[]{4, 4, 4, 4}, 4);
        assertExpected(new int[]{1, 2, 3, 4}, 3);
    }

    @Test
    public void testEmptyArrayReturnsFalseAsImplementationGuard() {
        assertEquals(false, solution.isMajorityElement(new int[]{}, 1));
    }

    private void assertExpected(int[] values, int target) {
        assertEquals(frequencyOracle(values, target), solution.isMajorityElement(values, target),
                "values=" + Arrays.toString(values) + ", target=" + target);
    }

    /** Independent O(n) oracle; this deliberately does not use the solution's binary searches. */
    private static boolean frequencyOracle(int[] values, int target) {
        int occurrences = 0;
        for (int value : values) {
            if (value == target) {
                occurrences++;
            }
        }
        return occurrences > values.length / 2;
    }
}

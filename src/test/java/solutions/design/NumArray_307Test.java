package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * Stateful contract tests for {@link NumArray_307} (LeetCode 307).
 *
 * <p>Expected range sums are calculated independently by a direct array scan. This keeps the
 * tests useful even if the segment-tree implementation and a second optimized implementation
 * would share the same defect.</p>
 */
public class NumArray_307Test {

    @Test
    public void officialExample() {
        NumArray_307 numArray = new NumArray_307(new int[]{1, 3, 5});

        assertEquals(9, numArray.sumRange(0, 2));
        numArray.update(1, 2);
        assertEquals(8, numArray.sumRange(0, 2));
    }

    @Test
    public void singletonSupportsReadAndUpdate() {
        NumArray_307 numArray = new NumArray_307(new int[]{42});

        assertEquals(42, numArray.sumRange(0, 0));
        numArray.update(0, -3);
        assertEquals(-3, numArray.sumRange(0, 0));
    }

    @Test
    public void fullRangeUsesEveryElement() {
        NumArray_307 numArray = new NumArray_307(new int[]{0, 9, 5, 7, 3});

        assertEquals(24, numArray.sumRange(0, 4));
    }

    @Test
    public void rangeContainedInLeftSubtree() {
        NumArray_307 numArray = new NumArray_307(new int[]{2, 4, 6, 8, 10, 12});

        assertEquals(12, numArray.sumRange(0, 2));
        assertEquals(10, numArray.sumRange(1, 2));
    }

    @Test
    public void rangeContainedInRightSubtree() {
        NumArray_307 numArray = new NumArray_307(new int[]{2, 4, 6, 8, 10, 12});

        assertEquals(30, numArray.sumRange(3, 5));
        assertEquals(18, numArray.sumRange(3, 4));
    }

    @Test
    public void rangeSpanningBothSubtrees() {
        NumArray_307 numArray = new NumArray_307(new int[]{2, 4, 6, 8, 10, 12});

        assertEquals(28, numArray.sumRange(1, 4));
        assertEquals(36, numArray.sumRange(2, 5));
    }

    @Test
    public void nonPowerOfTwoLengthSplitsCorrectly() {
        int[] values = {7, -2, 11, 0, 5, -9, 13};
        NumArray_307 numArray = new NumArray_307(values);

        assertRangesMatch(values, numArray,
                new int[][]{{0, 0}, {1, 5}, {0, 6}, {2, 4}, {5, 6}});
    }

    @Test
    public void everySingletonRangeIsAddressable() {
        int[] values = {8, -1, 0, 14, -7, 3, 6};
        NumArray_307 numArray = new NumArray_307(values);

        for (int i = 0; i < values.length; i++) {
            assertEquals(values[i], numArray.sumRange(i, i), "index " + i);
        }
    }

    @Test
    public void multipleUpdatesToSameIndexAreVisible() {
        NumArray_307 numArray = new NumArray_307(new int[]{5, 5, 5});

        numArray.update(1, 10);
        assertEquals(20, numArray.sumRange(0, 2));
        numArray.update(1, -5);
        assertEquals(5, numArray.sumRange(0, 2));
        numArray.update(1, 100);
        assertEquals(100, numArray.sumRange(1, 1));
        assertEquals(110, numArray.sumRange(0, 2));
    }

    @Test
    public void updatingToTheExistingValuePreservesSums() {
        NumArray_307 numArray = new NumArray_307(new int[]{-4, 0, 12, 3});

        numArray.update(2, 12);
        assertEquals(11, numArray.sumRange(0, 3));
        assertEquals(12, numArray.sumRange(2, 2));
    }

    @Test
    public void updatesAtBothBoundariesPropagateToFullAndPartialRanges() {
        NumArray_307 numArray = new NumArray_307(new int[]{1, 2, 3, 4, 5});

        numArray.update(0, 10);
        numArray.update(4, 20);
        assertEquals(39, numArray.sumRange(0, 4));
        assertEquals(12, numArray.sumRange(0, 1));
        assertEquals(24, numArray.sumRange(3, 4));
    }

    @Test
    public void negativeAndZeroValuesAreSummed() {
        NumArray_307 numArray = new NumArray_307(new int[]{-1, -2, -3, -4, 0});

        assertEquals(-10, numArray.sumRange(0, 3));
        numArray.update(2, 3);
        assertEquals(-3, numArray.sumRange(1, 3));
        assertEquals(0, numArray.sumRange(4, 4));
    }

    @Test
    public void officialValueBoundsAndDuplicatesAreHandled() {
        NumArray_307 numArray = new NumArray_307(new int[]{-100, 100, -100, 100, 0, 0});

        assertEquals(0, numArray.sumRange(0, 3));
        assertEquals(0, numArray.sumRange(0, 5));
        numArray.update(5, -100);
        assertEquals(-100, numArray.sumRange(4, 5));
    }

    @Test
    public void JavaIntegerExtremesWorkForSingletonRanges() {
        NumArray_307 min = new NumArray_307(new int[]{Integer.MIN_VALUE});
        NumArray_307 max = new NumArray_307(new int[]{Integer.MAX_VALUE});

        assertEquals(Integer.MIN_VALUE, min.sumRange(0, 0));
        assertEquals(Integer.MAX_VALUE, max.sumRange(0, 0));
        min.update(0, Integer.MAX_VALUE);
        max.update(0, Integer.MIN_VALUE);
        assertEquals(Integer.MAX_VALUE, min.sumRange(0, 0));
        assertEquals(Integer.MIN_VALUE, max.sumRange(0, 0));
    }

    @Test
    public void constructorSnapshotsInputValues() {
        int[] source = {3, 1, 4, 1, 5};
        NumArray_307 numArray = new NumArray_307(source);

        source[0] = 300;
        source[4] = -500;
        assertEquals(14, numArray.sumRange(0, 4));
        assertEquals(6, numArray.sumRange(1, 3));
    }

    @Test
    public void repeatedQueriesDoNotChangeState() {
        NumArray_307 numArray = new NumArray_307(new int[]{6, -2, 9, 4});

        assertEquals(13, numArray.sumRange(0, 2));
        assertEquals(13, numArray.sumRange(0, 2));
        assertEquals(11, numArray.sumRange(1, 3));
        assertEquals(13, numArray.sumRange(0, 2));
    }

    @Test
    public void independentInstancesDoNotShareTreeState() {
        NumArray_307 first = new NumArray_307(new int[]{1, 2, 3});
        NumArray_307 second = new NumArray_307(new int[]{10, 20, 30});

        first.update(1, 100);
        assertEquals(104, first.sumRange(0, 2));
        assertEquals(60, second.sumRange(0, 2));
        second.update(0, -10);
        assertEquals(40, second.sumRange(0, 2));
        assertEquals(104, first.sumRange(0, 2));
    }

    @Test
    public void updatingEveryPositionMaintainsAllSubranges() {
        int[] expected = {2, 4, 6, 8, 10, 12, 14, 16};
        NumArray_307 numArray = new NumArray_307(expected.clone());

        for (int index = 0; index < expected.length; index++) {
            expected[index] = (index % 2 == 0) ? -index - 1 : index * 3;
            numArray.update(index, expected[index]);
            assertEquals(naiveSum(expected, 0, index), numArray.sumRange(0, index));
            assertEquals(naiveSum(expected, index, expected.length - 1),
                    numArray.sumRange(index, expected.length - 1));
        }
        assertRangesMatch(expected, numArray,
                new int[][]{{0, 7}, {1, 6}, {2, 5}, {3, 4}});
    }

    @Test
    public void exhaustiveRangesOnSmallArrayMatchNaiveOracle() {
        int[] values = {-3, 1, 4, -1, 5, -9};
        NumArray_307 numArray = new NumArray_307(values.clone());

        for (int left = 0; left < values.length; left++) {
            for (int right = left; right < values.length; right++) {
                assertEquals(naiveSum(values, left, right), numArray.sumRange(left, right),
                        "range [" + left + ", " + right + "]");
            }
        }
    }

    @Test
    public void updateThenQueryMixedManualSequenceMatchesOracle() {
        int[] expected = {9, -4, 7, 2, 0, 11, -8};
        NumArray_307 numArray = new NumArray_307(expected.clone());

        numArray.update(3, -20);
        expected[3] = -20;
        assertEquals(naiveSum(expected, 1, 5), numArray.sumRange(1, 5));
        numArray.update(0, 100);
        expected[0] = 100;
        assertEquals(naiveSum(expected, 0, 6), numArray.sumRange(0, 6));
        numArray.update(6, 6);
        expected[6] = 6;
        assertEquals(naiveSum(expected, 4, 6), numArray.sumRange(4, 6));
        assertEquals(naiveSum(expected, 2, 2), numArray.sumRange(2, 2));
    }

    @Test
    public void seededStatefulSequenceWithOneElement() {
        runSeededScenario(307001, 1, 400);
    }

    @Test
    public void seededStatefulSequenceWithOddLength() {
        runSeededScenario(307002, 9, 1200);
    }

    @Test
    public void seededStatefulSequenceWithManySegmentBoundaries() {
        runSeededScenario(307003, 31, 2500);
    }

    @Test
    public void seededStatefulSequenceWithNonPowerOfTwoLength() {
        runSeededScenario(307004, 257, 3500);
    }

    @Test
    public void seededStatefulSequenceUsesFreshOracleAfterUpdates() {
        runSeededScenario(307005, 64, 5000);
    }

    @Test
    public void exactLeetCodeMaximumLengthAndCallBudget() {
        int n = 30_000;
        int[] expected = new int[n];
        for (int i = 0; i < n; i++) {
            expected[i] = (i % 201) - 100;
        }
        NumArray_307 numArray = new NumArray_307(expected.clone());

        assertEquals(naiveSum(expected, 0, n - 1), numArray.sumRange(0, n - 1));
        for (int operation = 0; operation < 29_998; operation++) {
            int index = (operation * 7919) % n;
            if (operation % 3 == 0) {
                int value = (operation % 201) - 100;
                expected[index] = value;
                numArray.update(index, value);
            } else {
                int left = (operation * 37) % n;
                int right = left + ((operation * 101) % (n - left));
                assertEquals(naiveSum(expected, left, right), numArray.sumRange(left, right),
                        "operation " + operation + " range [" + left + ", " + right + "]");
            }
        }
        assertEquals(naiveSum(expected, 0, n - 1), numArray.sumRange(0, n - 1));
    }

    @Test
    public void sparseUpdatesAcrossTwoThousandElements() {
        int n = 2000;
        int[] expected = new int[n];
        for (int i = 0; i < n; i++) {
            expected[i] = i + 1;
        }
        NumArray_307 numArray = new NumArray_307(expected.clone());

        for (int i = 0; i < n; i += 100) {
            expected[i] = 0;
            numArray.update(i, 0);
        }
        assertEquals(naiveSum(expected, 0, n - 1), numArray.sumRange(0, n - 1));
        assertEquals(naiveSum(expected, 1, 30), numArray.sumRange(1, 30));
    }

    @Test
    public void largerValuesRemainCorrectAfterRepeatedFullRangeUpdates() {
        int n = 2049;
        int[] expected = new int[n];
        Arrays.fill(expected, -100);
        NumArray_307 numArray = new NumArray_307(expected.clone());

        for (int round = 0; round < 12; round++) {
            int index = (round * 173) % n;
            int value = (round % 2 == 0) ? 100 : -100;
            expected[index] = value;
            numArray.update(index, value);
            assertEquals(naiveSum(expected, 0, n - 1), numArray.sumRange(0, n - 1));
        }
        assertEquals(naiveSum(expected, 100, 1900), numArray.sumRange(100, 1900));
    }

    private static void runSeededScenario(int seed, int length, int operations) {
        Random random = new Random(seed);
        int[] expected = new int[length];
        for (int i = 0; i < length; i++) {
            expected[i] = random.nextInt(201) - 100;
        }
        NumArray_307 numArray = new NumArray_307(expected.clone());

        for (int operation = 0; operation < operations; operation++) {
            if (random.nextBoolean()) {
                int index = random.nextInt(length);
                int value = random.nextInt(201) - 100;
                expected[index] = value;
                numArray.update(index, value);
            } else {
                int left = random.nextInt(length);
                int right = left + random.nextInt(length - left);
                assertEquals(naiveSum(expected, left, right), numArray.sumRange(left, right),
                        "seed " + seed + ", operation " + operation);
            }
        }
        assertEquals(naiveSum(expected, 0, length - 1), numArray.sumRange(0, length - 1));
    }

    private static void assertRangesMatch(int[] expected, NumArray_307 actual, int[][] ranges) {
        for (int[] range : ranges) {
            assertEquals(naiveSum(expected, range[0], range[1]),
                    actual.sumRange(range[0], range[1]),
                    "range [" + range[0] + ", " + range[1] + "]");
        }
    }

    private static int naiveSum(int[] values, int left, int right) {
        int sum = 0;
        for (int index = left; index <= right; index++) {
            sum += values[index];
        }
        return sum;
    }
}

package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link CanPartitionKSubsets_698}.
 *
 * <p>The official problem constrains {@code 1 <= k <= nums.length <= 16} and
 * {@code 1 <= nums[i] <= 10_000}. The oracle in this class independently
 * tracks reachable bit masks and the sum modulo the required bucket total;
 * it is used for small exhaustive and deterministic randomized cases rather
 * than merely comparing two implementations of the same search.</p>
 */
public class CanPartitionKSubsets_698Test {

    private final CanPartitionKSubsets_698 solution = new CanPartitionKSubsets_698();

    @Test
    public void officialExampleCanBePartitioned() {
        assertTrue(solution.canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
    }

    @Test
    public void officialExampleCannotBePartitioned() {
        assertFalse(solution.canPartitionKSubsets(new int[]{1, 2, 3, 4}, 3));
    }

    @Test
    public void everyElementCanBeItsOwnSubset() {
        assertTrue(solution.canPartitionKSubsets(new int[]{3, 3, 3, 3}, 4));
    }

    @Test
    public void everyElementSubsetRequiresEqualValues() {
        assertFalse(solution.canPartitionKSubsets(new int[]{1, 2, 3, 4}, 4));
    }

    @Test
    public void oneSubsetAlwaysUsesTheWholeArray() {
        assertTrue(solution.canPartitionKSubsets(new int[]{9, 1, 8, 2, 7, 3}, 1));
    }

    @Test
    public void oneSubsetWorksForASingleElement() {
        assertTrue(solution.canPartitionKSubsets(new int[]{10_000}, 1));
    }

    @Test
    public void twoSubsetsCanUseDifferentCardinalities() {
        assertTrue(solution.canPartitionKSubsets(new int[]{1, 5, 11, 5}, 2));
    }

    @Test
    public void twoSubsetsCanBeImpossibleEvenWhenThereAreRepeatedValues() {
        assertFalse(solution.canPartitionKSubsets(new int[]{2, 6, 6, 6}, 2));
    }

    @Test
    public void equalTargetCanRequireBacktrackingAfterAChoice() {
        assertTrue(solution.canPartitionKSubsets(new int[]{1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3}, 4));
    }

    @Test
    public void equalSumDoesNotImplyAValidPartition() {
        assertFalse(solution.canPartitionKSubsets(new int[]{1, 1, 1, 1, 2, 2, 2, 4}, 3));
    }

    @Test
    public void indivisibleTotalIsRejected() {
        assertFalse(solution.canPartitionKSubsets(new int[]{1, 2, 4}, 2));
    }

    @Test
    public void historicalNegativeCasesRemainCovered() {
        assertFalse(solution.canPartitionKSubsets(new int[]{1, 2, 3, 5}, 3));
        assertFalse(solution.canPartitionKSubsets(new int[]{2, 2, 2, 2, 3, 4, 5}, 4));
    }

    @Test
    public void largestElementAboveTargetIsRejected() {
        assertFalse(solution.canPartitionKSubsets(new int[]{10, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 4));
    }

    @Test
    public void duplicateValuesRemainDistinctElements() {
        assertTrue(solution.canPartitionKSubsets(new int[]{2, 2, 2, 2, 3, 3, 3, 3}, 4));
    }

    @Test
    public void duplicateValuesCanMakeACombinationImpossible() {
        assertFalse(solution.canPartitionKSubsets(new int[]{3, 5, 5, 5}, 2));
    }

    @Test
    public void unsortedInputIsAccepted() {
        assertTrue(solution.canPartitionKSubsets(new int[]{6, 1, 4, 3, 2, 2, 2}, 2));
    }

    @Test
    public void nullInputIsRejected() {
        assertFalse(solution.canPartitionKSubsets(null, 2));
    }

    @Test
    public void emptyInputIsRejected() {
        assertFalse(solution.canPartitionKSubsets(new int[]{}, 1));
    }

    @Test
    public void tooManySubsetsAreRejected() {
        assertFalse(solution.canPartitionKSubsets(new int[]{1, 2}, 3));
    }

    @Test
    public void allZeroElementsCanFormNonEmptyZeroSumSubsets() {
        assertTrue(solution.canPartitionKSubsets(new int[]{0, 0, 0, 0}, 4));
    }

    @Test
    public void zeroOnlyInputCanUseFewerThanNSubsets() {
        assertTrue(solution.canPartitionKSubsets(new int[]{0, 0, 0, 0}, 2));
    }

    @Test
    public void aSingleZeroIsValidForOneSubset() {
        assertTrue(solution.canPartitionKSubsets(new int[]{0}, 1));
    }

    @Test
    public void zerosCanBeAssignedToPositiveSubsets() {
        assertTrue(solution.canPartitionKSubsets(new int[]{0, 0, 1, 1, 1}, 3));
    }

    @Test
    public void zeroAndPositiveValuesStillRequireDivisibleTotal() {
        assertFalse(solution.canPartitionKSubsets(new int[]{0, 0, 1, 1}, 3));
    }

    @Test
    public void maximumElementValueAndMaximumLengthAreSupported() {
        assertTrue(solution.canPartitionKSubsets(
                new int[]{10_000, 10_000, 10_000, 10_000, 10_000, 10_000, 10_000, 10_000,
                        10_000, 10_000, 10_000, 10_000, 10_000, 10_000, 10_000, 10_000},
                16));
    }

    @Test
    public void maximumLengthCanBePartitionedIntoPairs() {
        assertTrue(solution.canPartitionKSubsets(
                new int[]{1, 16, 2, 15, 3, 14, 4, 13, 5, 12, 6, 11, 7, 10, 8, 9}, 8));
    }

    @Test
    public void maximumLengthCanBeRejectedWhenOneElementIsTooLarge() {
        assertFalse(solution.canPartitionKSubsets(
                new int[]{10_000, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 5));
    }

    @Test
    public void repeatedCallsDoNotRetainVisitedState() {
        assertTrue(solution.canPartitionKSubsets(new int[]{2, 2, 2, 2}, 2));
        assertFalse(solution.canPartitionKSubsets(new int[]{1, 2, 3, 4}, 3));
        assertTrue(solution.canPartitionKSubsets(new int[]{3, 3, 3}, 3));
    }

    @Test
    public void implementationSortsItsInputForPruning() {
        int[] input = {3, 1, 2, 6, 4, 5};

        assertTrue(solution.canPartitionKSubsets(input, 1));
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, input);
    }

    @Test
    public void exhaustiveSmallPositiveInputsAgreeWithIndependentOracle() {
        for (int length = 1; length <= 5; length++) {
            int[] values = new int[length];
            enumerateAndCheck(values, 0);
        }
    }

    @Test
    public void seededRandomInputsAgreeWithIndependentOracle() {
        Random random = new Random(698_2026L);

        for (int trial = 0; trial < 80; trial++) {
            int length = 1 + random.nextInt(10);
            int[] values = new int[length];
            for (int i = 0; i < length; i++) {
                values[i] = 1 + random.nextInt(20);
            }
            int k = 1 + random.nextInt(length);
            assertMatchesOracle(values, k, "seeded trial " + trial);
        }
    }

    @Test
    public void targetedAdversarialInputsAgreeWithIndependentOracle() {
        int[][] inputs = {
                {1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3},
                {1, 2, 4, 8, 16, 32, 64, 128, 256, 512},
                {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7},
                {1, 1, 1, 1, 2, 2, 2, 2, 4, 4, 4, 4},
                {5, 5, 5, 5, 5, 5, 5, 5, 9, 9, 9, 9, 9, 9, 9, 9}
        };
        int[] subsetCounts = {4, 3, 6, 4, 8};

        for (int i = 0; i < inputs.length; i++) {
            assertMatchesOracle(inputs[i], subsetCounts[i], "adversarial case " + i);
        }
    }

    private void enumerateAndCheck(int[] values, int index) {
        if (index == values.length) {
            for (int k = 1; k <= values.length; k++) {
                assertMatchesOracle(values, k, "exhaustive input " + Arrays.toString(values) + ", k=" + k);
            }
            return;
        }

        for (int value = 1; value <= 3; value++) {
            values[index] = value;
            enumerateAndCheck(values, index + 1);
        }
    }

    private void assertMatchesOracle(int[] original, int k, String context) {
        int[] input = Arrays.copyOf(original, original.length);
        boolean expected = canPartitionBySubsetDp(original, k);
        boolean actual = solution.canPartitionKSubsets(input, k);

        assertTrue(actual == expected, () -> context + ": expected " + expected + " but got " + actual);
    }

    /**
     * Independent bit-mask DP oracle. For each used-element mask, the state
     * is the current partial sum in the next bucket. A remainder of zero
     * means a bucket was completed, so bucket order is not distinguished.
     */
    private boolean canPartitionBySubsetDp(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
            return false;
        }

        int total = Arrays.stream(nums).sum();
        if (total % k != 0) {
            return false;
        }
        int target = total / k;
        if (target == 0) {
            return Arrays.stream(nums).allMatch(value -> value == 0);
        }

        int stateCount = 1 << nums.length;
        int[] remainder = new int[stateCount];
        Arrays.fill(remainder, -1);
        remainder[0] = 0;

        for (int mask = 0; mask < stateCount; mask++) {
            if (remainder[mask] < 0) {
                continue;
            }
            for (int index = 0; index < nums.length; index++) {
                int bit = 1 << index;
                if ((mask & bit) != 0) {
                    continue;
                }
                int nextSum = remainder[mask] + nums[index];
                if (nextSum <= target) {
                    int nextMask = mask | bit;
                    remainder[nextMask] = nextSum % target;
                }
            }
        }
        return remainder[stateCount - 1] == 0;
    }
}

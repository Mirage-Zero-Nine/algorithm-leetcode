package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Contract tests for LeetCode 315, Count of Smaller Numbers After Self.
 *
 * <p>Each ordinary case is checked against an independent quadratic oracle and against both
 * implementations in {@link CountSmaller_315}. The maximum-size case uses an independent Fenwick
 * tree oracle because the intentionally quadratic implementation is not expected to scale to the
 * online judge's limit.
 */
public class CountSmaller_315Test {

    private final CountSmaller_315 solution = new CountSmaller_315();

    @Test
    void officialExample() {
        assertBoth(new int[]{5, 2, 6, 1}, List.of(2, 1, 1, 0));
    }

    @Test
    void singletonHasNoElementAfterIt() {
        assertBoth(new int[]{-1}, List.of(0));
    }

    @Test
    void equalPairDoesNotCountAsSmaller() {
        assertBoth(new int[]{-1, -1}, List.of(0, 0));
    }

    @Test
    void emptyArrayUsesImplementationDefinedEmptyResult() {
        assertBoth(new int[]{}, List.of());
    }

    @Test
    void ascendingArrayHasNoSmallerSuffixValues() {
        assertBoth(new int[]{-5, -2, 0, 3, 9}, List.of(0, 0, 0, 0, 0));
    }

    @Test
    void descendingArrayCountsEveryLaterPosition() {
        assertBoth(new int[]{9, 3, 0, -2, -5}, List.of(4, 3, 2, 1, 0));
    }

    @Test
    void allEqualValuesAreNeverSmaller() {
        assertBoth(new int[]{7, 7, 7, 7, 7}, List.of(0, 0, 0, 0, 0));
    }

    @Test
    void duplicateRunsRemainStrictlySmallerOnly() {
        assertBoth(new int[]{4, 4, 1, 1, 3, 3, 2, 2}, List.of(6, 6, 0, 0, 2, 2, 0, 0));
    }

    @Test
    void negativeAndPositiveValuesAreComparedByNumericOrder() {
        assertBoth(new int[]{3, -1, 2, -1, 0}, List.of(4, 0, 2, 0, 0));
    }

    @Test
    void zeroAndSignTransitionsAreCovered() {
        assertBoth(new int[]{0, -1, 0, 1, -2}, List.of(2, 1, 1, 1, 0));
    }

    @Test
    void integerBoundariesDoNotRequireSubtraction() {
        assertBoth(
                new int[]{Integer.MAX_VALUE, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE},
                List.of(3, 2, 0, 1, 0));
    }

    @Test
    void alternatingExtremeValuesCountAcrossDuplicates() {
        assertBoth(
                new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE},
                List.of(0, 1, 0, 0));
    }

    @Test
    void largerMixedCaseHasIndependentKnownResult() {
        assertBoth(new int[]{8, 1, 7, 2, 6, 3, 5, 4}, List.of(7, 0, 5, 0, 3, 0, 1, 0));
    }

    @Test
    void interleavedValuesExerciseBothTreeBranches() {
        int[] nums = {10, 5, 8, 1, 9, 2, 7, 3, 6, 4};
        assertBoth(nums, referenceQuadratic(nums));
    }

    @Test
    void repeatedMinimumAndMaximumValues() {
        int[] nums = {10_000, -10_000, 10_000, -10_000, 0, 10_000, -10_000};
        assertBoth(nums, referenceQuadratic(nums));
    }

    @Test
    void valuesAtOfficialConstraintBoundaries() {
        int[] nums = {10_000, 9_999, -9_999, -10_000, 10_000, -10_000};
        assertBoth(nums, referenceQuadratic(nums));
    }

    @Test
    void resultIsIndexedByOriginalPosition() {
        int[] nums = {2, 5, 1, 4, 3};
        assertBoth(nums, List.of(1, 3, 0, 1, 0));
    }

    @Test
    void exhaustiveSmallTernaryArraysUseIndependentOracle() {
        int[] alphabet = {-1, 0, 1};
        for (int length = 0; length <= 5; length++) {
            enumerateAndAssert(new int[length], 0, alphabet);
        }
    }

    @Test
    void seededRandomSmallArraysExerciseManyOrderingPatterns() {
        Random random = new Random(315_2026L);
        for (int caseNumber = 0; caseNumber < 120; caseNumber++) {
            int[] nums = new int[1 + random.nextInt(25)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt();
            }
            assertBoth(nums, referenceQuadratic(nums));
        }
    }

    @Test
    void inputArrayIsNotMutated() {
        int[] nums = {5, -2, 5, 0, Integer.MIN_VALUE};
        int[] original = nums.clone();

        solution.countSmaller(nums);
        assertArrayEquals(original, nums);
        solution.brutal(nums);
        assertArrayEquals(original, nums);
    }

    @Test
    void repeatedCallsDoNotLeakStateBetweenInputs() {
        int[] first = {5, 2, 6, 1};
        int[] second = {-3, -1, -2};
        List<Integer> firstExpected = List.of(2, 1, 1, 0);
        List<Integer> secondExpected = List.of(0, 1, 0);

        assertIterableEquals(firstExpected, solution.countSmaller(first));
        assertIterableEquals(secondExpected, solution.countSmaller(second));
        assertIterableEquals(firstExpected, solution.countSmaller(first));
        assertIterableEquals(firstExpected, solution.brutal(first));
        assertIterableEquals(secondExpected, solution.brutal(second));
        assertIterableEquals(firstExpected, solution.brutal(first));
    }

    @Test
    void returnedListsAreFreshAndIndependent() {
        int[] nums = {4, 1, 3, 2};
        List<Integer> optimized = solution.countSmaller(nums);
        List<Integer> quadratic = solution.brutal(nums);
        optimized.set(0, 999);
        quadratic.set(0, 999);

        assertIterableEquals(List.of(3, 0, 1, 0), solution.countSmaller(nums));
        assertIterableEquals(List.of(3, 0, 1, 0), solution.brutal(nums));
    }

    @Test
    void quadraticApproachHandlesAReasonableNonTleLargeCase() {
        int[] nums = new int[400];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = ((i * 97) % 401) - 200;
        }
        assertIterableEquals(referenceQuadratic(nums), solution.brutal(nums));
    }

    @Test
    void optimizedApproachHandlesOfficialMaximumLength() {
        int[] nums = new int[100_000];
        Random random = new Random(315_100_000L);
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(20_001) - 10_000;
        }

        List<Integer> expected = referenceFenwick(nums);
        assertIterableEquals(expected, solution.countSmaller(nums));
        assertEquals(100_000, expected.size());
        assertEquals(0, expected.get(expected.size() - 1));
    }

    private void enumerateAndAssert(int[] nums, int index, int[] alphabet) {
        if (index == nums.length) {
            assertBoth(nums, referenceQuadratic(nums));
            return;
        }
        for (int value : alphabet) {
            nums[index] = value;
            enumerateAndAssert(nums, index + 1, alphabet);
        }
    }

    private void assertBoth(int[] nums, List<Integer> expected) {
        int[] optimizedInput = nums.clone();
        int[] quadraticInput = nums.clone();
        assertIterableEquals(expected, solution.countSmaller(optimizedInput));
        assertIterableEquals(expected, solution.brutal(quadraticInput));
        assertArrayEquals(nums, optimizedInput);
        assertArrayEquals(nums, quadraticInput);
    }

    /** Independent O(n^2) oracle for small and medium arrays. */
    private List<Integer> referenceQuadratic(int[] nums) {
        List<Integer> expected = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    count++;
                }
            }
            expected.add(count);
        }
        return expected;
    }

    /** Independent O(n log n) oracle used only for the official maximum-length case. */
    private List<Integer> referenceFenwick(int[] nums) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int unique = 0;
        for (int value : sorted) {
            if (unique == 0 || sorted[unique - 1] != value) {
                sorted[unique++] = value;
            }
        }

        int[] tree = new int[unique + 1];
        Integer[] expected = new Integer[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int rank = Arrays.binarySearch(sorted, 0, unique, nums[i]) + 1;
            expected[i] = prefixSum(tree, rank - 1);
            for (int cursor = rank; cursor < tree.length; cursor += cursor & -cursor) {
                tree[cursor]++;
            }
        }
        return Arrays.asList(expected);
    }

    private int prefixSum(int[] tree, int index) {
        int sum = 0;
        for (int cursor = index; cursor > 0; cursor -= cursor & -cursor) {
            sum += tree[cursor];
        }
        return sum;
    }
}

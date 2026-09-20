package solutions.cyclicsort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link FindDisappearedNumbers_448}.
 *
 * <p>The LeetCode contract requires every input value to be in {@code [1, n]};
 * the solution marks the input array in place and returns the missing values in
 * increasing index order. Expected values below are derived independently by
 * recording presence in a boolean array before invoking the mutating solution.
 */
public class FindDisappearedNumbers_448Test {

    private final FindDisappearedNumbers_448 solver = new FindDisappearedNumbers_448();

    @Test
    public void testOfficialExample() {
        assertMissingNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}, List.of(5, 6));
    }

    @Test
    public void testOfficialNoMissingExample() {
        assertMissingNumbers(new int[]{1, 2, 3, 4}, List.of());
    }

    @Test
    public void testOfficialSingleMissingExample() {
        assertMissingNumbers(new int[]{1, 1}, List.of(2));
    }

    @Test
    public void testSingletonRange() {
        assertMissingNumbers(new int[]{1}, List.of());
    }

    @Test
    public void testEmptyArrayImplementationBoundary() {
        assertEquals(List.of(), solver.findDisappearedNumbers(new int[0]));
    }

    @Test
    public void testNullInputImplementationBoundary() {
        assertEquals(List.of(), solver.findDisappearedNumbers(null));
    }

    @Test
    public void testSmallestDuplicateRange() {
        assertMissingNumbers(new int[]{2, 2}, List.of(1));
    }

    @Test
    public void testSortedPermutationHasNoMissingValues() {
        assertMissingNumbers(new int[]{1, 2, 3, 4, 5, 6}, List.of());
    }

    @Test
    public void testReversePermutationHasNoMissingValues() {
        assertMissingNumbers(new int[]{6, 5, 4, 3, 2, 1}, List.of());
    }

    @Test
    public void testMissingFirstValue() {
        assertMissingNumbers(new int[]{2, 3, 4, 5, 2}, List.of(1));
    }

    @Test
    public void testMissingLastValue() {
        assertMissingNumbers(new int[]{1, 2, 3, 4, 1}, List.of(5));
    }

    @Test
    public void testMissingInteriorValue() {
        assertMissingNumbers(new int[]{1, 1, 3, 4, 5}, List.of(2));
    }

    @Test
    public void testSeparatedMissingValues() {
        assertMissingNumbers(new int[]{1, 3, 5, 1, 3, 5}, List.of(2, 4, 6));
    }

    @Test
    public void testOneDistinctValueLeavesMaximumMissingSet() {
        assertMissingNumbers(new int[]{4, 4, 4, 4, 4}, List.of(1, 2, 3, 5));
    }

    @Test
    public void testDuplicatesAtBothRangeBoundaries() {
        assertMissingNumbers(new int[]{1, 1, 2, 5, 5, 5}, List.of(3, 4, 6));
    }

    @Test
    public void testInterleavedDuplicatePattern() {
        assertMissingNumbers(new int[]{8, 1, 5, 4, 1, 8, 4, 5}, List.of(2, 3, 6, 7));
    }

    @Test
    public void testResultIsStrictlyAscending() {
        List<Integer> result = solver.findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1});
        for (int i = 1; i < result.size(); i++) {
            assertTrue(result.get(i - 1) < result.get(i));
        }
    }

    @Test
    public void testPresentAndMissingValuesPartitionTheRange() {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        Set<Integer> present = new HashSet<>();
        for (int value : nums) {
            present.add(value);
        }
        List<Integer> missing = solver.findDisappearedNumbers(nums);
        Set<Integer> partition = new HashSet<>(present);
        partition.addAll(missing);
        assertEquals(Set.of(1, 2, 3, 4, 5, 6, 7, 8), partition);
        assertEquals(2, missing.size());
    }

    @Test
    public void testInputIsMarkedInPlaceForPresentAndMissingIndices() {
        int[] nums = {1, 3, 3, 5, 5};
        List<Integer> missing = solver.findDisappearedNumbers(nums);
        assertEquals(List.of(2, 4), missing);
        for (int i = 0; i < nums.length; i++) {
            if (missing.contains(i + 1)) {
                assertTrue(nums[i] > 0, "missing index should remain positive");
            } else {
                assertTrue(nums[i] < 0, "present index should be marked negative");
            }
        }
    }

    @Test
    public void testSameInstanceSupportsRepeatedCalls() {
        assertMissingNumbers(new int[]{2, 2, 3, 1}, List.of(4));
        assertMissingNumbers(new int[]{1, 1, 1, 1}, List.of(2, 3, 4));
        assertMissingNumbers(new int[]{3, 4, 1, 2}, List.of());
    }

    @Test
    public void testReturnedListsAreIndependent() {
        List<Integer> first = solver.findDisappearedNumbers(new int[]{1, 1, 3, 4});
        List<Integer> second = solver.findDisappearedNumbers(new int[]{2, 2, 3, 4});
        first.clear();
        assertEquals(List.of(1), second);
        assertNotSame(first, second);
    }

    @Test
    public void testExhaustiveValidArraysThroughLengthFive() {
        for (int n = 2; n <= 5; n++) {
            int total = (int) Math.pow(n, n);
            for (int code = 0; code < total; code++) {
                int encoded = code;
                int[] nums = new int[n];
                for (int i = 0; i < n; i++) {
                    nums[i] = encoded % n + 1;
                    encoded /= n;
                }
                assertMissingNumbers(nums, expectedMissing(nums));
            }
        }
    }

    @Test
    public void testSeededRandomValidArraysAgainstBooleanOracle() {
        Random random = new Random(448_2026L);
        for (int trial = 0; trial < 500; trial++) {
            int n = 2 + random.nextInt(99);
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = 1 + random.nextInt(n);
            }
            assertMissingNumbers(nums, expectedMissing(nums));
        }
    }

    @Test
    public void testMaximumAllowedLengthWithLowerHalfRepeated() {
        int n = 100_000;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i % (n / 2) + 1;
        }
        List<Integer> expected = new ArrayList<>(n / 2);
        for (int value = n / 2 + 1; value <= n; value++) {
            expected.add(value);
        }
        assertMissingNumbers(nums, expected);
    }

    @Test
    public void testMaximumAllowedLengthPermutationHasNoMissingValues() {
        int n = 100_000;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            // 37 is coprime to 100,000, so this visits every value exactly once.
            nums[i] = (i * 37) % n + 1;
        }
        assertMissingNumbers(nums, List.of());
    }

    @Test
    public void testManyDuplicateRunsAgainstOracle() {
        int[] nums = {
                9, 9, 1, 2, 2, 2, 7, 4, 4, 10,
                6, 6, 6, 3, 8, 8, 5, 5, 1, 10
        };
        assertMissingNumbers(nums, expectedMissing(nums));
    }

    @Test
    public void testFreshSolverProducesSameResult() {
        int[] input = {10, 2, 6, 7, 2, 9, 1, 10, 4, 6};
        List<Integer> expected = expectedMissing(input);
        List<Integer> first = new FindDisappearedNumbers_448().findDisappearedNumbers(input.clone());
        List<Integer> second = new FindDisappearedNumbers_448().findDisappearedNumbers(input.clone());
        assertEquals(new HashSet<>(expected), new HashSet<>(first));
        assertEquals(first, second);
    }

    private void assertMissingNumbers(int[] nums, List<Integer> expected) {
        int[] original = nums.clone();
        List<Integer> actual = solver.findDisappearedNumbers(nums);

        assertEquals(new HashSet<>(expected), new HashSet<>(actual));
        assertEquals(expected.size(), actual.size(), "result must not contain duplicates");
        assertTrue(actual.stream().allMatch(value -> value >= 1 && value <= original.length));
        assertEquals(expectedMissing(original), expected);
    }

    /** Builds the expected set without relying on the solution's in-place marking algorithm. */
    private static List<Integer> expectedMissing(int[] nums) {
        boolean[] seen = new boolean[nums.length + 1];
        for (int value : nums) {
            seen[value] = true;
        }
        List<Integer> missing = new ArrayList<>();
        for (int value = 1; value <= nums.length; value++) {
            if (!seen[value]) {
                missing.add(value);
            }
        }
        return missing;
    }
}

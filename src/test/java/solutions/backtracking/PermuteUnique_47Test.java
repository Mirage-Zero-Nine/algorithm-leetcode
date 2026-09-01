package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PermuteUnique_47Test {
    private final PermuteUnique_47 solution = new PermuteUnique_47();

    @Test
    void testWithDuplicates() {
        assertPermutationResult(new int[]{1, 1, 2});
    }

    @Test
    void testAllSame() {
        assertPermutationResult(new int[]{1, 1, 1});
    }

    @Test
    void testNoDuplicates() {
        assertPermutationResult(new int[]{1, 2, 3});
    }

    @Test
    void testTwoDuplicates() {
        assertPermutationResult(new int[]{2, 2, 1, 1});
    }

    @Test
    void testSingleElement() {
        assertPermutationResult(new int[]{1});
    }

    @Test
    void testTwoElements() {
        assertPermutationResult(new int[]{1, 2});
    }

    @Test
    void testNegativeNumbers() {
        assertPermutationResult(new int[]{-1, -1, 2});
    }

    @Test
    void testAllUnique() {
        assertPermutationResult(new int[]{1, 2, 3, 4});
    }

    @Test
    void testNoDuplicatePermutations() {
        assertPermutationResult(new int[]{1, 1, 2, 2});
    }

    @Test
    void testCorrectPermutationLength() {
        assertPermutationResult(new int[]{1, 2, 2, 3});
    }

    @Test
    void testGiantCase() {
        // 8 elements with duplicates: 8!/(2!*2!*2!*2!) = 2520.
        assertPermutationResult(new int[]{1, 1, 2, 2, 3, 3, 4, 4});
    }

    // --- NEW TESTS ---

    @Test
    void testTwoSameElements() {
        // [1,1] -> only [[1,1]].
        assertPermutationResult(new int[]{1, 1});
    }

    @Test
    void testTwoDiffElementsExactContent() {
        // [1,2] -> [[1,2],[2,1]]
        List<List<Integer>> result = solution.permuteUnique(new int[]{1, 2});
        Set<List<Integer>> expected = Set.of(List.of(1, 2), List.of(2, 1));
        assertEquals(expected, new HashSet<>(result));
    }

    @Test
    void testLeetCodeExampleExactContent() {
        // [1,1,2] -> [[1,1,2],[1,2,1],[2,1,1]]
        List<List<Integer>> result = solution.permuteUnique(new int[]{1, 1, 2});
        Set<List<Integer>> expected = Set.of(List.of(1, 1, 2), List.of(1, 2, 1), List.of(2, 1, 1));
        assertEquals(expected, new HashSet<>(result));
    }

    @Test
    void testFiveAllDistinct() {
        // 5! = 120.
        assertPermutationResult(new int[]{1, 2, 3, 4, 5});
    }

    @Test
    void testFiveAllSame() {
        // [2,2,2,2,2] -> only one result.
        assertPermutationResult(new int[]{2, 2, 2, 2, 2});
    }

    @Test
    void testPropertyCountFormula() {
        // [1,1,2,2,3] -> 5! / (2! * 2! * 1!) = 30.
        assertPermutationResult(new int[]{1, 1, 2, 2, 3});
    }

    @Test
    void testPropertyEveryResultIsPermutation() {
        // Every result must be a permutation of the input's multiset.
        assertPermutationResult(new int[]{1, 1, 2, 3});
    }

    @Test
    void testPropertyNoDuplicateResults() {
        // [1,1,1,2,2] -> 5! / (3! * 2!) = 10.
        assertPermutationResult(new int[]{1, 1, 1, 2, 2});
    }

    @Test
    void testNegativeAndPositiveMixed() {
        // [-1, -1, 0, 1] -> 4! / 2! = 12.
        assertPermutationResult(new int[]{-1, -1, 0, 1});
    }

    @Test
    void testThreePairsOfDuplicates() {
        // [1,1,2,2,3,3] -> 6! / (2! * 2! * 2!) = 90.
        assertPermutationResult(new int[]{1, 1, 2, 2, 3, 3});
    }

    @Test
    void testExhaustiveArraysFromSmallDomainThroughMaximumLength() {
        // Every generated input satisfies LeetCode 47's constraints. The
        // three-value domain covers every duplicate pattern and all lengths
        // through the maximum n = 8 without enumerating the entire 21-value
        // input space.
        int[] domain = {-10, 0, 10};
        for (int length = 1; length <= 8; length++) {
            assertAllArraysOfLength(domain, new int[length], 0);
        }
    }

    @Test
    void testExhaustiveShortArraysAcrossTheAllowedValueRange() {
        // Full-range exhaustion is practical for lengths 1 through 3:
        // 21^1 + 21^2 + 21^3 = 9,723 inputs.
        int[] domain = IntStream.rangeClosed(-10, 10).toArray();
        for (int length = 1; length <= 3; length++) {
            assertAllArraysOfLength(domain, new int[length], 0);
        }
    }

    @Test
    void testMaximumLengthWithAllDistinctBoundaryValues() {
        // Validate a complete 8! result set, including both allowed boundaries.
        assertPermutationResult(new int[]{-10, -7, -3, 0, 4, 8, 9, 10});
    }

    @Test
    void testNullInput() {
        assertTrue(solution.permuteUnique(null).isEmpty());
    }

    @Test
    void testEmptyInput() {
        assertTrue(solution.permuteUnique(new int[]{}).isEmpty());
    }

    private void assertAllArraysOfLength(int[] domain, int[] input, int index) {
        if (index == input.length) {
            Set<List<Integer>> expected = independentUniquePermutations(input);
            Set<List<Integer>> actual = new HashSet<>(solution.permuteUnique(input.clone()));
            assertEquals(expected, actual, "Unexpected permutations for " + Arrays.toString(input));
            assertEquals(expected.size(), actual.size(),
                    "Duplicate permutations for " + Arrays.toString(input));
            return;
        }

        for (int value : domain) {
            input[index] = value;
            assertAllArraysOfLength(domain, input, index + 1);
        }
    }

    private void assertPermutationResult(int[] input) {
        int[] original = input.clone();
        List<List<Integer>> actual = solution.permuteUnique(input);
        Set<List<Integer>> expected = independentUniquePermutations(original);
        Set<List<Integer>> unique = new HashSet<>(actual);

        assertEquals(expected, unique,
                "The complete permutation set must be returned for " + Arrays.toString(original));
        assertEquals(expected.size(), actual.size(),
                "The result must not contain duplicate permutations for " + Arrays.toString(original));
    }

    /**
     * Independent oracle that starts with sorted values and enumerates each
     * lexicographical permutation. It handles duplicates without generating
     * duplicate candidates and does not reuse the solution's backtracking rule.
     */
    private Set<List<Integer>> independentUniquePermutations(int[] input) {
        List<Integer> permutation = new ArrayList<>();
        for (int value : input) {
            permutation.add(value);
        }
        Collections.sort(permutation);

        Set<List<Integer>> permutations = new HashSet<>();
        do {
            permutations.add(new ArrayList<>(permutation));
        } while (nextPermutation(permutation));
        return permutations;
    }

    private boolean nextPermutation(List<Integer> permutation) {
        int i = permutation.size() - 2;
        while (i >= 0 && permutation.get(i) >= permutation.get(i + 1)) {
            i--;
        }
        if (i < 0) {
            return false;
        }

        int j = permutation.size() - 1;
        while (permutation.get(j) <= permutation.get(i)) {
            j--;
        }
        Collections.swap(permutation, i, j);
        Collections.reverse(permutation.subList(i + 1, permutation.size()));
        return true;
    }
}

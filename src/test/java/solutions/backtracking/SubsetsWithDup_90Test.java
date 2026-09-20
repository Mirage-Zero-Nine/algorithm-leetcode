package solutions.backtracking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubsetsWithDup_90Test {
    private final SubsetsWithDup_90 solution = new SubsetsWithDup_90();

    @Test
    void testWithDuplicates() {
        List<List<Integer>> result = solution.subsetsWithDup(new int[]{1, 2, 2});
        assertEquals(6, result.size());
    }

    @Test
    void testAllSame() {
        List<List<Integer>> result = solution.subsetsWithDup(new int[]{1, 1, 1});
        assertEquals(4, result.size());
    }

    @Test
    void testNoDuplicates() {
        List<List<Integer>> result = solution.subsetsWithDup(new int[]{1, 2, 3});
        assertEquals(8, result.size());
    }

    @Test
    void testSingleElement() {
        List<List<Integer>> result = solution.subsetsWithDup(new int[]{0});
        assertEquals(Set.of(List.of(), List.of(0)), canonicalize(result));
        assertEquals(2, result.size());
    }

    @Test
    void testSingletonBoundaryValues() {
        assertEquals(Set.of(List.of(), List.of(-10)), canonicalize(solution.subsetsWithDup(new int[]{-10})));
        assertEquals(Set.of(List.of(), List.of(10)), canonicalize(solution.subsetsWithDup(new int[]{10})));
    }

    @Test
    void testMultipleDuplicates() {
        List<List<Integer>> result = solution.subsetsWithDup(new int[]{4, 4, 4, 1, 4});
        assertTrue(result.size() >= 8);
    }

    @Test
    void testNullInput() {
        List<List<Integer>> result = solution.subsetsWithDup(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testEmptyArray() {
        List<List<Integer>> result = solution.subsetsWithDup(new int[]{});
        assertTrue(result.isEmpty());
    }

    @Test
    void testTwoDistinct() {
        List<List<Integer>> result = solution.subsetsWithDup(new int[]{1, 2});
        assertEquals(4, result.size());
    }

    @Test
    void testTwoDuplicates() {
        List<List<Integer>> result = solution.subsetsWithDup(new int[]{2, 2});
        assertEquals(3, result.size());
    }

    @Test
    void testNegativeNumbers() {
        List<List<Integer>> result = solution.subsetsWithDup(new int[]{-1, -1, 2});
        assertEquals(6, result.size());
    }

    @Test
    void testBacktrackingMethod() {
        List<List<Integer>> result = solution.backtracking(new int[]{1, 2, 2});
        assertEquals(6, result.size());
    }

    @Test
    void testBacktrackingEmptyInputUsesItsImplementationDefinedBaseCase() {
        assertEquals(Set.of(List.of()), canonicalize(solution.backtracking(new int[]{})));
    }

    @Test
    void testOfficialMinimumAndMaximumValuesTogether() {
        int[] input = {-10, 10, -10, 0, 10};
        assertBothImplementationsMatchOracle(input);
        assertEquals(18, oracle(input).size());
    }

    @Test
    void testJavaIntegerBoundariesAreHandledByTheImplementation() {
        int[] input = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0, Integer.MIN_VALUE};
        assertBothImplementationsMatchOracle(input);
    }

    @Test
    void testMaximumOfficialLengthWithAllDistinctValues() {
        int[] input = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4};
        assertBothImplementationsMatchOracle(input);
        assertEquals(1 << input.length, solution.subsetsWithDup(input.clone()).size());
    }

    @Test
    void testMultiplicityProductDeterminesCardinality() {
        int[][] inputs = {
                {-2, -2, -2, 2, 2, 2},
                {-3, -3, -2, -1, 0, 1},
                {0, 0, 0, 0, 1, 1, 2},
                {-10, -10, -9, -9, -8, -8, -7, -7, -6, -6},
                {-1, 0, 1, -1, 0, 1}
        };
        for (int[] input : inputs) {
            int expectedCount = multiplicityProduct(input);
            assertEquals(expectedCount, solution.subsetsWithDup(input.clone()).size());
            assertEquals(expectedCount, solution.backtracking(input.clone()).size());
        }
    }

    @Test
    void testBothImplementationsSortTheCallerInputAsDocumentedByTheirCode() {
        int[] iterativeInput = {3, -1, 2, -1, 0};
        solution.subsetsWithDup(iterativeInput);
        assertTrue(Arrays.equals(new int[]{-1, -1, 0, 2, 3}, iterativeInput));

        int[] backtrackingInput = {3, -1, 2, -1, 0};
        solution.backtracking(backtrackingInput);
        assertTrue(Arrays.equals(new int[]{-1, -1, 0, 2, 3}, backtrackingInput));
    }

    @Test
    void testReturnedRowsAreIndependentAndEachCallStartsFresh() {
        for (boolean useBacktracking : new boolean[]{false, true}) {
            List<List<Integer>> first = invoke(new int[]{1, 1, 2}, useBacktracking);
            List<List<Integer>> snapshot = deepCopy(first);
            assertNotNull(first);
            first.get(0).add(999);
            assertEquals(snapshot.size(), first.size());
            assertEquals(snapshot.subList(1, snapshot.size()), first.subList(1, first.size()));

            List<List<Integer>> second = invoke(new int[]{1, 1, 2}, useBacktracking);
            assertEquals(canonicalize(snapshot), canonicalize(second));
            assertEquals(snapshot.size(), second.size());
            assertEquals(second.size(), new HashSet<>(second).size());
            assertFalse(second.stream().anyMatch(row -> row.contains(999)));
        }
    }

    @Test
    void testRepeatedCallsWithDifferentInputsDoNotLeakState() {
        for (boolean useBacktracking : new boolean[]{false, true}) {
            assertEquals(oracle(new int[]{1, 2, 2}),
                    canonicalize(invoke(new int[]{1, 2, 2}, useBacktracking)));
            assertEquals(oracle(new int[]{-1, -1, 0}),
                    canonicalize(invoke(new int[]{-1, -1, 0}, useBacktracking)));
            assertEquals(oracle(new int[]{3}),
                    canonicalize(invoke(new int[]{3}, useBacktracking)));
        }
    }

    @Test
    void testGiantCase() {
        // 10 elements with some duplicates: [1,1,2,2,3,3,4,4,5,5]
        int[] nums = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5};
        List<List<Integer>> result = solution.subsetsWithDup(nums);
        // each of 5 distinct values appears 2 times -> (2+1)^5 = 243 subsets
        assertEquals(243, result.size());
    }

    /**
     * Exercises both implementations against an independent powerset oracle.  The oracle
     * enumerates index subsets, then canonicalizes values, so it does not share the
     * duplicate-skipping logic under test.
     */
    @ParameterizedTest(name = "case {index}: {0}")
    @MethodSource("validInputs")
    void bothImplementationsReturnExactlyTheUniquePowerSet(int[] input) {
        Set<List<Integer>> expected = oracle(input);

        assertCorrectForFreshInput(expected, input, false);
        assertCorrectForFreshInput(expected, input, true);
    }

    private void assertCorrectForFreshInput(Set<List<Integer>> expected, int[] input,
                                            boolean useBacktracking) {
        List<List<Integer>> actual = invoke(input, useBacktracking);
        Set<List<Integer>> canonical = canonicalize(actual);

        assertEquals(expected, canonical, "wrong subsets for " + Arrays.toString(input));
        assertEquals(expected.size(), actual.size(), "duplicate raw subsets for " + Arrays.toString(input));
        assertTrue(canonical.contains(List.of()), "the power set must contain the empty subset");
        assertEquals(actual.size(), new HashSet<>(actual).size(), "raw output contains duplicate lists");
    }

    private void assertBothImplementationsMatchOracle(int[] input) {
        Set<List<Integer>> expected = oracle(input);
        assertCorrectForFreshInput(expected, input, false);
        assertCorrectForFreshInput(expected, input, true);
    }

    private List<List<Integer>> invoke(int[] input, boolean useBacktracking) {
        int[] freshInput = input.clone();
        return useBacktracking
                ? solution.backtracking(freshInput)
                : solution.subsetsWithDup(freshInput);
    }

    private static List<List<Integer>> deepCopy(List<List<Integer>> rows) {
        List<List<Integer>> copy = new ArrayList<>();
        for (List<Integer> row : rows) {
            copy.add(new ArrayList<>(row));
        }
        return copy;
    }

    private static int multiplicityProduct(int[] input) {
        int[] sorted = input.clone();
        Arrays.sort(sorted);
        int product = 1;
        for (int i = 0; i < sorted.length; ) {
            int j = i + 1;
            while (j < sorted.length && sorted[j] == sorted[i]) {
                j++;
            }
            product *= j - i + 1;
            i = j;
        }
        return product;
    }

    private static Set<List<Integer>> oracle(int[] input) {
        Set<List<Integer>> expected = new HashSet<>();
        for (int mask = 0; mask < (1 << input.length); mask++) {
            List<Integer> subset = new ArrayList<>();
            for (int bit = 0; bit < input.length; bit++) {
                if ((mask & (1 << bit)) != 0) {
                    subset.add(input[bit]);
                }
            }
            subset.sort(Integer::compareTo);
            expected.add(subset);
        }
        return expected;
    }

    private static Set<List<Integer>> canonicalize(List<List<Integer>> subsets) {
        Set<List<Integer>> canonical = new HashSet<>();
        for (List<Integer> subset : subsets) {
            List<Integer> copy = new ArrayList<>(subset);
            copy.sort(Integer::compareTo);
            canonical.add(copy);
        }
        return canonical;
    }

    private static Stream<Arguments> validInputs() {
        return Stream.of(
                Arguments.of(new int[]{0}),
                Arguments.of(new int[]{-10}),
                Arguments.of(new int[]{10}),
                Arguments.of(new int[]{1, 2}),
                Arguments.of(new int[]{2, 1}),
                Arguments.of(new int[]{1, 2, 2}),
                Arguments.of(new int[]{2, 2, 1}),
                Arguments.of(new int[]{-1, -1, 2}),
                Arguments.of(new int[]{-10, 0, 10}),
                Arguments.of(new int[]{-10, -10, 10, 10}),
                Arguments.of(new int[]{0, 0, 0}),
                Arguments.of(new int[]{-2, -1, -2, 0}),
                Arguments.of(new int[]{3, 1, 3, 2, 1}),
                Arguments.of(new int[]{-3, -3, -2, -1, 0, 1}),
                Arguments.of(new int[]{-10, -9, -8, -7, -6, -5, -4, -3, -2, -1}),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}),
                Arguments.of(new int[]{1, 1, 1, 1}),
                Arguments.of(new int[]{-10, -10, -10, -10, -10, -10, -10, -10, -10, -10}),
                Arguments.of(new int[]{-10, -10, -9, -9, -8, -8, -7, -7, -6, -6}),
                Arguments.of(new int[]{5, 5, 4, 4, 3, 3, 2, 2, 1, 1}),
                Arguments.of(new int[]{-1, 0, 1, -1, 0, 1}),
                Arguments.of(new int[]{-10, 10, -10, 10, 0, 0}),
                Arguments.of(new int[]{-2, -2, -2, 2, 2, 2}),
                Arguments.of(new int[]{-10, -5, 0, 5, 10, -10, 0, 10})
        );
    }
}

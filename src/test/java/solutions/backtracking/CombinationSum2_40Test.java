package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CombinationSum2_40Test {
    private final CombinationSum2_40 solution = new CombinationSum2_40();

    @Test
    void exampleOneHasExactlyTheFourUniqueCombinations() {
        assertExact(new int[]{10, 1, 2, 7, 6, 1, 5}, 8,
                new int[][]{{1, 1, 6}, {1, 2, 5}, {1, 7}, {2, 6}});
    }

    @Test
    void exampleTwoUsesOnlyTwoOfThreeTwos() {
        assertExact(new int[]{2, 5, 2, 1, 2}, 5,
                new int[][]{{1, 2, 2}, {5}});
    }

    @Test
    void singleCandidateCanBeTheWholeCombination() {
        assertExact(new int[]{1}, 1, new int[][]{{1}});
    }

    @Test
    void singleCandidateCannotBeReused() {
        assertExact(new int[]{1}, 2, new int[][]{});
    }

    @Test
    void noCandidateCanReachTarget() {
        assertExact(new int[]{2}, 1, new int[][]{});
    }

    @Test
    void emptyArrayHasNoCombination() {
        assertExact(new int[]{}, 5, new int[][]{});
    }

    @Test
    void targetZeroIsTheDocumentedImplementationEdge() {
        // LeetCode's target is positive; this implementation intentionally returns no empty combination.
        assertExact(new int[]{1, 2}, 0, new int[][]{});
    }

    @Test
    void minimumCandidateGreaterThanTargetPrunesImmediately() {
        assertExact(new int[]{5, 6, 7}, 3, new int[][]{});
    }

    @Test
    void allEqualCandidatesProduceOneCombinationForEachAllowedCount() {
        assertExact(new int[]{1, 1, 1}, 2, new int[][]{{1, 1}});
    }

    @Test
    void tooFewEqualCandidatesProduceNoCombination() {
        assertExact(new int[]{4, 4}, 12, new int[][]{});
    }

    @Test
    void allElementsMayBeUsed() {
        assertExact(new int[]{1, 2, 3}, 6, new int[][]{{1, 2, 3}});
    }

    @Test
    void duplicateValuesWithSeveralCountsRemainUnique() {
        assertExact(new int[]{1, 1, 1, 2, 2, 3}, 4,
                new int[][]{{1, 1, 2}, {1, 3}, {2, 2}});
    }

    @Test
    void repeatedValueCannotExceedItsMultiplicity() {
        assertExact(new int[]{2, 2, 3}, 6, new int[][]{});
    }

    @Test
    void unsortedInputHasSameCombinationsAsSortedInput() {
        assertExact(new int[]{9, 1, 4, 2, 8, 6, 3}, 10,
                new int[][]{{1, 2, 3, 4}, {1, 3, 6}, {1, 9}, {2, 8}, {4, 6}});
    }

    @Test
    void permutationsDoNotChangeTheSetOfResults() {
        int[] first = {1, 2, 2, 3, 4};
        int[] second = {4, 2, 1, 3, 2};
        Set<String> expected = subsetOracle(first, 6);
        assertEquals(expected, resultSet(first, 6));
        assertEquals(subsetOracle(second, 6), resultSet(second, 6));
        assertEquals(resultSet(first, 6), resultSet(second, 6));
    }

    @Test
    void candidatesLargerThanTargetAreIgnored() {
        assertExact(new int[]{1, 20, 2, 30, 3}, 6,
                new int[][]{{1, 2, 3}});
    }

    @Test
    void exactTargetCandidateAndAlternativePairBothAppear() {
        assertExact(new int[]{6, 1, 2, 5, 4}, 6,
                new int[][]{{1, 5}, {2, 4}, {6}});
    }

    @Test
    void multipleDuplicateCombinationsAreNotReturnedTwice() {
        Set<String> results = resultSet(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        assertEquals(4, results.size());
    }

    @Test
    void partialMatchCanLeadToAValidSolution() {
        assertExact(new int[]{1, 1, 3, 5}, 7, new int[][]{{1, 1, 5}});
    }

    @Test
    void targetOneWithSeveralCandidatesFindsOnlyOne() {
        assertExact(new int[]{1, 1, 2, 3}, 1, new int[][]{{1}});
    }

    @Test
    void candidateAtTargetCanBeUsedAlongsideSmallerValues() {
        assertExact(new int[]{50, 1, 29, 30}, 30,
                new int[][]{{1, 29}, {30}});
    }

    @Test
    void maximumCandidateValueCanBeAnExactSolution() {
        assertExact(new int[]{50, 1, 2, 49}, 50,
                new int[][]{{1, 49}, {50}});
    }

    @Test
    void maximumTargetWithOnlyLargeCandidatesHasNoSolution() {
        assertExact(new int[]{31, 32, 40, 50}, 30, new int[][]{});
    }

    @Test
    void negativeTargetReturnsNoCombinationOutsideLeetCodeDomain() {
        // The problem requires a positive target; the implementation's lower-bound guard
        // also defines the behavior for this out-of-contract input.
        assertExact(new int[]{1, 2, 3}, -1, new int[][]{});
    }

    @Test
    void exactTargetCanRequireEveryAvailableDuplicate() {
        assertExact(new int[]{2, 2, 2, 3, 7}, 9,
                new int[][]{{2, 2, 2, 3}, {2, 7}});
    }

    @Test
    void duplicateValueCannotBeChosenMoreOftenThanItOccurs() {
        assertExact(new int[]{1, 1, 4}, 3, new int[][]{});
    }

    @Test
    void maximumTargetWithManyCandidatesHasExactResults() {
        int[] candidates = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertEquals(subsetOracle(candidates, 30), resultSet(candidates, 30));
    }

    @Test
    void maximumSizeDuplicateInputRemainsFastAndUnique() {
        int[] candidates = new int[100];
        Arrays.fill(candidates, 1);
        assertExact(candidates, 30, new int[][]{ones(30)});
    }

    @Test
    void maximumSizeInputWithMaximumValuesStillPrunesLargeBranches() {
        int[] candidates = new int[100];
        Arrays.fill(candidates, 0, 30, 1);
        Arrays.fill(candidates, 30, candidates.length, 50);
        assertExact(candidates, 30, new int[][]{ones(30)});
    }

    @Test
    void sortingInputDoesNotChangeItsCombinationSet() {
        int[] candidates = {8, 1, 7, 2, 6, 3, 5, 4};
        assertEquals(subsetOracle(candidates, 9), resultSet(candidates, 9));
    }

    @Test
    void implementationSortsTheCallerArrayBeforeBacktracking() {
        int[] candidates = {4, 1, 3, 2};
        solution.combinationSum2(candidates, 5);
        assertArrayEquals(new int[]{1, 2, 3, 4}, candidates);
    }

    @Test
    void repeatedCallsOnOneSolutionInstanceAreIndependent() {
        assertExact(new int[]{3, 1, 2}, 3, new int[][]{{1, 2}, {3}});
        assertExact(new int[]{5, 5, 1}, 5, new int[][]{{5}});
        assertExact(new int[]{2, 2, 2}, 4, new int[][]{{2, 2}});
    }

    @Test
    void returnedResultsAreFreshForEachInvocation() {
        List<List<Integer>> first = solution.combinationSum2(new int[]{1, 2, 3}, 3);
        assertEquals(2, first.size());
        first.get(0).clear();
        first.clear();

        assertExact(new int[]{1, 2, 3}, 3, new int[][]{{1, 2}, {3}});
    }

    @Test
    void preservedGiantInputHasIndependentExactExpectedSet() {
        int[] candidates = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 5};
        assertEquals(subsetOracle(candidates, 10), resultSet(candidates, 10));
    }

    @Test
    void independentSubsetOracleChecksSmallMixedInput() {
        int[] candidates = {1, 1, 2, 3, 4, 6};
        assertEquals(subsetOracle(candidates, 7), resultSet(candidates, 7));
    }

    @Test
    void independentSubsetOracleChecksDuplicateHeavyInput() {
        int[] candidates = {1, 1, 2, 2, 2, 4};
        assertEquals(subsetOracle(candidates, 5), resultSet(candidates, 5));
    }

    @Test
    void exhaustiveOracleChecksSeveralSmallArraysAndTargets() {
        int[][] inputs = {
                {1, 2, 3, 4, 4, 5},
                {1, 1, 2, 3, 3, 4},
                {2, 2, 2, 5, 6, 7},
                {1, 3, 3, 3, 4, 6},
                {2, 4, 5, 5, 6, 8},
                {1, 1, 1, 2, 4, 7},
                {3, 3, 4, 5, 7, 9},
                {1, 2, 2, 4, 6, 6}
        };
        for (int[] input : inputs) {
            for (int target = 1; target <= 12; target++) {
                assertEquals(subsetOracle(input, target), resultSet(input, target));
            }
        }
    }

    private void assertExact(int[] candidates, int target, int[][] expected) {
        Set<String> expectedSet = new HashSet<>();
        for (int[] combination : expected) {
            expectedSet.add(key(combination));
        }
        Set<String> actual = resultSet(candidates, target);
        assertEquals(expectedSet, actual);
    }

    private Set<String> resultSet(int[] candidates, int target) {
        List<List<Integer>> result = solution.combinationSum2(candidates.clone(), target);
        Set<String> canonical = new HashSet<>();
        for (List<Integer> combination : result) {
            int[] values = combination.stream().mapToInt(Integer::intValue).toArray();
            Arrays.sort(values);
            canonical.add(key(values));
        }
        assertEquals(result.size(), canonical.size(), "duplicate combination returned");
        return canonical;
    }

    private Set<String> subsetOracle(int[] candidates, int target) {
        Set<String> expected = new HashSet<>();
        for (int mask = 0; mask < (1 << candidates.length); mask++) {
            int sum = 0;
            int[] chosen = new int[Integer.bitCount(mask)];
            int position = 0;
            for (int i = 0; i < candidates.length; i++) {
                if ((mask & (1 << i)) != 0) {
                    sum += candidates[i];
                    chosen[position++] = candidates[i];
                }
            }
            if (sum == target) {
                Arrays.sort(chosen);
                expected.add(key(chosen));
            }
        }
        return expected;
    }

    private String key(int[] values) {
        return Arrays.toString(values);
    }

    private int[] ones(int length) {
        int[] values = new int[length];
        Arrays.fill(values, 1);
        return values;
    }
}

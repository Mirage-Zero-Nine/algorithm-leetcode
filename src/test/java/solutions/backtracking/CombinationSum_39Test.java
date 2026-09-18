package solutions.backtracking;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CombinationSum_39Test {
    private final CombinationSum_39 solution = new CombinationSum_39();

    private Set<String> canonicalize(List<List<Integer>> combinations) {
        return combinations.stream()
                .map(combo -> combo.stream().map(String::valueOf).collect(Collectors.joining(",")))
                .collect(Collectors.toSet());
    }

    @Test
    void testBasic() {
        List<List<Integer>> result = solution.combinationSum(new int[]{2, 3, 6, 7}, 7);
        assertEquals(2, result.size());
    }

    @Test
    void testMultipleSolutions() {
        List<List<Integer>> result = solution.combinationSum(new int[]{2, 3, 5}, 8);
        assertEquals(Set.of("2,2,2,2", "2,3,3", "3,5"), canonicalize(result));
    }

    @Test
    void testNoSolution() {
        List<List<Integer>> result = solution.combinationSum(new int[]{2}, 1);
        assertEquals(0, result.size());
    }

    @Test
    void testSingleElement() {
        List<List<Integer>> result = solution.combinationSum(new int[]{1}, 2);
        assertEquals(1, result.size());
    }

    @Test
    void testLargeTarget() {
        List<List<Integer>> result = solution.combinationSum(new int[]{2, 3, 5}, 8);
        assertEquals(3, result.size());
    }

    @Test
    void testEmptyCandidates() {
        List<List<Integer>> result = solution.combinationSum(new int[]{}, 7);
        assertEquals(0, result.size());
    }

    @Test
    void testTargetEqualsCandidate() {
        List<List<Integer>> result = solution.combinationSum(new int[]{7}, 7);
        assertEquals(1, result.size());
    }

    @Test
    void testTargetSmallerThanAllCandidates() {
        List<List<Integer>> result = solution.combinationSum(new int[]{5, 6, 7}, 3);
        assertEquals(0, result.size());
    }

    @Test
    void testSingleCandidateRepeated() {
        List<List<Integer>> result = solution.combinationSum(new int[]{3}, 9);
        assertEquals(1, result.size());
    }

    @Test
    void testGiantCase() {
        // many candidates, moderate target
        List<List<Integer>> result = solution.combinationSum(new int[]{2, 3, 5, 7, 11, 13}, 20);
        assertTrue(result.size() > 5);
    }

    @Test
    void testTargetZeroReturnsEmptyCombo() {
        List<List<Integer>> result = solution.combinationSum(new int[]{2, 3, 5}, 0);
        assertEquals(Set.of(List.of()), new HashSet<>(result));
    }

    @Test
    void testSingleCandidateDividesTargetEvenly() {
        List<List<Integer>> result = solution.combinationSum(new int[]{4}, 12);
        assertEquals(Set.of(List.of(4, 4, 4)), toSetOfSortedLists(result));
    }

    @Test
    void testAllCandidatesLargerThanTarget() {
        List<List<Integer>> result = solution.combinationSum(new int[]{10, 20, 30}, 5);
        assertEquals(Set.of(), toSetOfSortedLists(result));
    }

    @Test
    void testTargetEqualsOneCandidateExactResult() {
        List<List<Integer>> result = solution.combinationSum(new int[]{3, 5, 7}, 5);
        assertEquals(Set.of(List.of(5)), toSetOfSortedLists(result));
    }

    @Test
    void testAllOnesTargetN() {
        List<List<Integer>> result = solution.combinationSum(new int[]{1}, 5);
        assertEquals(Set.of(List.of(1, 1, 1, 1, 1)), toSetOfSortedLists(result));
    }

    @Test
    void testLeetCodeExampleExactCombinations() {
        List<List<Integer>> result = solution.combinationSum(new int[]{2, 3, 6, 7}, 7);
        assertEquals(Set.of(List.of(2, 2, 3), List.of(7)), toSetOfSortedLists(result));
    }

    @Test
    void testLargeTargetHasManyCombinations() {
        List<List<Integer>> result = solution.combinationSum(new int[]{1, 2, 3}, 15);
        assertTrue(result.size() > 20, "Large target with small candidates should produce many combos");
    }

    @Test
    void testPropertyEveryCombinationSumsToTarget() {
        int target = 12;
        List<List<Integer>> result = solution.combinationSum(new int[]{2, 3, 5, 7}, target);
        for (List<Integer> combo : result) {
            assertEquals(target, combo.stream().mapToInt(Integer::intValue).sum());
        }
    }

    @Test
    void testPropertyCombosUseOnlyCandidatesAndAreUniqueAndNonDecreasing() {
        int[] candidates = {2, 3, 5, 7};
        Set<Integer> candidateSet = Set.of(2, 3, 5, 7);
        List<List<Integer>> result = solution.combinationSum(candidates, 12);

        Set<List<Integer>> uniqueCombos = new HashSet<>();
        for (List<Integer> combo : result) {
            // uses only candidates
            assertTrue(candidateSet.containsAll(combo));
            // non-decreasing
            for (int i = 1; i < combo.size(); i++) {
                assertTrue(combo.get(i) >= combo.get(i - 1));
            }
            // unique when sorted
            List<Integer> sorted = new ArrayList<>(combo);
            Collections.sort(sorted);
            assertTrue(uniqueCombos.add(sorted), "Duplicate combo found: " + combo);
        }
    }

    @Test
    void testUnsortedCandidatesStillProduceCanonicalCombinations() {
        List<List<Integer>> result = solution.combinationSum(new int[]{7, 3, 2, 6}, 7);
        assertEquals(Set.of(List.of(2, 2, 3), List.of(7)), toSetOfSortedLists(result));
    }

    @Test
    void testNoSolutionWhenTargetIsNotRepresentableByCandidateGcd() {
        List<List<Integer>> result = solution.combinationSum(new int[]{6, 10, 14}, 9);
        assertEquals(Set.of(), toSetOfSortedLists(result));
    }

    @Test
    void testExactMaximumCandidateAndTarget() {
        List<List<Integer>> result = solution.combinationSum(new int[]{40}, 40);
        assertEquals(Set.of(List.of(40)), toSetOfSortedLists(result));
    }

    @Test
    void testMinimumPositiveTargetCannotUseMinimumOfficialCandidate() {
        List<List<Integer>> result = solution.combinationSum(new int[]{2, 40}, 1);
        assertEquals(Set.of(), toSetOfSortedLists(result));
    }

    @Test
    void testCandidateAtTargetAndSmallerAlternatives() {
        List<List<Integer>> result = solution.combinationSum(new int[]{2, 4, 8, 16}, 16);
        assertEquals(Set.of(
                List.of(2, 2, 2, 2, 2, 2, 2, 2),
                List.of(2, 2, 2, 2, 2, 2, 4),
                List.of(2, 2, 2, 2, 4, 4),
                List.of(2, 2, 4, 4, 4),
                List.of(2, 2, 2, 2, 8),
                List.of(2, 2, 4, 8),
                List.of(4, 4, 4, 4),
                List.of(4, 4, 8),
                List.of(8, 8),
                List.of(16)), toSetOfSortedLists(result));
    }

    @Test
    void testIndependentMultiplicityOracleForSmallCandidateSet() {
        int[] candidates = {3, 5, 7};
        int target = 20;
        Set<List<Integer>> expected = enumerateByMultiplicity(candidates, target);
        assertEquals(expected, toSetOfSortedLists(solution.combinationSum(candidates.clone(), target)));
    }

    @Test
    void testCandidateOrderDoesNotChangeTheCombinationSet() {
        int[] ascending = {2, 3, 5, 7};
        int[] descending = {7, 5, 3, 2};
        Set<List<Integer>> expected = enumerateByMultiplicity(new int[]{2, 3, 5, 7}, 18);
        assertEquals(expected, toSetOfSortedLists(solution.combinationSum(ascending, 18)));
        assertEquals(expected, toSetOfSortedLists(solution.combinationSum(descending, 18)));
    }

    @Test
    void testNoSolutionWithOnlyLargeCandidatesAndTarget36() {
        List<List<Integer>> result = solution.combinationSum(new int[]{37, 38, 39, 40}, 36);
        assertEquals(Set.of(), toSetOfSortedLists(result));
    }

    @Test
    void testSeveralWaysToUseARepeatedCandidate() {
        List<List<Integer>> result = solution.combinationSum(new int[]{4, 6, 9}, 18);
        assertEquals(Set.of(List.of(4, 4, 4, 6), List.of(6, 6, 6), List.of(9, 9)),
                toSetOfSortedLists(result));
    }

    @Test
    void testResultContainsNoEmptyCombinationForPositiveTarget() {
        List<List<Integer>> result = solution.combinationSum(new int[]{2, 3}, 5);
        assertFalse(result.stream().anyMatch(List::isEmpty));
        assertEquals(Set.of(List.of(2, 3)), toSetOfSortedLists(result));
    }

    @Test
    void testRepeatedInvocationDoesNotRetainPreviousResults() {
        assertEquals(Set.of(List.of(2, 2)),
                toSetOfSortedLists(solution.combinationSum(new int[]{2}, 4)));
        assertEquals(Set.of(), toSetOfSortedLists(solution.combinationSum(new int[]{3}, 2)));
    }

    @Test
    void testThirtyCandidatesWithinOfficialOutputBound() {
        int[] candidates = new int[30];
        for (int i = 0; i < candidates.length; i++) {
            candidates[i] = i + 11;
        }
        List<List<Integer>> result = solution.combinationSum(candidates, 40);
        Set<List<Integer>> expected = enumerateByMultiplicity(candidates, 40);
        assertTrue(expected.size() < 150, "official test data limits the number of combinations");
        assertEquals(expected, toSetOfSortedLists(result));
    }

    @Test
    void testCombinationOutputIsDuplicateFree() {
        int[] candidates = {2, 3, 5, 7};
        List<List<Integer>> result = solution.combinationSum(candidates, 20);
        assertEquals(enumerateByMultiplicity(candidates, 20), toSetOfSortedLists(result));
    }

    private Set<List<Integer>> enumerateByMultiplicity(int[] candidates, int target) {
        Set<List<Integer>> expected = new HashSet<>();
        enumerateByMultiplicity(candidates, target, 0, new ArrayList<>(), expected);
        return expected;
    }

    private void enumerateByMultiplicity(int[] candidates, int remaining, int index,
                                         List<Integer> current, Set<List<Integer>> expected) {
        if (index == candidates.length) {
            if (remaining == 0) {
                expected.add(List.copyOf(current));
            }
            return;
        }
        for (int count = 0; count * candidates[index] <= remaining; count++) {
            for (int i = 0; i < count; i++) {
                current.add(candidates[index]);
            }
            enumerateByMultiplicity(candidates, remaining - count * candidates[index], index + 1,
                    current, expected);
            for (int i = 0; i < count; i++) {
                current.remove(current.size() - 1);
            }
        }
    }

    private Set<List<Integer>> toSetOfSortedLists(List<List<Integer>> result) {
        Set<List<Integer>> canonical = result.stream()
                .map(combo -> combo.stream().sorted().collect(Collectors.toList()))
                .collect(Collectors.toSet());
        assertEquals(result.size(), canonical.size(), "duplicate combination returned");
        return canonical;
    }
}

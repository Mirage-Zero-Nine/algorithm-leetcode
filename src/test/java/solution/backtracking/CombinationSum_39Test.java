package solution.backtracking;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

    private Set<List<Integer>> toSetOfSortedLists(List<List<Integer>> result) {
        return result.stream()
                .map(combo -> combo.stream().sorted().collect(Collectors.toList()))
                .collect(Collectors.toSet());
    }
}

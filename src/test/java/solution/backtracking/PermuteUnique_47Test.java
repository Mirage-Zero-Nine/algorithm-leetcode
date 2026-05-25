package solution.backtracking;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PermuteUnique_47Test {
    private final PermuteUnique_47 solution = new PermuteUnique_47();

    @Test
    void testWithDuplicates() {
        List<List<Integer>> result = solution.permuteUnique(new int[]{1, 1, 2});
        assertEquals(3, result.size());
    }

    @Test
    void testAllSame() {
        List<List<Integer>> result = solution.permuteUnique(new int[]{1, 1, 1});
        assertEquals(1, result.size());
    }

    @Test
    void testNoDuplicates() {
        List<List<Integer>> result = solution.permuteUnique(new int[]{1, 2, 3});
        assertEquals(6, result.size());
    }

    @Test
    void testTwoDuplicates() {
        List<List<Integer>> result = solution.permuteUnique(new int[]{2, 2, 1, 1});
        assertEquals(6, result.size());
    }

    @Test
    void testSingleElement() {
        List<List<Integer>> result = solution.permuteUnique(new int[]{1});
        assertEquals(1, result.size());
    }

    @Test
    void testEmptyArray() {
        List<List<Integer>> result = solution.permuteUnique(new int[]{});
        assertEquals(0, result.size());
    }

    @Test
    void testNullInput() {
        List<List<Integer>> result = solution.permuteUnique(null);
        assertEquals(0, result.size());
    }

    @Test
    void testTwoElements() {
        List<List<Integer>> result = solution.permuteUnique(new int[]{1, 2});
        assertEquals(2, result.size());
    }

    @Test
    void testNegativeNumbers() {
        List<List<Integer>> result = solution.permuteUnique(new int[]{-1, -1, 2});
        assertEquals(3, result.size());
    }

    @Test
    void testAllUnique() {
        // 4! = 24
        List<List<Integer>> result = solution.permuteUnique(new int[]{1, 2, 3, 4});
        assertEquals(24, result.size());
    }

    @Test
    void testNoDuplicatePermutations() {
        List<List<Integer>> result = solution.permuteUnique(new int[]{1, 1, 2, 2});
        Set<List<Integer>> set = new HashSet<>(result);
        assertEquals(result.size(), set.size());
    }

    @Test
    void testCorrectPermutationLength() {
        int[] input = {1, 2, 2, 3};
        List<List<Integer>> result = solution.permuteUnique(input);
        for (List<Integer> perm : result) {
            assertEquals(input.length, perm.size());
        }
    }

    @Test
    void testGiantCase() {
        // 8 elements with duplicates: [1,1,2,2,3,3,4,4] -> 8!/(2!*2!*2!*2!) = 2520
        List<List<Integer>> result = solution.permuteUnique(new int[]{1, 1, 2, 2, 3, 3, 4, 4});
        assertEquals(2520, result.size());
    }

    // --- NEW TESTS ---

    @Test
    void testTwoSameElements() {
        // [1,1] -> only [[1,1]]
        List<List<Integer>> result = solution.permuteUnique(new int[]{1, 1});
        assertEquals(1, result.size());
        assertEquals(List.of(1, 1), result.get(0));
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
        // 5! = 120
        List<List<Integer>> result = solution.permuteUnique(new int[]{1, 2, 3, 4, 5});
        assertEquals(120, result.size());
        // No duplicates
        assertEquals(120, new HashSet<>(result).size());
    }

    @Test
    void testFiveAllSame() {
        // [2,2,2,2,2] -> only 1 result
        List<List<Integer>> result = solution.permuteUnique(new int[]{2, 2, 2, 2, 2});
        assertEquals(1, result.size());
        assertEquals(List.of(2, 2, 2, 2, 2), result.get(0));
    }

    @Test
    void testPropertyCountFormula() {
        // [1,1,2,2,3] -> 5! / (2! * 2! * 1!) = 30
        List<List<Integer>> result = solution.permuteUnique(new int[]{1, 1, 2, 2, 3});
        assertEquals(30, result.size());
    }

    @Test
    void testPropertyEveryResultIsPermutation() {
        // Every result must be a permutation of the input (same multiset)
        int[] input = {1, 1, 2, 3};
        List<Integer> sortedInput = Arrays.stream(input).sorted().boxed().toList();
        List<List<Integer>> result = solution.permuteUnique(input);
        for (List<Integer> perm : result) {
            List<Integer> sorted = perm.stream().sorted().toList();
            assertEquals(sortedInput, sorted, "Each result must be a permutation of input");
        }
    }

    @Test
    void testPropertyNoDuplicateResults() {
        // Verify uniqueness for a case with many duplicates: [1,1,1,2,2]
        // Expected: 5! / (3! * 2!) = 10
        List<List<Integer>> result = solution.permuteUnique(new int[]{1, 1, 1, 2, 2});
        assertEquals(10, result.size());
        Set<List<Integer>> unique = new HashSet<>(result);
        assertEquals(result.size(), unique.size(), "All results must be distinct");
    }

    @Test
    void testNegativeAndPositiveMixed() {
        // [-1, -1, 0, 1] -> 4! / 2! = 12
        List<List<Integer>> result = solution.permuteUnique(new int[]{-1, -1, 0, 1});
        assertEquals(12, result.size());
        assertEquals(12, new HashSet<>(result).size());
    }

    @Test
    void testThreePairsOfDuplicates() {
        // [1,1,2,2,3,3] -> 6! / (2! * 2! * 2!) = 90
        List<List<Integer>> result = solution.permuteUnique(new int[]{1, 1, 2, 2, 3, 3});
        assertEquals(90, result.size());
        assertEquals(90, new HashSet<>(result).size());
    }
}

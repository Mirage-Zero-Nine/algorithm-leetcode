package solutions.backtracking;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Subsets_78Test {
    private final Subsets_78 solution = new Subsets_78();

    @Test
    void testThreeElements() {
        List<List<Integer>> result = solution.subsets(new int[]{1, 2, 3});
        assertEquals(8, result.size());
    }

    @Test
    void testSingleElement() {
        List<List<Integer>> result = solution.subsets(new int[]{0});
        assertEquals(2, result.size());
    }

    @Test
    void testTwoElements() {
        List<List<Integer>> result = solution.subsets(new int[]{1, 2});
        assertEquals(4, result.size());
    }

    @Test
    void testFourElements() {
        List<List<Integer>> result = solution.subsets(new int[]{1, 2, 3, 4});
        assertEquals(16, result.size());
    }

    @Test
    void testNegativeNumbers() {
        List<List<Integer>> result = solution.subsets(new int[]{-1, 0, 1});
        assertEquals(8, result.size());
    }

    @Test
    void testEmptyArray() {
        List<List<Integer>> result = solution.subsets(new int[]{});
        assertEquals(1, result.size()); // only empty set
    }

    @Test
    void testFiveElements() {
        List<List<Integer>> result = solution.subsets(new int[]{1, 2, 3, 4, 5});
        assertEquals(32, result.size());
    }

    @Test
    void testBacktrackingMethod() {
        List<List<Integer>> result = solution.backtracking(new int[]{1, 2, 3});
        assertEquals(8, result.size());
    }

    @Test
    void testBacktrackingEmpty() {
        List<List<Integer>> result = solution.backtracking(new int[]{});
        assertEquals(0, result.size());
    }

    @Test
    void testContainsEmptySubset() {
        List<List<Integer>> result = solution.subsets(new int[]{1, 2});
        assertTrue(result.stream().anyMatch(List::isEmpty));
    }

    @Test
    void testGiantInput() {
        // 10 elements -> 1024 subsets
        List<List<Integer>> result = solution.subsets(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        assertEquals(1024, result.size());
    }

    @Test
    void testEmptyInputExactContent() {
        List<List<Integer>> result = solution.subsets(new int[]{});
        Set<List<Integer>> actual = new HashSet<>(result);
        Set<List<Integer>> expected = Set.of(List.of());
        assertEquals(expected, actual);
    }

    @Test
    void testSingleElementExactContent() {
        List<List<Integer>> result = solution.subsets(new int[]{5});
        Set<List<Integer>> actual = new HashSet<>(result);
        Set<List<Integer>> expected = Set.of(List.of(), List.of(5));
        assertEquals(expected, actual);
    }

    @Test
    void testTwoElementsExactContent() {
        List<List<Integer>> result = solution.subsets(new int[]{1, 2});
        Set<List<Integer>> actual = new HashSet<>(result);
        Set<List<Integer>> expected = Set.of(List.of(), List.of(1), List.of(2), List.of(1, 2));
        assertEquals(expected, actual);
    }

    @Test
    void testNegativeNumbersExactContent() {
        List<List<Integer>> result = solution.subsets(new int[]{-3, -1});
        Set<List<Integer>> actual = new HashSet<>(result);
        Set<List<Integer>> expected = Set.of(List.of(), List.of(-3), List.of(-1), List.of(-3, -1));
        assertEquals(expected, actual);
    }

    @Test
    void testLargeInput15Elements() {
        List<List<Integer>> result = solution.subsets(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
        assertEquals(32768, result.size());
    }

    @Test
    void testContainsFullSet() {
        int[] nums = {3, 7, 2, 9};
        List<List<Integer>> result = solution.subsets(nums);
        List<Integer> fullSet = Arrays.stream(nums).boxed().toList();
        assertTrue(result.stream().anyMatch(s -> new HashSet<>(s).equals(new HashSet<>(fullSet)) && s.size() == fullSet.size()));
    }

    @Test
    void testAllSubsetsUnique() {
        List<List<Integer>> result = solution.subsets(new int[]{1, 2, 3, 4, 5});
        Set<List<Integer>> uniqueSubsets = new HashSet<>();
        for (List<Integer> subset : result) {
            List<Integer> sorted = new ArrayList<>(subset);
            sorted.sort(Integer::compareTo);
            assertTrue(uniqueSubsets.add(sorted), "Duplicate subset found: " + sorted);
        }
    }

    @Test
    void testSubsetsOnlyContainOriginalElements() {
        int[] nums = {-5, 0, 7, 13};
        Set<Integer> originalElements = new HashSet<>(Arrays.stream(nums).boxed().toList());
        List<List<Integer>> result = solution.subsets(nums);
        for (List<Integer> subset : result) {
            for (Integer val : subset) {
                assertTrue(originalElements.contains(val), "Unexpected element: " + val);
            }
        }
    }

    @Test
    void testBothMethodsProduceSameSubsets() {
        int[] nums = {1, 2, 3, 4};
        List<List<Integer>> bitResult = solution.subsets(nums);
        List<List<Integer>> btResult = solution.backtracking(nums);
        Set<List<Integer>> bitSet = new HashSet<>();
        for (List<Integer> s : bitResult) {
            List<Integer> sorted = new ArrayList<>(s);
            sorted.sort(Integer::compareTo);
            bitSet.add(sorted);
        }
        Set<List<Integer>> btSet = new HashSet<>();
        for (List<Integer> s : btResult) {
            List<Integer> sorted = new ArrayList<>(s);
            sorted.sort(Integer::compareTo);
            btSet.add(sorted);
        }
        assertEquals(bitSet, btSet);
    }
}

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
}

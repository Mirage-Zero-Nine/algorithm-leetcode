package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CombinationSum3_216Test {
    private final CombinationSum3_216 solution = new CombinationSum3_216();

    @Test
    void testBasic() {
        List<List<Integer>> result = solution.combinationSum3(3, 7);
        assertEquals(1, result.size());
    }

    @Test
    void testMultipleSolutions() {
        List<List<Integer>> result = solution.combinationSum3(3, 9);
        assertTrue(result.size() >= 3);
    }

    @Test
    void testNoSolution() {
        List<List<Integer>> result = solution.combinationSum3(4, 1);
        assertEquals(0, result.size());
    }

    @Test
    void testLargeK() {
        List<List<Integer>> result = solution.combinationSum3(9, 45);
        assertEquals(1, result.size());
    }

    @Test
    void testSmallN() {
        List<List<Integer>> result = solution.combinationSum3(2, 3);
        assertEquals(1, result.size());
    }

    @Test
    void testKZero() {
        List<List<Integer>> result = solution.combinationSum3(0, 5);
        assertEquals(0, result.size());
    }

    @Test
    void testNZero() {
        List<List<Integer>> result = solution.combinationSum3(2, 0);
        assertEquals(0, result.size());
    }

    @Test
    void testNegativeK() {
        List<List<Integer>> result = solution.combinationSum3(-1, 5);
        assertEquals(0, result.size());
    }

    @Test
    void testNTooLarge() {
        // max sum with k=2 is 8+9=17, so n=18 should give 0
        List<List<Integer>> result = solution.combinationSum3(2, 18);
        assertEquals(0, result.size());
    }

    @Test
    void testK2N17() {
        // only [8,9] sums to 17
        List<List<Integer>> result = solution.combinationSum3(2, 17);
        assertEquals(1, result.size());
    }

    @Test
    void testK3N15() {
        List<List<Integer>> result = solution.combinationSum3(3, 15);
        // combinations: [1,5,9],[1,6,8],[2,4,9],[2,5,8],[2,6,7],[3,4,8],[3,5,7],[4,5,6]
        assertEquals(8, result.size());
    }

    @Test
    void testGiantK9N45() {
        // only one combination: [1,2,3,4,5,6,7,8,9]
        List<List<Integer>> result = solution.combinationSum3(9, 45);
        assertEquals(1, result.size());
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), result.get(0));
    }
}

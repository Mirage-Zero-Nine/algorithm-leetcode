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

class CombinationSum2_40Test {
    private final CombinationSum2_40 solution = new CombinationSum2_40();

    @Test
    void testBasic() {
        List<List<Integer>> result = solution.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        assertTrue(result.size() >= 4);
    }

    @Test
    void testDuplicates() {
        List<List<Integer>> result = solution.combinationSum2(new int[]{2, 5, 2, 1, 2}, 5);
        assertTrue(result.size() >= 2);
    }

    @Test
    void testNoSolution() {
        List<List<Integer>> result = solution.combinationSum2(new int[]{2}, 1);
        assertEquals(0, result.size());
    }

    @Test
    void testSingleElement() {
        List<List<Integer>> result = solution.combinationSum2(new int[]{1}, 1);
        assertEquals(1, result.size());
    }

    @Test
    void testAllSame() {
        List<List<Integer>> result = solution.combinationSum2(new int[]{1, 1, 1}, 2);
        assertEquals(1, result.size());
    }

    @Test
    void testEmptyArray() {
        List<List<Integer>> result = solution.combinationSum2(new int[]{}, 5);
        assertEquals(0, result.size());
    }

    @Test
    void testTargetZero() {
        // min value > target=0, so returns empty
        List<List<Integer>> result = solution.combinationSum2(new int[]{1, 2}, 0);
        assertEquals(0, result.size());
    }

    @Test
    void testMinValueLargerThanTarget() {
        List<List<Integer>> result = solution.combinationSum2(new int[]{5, 6, 7}, 3);
        assertEquals(0, result.size());
    }

    @Test
    void testNoDuplicateCombinations() {
        List<List<Integer>> result = solution.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        Set<List<Integer>> unique = new HashSet<>(result);
        assertEquals(result.size(), unique.size());
    }

    @Test
    void testAllElementsSumToTarget() {
        List<List<Integer>> result = solution.combinationSum2(new int[]{1, 2, 3}, 6);
        assertTrue(result.stream().anyMatch(l -> l.equals(Arrays.asList(1, 2, 3))));
    }

    @Test
    void testGiantInput() {
        int[] candidates = new int[]{1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,3,3,3,4,4,5};
        List<List<Integer>> result = solution.combinationSum2(candidates, 10);
        assertTrue(result.size() > 0);
        // verify no duplicates
        Set<List<Integer>> unique = new HashSet<>(result);
        assertEquals(result.size(), unique.size());
    }
}

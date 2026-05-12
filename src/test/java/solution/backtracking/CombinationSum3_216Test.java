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
}

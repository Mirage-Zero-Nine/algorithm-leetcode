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
        assertTrue(result.size() <= 2);
    }

    @Test
    void testMultipleDuplicates() {
        List<List<Integer>> result = solution.subsetsWithDup(new int[]{4, 4, 4, 1, 4});
        assertTrue(result.size() >= 8);
    }
}

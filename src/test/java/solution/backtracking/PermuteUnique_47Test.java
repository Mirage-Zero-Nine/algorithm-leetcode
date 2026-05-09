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
}

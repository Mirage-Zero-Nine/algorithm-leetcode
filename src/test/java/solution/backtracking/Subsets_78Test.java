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
}

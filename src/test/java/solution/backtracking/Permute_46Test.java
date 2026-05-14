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

class Permute_46Test {
    private final Permute_46 solution = new Permute_46();

    @Test
    void testThreeElements() {
        List<List<Integer>> result = solution.permute(new int[]{1, 2, 3});
        assertEquals(6, result.size());
    }

    @Test
    void testTwoElements() {
        List<List<Integer>> result = solution.permute(new int[]{0, 1});
        assertEquals(2, result.size());
    }

    @Test
    void testSingleElement() {
        List<List<Integer>> result = solution.permute(new int[]{1});
        assertEquals(1, result.size());
    }

    @Test
    void testFourElements() {
        List<List<Integer>> result = solution.permute(new int[]{1, 2, 3, 4});
        assertEquals(24, result.size());
    }

    @Test
    void testNegativeNumbers() {
        List<List<Integer>> result = solution.permute(new int[]{-1, 0, 1});
        assertEquals(6, result.size());
    }

    @Test
    void testNullInput() {
        List<List<Integer>> result = solution.permute(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testEmptyArray() {
        List<List<Integer>> result = solution.permute(new int[]{});
        assertTrue(result.isEmpty());
    }

    @Test
    void testFiveElements() {
        List<List<Integer>> result = solution.permute(new int[]{1, 2, 3, 4, 5});
        assertEquals(120, result.size());
    }

    @Test
    void testSixElements() {
        List<List<Integer>> result = solution.permute(new int[]{1, 2, 3, 4, 5, 6});
        assertEquals(720, result.size());
    }

    @Test
    void testAllNegative() {
        List<List<Integer>> result = solution.permute(new int[]{-3, -2, -1});
        assertEquals(6, result.size());
    }

    @Test
    void testGiantCase() {
        // 7! = 5040
        List<List<Integer>> result = solution.permute(new int[]{1, 2, 3, 4, 5, 6, 7});
        assertEquals(5040, result.size());
    }
}

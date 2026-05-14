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

class GetFactors_254Test {
    private final GetFactors_254 solution = new GetFactors_254();

    @Test
    void testOne() {
        List<List<Integer>> result = solution.getFactors(1);
        assertEquals(0, result.size());
    }

    @Test
    void testPrime() {
        List<List<Integer>> result = solution.getFactors(37);
        assertEquals(0, result.size());
    }

    @Test
    void testTwelve() {
        List<List<Integer>> result = solution.getFactors(12);
        assertTrue(result.size() >= 3);
    }

    @Test
    void testEight() {
        List<List<Integer>> result = solution.getFactors(8);
        assertTrue(result.size() >= 2);
    }

    @Test
    void testThirtyTwo() {
        List<List<Integer>> result = solution.getFactors(32);
        assertTrue(result.size() >= 5);
    }

    @Test
    void testTwo() {
        List<List<Integer>> result = solution.getFactors(2);
        assertEquals(0, result.size());
    }

    @Test
    void testThree() {
        List<List<Integer>> result = solution.getFactors(3);
        assertEquals(0, result.size());
    }

    @Test
    void testFour() {
        List<List<Integer>> result = solution.getFactors(4);
        assertEquals(1, result.size()); // [2, 2]
    }

    @Test
    void testSixteen() {
        List<List<Integer>> result = solution.getFactors(16);
        assertTrue(result.size() >= 4);
    }

    @Test
    void testSix() {
        List<List<Integer>> result = solution.getFactors(6);
        assertEquals(1, result.size()); // [2, 3]
    }

    @Test
    void testGiantNumber() {
        List<List<Integer>> result = solution.getFactors(1000000);
        assertTrue(result.size() > 10);
    }
}

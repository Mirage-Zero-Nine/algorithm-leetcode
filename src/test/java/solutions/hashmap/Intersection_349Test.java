package solutions.hashmap;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Unit tests for {@link Intersection_349}.
 */
public class Intersection_349Test {

    private final Intersection_349 solver = new Intersection_349();

    @Test
    public void testLeetCodeExample1() {
        // nums1 = [1,2,2,1], nums2 = [2,2] → [2]
        int[] result = solver.intersection(new int[]{1, 2, 2, 1}, new int[]{2, 2});
        assertArrayEquals(new int[]{2}, result);
    }

    @Test
    public void testLeetCodeExample2() {
        // nums1 = [4,9,5], nums2 = [9,4,9,8,4] → [4,9] or [9,4]
        int[] result = solver.intersection(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4});
        Set<Integer> s = new HashSet<>() {{ for (int v : result) add(v); }};
        assertTrue(s.contains(4));
        assertTrue(s.contains(9));
        assertEquals(2, result.length);
    }

    @Test
    public void testNoIntersection() {
        int[] result = solver.intersection(new int[]{1, 2, 3}, new int[]{4, 5, 6});
        assertEquals(0, result.length);
    }

    @Test
    public void testFullOverlap() {
        int[] result = solver.intersection(new int[]{1, 2, 3}, new int[]{1, 2, 3});
        assertEquals(3, result.length);
        Set<Integer> s = new HashSet<>() {{ for (int v : result) add(v); }};
        assertTrue(s.contains(1));
        assertTrue(s.contains(2));
        assertTrue(s.contains(3));
    }

    @Test
    public void testEmptyFirst() {
        int[] result = solver.intersection(new int[]{}, new int[]{1, 2, 3});
        assertEquals(0, result.length);
    }

    @Test
    public void testEmptySecond() {
        int[] result = solver.intersection(new int[]{1, 2, 3}, new int[0]);
        assertEquals(0, result.length);
    }

    @Test
    public void testBothEmpty() {
        int[] result = solver.intersection(new int[]{}, new int[0]);
        assertEquals(0, result.length);
    }

    @Test
    public void testSingleElementMatch() {
        int[] result = solver.intersection(new int[]{1}, new int[]{1});
        assertArrayEquals(new int[]{1}, result);
    }

    @Test
    public void testSingleElementNoMatch() {
        int[] result = solver.intersection(new int[]{1}, new int[]{2});
        assertEquals(0, result.length);
    }

    @Test
    public void testDuplicates() {
        int[] result = solver.intersection(new int[]{1, 1, 1, 1}, new int[]{1, 1, 1});
        assertArrayEquals(new int[]{1}, result);
    }

    @Test
    public void testNegativeNumbers() {
        int[] result = solver.intersection(new int[]{-1, -2, -3}, new int[]{-2, -3, -4});
        assertEquals(2, result.length);
        Set<Integer> s = new HashSet<>() {{ for (int v : result) add(v); }};
        assertTrue(s.contains(-2));
        assertTrue(s.contains(-3));
    }

    @Test
    public void testMixedPositiveNegative() {
        int[] result = solver.intersection(new int[]{-1, 0, 1}, new int[]{0, 1, 2});
        assertEquals(2, result.length);
        Set<Integer> s = new HashSet<>() {{ for (int v : result) add(v); }};
        assertTrue(s.contains(0));
        assertTrue(s.contains(1));
    }
    @Test void extra01() { assertArrayEquals(new int[]{}, solver.intersection(new int[]{}, new int[]{1})); }
    @Test void extra02() { assertEquals(1, solver.intersection(new int[]{1}, new int[]{1}).length); }
    @Test void extra03() { assertEquals(2, solver.intersection(new int[]{1,2,2}, new int[]{2,1}).length); }
    @Test void extra04() { assertEquals(0, solver.intersection(new int[]{1,2}, new int[]{3,4}).length); }
    @Test void extra05() { assertEquals(1, solver.intersection(new int[]{-1,-1}, new int[]{-1}).length); }
    @Test void extra06() { assertEquals(3, solver.intersection(new int[]{1,2,3}, new int[]{3,2,1}).length); }
    @Test void extra07() { assertEquals(1, solver.intersection(new int[]{0,0}, new int[]{0}).length); }
    @Test void extra08() { assertEquals(2, solver.intersection(new int[]{5,6,7}, new int[]{6,7,8}).length); }
    @Test void extra09() { assertEquals(1, solver.intersection(new int[]{1000}, new int[]{1000,1001}).length); }
    @Test void extra10() { assertEquals(2, solver.intersection(new int[]{4,9,4}, new int[]{9,4}).length); }
}

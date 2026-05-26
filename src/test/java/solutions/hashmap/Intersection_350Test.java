package solutions.hashmap;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Unit tests for {@link Intersection_350}.
 */
public class Intersection_350Test {

    private final Intersection_350 solver = new Intersection_350();

    @Test
    public void testLeetCodeExample1() {
        // nums1 = [1,2,2,1], nums2 = [2,2] → [2,2]
        int[] result = solver.intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2});
        assertArrayEquals(new int[]{2, 2}, result);
    }

    @Test
    public void testLeetCodeExample2() {
        // nums1 = [4,9,5], nums2 = [9,4,9,8,4] → [4,9] or [9,4]
        int[] result = solver.intersect(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4});
        assertEquals(2, result.length);
        assertTrue(java.util.Arrays.stream(result).boxed().anyMatch(x -> x == 4));
        assertTrue(java.util.Arrays.stream(result).boxed().anyMatch(x -> x == 9));
    }

    @Test
    public void testNoIntersection() {
        int[] result = solver.intersect(new int[]{1, 2, 3}, new int[]{4, 5, 6});
        assertEquals(0, result.length);
    }

    @Test
    public void testFullOverlap() {
        int[] result = solver.intersect(new int[]{1, 2, 3}, new int[]{1, 2, 3});
        assertArrayEquals(new int[]{1, 2, 3}, result);
    }

    @Test
    public void testEmptyFirst() {
        int[] result = solver.intersect(new int[]{}, new int[]{1, 2, 3});
        assertEquals(0, result.length);
    }

    @Test
    public void testEmptySecond() {
        int[] result = solver.intersect(new int[]{1, 2, 3}, new int[0]);
        assertEquals(0, result.length);
    }

    @Test
    public void testBothEmpty() {
        int[] result = solver.intersect(new int[]{}, new int[0]);
        assertEquals(0, result.length);
    }

    @Test
    public void testSingleElementMatch() {
        int[] result = solver.intersect(new int[]{1}, new int[]{1});
        assertArrayEquals(new int[]{1}, result);
    }

    @Test
    public void testSingleElementNoMatch() {
        int[] result = solver.intersect(new int[]{1}, new int[]{2});
        assertEquals(0, result.length);
    }

    @Test
    public void testAllDuplicates() {
        // nums1 = [2,2,2], nums2 = [2,2] → [2,2]
        int[] result = solver.intersect(new int[]{2, 2, 2}, new int[]{2, 2});
        assertArrayEquals(new int[]{2, 2}, result);
    }

    @Test
    public void testMultipleDuplicates() {
        // nums1 = [1,1,1,2,2], nums2 = [1,1,2,2,2] → [1,1,2,2]
        int[] result = solver.intersect(new int[]{1, 1, 1, 2, 2}, new int[]{1, 1, 2, 2, 2});
        assertEquals(4, result.length);
        long ones = Arrays.stream(result).filter(x -> x == 1).count();
        long twos = Arrays.stream(result).filter(x -> x == 2).count();
        assertEquals(2, ones);
        assertEquals(2, twos);
    }

    @Test
    public void testNegativeNumbers() {
        int[] result = solver.intersect(new int[]{-1, -2, -2}, new int[]{-2, -2, -3});
        assertArrayEquals(new int[]{-2, -2}, result);
    }

    @Test
    public void testMixedPositiveNegative() {
        int[] result = solver.intersect(new int[]{-1, 0, 1}, new int[]{0, 1, 2});
        assertArrayEquals(new int[]{0, 1}, result);
    }
}

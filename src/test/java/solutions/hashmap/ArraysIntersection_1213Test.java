package solutions.hashmap;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link ArraysIntersection_1213}.
 */
public class ArraysIntersection_1213Test {

    private final ArraysIntersection_1213 solver = new ArraysIntersection_1213();

    @Test
    public void testBasicIntersection() {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {1, 2, 3, 4, 5, 6};
        int[] arr3 = {1, 2, 3, 4, 5, 7};

        List<Integer> result = solver.arraysIntersection(arr1, arr2, arr3);

        // 1,2,3,4,5 are in all three arrays
        assertEquals(5, result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertTrue(result.contains(5));
    }

    @Test
    public void testNoCommonElements() {
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {4, 5, 6};
        int[] arr3 = {7, 8, 9};

        List<Integer> result = solver.arraysIntersection(arr1, arr2, arr3);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testAllSameArrays() {
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};
        int[] arr3 = {1, 2, 3};

        List<Integer> result = solver.arraysIntersection(arr1, arr2, arr3);

        assertEquals(3, result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
    }

    @Test
    public void testSingleElementIntersection() {
        int[] arr1 = {1};
        int[] arr2 = {1};
        int[] arr3 = {1};

        List<Integer> result = solver.arraysIntersection(arr1, arr2, arr3);

        assertEquals(1, result.size());
        assertEquals(1, result.get(0));
    }

    @Test
    public void testEmptyArrays() {
        int[] arr1 = {};
        int[] arr2 = {1, 2, 3};
        int[] arr3 = {1, 2, 3};

        List<Integer> result = solver.arraysIntersection(arr1, arr2, arr3);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testOverlappingRanges() {
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 3, 5, 7, 10};
        int[] arr3 = {3, 5, 7, 8, 11};

        List<Integer> result = solver.arraysIntersection(arr1, arr2, arr3);

        assertEquals(3, result.size());
        assertTrue(result.contains(3));
        assertTrue(result.contains(5));
        assertTrue(result.contains(7));
    }

    @Test
    public void testSingleElementInOneArray() {
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};
        int[] arr3 = {2};

        List<Integer> result = solver.arraysIntersection(arr1, arr2, arr3);

        assertEquals(1, result.size());
        assertEquals(2, result.get(0));
    }

    @Test
    public void testPartialOverlap() {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {3, 4, 5, 6, 7};
        int[] arr3 = {3, 4, 5, 8, 9};

        List<Integer> result = solver.arraysIntersection(arr1, arr2, arr3);

        assertEquals(3, result.size());
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertTrue(result.contains(5));
    }

    @Test
    public void testAllEmpty() {
        List<Integer> result = solver.arraysIntersection(new int[]{}, new int[]{}, new int[]{});
        assertTrue(result.isEmpty());
    }

    @Test
    public void testLargeValues() {
        int[] arr1 = {100, 200, 300};
        int[] arr2 = {100, 200, 300};
        int[] arr3 = {100, 200, 300};
        List<Integer> result = solver.arraysIntersection(arr1, arr2, arr3);
        assertEquals(List.of(100, 200, 300), result);
    }

    @Test
    public void testGiantCase() {
        int n = 10000;
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        int[] arr3 = new int[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = i;
            arr2[i] = i;
            arr3[i] = i;
        }
        List<Integer> result = solver.arraysIntersection(arr1, arr2, arr3);
        assertEquals(n, result.size());
    }
    @Test void extra01() { assertEquals(List.of(1), solver.arraysIntersection(new int[]{1},new int[]{1},new int[]{1})); }
    @Test void extra02() { assertEquals(List.of(), solver.arraysIntersection(new int[]{1},new int[]{2},new int[]{3})); }
    @Test void extra03() { assertEquals(List.of(2,3), solver.arraysIntersection(new int[]{1,2,3},new int[]{2,3,4},new int[]{0,2,3})); }
    @Test void extra04() { assertEquals(List.of(5), solver.arraysIntersection(new int[]{5,6},new int[]{5,7},new int[]{5,8})); }
    @Test void extra05() { assertEquals(List.of(1,2,3), solver.arraysIntersection(new int[]{1,2,3},new int[]{1,2,3},new int[]{1,2,3})); }
    @Test void extra06() { assertEquals(List.of(), solver.arraysIntersection(new int[]{},new int[]{},new int[]{})); }
    @Test void extra07() { assertEquals(List.of(0), solver.arraysIntersection(new int[]{0,1},new int[]{0,2},new int[]{0,3})); }
    @Test void extra08() { assertEquals(List.of(10), solver.arraysIntersection(new int[]{10,20},new int[]{10,30},new int[]{10,40})); }
    @Test void extra09() { assertEquals(List.of(2,4), solver.arraysIntersection(new int[]{1,2,4},new int[]{2,4,5},new int[]{0,2,4})); }
    @Test void extra10() { assertEquals(List.of(100), solver.arraysIntersection(new int[]{100},new int[]{100},new int[]{100})); }
}

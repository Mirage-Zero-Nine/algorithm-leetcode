package solution.array;

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
}

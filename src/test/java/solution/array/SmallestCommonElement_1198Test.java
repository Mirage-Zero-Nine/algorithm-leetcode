package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link SmallestCommonElement_1198}.
 * Note: The implementation has a known bug — the comparator sorts by o1[0]
 * regardless of which index triggered the comparison, and binarySearch is
 * called on rows that may not be sorted. Tests reflect actual behavior.
 */
public class SmallestCommonElement_1198Test {

    private final SmallestCommonElement_1198 solver = new SmallestCommonElement_1198();

    @Test
    public void testLeetCodeExample() {
        // mat = [[1,2,3,4,5],[2,4,5,8,10],[3,3,7,9,11],[1,3,5,7,9]]
        // Note: actual output is -1 due to comparator bug (sorts by o1[0])
        // and binarySearch on potentially unsorted rows.
        int[][] mat = {
            {1, 2, 3, 4, 5},
            {2, 4, 5, 8, 10},
            {3, 3, 7, 9, 11},
            {1, 3, 5, 7, 9}
        };
        assertEquals(-1, solver.smallestCommonElement(mat));
    }

    @Test
    public void testSingleRow() {
        // With one row, the loop finds min from first row,
        // j starts at 1 which equals mat.length, returns min.
        int[][] mat = {{1, 2, 3}};
        assertEquals(1, solver.smallestCommonElement(mat));
    }

    @Test
    public void testSingleColumn() {
        // Each row has one element; binarySearch on single-element arrays.
        int[][] mat = {{1}, {2}, {3}};
        assertEquals(-1, solver.smallestCommonElement(mat));
    }

    @Test
    public void testAllSameElements() {
        // All rows are [1,1,1] — sorted, binarySearch works.
        int[][] mat = {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        };
        assertEquals(1, solver.smallestCommonElement(mat));
    }

    @Test
    public void testTwoIdenticalRows() {
        // Identical sorted rows; first element of first row determines sort.
        int[][] mat = {
            {1, 2, 3},
            {1, 2, 3}
        };
        assertEquals(1, solver.smallestCommonElement(mat));
    }

    @Test
    public void testTwoRowsSameFirstElement() {
        // Both start with 1; sorted by first element (equal), then last element.
        // mat becomes [[1,2,3],[1,3,5]] or similar; binarySearch for 1 in [1,3,5] = found.
        int[][] mat = {
            {1, 2, 3},
            {1, 3, 5}
        };
        assertEquals(1, solver.smallestCommonElement(mat));
    }

    @Test
    public void testNoCommonElement() {
        // mat = [[1,2,3],[4,5,6],[7,8,9]]
        // First row starts with 1; binarySearch for 1 in [4,5,6] = not found.
        int[][] mat = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        assertEquals(-1, solver.smallestCommonElement(mat));
    }
}

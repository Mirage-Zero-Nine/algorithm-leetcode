package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Unit tests for {@link ReplaceElements_1299}.
 */
public class ReplaceElements_1299Test {

    private final ReplaceElements_1299 solver = new ReplaceElements_1299();

    @Test
    public void testLeetCodeExample() {
        // arr = [17,18,5,4,6,1]
        // right max: [18,6,6,6,1,-1]
        int[] arr = {17, 18, 5, 4, 6, 1};
        int[] expected = {18, 6, 6, 6, 1, -1};
        assertArrayEquals(expected, solver.replaceElements(arr));
    }

    @Test
    public void testSingleElement() {
        // arr = [-1]
        // last element replaced with -1
        int[] arr = {-1};
        int[] expected = {-1};
        assertArrayEquals(expected, solver.replaceElements(arr));
    }

    @Test
    public void testTwoElements() {
        // arr = [1,2]
        // right max: [2,-1]
        int[] arr = {1, 2};
        int[] expected = {2, -1};
        assertArrayEquals(expected, solver.replaceElements(arr));
    }

    @Test
    public void testDescendingArray() {
        // arr = [5,4,3,2,1]
        // right max: [4,3,2,1,-1]
        int[] arr = {5, 4, 3, 2, 1};
        int[] expected = {4, 3, 2, 1, -1};
        assertArrayEquals(expected, solver.replaceElements(arr));
    }

    @Test
    public void testAscendingArray() {
        // arr = [1,2,3,4,5]
        // right max: [5,5,5,5,-1]
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {5, 5, 5, 5, -1};
        assertArrayEquals(expected, solver.replaceElements(arr));
    }

    @Test
    public void testAllSameElements() {
        // arr = [3,3,3,3]
        // right max: [3,3,3,-1]
        int[] arr = {3, 3, 3, 3};
        int[] expected = {3, 3, 3, -1};
        assertArrayEquals(expected, solver.replaceElements(arr));
    }

    @Test
    public void testWithNegativeValues() {
        // arr = [-1,-2,-3,-4]
        // right max: [-1,-1,-1,-1] (all -1 since max of remaining is -1)
        int[] arr = {-1, -2, -3, -4};
        int[] expected = {-1, -1, -1, -1};
        assertArrayEquals(expected, solver.replaceElements(arr));
    }

    @Test
    public void testMixedPositiveAndNegative() {
        // arr = [1,-1]
        // right max: [-1,-1]
        int[] arr = {1, -1};
        int[] expected = {-1, -1};
        assertArrayEquals(expected, solver.replaceElements(arr));
    }

    @Test
    public void testMaxAtEnd() {
        // arr = [1,2,3,100]
        // right max: [100,100,100,-1]
        int[] arr = {1, 2, 3, 100};
        int[] expected = {100, 100, 100, -1};
        assertArrayEquals(expected, solver.replaceElements(arr));
    }

    @Test
    public void testMaxAtBeginning() {
        // arr = [100,1,2,3]
        // right max: [3,3,3,-1]
        int[] arr = {100, 1, 2, 3};
        int[] expected = {3, 3, 3, -1};
        assertArrayEquals(expected, solver.replaceElements(arr));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[10000];
        for (int i = 0; i < 10000; i++) arr[i] = i;
        int[] result = solver.replaceElements(arr);
        assertEquals(9999, result[0]);
        assertEquals(-1, result[9999]);
    }
}

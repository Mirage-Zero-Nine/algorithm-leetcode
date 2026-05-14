package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LargestUniqueNumber_1133Test {
    private final LargestUniqueNumber_1133 solution = new LargestUniqueNumber_1133();

    @Test
    void testBasic() {
        assertEquals(8, solution.largestUniqueNumber(new int[]{5, 7, 3, 9, 4, 9, 8, 3, 1}));
    }

    @Test
    void testNoUnique() {
        assertEquals(-1, solution.largestUniqueNumber(new int[]{9, 9, 8, 8}));
    }

    @Test
    void testSingleElement() {
        assertEquals(5, solution.largestUniqueNumber(new int[]{5}));
    }

    @Test
    void testAllUnique() {
        assertEquals(5, solution.largestUniqueNumber(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void testLargeNumbers() {
        assertEquals(999, solution.largestUniqueNumber(new int[]{999, 1000, 1000}));
    }

    @Test
    void testAllDuplicates() {
        assertEquals(-1, solution.largestUniqueNumber(new int[]{1, 1, 2, 2, 3, 3}));
    }

    @Test
    void testZero() {
        assertEquals(0, solution.largestUniqueNumber(new int[]{0}));
    }

    @Test
    void testZeroWithDuplicates() {
        assertEquals(0, solution.largestUniqueNumber(new int[]{0, 1, 1}));
    }

    @Test
    void testTwoElements() {
        assertEquals(2, solution.largestUniqueNumber(new int[]{1, 2}));
    }

    @Test
    void testGiantArray() {
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = i;
        }
        assertEquals(999, solution.largestUniqueNumber(arr));
    }
}

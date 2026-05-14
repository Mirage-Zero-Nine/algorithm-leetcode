package solution.array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MissingNumber_268Test {
    private final MissingNumber_268 solution = new MissingNumber_268();

    @Test
    void testBasic() {
        assertEquals(2, solution.missingNumber(new int[]{3, 0, 1}));
    }

    @Test
    void testMissingLast() {
        assertEquals(8, solution.missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
    }

    @Test
    void testMissingFirst() {
        assertEquals(0, solution.missingNumber(new int[]{1}));
    }

    @Test
    void testTwoElements() {
        assertEquals(2, solution.missingNumber(new int[]{0, 1}));
    }

    @Test
    void testSingleElement() {
        assertEquals(1, solution.missingNumber(new int[]{0}));
    }

    @Test
    void testMissingMiddle() {
        assertEquals(2, solution.missingNumber(new int[]{0, 1, 3}));
    }

    @Test
    void testBitManipulation() {
        assertEquals(2, solution.missingNumberBitManipulation(new int[]{3, 0, 1}));
        assertEquals(8, solution.missingNumberBitManipulation(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
    }

    @Test
    void testNaiveSolution() {
        assertEquals(2, solution.naiveSolution(new int[]{3, 0, 1}));
        assertEquals(0, solution.naiveSolution(new int[]{}));
    }

    @Test
    void testBinarySearch() {
        assertEquals(2, solution.binarySearch(new int[]{3, 0, 1}));
        assertEquals(8, solution.binarySearch(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
    }

    @Test
    void testMissingN() {
        assertEquals(3, solution.missingNumberBitManipulation(new int[]{0, 1, 2}));
    }

    @Test
    void testGiantCase() {
        int n = 10000;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        // missing 0
        assertEquals(0, solution.missingNumberBitManipulation(nums));
    }
}

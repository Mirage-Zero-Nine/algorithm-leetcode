package solutions.binarysearch;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MissingNumber_1228Test {
    private final MissingNumber_1228 solution = new MissingNumber_1228();

    @Test
    void testBasic() {
        assertEquals(9, solution.missingNumber(new int[]{5, 7, 11, 13}));
    }

    @Test
    void testNegative() {
        assertEquals(-3, solution.missingNumber(new int[]{-3, -5, -7}));
    }

    @Test
    void testTwoElements() {
        assertEquals(1, solution.missingNumber(new int[]{1, 5}));
    }

    @Test
    void testConsecutive() {
        assertEquals(1, solution.missingNumber(new int[]{1, 2, 3, 4}));
    }

    @Test
    void testLargeGap() {
        assertEquals(0, solution.missingNumber(new int[]{0, 20}));
    }

    @Test
    void testAllSameElements() {
        // all same → diff=0, no gap found → return arr[0]
        assertEquals(5, solution.missingNumber(new int[]{5, 5, 5, 5}));
    }

    @Test
    void testMissingInMiddle() {
        // [1,3,5,9,11] → diff=2, gap at 5→9 (diff=4), missing = 7
        assertEquals(7, solution.missingNumber(new int[]{1, 3, 5, 9, 11}));
    }

    @Test
    void testDecreasingSequence() {
        // [15,11,9,7,5,3] → diff=2, gap at 15→11 (diff=4), missing = 13
        assertEquals(13, solution.missingNumber(new int[]{15, 11, 9, 7, 5, 3}));
    }

    @Test
    void testMissingNearEnd() {
        // [0,5,10,15,25] → diff=5, gap at 15→25, missing = 20
        assertEquals(20, solution.missingNumber(new int[]{0, 5, 10, 15, 25}));
    }

    @Test
    void testNegativeDecreasing() {
        // [0,-2,-4,-8,-10] → diff=2, gap at -4→-8 (diff=4), missing = -6
        assertEquals(-6, solution.missingNumber(new int[]{0, -2, -4, -8, -10}));
    }

    @Test
    void testGiantArray() {
        // arithmetic progression 0,3,6,...,2997 with 9 removed → missing = 9
        int[] arr = new int[999];
        int idx = 0;
        for (int i = 0; i <= 2997; i += 3) {
            if (i == 9) continue;
            arr[idx++] = i;
        }
        assertEquals(9, solution.missingNumber(arr));
    }
}

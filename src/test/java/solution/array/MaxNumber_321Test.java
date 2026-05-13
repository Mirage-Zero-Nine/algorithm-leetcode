package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MaxNumber_321Test {
    private final MaxNumber_321 solution = new MaxNumber_321();

    @Test
    void testBasic() {
        assertArrayEquals(new int[]{9, 8, 6, 5, 3}, solution.maxNumber(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3}, 5));
    }

    @Test
    void testAllFromBoth() {
        assertArrayEquals(new int[]{6, 7, 6, 0, 4}, solution.maxNumber(new int[]{6, 7}, new int[]{6, 0, 4}, 5));
    }

    @Test
    void testSingleDigit() {
        assertArrayEquals(new int[]{9}, solution.maxNumber(new int[]{3, 9}, new int[]{8, 9}, 1));
    }

    @Test
    void testAllFromOne() {
        assertArrayEquals(new int[]{9, 8, 7}, solution.maxNumber(new int[]{9, 8, 7}, new int[]{1, 2}, 3));
    }

    @Test
    void testEmpty() {
        assertArrayEquals(new int[]{2, 1}, solution.maxNumber(new int[]{2, 1}, new int[]{}, 2));
    }

    @Test
    void testBothEmpty() {
        assertArrayEquals(new int[]{}, solution.maxNumber(new int[]{}, new int[]{}, 0));
    }

    @Test
    void testKEqualsTotal() {
        assertArrayEquals(new int[]{9, 3, 4, 6, 5, 1, 2, 5, 8, 3}, solution.maxNumber(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3}, 10));
    }

    @Test
    void testKZero() {
        assertArrayEquals(new int[]{}, solution.maxNumber(new int[]{1, 2, 3}, new int[]{4, 5, 6}, 0));
    }

    @Test
    void testSameDigits() {
        assertArrayEquals(new int[]{9, 9, 9, 9}, solution.maxNumber(new int[]{9, 9}, new int[]{9, 9}, 4));
    }

    @Test
    void testSecondEmpty() {
        assertArrayEquals(new int[]{5, 3}, solution.maxNumber(new int[]{5, 2, 3}, new int[]{}, 2));
    }

    @Test
    void testGiantCase() {
        int[] nums1 = new int[500];
        int[] nums2 = new int[500];
        for (int i = 0; i < 500; i++) {
            nums1[i] = i % 10;
            nums2[i] = (9 - i % 10);
        }
        int[] result = solution.maxNumber(nums1, nums2, 10);
        assertEquals(10, result.length);
        assertEquals(9, result[0]);
    }
}

package solutions.greedy;

import org.junit.jupiter.api.Test;

import java.util.Random;

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

    @Test
    void testAllZeros() {
        assertArrayEquals(new int[]{0, 0, 0}, solution.maxNumber(new int[]{0, 0}, new int[]{0, 0, 0}, 3));
    }

    @Test
    void testFirstArrayEmpty() {
        assertArrayEquals(new int[]{8, 5, 3}, solution.maxNumber(new int[]{}, new int[]{8, 1, 5, 3}, 3));
    }

    @Test
    void testBothSortedAscending() {
        assertArrayEquals(new int[]{5, 2, 3, 4, 5}, solution.maxNumber(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5}, 5));
    }

    @Test
    void testBothSortedDescending() {
        assertArrayEquals(new int[]{9, 9, 8, 8, 7}, solution.maxNumber(new int[]{9, 8, 7, 6, 5}, new int[]{9, 8, 7, 6, 5}, 5));
    }

    @Test
    void testKEqualsOne() {
        assertArrayEquals(new int[]{7}, solution.maxNumber(new int[]{1, 2, 3}, new int[]{4, 5, 7}, 1));
    }

    @Test
    void testKEqualsTotalLength() {
        // k = m + n, must use all digits in best interleave
        assertArrayEquals(new int[]{9, 8, 5, 3, 2, 1}, solution.maxNumber(new int[]{5, 3, 2}, new int[]{9, 8, 1}, 6));
    }

    @Test
    void testLargeRandomSmokeTest() {
        Random rng = new Random(42L);
        int[] nums1 = new int[50];
        int[] nums2 = new int[50];
        for (int i = 0; i < 50; i++) {
            nums1[i] = rng.nextInt(10);
            nums2[i] = rng.nextInt(10);
        }
        int k = 30;
        int[] result = solution.maxNumber(nums1, nums2, k);
        assertEquals(k, result.length);
        // first digit should be 9 (max possible)
        assertEquals(9, result[0]);
        for (int d : result) {
            assertTrue(d >= 0 && d <= 9);
        }
    }

    @Test
    void testPropertyResultLengthAlwaysK() {
        int[][] cases = {
            {1, 2, 3}, {4, 5, 6}
        };
        for (int k = 0; k <= 6; k++) {
            int[] result = solution.maxNumber(new int[]{1, 2, 3}, new int[]{4, 5, 6}, k);
            assertEquals(k, result.length, "Result length should equal k=" + k);
        }
    }
}

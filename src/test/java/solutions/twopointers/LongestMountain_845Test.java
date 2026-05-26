package solutions.twopointers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LongestMountain_845Test {
    private final LongestMountain_845 solution = new LongestMountain_845();

    @Test
    void testBasic() {
        assertEquals(5, solution.longestMountain(new int[]{2, 1, 4, 7, 3, 2, 5}));
    }

    @Test
    void testNoMountain() {
        assertEquals(0, solution.longestMountain(new int[]{2, 2, 2}));
    }

    @Test
    void testOnlyIncreasing() {
        assertEquals(0, solution.longestMountain(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void testOnlyDecreasing() {
        assertEquals(0, solution.longestMountain(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    void testMultipleMountains() {
        assertEquals(5, solution.longestMountain(new int[]{0, 1, 2, 3, 2, 2, 0, 1, 2, 1}));
    }

    @Test
    void testMinimalMountain() {
        assertEquals(3, solution.longestMountain(new int[]{1, 2, 1}));
    }

    @Test
    void testTwoElements() {
        assertEquals(0, solution.longestMountain(new int[]{1, 2}));
    }

    @Test
    void testEmptyArray() {
        assertEquals(0, solution.longestMountain(new int[]{}));
    }

    @Test
    void testSingleElement() {
        assertEquals(0, solution.longestMountain(new int[]{5}));
    }

    @Test
    void testPlateau() {
        assertEquals(0, solution.longestMountain(new int[]{1, 2, 2, 1}));
    }

    @Test
    void testEntireArrayMountain() {
        assertEquals(7, solution.longestMountain(new int[]{1, 2, 3, 4, 3, 2, 1}));
    }

    @Test
    void testGiantArray() {
        int[] arr = new int[10001];
        for (int i = 0; i <= 5000; i++) arr[i] = i;
        for (int i = 5001; i <= 10000; i++) arr[i] = 10000 - i;
        assertEquals(10001, solution.longestMountain(arr));
    }
}

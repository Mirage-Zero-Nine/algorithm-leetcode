package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxUncrossedLines_1035Test {

    private final MaxUncrossedLines_1035 test = new MaxUncrossedLines_1035();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.maxUncrossedLines(new int[]{1, 4, 2}, new int[]{1, 2, 4}));
        assertEquals(3, test.maxUncrossedLines(new int[]{2, 5, 1, 2, 5}, new int[]{10, 5, 2, 1, 5, 2}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxUncrossedLines(new int[]{1, 2, 3}, new int[]{4, 5, 6}));
        assertEquals(1, test.maxUncrossedLines(new int[]{1}, new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(2, test.maxUncrossedLines(new int[]{1, 3, 7, 1, 7, 5}, new int[]{1, 9, 2, 5, 1}));
    }

    @Test
    public void testIdenticalArrays() {
        assertEquals(5, test.maxUncrossedLines(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testReversedArrays() {
        assertEquals(1, test.maxUncrossedLines(new int[]{1, 2, 3, 4, 5}, new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void testSingleElementNoMatch() {
        assertEquals(0, test.maxUncrossedLines(new int[]{1}, new int[]{2}));
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, test.maxUncrossedLines(new int[]{}, new int[]{1, 2, 3}));
    }

    @Test
    public void testAllSameElements() {
        assertEquals(3, test.maxUncrossedLines(new int[]{1, 1, 1}, new int[]{1, 1, 1}));
    }

    @Test
    public void testGiantCase() {
        int[] a = new int[500];
        int[] b = new int[500];
        for (int i = 0; i < 500; i++) { a[i] = i; b[i] = i; }
        assertEquals(500, test.maxUncrossedLines(a, b));
    }

    @Test
    public void testPartialOverlap() {
        assertEquals(3, test.maxUncrossedLines(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 6, 7}));
    }
}

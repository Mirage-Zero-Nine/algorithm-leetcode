package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TwoSumLessThanK_1099Test {

    private final TwoSumLessThanK_1099 test = new TwoSumLessThanK_1099();

    @Test
    public void testHappyCases() {
        assertEquals(58, test.twoSumLessThanK(new int[]{34, 23, 1, 24, 75, 33, 54, 8}, 60));
        assertEquals(-1, test.twoSumLessThanK(new int[]{10, 20, 30}, 15));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.twoSumLessThanK(new int[]{10, 20, 30}, 5));
        assertEquals(2, test.twoSumLessThanK(new int[]{1, 1}, 3));
    }

    @Test
    public void testLargeCase() {
        assertEquals(9, test.twoSumLessThanK(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 10));
    }

    @Test
    public void testExactK() {
        // Sum equals K should not be included (must be strictly less)
        assertEquals(-1, test.twoSumLessThanK(new int[]{5, 5}, 10));
    }

    @Test
    public void testSumJustBelowK() {
        assertEquals(9, test.twoSumLessThanK(new int[]{4, 5}, 10));
    }

    @Test
    public void testAllSameElements() {
        assertEquals(4, test.twoSumLessThanK(new int[]{2, 2, 2, 2}, 5));
    }

    @Test
    public void testTwoElements() {
        assertEquals(3, test.twoSumLessThanK(new int[]{1, 2}, 5));
        assertEquals(-1, test.twoSumLessThanK(new int[]{3, 4}, 5));
    }

    @Test
    public void testLargeK() {
        assertEquals(199, test.twoSumLessThanK(new int[]{100, 99, 1, 2}, 200));
    }

    @Test
    public void testNoValidPair() {
        assertEquals(-1, test.twoSumLessThanK(new int[]{50, 60, 70}, 100));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = i + 1;
        }
        // Max pair sum < 2000: 999 + 1000 = 1999
        assertEquals(1999, test.twoSumLessThanK(arr, 2000));
    }
}

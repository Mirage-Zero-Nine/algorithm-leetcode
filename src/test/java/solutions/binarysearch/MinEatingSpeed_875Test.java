package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinEatingSpeed_875Test {

    private final MinEatingSpeed_875 test = new MinEatingSpeed_875();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
        assertEquals(30, test.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.minEatingSpeed(new int[]{1}, 1));
        assertEquals(3, test.minEatingSpeed(new int[]{3}, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(23, test.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6));
    }

    @Test
    public void testMoreHappyCases() {
        assertEquals(2, test.minEatingSpeed(new int[]{2, 2, 2, 2}, 4));
        assertEquals(1, test.minEatingSpeed(new int[]{2, 2, 2, 2}, 8));
    }

    @Test
    public void testHoursEqualsPileCount() {
        assertEquals(9, test.minEatingSpeed(new int[]{9, 7, 5}, 3));
    }

    @Test
    public void testSinglePileLargeHours() {
        assertEquals(1, test.minEatingSpeed(new int[]{100}, 100));
    }

    @Test
    public void testSinglePileTightHours() {
        assertEquals(34, test.minEatingSpeed(new int[]{100}, 3));
    }

    @Test
    public void testMixedPilesWithExactSpeedBoundary() {
        assertEquals(7, test.minEatingSpeed(new int[]{5, 6, 7, 8}, 5));
    }

    @Test
    public void testGiantCase() {
        int[] piles = new int[1000];
        for (int i = 0; i < piles.length; i++) {
            piles[i] = 100000000;
        }
        assertEquals(100000000, test.minEatingSpeed(piles, 1000));
    }

    @Test
    public void testAnotherBoundaryCase() {
        assertEquals(4, test.minEatingSpeed(new int[]{8, 8, 8}, 6));
    }
}

package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShipWithinDays_1011Test {

    private final ShipWithinDays_1011 test = new ShipWithinDays_1011();

    @Test
    public void testHappyCases() {
        assertEquals(15, test.shipWithinDays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5));
        assertEquals(6, test.shipWithinDays(new int[]{3, 2, 2, 4, 1, 4}, 3));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(3, test.shipWithinDays(new int[]{1, 2, 3, 1, 1}, 10));
        assertEquals(6, test.shipWithinDays(new int[]{1, 2, 3}, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(10, test.shipWithinDays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10));
    }

    @Test
    public void testSinglePackage() {
        assertEquals(7, test.shipWithinDays(new int[]{7}, 1));
    }

    @Test
    public void testDaysEqualsPackages() {
        assertEquals(9, test.shipWithinDays(new int[]{5, 9, 2, 4}, 4));
    }

    @Test
    public void testAllEqualWeights() {
        assertEquals(8, test.shipWithinDays(new int[]{4, 4, 4, 4}, 2));
    }

    @Test
    public void testTightDaysNeedsHigherCapacity() {
        assertEquals(11, test.shipWithinDays(new int[]{5, 6, 2, 3}, 2));
    }

    @Test
    public void testLooseDaysAllowsLowerCapacity() {
        assertEquals(6, test.shipWithinDays(new int[]{5, 6, 2, 3}, 3));
    }

    @Test
    public void testMonotonicIncreasingWeights() {
        assertEquals(17, test.shipWithinDays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 3));
    }

    @Test
    public void testGiantCase() {
        int[] weights = new int[1000];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = 1;
        }
        assertEquals(2, test.shipWithinDays(weights, 500));
    }
}

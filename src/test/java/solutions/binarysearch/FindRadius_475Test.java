package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindRadius_475Test {

    private final FindRadius_475 test = new FindRadius_475();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.findRadius(new int[]{1, 2, 3}, new int[]{2}));
        assertEquals(1, test.findRadius(new int[]{1, 2, 3, 4}, new int[]{1, 4}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.findRadius(new int[]{1}, new int[]{1}));
        assertEquals(2, test.findRadius(new int[]{1, 2, 3}, new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.findRadius(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[]{1, 9}));
    }

    @Test
    public void testHousesOutsideHeaterRange() {
        assertEquals(5, test.findRadius(new int[]{1, 10}, new int[]{5}));
    }

    @Test
    public void testUnsortedHeatersInput() {
        assertEquals(1, test.findRadius(new int[]{1, 2, 3, 4}, new int[]{4, 1}));
    }

    @Test
    public void testUnsortedHousesInput() {
        assertEquals(2, test.findRadius(new int[]{10, 1, 5}, new int[]{3, 8}));
    }

    @Test
    public void testDuplicateHeaters() {
        assertEquals(1, test.findRadius(new int[]{1, 2, 3}, new int[]{2, 2, 2}));
    }

    @Test
    public void testLargeCoordinates() {
        assertEquals(500000000, test.findRadius(new int[]{0, 1000000000}, new int[]{500000000}));
    }

    @Test
    public void testManyHousesManyHeaters() {
        assertEquals(1, test.findRadius(
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                new int[]{2, 5, 8, 10}
        ));
    }

    @Test
    public void testGiantInputEvenSpacing() {
        int[] houses = new int[200];
        int[] heaters = new int[20];
        for (int i = 0; i < houses.length; i++) {
            houses[i] = i * 2;
        }
        for (int i = 0; i < heaters.length; i++) {
            heaters[i] = i * 20;
        }
        assertEquals(18, test.findRadius(houses, heaters));
    }
}

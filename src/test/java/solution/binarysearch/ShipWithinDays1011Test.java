package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShipWithinDays1011Test {

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
}

package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinEatingSpeed875Test {

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
}

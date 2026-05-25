package solution.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CorpFlightBookings_1109Test {

    private final CorpFlightBookings_1109 test = new CorpFlightBookings_1109();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{10, 55, 45, 25, 25},
            test.corpFlightBookings(new int[][]{{1, 2, 10}, {2, 3, 20}, {2, 5, 25}}, 5));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[]{5}, test.corpFlightBookings(new int[][]{{1, 1, 5}}, 1));
        assertArrayEquals(new int[]{0, 0, 0}, test.corpFlightBookings(new int[][]{}, 3));
    }

    @Test
    public void testLargeCase() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5},
            test.corpFlightBookings(new int[][]{{1, 5, 1}, {2, 5, 1}, {3, 5, 1}, {4, 5, 1}, {5, 5, 1}}, 5));
    }

    @Test
    public void testSingleBookingFullRange() {
        assertArrayEquals(new int[]{10, 10, 10},
            test.corpFlightBookings(new int[][]{{1, 3, 10}}, 3));
    }

    @Test
    public void testSingleBookingPartialRange() {
        assertArrayEquals(new int[]{0, 5, 5, 0},
            test.corpFlightBookings(new int[][]{{2, 3, 5}}, 4));
    }

    @Test
    public void testMultipleOverlappingBookings() {
        assertArrayEquals(new int[]{10, 20, 10},
            test.corpFlightBookings(new int[][]{{1, 3, 10}, {2, 2, 10}}, 3));
    }

    @Test
    public void testNoOverlap() {
        assertArrayEquals(new int[]{5, 0, 0, 3},
            test.corpFlightBookings(new int[][]{{1, 1, 5}, {4, 4, 3}}, 4));
    }

    @Test
    public void testEmptyBookings() {
        assertArrayEquals(new int[]{0, 0, 0, 0, 0},
            test.corpFlightBookings(new int[][]{}, 5));
    }

    @Test
    public void testAllFlightsSameBooking() {
        assertArrayEquals(new int[]{7, 7, 7, 7},
            test.corpFlightBookings(new int[][]{{1, 4, 7}}, 4));
    }

    @Test
    public void testLastFlightOnly() {
        assertArrayEquals(new int[]{0, 0, 0, 0, 100},
            test.corpFlightBookings(new int[][]{{5, 5, 100}}, 5));
    }

    @Test
    public void testGiantCase() {
        int n = 10000;
        int[][] bookings = new int[1000][];
        for (int i = 0; i < 1000; i++) {
            bookings[i] = new int[]{1, n, 1};
        }
        int[] result = test.corpFlightBookings(bookings, n);
        for (int i = 0; i < n; i++) {
            assertEquals(1000, result[i]);
        }
    }
}

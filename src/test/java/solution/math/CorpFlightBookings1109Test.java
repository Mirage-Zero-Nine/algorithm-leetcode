package solution.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class CorpFlightBookings1109Test {

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
}

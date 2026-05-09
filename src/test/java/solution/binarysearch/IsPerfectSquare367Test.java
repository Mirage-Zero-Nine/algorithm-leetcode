package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsPerfectSquare367Test {

    private final IsPerfectSquare_367 test = new IsPerfectSquare_367();

    @Test
    public void testHappyCases() {
        assertTrue(test.isPerfectSquare(16));
        assertTrue(test.isPerfectSquare(1));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isPerfectSquare(14));
        assertTrue(test.isPerfectSquare(4));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isPerfectSquare(2147395600));
        assertFalse(test.isPerfectSquare(2147483647));
    }
}

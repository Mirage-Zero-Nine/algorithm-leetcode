package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsPerfectSquare_367Test {

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

    @Test
    public void testZero() {
        assertTrue(test.isPerfectSquare(0));
    }

    @Test
    public void testTwoIsNotPerfectSquare() {
        assertFalse(test.isPerfectSquare(2));
    }

    @Test
    public void testThreeIsNotPerfectSquare() {
        assertFalse(test.isPerfectSquare(3));
    }

    @Test
    public void testNineIsPerfectSquare() {
        assertTrue(test.isPerfectSquare(9));
    }

    @Test
    public void testFifteenIsNotPerfectSquare() {
        assertFalse(test.isPerfectSquare(15));
    }

    @Test
    public void testLargeNonSquareNearPerfectSquare() {
        assertFalse(test.isPerfectSquare(2147395601));
    }

    @Test
    public void testNegativeInputReturnsFalse() {
        assertFalse(test.isPerfectSquare(-1));
    }
}

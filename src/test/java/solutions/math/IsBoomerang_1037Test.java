package solutions.math;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsBoomerang_1037Test {

    private final IsBoomerang_1037 test = new IsBoomerang_1037();

    @Test
    public void testHappyCases() {
        assertTrue(test.isBoomerang(new int[][]{{1, 1}, {2, 3}, {3, 2}}));
        assertFalse(test.isBoomerang(new int[][]{{1, 1}, {2, 2}, {3, 3}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isBoomerang(new int[][]{{0, 0}, {0, 0}, {0, 0}}));
        assertFalse(test.isBoomerang(new int[][]{{0, 0}, {1, 0}, {2, 0}}));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isBoomerang(new int[][]{{0, 0}, {5, 5}, {5, 0}}));
    }

    @Test
    public void testVerticalLine() {
        assertFalse(test.isBoomerang(new int[][]{{0, 0}, {0, 1}, {0, 2}}));
    }

    @Test
    public void testHorizontalLine() {
        assertFalse(test.isBoomerang(new int[][]{{1, 5}, {3, 5}, {7, 5}}));
    }

    @Test
    public void testRightAngle() {
        assertTrue(test.isBoomerang(new int[][]{{0, 0}, {1, 0}, {0, 1}}));
    }

    @Test
    public void testNegativeCoordinates() {
        assertTrue(test.isBoomerang(new int[][]{{-1, -1}, {0, 1}, {1, -1}}));
    }

    @Test
    public void testTwoDuplicatePoints() {
        assertFalse(test.isBoomerang(new int[][]{{0, 0}, {0, 0}, {1, 1}}));
    }

    @Test
    public void testLargeCoordinates() {
        assertTrue(test.isBoomerang(new int[][]{{100, 100}, {0, 0}, {100, 0}}));
    }

    @Test
    public void testDiagonalNotCollinear() {
        assertTrue(test.isBoomerang(new int[][]{{1, 2}, {3, 5}, {5, 7}}));
    }
}

package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ComputeArea_223Test {

    private final ComputeArea_223 test = new ComputeArea_223();

    @Test
    public void testHappyCases() {
        assertEquals(45, test.computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
        assertEquals(16, test.computeArea(0, 0, 4, 4, 1, 1, 3, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(8, test.computeArea(0, 0, 2, 2, 3, 3, 5, 5));
        assertEquals(4, test.computeArea(0, 0, 2, 2, 0, 0, 2, 2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(175, test.computeArea(0, 0, 10, 10, 5, 5, 15, 15));
    }

    @Test
    public void testNoOverlapHorizontal() {
        assertEquals(8, test.computeArea(0, 0, 2, 2, 5, 0, 7, 2));
    }

    @Test
    public void testNoOverlapVertical() {
        assertEquals(8, test.computeArea(0, 0, 2, 2, 0, 5, 2, 7));
    }

    @Test
    public void testOneInsideAnother() {
        assertEquals(100, test.computeArea(0, 0, 10, 10, 2, 2, 5, 5));
    }

    @Test
    public void testNegativeCoordinates() {
        assertEquals(7, test.computeArea(-2, -2, 0, 0, -1, -1, 1, 1));
    }

    @Test
    public void testTouchingEdge() {
        assertEquals(8, test.computeArea(0, 0, 2, 2, 2, 0, 4, 2));
    }

    @Test
    public void testSinglePointRectangle() {
        // zero-area rectangle
        assertEquals(4, test.computeArea(0, 0, 2, 2, 3, 3, 3, 3));
    }

    @Test
    public void testGiantCoordinates() {
        // large coordinates, no overflow for area since int range
        assertEquals(200000, test.computeArea(-100000, 0, 0, 1, 0, 0, 100000, 1));
    }
}

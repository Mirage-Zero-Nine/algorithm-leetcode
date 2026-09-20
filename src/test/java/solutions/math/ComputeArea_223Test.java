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

    @Test
    public void testAdditional1() {
        assertEquals(2, test.computeArea(0,0,1,1,2,2,3,3));
    }

    @Test
    public void testAdditional2() {
        assertEquals(6, test.computeArea(0,0,2,2,1,0,3,2));
    }

    @Test
    public void testAdditional3() {
        assertEquals(6, test.computeArea(0,0,2,2,0,1,2,3));
    }

    @Test
    public void testAdditional4() {
        assertEquals(16, test.computeArea(-2,-2,2,2,-1,-1,1,1));
    }

    @Test
    public void testAdditional5() {
        assertEquals(20, test.computeArea(-5,0,-1,4,-3,1,1,3));
    }

    @Test
    public void testAdditional6() {
        assertEquals(10, test.computeArea(0,0,10,1,3,0,7,1));
    }

    @Test
    public void testAdditional7() {
        assertEquals(10, test.computeArea(0,0,1,10,0,2,1,8));
    }

    @Test
    public void testAdditional8() {
        assertEquals(2000000, test.computeArea(0,0,1000,1000,1000,1000,2000,2000));
    }

    @Test
    public void testAdditional9() {
        assertEquals(175, test.computeArea(-10,-10,0,0,-5,-5,5,5));
    }

    @Test
    public void testAdditional10() {
        assertEquals(0, test.computeArea(1,1,1,1,1,1,1,1));
    }
}

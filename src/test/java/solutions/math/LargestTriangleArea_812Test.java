package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LargestTriangleArea_812Test {

    private final LargestTriangleArea_812 test = new LargestTriangleArea_812();

    @Test
    public void testHappyCases() {
        assertEquals(2.0, test.largestTriangleArea(new int[][]{{0, 0}, {0, 1}, {1, 0}, {0, 2}, {2, 0}}), 0.0001);
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0.5, test.largestTriangleArea(new int[][]{{0, 0}, {1, 0}, {0, 1}}), 0.0001);
        assertEquals(0.0, test.largestTriangleArea(new int[][]{{0, 0}, {1, 1}, {2, 2}}), 0.0001);
    }

    @Test
    public void testLargeCase() {
        assertEquals(50.0, test.largestTriangleArea(new int[][]{{0, 0}, {10, 0}, {0, 10}, {5, 5}}), 0.0001);
    }

    @Test
    public void testUnitTriangle() {
        assertEquals(0.5, test.largestTriangleArea(new int[][]{{0, 0}, {1, 0}, {0, 1}}), 0.0001);
    }

    @Test
    public void testSquarePoints() {
        // largest triangle from a square is half the square area
        assertEquals(2.0, test.largestTriangleArea(new int[][]{{0, 0}, {2, 0}, {2, 2}, {0, 2}}), 0.0001);
    }

    @Test
    public void testNegativeCoordinates() {
        assertEquals(2.0, test.largestTriangleArea(new int[][]{{-1, -1}, {1, -1}, {0, 1}}), 0.0001);
    }

    @Test
    public void testAllCollinearPoints() {
        assertEquals(0.0, test.largestTriangleArea(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}}), 0.0001);
    }

    @Test
    public void testThreePointsOnly() {
        assertEquals(6.0, test.largestTriangleArea(new int[][]{{0, 0}, {4, 0}, {0, 3}}), 0.0001);
    }

    @Test
    public void testLargeCoordinates() {
        assertEquals(5000.0, test.largestTriangleArea(new int[][]{{0, 0}, {100, 0}, {0, 100}}), 0.0001);
    }

    @Test
    public void testMixedPoints() {
        // 5 points, largest triangle
        assertEquals(4.0, test.largestTriangleArea(new int[][]{{0, 0}, {2, 0}, {0, 4}, {1, 1}, {1, 2}}), 0.0001);
    }

    @Test
    public void testAdditional1() {
        assertEquals(0.5, test.largestTriangleArea(new int[][]{{0,0},{1,0},{0,1}}), 0.0001);
    }

    @Test
    public void testAdditional2() {
        assertEquals(2, test.largestTriangleArea(new int[][]{{0,0},{2,0},{0,2}}), 0.0001);
    }

    @Test
    public void testAdditional3() {
        assertEquals(6, test.largestTriangleArea(new int[][]{{0,0},{3,0},{0,4}}), 0.0001);
    }

    @Test
    public void testAdditional4() {
        assertEquals(1, test.largestTriangleArea(new int[][]{{0,0},{1,1},{2,0}}), 0.0001);
    }

    @Test
    public void testAdditional5() {
        assertEquals(0, test.largestTriangleArea(new int[][]{{0,0},{0,0},{1,1}}), 0.0001);
    }

    @Test
    public void testAdditional6() {
        assertEquals(4, test.largestTriangleArea(new int[][]{{-1,0},{0,2},{3,0}}), 0.0001);
    }

    @Test
    public void testAdditional7() {
        assertEquals(8, test.largestTriangleArea(new int[][]{{-2,-2},{2,-2},{0,2}}), 0.0001);
    }

    @Test
    public void testAdditional8() {
        assertEquals(2.5, test.largestTriangleArea(new int[][]{{1,1},{2,3},{4,2}}), 0.0001);
    }

    @Test
    public void testAdditional9() {
        assertEquals(2.5, test.largestTriangleArea(new int[][]{{0,0},{5,0},{2,1}}), 0.0001);
    }

    @Test
    public void testAdditional10() {
        assertEquals(5000, test.largestTriangleArea(new int[][]{{0,0},{100,0},{0,100}}), 0.0001);
    }
}

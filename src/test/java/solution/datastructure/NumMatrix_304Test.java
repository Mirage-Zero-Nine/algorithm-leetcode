package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumMatrix_304Test {

    @Test
    public void testHappyCases() {
        NumMatrix_304 nm = new NumMatrix_304(new int[][]{
            {3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}
        });
        assertEquals(8, nm.sumRegion(2, 1, 4, 3));
        assertEquals(11, nm.sumRegion(1, 1, 2, 2));
        assertEquals(12, nm.sumRegion(1, 2, 2, 4));
    }

    @Test
    public void testEdgeCases() {
        NumMatrix_304 nm = new NumMatrix_304(new int[][]{{1}});
        assertEquals(1, nm.sumRegion(0, 0, 0, 0));
    }

    @Test
    public void testLargeCase() {
        NumMatrix_304 nm = new NumMatrix_304(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        assertEquals(45, nm.sumRegion(0, 0, 2, 2));
        assertEquals(5, nm.sumRegion(1, 1, 1, 1));
    }

    @Test
    public void testSingleRow() {
        NumMatrix_304 nm = new NumMatrix_304(new int[][]{{1, 2, 3, 4, 5}});
        assertEquals(15, nm.sumRegion(0, 0, 0, 4));
        assertEquals(9, nm.sumRegion(0, 1, 0, 3));
        assertEquals(5, nm.sumRegion(0, 4, 0, 4));
    }

    @Test
    public void testSingleColumn() {
        NumMatrix_304 nm = new NumMatrix_304(new int[][]{{1}, {2}, {3}, {4}, {5}});
        assertEquals(15, nm.sumRegion(0, 0, 4, 0));
        assertEquals(9, nm.sumRegion(1, 0, 3, 0));
    }

    @Test
    public void testAllNegative() {
        NumMatrix_304 nm = new NumMatrix_304(new int[][]{{-1, -2}, {-3, -4}});
        assertEquals(-10, nm.sumRegion(0, 0, 1, 1));
        assertEquals(-1, nm.sumRegion(0, 0, 0, 0));
        assertEquals(-4, nm.sumRegion(1, 1, 1, 1));
    }

    @Test
    public void testAllZeros() {
        NumMatrix_304 nm = new NumMatrix_304(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}});
        assertEquals(0, nm.sumRegion(0, 0, 2, 2));
        assertEquals(0, nm.sumRegion(1, 1, 2, 2));
    }

    @Test
    public void testTopLeftCorner() {
        NumMatrix_304 nm = new NumMatrix_304(new int[][]{
            {3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}
        });
        assertEquals(3, nm.sumRegion(0, 0, 0, 0));
        assertEquals(14, nm.sumRegion(0, 0, 1, 1));
    }

    @Test
    public void testBottomRightCorner() {
        NumMatrix_304 nm = new NumMatrix_304(new int[][]{
            {3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}
        });
        assertEquals(5, nm.sumRegion(4, 4, 4, 4));
        assertEquals(13, nm.sumRegion(3, 3, 4, 4));
    }

    @Test
    public void testEntireMatrix() {
        NumMatrix_304 nm = new NumMatrix_304(new int[][]{
            {3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}
        });
        assertEquals(58, nm.sumRegion(0, 0, 4, 4));
    }

    @Test
    public void testGiantCase() {
        int n = 100;
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = 1;
        NumMatrix_304 nm = new NumMatrix_304(matrix);
        assertEquals(10000, nm.sumRegion(0, 0, 99, 99));
        assertEquals(2500, nm.sumRegion(0, 0, 49, 49));
        assertEquals(1, nm.sumRegion(50, 50, 50, 50));
    }
}

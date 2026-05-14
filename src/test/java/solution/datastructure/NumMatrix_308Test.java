package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumMatrix_308Test {

    @Test
    public void testHappyCases() {
        NumMatrix_308 nm = new NumMatrix_308(new int[][]{{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}});
        assertEquals(8, nm.sumRegion(2, 1, 4, 3));
        nm.update(3, 2, 2);
        assertEquals(10, nm.sumRegion(2, 1, 4, 3));
    }

    @Test
    public void testEdgeCases() {
        NumMatrix_308 nm = new NumMatrix_308(new int[][]{{1}});
        assertEquals(1, nm.sumRegion(0, 0, 0, 0));
        nm.update(0, 0, 5);
        assertEquals(5, nm.sumRegion(0, 0, 0, 0));
    }

    @Test
    public void testLargeCase() {
        NumMatrix_308 nm = new NumMatrix_308(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        assertEquals(45, nm.sumRegion(0, 0, 2, 2));
        nm.update(1, 1, 10);
        assertEquals(50, nm.sumRegion(0, 0, 2, 2));
    }

    @Test
    public void testMultipleUpdates() {
        NumMatrix_308 nm = new NumMatrix_308(new int[][]{{1, 2}, {3, 4}});
        assertEquals(10, nm.sumRegion(0, 0, 1, 1));
        nm.update(0, 0, 0);
        assertEquals(9, nm.sumRegion(0, 0, 1, 1));
        nm.update(1, 1, 0);
        assertEquals(5, nm.sumRegion(0, 0, 1, 1));
    }

    @Test
    public void testUpdateSameCell() {
        NumMatrix_308 nm = new NumMatrix_308(new int[][]{{1, 2}, {3, 4}});
        nm.update(0, 0, 10);
        assertEquals(10, nm.sumRegion(0, 0, 0, 0));
        nm.update(0, 0, -5);
        assertEquals(-5, nm.sumRegion(0, 0, 0, 0));
    }

    @Test
    public void testSingleRow() {
        NumMatrix_308 nm = new NumMatrix_308(new int[][]{{1, 2, 3, 4, 5}});
        assertEquals(15, nm.sumRegion(0, 0, 0, 4));
        nm.update(0, 2, 10);
        assertEquals(22, nm.sumRegion(0, 0, 0, 4));
    }

    @Test
    public void testSingleColumn() {
        NumMatrix_308 nm = new NumMatrix_308(new int[][]{{1}, {2}, {3}, {4}});
        assertEquals(10, nm.sumRegion(0, 0, 3, 0));
        nm.update(2, 0, 0);
        assertEquals(7, nm.sumRegion(0, 0, 3, 0));
    }

    @Test
    public void testAllZerosAfterUpdate() {
        NumMatrix_308 nm = new NumMatrix_308(new int[][]{{1, 1}, {1, 1}});
        nm.update(0, 0, 0);
        nm.update(0, 1, 0);
        nm.update(1, 0, 0);
        nm.update(1, 1, 0);
        assertEquals(0, nm.sumRegion(0, 0, 1, 1));
    }

    @Test
    public void testNegativeValues() {
        NumMatrix_308 nm = new NumMatrix_308(new int[][]{{-1, -2}, {-3, -4}});
        assertEquals(-10, nm.sumRegion(0, 0, 1, 1));
        nm.update(0, 0, 5);
        assertEquals(-4, nm.sumRegion(0, 0, 1, 1));
    }

    @Test
    public void testPartialRegionAfterUpdate() {
        NumMatrix_308 nm = new NumMatrix_308(new int[][]{{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}});
        assertEquals(11, nm.sumRegion(1, 1, 2, 2));
        nm.update(1, 1, 0);
        assertEquals(5, nm.sumRegion(1, 1, 2, 2));
    }

    @Test
    public void testGiantCase() {
        int n = 50;
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = 1;
        NumMatrix_308 nm = new NumMatrix_308(matrix);
        assertEquals(2500, nm.sumRegion(0, 0, 49, 49));
        nm.update(0, 0, 100);
        assertEquals(2599, nm.sumRegion(0, 0, 49, 49));
        assertEquals(100, nm.sumRegion(0, 0, 0, 0));
    }
}

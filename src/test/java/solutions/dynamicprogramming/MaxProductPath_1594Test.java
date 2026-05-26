package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxProductPath_1594Test {

    private final MaxProductPath_1594 test = new MaxProductPath_1594();

    @Test
    public void testHappyCases() {
        assertEquals(8, test.maxProductPath(new int[][]{{1, -2, 1}, {1, -2, 1}, {3, -4, 1}}));
        assertEquals(0, test.maxProductPath(new int[][]{{1, 3}, {0, -4}}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(2, test.maxProductPath(new int[][]{{1, 2}}));
        assertEquals(-1, test.maxProductPath(new int[][]{{-1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(120, test.maxProductPath(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }

    @Test
    public void testSinglePositiveCell() {
        assertEquals(5, test.maxProductPath(new int[][]{{5}}));
    }

    @Test
    public void testSingleZeroCell() {
        assertEquals(0, test.maxProductPath(new int[][]{{0}}));
    }

    @Test
    public void testAllNegativeNoPath() {
        // 2x2 all negative: all paths have odd number of negatives -> negative product
        assertEquals(-1, test.maxProductPath(new int[][]{{-1, -2}, {-3, -4}}));
    }

    @Test
    public void testZeroInPath() {
        // path can avoid zero: right-right-down gives 1*1*1*1=1
        assertEquals(1, test.maxProductPath(new int[][]{{1, 0, 1}, {1, 1, 1}}));
    }

    @Test
    public void testNegativeResult() {
        assertEquals(-1, test.maxProductPath(new int[][]{{-1, 1}, {1, 1}}));
    }

    @Test
    public void testSingleColumn() {
        assertEquals(6, test.maxProductPath(new int[][]{{1}, {2}, {3}}));
    }

    @Test
    public void testGiantGrid() {
        int n = 15;
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = 1;
        assertEquals(1, test.maxProductPath(grid));
    }
}

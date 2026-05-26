package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaximumMinimumPath_1102Test {

    private final MaximumMinimumPath_1102 test = new MaximumMinimumPath_1102();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.maximumMinimumPath(new int[][]{{5, 4, 5}, {1, 2, 6}, {7, 4, 6}}));
        assertEquals(2, test.maximumMinimumPath(new int[][]{{2, 2, 1, 2, 2, 2}, {1, 2, 2, 2, 1, 2}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.maximumMinimumPath(null));
        assertEquals(1, test.maximumMinimumPath(new int[][]{{1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.maximumMinimumPath(new int[][]{{3, 4, 6, 3, 4}, {0, 2, 1, 1, 7}, {8, 8, 3, 2, 7}, {3, 2, 4, 9, 8}, {4, 1, 2, 0, 0}, {4, 6, 5, 4, 3}}));
    }

    @Test
    public void testInvalidEmptyMatrix() {
        assertEquals(-1, test.maximumMinimumPath(new int[][]{}));
        assertEquals(-1, test.maximumMinimumPath(new int[][]{{}}));
    }

    @Test
    public void testSingleRow() {
        assertEquals(2, test.maximumMinimumPath(new int[][]{{5, 4, 2, 9}}));
    }

    @Test
    public void testSingleColumn() {
        assertEquals(3, test.maximumMinimumPath(new int[][]{{8}, {3}, {7}, {9}}));
    }

    @Test
    public void testAllEqualValues() {
        assertEquals(6, test.maximumMinimumPath(new int[][]{
                {6, 6, 6},
                {6, 6, 6},
                {6, 6, 6}
        }));
    }

    @Test
    public void testPathForcedThroughLowBottleneck() {
        assertEquals(5, test.maximumMinimumPath(new int[][]{
                {5, 5, 5},
                {5, 1, 5},
                {5, 5, 5}
        }));
    }

    @Test
    public void testContainsZeroValues() {
        assertEquals(9, test.maximumMinimumPath(new int[][]{
                {9, 0, 9},
                {9, 0, 9},
                {9, 9, 9}
        }));
    }

    @Test
    public void testGiantGrid() {
        int n = 10;
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = 1000 - i - j;
            }
        }
        assertEquals(982, test.maximumMinimumPath(grid));
    }
}

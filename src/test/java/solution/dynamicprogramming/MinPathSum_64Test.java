package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinPathSum_64Test {

    private final MinPathSum_64 test = new MinPathSum_64();

    @Test
    public void testHappyCases() {
        assertEquals(7, test.minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        assertEquals(12, test.minPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.minPathSum(new int[][]{{1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(21, test.minPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }

    @Test
    public void testSingleRow() {
        assertEquals(6, test.minPathSum(new int[][]{{1, 2, 3}}));
    }

    @Test
    public void testSingleColumn() {
        assertEquals(6, test.minPathSum(new int[][]{{1}, {2}, {3}}));
    }

    @Test
    public void testAllZeros() {
        assertEquals(0, test.minPathSum(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
    }

    @Test
    public void testAllOnes() {
        assertEquals(5, test.minPathSum(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}));
    }

    @Test
    public void testTwoByTwo() {
        assertEquals(3, test.minPathSum(new int[][]{{1, 2}, {1, 1}}));
    }

    @Test
    public void testNonSquareWide() {
        assertEquals(5, test.minPathSum(new int[][]{{1, 2, 3, 4}, {1, 1, 1, 1}}));
    }

    @Test
    public void testNonSquareTall() {
        assertEquals(5, test.minPathSum(new int[][]{{1, 1}, {2, 1}, {3, 1}, {4, 1}}));
    }

    @Test
    public void testGiantCase() {
        int size = 100;
        int[][] grid = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = 1;
            }
        }
        // Path from (0,0) to (99,99) requires 99 right + 99 down + start = 199 cells
        assertEquals(199, test.minPathSum(grid));
    }
}

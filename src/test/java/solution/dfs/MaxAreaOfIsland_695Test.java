package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxAreaOfIsland_695Test {

    private final MaxAreaOfIsland_695 test = new MaxAreaOfIsland_695();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.maxAreaOfIsland(new int[][]{{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxAreaOfIsland(null));
        assertEquals(0, test.maxAreaOfIsland(new int[][]{{0, 0, 0}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.maxAreaOfIsland(new int[][]{{1, 1, 0, 0}, {1, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}}));
    }

    @Test
    public void testEmptyGrid() {
        assertEquals(0, test.maxAreaOfIsland(new int[][]{}));
    }

    @Test
    public void testAllWater() {
        assertEquals(0, test.maxAreaOfIsland(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
    }

    @Test
    public void testAllLand() {
        assertEquals(9, test.maxAreaOfIsland(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}));
    }

    @Test
    public void testSingleCell() {
        assertEquals(1, test.maxAreaOfIsland(new int[][]{{1}}));
        assertEquals(0, test.maxAreaOfIsland(new int[][]{{0}}));
    }

    @Test
    public void testMultipleIslands() {
        assertEquals(2, test.maxAreaOfIsland(new int[][]{{1, 0, 1, 0}, {0, 0, 0, 0}, {1, 0, 1, 1}}));
    }

    @Test
    public void testLShapedIsland() {
        assertEquals(5, test.maxAreaOfIsland(new int[][]{{1, 0, 0}, {1, 0, 0}, {1, 1, 1}}));
    }

    @Test
    public void testDiagonalNotConnected() {
        // Diagonal cells are NOT connected
        assertEquals(1, test.maxAreaOfIsland(new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}));
    }

    @Test
    public void testGiantGrid() {
        // 50x50 grid with a single large island
        int[][] grid = new int[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                grid[i][j] = 1;
            }
        }
        assertEquals(2500, test.maxAreaOfIsland(grid));
    }
}

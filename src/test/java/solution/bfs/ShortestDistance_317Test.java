package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShortestDistance_317Test {

    private final ShortestDistance_317 test = new ShortestDistance_317();

    @Test
    public void testHappyCases() {
        assertEquals(7, test.shortestDistance(new int[][]{{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.shortestDistance(null));
        assertEquals(-1, test.shortestDistance(new int[][]{{1, 2, 1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(16, test.shortestDistance(new int[][]{{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {1, 0, 0, 0, 1}}));
    }

    @Test
    public void testSingleCellCases() {
        assertEquals(-1, test.shortestDistance(new int[][]{{1}}));
        assertEquals(-1, test.shortestDistance(new int[][]{{0}}));
    }

    @Test
    public void testEmptyGridCases() {
        assertEquals(-1, test.shortestDistance(new int[][]{}));
        assertEquals(-1, test.shortestDistance(new int[][]{{}}));
    }

    @Test
    public void testTwoBuildingsWithPath() {
        assertEquals(2, test.shortestDistance(new int[][]{
                {1, 0, 1}
        }));
    }

    @Test
    public void testTwoBuildingsBlockedByObstacle() {
        assertEquals(4, test.shortestDistance(new int[][]{
                {1, 2, 1},
                {0, 0, 0}
        }));
    }

    @Test
    public void testSingleBuildingBestAdjacent() {
        assertEquals(1, test.shortestDistance(new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        }));
    }

    @Test
    public void testObstacleMazeReachable() {
        assertEquals(16, test.shortestDistance(new int[][]{
                {1, 0, 2, 0, 1},
                {0, 0, 2, 0, 0},
                {0, 0, 0, 0, 0},
                {2, 2, 0, 2, 0},
                {1, 0, 0, 0, 1}
        }));
    }

    @Test
    public void testGiantOpenGridFourCorners() {
        int n = 15;
        int[][] grid = new int[n][n];
        grid[0][0] = 1;
        grid[0][n - 1] = 1;
        grid[n - 1][0] = 1;
        grid[n - 1][n - 1] = 1;
        assertEquals(56, test.shortestDistance(grid));
    }
}

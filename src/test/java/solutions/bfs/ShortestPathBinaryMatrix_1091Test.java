package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShortestPathBinaryMatrix_1091Test {

    private final ShortestPathBinaryMatrix_1091 test = new ShortestPathBinaryMatrix_1091();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.shortestPathBinaryMatrix(new int[][]{{0, 1}, {1, 0}}));
        assertEquals(4, test.shortestPathBinaryMatrix(new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 0}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.shortestPathBinaryMatrix(new int[][]{{1, 0}, {0, 0}}));
        assertEquals(1, test.shortestPathBinaryMatrix(new int[][]{{0}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, test.shortestPathBinaryMatrix(new int[][]{{0, 0, 0, 0, 0}, {1, 1, 0, 1, 0}, {1, 0, 0, 0, 0}, {0, 1, 1, 1, 0}, {0, 0, 0, 0, 0}}));
    }

    @Test
    public void testEndBlocked() {
        assertEquals(-1, test.shortestPathBinaryMatrix(new int[][]{{0, 0}, {0, 1}}));
    }

    @Test
    public void testNoPathExists() {
        assertEquals(-1, test.shortestPathBinaryMatrix(new int[][]{
                {0, 1, 1},
                {1, 1, 1},
                {1, 1, 0}
        }));
    }

    @Test
    public void testDiagonalPreferred() {
        assertEquals(3, test.shortestPathBinaryMatrix(new int[][]{
                {0, 1, 0},
                {0, 0, 0},
                {1, 0, 0}
        }));
    }

    @Test
    public void testAllZerosSmallGrid() {
        assertEquals(3, test.shortestPathBinaryMatrix(new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        }));
    }

    @Test
    public void testSingleCellBlocked() {
        assertEquals(-1, test.shortestPathBinaryMatrix(new int[][]{{1}}));
    }

    @Test
    public void testThinObstaclesStillReachable() {
        assertEquals(4, test.shortestPathBinaryMatrix(new int[][]{
                {0, 0, 1, 0},
                {1, 0, 1, 0},
                {1, 0, 0, 0},
                {1, 1, 1, 0}
        }));
    }

    @Test
    public void testGiantOpenGrid() {
        int n = 40;
        int[][] grid = new int[n][n];
        assertEquals(40, test.shortestPathBinaryMatrix(grid));
    }
}

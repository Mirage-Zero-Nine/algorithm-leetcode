package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShortestBridge_934Test {

    @Test
    public void testHappyCases() {
        assertEquals(1, new ShortestBridge_934().shortestBridge(new int[][]{{0, 1}, {1, 0}}));
        assertEquals(2, new ShortestBridge_934().shortestBridge(new int[][]{{0, 1, 0}, {0, 0, 0}, {0, 0, 1}}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, new ShortestBridge_934().shortestBridge(new int[][]{{1, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 0, 1, 0, 1}, {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(7, new ShortestBridge_934().shortestBridge(new int[][]{{1, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 1}}));
    }

    @Test
    public void testAdjacentIslandsNeedOneFlip() {
        assertEquals(1, new ShortestBridge_934().shortestBridge(new int[][]{
                {1, 0, 1}
        }));
    }

    @Test
    public void testDiagonalIslandsNeedOneFlip() {
        assertEquals(1, new ShortestBridge_934().shortestBridge(new int[][]{
                {1, 0},
                {0, 1}
        }));
    }

    @Test
    public void testSeparatedByTwoZeros() {
        assertEquals(2, new ShortestBridge_934().shortestBridge(new int[][]{
                {1, 0, 0, 1}
        }));
    }

    @Test
    public void testTwoRectangularIslands() {
        assertEquals(2, new ShortestBridge_934().shortestBridge(new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        }));
    }

    @Test
    public void testNarrowCorridor() {
        assertEquals(3, new ShortestBridge_934().shortestBridge(new int[][]{
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1}
        }));
    }

    @Test
    public void testFirstIslandLargeSecondSingle() {
        assertEquals(1, new ShortestBridge_934().shortestBridge(new int[][]{
                {1, 1, 1},
                {1, 0, 0},
                {1, 0, 1}
        }));
    }

    @Test
    public void testGiantGridCorners() {
        int n = 30;
        int[][] grid = new int[n][n];
        grid[0][0] = 1;
        grid[n - 1][n - 1] = 1;
        assertEquals(57, new ShortestBridge_934().shortestBridge(grid));
    }
}

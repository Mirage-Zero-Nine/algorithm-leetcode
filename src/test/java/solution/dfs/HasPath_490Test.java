package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class HasPath_490Test {

    private final HasPath_490 test = new HasPath_490();

    @Test
    public void testHappyCases() {
        assertTrue(test.hasPath(
            new int[][]{{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}},
            new int[]{0, 4}, new int[]{4, 4}
        ));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.hasPath(new int[][]{}, new int[]{0, 0}, new int[]{0, 0}));
        assertFalse(test.hasPath(
            new int[][]{{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}},
            new int[]{0, 4}, new int[]{3, 2}
        ));
    }

    @Test
    public void testLargeCase() {
        assertFalse(test.hasPath(
            new int[][]{{0, 0, 0, 0, 0}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 1, 0, 0, 1}, {0, 1, 0, 0, 0}},
            new int[]{4, 3}, new int[]{0, 1}
        ));
    }

    @Test
    public void testEmptyMaze() {
        assertFalse(test.hasPath(new int[][]{}, new int[]{0, 0}, new int[]{1, 1}));
    }

    @Test
    public void testEmptyColumns() {
        assertFalse(test.hasPath(new int[][]{{}, {}}, new int[]{0, 0}, new int[]{1, 0}));
    }

    @Test
    public void testSimpleOpenMaze() {
        // 3x3 open maze, ball rolls from corner to corner
        int[][] maze = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        // From (0,0) ball rolls right to (0,2), then down to (2,2)
        assertTrue(test.hasPath(maze, new int[]{0, 0}, new int[]{2, 2}));
    }

    @Test
    public void testDestinationNotOnStopPoint() {
        // Ball can't stop at (0,1) because it rolls past it
        int[][] maze = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        // From (0,0) rolling right goes to (0,2), can't stop at (0,1)
        assertFalse(test.hasPath(maze, new int[]{0, 0}, new int[]{0, 1}));
    }

    @Test
    public void testWallBlocksPath() {
        // Walls completely surround destination
        int[][] maze = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 1, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}
        };
        assertFalse(test.hasPath(maze, new int[]{0, 0}, new int[]{2, 2}));
    }

    @Test
    public void testStartNextToWall() {
        // Ball starts next to a wall and can reach destination
        int[][] maze = {
            {0, 1, 0},
            {0, 0, 0},
            {0, 1, 0}
        };
        assertTrue(test.hasPath(maze, new int[]{0, 0}, new int[]{2, 0}));
    }

    @Test
    public void testSingleRowMaze() {
        int[][] maze = {{0, 0, 0, 0, 0}};
        // Ball rolls from left to right end
        assertTrue(test.hasPath(maze, new int[]{0, 0}, new int[]{0, 4}));
    }

    @Test
    public void testSingleColumnMaze() {
        int[][] maze = {{0}, {0}, {0}, {0}, {0}};
        assertTrue(test.hasPath(maze, new int[]{0, 0}, new int[]{4, 0}));
    }

    @Test
    public void testGiantOpenMaze() {
        int n = 50;
        int[][] maze = new int[n][n];
        // All zeros - open maze. Ball at (0,0) rolls to (0, n-1), then to (n-1, n-1)
        assertTrue(test.hasPath(maze, new int[]{0, 0}, new int[]{n - 1, n - 1}));
    }
}

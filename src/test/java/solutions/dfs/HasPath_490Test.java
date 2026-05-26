package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
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

    @Test
    public void testStartEqualsDestination() {
        int[][] maze = {{0, 0, 1}, {0, 0, 0}, {1, 0, 0}};
        assertTrue(test.hasPath(maze, new int[]{1, 1}, new int[]{1, 1}));
    }

    @Test
    public void testOneByOneMaze() {
        int[][] maze = {{0}};
        assertTrue(test.hasPath(maze, new int[]{0, 0}, new int[]{0, 0}));
    }

    @Test
    public void testSingleRowNoWalls() {
        // Ball rolls from (0,0) all the way to (0,9) — only stops at boundary
        int[][] maze = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        assertTrue(test.hasPath(maze, new int[]{0, 0}, new int[]{0, 9}));
        // Can't stop at mid-point in single row with no walls
        assertFalse(test.hasPath(maze, new int[]{0, 0}, new int[]{0, 5}));
    }

    @Test
    public void testDirectLinePath() {
        // Wall at (0,4) stops ball at (0,3) from start (0,0)
        int[][] maze = {{0, 0, 0, 0, 1}, {0, 0, 0, 0, 0}};
        assertTrue(test.hasPath(maze, new int[]{0, 0}, new int[]{0, 3}));
    }

    @Test
    public void testMultipleBounces() {
        // Requires rolling right, then down, then left to reach destination
        int[][] maze = {
            {0, 0, 0, 0, 0},
            {1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1},
            {0, 0, 0, 0, 0}
        };
        // (0,0)->right->(0,4)->down->(2,4)->left->(2,0)->down->(4,0)->right->(4,4) — not target
        // (0,0)->right->(0,4)->down->(2,4)->left->(2,0)->down->(4,0) is reachable
        assertTrue(test.hasPath(maze, new int[]{0, 0}, new int[]{4, 0}));
    }

    @Test
    public void testDestinationWalledOff() {
        // Destination (2,2) is completely enclosed by walls
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
    public void testDestinationMidPathCannotStop() {
        // Ball rolls past destination because no wall to stop it there
        // Row: 0 0 0 0 1 — ball from (0,0) rolls right, stops at (0,3)
        // Destination (0,2) is mid-path, ball can't stop there
        int[][] maze = {{0, 0, 0, 0, 1}};
        assertFalse(test.hasPath(maze, new int[]{0, 0}, new int[]{0, 2}));
    }

    @Test
    public void testDiagonalCellsNotReachable() {
        // No diagonal moves allowed; (1,1) is diagonal from (0,0)
        // In this maze ball from (0,0) rolls right to (0,2), down to (2,2), left to (2,0), up to (0,0) — loop
        // (1,1) is never a stop point
        int[][] maze = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        assertFalse(test.hasPath(maze, new int[]{0, 0}, new int[]{1, 1}));
    }

    @Test
    public void testLargeMaze20x20Seed42() {
        Random rand = new Random(42L);
        int n = 20;
        int[][] maze = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maze[i][j] = rand.nextDouble() < 0.25 ? 1 : 0;
            }
        }
        maze[0][0] = 0;
        maze[n - 1][n - 1] = 0;
        // Deterministic with seed 42: destination unreachable
        assertFalse(test.hasPath(maze, new int[]{0, 0}, new int[]{n - 1, n - 1}));
    }
}

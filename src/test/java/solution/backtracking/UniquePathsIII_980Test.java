package solution.backtracking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UniquePathsIII_980Test {
    private final UniquePathsIII_980 solution = new UniquePathsIII_980();

    @Test
    void testBasic() {
        int[][] grid = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        assertEquals(2, solution.uniquePathsIII(grid));
    }

    @Test
    void testSmallGrid() {
        int[][] grid = {{1,0,0,0},{0,0,0,0},{0,0,0,2}};
        assertEquals(4, solution.uniquePathsIII(grid));
    }

    @Test
    void testWithObstacles() {
        int[][] grid = {{0,1},{2,0}};
        assertEquals(0, solution.uniquePathsIII(grid));
    }

    @Test
    void testSinglePath() {
        int[][] grid = {{1,2}};
        assertEquals(1, solution.uniquePathsIII(grid));
    }

    @Test
    void testNoPath() {
        int[][] grid = {{1,-1},{-1,2}};
        assertEquals(0, solution.uniquePathsIII(grid));
    }

    @Test
    void testNullGrid() {
        assertEquals(0, solution.uniquePathsIII(null));
    }

    @Test
    void testEmptyGrid() {
        assertEquals(0, solution.uniquePathsIII(new int[][]{}));
    }

    @Test
    void testEmptyRow() {
        assertEquals(0, solution.uniquePathsIII(new int[][]{{}}));
    }

    @Test
    void testStartNextToEnd() {
        int[][] grid = {{1, 2}};
        assertEquals(1, solution.uniquePathsIII(grid));
    }

    @Test
    void testAllObstaclesExceptStartEnd() {
        int[][] grid = {{1, -1, 2}};
        assertEquals(0, solution.uniquePathsIII(grid));
    }

    @Test
    void testVerticalPath() {
        int[][] grid = {{1},{0},{0},{2}};
        assertEquals(1, solution.uniquePathsIII(grid));
    }

    @Test
    void testLargerGrid() {
        int[][] grid = {
            {1, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 2}
        };
        assertEquals(4, solution.uniquePathsIII(grid));
    }

    @Test
    void testGiantGrid() {
        // 4x5 grid with no obstacles
        int[][] grid = {
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 2}
        };
        int result = solution.uniquePathsIII(grid);
        assertTrue(result > 0);
    }

    @Test
    void testMultipleObstacles() {
        int[][] grid = {
            {1, 0, 0},
            {0, -1, 0},
            {0, 0, 2}
        };
        int result = solution.uniquePathsIII(grid);
        assertTrue(result >= 0);
    }
}

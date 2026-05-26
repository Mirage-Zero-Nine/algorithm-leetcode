package solutions.backtracking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GetMaximumGold_1219Test {
    private final GetMaximumGold_1219 solution = new GetMaximumGold_1219();

    @Test
    void testBasic() {
        int[][] grid = {{0,6,0},{5,8,7},{0,9,0}};
        assertTrue(solution.getMaximumGold(grid) >= 20);
    }

    @Test
    void testSingleCell() {
        int[][] grid = {{1}};
        assertEquals(1, solution.getMaximumGold(grid));
    }

    @Test
    void testNoGold() {
        int[][] grid = {{0,0},{0,0}};
        assertEquals(0, solution.getMaximumGold(grid));
    }

    @Test
    void testLargerGrid() {
        int[][] grid = {{1,0,7},{2,0,6},{3,4,5},{0,3,0},{9,0,20}};
        assertTrue(solution.getMaximumGold(grid) >= 24);
    }

    @Test
    void testAllGold() {
        int[][] grid = {{1,2},{3,4}};
        assertTrue(solution.getMaximumGold(grid) >= 7);
    }

    @Test
    void testSingleRow() {
        int[][] grid = {{1, 2, 3, 4, 5}};
        assertTrue(solution.getMaximumGold(grid) > 0);
    }

    @Test
    void testSingleColumn() {
        int[][] grid = {{1},{2},{3}};
        assertTrue(solution.getMaximumGold(grid) > 0);
    }

    @Test
    void testDisconnectedCells() {
        int[][] grid = {{1,0,1},{0,0,0},{1,0,1}};
        assertEquals(1, solution.getMaximumGold(grid));
    }

    @Test
    void testAllZeros() {
        int[][] grid = {{0,0,0},{0,0,0}};
        assertEquals(0, solution.getMaximumGold(grid));
    }

    @Test
    void testLargeValues() {
        int[][] grid = {{100, 100},{100, 100}};
        assertTrue(solution.getMaximumGold(grid) >= 300);
    }

    @Test
    void testGiantGrid() {
        int[][] grid = new int[5][5];
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                grid[i][j] = 1;
        assertTrue(solution.getMaximumGold(grid) >= 10);
    }
}

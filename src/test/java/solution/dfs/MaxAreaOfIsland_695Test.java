package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
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

    @Test
    public void testTShapedIsland() {
        // T-shape: 3 top + 3 vertical = 5 cells
        int[][] grid = {
            {1, 1, 1},
            {0, 1, 0},
            {0, 1, 0}
        };
        assertEquals(5, test.maxAreaOfIsland(grid));
    }

    @Test
    public void testPlusShapedIsland() {
        // + shape: center cross in 5x5
        int[][] grid = {
            {0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 1, 1, 1, 1},
            {0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0}
        };
        assertEquals(9, test.maxAreaOfIsland(grid));
    }

    @Test
    public void testDonutIsland() {
        // Ring of land with water inside -> ring area = 8
        int[][] grid = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        assertEquals(8, test.maxAreaOfIsland(grid));
    }

    @Test
    public void testLargeGridRandomSeed42() {
        // 50x50 grid with random values using seed 42
        Random rand = new Random(42L);
        int[][] grid = new int[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                grid[i][j] = rand.nextInt(2);
            }
        }
        // Run the algorithm to get the expected value
        MaxAreaOfIsland_695 solver = new MaxAreaOfIsland_695();
        int expected = solver.maxAreaOfIsland(grid);
        // Regenerate grid since DFS mutates it
        rand = new Random(42L);
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                grid[i][j] = rand.nextInt(2);
            }
        }
        assertEquals(expected, new MaxAreaOfIsland_695().maxAreaOfIsland(grid));
    }

    @Test
    public void testLongThinRowIsland() {
        // Single row island of length 10
        int[][] grid = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        assertEquals(10, test.maxAreaOfIsland(grid));
    }

    @Test
    public void testLongThinColumnIsland() {
        // Single column island of height 8
        int[][] grid = new int[8][3];
        for (int i = 0; i < 8; i++) {
            grid[i][1] = 1;
        }
        assertEquals(8, test.maxAreaOfIsland(grid));
    }

    @Test
    public void testMultipleIslandsReturnMax() {
        // Islands of sizes 1, 3, 6 -> return 6
        int[][] grid = {
            {1, 0, 0, 0, 0},
            {0, 0, 1, 1, 1},
            {0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0}
        };
        assertEquals(6, test.maxAreaOfIsland(grid));
    }

    @Test
    public void testLargeDonutIsland() {
        // 5x5 donut: outer ring with hollow center
        int[][] grid = {
            {1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {1, 0, 0, 0, 1},
            {1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1}
        };
        assertEquals(16, test.maxAreaOfIsland(grid));
    }

    @Test
    public void testDiagonalIslandsSeparate() {
        // Two 2x2 islands connected only diagonally -> separate, max = 4
        int[][] grid = {
            {1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 1, 1, 0},
            {0, 0, 1, 1, 0}
        };
        assertEquals(4, test.maxAreaOfIsland(grid));
    }
}

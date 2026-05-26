package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class NumIslands_200Test {

    private final NumIslands_200 test = new NumIslands_200();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.numIslands(new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}}));
        assertEquals(3, test.numIslands(new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.numIslands(null));
        assertEquals(0, test.numIslands(new char[][]{}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.numIslands(new char[][]{{'1', '1', '1'}, {'1', '1', '1'}, {'1', '1', '1'}}));
    }

    @Test
    public void testAllWater() {
        assertEquals(0, test.numIslands(new char[][]{{'0', '0', '0'}, {'0', '0', '0'}}));
    }

    @Test
    public void testSingleLand() {
        assertEquals(1, test.numIslands(new char[][]{{'1'}}));
    }

    @Test
    public void testSingleWater() {
        assertEquals(0, test.numIslands(new char[][]{{'0'}}));
    }

    @Test
    public void testDiagonalNotConnected() {
        // Diagonal cells are NOT connected
        assertEquals(4, test.numIslands(new char[][]{{'1', '0', '1'}, {'0', '0', '0'}, {'1', '0', '1'}}));
    }

    @Test
    public void testSingleRow() {
        assertEquals(3, test.numIslands(new char[][]{{'1', '0', '1', '1', '0', '1'}}));
    }

    @Test
    public void testSingleColumn() {
        assertEquals(2, test.numIslands(new char[][]{{'1'}, {'0'}, {'1'}, {'1'}}));
    }

    @Test
    public void testGiantGrid() {
        // 100x100 grid with checkerboard pattern
        char[][] grid = new char[100][100];
        int expected = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if ((i + j) % 2 == 0) {
                    grid[i][j] = '1';
                    expected++;
                } else {
                    grid[i][j] = '0';
                }
            }
        }
        // In a checkerboard, no two '1's are adjacent, so each '1' is its own island
        assertEquals(expected, test.numIslands(grid));
    }

    @Test
    public void testUnionFind() {
        assertEquals(1, test.numIslandsUnionFind(new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}}));
        assertEquals(3, test.numIslandsUnionFind(new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}}));
    }

    @Test
    public void testEmptyFirstRow() {
        // grid with empty inner array
        assertEquals(0, test.numIslands(new char[][]{{}}));
    }

    @Test
    public void testPlusShapeIsland() {
        // Plus shape is one connected island
        char[][] grid = {
                {'0', '1', '0'},
                {'1', '1', '1'},
                {'0', '1', '0'}
        };
        assertEquals(1, test.numIslands(grid));
    }

    @Test
    public void testDonutShapeIsland() {
        // Ring of land with water in the middle — still one island
        char[][] grid = {
                {'1', '1', '1'},
                {'1', '0', '1'},
                {'1', '1', '1'}
        };
        assertEquals(1, test.numIslands(grid));
    }

    @Test
    public void testMultipleDisjointSingleCellIslands() {
        // 9 isolated single-cell islands
        char[][] grid = {
                {'1', '0', '1', '0', '1'},
                {'0', '0', '0', '0', '0'},
                {'1', '0', '1', '0', '1'},
                {'0', '0', '0', '0', '0'},
                {'1', '0', '1', '0', '1'}
        };
        assertEquals(9, test.numIslands(grid));
    }

    @Test
    public void testBorderOnlyLand() {
        // Land only on the border, water inside — one connected island
        char[][] grid = {
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '0', '1'},
                {'1', '0', '0', '0', '1'},
                {'1', '0', '0', '0', '1'},
                {'1', '1', '1', '1', '1'}
        };
        assertEquals(1, test.numIslands(grid));
    }

    @Test
    public void testLongThinIslandFullRow() {
        // Single row of all land — one island
        char[][] grid = new char[1][100];
        for (int j = 0; j < 100; j++) {
            grid[0][j] = '1';
        }
        assertEquals(1, test.numIslands(grid));
    }

    @Test
    public void testLargeRandomGridCrossCheckUnionFind() {
        // Generate a 100x100 random grid and cross-check DFS vs union-find
        Random rand = new Random(42L);
        char[][] gridDfs = new char[100][100];
        char[][] gridUf = new char[100][100];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                char c = rand.nextBoolean() ? '1' : '0';
                gridDfs[i][j] = c;
                gridUf[i][j] = c;
            }
        }
        int dfsResult = test.numIslands(gridDfs);
        int ufResult = test.numIslandsUnionFind(gridUf);
        assertEquals(ufResult, dfsResult);
    }

    @Test
    public void testInteriorOnlyLand() {
        // Land only in the interior, border is all water
        char[][] grid = {
                {'0', '0', '0', '0', '0'},
                {'0', '1', '1', '1', '0'},
                {'0', '1', '1', '1', '0'},
                {'0', '1', '1', '1', '0'},
                {'0', '0', '0', '0', '0'}
        };
        assertEquals(1, test.numIslands(grid));
    }

    @Test
    public void testTwoIslandsSeparatedByWaterColumn() {
        // Two islands separated by a column of water
        char[][] grid = {
                {'1', '1', '0', '1', '1'},
                {'1', '1', '0', '1', '1'},
                {'1', '1', '0', '1', '1'}
        };
        assertEquals(2, test.numIslands(grid));
    }

    @Test
    public void testSnakeShapeIsland() {
        // Snake/zigzag pattern — one connected island
        char[][] grid = {
                {'1', '1', '1', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '1', '1', '1'},
                {'0', '0', '0', '0', '1'},
                {'0', '0', '1', '1', '1'}
        };
        assertEquals(1, test.numIslands(grid));
    }
}

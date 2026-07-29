package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class NumIslands_200Test {

    private final NumIslands_200 test = new NumIslands_200();

    @Test
    public void testHappyCases() {
        assertAllImplementations(1, new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}});
        assertAllImplementations(3, new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}});
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertAllImplementations(0, null);
        assertAllImplementations(0, new char[][]{});
    }

    @Test
    public void testAllWater() {
        assertAllImplementations(0, new char[][]{{'0', '0', '0'}, {'0', '0', '0'}});
    }

    @Test
    public void testSingleLand() {
        assertAllImplementations(1, new char[][]{{'1'}});
        assertAllImplementations(1, new char[][]{{'1', '1', '1'}, {'1', '1', '1'}, {'1', '1', '1'}});
    }

    @Test
    public void testSingleWater() {
        assertAllImplementations(0, new char[][]{{'0'}});
    }

    @Test
    public void testDiagonalNotConnected() {
        // Diagonal cells are NOT connected
        assertAllImplementations(4, new char[][]{{'1', '0', '1'}, {'0', '0', '0'}, {'1', '0', '1'}});
    }

    @Test
    public void testSingleRow() {
        assertAllImplementations(3, new char[][]{{'1', '0', '1', '1', '0', '1'}});
    }

    @Test
    public void testSingleColumn() {
        assertAllImplementations(2, new char[][]{{'1'}, {'0'}, {'1'}, {'1'}});
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
        assertAllImplementations(expected, grid);
    }

    @Test
    public void testDenseConnectedGrid() {
        char[][] grid = {
                {'1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '0', '1', '1'},
                {'0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '0'},
                {'1', '0', '1', '1', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '0', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1'},
                {'0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1'},
                {'1', '0', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '0'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'}
        };

        assertAllImplementations(1, grid);
    }

    @Test
    public void testLargeConnectedGrid() {
        // A dense island exercises the worst-case DFS recursion depth.
        char[][] grid = new char[300][300];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = '1';
            }
        }

        assertAllImplementations(1, grid);
    }

    @Test
    public void testConnectedIrregularIsland() {
        assertAllImplementations(1, new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '1', '0'}, {'0', '0', '0', '0', '0'}});
    }

    @Test
    public void testEmptyFirstRow() {
        // grid with empty inner array
        assertAllImplementations(0, new char[][]{{}});
    }

    @Test
    public void testRaggedGridIsRejected() {
        char[][] grid = {{'1', '0'}, {'0'}};

        assertAll(
                () -> assertThrows(ArrayIndexOutOfBoundsException.class, () -> test.numIslands(copyGrid(grid))),
                () -> assertThrows(ArrayIndexOutOfBoundsException.class, () -> test.numIslandsBFS(copyGrid(grid))),
                () -> assertThrows(ArrayIndexOutOfBoundsException.class, () -> test.numIslandsUnionFind(copyGrid(grid)))
        );
    }

    @Test
    public void testPlusShapeIsland() {
        // Plus shape is one connected island
        char[][] grid = {
                {'0', '1', '0'},
                {'1', '1', '1'},
                {'0', '1', '0'}
        };
        assertAllImplementations(1, grid);
    }

    @Test
    public void testDonutShapeIsland() {
        // Ring of land with water in the middle — still one island
        char[][] grid = {
                {'1', '1', '1'},
                {'1', '0', '1'},
                {'1', '1', '1'}
        };
        assertAllImplementations(1, grid);
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
        assertAllImplementations(9, grid);
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
        assertAllImplementations(1, grid);
    }

    @Test
    public void testLongThinIslandFullRow() {
        // Single row of all land — one island
        char[][] grid = new char[1][100];
        for (int j = 0; j < 100; j++) {
            grid[0][j] = '1';
        }
        assertAllImplementations(1, grid);
    }

    @Test
    public void testLargeRandomGrid() {
        // Generate a deterministic 100x100 random grid.
        Random rand = new Random(42L);
        char[][] grid = new char[100][100];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                grid[i][j] = rand.nextBoolean() ? '1' : '0';
            }
        }

        assertAllImplementations(641, grid);
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
        assertAllImplementations(1, grid);
    }

    @Test
    public void testTwoIslandsSeparatedByWaterColumn() {
        // Two islands separated by a column of water
        char[][] grid = {
                {'1', '1', '0', '1', '1'},
                {'1', '1', '0', '1', '1'},
                {'1', '1', '0', '1', '1'}
        };
        assertAllImplementations(2, grid);
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
        assertAllImplementations(1, grid);
    }

    private void assertAllImplementations(int expected, char[][] grid) {
        assertAll(
                () -> assertEquals(expected, test.numIslands(copyGrid(grid))),
                () -> assertEquals(expected, test.numIslandsBFS(copyGrid(grid))),
                () -> assertEquals(expected, test.numIslandsUnionFind(copyGrid(grid)))
        );
    }

    private char[][] copyGrid(char[][] grid) {
        if (grid == null) {
            return null;
        }

        char[][] copy = new char[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            copy[i] = grid[i] == null ? null : grid[i].clone();
        }
        return copy;
    }
}

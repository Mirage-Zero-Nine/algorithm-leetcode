package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayDeque;
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
    public void testAllThreeByThreeBinaryGrids() {
        // Exhaust every legal binary 3x3 grid. Each implementation gets its own
        // fresh grid because the DFS and BFS implementations mark visited land.
        for (int mask = 0; mask < (1 << 9); mask++) {
            char[][] grid = gridFromMask(mask);
            assertAllImplementations(countIslandsWithoutMutation(grid), grid);
        }
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

    private char[][] gridFromMask(int mask) {
        char[][] grid = new char[3][3];
        for (int cell = 0; cell < 9; cell++) {
            grid[cell / 3][cell % 3] = (mask & (1 << cell)) == 0 ? '0' : '1';
        }
        return grid;
    }

    private int countIslandsWithoutMutation(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int islands = 0;
        int[] rowDelta = {-1, 1, 0, 0};
        int[] columnDelta = {0, 0, -1, 1};

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                if (grid[row][column] != '1' || visited[row][column]) {
                    continue;
                }

                islands++;
                ArrayDeque<int[]> queue = new ArrayDeque<>();
                queue.add(new int[]{row, column});
                visited[row][column] = true;
                while (!queue.isEmpty()) {
                    int[] cell = queue.remove();
                    for (int direction = 0; direction < 4; direction++) {
                        int nextRow = cell[0] + rowDelta[direction];
                        int nextColumn = cell[1] + columnDelta[direction];
                        if (nextRow >= 0 && nextRow < grid.length
                                && nextColumn >= 0 && nextColumn < grid[nextRow].length
                                && grid[nextRow][nextColumn] == '1'
                                && !visited[nextRow][nextColumn]) {
                            visited[nextRow][nextColumn] = true;
                            queue.add(new int[]{nextRow, nextColumn});
                        }
                    }
                }
            }
        }
        return islands;
    }
}

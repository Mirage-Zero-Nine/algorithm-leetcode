package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}

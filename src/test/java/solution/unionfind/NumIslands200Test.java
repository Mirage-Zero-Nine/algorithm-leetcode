package solution.unionfind;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import solution.dfs.NumIslands_200;

/**
 * @author BorisMirage
 * Time: 2023/04/16 23:24
 * Created with IntelliJ IDEA
 */

public class NumIslands200Test {
    private final NumIslands_200 test = new NumIslands_200();

    @Test
    public void test() {
        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        assertEquals(1, test.numIslands(grid1));

        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        assertEquals(3, test.numIslands(grid2));
    }

    @Test
    public void testBfs() {
        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        assertEquals(1, test.numIslandsBFS(grid1));

        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        assertEquals(3, test.numIslandsBFS(grid2));
    }


    @Test
    public void testUf() {
        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        assertEquals(1, test.numIslandsUnionFind(grid1));

        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        assertEquals(3, test.numIslandsUnionFind(grid2));
        char[][] grid3 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'0', '0', '0', '0', '0'}
        };
        assertEquals(1, test.numIslandsUnionFind(grid3));
    }

    @Test
    public void testNumIslandsNullInput() {
        assertEquals(0, test.numIslands(null));
        assertEquals(0, test.numIslandsBFS(null));
        assertEquals(0, test.numIslandsUnionFind(null));

    }

    @Test
    public void testNumIslandsEmptyInput() {
        char[][] grid = new char[][]{};
        assertEquals(0, test.numIslands(grid));
        assertEquals(0, test.numIslandsBFS(grid));
        assertEquals(0, test.numIslandsUnionFind(grid));

    }

    @Test
    public void testNumIslandsInvalidInput1() {
        char[][] grid = new char[][]{{'1', '0'}, {'0'}};
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> test.numIslands(grid));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> test.numIslandsUnionFind(grid));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> test.numIslandsBFS(grid));
    }

    @Test
    public void testNumIslandsNoIslands() {
        char[][] grid = new char[][]{{'0', '0', '0'}, {'0', '0', '0'}, {'0', '0', '0'}};
        assertEquals(0, test.numIslands(grid));
        assertEquals(0, test.numIslandsUnionFind(grid));
        assertEquals(0, test.numIslandsBFS(grid));

    }
}
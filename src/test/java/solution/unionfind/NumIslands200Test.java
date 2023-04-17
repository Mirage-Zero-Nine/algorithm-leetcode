package solution.unionfind;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2023/04/16 23:24
 * Created with IntelliJ IDEA
 */

public class NumIslands200Test {
    private final NumIslands_200 solution = new NumIslands_200();

    @Test
    public void test() {
        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        assertEquals(1, solution.numIslands(grid1));

        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        assertEquals(3, solution.numIslands(grid2));
    }

    @Test
    public void testUf1(){

        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };

        assertEquals(1, solution.numIslandsUnionFind(grid1));
    }

    @Test
    public void testUf2(){
        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        assertEquals(3, solution.numIslandsUnionFind(grid2));
    }

    @Test
    public void testNumIslandsNullInput() {
        assertEquals(0, solution.numIslands(null));
    }

    @Test
    public void testNumIslandsEmptyInput() {
        char[][] grid = new char[][]{};
        assertEquals(0, solution.numIslands(grid));
    }

    @Test
    public void testNumIslandsInvalidInput1() {
        char[][] grid = new char[][]{{'1', '0'}, {'0'}};
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> solution.numIslands(grid));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> solution.numIslandsUnionFind(grid));
    }

    @Test
    public void testNumIslandsNoIslands() {
        NumIslands_200 numIslands = new NumIslands_200();
        char[][] grid = new char[][]{{'0', '0', '0'}, {'0', '0', '0'}, {'0', '0', '0'}};
        assertEquals(0, numIslands.numIslands(grid));
    }

    @Test
    public void testNumIslandsOneIsland() {
        NumIslands_200 numIslands = new NumIslands_200();
        char[][] grid = new char[][]{{'1', '1', '0'}, {'1', '1', '0'}, {'0', '0', '1'}};
        assertEquals(2, numIslands.numIslands(grid));
    }
}
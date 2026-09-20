package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NumDistinctIslands_694Test {

    @Test
    public void testHappyCases() {
        assertEquals(3, new NumDistinctIslands_694().numDistinctIslands(new int[][]{{1, 1, 0, 1, 1}, {1, 0, 0, 0, 0}, {0, 0, 0, 0, 1}, {1, 1, 0, 1, 1}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, new NumDistinctIslands_694().numDistinctIslands(new int[][]{{0, 0}, {0, 0}}));
        assertEquals(1, new NumDistinctIslands_694().numDistinctIslands(new int[][]{{1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, new NumDistinctIslands_694().numDistinctIslands(new int[][]{{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}}));
    }

    @Test
    public void testAllOnes() {
        assertEquals(1, new NumDistinctIslands_694().numDistinctIslands(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}));
    }

    @Test
    public void testAllZeros() {
        assertEquals(0, new NumDistinctIslands_694().numDistinctIslands(new int[][]{{0, 0, 0}, {0, 0, 0}}));
    }

    @Test
    public void testSingleCellIslands() {
        // All isolated single cells are the same shape
        assertEquals(1, new NumDistinctIslands_694().numDistinctIslands(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}));
    }

    @Test
    public void testTwoDistinctShapes() {
        // L-shape and straight line
        assertEquals(2, new NumDistinctIslands_694().numDistinctIslands(new int[][]{
                {1, 1, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        }));
    }

    @Test
    public void testFromMainMethod1() {
        assertEquals(2, new NumDistinctIslands_694().numDistinctIslands(new int[][]{
                {1, 1, 0},
                {0, 1, 1},
                {0, 0, 0},
                {1, 1, 1},
                {0, 1, 0}
        }));
    }

    @Test
    public void testFromMainMethod2() {
        assertEquals(2, new NumDistinctIslands_694().numDistinctIslands(new int[][]{
                {0, 0, 1},
                {0, 0, 1},
                {1, 1, 0}
        }));
    }

    @Test
    public void testSingleRow() {
        // Two separate horizontal islands of same length
        assertEquals(1, new NumDistinctIslands_694().numDistinctIslands(new int[][]{{1, 1, 0, 1, 1}}));
    }

    @Test
    public void testSingleColumn() {
        assertEquals(1, new NumDistinctIslands_694().numDistinctIslands(new int[][]{{1}, {1}, {0}, {1}, {1}}));
    }

    @Test
    public void testGiantGrid() {
        assertEquals(15, new NumDistinctIslands_694().numDistinctIslands(new int[][]{
                {0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0},
                {0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0},
                {1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}
        }));
    }

    @ParameterizedTest(name = "solid island side {0}")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    public void testSolidIslandSizes(int side) {
        int[][] grid = new int[side][side];
        for (int i = 0; i < side; i++) java.util.Arrays.fill(grid[i], 1);
        assertEquals(1, new NumDistinctIslands_694().numDistinctIslands(grid));
    }
}

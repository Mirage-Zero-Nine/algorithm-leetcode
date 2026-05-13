package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
}

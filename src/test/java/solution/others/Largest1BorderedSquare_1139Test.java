package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Largest1BorderedSquare_1139Test {

    private final Largest1BorderedSquare_1139 test = new Largest1BorderedSquare_1139();

    @Test
    public void testHappyCases() {
        assertEquals(9, test.largest1BorderedSquare(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}));
        assertEquals(1, test.largest1BorderedSquare(new int[][]{{1, 1, 0, 0}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.largest1BorderedSquare(new int[][]{{0}}));
        assertEquals(1, test.largest1BorderedSquare(new int[][]{{1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.largest1BorderedSquare(new int[][]{{1, 1, 1, 1}, {1, 0, 0, 1}, {1, 1, 1, 1}}));
    }

    @Test
    public void testAllZeros() {
        assertEquals(0, test.largest1BorderedSquare(new int[][]{{0, 0}, {0, 0}}));
    }

    @Test
    public void testAllOnes2x2() {
        assertEquals(4, test.largest1BorderedSquare(new int[][]{{1, 1}, {1, 1}}));
    }

    @Test
    public void test4x4AllOnes() {
        assertEquals(16, test.largest1BorderedSquare(new int[][]{{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}}));
    }

    @Test
    public void testSingleRow() {
        assertEquals(1, test.largest1BorderedSquare(new int[][]{{1, 0, 1, 1, 1}}));
    }

    @Test
    public void testSingleColumn() {
        assertEquals(1, test.largest1BorderedSquare(new int[][]{{1}, {0}, {1}, {1}}));
    }

    @Test
    public void testNoSquarePossible() {
        assertEquals(1, test.largest1BorderedSquare(new int[][]{{1, 0, 1}, {0, 1, 0}, {1, 0, 1}}));
    }

    @Test
    public void testGiantGrid() {
        int n = 50;
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = 1;
        assertEquals(n * n, test.largest1BorderedSquare(grid));
    }
}

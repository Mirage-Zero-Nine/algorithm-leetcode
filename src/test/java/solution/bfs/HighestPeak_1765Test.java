package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class HighestPeak_1765Test {

    private final HighestPeak_1765 test = new HighestPeak_1765();

    @Test
    public void testHappyCases() {
        int[][] result = test.highestPeak(new int[][]{{0, 1}, {0, 0}});
        assertEquals(0, result[0][1]);
        assertEquals(1, result[0][0]);
    }

    @Test
    public void testEdgeCases() {
        int[][] result = test.highestPeak(new int[][]{{1}});
        assertEquals(0, result[0][0]);
    }

    @Test
    public void testLargeCase() {
        int[][] result = test.highestPeak(new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}});
        assertEquals(0, result[0][0]);
        assertEquals(4, result[2][2]);
    }

    @Test
    public void testAllWaterGrid() {
        int[][] isWater = new int[][]{{1, 1}, {1, 1}};
        int[][] result = test.highestPeak(isWater);
        assertEquals(0, result[0][0]);
        assertEquals(0, result[1][1]);
        assertValidResult(new int[][]{{1, 1}, {1, 1}}, result);
    }

    @Test
    public void testSingleRowWithOneWater() {
        int[][] original = new int[][]{{0, 0, 1, 0, 0}};
        int[][] result = test.highestPeak(copy(original));
        assertEquals(2, result[0][0]);
        assertEquals(0, result[0][2]);
        assertEquals(2, result[0][4]);
        assertValidResult(original, result);
    }

    @Test
    public void testSingleColumnWithOneWater() {
        int[][] original = new int[][]{{0}, {1}, {0}, {0}};
        int[][] result = test.highestPeak(copy(original));
        assertEquals(1, result[0][0]);
        assertEquals(0, result[1][0]);
        assertEquals(2, result[3][0]);
        assertValidResult(original, result);
    }

    @Test
    public void testTwoWaterSources() {
        int[][] original = new int[][]{
                {1, 0, 0},
                {0, 0, 0},
                {0, 0, 1}
        };
        int[][] result = test.highestPeak(copy(original));
        assertEquals(0, result[0][0]);
        assertEquals(0, result[2][2]);
        assertEquals(2, result[1][1]);
        assertValidResult(original, result);
    }

    @Test
    public void testNegativeCaseNoWaterReturnsAllMinusOneByCurrentImplementation() {
        int[][] result = test.highestPeak(new int[][]{{0, 0}, {0, 0}});
        assertEquals(-1, result[0][0]);
        assertEquals(-1, result[1][1]);
    }

    @Test
    public void testNonSquareGrid() {
        int[][] original = new int[][]{
                {0, 1, 0, 0},
                {0, 0, 0, 1}
        };
        int[][] result = test.highestPeak(copy(original));
        assertValidResult(original, result);
    }

    @Test
    public void testGiantGrid() {
        int m = 40, n = 40;
        int[][] original = new int[m][n];
        original[0][0] = 1;
        original[m - 1][n - 1] = 1;
        int[][] result = test.highestPeak(copy(original));
        assertValidResult(original, result);
        assertEquals(0, result[0][0]);
        assertEquals(0, result[m - 1][n - 1]);
    }

    private int[][] copy(int[][] grid) {
        int[][] out = new int[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            out[i] = grid[i].clone();
        }
        return out;
    }

    private void assertValidResult(int[][] original, int[][] result) {
        int m = original.length, n = original[0].length;
        int[] d = new int[]{0, 1, 0, -1, 0};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (original[i][j] == 1) {
                    assertEquals(0, result[i][j]);
                } else {
                    assertTrue(result[i][j] >= 0);
                }
                for (int k = 0; k < 4; k++) {
                    int ni = i + d[k], nj = j + d[k + 1];
                    if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                        assertTrue(Math.abs(result[i][j] - result[ni][nj]) <= 1);
                    }
                }
            }
        }
    }
}

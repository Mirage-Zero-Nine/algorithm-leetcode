package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;

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

    @Test
    public void testSingleWaterCell() {
        int[][] original = {{1}};
        assertExactHeights(original);
    }

    @Test
    public void testSingleRowAllWater() {
        assertExactHeights(new int[][]{{1, 1, 1, 1, 1, 1}});
    }

    @Test
    public void testSingleColumnAllWater() {
        assertExactHeights(new int[][]{{1}, {1}, {1}, {1}, {1}});
    }

    @Test
    public void testSingleRowWaterInTheMiddle() {
        assertExactHeights(new int[][]{{0, 0, 1, 0, 0, 0, 0}});
    }

    @Test
    public void testSingleColumnWaterInTheMiddle() {
        assertExactHeights(new int[][]{{0}, {0}, {0}, {1}, {0}, {0}});
    }

    @Test
    public void testTwoByTwoCornerSource() {
        assertExactHeights(new int[][]{{1, 0}, {0, 0}});
    }

    @Test
    public void testThreeByThreeCenterSource() {
        assertExactHeights(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}});
    }

    @Test
    public void testThreeByThreeOppositeSources() {
        assertExactHeights(new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 1}});
    }

    @Test
    public void testOfficialSecondExample() {
        assertExactHeights(new int[][]{
                {0, 0, 1},
                {1, 0, 0},
                {0, 0, 0}
        });
    }

    @Test
    public void testTieBetweenTwoWaterFronts() {
        assertExactHeights(new int[][]{{1, 0, 0, 0, 1}});
    }

    @Test
    public void testRectangularTwoByFiveGrid() {
        assertExactHeights(new int[][]{{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}});
    }

    @Test
    public void testRectangularFiveByTwoGrid() {
        assertExactHeights(new int[][]{{0, 1}, {0, 0}, {0, 0}, {1, 0}, {0, 0}});
    }

    @Test
    public void testAllWaterFourByFour() {
        int[][] grid = new int[4][4];
        for (int[] row : grid) {
            Arrays.fill(row, 1);
        }
        assertExactHeights(grid);
    }

    @Test
    public void testCheckerboardWater() {
        assertExactHeights(new int[][]{
                {1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0}
        });
    }

    @Test
    public void testWaterOnTheBorder() {
        assertExactHeights(new int[][]{
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        });
    }

    @Test
    public void testIsolatedCenterSourceHasExpectedPeak() {
        int[][] grid = new int[7][7];
        grid[3][3] = 1;
        int[][] result = test.highestPeak(copy(grid));
        assertExactHeights(grid, result);
        assertEquals(6, result[0][0]);
        assertEquals(6, result[6][6]);
    }

    @Test
    public void testThreeSourcesCreateCompetingDistanceFronts() {
        assertExactHeights(new int[][]{
                {1, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        });
    }

    @Test
    public void testExhaustiveTwoByThreeWaterPlacements() {
        for (int mask = 1; mask < (1 << 6); mask++) {
            int[][] grid = new int[2][3];
            for (int cell = 0; cell < 6; cell++) {
                grid[cell / 3][cell % 3] = (mask >>> cell) & 1;
            }
            assertExactHeights(grid);
        }
    }

    @Test
    public void testSeededSmallGridsAgainstDistanceOracle() {
        Random random = new Random(1765L);
        for (int sample = 0; sample < 40; sample++) {
            int rows = 1 + random.nextInt(6);
            int columns = 1 + random.nextInt(6);
            int[][] grid = new int[rows][columns];
            boolean hasWater = false;
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    grid[row][column] = random.nextInt(4) == 0 ? 1 : 0;
                    hasWater |= grid[row][column] == 1;
                }
            }
            if (!hasWater) {
                grid[random.nextInt(rows)][random.nextInt(columns)] = 1;
            }
            assertExactHeights(grid);
        }
    }

    @Test
    public void testMaximumDimensionGrid() {
        int[][] grid = new int[1][1000];
        grid[0][0] = 1;
        int[][] result = test.highestPeak(copy(grid));
        assertExactHeights(grid, result);
        assertEquals(999, result[0][999]);
    }

    @Test
    public void testMillionCellBoundaryGrid() {
        int size = 1000;
        int[][] original = new int[size][size];
        original[0][0] = 1;
        original[size - 1][size - 1] = 1;
        int[][] result = test.highestPeak(copy(original));
        assertEquals(0, result[0][0]);
        assertEquals(0, result[size - 1][size - 1]);
        assertEquals(499, result[0][size / 2 - 1]);
        assertEquals(499, result[size / 2 - 1][0]);
        assertEquals(998, result[size / 2 - 1][size / 2 - 1]);
        assertTrue(result[size / 2][size / 2] >= 0);
        assertGridProperties(original, result);
        assertEquals(size, result.length);
        assertEquals(size, result[0].length);
    }

    @Test
    public void testRepeatedCallsOnSameInstanceUseFreshInput() {
        int[][] first = {{1, 0, 0}, {0, 0, 0}};
        int[][] second = {{0, 0, 1, 0}};
        assertExactHeights(first, test.highestPeak(copy(first)));
        assertExactHeights(second, test.highestPeak(copy(second)));
    }

    @Test
    public void testReturnedGridIsTheMutatedInputByImplementationDesign() {
        int[][] input = {{0, 1}, {0, 0}};
        int[][] result = test.highestPeak(input);
        assertTrue(result == input);
        assertExactHeights(new int[][]{{0, 1}, {0, 0}}, result);
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

    /**
     * Computes the independent optimum: each cell's distance to its nearest water cell.
     * Any valid assignment is bounded by this distance along a shortest path to water,
     * and these distances satisfy all local constraints, so they are the maximal heights.
     */
    private int[][] expectedHeights(int[][] original) {
        int m = original.length, n = original[0].length;
        int[][] expected = new int[m][n];
        for (int row = 0; row < m; row++) {
            for (int column = 0; column < n; column++) {
                if (original[row][column] == 1) {
                    continue;
                }
                int nearest = Integer.MAX_VALUE;
                for (int waterRow = 0; waterRow < m; waterRow++) {
                    for (int waterColumn = 0; waterColumn < n; waterColumn++) {
                        if (original[waterRow][waterColumn] == 1) {
                            nearest = Math.min(nearest,
                                    Math.abs(row - waterRow) + Math.abs(column - waterColumn));
                        }
                    }
                }
                expected[row][column] = nearest;
            }
        }
        return expected;
    }

    private void assertExactHeights(int[][] original) {
        assertExactHeights(original, test.highestPeak(copy(original)));
    }

    private void assertExactHeights(int[][] original, int[][] result) {
        int[][] expected = expectedHeights(original);
        for (int row = 0; row < original.length; row++) {
            assertEquals(original[row].length, result[row].length);
            for (int column = 0; column < original[row].length; column++) {
                assertEquals(expected[row][column], result[row][column],
                        "wrong height at [" + row + "][" + column + "]");
            }
        }
        assertGridProperties(original, result);
    }

    private void assertGridProperties(int[][] original, int[][] result) {
        int[] direction = {0, 1, 0, -1, 0};
        for (int row = 0; row < result.length; row++) {
            for (int column = 0; column < result[row].length; column++) {
                assertTrue(result[row][column] >= 0);
                if (row < original.length && column < original[row].length && original[row][column] == 1) {
                    assertEquals(0, result[row][column]);
                }
                for (int d = 0; d < 4; d++) {
                    int neighborRow = row + direction[d];
                    int neighborColumn = column + direction[d + 1];
                    if (neighborRow >= 0 && neighborRow < result.length
                            && neighborColumn >= 0 && neighborColumn < result[row].length) {
                        assertTrue(Math.abs(result[row][column] - result[neighborRow][neighborColumn]) <= 1);
                    }
                }
            }
        }
    }
}

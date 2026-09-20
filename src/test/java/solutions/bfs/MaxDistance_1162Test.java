package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class MaxDistance_1162Test {

    private final MaxDistance_1162 test = new MaxDistance_1162();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.maxDistance(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}));
        assertEquals(4, test.maxDistance(new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.maxDistance(new int[][]{{1}}));
        assertEquals(-1, test.maxDistance(new int[][]{{1, 1}, {1, 1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.maxDistance(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 1}}));
    }

    @Test
    public void testAllWaterReturnsMinusOne() {
        assertEquals(-1, test.maxDistance(new int[][]{{0, 0}, {0, 0}}));
    }

    @Test
    public void testSingleLandInCenter() {
        assertEquals(2, test.maxDistance(new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        }));
    }

    @Test
    public void testSingleLandAtCorner() {
        assertEquals(4, test.maxDistance(new int[][]{
                {1, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        }));
    }

    @Test
    public void testMultipleLandsReduceDistance() {
        assertEquals(2, test.maxDistance(new int[][]{
                {1, 0, 1},
                {0, 0, 0},
                {1, 0, 1}
        }));
    }

    @Test
    public void testLineOfLandOnOneSide() {
        assertEquals(3, test.maxDistance(new int[][]{
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        }));
    }

    @Test
    public void testCheckerboardHasDistanceOne() {
        assertEquals(1, test.maxDistance(new int[][]{
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 1}
        }));
    }

    @Test
    public void testGiantGrid() {
        int n = 50;
        int[][] grid = new int[n][n];
        grid[0][0] = 1;
        assertEquals(98, test.maxDistance(grid));
    }

    @Test
    public void oneByOneWaterHasNoLand() {
        assertDistance(-1, new int[][]{{0}});
    }

    @Test
    public void twoByTwoSingleCornerHasDistanceTwo() {
        assertDistance(2, new int[][]{{1, 0}, {0, 0}});
    }

    @Test
    public void twoByTwoDiagonalLandsHaveDistanceOne() {
        assertDistance(1, new int[][]{{1, 0}, {0, 1}});
    }

    @Test
    public void oppositeCornerLandsHaveTwoTiedFarthestCells() {
        int[][] grid = {
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 1}
        };
        assertDistance(3, grid);
        assertEquals(3, bruteForce(grid));
    }

    @Test
    public void aLandRingLeavesCenterAtDistanceTwo() {
        assertDistance(2, new int[][]{
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        });
    }

    @Test
    public void twoLandRowsLeaveAThreeRowWaterBand() {
        assertDistance(3, new int[][]{
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        });
    }

    @Test
    public void oneLandAtCornerScalesWithGridSize() {
        int n = 10;
        int[][] grid = new int[n][n];
        grid[0][0] = 1;
        assertDistance(18, grid);
    }

    @Test
    public void centerLandWithFourCornerWaterRegions() {
        int[][] grid = new int[7][7];
        grid[3][3] = 1;
        assertDistance(6, grid);
    }

    @Test
    public void sparseSymmetricLandsUseTheExactNearestDistance() {
        assertDistance(5, new int[][]{
                {0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0}
        });
    }

    @Test
    public void everyWaterCellCanBeAdjacentToLand() {
        assertDistance(1, new int[][]{
                {1, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 1, 1},
                {1, 0, 1, 0}
        });
    }

    @Test
    public void inputGridIsNotMutated() {
        int[][] grid = {
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 0}
        };
        int[][] before = copy(grid);
        assertEquals(bruteForce(grid), test.maxDistance(grid));
        assertTrue(Arrays.deepEquals(before, grid));
    }

    @Test
    public void repeatedCallsDoNotLeakQueueOrVisitedState() {
        MaxDistance_1162 solution = new MaxDistance_1162();
        int[][] first = {{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int[][] second = {{1, 0}, {0, 1}};
        assertEquals(4, solution.maxDistance(first));
        assertEquals(1, solution.maxDistance(second));
        assertEquals(4, solution.maxDistance(first));
    }

    @Test
    public void everyThreeByThreeBinaryGridMatchesIndependentOracle() {
        for (int mask = 0; mask < (1 << 9); mask++) {
            int[][] grid = fromMask(3, mask);
            assertEquals(bruteForce(grid), new MaxDistance_1162().maxDistance(grid),
                    "mask=" + mask);
        }
    }

    @Test
    public void seededSmallGridsMatchIndependentOracle() {
        Random random = new Random(1162L);
        for (int sample = 0; sample < 120; sample++) {
            int n = 1 + random.nextInt(7);
            int[][] grid = new int[n][n];
            for (int row = 0; row < n; row++) {
                for (int column = 0; column < n; column++) {
                    grid[row][column] = random.nextBoolean() ? 1 : 0;
                }
            }
            assertEquals(bruteForce(grid), new MaxDistance_1162().maxDistance(grid),
                    "sample=" + sample + ", n=" + n);
        }
    }

    @Test
    public void minimumNontrivialGridMatchesOracle() {
        int[][] grid = {{1, 0}, {0, 0}};
        assertDistance(bruteForce(grid), grid);
    }

    @Test
    public void maximumSupportedGridSingleCornerLand() {
        int n = 100;
        int[][] grid = new int[n][n];
        grid[0][0] = 1;
        assertDistance(198, grid);
    }

    @Test
    public void maximumSupportedGridWithCentralLandMatchesOracle() {
        int n = 100;
        int[][] grid = new int[n][n];
        grid[n / 2][n / 2] = 1;
        assertDistance(100, grid);
    }

    @Test
    public void maximumSupportedCheckerboardHasDistanceOne() {
        int n = 100;
        int[][] grid = new int[n][n];
        for (int row = 0; row < n; row++) {
            for (int column = 0; column < n; column++) {
                grid[row][column] = (row + column) % 2;
            }
        }
        assertDistance(1, grid);
    }

    @Test
    public void maximumSupportedGridBoundaryPatternMatchesOracle() {
        int n = 100;
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            grid[0][i] = 1;
            grid[n - 1][i] = 1;
            grid[i][0] = 1;
            grid[i][n - 1] = 1;
        }
        assertDistance(49, grid);
    }

    private void assertDistance(int expected, int[][] grid) {
        int[][] freshInput = copy(grid);
        assertEquals(expected, new MaxDistance_1162().maxDistance(freshInput));
    }

    /** Directly computes max over water cells of their nearest land Manhattan distance. */
    private static int bruteForce(int[][] grid) {
        int n = grid.length;
        int best = -1;
        boolean hasLand = false;
        boolean hasWater = false;
        for (int row = 0; row < n; row++) {
            for (int column = 0; column < n; column++) {
                if (grid[row][column] == 1) {
                    hasLand = true;
                } else {
                    hasWater = true;
                }
            }
        }
        if (!hasLand || !hasWater) {
            return -1;
        }
        for (int waterRow = 0; waterRow < n; waterRow++) {
            for (int waterColumn = 0; waterColumn < n; waterColumn++) {
                if (grid[waterRow][waterColumn] != 0) {
                    continue;
                }
                int nearest = Integer.MAX_VALUE;
                for (int landRow = 0; landRow < n; landRow++) {
                    for (int landColumn = 0; landColumn < n; landColumn++) {
                        if (grid[landRow][landColumn] == 1) {
                            nearest = Math.min(nearest,
                                    Math.abs(waterRow - landRow) + Math.abs(waterColumn - landColumn));
                        }
                    }
                }
                best = Math.max(best, nearest);
            }
        }
        return best;
    }

    private static int[][] fromMask(int n, int mask) {
        int[][] grid = new int[n][n];
        for (int cell = 0; cell < n * n; cell++) {
            grid[cell / n][cell % n] = (mask >>> cell) & 1;
        }
        return grid;
    }

    private static int[][] copy(int[][] grid) {
        int[][] result = new int[grid.length][];
        for (int row = 0; row < grid.length; row++) {
            result[row] = grid[row].clone();
        }
        return result;
    }
}

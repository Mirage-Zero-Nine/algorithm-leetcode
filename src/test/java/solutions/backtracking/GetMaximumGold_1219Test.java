package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Contract and edge-case tests for the path-with-maximum-gold problem. */
class GetMaximumGold_1219Test {
    @Test
    void testBasic() {
        assertGold(24, new int[][]{{0, 6, 0}, {5, 8, 7}, {0, 9, 0}});
    }

    @Test
    void testLargerGrid() {
        assertGold(28, new int[][]{{1, 0, 7}, {2, 0, 6}, {3, 4, 5}, {0, 3, 0}, {9, 0, 20}});
    }

    @Test
    void testSingleCell() {
        assertGold(1, new int[][]{{1}});
    }

    @Test
    void oneHundredIsMaximumCellValue() {
        assertGold(100, new int[][]{{100}});
    }

    @Test
    void testNoGold() {
        assertGold(0, new int[][]{{0, 0}, {0, 0}});
    }

    @Test
    void isolatedGoldCells() {
        assertGold(7, new int[][]{{1, 0, 7}, {0, 0, 0}, {5, 0, 2}});
    }

    @Test
    void testSingleRow() {
        assertGold(15, new int[][]{{1, 2, 3, 4, 5}});
    }

    @Test
    void testSingleColumn() {
        assertGold(6, new int[][]{{1}, {2}, {3}});
    }

    @Test
    void testAllGold() {
        assertGold(10, new int[][]{{1, 2}, {3, 4}});
    }

    @Test
    void branchChoosesHigherWeightedRoute() {
        assertGold(24, new int[][]{{1, 10, 1}, {1, 1, 10}});
    }

    @Test
    void deadEndCanBeStartedAtEitherEnd() {
        assertGold(15, new int[][]{{9, 1, 2, 3}});
    }

    @Test
    void cycleCannotReuseTheStartingCell() {
        assertGold(10, new int[][]{{1, 2}, {3, 4}});
    }

    @Test
    void zeroCellsSplitComponents() {
        assertGold(12, new int[][]{{5, 6, 0, 7}, {0, 0, 0, 5}});
    }

    @Test
    void testDisconnectedCells() {
        assertGold(1, new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}});
    }

    @Test
    void twoByThreeWithOnlyOneGoldCell() {
        assertGold(1, new int[][]{{0, 0, 0}, {0, 1, 0}});
    }

    @Test
    void twoByThreeAllZeros() {
        assertGold(0, new int[][]{{0, 0, 0}, {0, 0, 0}});
    }

    @Test
    void disconnectedComponentWithLargestSingleCellWins() {
        assertGold(100, new int[][]{{2, 2, 0}, {2, 0, 0}, {0, 0, 100}});
    }

    @Test
    void weightedForkDoesNotRequireVisitingEveryGoldCell() {
        assertGold(105, new int[][]{{1, 100, 1}, {1, 1, 1}});
    }

    @Test
    void allGoldThreeByThreeHasHamiltonianPath() {
        assertGold(9, new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}});
    }

    @Test
    void testLargeValues() {
        assertGold(400, new int[][]{{100, 100}, {100, 100}});
    }

    @Test
    void narrowTurnedCorridor() {
        assertGold(31, new int[][]{{1, 2, 0}, {0, 3, 4}, {8, 7, 6}});
    }

    @Test
    void multipleStartsInOneComponent() {
        assertGold(21, new int[][]{{10, 1, 10}, {0, 9, 0}});
    }

    @Test
    void testGiantGrid() {
        int[][] grid = new int[5][5];
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                grid[row][column] = 1;
            }
        }
        assertGold(25, grid);
    }

    @Test
    void repeatedCallUsesFreshMaximum() {
        GetMaximumGold_1219 solution = new GetMaximumGold_1219();
        assertEquals(9, solution.getMaximumGold(new int[][]{{4, 5}}));
        assertEquals(2, solution.getMaximumGold(new int[][]{{2}}));
    }

    @Test
    void inputGridIsNotMutated() {
        int[][] grid = {{0, 6, 0}, {5, 8, 7}, {0, 9, 0}};
        int[][] before = deepCopy(grid);
        assertEquals(24, new GetMaximumGold_1219().getMaximumGold(grid));
        assertTrue(Arrays.deepEquals(before, grid));
    }

    @Test
    void safeMaximumDimensionGridWithTwentyFiveGoldCells() {
        int[][] grid = new int[15][15];
        for (int column = 0; column < 25; column++) {
            grid[0][column % 15] = 1;
            if (column >= 15) grid[1][column - 15] = 1;
        }
        assertGold(25, grid);
    }

    @Test
    void maximumGoldValueAndCountWithinOfficialBounds() {
        int[][] grid = new int[15][15];
        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 5; column++) {
                grid[row][column] = 100;
            }
        }
        assertGold(2_500, grid);
    }

    @Test
    void safeMaximumDimensionGridWithIsolatedGold() {
        int[][] grid = new int[15][15];
        for (int row = 0; row < 5; row++) for (int column = 0; column < 5; column++) {
            if ((row + column) % 2 == 0) grid[row * 3][column * 3] = 100;
        }
        assertGold(100, grid);
    }

    @Test
    void smallGraphsAgreeWithIndependentPathOracle() {
        int[][][] grids = {
                {{1, 0, 2}, {3, 4, 0}}, {{0, 2, 3}, {4, 0, 5}},
                {{2, 1, 0}, {0, 3, 4}, {5, 0, 6}}, {{1, 2, 3}, {0, 4, 0}, {5, 6, 7}},
                {{8, 0, 1}, {2, 3, 0}, {0, 4, 5}}, {{1, 9}, {8, 2}, {3, 4}}
        };
        for (int[][] grid : grids) assertGold(independentOracle(grid), grid);
    }

    @Test
    void exhaustiveThreeByThreeOccupanciesAgreeWithIndependentPathOracle() {
        for (int mask = 0; mask < (1 << 9); mask++) {
            int[][] grid = new int[3][3];
            for (int cell = 0; cell < 9; cell++) {
                if ((mask & (1 << cell)) != 0) {
                    grid[cell / 3][cell % 3] = cell + 1;
                }
            }
            assertGold(independentOracle(grid), grid);
        }
    }

    private static void assertGold(int expected, int[][] grid) {
        int[][] before = deepCopy(grid);
        assertEquals(expected, new GetMaximumGold_1219().getMaximumGold(grid));
        assertTrue(Arrays.deepEquals(before, grid), "solution must not alter the input grid");
    }

    /** Brute-force reference implementation using a cell bit mask, independent of the SUT. */
    private static int independentOracle(int[][] grid) {
        int best = 0;
        for (int row = 0; row < grid.length; row++) for (int column = 0; column < grid[0].length; column++) {
            if (grid[row][column] > 0) best = Math.max(best, oracleDfs(grid, row, column, 0));
        }
        return best;
    }

    private static int oracleDfs(int[][] grid, int row, int column, int used) {
        int bit = 1 << (row * grid[0].length + column);
        if ((used & bit) != 0 || grid[row][column] == 0) return 0;
        int continuation = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] direction : directions) {
            int nextRow = row + direction[0], nextColumn = column + direction[1];
            if (nextRow >= 0 && nextRow < grid.length && nextColumn >= 0 && nextColumn < grid[0].length) {
                continuation = Math.max(continuation, oracleDfs(grid, nextRow, nextColumn, used | bit));
            }
        }
        return grid[row][column] + continuation;
    }

    private static int[][] deepCopy(int[][] grid) {
        int[][] copy = new int[grid.length][];
        for (int row = 0; row < grid.length; row++) copy[row] = grid[row].clone();
        return copy;
    }
}

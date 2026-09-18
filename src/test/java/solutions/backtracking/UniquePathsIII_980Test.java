package solutions.backtracking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class UniquePathsIII_980Test {
    private final UniquePathsIII_980 solution = new UniquePathsIII_980();

    @Test
    void testBasic() {
        int[][] grid = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        assertEquals(2, solution.uniquePathsIII(grid));
    }

    @Test
    void testSmallGrid() {
        int[][] grid = {{1,0,0,0},{0,0,0,0},{0,0,0,2}};
        assertEquals(4, solution.uniquePathsIII(grid));
    }

    @Test
    void testWithObstacles() {
        int[][] grid = {{0,1},{2,0}};
        assertEquals(0, solution.uniquePathsIII(grid));
    }

    @Test
    void testSinglePath() {
        int[][] grid = {{1,2}};
        assertEquals(1, solution.uniquePathsIII(grid));
    }

    @Test
    void testNoPath() {
        int[][] grid = {{1,-1},{-1,2}};
        assertEquals(0, solution.uniquePathsIII(grid));
    }

    @Test
    void testNullGrid() {
        assertEquals(0, solution.uniquePathsIII(null));
    }

    @Test
    void testEmptyGrid() {
        assertEquals(0, solution.uniquePathsIII(new int[][]{}));
    }

    @Test
    void testEmptyRow() {
        assertEquals(0, solution.uniquePathsIII(new int[][]{{}}));
    }

    @Test
    void testStartNextToEnd() {
        int[][] grid = {{1, 2}};
        assertEquals(1, solution.uniquePathsIII(grid));
    }

    @Test
    void testAllObstaclesExceptStartEnd() {
        int[][] grid = {{1, -1, 2}};
        assertEquals(0, solution.uniquePathsIII(grid));
    }

    @Test
    void testVerticalPath() {
        int[][] grid = {{1},{0},{0},{2}};
        assertEquals(1, solution.uniquePathsIII(grid));
    }

    @Test
    void testLargerGrid() {
        int[][] grid = {
            {1, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 2}
        };
        assertEquals(4, solution.uniquePathsIII(grid));
    }

    @Test
    void testGiantGrid() {
        // 4x5 grid with no obstacles
        int[][] grid = {
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 2}
        };
        assertEquals(20, oracle(grid));
        assertEquals(20, solution.uniquePathsIII(grid));
    }

    @Test
    void testMultipleObstacles() {
        int[][] grid = {
            {1, 0, 0},
            {0, -1, 0},
            {0, 0, 2}
        };
        assertEquals(0, solution.uniquePathsIII(grid));
    }

    @Test
    void testOneRowWithBlockedMiddle() {
        assertEquals(0, solution.uniquePathsIII(new int[][]{{1, 0, -1, 0, 2}}));
    }

    @Test
    void testOneRowWithOnlyEndpoints() {
        assertEquals(1, solution.uniquePathsIII(new int[][]{{1, 2}}));
    }

    @Test
    void testOneColumnWithOneEmpty() {
        assertEquals(1, solution.uniquePathsIII(new int[][]{{1}, {0}, {2}}));
    }

    @Test
    void testEndpointCannotBeReachedBeforeVisitingRemainingCells() {
        assertEquals(0, solution.uniquePathsIII(new int[][]{{1, 2, 0}}));
    }

    @Test
    void testTwoByTwoParityImpossibility() {
        assertEquals(0, solution.uniquePathsIII(new int[][]{{1, 0}, {0, 2}}));
    }

    @Test
    void testTwoByTwoSingleHamiltonianPath() {
        assertEquals(1, solution.uniquePathsIII(new int[][]{{1, 0}, {2, 0}}));
    }

    @Test
    void testThreeByTwoEndpointPlacement() {
        assertEquals(1, solution.uniquePathsIII(new int[][]{{1, 0}, {0, 0}, {0, 2}}));
    }

    @Test
    void testThreeByThreeOppositeEndpoints() {
        assertEquals(2, solution.uniquePathsIII(new int[][]{{1, 0, 0}, {0, 0, 0}, {2, 0, 0}}));
    }

    @Test
    void testConnectedGridWithObstacleDeadEnd() {
        assertEquals(0, solution.uniquePathsIII(new int[][]{{1, 0, 0}, {-1, -1, 0}, {0, 0, 2}}));
    }

    @Test
    void testDisconnectedFreeCellRegions() {
        assertEquals(0, solution.uniquePathsIII(new int[][]{{1, 0, -1, 2}, {0, -1, 0, 0}}));
    }

    @Test
    void testBottleneckWithOneValidRoute() {
        assertEquals(1, solution.uniquePathsIII(new int[][]{{1, 0, -1, 0, 0}, {0, 0, -1, 0, 0}, {0, 0, 0, 0, 2}}));
    }

    @Test
    void testObstacleSplitsGridButLeavesNoHamiltonianPath() {
        assertEquals(0, solution.uniquePathsIII(new int[][]{{1, 0, 0, 0}, {0, -1, 0, 0}, {0, 0, 0, 2}}));
    }

    @Test
    void testThreeByFourNoObstacleGrid() {
        assertEquals(4, solution.uniquePathsIII(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}}));
    }

    @Test
    void testThreeByFiveNoObstacleGrid() {
        assertEquals(8, solution.uniquePathsIII(new int[][]{{1, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 2}}));
    }

    @Test
    void testMaximumCellCountSingleRow() {
        int[][] grid = new int[1][20];
        grid[0][0] = 1;
        grid[0][19] = 2;
        assertEquals(1, solution.uniquePathsIII(grid));
    }

    @Test
    void testMaximumCellCountSingleColumn() {
        int[][] grid = new int[20][1];
        grid[0][0] = 1;
        grid[19][0] = 2;
        assertEquals(1, solution.uniquePathsIII(grid));
    }

    @Test
    void testStartAndEndInMiddleOfLongRow() {
        assertEquals(0, solution.uniquePathsIII(new int[][]{{0, 1, 0, 2, 0}}));
    }

    @Test
    void testInputIsNotMutated() {
        int[][] grid = {{1, 0, 0}, {0, -1, 2}};
        int[][] before = deepCopy(grid);
        solution.uniquePathsIII(grid);
        assertTrue(Arrays.deepEquals(before, grid));
    }

    @Test
    void testDeterministicSmallGridsMatchIndependentBitmaskOracle() {
        int[][][] grids = {
            {{1, 0, 0}, {0, 0, 2}},
            {{1, -1, 0}, {0, 0, 2}},
            {{0, 1, 0}, {0, 0, 2}},
            {{1, 0}, {0, 0}, {2, -1}},
            {{1, 0, -1}, {0, 0, 2}},
            {{1, 0, 0}, {0, 0, 0}, {0, 2, -1}},
            {{1, -1, 0, 0}, {0, 0, 0, 2}},
            {{1, 0, 0, 0}, {-1, 0, 0, 2}}
        };
        for (int[][] grid : grids) {
            assertEquals(oracle(grid), solution.uniquePathsIII(deepCopy(grid)), Arrays.deepToString(grid));
        }
    }

    /** Independent Hamiltonian-path count over the small grid's free-cell graph. */
    private int oracle(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Map<Integer, Integer> cellToBit = new HashMap<>();
        int start = -1;
        int end = -1;
        int bit = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] != -1) {
                    cellToBit.put(row * cols + col, bit);
                    if (grid[row][col] == 1) start = row * cols + col;
                    if (grid[row][col] == 2) end = row * cols + col;
                    bit++;
                }
            }
        }
        Map<Long, Integer> memo = new HashMap<>();
        return oracle(grid, cols, cellToBit, start, end, 1 << cellToBit.get(start), (1 << bit) - 1, memo);
    }

    private int oracle(int[][] grid, int cols, Map<Integer, Integer> cellToBit,
                       int current, int end, int visited, int allVisited, Map<Long, Integer> memo) {
        if (current == end) return visited == allVisited ? 1 : 0;
        long key = (((long) current) << 32) | (visited & 0xffffffffL);
        Integer saved = memo.get(key);
        if (saved != null) return saved;
        int row = current / cols;
        int col = current % cols;
        int count = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] direction : directions) {
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];
            int next = nextRow * cols + nextCol;
            if (nextRow >= 0 && nextRow < grid.length && nextCol >= 0 && nextCol < cols
                    && cellToBit.containsKey(next) && (visited & (1 << cellToBit.get(next))) == 0) {
                count += oracle(grid, cols, cellToBit, next, end,
                        visited | (1 << cellToBit.get(next)), allVisited, memo);
            }
        }
        memo.put(key, count);
        return count;
    }

    private int[][] deepCopy(int[][] grid) {
        int[][] copy = new int[grid.length][];
        for (int row = 0; row < grid.length; row++) copy[row] = grid[row].clone();
        return copy;
    }
}

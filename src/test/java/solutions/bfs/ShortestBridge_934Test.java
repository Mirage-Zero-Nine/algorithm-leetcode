package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import org.junit.jupiter.api.Test;

/** Tests the DFS marking and multi-source BFS used by {@link ShortestBridge_934}. */
public class ShortestBridge_934Test {

    @Test
    public void officialExampleOne() {
        assertBridge(1, new int[][]{{0, 1}, {1, 0}});
    }

    @Test
    public void officialExampleTwo() {
        assertBridge(2, new int[][]{{0, 1, 0}, {0, 0, 0}, {0, 0, 1}});
    }

    @Test
    public void officialExampleThree() {
        assertBridge(1, new int[][]{
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        });
    }

    @Test
    public void diagonalTwoByTwoIslandsNeedOneFlip() {
        assertBridge(1, new int[][]{{1, 0}, {0, 1}});
    }

    @Test
    public void horizontalAdjacentFrontiersNeedOneFlip() {
        assertBridge(1, new int[][]{{1, 0, 1}});
    }

    @Test
    public void verticalAdjacentFrontiersNeedOneFlip() {
        assertBridge(1, new int[][]{{1}, {0}, {1}});
    }

    @Test
    public void islandsSeparatedByTwoWaterCells() {
        assertBridge(2, new int[][]{{1, 0, 0, 1}});
    }

    @Test
    public void islandsSeparatedByLongStraightCorridor() {
        assertBridge(8, new int[][]{{1, 0, 0, 0, 0, 0, 0, 0, 0, 1}});
    }

    @Test
    public void rectangularIslands() {
        assertBridge(2, new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        });
    }

    @Test
    public void narrowTwoRowCorridor() {
        assertBridge(3, new int[][]{
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1}
        });
    }

    @Test
    public void firstIslandCanSurroundSecondIsland() {
        assertBridge(2, new int[][]{
                {1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 1, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}
        });
    }

    @Test
    public void firstIslandHasMultipleNearestFrontiers() {
        assertBridge(1, new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 1, 1}
        });
    }

    @Test
    public void secondIslandCanBeAHorizontalLine() {
        assertBridge(1, new int[][]{
                {1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0}
        });
    }

    @Test
    public void islandsCanTouchGridBorders() {
        assertBridge(3, new int[][]{
                {1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1}
        });
    }

    @Test
    public void equalDistanceRoutesUseTheNearestOne() {
        assertBridge(4, new int[][]{
                {1, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        });
    }

    @Test
    public void singleRowRectangularInputIsSupportedByImplementation() {
        assertBridge(4, new int[][]{{1, 0, 0, 0, 0, 1}});
    }

    @Test
    public void singleColumnRectangularInputIsSupportedByImplementation() {
        assertBridge(4, new int[][]{{1}, {0}, {0}, {0}, {0}, {1}});
    }

    @Test
    public void rectangularFiveByTwoInputIsSupportedByImplementation() {
        assertBridge(2, new int[][]{
                {1, 0},
                {1, 0},
                {0, 0},
                {0, 1},
                {0, 1}
        });
    }

    @Test
    public void minimumValidSquareHasTheIndependentExpectedDistance() {
        int[][][] grids = {
                {{1, 0}, {0, 1}},
                {{0, 1}, {1, 0}}
        };
        for (int[][] grid : grids) {
            assertEquals(bruteForce(grid), new ShortestBridge_934().shortestBridge(copy(grid)));
        }
    }

    @Test
    public void exhaustiveThreeByThreeValidTwoIslandGridsMatchOracle() {
        int checked = 0;
        for (int mask = 0; mask < (1 << 9); mask++) {
            int[][] grid = fromMask(3, mask);
            if (islandCount(grid) == 2) {
                assertEquals(bruteForce(grid), new ShortestBridge_934().shortestBridge(copy(grid)),
                        "mask=" + mask);
                checked++;
            }
        }
        // Ensures the exhaustive loop exercised a substantial set of valid contracts.
        assertEquals(208, checked);
    }

    @Test
    public void seededSeparatedRectanglesMatchIndependentOracle() {
        Random random = new Random(934L);
        for (int sample = 0; sample < 80; sample++) {
            int n = 5 + random.nextInt(12);
            int[][] grid = new int[n][n];
            int firstHeight = 1 + random.nextInt(n / 2);
            int firstWidth = 1 + random.nextInt(n / 2);
            int secondHeight = 1 + random.nextInt(n / 2);
            int secondWidth = 1 + random.nextInt(n / 2);
            for (int row = 0; row < firstHeight; row++) {
                for (int column = 0; column < firstWidth; column++) {
                    grid[row][column] = 1;
                }
            }
            for (int row = n - secondHeight; row < n; row++) {
                for (int column = n - secondWidth; column < n; column++) {
                    grid[row][column] = 1;
                }
            }
            assertEquals(bruteForce(grid), new ShortestBridge_934().shortestBridge(copy(grid)),
                    "sample=" + sample + ", n=" + n);
        }
    }

    @Test
    public void repeatedCallsOnFreshInputsDoNotLeakDimensionsOrQueueState() {
        ShortestBridge_934 solution = new ShortestBridge_934();
        int[][] first = {{1, 0, 0, 1}};
        int[][] second = {{1, 0}, {0, 1}};
        assertEquals(2, solution.shortestBridge(copy(first)));
        assertEquals(1, solution.shortestBridge(copy(second)));
        assertEquals(2, solution.shortestBridge(copy(first)));
    }

    @Test
    public void maximumSupportedSquareWithOppositeCornerIslands() {
        int n = 100;
        int[][] grid = new int[n][n];
        grid[0][0] = 1;
        grid[n - 1][n - 1] = 1;
        assertBridge(197, grid);
    }

    @Test
    public void maximumSupportedSquareWithFullSideIslands() {
        int n = 100;
        int[][] grid = new int[n][n];
        for (int row = 0; row < n; row++) {
            grid[row][0] = 1;
            grid[row][n - 1] = 1;
        }
        assertBridge(98, grid);
    }

    @Test
    public void maximumSupportedSquareWithLargeFirstIsland() {
        int n = 100;
        int[][] grid = new int[n][n];
        for (int row = 0; row < n / 2; row++) {
            Arrays.fill(grid[row], 1);
        }
        grid[n - 1][n - 1] = 1;
        assertBridge(49, grid);
    }

    private static void assertBridge(int expected, int[][] grid) {
        assertEquals(expected, new ShortestBridge_934().shortestBridge(copy(grid)));
    }

    /** Independent contract oracle: label both islands, then minimize Manhattan distance minus one. */
    private static int bruteForce(int[][] grid) {
        List<List<int[]>> islands = new ArrayList<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                if (grid[row][column] != 1 || visited[row][column]) {
                    continue;
                }
                List<int[]> island = new ArrayList<>();
                Queue<int[]> queue = new ArrayDeque<>();
                queue.add(new int[]{row, column});
                visited[row][column] = true;
                while (!queue.isEmpty()) {
                    int[] current = queue.remove();
                    island.add(current);
                    for (int[] direction : DIRECTIONS) {
                        int nextRow = current[0] + direction[0];
                        int nextColumn = current[1] + direction[1];
                        if (nextRow >= 0 && nextRow < grid.length
                                && nextColumn >= 0 && nextColumn < grid[nextRow].length
                                && grid[nextRow][nextColumn] == 1
                                && !visited[nextRow][nextColumn]) {
                            visited[nextRow][nextColumn] = true;
                            queue.add(new int[]{nextRow, nextColumn});
                        }
                    }
                }
                islands.add(island);
            }
        }
        if (islands.size() != 2) {
            throw new AssertionError("Expected exactly two islands but found " + islands.size());
        }
        int best = Integer.MAX_VALUE;
        for (int[] first : islands.get(0)) {
            for (int[] second : islands.get(1)) {
                best = Math.min(best, Math.abs(first[0] - second[0])
                        + Math.abs(first[1] - second[1]) - 1);
            }
        }
        return best;
    }

    private static int islandCount(int[][] grid) {
        int count = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                if (grid[row][column] == 1 && !visited[row][column]) {
                    count++;
                    markIsland(grid, visited, row, column);
                }
            }
        }
        return count;
    }

    private static void markIsland(int[][] grid, boolean[][] visited, int row, int column) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{row, column});
        visited[row][column] = true;
        while (!queue.isEmpty()) {
            int[] current = queue.remove();
            for (int[] direction : DIRECTIONS) {
                int nextRow = current[0] + direction[0];
                int nextColumn = current[1] + direction[1];
                if (nextRow >= 0 && nextRow < grid.length
                        && nextColumn >= 0 && nextColumn < grid[nextRow].length
                        && grid[nextRow][nextColumn] == 1
                        && !visited[nextRow][nextColumn]) {
                    visited[nextRow][nextColumn] = true;
                    queue.add(new int[]{nextRow, nextColumn});
                }
            }
        }
    }

    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

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

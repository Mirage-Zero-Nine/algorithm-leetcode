package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/** Contract and regression tests for {@link MaximumMinimumPath_1102}. */
public class MaximumMinimumPath_1102Test {

    private final MaximumMinimumPath_1102 test = new MaximumMinimumPath_1102();

    @Test
    public void testOfficialExamples() {
        assertEquals(4, test.maximumMinimumPath(new int[][]{{5, 4, 5}, {1, 2, 6}, {7, 4, 6}}));
        assertEquals(2, test.maximumMinimumPath(new int[][]{{2, 2, 1, 2, 2, 2}, {1, 2, 2, 2, 1, 2}}));
        assertEquals(3, test.maximumMinimumPath(new int[][]{{3, 4, 6, 3, 4}, {0, 2, 1, 1, 7},
                {8, 8, 3, 2, 7}, {3, 2, 4, 9, 8}, {4, 1, 2, 0, 0}, {4, 6, 5, 4, 3}}));
    }

    @Test
    public void testNullAndEmptyMatricesUseDocumentedFallback() {
        assertEquals(-1, test.maximumMinimumPath(null));
        assertEquals(-1, test.maximumMinimumPath(new int[][]{}));
        assertEquals(-1, test.maximumMinimumPath(new int[][]{{}}));
    }

    @Test
    public void testSingleCellAtMinimumAndMaximumValues() {
        assertEquals(0, test.maximumMinimumPath(new int[][]{{0}}));
        assertEquals(1_000_000_000, test.maximumMinimumPath(new int[][]{{1_000_000_000}}));
    }

    @Test
    public void testSingleRowMustUseEveryCell() {
        assertEquals(2, test.maximumMinimumPath(new int[][]{{5, 4, 2, 9}}));
        assertEquals(0, test.maximumMinimumPath(new int[][]{{0, 1, 2, 3, 4, 5}}));
        assertEquals(-7, test.maximumMinimumPath(new int[][]{{-2, -3, -7, -1}}));
    }

    @Test
    public void testSingleColumnMustUseEveryCell() {
        assertEquals(3, test.maximumMinimumPath(new int[][]{{8}, {3}, {7}, {9}}));
        assertEquals(0, test.maximumMinimumPath(new int[][]{{4}, {0}, {8}, {9}}));
        assertEquals(-8, test.maximumMinimumPath(new int[][]{{-2}, {-8}, {-1}}));
    }

    @Test
    public void testTwoByTwoChoosesBetterOfTheTwoMonotoneRoutes() {
        assertEquals(7, test.maximumMinimumPath(new int[][]{{9, 1}, {8, 7}}));
        assertEquals(4, test.maximumMinimumPath(new int[][]{{5, 4}, {1, 5}}));
        assertEquals(0, test.maximumMinimumPath(new int[][]{{0, 100}, {99, 0}}));
    }

    @Test
    public void testAllEqualValues() {
        assertEquals(6, test.maximumMinimumPath(new int[][]{{6, 6, 6}, {6, 6, 6}, {6, 6, 6}}));
        assertEquals(0, test.maximumMinimumPath(new int[][]{{0, 0}, {0, 0}}));
        assertEquals(1_000_000_000, test.maximumMinimumPath(new int[][]{{1_000_000_000, 1_000_000_000},
                {1_000_000_000, 1_000_000_000}}));
    }

    @Test
    public void testPathAvoidsLowBottleneckWhenAHighRouteExists() {
        assertEquals(5, test.maximumMinimumPath(new int[][]{{5, 5, 5}, {5, 1, 5}, {5, 5, 5}}));
        assertEquals(9, test.maximumMinimumPath(new int[][]{{9, 0, 9}, {9, 0, 9}, {9, 9, 9}}));
        assertEquals(8, test.maximumMinimumPath(new int[][]{{8, 8, 1, 8}, {1, 8, 1, 8}, {8, 8, 8, 8}}));
    }

    @Test
    public void testRectangularGridsAndTurnChoices() {
        assertEquals(2, test.maximumMinimumPath(new int[][]{{5, 4, 2, 9}}));
        assertEquals(3, test.maximumMinimumPath(new int[][]{{8}, {3}, {7}, {9}}));
        assertEquals(5, test.maximumMinimumPath(new int[][]{{9, 5, 8, 7, 6}, {8, 1, 6, 5, 5}}));
        assertEquals(0, test.maximumMinimumPath(new int[][]{{9, 8}, {7, 6}, {5, 4}, {3, 2}, {1, 0}}));
    }

    @Test
    public void testZerosAndNegativeValuesAreValidForTheImplementation() {
        assertEquals(-5, test.maximumMinimumPath(new int[][]{{-5, -2}, {-3, -4}}));
        assertEquals(-4, test.maximumMinimumPath(new int[][]{{-4, -2, -3}, {-5, 9, 8}, {-6, 7, 10}}));
        assertEquals(-10, test.maximumMinimumPath(new int[][]{{-10, -9, -8}, {-11, -12, -7}, {-13, -14, -6}}));
        assertEquals(0, test.maximumMinimumPath(new int[][]{{0, -1, 0}, {0, -2, 0}, {0, 0, 0}}));
    }

    @Test
    public void testSignedIntegerBoundariesDoNotOverflowPriorityOrdering() {
        assertEquals(Integer.MAX_VALUE, test.maximumMinimumPath(new int[][]{
                {Integer.MAX_VALUE, Integer.MAX_VALUE}, {Integer.MIN_VALUE, Integer.MAX_VALUE}
        }));
        assertEquals(Integer.MIN_VALUE, test.maximumMinimumPath(new int[][]{
                {Integer.MIN_VALUE, Integer.MAX_VALUE}, {Integer.MAX_VALUE, Integer.MIN_VALUE}
        }));
    }

    @Test
    public void testLowValueDeadEndDoesNotWinByPriorityAlone() {
        int[][] grid = {
                {10, 10, 10, 1},
                {1, 1, 10, 1},
                {10, 10, 10, 10},
                {1, 1, 1, 10}
        };
        assertEquals(10, test.maximumMinimumPath(grid));
    }

    @Test
    public void testCheckerboardAndDuplicateValues() {
        assertEquals(1, test.maximumMinimumPath(new int[][]{
                {4, 1, 4, 1, 4}, {1, 4, 1, 4, 1}, {4, 1, 4, 1, 4}, {1, 4, 1, 4, 1}
        }));
        assertEquals(2, test.maximumMinimumPath(new int[][]{
                {2, 2, 2, 1}, {2, 1, 2, 1}, {2, 2, 2, 2}
        }));
    }

    @Test
    public void testInputIsNotMutated() {
        int[][] grid = {{5, 1, 7}, {4, 8, 6}, {3, 2, 9}};
        int[][] before = copy(grid);
        assertEquals(4, test.maximumMinimumPath(grid));
        assertArrayEquals(before[0], grid[0]);
        assertArrayEquals(before[1], grid[1]);
        assertArrayEquals(before[2], grid[2]);
    }

    @Test
    public void testRepeatedCallsDoNotLeakVisitedState() {
        int[][] first = {{5, 1}, {4, 5}};
        int[][] second = {{2, 2, 1}, {1, 2, 2}};
        assertEquals(4, test.maximumMinimumPath(first));
        assertEquals(2, test.maximumMinimumPath(second));
        assertEquals(4, test.maximumMinimumPath(first));
    }

    @ParameterizedTest(name = "small grid {index}")
    @MethodSource("smallGridCases")
    public void testSeededSmallGridsAgainstIndependentThresholdOracle(int[][] grid) {
        assertEquals(thresholdOracle(grid), test.maximumMinimumPath(copy(grid)));
    }

    static Stream<Arguments> smallGridCases() {
        List<Arguments> cases = new ArrayList<>();
        Random random = new Random(1102L);
        for (int sample = 0; sample < 30; sample++) {
            int rows = 1 + random.nextInt(5);
            int columns = 1 + random.nextInt(5);
            int[][] grid = new int[rows][columns];
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    grid[row][column] = random.nextInt(21) - 10;
                }
            }
            cases.add(Arguments.of((Object) grid));
        }
        return cases.stream();
    }

    @Test
    public void testMaximumOfficialDimensionsAndValues() {
        int size = 100;
        int[][] grid = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                grid[row][column] = 1_000_000_000 - row - column;
            }
        }
        assertEquals(999_999_802, test.maximumMinimumPath(grid));
    }

    @Test
    public void testMaximumDimensionsAllZeroGrid() {
        int[][] grid = new int[100][100];
        assertEquals(0, test.maximumMinimumPath(grid));
    }

    private static int thresholdOracle(int[][] grid) {
        List<Integer> values = new ArrayList<>();
        for (int[] row : grid) {
            for (int value : row) {
                if (!values.contains(value)) {
                    values.add(value);
                }
            }
        }
        values.sort(Integer::compareTo);
        for (int index = values.size() - 1; index >= 0; index--) {
            if (reachableAtLeast(grid, values.get(index))) {
                return values.get(index);
            }
        }
        throw new AssertionError("A non-empty grid must contain a reachable endpoint");
    }

    private static boolean reachableAtLeast(int[][] grid, int threshold) {
        int rows = grid.length;
        int columns = grid[0].length;
        if (grid[0][0] < threshold || grid[rows - 1][columns - 1] < threshold) {
            return false;
        }
        boolean[][] visited = new boolean[rows][columns];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] current = queue.remove();
            if (current[0] == rows - 1 && current[1] == columns - 1) {
                return true;
            }
            for (int[] direction : directions) {
                int nextRow = current[0] + direction[0];
                int nextColumn = current[1] + direction[1];
                if (nextRow >= 0 && nextRow < rows && nextColumn >= 0 && nextColumn < columns
                        && !visited[nextRow][nextColumn] && grid[nextRow][nextColumn] >= threshold) {
                    visited[nextRow][nextColumn] = true;
                    queue.add(new int[]{nextRow, nextColumn});
                }
            }
        }
        return false;
    }

    private static int[][] copy(int[][] grid) {
        int[][] result = new int[grid.length][];
        for (int row = 0; row < grid.length; row++) {
            result[row] = grid[row].clone();
        }
        return result;
    }
}

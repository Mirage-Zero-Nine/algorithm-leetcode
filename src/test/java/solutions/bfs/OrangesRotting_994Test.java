package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;

import org.junit.jupiter.api.Test;

/** Contract and regression tests for {@link OrangesRotting_994}. */
public class OrangesRotting_994Test {

    private final OrangesRotting_994 test = new OrangesRotting_994();

    @Test
    public void testOfficialExamples() {
        assertEquals(4, test.orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
        assertEquals(-1, test.orangesRotting(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}}));
        assertEquals(0, test.orangesRotting(new int[][]{{0, 2}}));
    }

    @Test
    public void testDocumentedInvalidInputs() {
        assertEquals(-1, test.orangesRotting(null));
        assertEquals(-1, test.orangesRotting(new int[][]{}));
        assertEquals(-1, test.orangesRotting(new int[][]{{}}));
    }

    @Test
    public void testSingleEmptyCell() {
        assertEquals(0, test.orangesRotting(new int[][]{{0}}));
    }

    @Test
    public void testSingleFreshCellHasNoSource() {
        assertEquals(-1, test.orangesRotting(new int[][]{{1}}));
    }

    @Test
    public void testSingleRottenCellIsAlreadyComplete() {
        assertEquals(0, test.orangesRotting(new int[][]{{2}}));
    }

    @Test
    public void testAllEmptyGrid() {
        assertEquals(0, test.orangesRotting(new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        }));
    }

    @Test
    public void testAllRottenGrid() {
        assertEquals(0, test.orangesRotting(new int[][]{
                {2, 2},
                {2, 2}
        }));
    }

    @Test
    public void testAllFreshGridHasNoSource() {
        assertEquals(-1, test.orangesRotting(new int[][]{
                {1, 1},
                {1, 1}
        }));
    }

    @Test
    public void testOneFreshOrangeToTheRight() {
        assertEquals(1, test.orangesRotting(new int[][]{{2, 1}}));
    }

    @Test
    public void testOneFreshOrangeBelow() {
        assertEquals(1, test.orangesRotting(new int[][]{{2}, {1}}));
    }

    @Test
    public void testDiagonalOrangeIsNotAdjacent() {
        assertEquals(-1, test.orangesRotting(new int[][]{{2, 0}, {0, 1}}));
    }

    @Test
    public void testFreshOrangeSeparatedByEmptyCells() {
        assertEquals(-1, test.orangesRotting(new int[][]{{2, 0, 1}}));
    }

    @Test
    public void testMultipleSourcesPropagateSimultaneously() {
        assertEquals(2, test.orangesRotting(new int[][]{
                {2, 1, 1},
                {1, 1, 1},
                {1, 1, 2}
        }));
    }

    @Test
    public void testCenterSourceReachesFourCardinalNeighborsInOneMinute() {
        assertEquals(1, test.orangesRotting(new int[][]{
                {0, 1, 0},
                {1, 2, 1},
                {0, 1, 0}
        }));
    }

    @Test
    public void testSingleRowRequiresOneMinutePerStep() {
        assertEquals(9, test.orangesRotting(new int[][]{{2, 1, 1, 1, 1, 1, 1, 1, 1, 1}}));
    }

    @Test
    public void testSingleColumnRequiresOneMinutePerStep() {
        assertEquals(9, test.orangesRotting(new int[][]{
                {2}, {1}, {1}, {1}, {1}, {1}, {1}, {1}, {1}, {1}
        }));
    }

    @Test
    public void testRectangularGridUsesFourDirectionsOnly() {
        assertEquals(10, test.orangesRotting(new int[][]{
                {2, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        }));
    }

    @Test
    public void testZerosCreateAnUnreachableComponent() {
        assertEquals(-1, test.orangesRotting(new int[][]{
                {2, 0, 0},
                {0, 0, 0},
                {0, 0, 1}
        }));
    }

    @Test
    public void testOpenRingAroundEmptyCenter() {
        assertEquals(6, test.orangesRotting(new int[][]{
                {2, 1, 1, 1},
                {1, 0, 0, 1},
                {1, 0, 0, 1},
                {1, 1, 1, 1}
        }));
    }

    @Test
    public void testCheckerboardSourcesRotInOneMinute() {
        assertEquals(1, test.orangesRotting(new int[][]{
                {2, 1, 2, 1, 2},
                {1, 2, 1, 2, 1},
                {2, 1, 2, 1, 2},
                {1, 2, 1, 2, 1},
                {2, 1, 2, 1, 2}
        }));
    }

    @Test
    public void testMaximumOfficialSquareWithCornerSource() {
        int size = 10;
        int[][] grid = new int[size][size];
        for (int row = 0; row < size; row++) {
            Arrays.fill(grid[row], 1);
        }
        grid[0][0] = 2;
        assertEquals(18, test.orangesRotting(grid));
    }

    @Test
    public void testMaximumOfficialSquareWithCenterSource() {
        int size = 10;
        int[][] grid = new int[size][size];
        for (int row = 0; row < size; row++) {
            Arrays.fill(grid[row], 1);
        }
        grid[5][5] = 2;
        assertEquals(10, test.orangesRotting(grid));
    }

    @Test
    public void testIndependentOracleOnSeededOfficialSizeGrids() {
        Random random = new Random(994L);
        for (int sample = 0; sample < 40; sample++) {
            int rows = 1 + random.nextInt(10);
            int columns = 1 + random.nextInt(10);
            int[][] grid = new int[rows][columns];
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    grid[row][column] = random.nextInt(3);
                }
            }
            assertEquals(independentOracle(grid), test.orangesRotting(copy(grid)),
                    "sample=" + sample);
        }
    }

    @Test
    public void testInputMutationMarksOnlyFreshOrangesAsRotten() {
        int[][] grid = {
                {2, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        };
        assertEquals(4, test.orangesRotting(grid));
        assertArrayEquals(new int[]{2, 2, 0}, grid[0]);
        assertArrayEquals(new int[]{2, 2, 2}, grid[1]);
        assertArrayEquals(new int[]{0, 2, 2}, grid[2]);
    }

    @Test
    public void testRepeatedCallsOnSameInstanceAndFreshInputs() {
        int[][] first = {{2, 1, 1}};
        assertEquals(2, test.orangesRotting(first));
        assertEquals(0, test.orangesRotting(first));
        assertEquals(-1, test.orangesRotting(new int[][]{{1, 1}}));
        assertEquals(1, test.orangesRotting(new int[][]{{2, 1}}));
    }

    @Test
    public void testSeededGridWithGuaranteedReachability() {
        int size = 10;
        int[][] grid = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                grid[row][column] = (row + column) % 3 == 0 ? 2 : 1;
            }
        }
        assertEquals(independentOracle(grid), test.orangesRotting(copy(grid)));
    }

    private static int independentOracle(int[][] grid) {
        int answer = 0;
        boolean hasFresh = false;
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[0].length; column++) {
                if (grid[row][column] == 1) {
                    hasFresh = true;
                    int distance = distanceToRotten(grid, row, column);
                    if (distance < 0) {
                        return -1;
                    }
                    answer = Math.max(answer, distance);
                }
            }
        }
        return hasFresh ? answer : 0;
    }

    private static int distanceToRotten(int[][] grid, int startRow, int startColumn) {
        int rows = grid.length;
        int columns = grid[0].length;
        int[][] distance = new int[rows][columns];
        for (int[] row : distance) {
            Arrays.fill(row, -1);
        }
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startRow, startColumn});
        distance[startRow][startColumn] = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] current = queue.remove();
            if (grid[current[0]][current[1]] == 2) {
                return distance[current[0]][current[1]];
            }
            for (int[] direction : directions) {
                int nextRow = current[0] + direction[0];
                int nextColumn = current[1] + direction[1];
                if (nextRow >= 0 && nextRow < rows && nextColumn >= 0 && nextColumn < columns
                        && grid[nextRow][nextColumn] != 0 && distance[nextRow][nextColumn] < 0) {
                    distance[nextRow][nextColumn] = distance[current[0]][current[1]] + 1;
                    queue.add(new int[]{nextRow, nextColumn});
                }
            }
        }
        return -1;
    }

    private static int[][] copy(int[][] grid) {
        int[][] result = new int[grid.length][];
        for (int row = 0; row < grid.length; row++) {
            result[row] = grid[row].clone();
        }
        return result;
    }
}

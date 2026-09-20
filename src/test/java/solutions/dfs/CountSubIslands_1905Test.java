package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class CountSubIslands_1905Test {

    private final CountSubIslands_1905 test = new CountSubIslands_1905();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.countSubIslands(
            new int[][]{{1, 1, 1, 0, 0}, {0, 1, 1, 1, 1}, {0, 0, 0, 0, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 1, 1}},
            new int[][]{{1, 1, 1, 0, 0}, {0, 0, 1, 1, 1}, {0, 1, 0, 0, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 1, 0}}
        ));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.countSubIslands(new int[][]{{0}}, new int[][]{{1}}));
        assertEquals(1, test.countSubIslands(new int[][]{{1}}, new int[][]{{1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(2, test.countSubIslands(
            new int[][]{{1, 0, 1, 0, 1}, {1, 1, 1, 1, 1}, {0, 0, 0, 0, 0}, {1, 1, 1, 1, 1}, {1, 0, 1, 0, 1}},
            new int[][]{{0, 0, 0, 0, 0}, {1, 1, 1, 1, 1}, {0, 1, 0, 1, 0}, {0, 1, 0, 1, 0}, {1, 0, 0, 0, 1}}
        ));
    }

    @Test
    public void testAllWaterInGrid2() {
        assertEquals(0, test.countSubIslands(
            new int[][]{{1, 1}, {1, 1}},
            new int[][]{{0, 0}, {0, 0}}
        ));
    }

    @Test
    public void testAllLandBothGrids() {
        assertEquals(1, test.countSubIslands(
            new int[][]{{1, 1}, {1, 1}},
            new int[][]{{1, 1}, {1, 1}}
        ));
    }

    @Test
    public void testNoSubIsland() {
        // grid2 island extends beyond grid1 land
        assertEquals(0, test.countSubIslands(
            new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}},
            new int[][]{{1, 1, 0}, {0, 0, 0}, {0, 0, 0}}
        ));
    }

    @Test
    public void testMultipleDisjointSubIslands() {
        assertEquals(3, test.countSubIslands(
            new int[][]{{1, 0, 1, 0, 1}, {0, 0, 0, 0, 0}, {1, 0, 1, 0, 1}},
            new int[][]{{1, 0, 1, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}}
        ));
    }

    @Test
    public void testSingleRowGrid() {
        assertEquals(2, test.countSubIslands(
            new int[][]{{1, 0, 1, 0, 1}},
            new int[][]{{1, 0, 1, 0, 0}}
        ));
    }

    @Test
    public void testSingleColumnGrid() {
        assertEquals(1, test.countSubIslands(
            new int[][]{{1}, {1}, {0}, {1}},
            new int[][]{{1}, {1}, {0}, {0}}
        ));
    }

    @Test
    public void testPartialOverlap() {
        // grid2 island partially overlaps grid1 but one cell is water in grid1
        assertEquals(0, test.countSubIslands(
            new int[][]{{1, 1, 0}, {1, 0, 0}, {0, 0, 0}},
            new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 0}}
        ));
    }

    @Test
    public void testGiantCase() {
        int n = 50;
        int[][] grid1 = new int[n][n];
        int[][] grid2 = new int[n][n];
        // fill all with 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid1[i][j] = 1;
                grid2[i][j] = 1;
            }
        }
        // one big island in grid2 fully contained in grid1
        assertEquals(1, test.countSubIslands(grid1, grid2));
    }

    @Test
    public void officialExampleTwoIsCounted() {
        assertAgainstOracle(
            new int[][]{{1, 0, 1, 0, 1}, {1, 1, 1, 1, 1}, {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1}, {1, 0, 1, 0, 1}},
            new int[][]{{0, 0, 0, 0, 0}, {1, 1, 1, 1, 1}, {0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0}, {1, 0, 0, 0, 1}});
    }

    @Test
    public void grid2SingleCellIslandsCanBeCountedIndependently() {
        assertAgainstOracle(
            new int[][]{{1, 0, 1, 0, 1}, {0, 0, 0, 0, 0}, {1, 0, 1, 0, 1}},
            new int[][]{{1, 0, 1, 0, 1}, {0, 0, 0, 0, 0}, {1, 0, 0, 0, 1}});
    }

    @Test
    public void oneGrid2IslandFailsWhenAnyCellIsWaterInGrid1() {
        assertAgainstOracle(
            new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}},
            new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}});
    }

    @Test
    public void anIslandWithAHoleIsStillOneFourConnectedComponent() {
        assertAgainstOracle(
            new int[][]{{1, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}},
            new int[][]{{1, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}});
    }

    @Test
    public void aHoleCanContainAnIndependentIsland() {
        assertAgainstOracle(
            new int[][]{{1, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}},
            new int[][]{{1, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}});
    }

    @Test
    public void diagonalContactDoesNotMergeIslands() {
        assertAgainstOracle(
            new int[][]{{1, 0, 1}, {0, 1, 0}, {1, 0, 1}},
            new int[][]{{1, 0, 1}, {0, 1, 0}, {1, 0, 1}});
    }

    @Test
    public void borderAndCornerIslandsUseOnlyFourDirections() {
        assertAgainstOracle(
            new int[][]{{1, 1, 0, 0, 1}, {0, 0, 0, 0, 1}, {1, 0, 1, 0, 0},
                {1, 0, 1, 0, 1}, {0, 0, 0, 0, 1}},
            new int[][]{{1, 1, 0, 0, 1}, {0, 0, 0, 0, 1}, {1, 0, 1, 0, 0},
                {1, 0, 1, 0, 1}, {0, 0, 0, 0, 1}});
    }

    @Test
    public void rectangularGridWithSeveralMixedComponentsMatchesOracle() {
        assertAgainstOracle(
            new int[][]{{1, 1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 1, 0, 1},
                {0, 0, 1, 0, 0, 0, 0}},
            new int[][]{{1, 1, 0, 1, 0, 0, 1}, {1, 1, 0, 1, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 1}});
    }

    @Test
    public void oneRowHasSeparatedAndInvalidComponents() {
        assertAgainstOracle(
            new int[][]{{1, 1, 0, 1, 0, 1, 1, 0, 0, 1}},
            new int[][]{{1, 1, 0, 1, 1, 1, 1, 0, 0, 1}});
    }

    @Test
    public void oneColumnHasSeparatedAndInvalidComponents() {
        assertAgainstOracle(
            new int[][]{{1}, {1}, {0}, {1}, {0}, {1}, {1}, {0}, {1}},
            new int[][]{{1}, {1}, {0}, {1}, {1}, {1}, {1}, {0}, {0}});
    }

    @Test
    public void allWaterGrid1RejectsEveryGrid2Island() {
        assertAgainstOracle(new int[][]{{0, 0, 0}, {0, 0, 0}},
            new int[][]{{1, 0, 1}, {1, 1, 0}});
    }

    @Test
    public void allLandGrid1AcceptsEveryGrid2Component() {
        assertAgainstOracle(new int[][]{{1, 1, 1}, {1, 1, 1}},
            new int[][]{{1, 0, 1}, {0, 1, 0}});
    }

    @Test
    public void grid2MutationVisitsEveryLandCellAndPreservesGrid1() {
        int[][] grid1 = {{1, 1, 0}, {0, 1, 1}};
        int[][] grid2 = {{1, 0, 1}, {1, 1, 0}};
        int[][] grid1Before = copy(grid1);

        assertEquals(0, test.countSubIslands(grid1, grid2));
        assertArrayEquals(grid1Before[0], grid1[0]);
        assertArrayEquals(grid1Before[1], grid1[1]);
        assertAllZeros(grid2);
    }

    @Test
    public void repeatedCallsRequireFreshGrid2BecauseTheMethodConsumesIt() {
        int[][] grid1 = {{1, 1}, {1, 0}};
        int[][] grid2 = {{1, 1}, {0, 1}};

        assertEquals(0, test.countSubIslands(grid1, grid2));
        assertEquals(0, test.countSubIslands(grid1, grid2));
        assertEquals(0, test.countSubIslands(grid1, copy(grid2)));
        assertEquals(1, test.countSubIslands(grid1, new int[][]{{1, 1}, {0, 0}}));
    }

    @Test
    public void sameSolutionInstanceHandlesIndependentFreshInputs() {
        assertAgainstOracle(new int[][]{{1}}, new int[][]{{1}});
        assertAgainstOracle(new int[][]{{0, 1}, {1, 1}}, new int[][]{{1, 1}, {1, 0}});
        assertAgainstOracle(new int[][]{{1, 0, 0}}, new int[][]{{0, 1, 1}});
    }

    @Test
    public void exhaustiveThreeByThreeGrid2MasksMatchIndependentOracle() {
        int[][][] grid1s = {
            fromMask(3, 3, 0x1ff),
            fromMask(3, 3, 0x155),
            fromMask(3, 3, 0x0e7),
            fromMask(3, 3, 0x081)
        };
        int checked = 0;
        for (int[][] grid1 : grid1s) {
            for (int mask = 0; mask < (1 << 9); mask++) {
                int[][] grid2 = fromMask(3, 3, mask);
                assertEquals(independentCount(grid1, grid2),
                    test.countSubIslands(copy(grid1), copy(grid2)),
                    "grid1 mask and grid2 mask case " + checked);
                checked++;
            }
        }
        assertEquals(2_048, checked);
    }

    @Test
    public void seededRandomSmallMatricesMatchIndependentOracle() {
        Random random = new Random(1905L);
        for (int caseNumber = 0; caseNumber < 100; caseNumber++) {
            int rows = 1 + random.nextInt(8);
            int columns = 1 + random.nextInt(8);
            int[][] grid1 = randomGrid(random, rows, columns);
            int[][] grid2 = randomGrid(random, rows, columns);
            assertAgainstOracle(grid1, grid2);
        }
    }

    @Test
    public void seededRandomRectanglesExerciseSparseAndDensePatterns() {
        Random random = new Random(19_050_001L);
        for (int caseNumber = 0; caseNumber < 30; caseNumber++) {
            int rows = 20 + random.nextInt(11);
            int columns = 20 + random.nextInt(11);
            int[][] grid1 = randomGrid(random, rows, columns, 0.25 + caseNumber % 5 * 0.15);
            int[][] grid2 = randomGrid(random, rows, columns, 0.75 - caseNumber % 5 * 0.15);
            assertAgainstOracle(grid1, grid2);
        }
    }

    @Test
    public void maximumDimensionCheckerboardHasOneIslandPerLandCell() {
        int[][] grid1 = new int[500][500];
        int[][] grid2 = new int[500][500];
        for (int row = 0; row < 500; row++) {
            Arrays.fill(grid1[row], 1);
            for (int column = 0; column < 500; column++) {
                grid2[row][column] = (row + column) % 2;
            }
        }

        assertEquals(125_000, test.countSubIslands(grid1, grid2));
        assertAllZeros(grid2);
    }

    @Test
    public void maximumDimensionStripedGridHasOneIslandPerLandRow() {
        int[][] grid1 = new int[500][500];
        int[][] grid2 = new int[500][500];
        for (int row = 0; row < 500; row++) {
            Arrays.fill(grid1[row], 1);
            if (row % 2 == 0) {
                Arrays.fill(grid2[row], 1);
            }
        }

        assertEquals(250, test.countSubIslands(grid1, grid2));
        assertAllZeros(grid2);
    }

    @Test
    public void maximumDimensionConnectedIslandAvoidsCallStackOverflow() {
        int[][] grid1 = new int[500][500];
        int[][] grid2 = new int[500][500];
        for (int row = 0; row < 500; row++) {
            Arrays.fill(grid1[row], 1);
            Arrays.fill(grid2[row], 1);
        }

        assertEquals(1, test.countSubIslands(grid1, grid2));
    }

    private void assertAgainstOracle(int[][] grid1, int[][] grid2) {
        int expected = independentCount(grid1, grid2);
        int[][] grid1Input = copy(grid1);
        int[][] grid2Input = copy(grid2);
        assertEquals(expected, test.countSubIslands(grid1Input, grid2Input));
        assertGridEquals(grid1, grid1Input);
        assertAllZeros(grid2Input);
    }

    private static int independentCount(int[][] grid1, int[][] grid2) {
        int rows = grid2.length;
        int columns = grid2[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int count = 0;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (grid2[row][column] != 1 || visited[row][column]) {
                    continue;
                }
                boolean subIsland = true;
                Queue<int[]> queue = new ArrayDeque<>();
                queue.add(new int[]{row, column});
                visited[row][column] = true;
                while (!queue.isEmpty()) {
                    int[] cell = queue.remove();
                    int currentRow = cell[0];
                    int currentColumn = cell[1];
                    if (grid1[currentRow][currentColumn] != 1) {
                        subIsland = false;
                    }
                    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                    for (int[] direction : directions) {
                        int nextRow = currentRow + direction[0];
                        int nextColumn = currentColumn + direction[1];
                        if (nextRow >= 0 && nextRow < rows && nextColumn >= 0
                                && nextColumn < columns && grid2[nextRow][nextColumn] == 1
                                && !visited[nextRow][nextColumn]) {
                            visited[nextRow][nextColumn] = true;
                            queue.add(new int[]{nextRow, nextColumn});
                        }
                    }
                }
                if (subIsland) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int[][] randomGrid(Random random, int rows, int columns) {
        return randomGrid(random, rows, columns, 0.5);
    }

    private static int[][] randomGrid(Random random, int rows, int columns, double landProbability) {
        int[][] grid = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                grid[row][column] = random.nextDouble() < landProbability ? 1 : 0;
            }
        }
        return grid;
    }

    private static int[][] fromMask(int rows, int columns, int mask) {
        int[][] grid = new int[rows][columns];
        for (int cell = 0; cell < rows * columns; cell++) {
            grid[cell / columns][cell % columns] = (mask >>> cell) & 1;
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

    private static void assertGridEquals(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row]);
        }
    }

    private static void assertAllZeros(int[][] grid) {
        for (int[] row : grid) {
            for (int cell : row) {
                assertEquals(0, cell);
            }
        }
    }
}

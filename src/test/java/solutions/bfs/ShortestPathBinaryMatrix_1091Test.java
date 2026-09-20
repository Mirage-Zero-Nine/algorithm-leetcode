package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

/** Contract and regression tests for {@link ShortestPathBinaryMatrix_1091}. */
public class ShortestPathBinaryMatrix_1091Test {

    private final ShortestPathBinaryMatrix_1091 test = new ShortestPathBinaryMatrix_1091();

    @Test
    public void testOfficialExamples() {
        assertEquals(2, test.shortestPathBinaryMatrix(new int[][]{{0, 1}, {1, 0}}));
        assertEquals(4, test.shortestPathBinaryMatrix(new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 0}}));
        assertEquals(-1, test.shortestPathBinaryMatrix(new int[][]{{1, 0, 0}, {1, 1, 0}, {1, 1, 0}}));
    }

    @Test
    public void testSingletonOpenCellHasPathLengthOne() {
        assertEquals(1, test.shortestPathBinaryMatrix(new int[][]{{0}}));
    }

    @Test
    public void testSingletonBlockedCellHasNoPath() {
        assertEquals(-1, test.shortestPathBinaryMatrix(new int[][]{{1}}));
    }

    @Test
    public void testBlockedStartIsRejectedBeforeSearch() {
        assertEquals(-1, test.shortestPathBinaryMatrix(new int[][]{{1, 0}, {0, 0}}));
    }

    @Test
    public void testBlockedDestinationIsRejectedBeforeSearch() {
        assertEquals(-1, test.shortestPathBinaryMatrix(new int[][]{{0, 0}, {0, 1}}));
    }

    @Test
    public void testDiagonalMovementCanBeTheOnlyRoute() {
        int[][] grid = {
                {0, 1, 1, 1},
                {1, 0, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 0}
        };
        assertEquals(4, test.shortestPathBinaryMatrix(grid));
    }

    @Test
    public void testDiagonalCornerCrossingIsAllowed() {
        int[][] grid = {
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 0}
        };
        assertEquals(3, test.shortestPathBinaryMatrix(grid));
    }

    @Test
    public void testHistoricalDiagonalPreferenceCase() {
        assertEquals(3, test.shortestPathBinaryMatrix(new int[][]{
                {0, 1, 0},
                {0, 0, 0},
                {1, 0, 0}
        }));
    }

    @Test
    public void testOrthogonalRouteCanBeatALongerDetour() {
        int[][] grid = {
                {0, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 0, 1},
                {1, 1, 1, 0, 0}
        };
        assertEquals(shortestPathOracle(grid), test.shortestPathBinaryMatrix(grid));
    }

    @Test
    public void testNoPathWhenOpenDestinationIsSealed() {
        int[][] grid = {
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 0}
        };
        assertEquals(-1, test.shortestPathBinaryMatrix(grid));
    }

    @Test
    public void testHistoricalNoPathCase() {
        assertEquals(-1, test.shortestPathBinaryMatrix(new int[][]{
                {0, 1, 1},
                {1, 1, 1},
                {1, 1, 0}
        }));
    }

    @Test
    public void testNoPathThroughACompleteInteriorWall() {
        int[][] grid = {
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {0, 0, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };
        assertEquals(shortestPathOracle(grid), test.shortestPathBinaryMatrix(grid));
    }

    @Test
    public void testAllZerosUseChebyshevLength() {
        assertEquals(2, test.shortestPathBinaryMatrix(new int[][]{{0, 0}, {0, 0}}));
        assertEquals(3, test.shortestPathBinaryMatrix(new int[][]{
                {0, 0, 0}, {0, 0, 0}, {0, 0, 0}
        }));
        assertEquals(7, test.shortestPathBinaryMatrix(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        }));
    }

    @Test
    public void testAllBlockedGridHasNoPath() {
        assertEquals(-1, test.shortestPathBinaryMatrix(new int[][]{
                {1, 1, 1}, {1, 1, 1}, {1, 1, 1}
        }));
    }

    @Test
    public void testBranchingRoutesChooseTheShortestTie() {
        int[][] grid = {
                {0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 0}
        };
        assertEquals(shortestPathOracle(grid), test.shortestPathBinaryMatrix(grid));
    }

    @Test
    public void testCyclesDoNotCauseRevisitsOrIncorrectDistance() {
        int[][] grid = {
                {0, 0, 0, 1, 0, 0},
                {0, 1, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1},
                {1, 1, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0}
        };
        assertEquals(shortestPathOracle(grid), test.shortestPathBinaryMatrix(grid));
    }

    @Test
    public void testThinObstaclesStillAllowAPath() {
        int[][] grid = {
                {0, 0, 1, 0},
                {1, 0, 1, 0},
                {1, 0, 0, 0},
                {1, 1, 1, 0}
        };
        assertEquals(shortestPathOracle(grid), test.shortestPathBinaryMatrix(grid));
    }

    @Test
    public void testHistoricalFiveByFiveCase() {
        assertEquals(6, test.shortestPathBinaryMatrix(new int[][]{
                {0, 0, 0, 0, 0},
                {1, 1, 0, 1, 0},
                {1, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}
        }));
    }

    @Test
    public void testDiagonalAndOrthogonalChoicesWithDuplicateFrontiers() {
        int[][] grid = {
                {0, 0, 1, 0, 0, 0},
                {0, 1, 0, 1, 1, 0},
                {0, 0, 0, 0, 1, 0},
                {1, 1, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1},
                {0, 1, 1, 0, 0, 0}
        };
        assertEquals(shortestPathOracle(grid), test.shortestPathBinaryMatrix(grid));
    }

    @Test
    public void testInputIsNotMutated() {
        int[][] grid = {
                {0, 0, 1, 0},
                {1, 0, 0, 0},
                {0, 1, 0, 1},
                {0, 0, 0, 0}
        };
        int[][] before = copy(grid);
        assertEquals(shortestPathOracle(grid), test.shortestPathBinaryMatrix(grid));
        for (int row = 0; row < grid.length; row++) {
            assertArrayEquals(before[row], grid[row]);
        }
    }

    @Test
    public void testRepeatedCallsUseFreshVisitedState() {
        int[][] first = {
                {0, 1, 0},
                {0, 0, 0},
                {1, 0, 0}
        };
        int[][] second = {
                {0, 0, 0, 0},
                {1, 1, 1, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 0}
        };
        int firstExpected = shortestPathOracle(first);
        int secondExpected = shortestPathOracle(second);
        assertEquals(firstExpected, test.shortestPathBinaryMatrix(first));
        assertEquals(secondExpected, test.shortestPathBinaryMatrix(second));
        assertEquals(firstExpected, test.shortestPathBinaryMatrix(first));
    }

    @Test
    public void testMaximumOfficialDimensionAllZeros() {
        int[][] grid = new int[100][100];
        assertEquals(100, test.shortestPathBinaryMatrix(grid));
    }

    @Test
    public void testMaximumOfficialDimensionCheckerboard() {
        int size = 100;
        int[][] grid = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                grid[row][column] = (row + column) % 2;
            }
        }
        assertEquals(100, test.shortestPathBinaryMatrix(grid));
    }

    @Test
    public void testMaximumDimensionWithBlockedEndpointReturnsNegativeOne() {
        int[][] grid = new int[100][100];
        grid[99][99] = 1;
        assertEquals(-1, test.shortestPathBinaryMatrix(grid));
    }

    @ParameterizedTest(name = "seeded square grid {index}")
    @MethodSource("seededGridCases")
    public void testSeededSquareGridsAgainstIndependentBfsOracle(int[][] grid) {
        assertEquals(shortestPathOracle(grid), test.shortestPathBinaryMatrix(copy(grid)));
    }

    static Stream<Arguments> seededGridCases() {
        List<Arguments> cases = new ArrayList<>();
        Random random = new Random(1091L);
        for (int sample = 0; sample < 40; sample++) {
            int size = 1 + random.nextInt(10);
            int[][] grid = new int[size][size];
            for (int row = 0; row < size; row++) {
                for (int column = 0; column < size; column++) {
                    grid[row][column] = random.nextInt(2);
                }
            }
            if (sample % 2 == 0) {
                grid[0][0] = 0;
                grid[size - 1][size - 1] = 0;
            }
            cases.add(Arguments.of((Object) grid));
        }
        return cases.stream();
    }

    @Test
    public void testEveryThreeByThreeBinaryGridAgainstIndependentOracle() {
        for (int mask = 0; mask < (1 << 9); mask++) {
            int[][] grid = new int[3][3];
            for (int cell = 0; cell < 9; cell++) {
                grid[cell / 3][cell % 3] = (mask >>> cell) & 1;
            }
            assertEquals(shortestPathOracle(grid), test.shortestPathBinaryMatrix(grid),
                    "mask=" + mask);
        }
    }

    private static int shortestPathOracle(int[][] grid) {
        int size = grid.length;
        if (grid[0][0] != 0 || grid[size - 1][size - 1] != 0) {
            return -1;
        }

        int[][] distance = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                distance[row][column] = -1;
            }
        }
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});
        distance[0][0] = 1;
        while (!queue.isEmpty()) {
            int[] current = queue.remove();
            if (current[0] == size - 1 && current[1] == size - 1) {
                return distance[current[0]][current[1]];
            }
            for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
                for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                    if (rowOffset == 0 && columnOffset == 0) {
                        continue;
                    }
                    int nextRow = current[0] + rowOffset;
                    int nextColumn = current[1] + columnOffset;
                    if (nextRow >= 0 && nextRow < size && nextColumn >= 0 && nextColumn < size
                            && distance[nextRow][nextColumn] == -1 && grid[nextRow][nextColumn] == 0) {
                        distance[nextRow][nextColumn] = distance[current[0]][current[1]] + 1;
                        queue.add(new int[]{nextRow, nextColumn});
                    }
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

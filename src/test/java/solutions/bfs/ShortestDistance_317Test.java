package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Contract tests for LeetCode 317. The expected result is computed by a small,
 * independent shortest-path oracle: for every empty land it runs a fresh BFS to
 * every building, treating buildings and obstacles as non-traversable cells.
 */
public class ShortestDistance_317Test {

    private final ShortestDistance_317 solution = new ShortestDistance_317();

    @Test
    void officialExampleOne() {
        assertDistance(7, new int[][]{{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}});
    }

    @Test
    void officialExampleTwoSingleRow() {
        assertDistance(1, new int[][]{{1, 0}});
    }

    @Test
    void officialExampleThreeNoEmptyLand() {
        assertDistance(-1, new int[][]{{1}});
    }

    @Test
    void nullGridUsesDocumentedImplementationGuard() {
        assertEquals(-1, solution.shortestDistance(null));
    }

    @Test
    void emptyGridUsesDocumentedImplementationGuard() {
        assertEquals(-1, solution.shortestDistance(new int[][]{}));
    }

    @Test
    void emptyRowUsesDocumentedImplementationGuard() {
        assertEquals(-1, solution.shortestDistance(new int[][]{{}}));
    }

    @Test
    void oneBuildingChoosesNearestAdjacentLand() {
        assertDistance(1, new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}});
    }

    @Test
    void oneBuildingUsesManhattanDistanceOnAnOpenRow() {
        assertDistance(1, new int[][]{{0, 0, 1, 0, 0, 0, 0}});
    }

    @Test
    void oneBuildingMustRouteAroundAnObstacle() {
        assertDistance(1, new int[][]{{0, 2, 1}, {0, 0, 0}});
    }

    @Test
    void twoBuildingsHaveAUniqueBestMeetingLand() {
        assertDistance(2, new int[][]{{1, 0, 1}});
    }

    @Test
    void twoBuildingsHaveSeveralTiedBestLands() {
        assertDistance(3, new int[][]{{1, 0, 0, 1}});
    }

    @Test
    void twoBuildingsUseADetourAroundAnObstacle() {
        assertDistance(4, new int[][]{{1, 2, 1}, {0, 0, 0}});
    }

    @Test
    void obstacleCanMakeAllCandidateLandsInvalid() {
        assertDistance(-1, new int[][]{{1, 2, 0}, {2, 2, 2}, {0, 2, 1}});
    }

    @Test
    void buildingsSeparatedByObstacleWithoutAnyEmptyLandReturnMinusOne() {
        assertDistance(-1, new int[][]{{1, 2, 1}});
    }

    @Test
    void allBuildingsWithNoEmptyLandReturnMinusOne() {
        assertDistance(-1, new int[][]{{1, 1}, {1, 1}});
    }

    @Test
    void aSingleBuildingAndIsolatedEmptyLandReturnMinusOne() {
        assertDistance(-1, new int[][]{{1, 2}, {2, 0}});
    }

    @Test
    void rectangularGridWithBuildingsOnOppositeCorners() {
        assertDistance(5, new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 1}});
    }

    @Test
    void singleColumnHasTheSameDistanceSemanticsAsRows() {
        assertDistance(3, new int[][]{{1}, {0}, {0}, {1}});
    }

    @Test
    void obstacleMazeFindsTheShortestCommonLand() {
        assertDistance(16, new int[][]{
                {1, 0, 2, 0, 1},
                {0, 0, 2, 0, 0},
                {0, 0, 0, 0, 0},
                {2, 2, 0, 2, 0},
                {1, 0, 0, 0, 1}
        });
    }

    @Test
    void obstaclesCanLeaveOnlyOneCommonCandidate() {
        assertDistance(8, new int[][]{
                {1, 2, 0, 2, 1},
                {0, 2, 0, 2, 0},
                {0, 0, 0, 0, 0}
        });
    }

    @Test
    void duplicateBuildingDistancesStillSumIndependently() {
        assertDistance(5, new int[][]{
                {1, 0, 1},
                {0, 0, 0},
                {1, 0, 0}
        });
    }

    @Test
    void symmetricFourBuildingsHaveACentralTie() {
        assertDistance(12, new int[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        });
    }

    @Test
    void repeatedCallsOnOneInstanceDoNotLeakAccumulatedDistances() {
        int[][] first = {{1, 0, 1}, {0, 0, 0}};
        int[][] second = {{1, 0, 0, 1}};
        assertDistance(2, first);
        assertDistance(3, second);
        assertDistance(2, first);
    }

    @Test
    void inputGridIsNotMutated() {
        int[][] grid = {
                {1, 0, 2, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}
        };
        int[][] before = copy(grid);
        assertDistance(7, grid);
        assertArrayEquals(before, grid);
    }

    @Test
    void exhaustiveTwoByThreeTernaryGridsMatchIndependentOracle() {
        for (int encoded = 0; encoded < 729; encoded++) {
            int value = encoded;
            int[][] grid = new int[2][3];
            boolean hasBuilding = false;
            for (int row = 0; row < 2; row++) {
                for (int column = 0; column < 3; column++) {
                    grid[row][column] = value % 3;
                    hasBuilding |= grid[row][column] == 1;
                    value /= 3;
                }
            }
            if (hasBuilding) {
                assertDistance(oracle(grid), grid);
            }
        }
    }

    @Test
    void seededSmallGridsCoverMixedReachabilityAndTies() {
        Random random = new Random(317_2026L);
        for (int caseNumber = 0; caseNumber < 120; caseNumber++) {
            int rows = 1 + random.nextInt(5);
            int columns = 1 + random.nextInt(5);
            int[][] grid = new int[rows][columns];
            int buildingCount = 0;
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    grid[row][column] = random.nextInt(3);
                    buildingCount += grid[row][column] == 1 ? 1 : 0;
                }
            }
            if (buildingCount == 0) {
                grid[0][0] = 1;
            }
            assertDistance(oracle(grid), grid);
        }
    }

    @Test
    void maximumOfficialSquareBoundaryWithFourBuildings() {
        int size = 50;
        int[][] grid = new int[size][size];
        grid[0][0] = 1;
        grid[0][size - 1] = 1;
        grid[size - 1][0] = 1;
        grid[size - 1][size - 1] = 1;
        assertEquals(196, solution.shortestDistance(grid));
    }

    @Test
    void maximumOfficialSingleRowBoundary() {
        int[][] grid = new int[1][50];
        grid[0][0] = 1;
        grid[0][49] = 1;
        assertDistance(49, grid);
    }

    @Test
    void maximumOfficialSingleColumnBoundary() {
        int[][] grid = new int[50][1];
        grid[0][0] = 1;
        grid[49][0] = 1;
        assertDistance(49, grid);
    }

    @Test
    void severalBuildingsOnAnOpenFiveByFiveGrid() {
        int[][] grid = new int[5][5];
        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 5; column++) {
                grid[row][column] = row == 0 ? 1 : 0;
            }
        }
        assertDistance(11, grid);
    }

    private void assertDistance(int expected, int[][] grid) {
        assertEquals(expected, oracle(grid), "independent oracle sanity check");
        assertEquals(expected, solution.shortestDistance(grid));
    }

    private static int oracle(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        int rows = grid.length;
        int columns = grid[0].length;
        List<int[]> buildings = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (grid[row][column] == 1) {
                    buildings.add(new int[]{row, column});
                }
            }
        }
        if (buildings.isEmpty()) {
            return -1;
        }

        int answer = Integer.MAX_VALUE;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (grid[row][column] != 0) {
                    continue;
                }
                int totalDistance = 0;
                boolean reachesEveryBuilding = true;
                for (int[] building : buildings) {
                    int distance = distanceToBuilding(grid, row, column, building[0], building[1]);
                    if (distance < 0) {
                        reachesEveryBuilding = false;
                        break;
                    }
                    totalDistance += distance;
                }
                if (reachesEveryBuilding) {
                    answer = Math.min(answer, totalDistance);
                }
            }
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private static int distanceToBuilding(int[][] grid, int startRow, int startColumn,
            int targetRow, int targetColumn) {
        int rows = grid.length;
        int columns = grid[0].length;
        boolean[][] visited = new boolean[rows][columns];
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startRow, startColumn, 0});
        visited[startRow][startColumn] = true;
        int[] rowDirections = {1, -1, 0, 0};
        int[] columnDirections = {0, 0, 1, -1};

        while (!queue.isEmpty()) {
            int[] current = queue.remove();
            if (current[0] == targetRow && current[1] == targetColumn) {
                return current[2];
            }
            for (int direction = 0; direction < 4; direction++) {
                int nextRow = current[0] + rowDirections[direction];
                int nextColumn = current[1] + columnDirections[direction];
                if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns
                        || visited[nextRow][nextColumn]) {
                    continue;
                }
                boolean isTarget = nextRow == targetRow && nextColumn == targetColumn;
                if (grid[nextRow][nextColumn] == 0 || isTarget) {
                    visited[nextRow][nextColumn] = true;
                    queue.add(new int[]{nextRow, nextColumn, current[2] + 1});
                }
            }
        }
        return -1;
    }

    private static int[][] copy(int[][] grid) {
        return Arrays.stream(grid).map(int[]::clone).toArray(int[][]::new);
    }
}

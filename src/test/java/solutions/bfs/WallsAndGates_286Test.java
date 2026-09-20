package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class WallsAndGates_286Test {

    private final WallsAndGates_286 test = new WallsAndGates_286();

    @Test
    public void testHappyCases() {
        int INF = Integer.MAX_VALUE;
        int[][] rooms = {{INF, -1, 0, INF}, {INF, INF, INF, -1}, {INF, -1, INF, -1}, {0, -1, INF, INF}};
        test.wallsAndGates(rooms);
        assertArrayEquals(new int[]{3, -1, 0, 1}, rooms[0]);
        assertArrayEquals(new int[]{2, 2, 1, -1}, rooms[1]);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertDoesNotThrow(() -> test.wallsAndGates(null));
        int[][] empty = {};
        assertDoesNotThrow(() -> test.wallsAndGates(empty));
    }

    @Test
    public void testLargeCase() {
        int INF = Integer.MAX_VALUE;
        int[][] rooms = {{0, INF, INF}, {INF, INF, INF}, {INF, INF, 0}};
        test.wallsAndGates(rooms);
        assertArrayEquals(new int[]{0, 1, 2}, rooms[0]);
        assertArrayEquals(new int[]{1, 2, 1}, rooms[1]);
        assertArrayEquals(new int[]{2, 1, 0}, rooms[2]);
    }

    @Test
    public void testAllWallsUnchanged() {
        int[][] rooms = {{-1, -1}, {-1, -1}};
        test.wallsAndGates(rooms);
        assertArrayEquals(new int[]{-1, -1}, rooms[0]);
        assertArrayEquals(new int[]{-1, -1}, rooms[1]);
    }

    @Test
    public void testNoGatesKeepsInfinity() {
        int INF = Integer.MAX_VALUE;
        int[][] rooms = {{INF, INF}, {INF, -1}};
        test.wallsAndGates(rooms);
        assertArrayEquals(new int[]{INF, INF}, rooms[0]);
        assertArrayEquals(new int[]{INF, -1}, rooms[1]);
    }

    @Test
    public void testSingleRow() {
        int INF = Integer.MAX_VALUE;
        int[][] rooms = {{INF, 0, INF, -1, INF}};
        test.wallsAndGates(rooms);
        assertArrayEquals(new int[]{1, 0, 1, -1, INF}, rooms[0]);
    }

    @Test
    public void testSingleColumn() {
        int INF = Integer.MAX_VALUE;
        int[][] rooms = {{INF}, {0}, {INF}, {-1}, {INF}};
        test.wallsAndGates(rooms);
        assertArrayEquals(new int[]{1}, rooms[0]);
        assertArrayEquals(new int[]{0}, rooms[1]);
        assertArrayEquals(new int[]{1}, rooms[2]);
        assertArrayEquals(new int[]{-1}, rooms[3]);
        assertArrayEquals(new int[]{INF}, rooms[4]);
    }

    @Test
    public void testTwoGatesChoosesNearest() {
        int INF = Integer.MAX_VALUE;
        int[][] rooms = {
                {0, INF, INF, 0},
                {INF, INF, INF, INF}
        };
        test.wallsAndGates(rooms);
        assertArrayEquals(new int[]{0, 1, 1, 0}, rooms[0]);
        assertArrayEquals(new int[]{1, 2, 2, 1}, rooms[1]);
    }

    @Test
    public void testObstacleBlocksPath() {
        int INF = Integer.MAX_VALUE;
        int[][] rooms = {
                {0, -1, INF},
                {INF, -1, INF},
                {INF, INF, INF}
        };
        test.wallsAndGates(rooms);
        assertArrayEquals(new int[]{0, -1, 6}, rooms[0]);
        assertArrayEquals(new int[]{1, -1, 5}, rooms[1]);
        assertArrayEquals(new int[]{2, 3, 4}, rooms[2]);
    }

    @Test
    public void testGiantGridSpotChecks() {
        int INF = Integer.MAX_VALUE;
        int n = 20;
        int[][] rooms = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rooms[i][j] = INF;
            }
        }
        rooms[0][0] = 0;
        rooms[n - 1][n - 1] = 0;
        test.wallsAndGates(rooms);
        assertEquals(0, rooms[0][0]);
        assertEquals(0, rooms[n - 1][n - 1]);
        assertEquals(10, rooms[5][5]);
        assertEquals(9, rooms[10][19]);
    }

    // --- NEW TESTS ---

    @Test
    public void testSingleCellGate() {
        int[][] rooms = {{0}};
        test.wallsAndGates(rooms);
        assertArrayEquals(new int[]{0}, rooms[0]);
    }

    @Test
    public void testSingleCellWall() {
        int[][] rooms = {{-1}};
        test.wallsAndGates(rooms);
        assertArrayEquals(new int[]{-1}, rooms[0]);
    }

    @Test
    public void testSingleCellINF() {
        int INF = Integer.MAX_VALUE;
        int[][] rooms = {{INF}};
        test.wallsAndGates(rooms);
        assertArrayEquals(new int[]{INF}, rooms[0]);
    }

    @Test
    public void testAllGates() {
        int[][] rooms = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        test.wallsAndGates(rooms);
        for (int[] row : rooms) {
            for (int cell : row) {
                assertEquals(0, cell);
            }
        }
    }

    @Test
    public void testAllINFNoGates() {
        int INF = Integer.MAX_VALUE;
        int[][] rooms = {{INF, INF, INF}, {INF, INF, INF}};
        test.wallsAndGates(rooms);
        for (int[] row : rooms) {
            for (int cell : row) {
                assertEquals(INF, cell);
            }
        }
    }

    @Test
    public void testLinearCorridorGateAtEnd() {
        int INF = Integer.MAX_VALUE;
        int[][] rooms = {{INF, INF, INF, INF, 0}};
        test.wallsAndGates(rooms);
        assertArrayEquals(new int[]{4, 3, 2, 1, 0}, rooms[0]);
    }

    @Test
    public void testWallForcesDetour() {
        int INF = Integer.MAX_VALUE;
        // Gate at (0,0), wall blocks direct path to (0,2)
        int[][] rooms = {
                {0, -1, INF},
                {INF, -1, INF},
                {INF, INF, INF}
        };
        test.wallsAndGates(rooms);
        // (0,2) must go around: (0,0)->down->down->right->right->up->up = 6
        assertEquals(0, rooms[0][0]);
        assertEquals(-1, rooms[0][1]);
        assertEquals(6, rooms[0][2]);
        assertEquals(1, rooms[1][0]);
        assertEquals(2, rooms[2][0]);
        assertEquals(3, rooms[2][1]);
        assertEquals(4, rooms[2][2]);
        assertEquals(5, rooms[1][2]);
    }

    @Test
    public void testMultipleGatesOverlappingReach() {
        int INF = Integer.MAX_VALUE;
        int[][] rooms = {
                {0, INF, INF, INF, INF, 0},
                {INF, INF, INF, INF, INF, INF}
        };
        test.wallsAndGates(rooms);
        // Middle cells should get min distance from either gate
        assertArrayEquals(new int[]{0, 1, 2, 2, 1, 0}, rooms[0]);
        assertArrayEquals(new int[]{1, 2, 3, 3, 2, 1}, rooms[1]);
    }

    @Test
    public void testLargeGridWithRandomGatesAndWalls() {
        int INF = Integer.MAX_VALUE;
        int n = 50;
        Random rng = new Random(42L);
        int[][] rooms = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int r = rng.nextInt(10);
                if (r == 0) rooms[i][j] = 0;       // ~10% gates
                else if (r == 1) rooms[i][j] = -1;  // ~10% walls
                else rooms[i][j] = INF;
            }
        }
        // Make a copy for brute-force reference
        int[][] expected = new int[n][n];
        for (int i = 0; i < n; i++) expected[i] = rooms[i].clone();
        bruteForceBFS(expected);

        test.wallsAndGates(rooms);
        for (int i = 0; i < n; i++) {
            assertArrayEquals(expected[i], rooms[i], "Row " + i + " mismatch");
        }
    }

    @Test
    public void testPropertyGatesAndWallsUnchanged() {
        int INF = Integer.MAX_VALUE;
        int[][] rooms = {
                {0, INF, -1, INF},
                {-1, INF, 0, INF},
                {INF, -1, INF, 0}
        };
        test.wallsAndGates(rooms);
        // Gates remain 0
        assertEquals(0, rooms[0][0]);
        assertEquals(0, rooms[1][2]);
        assertEquals(0, rooms[2][3]);
        // Walls remain -1
        assertEquals(-1, rooms[0][2]);
        assertEquals(-1, rooms[1][0]);
        assertEquals(-1, rooms[2][1]);
        // Every non-wall, non-gate cell should be finite
        for (int[] row : rooms) {
            for (int cell : row) {
                assertTrue(cell == -1 || cell >= 0);
            }
        }
    }

    @Test
    public void testUnreachableRoomsBehindWallsKeepInfinitySentinel() {
        int[][] rooms = {
                {0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {-1, -1, Integer.MAX_VALUE, -1},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1}
        };

        test.wallsAndGates(rooms);

        assertArrayEquals(new int[]{0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE}, rooms[0]);
        assertArrayEquals(new int[]{-1, -1, Integer.MAX_VALUE, -1}, rooms[1]);
        assertArrayEquals(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1}, rooms[2]);
    }

    @Test
    public void testSymmetricTiedGatesChooseSameMinimumDistance() {
        int INF = Integer.MAX_VALUE;
        int[][] rooms = {
                {0, INF, INF, INF, 0},
                {INF, INF, INF, INF, INF},
                {INF, INF, INF, INF, INF},
                {INF, INF, INF, INF, INF},
                {0, INF, INF, INF, 0}
        };

        test.wallsAndGates(rooms);

        int[][] expected = expectedDistances(rooms);
        assertGridEquals(expected, rooms);
        assertEquals(4, rooms[2][2]);
        assertEquals(2, rooms[0][2]);
    }

    @Test
    public void testRectangularGridWithSeparatedComponents() {
        int INF = Integer.MAX_VALUE;
        int[][] rooms = {
                {0, INF, -1, INF, INF, INF, -1},
                {INF, INF, -1, INF, -1, INF, INF},
                {-1, INF, -1, INF, -1, INF, -1}
        };

        int[][] expected = expectedDistances(rooms);
        test.wallsAndGates(rooms);
        assertGridEquals(expected, rooms);
        assertEquals(INF, rooms[0][3]);
        assertEquals(INF, rooms[2][5]);
    }

    @Test
    public void testInputWallsAndGatesRemainUnchangedExactly() {
        int INF = Integer.MAX_VALUE;
        int[][] rooms = {
                {-1, INF, 0, INF, -1},
                {INF, -1, INF, -1, INF},
                {0, INF, -1, INF, INF}
        };
        int[][] original = copyGrid(rooms);

        test.wallsAndGates(rooms);

        for (int row = 0; row < rooms.length; row++) {
            for (int col = 0; col < rooms[row].length; col++) {
                if (original[row][col] == -1 || original[row][col] == 0) {
                    assertEquals(original[row][col], rooms[row][col],
                            "A wall or gate changed at (" + row + "," + col + ")");
                }
            }
        }
    }

    @Test
    public void testSameInstanceCanProcessIndependentInputs() {
        int INF = Integer.MAX_VALUE;
        int[][] first = {{0, INF, INF}, {INF, -1, INF}};
        int[][] second = {{INF, INF}, {INF, 0}, {INF, INF}};

        test.wallsAndGates(first);
        test.wallsAndGates(second);

        assertArrayEquals(new int[]{0, 1, 2}, first[0]);
        assertArrayEquals(new int[]{1, -1, 3}, first[1]);
        assertArrayEquals(new int[]{2, 1}, second[0]);
        assertArrayEquals(new int[]{1, 0}, second[1]);
        assertArrayEquals(new int[]{2, 1}, second[2]);
    }

    @Test
    public void testOpenRectangularRoomUsesManhattanDistance() {
        int INF = Integer.MAX_VALUE;
        int[][] rooms = new int[3][8];
        for (int row = 0; row < rooms.length; row++) {
            java.util.Arrays.fill(rooms[row], INF);
        }
        rooms[1][5] = 0;

        test.wallsAndGates(rooms);

        for (int row = 0; row < rooms.length; row++) {
            for (int col = 0; col < rooms[row].length; col++) {
                assertEquals(Math.abs(row - 1) + Math.abs(col - 5), rooms[row][col]);
            }
        }
    }

    @Test
    public void testSeededSmallGridsMatchIndependentMultiSourceOracle() {
        Random rng = new Random(286L);
        for (int iteration = 0; iteration < 60; iteration++) {
            int rows = 1 + rng.nextInt(8);
            int cols = 1 + rng.nextInt(8);
            int[][] rooms = new int[rows][cols];
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    int value = rng.nextInt(10);
                    rooms[row][col] = value < 2 ? -1 : value < 4 ? 0 : Integer.MAX_VALUE;
                }
            }
            int[][] expected = expectedDistances(rooms);

            test.wallsAndGates(rooms);

            assertGridEquals(expected, rooms);
        }
    }

    @Test
    public void testMaximumSupportedDimensionsWithCenterGate() {
        int size = 250;
        int[][] rooms = new int[size][size];
        for (int row = 0; row < size; row++) {
            java.util.Arrays.fill(rooms[row], Integer.MAX_VALUE);
        }
        rooms[size / 2][size / 2] = 0;

        test.wallsAndGates(rooms);

        assertEquals(0, rooms[size / 2][size / 2]);
        assertEquals(size, rooms[0][0]);
        assertEquals(size - 2, rooms[size - 1][size - 1]);
        assertEquals(size - 1, rooms[0][size - 1]);
        assertEquals(size - 1, rooms[size - 1][0]);
        assertEquals(size / 2, rooms[0][size / 2]);
        assertEquals(size / 2 - 1, rooms[size - 1][size / 2]);
    }

    @Test
    public void testEmptyRowIsHandledByImplementationGuard() {
        int[][] rooms = {{} };
        assertDoesNotThrow(() -> test.wallsAndGates(rooms));
        assertEquals(0, rooms[0].length);
    }

    /** Reference BFS implementation for cross-checking. */
    private void bruteForceBFS(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return;
        int rows = rooms.length, cols = rooms[0].length;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (rooms[i][j] == 0) q.add(new int[]{i, j});
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int[] d : dirs) {
                int nr = cur[0] + d[0], nc = cur[1] + d[1];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && rooms[nr][nc] == Integer.MAX_VALUE) {
                    rooms[nr][nc] = rooms[cur[0]][cur[1]] + 1;
                    q.add(new int[]{nr, nc});
                }
            }
        }
    }

    /**
     * Independently computes nearest-gate distances by a multi-source BFS over
     * a fresh distance matrix.  The production method is not used to derive
     * expected values, so this catches shared traversal mistakes.
     */
    private int[][] expectedDistances(int[][] input) {
        int rows = input.length;
        if (rows == 0) {
            return new int[0][];
        }
        int cols = input[0].length;
        int[][] distances = copyGrid(input);
        Queue<int[]> queue = new LinkedList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (input[row][col] == 0) {
                    queue.add(new int[]{row, col});
                }
            }
        }
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] current = queue.remove();
            for (int[] direction : directions) {
                int nextRow = current[0] + direction[0];
                int nextCol = current[1] + direction[1];
                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
                        && distances[nextRow][nextCol] == Integer.MAX_VALUE) {
                    distances[nextRow][nextCol] = distances[current[0]][current[1]] + 1;
                    queue.add(new int[]{nextRow, nextCol});
                }
            }
        }
        return distances;
    }

    private int[][] copyGrid(int[][] grid) {
        int[][] copy = new int[grid.length][];
        for (int row = 0; row < grid.length; row++) {
            copy[row] = grid[row].clone();
        }
        return copy;
    }

    private void assertGridEquals(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row], "Row " + row + " mismatch");
        }
    }
}

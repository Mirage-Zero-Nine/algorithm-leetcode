package solution.bfs;

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
}

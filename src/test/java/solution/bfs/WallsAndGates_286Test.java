package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

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
        test.wallsAndGates(null); // should not throw
        int[][] empty = {};
        test.wallsAndGates(empty); // should not throw
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
        org.junit.jupiter.api.Assertions.assertEquals(0, rooms[0][0]);
        org.junit.jupiter.api.Assertions.assertEquals(0, rooms[n - 1][n - 1]);
        org.junit.jupiter.api.Assertions.assertEquals(10, rooms[5][5]);
        org.junit.jupiter.api.Assertions.assertEquals(9, rooms[10][19]);
    }
}

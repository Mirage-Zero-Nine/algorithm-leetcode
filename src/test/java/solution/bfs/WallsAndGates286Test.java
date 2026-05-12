package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class WallsAndGates286Test {

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
}

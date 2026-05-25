package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;
import library.Robot;
import org.junit.jupiter.api.Test;

public class CleanRoom_489Test {

    private final CleanRoom_489 test = new CleanRoom_489();

    static class MockRobot implements Robot {
        private final int[][] room;
        private int row, col, dir;
        private final Set<String> cleaned = new HashSet<>();
        private final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        MockRobot(int[][] room, int startRow, int startCol) {
            this.room = room; this.row = startRow; this.col = startCol; this.dir = 0;
        }

        @Override
        public boolean move() {
            int nr = row + dirs[dir][0], nc = col + dirs[dir][1];
            if (nr >= 0 && nr < room.length && nc >= 0 && nc < room[0].length && room[nr][nc] == 1) {
                row = nr; col = nc; return true;
            }
            return false;
        }

        @Override
        public void turnLeft() { dir = (dir + 3) % 4; }

        @Override
        public void turnRight() { dir = (dir + 1) % 4; }

        @Override
        public void clean() { cleaned.add(row + "," + col); }

        int getCleanedCount() { return cleaned.size(); }
    }

    @Test
    public void testHappyCases() {
        int[][] room = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        MockRobot robot = new MockRobot(room, 1, 1);
        test.cleanRoom(robot);
        assertEquals(6, robot.getCleanedCount());
    }

    @Test
    public void testEdgeCases() {
        int[][] room = {{1}};
        MockRobot robot = new MockRobot(room, 0, 0);
        test.cleanRoom(robot);
        assertEquals(1, robot.getCleanedCount());
    }

    @Test
    public void testLargeCase() {
        int[][] room = {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};
        MockRobot robot = new MockRobot(room, 1, 1);
        test.cleanRoom(robot);
        assertEquals(12, robot.getCleanedCount());
    }

    @Test
    public void testSingleCell() {
        int[][] room = {{1}};
        MockRobot robot = new MockRobot(room, 0, 0);
        test.cleanRoom(robot);
        assertEquals(1, robot.getCleanedCount());
    }

    @Test
    public void testSingleRow() {
        int[][] room = {{1, 1, 1, 1, 1}};
        MockRobot robot = new MockRobot(room, 0, 2);
        test.cleanRoom(robot);
        assertEquals(5, robot.getCleanedCount());
    }

    @Test
    public void testSingleColumn() {
        int[][] room = {{1}, {1}, {1}, {1}};
        MockRobot robot = new MockRobot(room, 2, 0);
        test.cleanRoom(robot);
        assertEquals(4, robot.getCleanedCount());
    }

    @Test
    public void testRoomWithObstacles() {
        int[][] room = {
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        MockRobot robot = new MockRobot(room, 0, 0);
        test.cleanRoom(robot);
        assertEquals(10, robot.getCleanedCount());
    }

    @Test
    public void testLShapedRoom() {
        int[][] room = {
            {1, 0, 0},
            {1, 0, 0},
            {1, 1, 1}
        };
        MockRobot robot = new MockRobot(room, 2, 0);
        test.cleanRoom(robot);
        assertEquals(5, robot.getCleanedCount());
    }

    @Test
    public void testCornerStart() {
        int[][] room = {{1, 1}, {1, 1}};
        MockRobot robot = new MockRobot(room, 0, 0);
        test.cleanRoom(robot);
        assertEquals(4, robot.getCleanedCount());
    }

    @Test
    public void testNarrowCorridor() {
        int[][] room = {
            {0, 1, 0},
            {0, 1, 0},
            {0, 1, 0},
            {0, 1, 0},
            {0, 1, 0}
        };
        MockRobot robot = new MockRobot(room, 2, 1);
        test.cleanRoom(robot);
        assertEquals(5, robot.getCleanedCount());
    }

    @Test
    public void testGiantRoom() {
        int n = 20;
        int[][] room = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                room[i][j] = 1;
        MockRobot robot = new MockRobot(room, n / 2, n / 2);
        test.cleanRoom(robot);
        assertEquals(n * n, robot.getCleanedCount());
    }

    // --- NEW TESTS ---

    @Test
    public void testSingleCellNoObstacles() {
        // 1x1 room, trivial case - robot just cleans the one cell
        int[][] room = {{1}};
        MockRobot robot = new MockRobot(room, 0, 0);
        test.cleanRoom(robot);
        assertEquals(1, robot.getCleanedCount());
        assert robot.cleaned.contains("0,0");
    }

    @Test
    public void testAllReachableCleaned() {
        // 4x4 fully open room - property: every reachable empty cell is cleaned
        int[][] room = {
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1}
        };
        MockRobot robot = new MockRobot(room, 0, 0);
        test.cleanRoom(robot);
        // Every cell should be cleaned
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                assert robot.cleaned.contains(i + "," + j) : "Cell " + i + "," + j + " not cleaned";
        assertEquals(16, robot.getCleanedCount());
    }

    @Test
    public void testBlockedCellsNotCleaned() {
        // Property: blocked cells are NOT cleaned, reachable cells ARE cleaned
        int[][] room = {
            {1, 1, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        MockRobot robot = new MockRobot(room, 0, 0);
        test.cleanRoom(robot);
        // Blocked cells must not be cleaned
        assert !robot.cleaned.contains("0,2") : "Blocked cell 0,2 was cleaned";
        assert !robot.cleaned.contains("1,1") : "Blocked cell 1,1 was cleaned";
        assert !robot.cleaned.contains("1,2") : "Blocked cell 1,2 was cleaned";
        // Reachable cells from (0,0): (0,0),(0,1),(1,0),(2,0),(2,1),(2,2),(2,3),(1,3),(0,3)
        assertEquals(9, robot.getCleanedCount());
    }

    @Test
    public void testLShapeRoomStartAtCorner() {
        // L-shape room, robot starts at top-left corner
        int[][] room = {
            {1, 1, 1},
            {1, 0, 0},
            {1, 0, 0},
            {1, 1, 1}
        };
        MockRobot robot = new MockRobot(room, 0, 0);
        test.cleanRoom(robot);
        assertEquals(8, robot.getCleanedCount());
        assert !robot.cleaned.contains("1,1");
        assert !robot.cleaned.contains("1,2");
        assert !robot.cleaned.contains("2,1");
        assert !robot.cleaned.contains("2,2");
    }

    @Test
    public void testLongThinCorridor() {
        // 1x10 corridor, robot starts at one end
        int[][] room = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
        MockRobot robot = new MockRobot(room, 0, 0);
        test.cleanRoom(robot);
        assertEquals(10, robot.getCleanedCount());
    }

    @Test
    public void testRobotStartCenter() {
        // 5x5 open room, robot starts at center
        int[][] room = new int[5][5];
        for (int[] r : room) java.util.Arrays.fill(r, 1);
        MockRobot robot = new MockRobot(room, 2, 2);
        test.cleanRoom(robot);
        assertEquals(25, robot.getCleanedCount());
    }

    @Test
    public void testRobotStartAgainstWall() {
        // 3x5 room, robot starts at (0,2) - top wall, middle column
        int[][] room = {
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1}
        };
        MockRobot robot = new MockRobot(room, 0, 2);
        test.cleanRoom(robot);
        assertEquals(15, robot.getCleanedCount());
    }

    @Test
    public void testBlockedCellsUnchanged() {
        // Property: blocked cells remain blocked (not in cleaned set) regardless of adjacency
        int[][] room = {
            {1, 0, 1},
            {0, 1, 0},
            {1, 0, 1}
        };
        // Only center and corners reachable? Center connects to nothing except itself
        // Actually from (1,1): up=(0,1)=0 blocked, right=(1,2)=0 blocked, down=(2,1)=0 blocked, left=(1,0)=0 blocked
        // So only (1,1) is reachable from start (1,1)
        MockRobot robot = new MockRobot(room, 1, 1);
        test.cleanRoom(robot);
        assertEquals(1, robot.getCleanedCount());
        // All blocked cells must NOT be cleaned
        assert !robot.cleaned.contains("0,1");
        assert !robot.cleaned.contains("1,0");
        assert !robot.cleaned.contains("1,2");
        assert !robot.cleaned.contains("2,1");
    }
}

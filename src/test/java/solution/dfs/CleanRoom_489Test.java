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
}

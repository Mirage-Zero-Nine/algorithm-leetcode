package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import library.Robot;
import org.junit.jupiter.api.Test;

/** Contract and traversal tests for the blind robot-cleaner DFS. */
public class CleanRoom_489Test {

    private final CleanRoom_489 solution = new CleanRoom_489();

    /** A deterministic robot simulator; its clean set is intentionally independent of the solver. */
    static final class MockRobot implements Robot {
        private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        private final int[][] room;
        private int row;
        private int column;
        private int direction;
        private final Set<String> cleaned = new HashSet<>();
        private int cleanCalls;
        private int blockedMoveAttempts;

        MockRobot(int[][] room, int startRow, int startColumn) {
            this(room, startRow, startColumn, 0);
        }

        MockRobot(int[][] room, int startRow, int startColumn, int initialDirection) {
            this.room = room;
            this.row = startRow;
            this.column = startColumn;
            this.direction = Math.floorMod(initialDirection, 4);
        }

        @Override
        public boolean move() {
            int nextRow = row + DIRECTIONS[direction][0];
            int nextColumn = column + DIRECTIONS[direction][1];
            if (nextRow < 0 || nextRow >= room.length
                    || nextColumn < 0 || nextColumn >= room[0].length
                    || room[nextRow][nextColumn] == 0) {
                blockedMoveAttempts++;
                return false;
            }
            row = nextRow;
            column = nextColumn;
            return true;
        }

        @Override
        public void turnLeft() {
            direction = (direction + 3) % 4;
        }

        @Override
        public void turnRight() {
            direction = (direction + 1) % 4;
        }

        @Override
        public void clean() {
            cleanCalls++;
            cleaned.add(key(row, column));
        }

        int row() {
            return row;
        }

        int column() {
            return column;
        }

        int direction() {
            return direction;
        }

        int cleanCalls() {
            return cleanCalls;
        }

        int blockedMoveAttempts() {
            return blockedMoveAttempts;
        }
    }

    @Test
    void singleCellIsCleanedAndRobotReturnsHome() {
        assertRoomCleaned(new int[][]{{1}}, 0, 0, 0);
    }

    @Test
    void singleRowFromLeftEnd() {
        assertRoomCleaned(new int[][]{{1, 1, 1, 1, 1}}, 0, 0, 0);
    }

    @Test
    void singleRowFromMiddle() {
        assertRoomCleaned(new int[][]{{1, 1, 1, 1, 1, 1, 1}}, 0, 3, 0);
    }

    @Test
    void singleColumnFromTopEnd() {
        assertRoomCleaned(new int[][]{{1}, {1}, {1}, {1}, {1}}, 0, 0, 0);
    }

    @Test
    void singleColumnFromMiddle() {
        assertRoomCleaned(new int[][]{{1}, {1}, {1}, {1}, {1}, {1}}, 3, 0, 0);
    }

    @Test
    void twoByTwoOpenRoom() {
        assertRoomCleaned(new int[][]{{1, 1}, {1, 1}}, 1, 1, 0);
    }

    @Test
    void rectangularOpenRoomFromCorner() {
        assertRoomCleaned(new int[][]{{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}}, 0, 0, 0);
    }

    @Test
    void rectangularOpenRoomFromCenter() {
        assertRoomCleaned(new int[][]{
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1}
        }, 2, 2, 0);
    }

    @Test
    void LShapedRoom() {
        assertRoomCleaned(new int[][]{
            {1, 1, 1, 0},
            {1, 0, 1, 0},
            {1, 0, 1, 1},
            {1, 1, 1, 1}
        }, 0, 0, 0);
    }

    @Test
    void TShapedBranches() {
        assertRoomCleaned(new int[][]{
            {0, 1, 0, 0, 0},
            {1, 1, 1, 1, 1},
            {0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0}
        }, 1, 2, 0);
    }

    @Test
    void windingCorridorWithManyDeadEnds() {
        assertRoomCleaned(new int[][]{
            {1, 1, 0, 1, 1, 1, 0},
            {0, 1, 0, 1, 0, 1, 0},
            {1, 1, 1, 1, 0, 1, 1},
            {1, 0, 0, 1, 1, 1, 0},
            {1, 1, 1, 0, 0, 1, 0}
        }, 0, 0, 0);
    }

    @Test
    void loopRequiresVisitedGuard() {
        assertRoomCleaned(new int[][]{
            {1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1}
        }, 2, 2, 0);
    }

    @Test
    void roomsWithInternalPillarsAndMultipleRoutes() {
        assertRoomCleaned(new int[][]{
            {1, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 0, 1},
            {1, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 1}
        }, 4, 5, 0);
    }

    @Test
    void blockedCellsAreNeverCleaned() {
        int[][] room = {
            {1, 0, 1},
            {0, 1, 0},
            {1, 0, 1}
        };
        MockRobot robot = run(room, 1, 1, 0);
        assertEquals(Set.of("1,1"), robot.cleaned);
        assertEquals(1, robot.cleanCalls());
    }

    @Test
    void disconnectedOpenComponentIsNotReachable() {
        int[][] room = {
            {1, 1, 0, 1, 1},
            {1, 1, 0, 1, 1},
            {0, 0, 0, 0, 0},
            {1, 1, 0, 1, 1}
        };
        MockRobot robot = run(room, 0, 0, 0);
        assertEquals(Set.of("0,0", "0,1", "1,0", "1,1"), robot.cleaned);
        assertFalse(robot.cleaned.contains("0,3"));
        assertFalse(robot.cleaned.contains("3,3"));
    }

    @Test
    void initialFacingRightStillUsesRelativeDirections() {
        assertRoomCleanedWithoutPose(new int[][]{
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        }, 0, 1, 1);
    }

    @Test
    void initialFacingDownStillUsesRelativeDirections() {
        assertRoomCleanedWithoutPose(new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        }, 0, 0, 2);
    }

    @Test
    void initialFacingLeftStillUsesRelativeDirections() {
        assertRoomCleanedWithoutPose(new int[][]{
            {1, 1, 1},
            {1, 1, 1},
            {0, 1, 0}
        }, 1, 1, 3);
    }

    @Test
    void repeatedCallsUseFreshTraversalState() {
        int[][] room = {
            {1, 1, 1, 0},
            {1, 0, 1, 1},
            {1, 1, 1, 1}
        };
        MockRobot first = run(room, 0, 0, 0);
        MockRobot second = run(room, 2, 3, 0);
        assertEquals(reachable(room, 0, 0), first.cleaned);
        assertEquals(reachable(room, 2, 3), second.cleaned);
    }

    @Test
    void mediumDenseRoom() {
        int[][] room = new int[10][12];
        for (int[] row : room) {
            Arrays.fill(row, 1);
        }
        for (int row = 1; row < room.length - 1; row += 2) {
            room[row][3] = 0;
            room[row][8] = 0;
        }
        assertRoomCleaned(room, 5, 6, 0);
    }

    @Test
    void largerRoomWithinLeetCodeDimensions() {
        int rows = 30;
        int columns = 40;
        int[][] room = new int[rows][columns];
        for (int[] row : room) {
            Arrays.fill(row, 1);
        }
        for (int row = 1; row < rows - 1; row += 3) {
            for (int column = 1; column < columns - 1; column += 7) {
                room[row][column] = 0;
            }
        }
        assertRoomCleaned(room, rows / 2, columns / 2, 0);
    }

    @Test
    void allCellsReachableInAThinLargeRoom() {
        int[][] room = new int[1][100];
        Arrays.fill(room[0], 1);
        assertRoomCleaned(room, 0, 50, 0);
    }

    @Test
    void robotNeverLeavesRoomOrMovesThroughAnObstacle() {
        int[][] room = {
            {1, 1, 1, 1},
            {1, 0, 1, 1},
            {1, 1, 1, 1}
        };
        MockRobot robot = run(room, 1, 2, 0);
        assertTrue(robot.blockedMoveAttempts() > 0);
        assertEquals(reachable(room, 1, 2), robot.cleaned);
        assertEquals(reachable(room, 1, 2).size(), robot.cleanCalls());
    }

    @Test
    void startInADeadEndStillCleansTheWholeComponent() {
        assertRoomCleaned(new int[][]{
            {1, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 1}
        }, 0, 0, 0);
    }

    private void assertRoomCleaned(int[][] room, int startRow, int startColumn, int initialDirection) {
        assertRoomCleaned(room, startRow, startColumn, initialDirection, true);
    }

    private void assertRoomCleanedWithoutPose(int[][] room, int startRow, int startColumn, int initialDirection) {
        assertRoomCleaned(room, startRow, startColumn, initialDirection, false);
    }

    private void assertRoomCleaned(int[][] room, int startRow, int startColumn,
                                   int initialDirection, boolean requireHome) {
        MockRobot robot = run(room, startRow, startColumn, initialDirection);
        Set<String> expected = reachable(room, startRow, startColumn);
        assertEquals(expected, robot.cleaned, "every and only reachable open cell must be cleaned");
        assertEquals(expected.size(), robot.cleanCalls(), "each reachable cell is cleaned exactly once");
        if (requireHome) {
            assertEquals(startRow, robot.row(), "backtracking must return to the start row");
            assertEquals(startColumn, robot.column(), "backtracking must return to the start column");
            assertEquals(initialDirection, robot.direction(), "backtracking must restore the initial orientation");
        }
        for (String cell : robot.cleaned) {
            String[] coordinates = cell.split(",");
            assertEquals(1, room[Integer.parseInt(coordinates[0])][Integer.parseInt(coordinates[1])]);
        }
    }

    private MockRobot run(int[][] room, int startRow, int startColumn, int initialDirection) {
        MockRobot robot = new MockRobot(room, startRow, startColumn, initialDirection);
        solution.cleanRoom(robot);
        return robot;
    }

    /** Independent BFS oracle for the cells physically reachable from the start. */
    private Set<String> reachable(int[][] room, int startRow, int startColumn) {
        Set<String> visited = new HashSet<>();
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startRow, startColumn});
        visited.add(key(startRow, startColumn));
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] cell = queue.remove();
            for (int[] direction : directions) {
                int nextRow = cell[0] + direction[0];
                int nextColumn = cell[1] + direction[1];
                if (nextRow >= 0 && nextRow < room.length
                        && nextColumn >= 0 && nextColumn < room[0].length
                        && room[nextRow][nextColumn] == 1
                        && visited.add(key(nextRow, nextColumn))) {
                    queue.add(new int[]{nextRow, nextColumn});
                }
            }
        }
        return visited;
    }

    private static String key(int row, int column) {
        return row + "," + column;
    }
}

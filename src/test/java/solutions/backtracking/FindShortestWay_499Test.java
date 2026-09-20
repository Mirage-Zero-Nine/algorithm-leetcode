package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Contract tests for the rolling-ball solution to Maze III. */
class FindShortestWay_499Test {
    private final FindShortestWay_499 solution = new FindShortestWay_499();

    @Test
    void testBasic() {
        assertResult(new int[][]{{0, 0, 0, 0, 0}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1}, {0, 1, 0, 0, 0}}, new int[]{4, 3}, new int[]{0, 1}, "lul");
    }

    @Test
    void testOfficialThirdExample() {
        assertResult(new int[][]{{0, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 0, 0, 1, 0},
                        {0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 1}},
                new int[]{0, 4}, new int[]{3, 5}, "dldr");
    }

    @Test
    void testNoPath() {
        assertResult(new int[][]{{0, 0, 0, 0, 0}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1}, {0, 1, 0, 0, 0}}, new int[]{4, 3}, new int[]{3, 0}, "impossible");
    }

    @Test
    void testSimple() {
        assertResult(new int[][]{{0, 0}, {0, 0}}, new int[]{0, 0}, new int[]{1, 1}, "dr");
    }

    @Test
    void testSamePosition() {
        assertResult(new int[][]{{0, 0}, {0, 0}}, new int[]{0, 0}, new int[]{0, 0}, "");
    }

    @Test
    void testLargerMaze() {
        assertResult(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, new int[]{0, 0}, new int[]{2, 2}, "dr");
    }

    @Test
    void testHoleOnPath() {
        assertResult(new int[][]{{0, 0, 0, 0, 0}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1}, {0, 1, 0, 0, 0}}, new int[]{4, 3}, new int[]{0, 1}, "lul");
    }

    @Test
    void testSurroundedByWalls() {
        assertResult(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}, new int[]{1, 1}, new int[]{1, 1}, "");
    }

    @Test
    void testHorizontalRoll() {
        assertResult(new int[][]{{0, 0, 0, 0, 0}}, new int[]{0, 0}, new int[]{0, 4}, "r");
    }

    @Test
    void testVerticalRoll() {
        assertResult(new int[][]{{0}, {0}, {0}, {0}, {0}}, new int[]{0, 0}, new int[]{4, 0}, "d");
    }

    @Test
    void testGiantMaze() {
        int[][] maze = new int[10][10];
        assertEquals("dr", solution.findShortestWay(maze, new int[]{0, 0}, new int[]{9, 9}));
    }

    @Test
    void testHoleStopsMidRollInEveryDirection() {
        int[][] maze = new int[5][5];
        assertResult(maze, new int[]{2, 2}, new int[]{1, 2}, "u");
        assertResult(maze, new int[]{2, 2}, new int[]{3, 2}, "d");
        assertResult(maze, new int[]{2, 2}, new int[]{2, 1}, "l");
        assertResult(maze, new int[]{2, 2}, new int[]{2, 3}, "r");
    }

    @Test
    void testLexicographicallySmallestEqualDistancePath() {
        int[][] maze = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        assertResult(maze, new int[]{2, 2}, new int[]{0, 0}, "lu");
    }

    @Test
    void testDistanceBeatsFewerDirectionChanges() {
        // luld travels five cells in four rolls; dlu uses only three rolls but travels seven cells.
        int[][] maze = {{1, 1, 1, 1, 1, 1}, {1, 0, 0, 0, 0, 1}, {1, 0, 1, 0, 0, 1},
                {1, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1}};
        assertResult(maze, new int[]{2, 4}, new int[]{2, 1}, "luld");
    }

    @Test
    void testBranchingMazeChoosesShortestRoute() {
        assertResult(new int[][]{{1, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}}, new int[]{1, 1}, new int[]{3, 3}, "dr");
    }

    @Test
    void testDisconnectedCorridors() {
        assertResult(new int[][]{{1, 1, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1}, {1, 1, 1, 1, 1}}, new int[]{1, 1}, new int[]{3, 3}, "impossible");
    }

    @Test
    void testWallForcesTurns() {
        assertResult(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}, new int[]{0, 0}, new int[]{2, 2}, "dr");
    }

    @Test
    void testLongDetourAroundBarrier() {
        assertResult(new int[][]{{0, 0, 0, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}, {0, 1, 0, 0}},
                new int[]{3, 0}, new int[]{0, 3}, "ur");
    }

    @Test
    void testRectangularMazeWithForcedMultipleTurns() {
        int[][] maze = {{1, 1, 1, 1, 1, 1, 1, 1}, {1, 0, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 0, 1}};
        assertResult(maze, new int[]{1, 1}, new int[]{4, 6}, "drurd");
    }

    @Test
    void testAdjacentHole() {
        assertResult(new int[][]{{1, 1, 1}, {1, 0, 0}, {1, 1, 1}}, new int[]{1, 1}, new int[]{1, 2}, "r");
    }

    @Test
    void testHoleInterceptedBeforeWall() {
        int[][] maze = {{1, 1, 1, 1, 1, 1, 1}, {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}};
        assertResult(maze, new int[]{1, 1}, new int[]{1, 3}, "r");
    }

    @Test
    void testHoleBehindWallIsNotReached() {
        int[][] maze = {{1, 1, 1, 1, 1, 1, 1}, {1, 0, 0, 1, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}};
        assertResult(maze, new int[]{1, 1}, new int[]{1, 5}, "impossible");
    }

    @Test
    void testDeadEndHole() {
        assertResult(new int[][]{{1, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 1, 1, 0, 1},
                {1, 1, 1, 0, 1}, {1, 1, 1, 1, 1}}, new int[]{1, 1}, new int[]{3, 3}, "rd");
    }

    @Test
    void testSingleCell() {
        assertResult(new int[][]{{0}}, new int[]{0, 0}, new int[]{0, 0}, "");
    }

    @Test
    void testNonWallBorderSupportedByImplementation() {
        assertResult(new int[][]{{0, 0, 0}, {0, 0, 0}}, new int[]{1, 1}, new int[]{0, 2}, "ru");
    }

    @Test
    void testRepeatedInvocationDoesNotLeakState() {
        int[][] maze = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        assertEquals("dr", solution.findShortestWay(copy(maze), new int[]{0, 0}, new int[]{2, 2}));
        assertEquals("lu", solution.findShortestWay(copy(maze), new int[]{2, 2}, new int[]{0, 0}));
    }

    @Test
    void testMaximumDocumentedMazeSize() {
        int[][] maze = new int[30][30];
        assertEquals("dr", solution.findShortestWay(maze, new int[]{0, 0}, new int[]{29, 29}));
    }

    @Test
    void testMaximumCurrentOfficialMazeDimensions() {
        int[][] maze = new int[100][100];
        for (int row = 0; row < maze.length; row++) {
            maze[row][0] = 1;
            maze[row][maze[row].length - 1] = 1;
        }
        for (int column = 0; column < maze[0].length; column++) {
            maze[0][column] = 1;
            maze[maze.length - 1][column] = 1;
        }
        assertEquals("dr", solution.findShortestWay(maze, new int[]{1, 1}, new int[]{98, 98}));
    }

    @Test
    void testSingleRowCorridorWithWalls() {
        assertResult(new int[][]{{1, 1, 1, 1, 1, 1, 1}, {1, 0, 0, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1, 1, 1}},
                new int[]{1, 5}, new int[]{1, 1}, "l");
    }

    @Test
    void testInputArraysAndMazeAreNotMutated() {
        int[][] maze = {{1, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}};
        int[][] originalMaze = copy(maze);
        int[] ball = {1, 1};
        int[] hole = {3, 3};
        int[] originalBall = ball.clone();
        int[] originalHole = hole.clone();

        assertEquals("dr", solution.findShortestWay(maze, ball, hole));
        org.junit.jupiter.api.Assertions.assertArrayEquals(originalBall, ball);
        org.junit.jupiter.api.Assertions.assertArrayEquals(originalHole, hole);
        for (int row = 0; row < maze.length; row++) {
            org.junit.jupiter.api.Assertions.assertArrayEquals(originalMaze[row], maze[row]);
        }
    }

    @Test
    void testSeededRandomMazesAgainstIndependentOracle() {
        Random random = new Random(499L);
        for (int caseNumber = 0; caseNumber < 20; caseNumber++) {
            int size = 5 + random.nextInt(3);
            int[][] maze = new int[size][size];
            for (int row = 0; row < size; row++) {
                for (int column = 0; column < size; column++) {
                    maze[row][column] = random.nextInt(4) == 0 ? 1 : 0;
                }
            }
            int[] ball = {1 + random.nextInt(size - 2), 1 + random.nextInt(size - 2)};
            int[] hole = {1 + random.nextInt(size - 2), 1 + random.nextInt(size - 2)};
            maze[ball[0]][ball[1]] = 0;
            maze[hole[0]][hole[1]] = 0;
            assertResult(maze, ball, hole, reference(maze, ball, hole));
        }
    }

    private void assertResult(int[][] maze, int[] ball, int[] hole, String expected) {
        assertEquals(expected, solution.findShortestWay(copy(maze), ball.clone(), hole.clone()));
    }

    private static int[][] copy(int[][] maze) {
        int[][] result = new int[maze.length][];
        for (int row = 0; row < maze.length; row++) {
            result[row] = maze[row].clone();
        }
        return result;
    }

    /** Independent Dijkstra oracle: edges are complete rolls, with hole interception. */
    private static String reference(int[][] maze, int[] ball, int[] hole) {
        record State(int distance, String path, int row, int column) {}
        Comparator<State> order = Comparator.comparingInt(State::distance).thenComparing(State::path);
        PriorityQueue<State> queue = new PriorityQueue<>(order);
        Map<Long, State> best = new HashMap<>();
        State start = new State(0, "", ball[0], ball[1]);
        queue.offer(start);
        best.put(key(start.row(), start.column()), start);
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        char[] moves = {'u', 'r', 'd', 'l'};

        while (!queue.isEmpty()) {
            State current = queue.poll();
            if (order.compare(current, best.get(key(current.row(), current.column()))) != 0) {
                continue;
            }
            if (current.row() == hole[0] && current.column() == hole[1]) {
                return current.path();
            }
            for (int direction = 0; direction < directions.length; direction++) {
                int row = current.row();
                int column = current.column();
                int distance = current.distance();
                while (row + directions[direction][0] >= 0 && row + directions[direction][0] < maze.length
                        && column + directions[direction][1] >= 0
                        && column + directions[direction][1] < maze[0].length
                        && maze[row + directions[direction][0]][column + directions[direction][1]] == 0) {
                    row += directions[direction][0];
                    column += directions[direction][1];
                    distance++;
                    if (row == hole[0] && column == hole[1]) {
                        break;
                    }
                }
                State next = new State(distance, current.path() + moves[direction], row, column);
                State previous = best.get(key(row, column));
                if (previous == null || order.compare(next, previous) < 0) {
                    best.put(key(row, column), next);
                    queue.offer(next);
                }
            }
        }
        return "impossible";
    }

    private static long key(int row, int column) {
        return ((long) row << 32) ^ (column & 0xffffffffL);
    }
}

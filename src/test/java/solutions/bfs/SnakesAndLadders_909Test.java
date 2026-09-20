package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;
import org.junit.jupiter.api.Test;

/** Contract and regression tests for {@link SnakesAndLadders_909}. */
public class SnakesAndLadders_909Test {

    @Test
    public void officialExampleOne() {
        assertMoves(4, new int[][]{
            {-1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, 35, -1, -1, 13, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, 15, -1, -1, -1, -1}
        });
    }

    @Test
    public void officialExampleTwo() {
        assertMoves(1, new int[][]{{-1, 4}, {-1, 3}});
    }

    @Test
    public void minimumBoardReachesEndInOneRoll() {
        assertMoves(1, emptyBoard(2));
    }

    @Test
    public void noJumpsThreeByThree() {
        assertMoves(2, emptyBoard(3));
    }

    @Test
    public void noJumpsFourByFour() {
        assertMoves(3, emptyBoard(4));
    }

    @Test
    public void noJumpsMaximumBoard() {
        // The distance is ceil((20 * 20 - 1) / 6), the largest no-jump answer.
        assertMoves(67, emptyBoard(20));
    }

    @Test
    public void ladderImmediatelyAfterStart() {
        assertMoves(2, boardWithJumps(4, 2, 14));
    }

    @Test
    public void snakeCanBeAvoidedByChoosingAnotherDieResult() {
        assertMoves(3, boardWithJumps(4, 6, 2));
    }

    @Test
    public void ladderOnReversedBoustrophedonRowIsResolvedCorrectly() {
        // On a 4x4 board, label 8 is at row 2, column 0, not column 3.
        assertMoves(2, boardWithJumps(4, 8, 16));
    }

    @Test
    public void ladderAtTheLastReachableDestinationEndsTheGame() {
        assertMoves(1, boardWithJumps(4, 6, 16));
    }

    @Test
    public void onlyOneSnakeOrLadderIsTakenPerRoll() {
        // 2 -> 3 -> 1 must stop at 3 after the first roll.  Square 8 is
        // deliberately beyond the initial six destinations, so this case
        // fails if the implementation incorrectly chains 3 -> 1 immediately.
        assertMoves(3, boardWithJumps(4,
            2, 3,
            3, 1,
            4, 1,
            5, 1,
            6, 1,
            7, 1,
            8, 15));
    }

    @Test
    public void cyclicSnakesCanMakeTheTargetUnreachable() {
        assertMoves(-1, boardWithJumps(4,
            2, 3,
            3, 2,
            4, 1,
            5, 1,
            6, 1,
            7, 1,
            8, 1,
            9, 1));
    }

    @Test
    public void allInitialDestinationsCanBeTrapped() {
        assertMoves(-1, boardWithJumps(3,
            2, 1,
            3, 1,
            4, 1,
            5, 1,
            6, 1,
            7, 1));
    }

    @Test
    public void lateLadderShortensAForcedSnakeRoute() {
        assertMoves(3, boardWithJumps(5,
            2, 1,
            3, 1,
            4, 1,
            5, 1,
            6, 1,
            13, 24));
    }

    @Test
    public void multipleLaddersUseTheirMappedLabels() {
        assertMoves(2, boardWithJumps(5, 2, 20, 4, 24));
    }

    @Test
    public void snakesAndLaddersMayPointToAlreadyVisitedSquares() {
        assertMoves(3, boardWithJumps(4,
            2, 7,
            7, 2,
            3, 9,
            9, 3));
    }

    @Test
    public void jumpDirectlyToTheFinalSquare() {
        assertMoves(1, boardWithJumps(6, 2, 36));
    }

    @Test
    public void denseMaximalBoardWithAllEarlyMovesTrapped() {
        int[][] board = boardWithJumps(20,
            2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1);
        assertMoves(-1, board);
    }

    @Test
    public void denseMaximalBoardWithEveryEarlyMoveFinishing() {
        int[][] board = boardWithJumps(20,
            2, 400, 3, 400, 4, 400, 5, 400, 6, 400, 7, 400);
        assertMoves(1, board);
    }

    @Test
    public void boardIsNotMutated() {
        int[][] board = boardWithJumps(6, 2, 35, 17, 9, 31, 36);
        int[][] before = copy(board);
        new SnakesAndLadders_909().snakesAndLadders(board);
        assertArrayEquals(before, board);
    }

    @Test
    public void repeatedCallsDoNotLeakVisitedState() {
        SnakesAndLadders_909 solution = new SnakesAndLadders_909();
        int[][] jumpBoard = boardWithJumps(4, 2, 16);
        assertEquals(1, solution.snakesAndLadders(jumpBoard));
        assertEquals(3, solution.snakesAndLadders(emptyBoard(4)));
        assertEquals(1, solution.snakesAndLadders(boardWithJumps(2, 2, 4)));
    }

    @Test
    public void independentOracleCoversDeterministicSmallBoards() {
        Random random = new Random(909L);
        SnakesAndLadders_909 solution = new SnakesAndLadders_909();
        for (int caseNumber = 0; caseNumber < 120; caseNumber++) {
            int n = 2 + random.nextInt(7);
            int[][] board = randomBoard(n, random);
            int expected = referenceBfs(board);
            assertEquals(expected, solution.snakesAndLadders(board),
                "seeded board case " + caseNumber);
        }
    }

    @Test
    public void independentOracleCoversDenseMaximumBoard() {
        Random random = new Random(90920L);
        int[][] board = emptyBoard(20);
        int end = 400;
        for (int label = 2; label < end; label++) {
            if (random.nextInt(4) == 0) {
                int destination = 1 + random.nextInt(end);
                int[] position = positionForLabel(20, label);
                board[position[0]][position[1]] = destination;
            }
        }
        assertEquals(referenceBfs(board), new SnakesAndLadders_909().snakesAndLadders(board));
    }

    @Test
    public void independentOracleExhaustsSmallJumpPatterns() {
        // Three independent jump starts on a 3x3 board give 2^3 patterns;
        // the remaining squares stay ordinary, making the expected result
        // independently computable without relying on the solution's code.
        for (int mask = 0; mask < 8; mask++) {
            int[][] board = emptyBoard(3);
            if ((mask & 1) != 0) setLabel(board, 2, 1);
            if ((mask & 2) != 0) setLabel(board, 3, 8);
            if ((mask & 4) != 0) setLabel(board, 7, 1);
            int expected = referenceBfs(board);
            assertEquals(expected, new SnakesAndLadders_909().snakesAndLadders(board),
                "jump pattern " + mask);
        }
    }

    @Test
    public void everyDieDestinationIsBoundedBySix() {
        // A ladder at 7 cannot be taken from square 1 in one roll, while a
        // ladder at 6 can.  This also exercises the end-of-row mapping.
        assertMoves(2, boardWithJumps(4, 8, 16));
        assertMoves(1, boardWithJumps(4, 6, 16));
    }

    @Test
    public void ordinaryForwardProgressRemainsAvailableAfterADeadEndBranch() {
        assertMoves(2, boardWithJumps(4,
            2, 1,
            3, 1,
            4, 1,
            5, 1,
            7, 1,
            8, 16));
    }

    private static void assertMoves(int expected, int[][] board) {
        assertEquals(expected, referenceBfs(board), "test expectation must match independent oracle");
        assertEquals(expected, new SnakesAndLadders_909().snakesAndLadders(board));
    }

    private static int[][] emptyBoard(int n) {
        int[][] board = new int[n][n];
        for (int[] row : board) {
            Arrays.fill(row, -1);
        }
        return board;
    }

    private static int[][] boardWithJumps(int n, int... labelAndDestination) {
        int[][] board = emptyBoard(n);
        for (int i = 0; i < labelAndDestination.length; i += 2) {
            setLabel(board, labelAndDestination[i], labelAndDestination[i + 1]);
        }
        return board;
    }

    private static void setLabel(int[][] board, int label, int destination) {
        int[] position = positionForLabel(board.length, label);
        board[position[0]][position[1]] = destination;
    }

    private static int[] positionForLabel(int n, int label) {
        int rowFromBottom = (label - 1) / n;
        int offset = (label - 1) % n;
        int row = n - 1 - rowFromBottom;
        int column = rowFromBottom % 2 == 0 ? offset : n - 1 - offset;
        return new int[]{row, column};
    }

    private static int[][] randomBoard(int n, Random random) {
        int[][] board = emptyBoard(n);
        int end = n * n;
        for (int label = 2; label < end; label++) {
            if (random.nextInt(5) == 0) {
                setLabel(board, label, 1 + random.nextInt(end));
            }
        }
        return board;
    }

    /** Independent one-hop BFS oracle implementing the statement directly. */
    private static int referenceBfs(int[][] board) {
        int n = board.length;
        int end = n * n;
        int[] distance = new int[end + 1];
        Arrays.fill(distance, -1);
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        distance[1] = 0;
        while (!queue.isEmpty()) {
            int current = queue.remove();
            if (current == end) {
                return distance[current];
            }
            int upper = Math.min(end, current + 6);
            for (int next = current + 1; next <= upper; next++) {
                int[] position = positionForLabel(n, next);
                int destination = board[position[0]][position[1]];
                int landed = destination == -1 ? next : destination;
                if (distance[landed] == -1) {
                    distance[landed] = distance[current] + 1;
                    queue.add(landed);
                }
            }
        }
        return -1;
    }

    private static int[][] copy(int[][] board) {
        int[][] result = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            result[i] = board[i].clone();
        }
        return result;
    }
}

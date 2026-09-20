package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link CountBattleships_419}.
 *
 * <p>The two production methods intentionally have different input contracts after a call:
 * {@code countBattleships} marks visited cells as empty, whereas {@code countBattleshipsQuick}
 * leaves the board unchanged. Every comparison therefore receives its own deep copy.</p>
 */
public class CountBattleships_419Test {

    private CountBattleships_419 solution;

    @BeforeEach
    void setUp() {
        solution = new CountBattleships_419();
    }

    @Test
    void officialExampleIsCountedByBothApproaches() {
        assertBothAgainstOracles(new char[][]{
                {'X', '.', '.', 'X'},
                {'.', '.', '.', 'X'},
                {'.', '.', '.', 'X'}
        });
    }

    @Test
    void officialEmptyExampleIsZero() {
        assertBothAgainstOracles(new char[][]{{'.'}});
    }

    @Test
    void allEmptyRectangularBoardsHaveNoShips() {
        assertBothAgainstOracles(new char[][]{
                {'.', '.', '.', '.'},
                {'.', '.', '.', '.'},
                {'.', '.', '.', '.'}
        });
    }

    @Test
    void aSingleCellShipIsCounted() {
        assertBothAgainstOracles(new char[][]{{'X'}});
    }

    @Test
    void horizontalShipsOfSeveralLengthsAreCountedSeparately() {
        assertBothAgainstOracles(new char[][]{
                {'X', 'X', '.', '.', 'X', 'X', 'X', '.', '.', 'X'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}
        });
    }

    @Test
    void verticalShipsOfSeveralLengthsAreCountedSeparately() {
        assertBothAgainstOracles(new char[][]{
                {'X', '.', '.', 'X', '.', '.', 'X'},
                {'X', '.', '.', 'X', '.', '.', 'X'},
                {'.', '.', '.', 'X', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.', '.'}
        });
    }

    @Test
    void mixedHorizontalAndVerticalShipsAreCounted() {
        assertBothAgainstOracles(new char[][]{
                {'X', 'X', '.', '.', '.', 'X'},
                {'.', '.', '.', '.', '.', 'X'},
                {'X', '.', 'X', 'X', '.', '.'},
                {'X', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'X', '.', 'X'},
                {'.', '.', '.', 'X', '.', '.'}
        });
    }

    @Test
    void shipsMayTouchDiagonallyButNotHorizontallyOrVertically() {
        assertBothAgainstOracles(new char[][]{
                {'X', '.', 'X'},
                {'.', 'X', '.'},
                {'X', '.', 'X'}
        });
    }

    @Test
    void shipsAlongEveryBoardEdgeAreCounted() {
        assertBothAgainstOracles(new char[][]{
                {'X', 'X', '.', '.', '.', 'X'},
                {'.', '.', '.', '.', '.', 'X'},
                {'X', '.', '.', '.', '.', '.'},
                {'X', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.'},
                {'X', 'X', 'X', '.', '.', '.'}
        });
    }

    @Test
    void oneRowBoardSupportsSeparatedShips() {
        assertBothAgainstOracles(new char[][]{{'X', 'X', '.', 'X', '.', 'X', 'X', 'X', '.', 'X'}});
    }

    @Test
    void oneColumnBoardSupportsSeparatedShips() {
        assertBothAgainstOracles(new char[][]{
                {'X'},
                {'X'},
                {'.'},
                {'X'},
                {'.'},
                {'X'},
                {'X'},
                {'X'},
                {'.'},
                {'X'}
        });
    }

    @Test
    void rectangularBoardWithManySingleCellShipsUsesIndependentOracles() {
        assertBothAgainstOracles(new char[][]{
                {'X', '.', 'X', '.', 'X', '.', 'X', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'X', '.', 'X', '.', 'X', '.', 'X', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'X', '.', 'X', '.', 'X', '.', 'X', '.'}
        });
    }

    @Test
    void dfsMarksOnlyXCellsAndQuickPreservesItsInput() {
        char[][] board = {
                {'X', 'X', '.', 'X'},
                {'.', '.', '.', 'X'},
                {'X', '.', '.', '.'}
        };
        char[][] dfsBoard = copy(board);
        assertEquals(3, solution.countBattleships(dfsBoard));
        for (char[] row : dfsBoard) {
            for (char cell : row) {
                assertEquals('.', cell, "DFS must mark every visited ship cell as empty");
            }
        }

        char[][] quickBoard = copy(board);
        assertEquals(3, solution.countBattleshipsQuick(quickBoard));
        assertBoardEquals(board, quickBoard);
    }

    @Test
    void dfsSecondCallOnTheSameMutatedBoardReturnsZero() {
        char[][] board = {
                {'X', 'X', '.', 'X'},
                {'.', '.', '.', 'X'}
        };
        assertEquals(2, solution.countBattleships(board));
        assertEquals(0, solution.countBattleships(board));
    }

    @Test
    void quickCanBeCalledRepeatedlyWithoutChangingItsAnswer() {
        char[][] board = {
                {'X', 'X', '.', 'X'},
                {'.', '.', '.', 'X'}
        };
        assertEquals(2, solution.countBattleshipsQuick(board));
        assertEquals(2, solution.countBattleshipsQuick(board));
        assertBoardEquals(new char[][]{
                {'X', 'X', '.', 'X'},
                {'.', '.', '.', 'X'}
        }, board);
    }

    @Test
    void nullBoardIsSupportedByBothMethods() {
        assertEquals(0, solution.countBattleships(null));
        assertEquals(0, solution.countBattleshipsQuick(null));
    }

    @Test
    void zeroRowBoardIsSupportedByBothMethods() {
        char[][] board = new char[0][0];
        assertEquals(0, solution.countBattleships(board));
        assertEquals(0, solution.countBattleshipsQuick(board));
    }

    @Test
    void zeroColumnBoardIsSupportedByBothMethods() {
        char[][] board = new char[][]{{}};
        assertEquals(0, solution.countBattleships(board));
        assertEquals(0, solution.countBattleshipsQuick(board));
    }

    @Test
    void maximumWidthHorizontalShipIsCounted() {
        char[][] board = new char[1][200];
        Arrays.fill(board[0], 'X');
        assertBothAgainstOracles(board);
    }

    @Test
    void maximumHeightVerticalShipIsCounted() {
        char[][] board = new char[200][1];
        for (char[] row : board) {
            row[0] = 'X';
        }
        assertBothAgainstOracles(board);
    }

    @Test
    void maximumBoardCheckerboardContainsTwentyThousandSingleCellShips() {
        char[][] board = new char[200][200];
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column] = (row + column) % 2 == 0 ? 'X' : '.';
            }
        }
        assertBothAgainstOracles(board);
        assertEquals(20_000, headCellOracle(board));
    }

    @Test
    void maximumBoardWithAlternatingFullRowsContainsOneShipPerOccupiedRow() {
        char[][] board = new char[200][200];
        for (int row = 0; row < board.length; row++) {
            Arrays.fill(board[row], row % 2 == 0 ? 'X' : '.');
        }
        assertBothAgainstOracles(board);
        assertEquals(100, headCellOracle(board));
    }

    @Test
    void exhaustiveValidThreeByThreeBoardsAgreeWithBothIndependentOracles() {
        int checked = 0;
        for (int mask = 0; mask < (1 << 9); mask++) {
            char[][] board = new char[3][3];
            for (int cell = 0; cell < 9; cell++) {
                board[cell / 3][cell % 3] = (mask & (1 << cell)) == 0 ? '.' : 'X';
            }
            if (isValidBoard(board)) {
                assertBothAgainstOracles(board);
                checked++;
            }
        }
        assertTrue(checked > 20, "The exhaustive fixture should exercise many valid layouts");
    }

    @Test
    void seededRandomValidBoardsAgreeWithBothIndependentOracles() {
        Random random = new Random(419_2025L);
        for (int testCase = 0; testCase < 120; testCase++) {
            int rows = 1 + random.nextInt(12);
            int columns = 1 + random.nextInt(12);
            char[][] board = randomValidBoard(random, rows, columns);
            assertTrue(isValidBoard(board));
            assertBothAgainstOracles(board);
        }
    }

    @Test
    void seededRandomBoardsAlsoCoverEmptyAndSparseRows() {
        Random random = new Random(419_7L);
        for (int testCase = 0; testCase < 80; testCase++) {
            int rows = 1 + random.nextInt(8);
            int columns = 1 + random.nextInt(20);
            char[][] board = new char[rows][columns];
            for (char[] row : board) {
                Arrays.fill(row, '.');
            }
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    if (random.nextInt(5) == 0 && canPlace(board, row, column, 1, true)) {
                        board[row][column] = 'X';
                    }
                }
            }
            assertBothAgainstOracles(board);
        }
    }

    @Test
    void independentInstancesDoNotShareBoardOrTraversalState() {
        char[][] first = {
                {'X', '.', 'X'},
                {'.', '.', '.'}
        };
        char[][] second = {
                {'X', 'X', '.', '.'},
                {'.', '.', '.', 'X'}
        };
        CountBattleships_419 another = new CountBattleships_419();
        assertBothAgainstOracles(first);
        assertEquals(2, another.countBattleshipsQuick(copy(second)));
        assertEquals(2, solution.countBattleshipsQuick(copy(second)));
    }

    private void assertBothAgainstOracles(char[][] board) {
        int expectedByHeads = headCellOracle(board);
        int expectedByComponents = componentOracle(board);
        assertEquals(expectedByComponents, expectedByHeads,
                "The fixture must contain only straight, separated ships");

        char[][] dfsInput = copy(board);
        assertEquals(expectedByComponents, solution.countBattleships(dfsInput));
        for (char[] row : dfsInput) {
            for (char cell : row) {
                assertTrue(cell == '.', "DFS should leave no unvisited X cells");
            }
        }

        char[][] quickInput = copy(board);
        assertEquals(expectedByHeads, solution.countBattleshipsQuick(quickInput));
        assertBoardEquals(board, quickInput);
    }

    private static int headCellOracle(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return 0;
        }
        int count = 0;
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (board[row][column] == 'X'
                        && (row == 0 || board[row - 1][column] != 'X')
                        && (column == 0 || board[row][column - 1] != 'X')) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int componentOracle(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return 0;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        int count = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (board[row][column] != 'X' || visited[row][column]) {
                    continue;
                }
                count++;
                Queue<int[]> queue = new ArrayDeque<>();
                queue.add(new int[]{row, column});
                visited[row][column] = true;
                while (!queue.isEmpty()) {
                    int[] current = queue.remove();
                    for (int[] direction : directions) {
                        int nextRow = current[0] + direction[0];
                        int nextColumn = current[1] + direction[1];
                        if (nextRow >= 0 && nextRow < board.length
                                && nextColumn >= 0 && nextColumn < board[0].length
                                && board[nextRow][nextColumn] == 'X'
                                && !visited[nextRow][nextColumn]) {
                            visited[nextRow][nextColumn] = true;
                            queue.add(new int[]{nextRow, nextColumn});
                        }
                    }
                }
            }
        }
        return count;
    }

    private static boolean isValidBoard(char[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return true;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (board[row][column] != 'X' || visited[row][column]) {
                    continue;
                }
                int minRow = row;
                int maxRow = row;
                int minColumn = column;
                int maxColumn = column;
                Queue<int[]> queue = new ArrayDeque<>();
                queue.add(new int[]{row, column});
                visited[row][column] = true;
                while (!queue.isEmpty()) {
                    int[] current = queue.remove();
                    minRow = Math.min(minRow, current[0]);
                    maxRow = Math.max(maxRow, current[0]);
                    minColumn = Math.min(minColumn, current[1]);
                    maxColumn = Math.max(maxColumn, current[1]);
                    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                    for (int[] direction : directions) {
                        int nextRow = current[0] + direction[0];
                        int nextColumn = current[1] + direction[1];
                        if (nextRow >= 0 && nextRow < board.length
                                && nextColumn >= 0 && nextColumn < board[0].length
                                && board[nextRow][nextColumn] == 'X'
                                && !visited[nextRow][nextColumn]) {
                            visited[nextRow][nextColumn] = true;
                            queue.add(new int[]{nextRow, nextColumn});
                        }
                    }
                }
                if (minRow != maxRow && minColumn != maxColumn) {
                    return false;
                }
            }
        }
        return true;
    }

    private static char[][] randomValidBoard(Random random, int rows, int columns) {
        char[][] board = new char[rows][columns];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        int requestedShips = 1 + random.nextInt(10);
        for (int ship = 0; ship < requestedShips; ship++) {
            boolean horizontal = random.nextBoolean();
            int maxLength = horizontal ? columns : rows;
            int length = 1 + random.nextInt(Math.min(maxLength, 5));
            for (int attempt = 0; attempt < 100; attempt++) {
                int row = random.nextInt(rows);
                int column = random.nextInt(columns);
                if (canPlace(board, row, column, length, horizontal)) {
                    for (int offset = 0; offset < length; offset++) {
                        board[row + (horizontal ? 0 : offset)][column + (horizontal ? offset : 0)] = 'X';
                    }
                    break;
                }
            }
        }
        return board;
    }

    private static boolean canPlace(char[][] board, int row, int column, int length, boolean horizontal) {
        boolean[][] candidate = new boolean[board.length][board[0].length];
        for (int offset = 0; offset < length; offset++) {
            int candidateRow = row + (horizontal ? 0 : offset);
            int candidateColumn = column + (horizontal ? offset : 0);
            if (candidateRow < 0 || candidateRow >= board.length
                    || candidateColumn < 0 || candidateColumn >= board[0].length
                    || board[candidateRow][candidateColumn] == 'X') {
                return false;
            }
            candidate[candidateRow][candidateColumn] = true;
        }
        for (int candidateRow = 0; candidateRow < board.length; candidateRow++) {
            for (int candidateColumn = 0; candidateColumn < board[0].length; candidateColumn++) {
                if (!candidate[candidateRow][candidateColumn]) {
                    continue;
                }
                for (int deltaRow = -1; deltaRow <= 1; deltaRow++) {
                    for (int deltaColumn = -1; deltaColumn <= 1; deltaColumn++) {
                        int neighborRow = candidateRow + deltaRow;
                        int neighborColumn = candidateColumn + deltaColumn;
                        if (neighborRow >= 0 && neighborRow < board.length
                                && neighborColumn >= 0 && neighborColumn < board[0].length
                                && !candidate[neighborRow][neighborColumn]
                                && board[neighborRow][neighborColumn] == 'X') {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private static char[][] copy(char[][] board) {
        if (board == null) {
            return null;
        }
        char[][] copy = new char[board.length][];
        for (int row = 0; row < board.length; row++) {
            copy[row] = board[row].clone();
        }
        return copy;
    }

    private static void assertBoardEquals(char[][] expected, char[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row]);
        }
    }
}

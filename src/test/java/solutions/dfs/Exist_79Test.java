package solutions.dfs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

/**
 * @author BorisMirage
 * Time: 2025/05/06 19:15
 * Created with IntelliJ IDEA
 */

public class Exist_79Test {
    private final Exist_79 test = new Exist_79();

    @Test
    public void test() {
        assertFalse(test.exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABCB")); // revisiting 'B' is not allowed
        assertTrue(test.exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ASFDE"));
        assertFalse(test.exist(new char[][]{
                {'A', 'B'},
                {'C', 'D'}
        }, "ABCDZ"));

        assertFalse(test.exist(new char[][]{
                {'A'}
        }, "AA"));
        assertTrue(test.exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABCCED"));
        assertTrue(test.exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "SEE"));
        assertTrue(test.exist(new char[][]{
                {'A', 'B'},
                {'C', 'D'}
        }, "AB"));
        assertTrue(test.exist(new char[][]{
                {'A'}
        }, "A"));
        assertFalse(test.exist(new char[][]{
                {'A', 'B'},
                {'C', 'D'}
        }, "ABA"));
    }

    @Test
    public void testEmpty() {
        assertFalse(test.exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, ""));
        assertFalse(test.exist(new char[][]{{}}, ""));
        assertFalse(test.exist(new char[][]{{}}, "ASCSADSA"));
        assertFalse(test.exist(new char[][]{}, "A"));
        assertFalse(test.exist(new char[0][0], "A"));
        assertFalse(test.exist(null, "ANY"));
        assertFalse(test.exist(new char[][]{{'A', 'B'}, {'C', 'D'}}, null));
        assertFalse(test.exist(new char[][]{
                {}, {}, {}
        }, "A"));
    }

    @Test
    public void testWordNotPresentDueToDirectionConstraint() {
        assertFalse(test.exist(new char[][]{
                {'A', 'B', 'C'},
                {'D', 'E', 'F'},
                {'G', 'H', 'I'}
        }, "AEI"));
    }

    @Test
    public void testWordFoundWithTurns() {
        assertTrue(test.exist(new char[][]{
                {'C', 'A', 'A'},
                {'A', 'A', 'A'},
                {'B', 'C', 'D'}
        }, "AAB"));
    }

    @Test
    public void testCannotReuseSameCell() {
        assertFalse(test.exist(new char[][]{
                {'A', 'A'}
        }, "AAA"));
    }

    @Test
    public void testSingleRowBoard() {
        assertTrue(test.exist(new char[][]{
                {'H', 'E', 'L', 'L', 'O'}
        }, "HELLO"));
    }

    @Test
    public void testSingleColumnBoard() {
        assertTrue(test.exist(new char[][]{
                {'W'},
                {'O'},
                {'R'},
                {'D'}
        }, "WORD"));
    }

    @Test
    public void testSingleColumnWrongOrder() {
        assertFalse(test.exist(new char[][]{
                {'W'},
                {'O'},
                {'R'},
                {'D'}
        }, "WROD"));
    }

    @Test
    public void testCaseSensitivity() {
        assertFalse(test.exist(new char[][]{
                {'a', 'b'},
                {'c', 'd'}
        }, "AB"));
    }

    @Test
    public void testGiantCaseLongSnakePath() {
        int n = 30;
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = 'A';
            }
        }
        String word = "A".repeat(200);
        assertTrue(test.exist(board, word));
    }

    @Test
    public void testWordLongerThanBoardArea() {
        // 2x2 board = 4 cells, word of length 5 is impossible
        assertFalse(test.exist(new char[][]{
                {'A', 'B'},
                {'C', 'D'}
        }, "ABCDA"));
    }

    @Test
    public void testSingleCellMatch() {
        assertTrue(test.exist(new char[][]{{'Z'}}, "Z"));
    }

    @Test
    public void testSingleCellNoMatch() {
        assertFalse(test.exist(new char[][]{{'Z'}}, "X"));
    }

    @Test
    public void testDiagonalNotAllowed() {
        // 'A' at (0,0), 'B' at (1,1) — diagonal, not adjacent
        assertFalse(test.exist(new char[][]{
                {'A', 'X'},
                {'X', 'B'}
        }, "AB"));
    }

    @Test
    public void testWordUsingAllCells() {
        // Snake path through entire 3x3 board
        // A B C
        // F E D
        // G H I
        assertTrue(test.exist(new char[][]{
                {'A', 'B', 'C'},
                {'F', 'E', 'D'},
                {'G', 'H', 'I'}
        }, "ABCDEFGHI"));
    }

    @Test
    public void testBacktrackNearlySucceeds() {
        // Path "ABCDE" nearly works via top row but 'E' not adjacent to 'D' that way
        // A B C
        // X X D
        // X X E
        // "ABCDE" should succeed: A(0,0)->B(0,1)->C(0,2)->D(1,2)->E(2,2)
        assertTrue(test.exist(new char[][]{
                {'A', 'B', 'C'},
                {'X', 'X', 'D'},
                {'X', 'X', 'E'}
        }, "ABCDE"));

        // But "ABCED" should fail — E(2,2) is not adjacent to B or A to form that path
        assertFalse(test.exist(new char[][]{
                {'A', 'B', 'C'},
                {'X', 'X', 'D'},
                {'X', 'X', 'E'}
        }, "ABCED"));
    }

    @Test
    public void testRepeatedLettersRequiringBacktrack() {
        // Board full of 'A' except one 'B' — must find exact path
        // A A A
        // A A A
        // A A B
        assertTrue(test.exist(new char[][]{
                {'A', 'A', 'A'},
                {'A', 'A', 'A'},
                {'A', 'A', 'B'}
        }, "AAAAAAAAB"));

        // 9 cells, word "AAAAAAAAAB" (length 10) impossible
        assertFalse(test.exist(new char[][]{
                {'A', 'A', 'A'},
                {'A', 'A', 'A'},
                {'A', 'A', 'B'}
        }, "AAAAAAAAAB"));
    }

    @Test
    public void testLargeBoard10x10() {
        // 10x10 board filled with sequential letters, search for a 20-char path
        char[][] board = new char[10][10];
        StringBuilder path = new StringBuilder();
        // Fill with snake pattern: row 0 left-to-right, row 1 right-to-left, etc.
        char c = 'A';
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int col = (i % 2 == 0) ? j : 9 - j;
                board[i][col] = c;
                if (path.length() < 20) {
                    path.append(c);
                }
                c = (char) ('A' + (c - 'A' + 1) % 26);
            }
        }
        // The first 20 chars follow a valid snake path
        assertTrue(test.exist(board, path.toString()));
    }

    @Test
    public void testBoardNotMutatedAfterCall() {
        char[][] board = {
                {'A', 'B', 'C'},
                {'D', 'E', 'F'},
                {'G', 'H', 'I'}
        };
        char[][] original = {
                {'A', 'B', 'C'},
                {'D', 'E', 'F'},
                {'G', 'H', 'I'}
        };
        test.exist(board, "ABCFEDGHI");
        // Board must be restored to original state
        for (int i = 0; i < board.length; i++) {
            assertArrayEquals(original[i], board[i], "Row " + i + " was mutated");
        }
    }

    @Test
    public void testMaximumDocumentedBoardAndWordLength() {
        // LeetCode permits a 6x6 board and a word of length 15.  A path can
        // be selected without relying on an accidental diagonal adjacency.
        char[][] board = new char[6][6];
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column] = (char) ('A' + (row + column) % 2);
            }
        }
        assertTrue(test.exist(board, "ABABABABABABABA"));
    }

    @Test
    public void testLowerAndUpperCaseLettersRemainDistinct() {
        char[][] board = {
                {'Z', 'a', 'z'},
                {'x', 'Y', 'b'}
        };

        assertTrue(test.exist(board, "Z"));
        assertTrue(test.exist(board, "Za"));
        assertTrue(test.exist(board, "zb"));
        assertFalse(test.exist(board, "ZA"));
        assertFalse(test.exist(board, "Zb"));
    }

    @Test
    public void testDeepFailedSearchRestoresBoardForLaterCalls() {
        char[][] board = {
                {'A', 'A', 'A'},
                {'A', 'A', 'A'},
                {'A', 'A', 'A'}
        };
        char[][] original = copy(board);

        assertFalse(test.exist(board, "AAAAAB"));
        assertBoardEquals(original, board);
        assertTrue(test.exist(board, "AAAAAAAAA"));
        assertBoardEquals(original, board);
    }

    @Test
    public void testSuccessfulSearchAlsoRestoresBoardAndSupportsReuse() {
        char[][] board = {
                {'C', 'A', 'A'},
                {'A', 'B', 'A'},
                {'A', 'A', 'D'}
        };
        char[][] original = copy(board);

        assertTrue(test.exist(board, "CAB"));
        assertBoardEquals(original, board);
        assertTrue(test.exist(board, "DAB"));
        assertBoardEquals(original, board);
        assertFalse(test.exist(board, "CDA"));
        assertBoardEquals(original, board);
    }

    @Test
    public void testFreshInstancesDoNotShareSearchState() {
        char[][] board = {
                {'A', 'B'},
                {'C', 'D'}
        };

        assertFalse(new Exist_79().exist(copy(board), "ABCDZ"));
        assertTrue(new Exist_79().exist(copy(board), "AB"));
        assertTrue(new Exist_79().exist(copy(board), "CD"));
    }

    @Test
    public void testExhaustiveTwoByTwoBoardsAgainstIndependentOracle() {
        // Enumerating every A/B board and every short A/B word exercises
        // starts, turns, revisits, and the four-neighbor boundary rules.
        for (int boardMask = 0; boardMask < 1 << 4; boardMask++) {
            char[][] board = boardFromMask(boardMask, 2, 2);
            for (int length = 1; length <= 5; length++) {
                for (int wordMask = 0; wordMask < 1 << length; wordMask++) {
                    String word = binaryWord(wordMask, length);
                    boolean expected = referenceExist(board, word);
                    boolean actual = test.exist(copy(board), word);
                    assertEquals(expected, actual,
                            "board=" + boardText(board) + ", word=" + word);
                }
            }
        }
    }

    @Test
    public void testSeededRandomSmallBoardsAgainstIndependentOracle() {
        Random random = new Random(79_2026L);
        String alphabet = "ABab";

        for (int caseNumber = 0; caseNumber < 250; caseNumber++) {
            int rows = 1 + random.nextInt(4);
            int columns = 1 + random.nextInt(4);
            char[][] board = new char[rows][columns];
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    board[row][column] = alphabet.charAt(random.nextInt(alphabet.length()));
                }
            }

            int length = 1 + random.nextInt(Math.min(8, rows * columns + 2));
            StringBuilder word = new StringBuilder(length);
            for (int index = 0; index < length; index++) {
                word.append(alphabet.charAt(random.nextInt(alphabet.length())));
            }

            boolean expected = referenceExist(board, word.toString());
            boolean actual = test.exist(copy(board), word.toString());
            assertEquals(expected, actual,
                    "random case " + caseNumber + ": board=" + boardText(board)
                            + ", word=" + word);
        }
    }

    private static boolean referenceExist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.isEmpty()) {
            return false;
        }
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                boolean[][] used = new boolean[board.length][board[0].length];
                if (referenceDfs(board, word, 0, row, column, used)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean referenceDfs(char[][] board, String word, int index,
                                        int row, int column, boolean[][] used) {
        if (row < 0 || row >= board.length || column < 0 || column >= board[0].length
                || used[row][column] || board[row][column] != word.charAt(index)) {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }

        used[row][column] = true;
        boolean found = referenceDfs(board, word, index + 1, row + 1, column, used)
                || referenceDfs(board, word, index + 1, row - 1, column, used)
                || referenceDfs(board, word, index + 1, row, column + 1, used)
                || referenceDfs(board, word, index + 1, row, column - 1, used);
        used[row][column] = false;
        return found;
    }

    private static char[][] boardFromMask(int mask, int rows, int columns) {
        char[][] board = new char[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                int bit = row * columns + column;
                board[row][column] = ((mask >> bit) & 1) == 0 ? 'A' : 'B';
            }
        }
        return board;
    }

    private static String binaryWord(int mask, int length) {
        StringBuilder word = new StringBuilder(length);
        for (int bit = 0; bit < length; bit++) {
            word.append(((mask >> bit) & 1) == 0 ? 'A' : 'B');
        }
        return word.toString();
    }

    private static char[][] copy(char[][] board) {
        char[][] result = new char[board.length][];
        for (int row = 0; row < board.length; row++) {
            result[row] = board[row].clone();
        }
        return result;
    }

    private static void assertBoardEquals(char[][] expected, char[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row]);
        }
    }

    private static String boardText(char[][] board) {
        StringBuilder text = new StringBuilder("[");
        for (int row = 0; row < board.length; row++) {
            if (row > 0) {
                text.append(';');
            }
            text.append(board[row]);
        }
        return text.append(']').toString();
    }
}

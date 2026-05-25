package solution.dfs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}

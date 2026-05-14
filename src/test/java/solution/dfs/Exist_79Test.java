package solution.dfs;

import org.junit.jupiter.api.Test;
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
}

package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class Solve_130Test {

    private final Solve_130 test = new Solve_130();

    @Test
    public void testHappyCases() {
        char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        test.solve(board);
        assertArrayEquals(new char[]{'X', 'X', 'X', 'X'}, board[0]);
        assertArrayEquals(new char[]{'X', 'X', 'X', 'X'}, board[1]);
        assertArrayEquals(new char[]{'X', 'O', 'X', 'X'}, board[3]);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        char[][] board = {{'O'}};
        test.solve(board);
        assertArrayEquals(new char[]{'O'}, board[0]);
    }

    @Test
    public void testLargeCase() {
        char[][] board = {
            {'X', 'O', 'X', 'O', 'X'},
            {'O', 'X', 'O', 'X', 'O'},
            {'X', 'O', 'X', 'O', 'X'},
            {'O', 'X', 'O', 'X', 'O'},
            {'X', 'O', 'X', 'O', 'X'}
        };
        test.solve(board);
        // Border O's remain, interior O's become X
        assertArrayEquals(new char[]{'X', 'O', 'X', 'O', 'X'}, board[0]);
    }

    @Test
    public void testEmptyBoardNoCrash() {
        char[][] board = {};
        test.solve(board);
        assertArrayEquals(new char[][]{}, board);
    }

    @Test
    public void testAllXUnchanged() {
        char[][] board = {
                {'X', 'X'},
                {'X', 'X'}
        };
        test.solve(board);
        assertArrayEquals(new char[][]{
                {'X', 'X'},
                {'X', 'X'}
        }, board);
    }

    @Test
    public void testAllOBorderConnectedRemain() {
        char[][] board = {
                {'O', 'O', 'O'},
                {'O', 'O', 'O'},
                {'O', 'O', 'O'}
        };
        test.solve(board);
        assertArrayEquals(new char[][]{
                {'O', 'O', 'O'},
                {'O', 'O', 'O'},
                {'O', 'O', 'O'}
        }, board);
    }

    @Test
    public void testSingleRowUnchanged() {
        char[][] board = {
                {'X', 'O', 'O', 'X', 'O'}
        };
        test.solve(board);
        assertArrayEquals(new char[][]{
                {'X', 'O', 'O', 'X', 'O'}
        }, board);
    }

    @Test
    public void testSingleColumnUnchanged() {
        char[][] board = {
                {'X'},
                {'O'},
                {'O'},
                {'X'}
        };
        test.solve(board);
        assertArrayEquals(new char[][]{
                {'X'},
                {'O'},
                {'O'},
                {'X'}
        }, board);
    }

    @Test
    public void testInteriorRegionCaptured() {
        char[][] board = {
                {'X', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'O', 'O', 'X'},
                {'X', 'X', 'X', 'X', 'X'}
        };
        test.solve(board);
        assertArrayEquals(new char[][]{
                {'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X'}
        }, board);
    }

    @Test
    public void testGiantCheckerboard() {
        int n = 20;
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = ((i + j) % 2 == 0) ? 'O' : 'X';
            }
        }
        test.solve(board);
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if ((i + j) % 2 == 0) {
                    org.junit.jupiter.api.Assertions.assertEquals('X', board[i][j]);
                }
            }
        }
    }
}

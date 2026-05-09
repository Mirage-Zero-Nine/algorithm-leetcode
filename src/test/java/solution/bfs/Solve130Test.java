package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class Solve130Test {

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
}

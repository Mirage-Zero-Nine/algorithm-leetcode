package solution.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class GameOfLife_289Test {
    private final GameOfLife_289 solver = new GameOfLife_289();

    @Test public void testBasic() {
        int[][] board = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        int[][] expected = {
                {0, 0, 0},
                {1, 0, 1},
                {0, 1, 1},
                {0, 1, 0}
        };
        solver.gameOfLife(board);
        assertArrayEquals(expected, board);
    }

    @Test public void testAllDead() {
        int[][] board = {{0, 0}, {0, 0}};
        int[][] expected = {{0, 0}, {0, 0}};
        solver.gameOfLife(board);
        assertArrayEquals(expected, board);
    }

    @Test public void testBlock() {
        // stable 2x2 block
        int[][] board = {{1, 1}, {1, 1}};
        int[][] expected = {{1, 1}, {1, 1}};
        solver.gameOfLife(board);
        assertArrayEquals(expected, board);
    }

    @Test public void testBlinker() {
        // blinker horizontal becomes vertical
        int[][] board = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
        };
        int[][] expected = {
                {0, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
        };
        solver.gameOfLife(board);
        assertArrayEquals(expected, board);
    }

    @Test public void testEmpty() {
        int[][] board = {};
        solver.gameOfLife(board);
        assertArrayEquals(new int[][]{}, board);
    }
}

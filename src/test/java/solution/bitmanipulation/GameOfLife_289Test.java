package solution.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test public void testSingleCellDead() {
        int[][] board = {{0}};
        solver.gameOfLife(board);
        assertArrayEquals(new int[][]{{0}}, board);
    }

    @Test public void testSingleCellAlive() {
        // live cell with 0 neighbors dies
        int[][] board = {{1}};
        solver.gameOfLife(board);
        assertArrayEquals(new int[][]{{0}}, board);
    }

    @Test public void testAllAlive3x3() {
        int[][] board = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        int[][] expected = {
                {1, 0, 1},
                {0, 0, 0},
                {1, 0, 1}
        };
        solver.gameOfLife(board);
        assertArrayEquals(expected, board);
    }

    @Test public void testSingleRow() {
        int[][] board = {{1, 1, 1}};
        // middle cell has 2 neighbors but it's alive -> survives? No, in 1 row neighbors are only left/right
        // cell 0: 1 neighbor (cell 1) -> dies
        // cell 1: 2 neighbors (cell 0, cell 2) -> survives
        // cell 2: 1 neighbor (cell 1) -> dies
        int[][] expected = {{0, 1, 0}};
        solver.gameOfLife(board);
        assertArrayEquals(expected, board);
    }

    @Test public void testGiantCase() {
        int[][] board = new int[50][50];
        // fill with a pattern
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                board[i][j] = (i + j) % 2;
            }
        }
        solver.gameOfLife(board);
        // just verify it doesn't crash and dimensions are preserved
        assertEquals(50, board.length);
        assertEquals(50, board[0].length);
    }
}

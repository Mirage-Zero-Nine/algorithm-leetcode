package solutions.bitmanipulation;

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

    @Test public void testAllThreeByThreeBoardsAgainstSimultaneousRules() {
        for (int mask = 0; mask < 512; mask++) {
            int[][] initial = new int[3][3];
            int[][] expected = new int[3][3];
            for (int i = 0; i < 9; i++) initial[i / 3][i % 3] = (mask >>> i) & 1;
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    int neighbors = 0;
                    for (int y = 0; y < 3; y++)
                        for (int x = 0; x < 3; x++)
                            if ((r != y || c != x) && Math.abs(r - y) <= 1 && Math.abs(c - x) <= 1)
                                neighbors += initial[y][x];
                    expected[r][c] = neighbors == 3 || (initial[r][c] == 1 && neighbors == 2) ? 1 : 0;
                }
            }
            solver.gameOfLife(initial);
            assertArrayEquals(expected, initial, "mask=" + mask);
        }
    }

    @Test public void testBlinkerReturnsAfterTwoGenerations() {
        int[][] board = {{0, 1, 0}, {0, 1, 0}, {0, 1, 0}};
        solver.gameOfLife(board);
        solver.gameOfLife(board);
        assertArrayEquals(new int[][]{{0, 1, 0}, {0, 1, 0}, {0, 1, 0}}, board);
    }

    @Test public void testLargeSeparatedStableBlocks() {
        int[][] board = new int[100][100];
        int[][] expected = new int[100][100];
        for (int r = 0; r < 100; r++)
            for (int c = 0; c < 100; c++)
                expected[r][c] = board[r][c] = r % 4 < 2 && c % 4 < 2 ? 1 : 0;
        solver.gameOfLife(board);
        assertArrayEquals(expected, board);
    }
}

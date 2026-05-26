package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SnakesAndLadders_909Test {

    private final SnakesAndLadders_909 test = new SnakesAndLadders_909();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.snakesAndLadders(new int[][]{
            {-1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, 35, -1, -1, 13, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, 15, -1, -1, -1, -1}
        }));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.snakesAndLadders(new int[][]{{-1, -1}, {-1, -1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.snakesAndLadders(new int[][]{{-1, -1, -1}, {-1, 9, 8}, {-1, 8, 9}}));
    }

    @Test
    public void testBoardWithNoSnakesOrLadders3x3() {
        assertEquals(2, test.snakesAndLadders(new int[][]{
                {-1, -1, -1},
                {-1, -1, -1},
                {-1, -1, -1}
        }));
    }

    @Test
    public void testLadderNearStartReducesMoves() {
        assertEquals(2, test.snakesAndLadders(new int[][]{
                {-1, -1, -1, -1},
                {-1, -1, -1, -1},
                {-1, -1, -1, -1},
                {-1, 14, -1, -1}
        }));
    }

    @Test
    public void testSnakeDelaysProgress() {
        assertEquals(3, test.snakesAndLadders(new int[][]{
                {-1, -1, -1, -1},
                {-1, -1, -1, -1},
                {-1, -1, 2, -1},
                {-1, -1, -1, -1}
        }));
    }

    @Test
    public void testMultipleLadders() {
        assertEquals(2, test.snakesAndLadders(new int[][]{
                {-1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1},
                {-1, 20, -1, 24, -1}
        }));
    }

    @Test
    public void testAlternatingRowsIndexingCase() {
        assertEquals(2, test.snakesAndLadders(new int[][]{
                {-1, -1, -1, -1},
                {-1, -1, -1, -1},
                {-1, 12, -1, -1},
                {-1, -1, -1, -1}
        }));
    }

    @Test
    public void testDenseJumpsStillFinite() {
        assertEquals(3, test.snakesAndLadders(new int[][]{
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 30, -1, 31, -1, -1},
                {-1, 15, -1, -1, -1, -1}
        }));
    }

    @Test
    public void testGiantBoardWithoutJumps() {
        int n = 10;
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = -1;
            }
        }
        assertEquals(17, test.snakesAndLadders(board));
    }
}

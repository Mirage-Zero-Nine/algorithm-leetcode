package solutions.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinTotalDistance_296Test {
    private final MinTotalDistance_296 solver = new MinTotalDistance_296();

    @Test public void testBasic() {
        int[][] grid = {
                {1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}
        };
        assertEquals(6, solver.minTotalDistance(grid));
    }

    @Test public void testSingleOne() {
        int[][] grid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        assertEquals(0, solver.minTotalDistance(grid));
    }

    @Test public void testAllZero() {
        int[][] grid = {
                {0, 0},
                {0, 0}
        };
        assertEquals(0, solver.minTotalDistance(grid));
    }

    @Test public void testTwoOnes() {
        int[][] grid = {
                {1, 0, 0, 0, 1}
        };
        assertEquals(4, solver.minTotalDistance(grid));
    }

    @Test public void testVertical() {
        int[][] grid = {
                {1},
                {0},
                {1}
        };
        assertEquals(2, solver.minTotalDistance(grid));
    }

    @Test public void testAllOnes() {
        int[][] grid = {
                {1, 1},
                {1, 1}
        };
        assertEquals(4, solver.minTotalDistance(grid));
    }

    @Test public void testCorners() {
        int[][] grid = {
                {1, 0, 1},
                {0, 0, 0},
                {1, 0, 1}
        };
        assertEquals(8, solver.minTotalDistance(grid));
    }

    @Test public void testSingleRow() {
        int[][] grid = {
                {1, 0, 0, 1, 0, 1}
        };
        assertEquals(5, solver.minTotalDistance(grid));
    }

    @Test public void testThreeInRow() {
        int[][] grid = {
                {1, 1, 1}
        };
        assertEquals(2, solver.minTotalDistance(grid));
    }

    @Test public void testLargeGrid() {
        int[][] grid = new int[50][50];
        grid[0][0] = 1;
        grid[49][49] = 1;
        assertEquals(98, solver.minTotalDistance(grid));
    }

    @Test public void testDiagonal() {
        int[][] grid = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        assertEquals(4, solver.minTotalDistance(grid));
    }
}

package solution.findkth;

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
}

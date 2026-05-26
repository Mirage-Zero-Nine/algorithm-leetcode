package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxDistance_1162Test {

    private final MaxDistance_1162 test = new MaxDistance_1162();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.maxDistance(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}));
        assertEquals(4, test.maxDistance(new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.maxDistance(new int[][]{{1}}));
        assertEquals(-1, test.maxDistance(new int[][]{{1, 1}, {1, 1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.maxDistance(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 1}}));
    }

    @Test
    public void testAllWaterReturnsMinusOne() {
        assertEquals(-1, test.maxDistance(new int[][]{{0, 0}, {0, 0}}));
    }

    @Test
    public void testSingleLandInCenter() {
        assertEquals(2, test.maxDistance(new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        }));
    }

    @Test
    public void testSingleLandAtCorner() {
        assertEquals(4, test.maxDistance(new int[][]{
                {1, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        }));
    }

    @Test
    public void testMultipleLandsReduceDistance() {
        assertEquals(2, test.maxDistance(new int[][]{
                {1, 0, 1},
                {0, 0, 0},
                {1, 0, 1}
        }));
    }

    @Test
    public void testLineOfLandOnOneSide() {
        assertEquals(3, test.maxDistance(new int[][]{
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        }));
    }

    @Test
    public void testCheckerboardHasDistanceOne() {
        assertEquals(1, test.maxDistance(new int[][]{
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 1}
        }));
    }

    @Test
    public void testGiantGrid() {
        int n = 50;
        int[][] grid = new int[n][n];
        grid[0][0] = 1;
        assertEquals(98, test.maxDistance(grid));
    }
}

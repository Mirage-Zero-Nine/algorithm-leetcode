package solution.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TrapRainWater_407Test {

    private final TrapRainWater_407 test = new TrapRainWater_407();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.trapRainWater(new int[][]{
            {1, 4, 3, 1, 3, 2},
            {3, 2, 1, 3, 2, 4},
            {2, 3, 3, 2, 3, 1}
        }));
        assertEquals(7, test.trapRainWater(new int[][]{
            {3, 3, 3, 3, 3},
            {3, 2, 2, 2, 3},
            {3, 2, 1, 2, 3},
            {3, 3, 3, 3, 3}
        }));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.trapRainWater(new int[][]{{1, 2, 3}}));
        assertEquals(0, test.trapRainWater(new int[][]{{1}, {2}, {3}}));
        assertEquals(0, test.trapRainWater(new int[][]{
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        }));
    }

    @Test
    public void testLargeCase() {
        assertEquals(48, test.trapRainWater(new int[][]{
            {5, 5, 5, 5, 5, 5},
            {5, 1, 1, 1, 1, 5},
            {5, 1, 5, 5, 1, 5},
            {5, 1, 5, 5, 1, 5},
            {5, 1, 1, 1, 1, 5},
            {5, 5, 5, 5, 5, 5}
        }));
    }

    @Test
    public void testEmptyMatrix() {
        assertEquals(0, test.trapRainWater(new int[][]{}));
    }

    @Test
    public void testTwoByTwoMatrix() {
        assertEquals(0, test.trapRainWater(new int[][]{{1, 2}, {3, 4}}));
    }

    @Test
    public void testFlatSurface3x3() {
        assertEquals(0, test.trapRainWater(new int[][]{
            {5, 5, 5},
            {5, 5, 5},
            {5, 5, 5}
        }));
    }

    @Test
    public void testSingleHole() {
        // 3x3 with center lower
        assertEquals(4, test.trapRainWater(new int[][]{
            {5, 5, 5},
            {5, 1, 5},
            {5, 5, 5}
        }));
    }

    @Test
    public void testAsymmetricWalls() {
        assertEquals(1, test.trapRainWater(new int[][]{
            {3, 3, 3},
            {3, 2, 3},
            {3, 3, 3}
        }));
    }

    @Test
    public void testNoTrapping() {
        // Pyramid shape - no water trapped
        assertEquals(0, test.trapRainWater(new int[][]{
            {1, 1, 1, 1, 1},
            {1, 2, 2, 2, 1},
            {1, 2, 3, 2, 1},
            {1, 2, 2, 2, 1},
            {1, 1, 1, 1, 1}
        }));
    }

    @Test
    public void testLargePool() {
        // 5x5 with deep center
        assertEquals(12, test.trapRainWater(new int[][]{
            {3, 3, 3, 3, 3},
            {3, 1, 1, 1, 3},
            {3, 1, 1, 1, 3},
            {3, 3, 3, 3, 3}
        }));
    }

    @Test
    public void testGiantCase() {
        // 10x10 grid with border height 10 and interior height 1
        int[][] grid = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 0 || i == 9 || j == 0 || j == 9) {
                    grid[i][j] = 10;
                } else {
                    grid[i][j] = 1;
                }
            }
        }
        // Interior is 8x8 = 64 cells, each can hold 9 units of water
        assertEquals(64 * 9, test.trapRainWater(grid));
    }

    @Test
    public void testUnevenBorder() {
        // Water level limited by lowest border cell
        assertEquals(1, test.trapRainWater(new int[][]{
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        }));
    }
}

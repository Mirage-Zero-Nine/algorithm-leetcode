package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountSubIslands_1905Test {

    private final CountSubIslands_1905 test = new CountSubIslands_1905();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.countSubIslands(
            new int[][]{{1, 1, 1, 0, 0}, {0, 1, 1, 1, 1}, {0, 0, 0, 0, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 1, 1}},
            new int[][]{{1, 1, 1, 0, 0}, {0, 0, 1, 1, 1}, {0, 1, 0, 0, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 1, 0}}
        ));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.countSubIslands(new int[][]{{0}}, new int[][]{{1}}));
        assertEquals(1, test.countSubIslands(new int[][]{{1}}, new int[][]{{1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(2, test.countSubIslands(
            new int[][]{{1, 0, 1, 0, 1}, {1, 1, 1, 1, 1}, {0, 0, 0, 0, 0}, {1, 1, 1, 1, 1}, {1, 0, 1, 0, 1}},
            new int[][]{{0, 0, 0, 0, 0}, {1, 1, 1, 1, 1}, {0, 1, 0, 1, 0}, {0, 1, 0, 1, 0}, {1, 0, 0, 0, 1}}
        ));
    }

    @Test
    public void testAllWaterInGrid2() {
        assertEquals(0, test.countSubIslands(
            new int[][]{{1, 1}, {1, 1}},
            new int[][]{{0, 0}, {0, 0}}
        ));
    }

    @Test
    public void testAllLandBothGrids() {
        assertEquals(1, test.countSubIslands(
            new int[][]{{1, 1}, {1, 1}},
            new int[][]{{1, 1}, {1, 1}}
        ));
    }

    @Test
    public void testNoSubIsland() {
        // grid2 island extends beyond grid1 land
        assertEquals(0, test.countSubIslands(
            new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}},
            new int[][]{{1, 1, 0}, {0, 0, 0}, {0, 0, 0}}
        ));
    }

    @Test
    public void testMultipleDisjointSubIslands() {
        assertEquals(3, test.countSubIslands(
            new int[][]{{1, 0, 1, 0, 1}, {0, 0, 0, 0, 0}, {1, 0, 1, 0, 1}},
            new int[][]{{1, 0, 1, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}}
        ));
    }

    @Test
    public void testSingleRowGrid() {
        assertEquals(2, test.countSubIslands(
            new int[][]{{1, 0, 1, 0, 1}},
            new int[][]{{1, 0, 1, 0, 0}}
        ));
    }

    @Test
    public void testSingleColumnGrid() {
        assertEquals(1, test.countSubIslands(
            new int[][]{{1}, {1}, {0}, {1}},
            new int[][]{{1}, {1}, {0}, {0}}
        ));
    }

    @Test
    public void testPartialOverlap() {
        // grid2 island partially overlaps grid1 but one cell is water in grid1
        assertEquals(0, test.countSubIslands(
            new int[][]{{1, 1, 0}, {1, 0, 0}, {0, 0, 0}},
            new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 0}}
        ));
    }

    @Test
    public void testGiantCase() {
        int n = 50;
        int[][] grid1 = new int[n][n];
        int[][] grid2 = new int[n][n];
        // fill all with 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid1[i][j] = 1;
                grid2[i][j] = 1;
            }
        }
        // one big island in grid2 fully contained in grid1
        assertEquals(1, test.countSubIslands(grid1, grid2));
    }
}

package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OrangesRotting_994Test {

    private final OrangesRotting_994 test = new OrangesRotting_994();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
        assertEquals(-1, test.orangesRotting(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.orangesRotting(new int[][]{{0, 2}}));
        assertEquals(0, test.orangesRotting(new int[][]{{0}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(8, test.orangesRotting(new int[][]{{2, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}}));
    }

    @Test
    public void testInvalidInputs() {
        assertEquals(-1, test.orangesRotting(null));
        assertEquals(-1, test.orangesRotting(new int[][]{}));
        assertEquals(-1, test.orangesRotting(new int[][]{{}}));
    }

    @Test
    public void testAllFreshNoRotten() {
        assertEquals(-1, test.orangesRotting(new int[][]{
                {1, 1},
                {1, 1}
        }));
    }

    @Test
    public void testAllRottenAlreadyDone() {
        assertEquals(0, test.orangesRotting(new int[][]{
                {2, 2},
                {2, 2}
        }));
    }

    @Test
    public void testSingleFreshAdjacentToRotten() {
        assertEquals(1, test.orangesRotting(new int[][]{
                {2, 1}
        }));
    }

    @Test
    public void testSingleFreshIsolatedByEmpty() {
        assertEquals(-1, test.orangesRotting(new int[][]{
                {2, 0, 1}
        }));
    }

    @Test
    public void testMultipleSourcesAccelerateRotting() {
        assertEquals(2, test.orangesRotting(new int[][]{
                {2, 1, 1},
                {1, 1, 1},
                {1, 1, 2}
        }));
    }

    @Test
    public void testGiantGridPropagation() {
        int n = 25;
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = 1;
            }
        }
        grid[0][0] = 2;
        assertEquals(48, test.orangesRotting(grid));
    }
}

package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

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

    @Test
    public void testGridAllZerosReturnsZero() {
        assertEquals(0, test.orangesRotting(new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        }));
    }

    @Test
    public void testGridAllTwosReturnsZero() {
        assertEquals(0, test.orangesRotting(new int[][]{
                {2, 2, 2},
                {2, 2, 2}
        }));
    }

    @Test
    public void testGridAllOnesReturnsMinus1() {
        assertEquals(-1, test.orangesRotting(new int[][]{
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        }));
    }

    @Test
    public void testSingleRottenInfectsAllNeighbors() {
        // Rotten in center, 4 fresh neighbors -> 1 minute
        assertEquals(1, test.orangesRotting(new int[][]{
                {0, 1, 0},
                {1, 2, 1},
                {0, 1, 0}
        }));
    }

    @Test
    public void testUnreachableFreshBlockedByZeros() {
        // Fresh orange at corner completely surrounded by empty cells
        assertEquals(-1, test.orangesRotting(new int[][]{
                {2, 0, 0},
                {0, 0, 0},
                {0, 0, 1}
        }));
    }

    @Test
    public void testLeetCodeExample() {
        assertEquals(4, test.orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
    }

    @Test
    public void testMultiSourceBFS() {
        // Multiple rotten sources at corners, fresh in middle
        assertEquals(3, test.orangesRotting(new int[][]{
                {2, 1, 1, 1, 2},
                {1, 1, 1, 1, 1},
                {2, 1, 1, 1, 2}
        }));
    }

    @Test
    public void testLargeGrid50x50Seed42() {
        Random rand = new Random(42L);
        int[][] grid = new int[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                grid[i][j] = rand.nextInt(3); // 0, 1, or 2
            }
        }
        int result = test.orangesRotting(grid);
        assertTrue(result >= -1, "Result should be >= -1");
    }

    @Test
    public void testPropertyResultIsValidRange() {
        // Test multiple grids: result is always -1 or >= 0
        int[][][] grids = {
                {{0}}, {{1}}, {{2}}, {{2, 1}}, {{1, 0, 2}},
                {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}},
                {{1, 1, 1}, {1, 1, 1}},
                {{2, 2}, {2, 2}}
        };
        for (int[][] grid : grids) {
            int result = test.orangesRotting(grid);
            assertTrue(result == -1 || result >= 0,
                    "Result must be -1 or non-negative, got: " + result);
        }
    }

    @Test
    public void testAlreadyAllRottenOrEmpty() {
        assertEquals(0, test.orangesRotting(new int[][]{{2, 0, 2}, {0, 0, 0}, {2, 0, 2}}));
    }
}

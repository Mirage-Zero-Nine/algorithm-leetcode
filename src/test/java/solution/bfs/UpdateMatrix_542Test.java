package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class UpdateMatrix_542Test {

    private final UpdateMatrix_542 test = new UpdateMatrix_542();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}},
            test.updateMatrix(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));
        assertArrayEquals(new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 2, 1}},
            test.updateMatrix(new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[][]{{0}}, test.updateMatrix(new int[][]{{0}}));
    }

    @Test
    public void testLargeCase() {
        int[][] result = test.updateMatrix(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 0}});
        assertArrayEquals(new int[]{4, 3, 2}, result[0]);
        assertArrayEquals(new int[]{3, 2, 1}, result[1]);
        assertArrayEquals(new int[]{2, 1, 0}, result[2]);
    }

    @Test
    public void testNullAndEmptyInput() {
        org.junit.jupiter.api.Assertions.assertNull(test.updateMatrix(null));
        assertArrayEquals(new int[][]{}, test.updateMatrix(new int[][]{}));
        assertArrayEquals(new int[][]{{}}, test.updateMatrix(new int[][]{{}}));
    }

    @Test
    public void testAllZeroMatrix() {
        assertArrayEquals(new int[][]{
                {0, 0},
                {0, 0}
        }, test.updateMatrix(new int[][]{
                {0, 0},
                {0, 0}
        }));
    }

    @Test
    public void testSingleRow() {
        assertArrayEquals(new int[][]{
                {2, 1, 0, 1}
        }, test.updateMatrix(new int[][]{
                {1, 1, 0, 1}
        }));
    }

    @Test
    public void testSingleColumn() {
        assertArrayEquals(new int[][]{
                {1},
                {0},
                {1},
                {2}
        }, test.updateMatrix(new int[][]{
                {1},
                {0},
                {1},
                {1}
        }));
    }

    @Test
    public void testMultipleZeroSources() {
        assertArrayEquals(new int[][]{
                {0, 1, 0},
                {1, 2, 1},
                {0, 1, 0}
        }, test.updateMatrix(new int[][]{
                {0, 1, 0},
                {1, 1, 1},
                {0, 1, 0}
        }));
    }

    @Test
    public void testAllOnesNoZeroRemainsMaxValue() {
        int[][] result = test.updateMatrix(new int[][]{
                {1, 1},
                {1, 1}
        });
        assertArrayEquals(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}, result[0]);
        assertArrayEquals(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}, result[1]);
    }

    @Test
    public void testGiantGridSpotChecks() {
        int n = 30;
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = 1;
            }
        }
        matrix[0][0] = 0;
        int[][] out = test.updateMatrix(matrix);
        org.junit.jupiter.api.Assertions.assertEquals(0, out[0][0]);
        org.junit.jupiter.api.Assertions.assertEquals(10, out[5][5]);
        org.junit.jupiter.api.Assertions.assertEquals(58, out[n - 1][n - 1]);
    }

    @Test
    public void testAllZerosReturnsAllZeros() {
        int[][] matrix = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int[][] expected = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        assertArrayEquals(expected, test.updateMatrix(matrix));
    }

    @Test
    public void testAllOnesNoZeroImplBehavior() {
        // No zeros in matrix - impossible per problem constraints, but impl leaves MAX_VALUE
        int[][] matrix = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int[][] result = test.updateMatrix(matrix);
        for (int[] row : result) {
            for (int val : row) {
                assertEquals(Integer.MAX_VALUE, val);
            }
        }
    }

    @Test
    public void testSingleZeroInCornerWithOnesElsewhere() {
        int[][] matrix = {
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 0}
        };
        int[][] expected = {
            {6, 5, 4, 3},
            {5, 4, 3, 2},
            {4, 3, 2, 1},
            {3, 2, 1, 0}
        };
        assertArrayEquals(expected, test.updateMatrix(matrix));
    }

    @Test
    public void testMultipleZerosTakeMinDistance() {
        // Two zeros at opposite corners - each cell takes min distance
        int[][] matrix = {
            {0, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 0}
        };
        int[][] expected = {
            {0, 1, 2, 3},
            {1, 2, 3, 2},
            {2, 3, 2, 1},
            {3, 2, 1, 0}
        };
        assertArrayEquals(expected, test.updateMatrix(matrix));
    }

    @Test
    public void testWallPathStyleGrid() {
        // Zeros form a wall, ones must go around
        int[][] matrix = {
            {0, 0, 0, 0, 0},
            {1, 1, 1, 1, 0},
            {1, 1, 1, 1, 0},
            {1, 1, 1, 1, 0},
            {1, 1, 1, 1, 0}
        };
        int[][] expected = {
            {0, 0, 0, 0, 0},
            {1, 1, 1, 1, 0},
            {2, 2, 2, 1, 0},
            {3, 3, 2, 1, 0},
            {4, 3, 2, 1, 0}
        };
        assertArrayEquals(expected, test.updateMatrix(matrix));
    }

    @Test
    public void testDiagonalOnlyNoConnectivity() {
        // Zero at center, diagonal cells are distance 2 (not 1) since no diagonal moves
        int[][] matrix = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        int[][] expected = {
            {2, 1, 2},
            {1, 0, 1},
            {2, 1, 2}
        };
        assertArrayEquals(expected, test.updateMatrix(matrix));
    }

    @Test
    public void testLargeGrid50x50Seed42() {
        Random rand = new Random(42L);
        int n = 50;
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextInt(2);
            }
        }
        int[][] result = test.updateMatrix(matrix);
        assertEquals(n, result.length);
        assertEquals(n, result[0].length);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                assertTrue(result[i][j] >= 0, "Distance should be non-negative");
            }
        }
    }

    @Test
    public void testPropertyZerosStayZero() {
        int[][][] grids = {
            {{0, 1, 0}, {1, 0, 1}, {0, 1, 0}},
            {{0, 0}, {0, 0}},
            {{0, 1, 1}, {1, 1, 1}, {1, 1, 0}}
        };
        for (int[][] grid : grids) {
            int rows = grid.length, cols = grid[0].length;
            int[][] copy = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                copy[i] = grid[i].clone();
            }
            int[][] result = test.updateMatrix(copy);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 0) {
                        assertEquals(0, result[i][j], "Zero cell must remain 0");
                    }
                }
            }
        }
    }

    @Test
    public void testPropertyDistanceLeManhattanToNearestZero() {
        int[][] matrix = {
            {0, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 0, 1},
            {1, 1, 1, 1}
        };
        int rows = matrix.length, cols = matrix[0].length;
        int[][] copy = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            copy[i] = matrix[i].clone();
        }
        int[][] result = test.updateMatrix(copy);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) {
                    int minManhattan = Integer.MAX_VALUE;
                    for (int r = 0; r < rows; r++) {
                        for (int c = 0; c < cols; c++) {
                            if (matrix[r][c] == 0) {
                                minManhattan = Math.min(minManhattan, Math.abs(i - r) + Math.abs(j - c));
                            }
                        }
                    }
                    assertTrue(result[i][j] <= minManhattan,
                        "BFS distance at [" + i + "][" + j + "] should be <= manhattan distance to nearest 0");
                }
            }
        }
    }
}

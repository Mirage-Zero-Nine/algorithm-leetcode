package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

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
}

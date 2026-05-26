package solutions.dynamicprogramming;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinFallingPathSum_931Test {
    private final MinFallingPathSum_931 solution = new MinFallingPathSum_931();

    @Test
    void testBasic() {
        assertEquals(13, solution.minFallingPathSum(new int[][]{{2, 1, 3}, {6, 5, 4}, {7, 8, 9}}));
    }

    @Test
    void testSingleRow() {
        assertEquals(5, solution.minFallingPathSum(new int[][]{{5}}));
    }

    @Test
    void testNegatives() {
        assertEquals(-59, solution.minFallingPathSum(new int[][]{{-19, 57}, {-40, -5}}));
    }

    @Test
    void testTwoRows() {
        assertEquals(5, solution.minFallingPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }

    @Test
    void testAllNegative() {
        assertEquals(-6, solution.minFallingPathSum(new int[][]{{-1, -2}, {-3, -4}}));
    }

    @Test
    void testLargerMatrix() {
        assertEquals(7, solution.minFallingPathSum(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {1, 1, 1, 1}}));
    }

    @Test
    void testAllZeros() {
        assertEquals(0, solution.minFallingPathSum(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
    }

    @Test
    void testTwoByTwo() {
        assertEquals(2, solution.minFallingPathSum(new int[][]{{1, 2}, {1, 2}}));
    }

    @Test
    void testDiagonalPath() {
        assertEquals(3, solution.minFallingPathSum(new int[][]{{1, 100, 100}, {100, 1, 100}, {100, 100, 1}}));
    }

    @Test
    void testGiantMatrix() {
        int n = 100;
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = 1;
        assertEquals(n, solution.minFallingPathSum(matrix));
    }

    @Test
    void testSingleElement1x1() {
        assertEquals(-7, solution.minFallingPathSum(new int[][]{{-7}}));
        assertEquals(0, solution.minFallingPathSum(new int[][]{{0}}));
        assertEquals(99, solution.minFallingPathSum(new int[][]{{99}}));
    }

    @Test
    void testAllSameValue() {
        int val = 4;
        int n = 5;
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = val;
        assertEquals(n * val, solution.minFallingPathSum(matrix));
    }

    @Test
    void testAllZerosLarger() {
        int n = 7;
        int[][] matrix = new int[n][n];
        assertEquals(0, solution.minFallingPathSum(matrix));
    }

    @Test
    void test2x2MinPath() {
        // min path: 1 -> 3 = 4
        assertEquals(4, solution.minFallingPathSum(new int[][]{{1, 5}, {3, 9}}));
    }

    @Test
    void testLeetCodeExample() {
        assertEquals(13, solution.minFallingPathSum(new int[][]{{2, 1, 3}, {6, 5, 4}, {7, 8, 9}}));
    }

    @Test
    void testLargeGrid100x100RandomSeed42() {
        int n = 100;
        Random rand = new Random(42L);
        int[][] matrix = new int[n][n];
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextInt(201) - 100; // range [-100, 100]
                copy[i][j] = matrix[i][j];
            }
        int result = solution.minFallingPathSum(matrix);
        // brute force DP on copy
        for (int i = 1; i < n; i++)
            for (int j = 0; j < n; j++) {
                int min = copy[i - 1][j];
                if (j > 0) min = Math.min(min, copy[i - 1][j - 1]);
                if (j < n - 1) min = Math.min(min, copy[i - 1][j + 1]);
                copy[i][j] += min;
            }
        int expected = copy[n - 1][0];
        for (int j = 1; j < n; j++) expected = Math.min(expected, copy[n - 1][j]);
        assertEquals(expected, result);
    }

    @Test
    void testNegativeValues() {
        // path: -10 -> -20 -> -30 = -60
        assertEquals(-60, solution.minFallingPathSum(new int[][]{{-10, -5, -1}, {-3, -20, -2}, {-30, -4, -6}}));
    }

    @Test
    void testSingleColumnNx1Equivalent() {
        // For n x n, the "single column path" scenario: only one viable path down the diagonal
        // Matrix where optimal path is forced along one column
        int[][] matrix = {{1, 99, 99}, {99, 1, 99}, {99, 99, 1}};
        // path: 1 -> 1 -> 1 = 3
        assertEquals(3, solution.minFallingPathSum(matrix));
    }

    @Test
    void testPropertyResultLeqNTimesMax() {
        int n = 10;
        Random rand = new Random(123L);
        int[][] matrix = new int[n][n];
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextInt(201) - 100;
                maxVal = Math.max(maxVal, matrix[i][j]);
            }
        int result = solution.minFallingPathSum(matrix);
        assertTrue(result <= (long) n * maxVal, "result should be <= n * max value in matrix");
    }

    @Test
    void testPropertyResultGeqSumOfRowMins() {
        int n = 10;
        Random rand = new Random(456L);
        int[][] matrix = new int[n][n];
        int[][] copy = new int[n][n];
        int sumOfRowMins = 0;
        for (int i = 0; i < n; i++) {
            int rowMin = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextInt(201) - 100;
                copy[i][j] = matrix[i][j];
                rowMin = Math.min(rowMin, matrix[i][j]);
            }
            sumOfRowMins += rowMin;
        }
        int result = solution.minFallingPathSum(matrix);
        assertTrue(result >= sumOfRowMins, "result should be >= sum of row minimums");
    }
}

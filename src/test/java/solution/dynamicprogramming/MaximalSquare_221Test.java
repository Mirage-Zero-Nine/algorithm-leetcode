package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class MaximalSquare_221Test {

    private final MaximalSquare_221 test = new MaximalSquare_221();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.maximalSquare(new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maximalSquare(null));
        assertEquals(0, test.maximalSquare(new char[][]{{'0'}}));
        assertEquals(1, test.maximalSquare(new char[][]{{'1'}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(9, test.maximalSquare(new char[][]{{'1', '1', '1'}, {'1', '1', '1'}, {'1', '1', '1'}}));
    }

    @Test
    public void testEmptyMatrix() {
        assertEquals(0, test.maximalSquare(new char[][]{}));
    }

    @Test
    public void testEmptyRow() {
        assertEquals(0, test.maximalSquare(new char[][]{{}}));
    }

    @Test
    public void testAllZeros() {
        assertEquals(0, test.maximalSquare(new char[][]{{'0', '0'}, {'0', '0'}}));
    }

    @Test
    public void testSingleRow() {
        assertEquals(1, test.maximalSquare(new char[][]{{'1', '1', '1', '1'}}));
    }

    @Test
    public void testSingleColumn() {
        assertEquals(1, test.maximalSquare(new char[][]{{'1'}, {'1'}, {'1'}}));
    }

    @Test
    public void test2x2Square() {
        assertEquals(4, test.maximalSquare(new char[][]{{'1', '1'}, {'1', '1'}}));
    }

    @Test
    public void testGiantCase() {
        char[][] matrix = new char[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                matrix[i][j] = '1';
            }
        }
        assertEquals(2500, test.maximalSquare(matrix));
    }

    @Test
    public void testDiagonalOnes() {
        assertEquals(1, test.maximalSquare(new char[][]{{'1', '0', '0'}, {'0', '1', '0'}, {'0', '0', '1'}}));
    }

    @Test
    public void testAllOnesMxN() {
        // 3x5 all ones -> min(3,5)^2 = 9
        char[][] matrix = new char[3][5];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 5; j++)
                matrix[i][j] = '1';
        assertEquals(9, test.maximalSquare(matrix));
    }

    @Test
    public void testLShapeOnes() {
        // L-shape: no 2x2 block of all ones
        char[][] matrix = {
                {'1', '0'},
                {'1', '0'},
                {'1', '1'}
        };
        assertEquals(1, test.maximalSquare(matrix));
    }

    @Test
    public void test3x3WithOneZero() {
        // 3x3 all ones except center -> every 2x2 includes center, max square is 1
        char[][] matrix = {
                {'1', '1', '1'},
                {'1', '0', '1'},
                {'1', '1', '1'}
        };
        assertEquals(1, test.maximalSquare(matrix));
    }

    @Test
    public void testCheckerboardPattern() {
        // Checkerboard: no two adjacent ones form a 2x2 square
        char[][] matrix = new char[4][4];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                matrix[i][j] = (i + j) % 2 == 0 ? '1' : '0';
        assertEquals(1, test.maximalSquare(matrix));
    }

    @Test
    public void testLongRowOfOnes() {
        // 1x100 row of all ones -> max square is 1x1 = 1
        char[][] matrix = new char[1][100];
        for (int j = 0; j < 100; j++) matrix[0][j] = '1';
        assertEquals(1, test.maximalSquare(matrix));
    }

    @Test
    public void testLongColumnOfOnes() {
        // 100x1 column of all ones -> max square is 1x1 = 1
        char[][] matrix = new char[100][1];
        for (int i = 0; i < 100; i++) matrix[i][0] = '1';
        assertEquals(1, test.maximalSquare(matrix));
    }

    @Test
    public void testAllZerosLarge() {
        // 20x20 all zeros -> 0
        char[][] matrix = new char[20][20];
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++)
                matrix[i][j] = '0';
        assertEquals(0, test.maximalSquare(matrix));
    }

    @Test
    public void testLarge50x50CrossCheck() {
        // Random 50x50 matrix, cross-check with reference DP
        Random rand = new Random(42L);
        char[][] matrix = new char[50][50];
        for (int i = 0; i < 50; i++)
            for (int j = 0; j < 50; j++)
                matrix[i][j] = rand.nextBoolean() ? '1' : '0';

        int expected = referenceMaximalSquare(matrix);
        assertEquals(expected, test.maximalSquare(matrix));
    }

    @Test
    public void testPropertyResultIsPerfectSquare() {
        // For any input, result must be a perfect square
        Random rand = new Random(123L);
        char[][] matrix = new char[10][15];
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 15; j++)
                matrix[i][j] = rand.nextBoolean() ? '1' : '0';

        int result = test.maximalSquare(matrix);
        int sqrt = (int) Math.round(Math.sqrt(result));
        assertTrue(sqrt * sqrt == result, "Result " + result + " is not a perfect square");
    }

    private int referenceMaximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length, max = 0;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    max = Math.max(max, dp[i][j]);
                }
        return max * max;
    }
}

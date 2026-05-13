package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}

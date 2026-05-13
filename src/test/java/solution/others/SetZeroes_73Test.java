package solution.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SetZeroes_73Test {

    private final SetZeroes_73 test = new SetZeroes_73();

    @Test
    public void testHappyCases() {
        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        test.setZeroes(matrix);
        assertArrayEquals(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}, matrix);
    }

    @Test
    public void testEdgeCases() {
        int[][] matrix = {{0}};
        test.setZeroes(matrix);
        assertArrayEquals(new int[][]{{0}}, matrix);
    }

    @Test
    public void testLargeCase() {
        int[][] matrix = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        test.setZeroes(matrix);
        assertArrayEquals(new int[]{0, 0, 0, 0}, matrix[0]);
        assertArrayEquals(new int[]{0, 4, 5, 0}, matrix[1]);
    }

    @Test
    public void testNoZeroes() {
        int[][] matrix = {{1, 2}, {3, 4}};
        test.setZeroes(matrix);
        assertArrayEquals(new int[][]{{1, 2}, {3, 4}}, matrix);
    }

    @Test
    public void testAllZeroes() {
        int[][] matrix = {{0, 0}, {0, 0}};
        test.setZeroes(matrix);
        assertArrayEquals(new int[][]{{0, 0}, {0, 0}}, matrix);
    }

    @Test
    public void testZeroInFirstRow() {
        int[][] matrix = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
        test.setZeroes(matrix);
        assertArrayEquals(new int[]{0, 0, 0}, matrix[0]);
        assertArrayEquals(new int[]{0, 4, 5}, matrix[1]);
        assertArrayEquals(new int[]{0, 7, 8}, matrix[2]);
    }

    @Test
    public void testZeroInFirstColumn() {
        int[][] matrix = {{1, 2, 3}, {0, 4, 5}, {6, 7, 8}};
        test.setZeroes(matrix);
        assertArrayEquals(new int[]{0, 2, 3}, matrix[0]);
        assertArrayEquals(new int[]{0, 0, 0}, matrix[1]);
        assertArrayEquals(new int[]{0, 7, 8}, matrix[2]);
    }

    @Test
    public void testSingleRow() {
        int[][] matrix = {{1, 0, 3}};
        test.setZeroes(matrix);
        assertArrayEquals(new int[]{0, 0, 0}, matrix[0]);
    }

    @Test
    public void testSingleColumn() {
        int[][] matrix = {{1}, {0}, {3}};
        test.setZeroes(matrix);
        assertArrayEquals(new int[][]{{0}, {0}, {0}}, matrix);
    }

    @Test
    public void testMultipleZeroes() {
        int[][] matrix = {{1, 2, 3, 4}, {5, 0, 7, 8}, {9, 10, 0, 12}, {13, 14, 15, 16}};
        test.setZeroes(matrix);
        assertArrayEquals(new int[]{1, 0, 0, 4}, matrix[0]);
        assertArrayEquals(new int[]{0, 0, 0, 0}, matrix[1]);
        assertArrayEquals(new int[]{0, 0, 0, 0}, matrix[2]);
        assertArrayEquals(new int[]{13, 0, 0, 16}, matrix[3]);
    }

    @Test
    public void testGiantCase() {
        int n = 100;
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = 1;
        matrix[50][50] = 0;
        test.setZeroes(matrix);
        for (int j = 0; j < n; j++) assertEquals(0, matrix[50][j]);
        for (int i = 0; i < n; i++) assertEquals(0, matrix[i][50]);
        assertEquals(1, matrix[0][0]);
    }
}

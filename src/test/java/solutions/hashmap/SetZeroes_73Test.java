package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
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

    @Test
    public void test1x1Nonzero() {
        int[][] matrix = {{5}};
        test.setZeroes(matrix);
        assertArrayEquals(new int[][]{{5}}, matrix);
    }

    @Test
    public void test1x1Zero() {
        int[][] matrix = {{0}};
        test.setZeroes(matrix);
        assertArrayEquals(new int[][]{{0}}, matrix);
    }

    @Test
    public void test1xNRowWithOneZero() {
        int[][] matrix = {{1, 2, 0, 4, 5}};
        test.setZeroes(matrix);
        assertArrayEquals(new int[][]{{0, 0, 0, 0, 0}}, matrix);
    }

    @Test
    public void testMx1ColWithOneZero() {
        int[][] matrix = {{1}, {2}, {0}, {4}, {5}};
        test.setZeroes(matrix);
        assertArrayEquals(new int[][]{{0}, {0}, {0}, {0}, {0}}, matrix);
    }

    @Test
    public void testAllZeroesLarger() {
        int[][] matrix = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        test.setZeroes(matrix);
        assertArrayEquals(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, matrix);
    }

    @Test
    public void testNoZeroesLarger() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        test.setZeroes(matrix);
        assertArrayEquals(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, matrix);
    }

    @Test
    public void testLeetCodeExample1() {
        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        test.setZeroes(matrix);
        assertArrayEquals(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}, matrix);
    }

    @Test
    public void testLeetCodeExample2() {
        int[][] matrix = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        test.setZeroes(matrix);
        assertArrayEquals(new int[][]{{0, 0, 0, 0}, {0, 4, 5, 0}, {0, 3, 1, 0}}, matrix);
    }

    @Test
    public void testLarge50x50Seed42() {
        Random rand = new Random(42L);
        int[][] matrix = new int[50][50];
        for (int i = 0; i < 50; i++)
            for (int j = 0; j < 50; j++)
                matrix[i][j] = rand.nextInt(10); // 0-9, ~10% zeros

        // Record which rows/cols should be zeroed
        boolean[] zeroRow = new boolean[50];
        boolean[] zeroCol = new boolean[50];
        for (int i = 0; i < 50; i++)
            for (int j = 0; j < 50; j++)
                if (matrix[i][j] == 0) { zeroRow[i] = true; zeroCol[j] = true; }

        int[][] original = new int[50][50];
        for (int i = 0; i < 50; i++) original[i] = matrix[i].clone();

        test.setZeroes(matrix);

        for (int i = 0; i < 50; i++)
            for (int j = 0; j < 50; j++) {
                if (zeroRow[i] || zeroCol[j]) assertEquals(0, matrix[i][j], "Cell [" + i + "][" + j + "] should be 0");
                else assertEquals(original[i][j], matrix[i][j], "Cell [" + i + "][" + j + "] should be unchanged");
            }
    }

    @Test
    public void testPropertyZeroPropagationAndPreservation() {
        int[][] matrix = {{2, 3, 0, 5}, {1, 7, 8, 9}, {0, 2, 3, 4}, {5, 6, 7, 8}};
        boolean[] zeroRow = new boolean[4];
        boolean[] zeroCol = new boolean[4];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (matrix[i][j] == 0) { zeroRow[i] = true; zeroCol[j] = true; }

        int[][] original = new int[4][4];
        for (int i = 0; i < 4; i++) original[i] = matrix[i].clone();

        test.setZeroes(matrix);

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                if (zeroRow[i] || zeroCol[j]) assertEquals(0, matrix[i][j]);
                else assertEquals(original[i][j], matrix[i][j]);
            }
    }
}

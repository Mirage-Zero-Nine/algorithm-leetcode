package solution.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Rotate_48Test {

    private final Rotate_48 test = new Rotate_48();

    @Test
    public void testHappyCases() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        test.rotate(matrix);
        assertArrayEquals(new int[]{7, 4, 1}, matrix[0]);
        assertArrayEquals(new int[]{9, 6, 3}, matrix[2]);
    }

    @Test
    public void testEdgeCases() {
        int[][] matrix = {{1}};
        test.rotate(matrix);
        assertArrayEquals(new int[][]{{1}}, matrix);
        int[][] empty = {};
        test.rotate(empty);
    }

    @Test
    public void testLargeCase() {
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        test.rotate(matrix);
        assertArrayEquals(new int[]{15, 13, 2, 5}, matrix[0]);
    }

    @Test
    public void test2x2() {
        int[][] matrix = {{1, 2}, {3, 4}};
        test.rotate(matrix);
        assertArrayEquals(new int[][]{{3, 1}, {4, 2}}, matrix);
    }

    @Test
    public void test3x3FullVerify() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        test.rotate(matrix);
        assertArrayEquals(new int[][]{{7, 4, 1}, {8, 5, 2}, {9, 6, 3}}, matrix);
    }

    @Test
    public void test4x4FullVerify() {
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        test.rotate(matrix);
        assertArrayEquals(new int[][]{{15, 13, 2, 5}, {14, 3, 4, 1}, {12, 6, 8, 9}, {16, 7, 10, 11}}, matrix);
    }

    @Test
    public void testAllSameValues() {
        int[][] matrix = {{1, 1}, {1, 1}};
        test.rotate(matrix);
        assertArrayEquals(new int[][]{{1, 1}, {1, 1}}, matrix);
    }

    @Test
    public void testNegativeValues() {
        int[][] matrix = {{-1, -2}, {-3, -4}};
        test.rotate(matrix);
        assertArrayEquals(new int[][]{{-3, -1}, {-4, -2}}, matrix);
    }

    @Test
    public void testRotateTwice() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        test.rotate(matrix);
        test.rotate(matrix);
        // 180 degree rotation
        assertArrayEquals(new int[][]{{9, 8, 7}, {6, 5, 4}, {3, 2, 1}}, matrix);
    }

    @Test
    public void testGiantCase() {
        int n = 100;
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = i * n + j;
        test.rotate(matrix);
        // After clockwise rotation: new[i][j] = old[n-1-j][i]
        assertEquals((n - 1) * n, matrix[0][0]); // old[99][0] = 99*100 = 9900
        assertEquals(0, matrix[0][n - 1]); // old[0][0] = 0
    }
}

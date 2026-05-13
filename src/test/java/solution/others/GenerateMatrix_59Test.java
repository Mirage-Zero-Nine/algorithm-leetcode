package solution.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GenerateMatrix_59Test {

    private final GenerateMatrix_59 test = new GenerateMatrix_59();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[][]{{1, 2, 3}, {8, 9, 4}, {7, 6, 5}}, test.generateMatrix(3));
    }

    @Test
    public void testEdgeCases() {
        assertArrayEquals(new int[][]{{1}}, test.generateMatrix(1));
        assertArrayEquals(new int[][]{{1, 2}, {4, 3}}, test.generateMatrix(2));
    }

    @Test
    public void testLargeCase() {
        int[][] result = test.generateMatrix(4);
        assertArrayEquals(new int[]{1, 2, 3, 4}, result[0]);
        assertArrayEquals(new int[]{12, 13, 14, 5}, result[1]);
    }

    @Test
    public void testZero() {
        int[][] result = test.generateMatrix(0);
        assertEquals(0, result.length);
    }

    @Test
    public void testFourFull() {
        int[][] result = test.generateMatrix(4);
        assertArrayEquals(new int[]{11, 16, 15, 6}, result[2]);
        assertArrayEquals(new int[]{10, 9, 8, 7}, result[3]);
    }

    @Test
    public void testFiveFirstRow() {
        int[][] result = test.generateMatrix(5);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result[0]);
    }

    @Test
    public void testFiveLastRow() {
        int[][] result = test.generateMatrix(5);
        assertArrayEquals(new int[]{13, 12, 11, 10, 9}, result[4]);
    }

    @Test
    public void testFiveCenter() {
        int[][] result = test.generateMatrix(5);
        assertEquals(25, result[2][2]);
    }

    @Test
    public void testSixCorners() {
        int[][] result = test.generateMatrix(6);
        assertEquals(1, result[0][0]);
        assertEquals(6, result[0][5]);
        assertEquals(16, result[5][0]);
        assertEquals(11, result[5][5]);
    }

    @Test
    public void testGiantCase() {
        int[][] result = test.generateMatrix(100);
        assertEquals(100, result.length);
        assertEquals(1, result[0][0]);
        assertEquals(100, result[0][99]);
        assertEquals(9998, result[49][50]);
    }
}

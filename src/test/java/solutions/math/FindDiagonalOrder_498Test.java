package solutions.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class FindDiagonalOrder_498Test {

    private final FindDiagonalOrder_498 test = new FindDiagonalOrder_498();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{1, 2, 4, 7, 5, 3, 6, 8, 9},
            test.findDiagonalOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[]{}, test.findDiagonalOrder(new int[][]{}));
        assertArrayEquals(new int[]{1}, test.findDiagonalOrder(new int[][]{{1}}));
    }

    @Test
    public void testLargeCase() {
        assertArrayEquals(new int[]{1, 2, 3, 4}, test.findDiagonalOrder(new int[][]{{1, 2}, {3, 4}}));
    }

    @Test
    public void testSingleRow() {
        assertArrayEquals(new int[]{1, 2, 3, 4}, test.findDiagonalOrder(new int[][]{{1, 2, 3, 4}}));
    }

    @Test
    public void testSingleColumn() {
        assertArrayEquals(new int[]{1, 2, 3, 4}, test.findDiagonalOrder(new int[][]{{1}, {2}, {3}, {4}}));
    }

    @Test
    public void testTwoByThree() {
        assertArrayEquals(new int[]{1, 2, 4, 5, 3, 6},
            test.findDiagonalOrder(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }

    @Test
    public void testThreeByTwo() {
        assertArrayEquals(new int[]{1, 2, 3, 5, 4, 6},
            test.findDiagonalOrder(new int[][]{{1, 2}, {3, 4}, {5, 6}}));
    }

    @Test
    public void testNullMatrix() {
        assertArrayEquals(new int[]{}, test.findDiagonalOrder(null));
    }

    @Test
    public void testEmptyRow() {
        assertArrayEquals(new int[]{}, test.findDiagonalOrder(new int[][]{{}}));
    }

    @Test
    public void testGiantCase() {
        int[][] matrix = new int[100][100];
        int val = 0;
        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 100; j++)
                matrix[i][j] = val++;
        int[] result = test.findDiagonalOrder(matrix);
        assertArrayEquals(new int[]{0}, new int[]{result[0]});
        assertArrayEquals(new int[]{9999}, new int[]{result[9999]});
    }
}

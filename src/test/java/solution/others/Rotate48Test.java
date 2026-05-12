package solution.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class Rotate48Test {

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
}

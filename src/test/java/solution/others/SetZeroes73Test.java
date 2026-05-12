package solution.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class SetZeroes73Test {

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
}

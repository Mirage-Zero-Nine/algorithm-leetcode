package solution.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class GenerateMatrix59Test {

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
}

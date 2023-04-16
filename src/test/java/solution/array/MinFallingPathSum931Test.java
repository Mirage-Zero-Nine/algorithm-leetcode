package solution.array;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2023/04/16 01:11
 * Created with IntelliJ IDEA
 */

public class MinFallingPathSum931Test {

    private final MinFallingPathSum_931 test = new MinFallingPathSum_931();

    @Test
    public void test() {
        int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assertEquals(12, test.minFallingPathSum(matrix1));

        int[][] matrix2 = {{-19, 57}, {-40, -5}};
        assertEquals(-59, test.minFallingPathSum(matrix2));

        int[][] matrix3 = {{-48}};
        assertEquals(-48, test.minFallingPathSum(matrix3));

        int[][] matrix5 = {{-19, 57, -9, 20}, {-40, -5, 13, 14}, {-60, -11, 7, 14}, {-51, -46, 47, 31}};
        assertEquals(-170, test.minFallingPathSum(matrix5));
    }
}
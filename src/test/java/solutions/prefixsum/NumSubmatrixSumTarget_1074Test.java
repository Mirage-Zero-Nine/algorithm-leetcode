package solutions.prefixsum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumSubmatrixSumTarget_1074Test {
    private final NumSubmatrixSumTarget_1074 solution = new NumSubmatrixSumTarget_1074();

    @Test
    void testBasic() {
        assertEquals(4, solution.numSubmatrixSumTarget(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 0));
    }

    @Test
    void testSingleCell() {
        assertEquals(1, solution.numSubmatrixSumTarget(new int[][]{{1}}, 1));
    }

    @Test
    void testNoMatch() {
        assertEquals(1, solution.numSubmatrixSumTarget(new int[][]{{1, 2}, {3, 4}}, 10));
    }

    @Test
    void testNegatives() {
        assertEquals(5, solution.numSubmatrixSumTarget(new int[][]{{1, -1}, {-1, 1}}, 0));
    }

    @Test
    void testLargeTarget() {
        assertEquals(0, solution.numSubmatrixSumTarget(new int[][]{{904}}, 0));
    }

    @Test
    void testSingleCellZeroTarget() {
        assertEquals(1, solution.numSubmatrixSumTarget(new int[][]{{0}}, 0));
    }

    @Test
    void testAllOnes() {
        assertEquals(4, solution.numSubmatrixSumTarget(new int[][]{{1, 1}, {1, 1}}, 1));
    }

    @Test
    void testTargetEqualsFullMatrix() {
        assertEquals(1, solution.numSubmatrixSumTarget(new int[][]{{1, 2}, {3, 4}}, 10));
    }

    @Test
    void testSingleRow() {
        assertEquals(2, solution.numSubmatrixSumTarget(new int[][]{{1, 2, 3}}, 3));
    }

    @Test
    void testSingleColumn() {
        assertEquals(2, solution.numSubmatrixSumTarget(new int[][]{{1}, {2}, {3}}, 3));
    }

    @Test
    void testNegativeTarget() {
        assertEquals(2, solution.numSubmatrixSumTarget(new int[][]{{1, -1}, {-1, 1}}, -1));
    }

    @Test
    void testGiantCase() {
        int[][] matrix = new int[50][50];
        for (int i = 0; i < 50; i++)
            for (int j = 0; j < 50; j++)
                matrix[i][j] = 1;
        // each 1x1 submatrix sums to 1, there are 2500 of them; plus larger submatrices won't sum to 1
        // Actually only 1x1 cells sum to 1
        assertEquals(2500, solution.numSubmatrixSumTarget(matrix, 1));
    }
}

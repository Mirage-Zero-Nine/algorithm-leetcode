package solutions.prefixsum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumSubmatrixSumTarget_1074Test {

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.ValueSource(ints = {-6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6})
    void allTwoByThreeSignedMatricesMatchRectangleEnumeration(int target) {
        for (int code = 0; code < 729; code++) {
            int[][] matrix = new int[2][3];
            for (int i = 0, value = code; i < 6; i++, value /= 3) matrix[i / 3][i % 3] = value % 3 - 1;
            int expected = 0;
            for (int top = 0; top < 2; top++) for (int bottom = top; bottom < 2; bottom++) {
                for (int left = 0; left < 3; left++) for (int right = left; right < 3; right++) {
                    int sum = 0;
                    for (int row = top; row <= bottom; row++) for (int col = left; col <= right; col++) sum += matrix[row][col];
                    if (sum == target) expected++;
                }
            }
            assertEquals(expected, solution.numSubmatrixSumTarget(matrix, target), "matrix code=" + code);
        }
    }

    @Test
    void zeroRectangleCountsAllChoicesOfBothBoundaries() {
        assertEquals(100 * 101 / 2 * (100 * 101 / 2),
                solution.numSubmatrixSumTarget(new int[100][100], 0));
    }

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

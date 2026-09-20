package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Contract tests for the recursive search in {@link SearchMatrix_240}.
 *
 * <p>Expected values are either explicit contract examples or are calculated by an independent
 * direct scan. The scan is intentionally not another implementation of the recursive partitioning
 * used by the solution, so generated cases can expose incorrect pruning.
 */
public class SearchMatrix_240Test {

    private final SearchMatrix_240 solution = new SearchMatrix_240();

    @Test
    public void testOfficialExampleFound() {
        int[][] matrix = officialMatrix();
        assertTrue(solution.searchMatrix(matrix, 5));
    }

    @Test
    public void testOfficialExampleMissing() {
        int[][] matrix = officialMatrix();
        assertFalse(solution.searchMatrix(matrix, 20));
    }

    @Test
    public void testEmptyMatrixReturnsFalse() {
        assertFalse(solution.searchMatrix(new int[0][0], 1));
    }

    @Test
    public void testSingleCellFound() {
        assertTrue(solution.searchMatrix(new int[][]{{7}}, 7));
    }

    @Test
    public void testSingleCellMissing() {
        assertFalse(solution.searchMatrix(new int[][]{{7}}, 6));
    }

    @Test
    public void testSingleRowFindsEveryBoundary() {
        int[][] matrix = {{-4, -1, 0, 6, 13}};
        assertTrue(solution.searchMatrix(matrix, -4));
        assertTrue(solution.searchMatrix(matrix, 0));
        assertTrue(solution.searchMatrix(matrix, 13));
    }

    @Test
    public void testSingleRowRejectsGapsAndOutsideValues() {
        int[][] matrix = {{-4, -1, 0, 6, 13}};
        assertFalse(solution.searchMatrix(matrix, -5));
        assertFalse(solution.searchMatrix(matrix, 5));
        assertFalse(solution.searchMatrix(matrix, 14));
    }

    @Test
    public void testSingleColumnFindsEveryBoundary() {
        int[][] matrix = {{-4}, {-1}, {0}, {6}, {13}};
        assertTrue(solution.searchMatrix(matrix, -4));
        assertTrue(solution.searchMatrix(matrix, 0));
        assertTrue(solution.searchMatrix(matrix, 13));
    }

    @Test
    public void testSingleColumnRejectsGapsAndOutsideValues() {
        int[][] matrix = {{-4}, {-1}, {0}, {6}, {13}};
        assertFalse(solution.searchMatrix(matrix, -5));
        assertFalse(solution.searchMatrix(matrix, 5));
        assertFalse(solution.searchMatrix(matrix, 14));
    }

    @Test
    public void testWideRectangularMatrix() {
        int[][] matrix = {
                {1, 4, 7, 10, 13, 16},
                {2, 5, 8, 11, 14, 17}
        };
        assertTrue(solution.searchMatrix(matrix, 16));
        assertTrue(solution.searchMatrix(matrix, 2));
        assertFalse(solution.searchMatrix(matrix, 12));
    }

    @Test
    public void testTallRectangularMatrix() {
        int[][] matrix = {
                {1, 4},
                {2, 5},
                {3, 6},
                {7, 10},
                {8, 11},
                {9, 12}
        };
        assertTrue(solution.searchMatrix(matrix, 7));
        assertTrue(solution.searchMatrix(matrix, 12));
        assertFalse(solution.searchMatrix(matrix, 13));
    }

    @Test
    public void testTargetsBetweenValuesAreRejected() {
        int[][] matrix = {{1, 10, 20}, {2, 11, 21}, {3, 12, 22}};
        assertFalse(solution.searchMatrix(matrix, 0));
        assertFalse(solution.searchMatrix(matrix, 15));
        assertFalse(solution.searchMatrix(matrix, 23));
    }

    @Test
    public void testTargetOutsideBothEndsIsRejected() {
        int[][] matrix = {{2, 3, 7}, {4, 5, 8}, {6, 9, 11}};
        assertFalse(solution.searchMatrix(matrix, 1));
        assertFalse(solution.searchMatrix(matrix, 12));
    }

    @Test
    public void testDuplicateValuesAcrossRowsAndColumns() {
        int[][] matrix = {
                {1, 1, 2, 3},
                {1, 2, 2, 4},
                {2, 2, 3, 5}
        };
        assertTrue(solution.searchMatrix(matrix, 1));
        assertTrue(solution.searchMatrix(matrix, 2));
        assertTrue(solution.searchMatrix(matrix, 5));
        assertFalse(solution.searchMatrix(matrix, 0));
        assertFalse(solution.searchMatrix(matrix, 6));
    }

    @Test
    public void testAllEqualMatrix() {
        int[][] matrix = {
                {9, 9, 9, 9},
                {9, 9, 9, 9},
                {9, 9, 9, 9}
        };
        assertTrue(solution.searchMatrix(matrix, 9));
        assertFalse(solution.searchMatrix(matrix, 8));
        assertFalse(solution.searchMatrix(matrix, 10));
    }

    @Test
    public void testNegativeAndZeroValues() {
        int[][] matrix = {
                {-9, -5, -1, 2},
                {-8, -4, 0, 3},
                {-7, -3, 1, 4}
        };
        assertTrue(solution.searchMatrix(matrix, -9));
        assertTrue(solution.searchMatrix(matrix, 0));
        assertTrue(solution.searchMatrix(matrix, 4));
        assertFalse(solution.searchMatrix(matrix, -2));
    }

    @Test
    public void testIntegerExtremesRemainOrdered() {
        int[][] matrix = {
                {Integer.MIN_VALUE, -1, 0, Integer.MAX_VALUE - 1},
                {Integer.MIN_VALUE + 1, 0, 1, Integer.MAX_VALUE},
                {Integer.MIN_VALUE + 2, 1, 2, Integer.MAX_VALUE}
        };
        assertTrue(solution.searchMatrix(matrix, Integer.MIN_VALUE));
        assertTrue(solution.searchMatrix(matrix, Integer.MAX_VALUE));
        assertTrue(solution.searchMatrix(matrix, 1));
        assertFalse(solution.searchMatrix(matrix, Integer.MIN_VALUE + 3));
    }

    @Test
    public void testTopRightAndBottomLeftElements() {
        int[][] matrix = officialMatrix();
        assertTrue(solution.searchMatrix(matrix, 15));
        assertTrue(solution.searchMatrix(matrix, 18));
    }

    @Test
    public void testOneRowMatrixWithNoColumnsUsesImplementationGuard() {
        assertFalse(solution.searchMatrix(new int[][]{{}}, 0));
    }

    @Test
    public void testRepeatedCallsDoNotShareSearchState() {
        assertTrue(solution.searchMatrix(new int[][]{{1, 3}, {2, 4}}, 4));
        assertFalse(solution.searchMatrix(new int[][]{{10, 20}, {15, 25}}, 4));
        assertTrue(solution.searchMatrix(new int[][]{{-8, -2}, {-4, 0}}, -4));
        assertFalse(solution.searchMatrix(new int[][]{{-8, -2}, {-4, 0}}, 7));
    }

    @Test
    public void testInputMatrixIsNotMutated() {
        int[][] matrix = officialMatrix();
        int[][] original = copy(matrix);

        assertTrue(solution.searchMatrix(matrix, 24));
        assertFalse(solution.searchMatrix(matrix, 25));

        for (int row = 0; row < matrix.length; row++) {
            assertArrayEquals(original[row], matrix[row]);
        }
    }

    @Test
    public void testMaxDimensionMatrixFindsCornersAndInterior() {
        int[][] matrix = affineMatrix(300, 300, -1_000_000_000, 3_000_000, 2_000);
        assertTrue(solution.searchMatrix(matrix, matrix[0][0]));
        assertTrue(solution.searchMatrix(matrix, matrix[149][217]));
        assertTrue(solution.searchMatrix(matrix, matrix[299][299]));
    }

    @Test
    public void testMaxDimensionMatrixRejectsMissingTargets() {
        int[][] matrix = affineMatrix(300, 300, -1_000_000_000, 3_000_000, 2_000);
        assertFalse(solution.searchMatrix(matrix, -1_000_000_001));
        assertFalse(solution.searchMatrix(matrix, -101_000_000));
        assertFalse(solution.searchMatrix(matrix, Integer.MAX_VALUE));
    }

    @Test
    public void testExhaustiveSmallAffineMatricesAgainstScan() {
        for (int rows = 1; rows <= 4; rows++) {
            for (int columns = 1; columns <= 5; columns++) {
                for (int rowStep = 0; rowStep <= 3; rowStep++) {
                    for (int columnStep = 0; columnStep <= 3; columnStep++) {
                        int[][] matrix = affineMatrix(rows, columns, -7, rowStep, columnStep);
                        for (int target = -9; target <= 12; target++) {
                            assertEquals(scan(matrix, target), solution.searchMatrix(matrix, target),
                                    "rows=" + rows + ", columns=" + columns
                                            + ", rowStep=" + rowStep + ", columnStep=" + columnStep
                                            + ", target=" + target);
                        }
                    }
                }
            }
        }
    }

    @Test
    public void testSeededRandomSortedMatricesAgainstScan() {
        Random random = new Random(240L);
        for (int caseNumber = 0; caseNumber < 120; caseNumber++) {
            int rows = 1 + random.nextInt(12);
            int columns = 1 + random.nextInt(13);
            int base = -1_000 + random.nextInt(2_001);
            int rowStep = random.nextInt(8);
            int columnStep = random.nextInt(8);
            int[][] matrix = affineMatrix(rows, columns, base, rowStep, columnStep);

            for (int query = 0; query < 20; query++) {
                int target;
                if (query % 2 == 0) {
                    target = matrix[random.nextInt(rows)][random.nextInt(columns)];
                } else {
                    target = base - 10 + random.nextInt(2_000);
                }
                assertEquals(scan(matrix, target), solution.searchMatrix(matrix, target),
                        "random case=" + caseNumber + ", query=" + query);
            }
        }
    }

    @Test
    public void testSeededIrregularSortedMatricesAgainstScan() {
        Random random = new Random(2_400_240L);
        for (int caseNumber = 0; caseNumber < 100; caseNumber++) {
            int rows = 1 + random.nextInt(10);
            int columns = 1 + random.nextInt(11);
            int[][] matrix = new int[rows][columns];
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    int lowerBound = -500;
                    if (row > 0) {
                        lowerBound = Math.max(lowerBound, matrix[row - 1][column]);
                    }
                    if (column > 0) {
                        lowerBound = Math.max(lowerBound, matrix[row][column - 1]);
                    }
                    matrix[row][column] = lowerBound + random.nextInt(5);
                }
            }

            for (int query = 0; query < 24; query++) {
                int target = query % 3 == 0
                        ? matrix[random.nextInt(rows)][random.nextInt(columns)]
                        : -520 + random.nextInt(700);
                assertEquals(scan(matrix, target), solution.searchMatrix(matrix, target),
                        "irregular case=" + caseNumber + ", query=" + query);
            }
        }
    }

    @Test
    public void testTargetAtEveryCellInAStaircaseMatrix() {
        int[][] matrix = {
                {1, 4, 7, 11},
                {2, 5, 8, 12},
                {3, 6, 9, 16},
                {10, 13, 14, 17}
        };
        for (int[] row : matrix) {
            for (int value : row) {
                assertTrue(solution.searchMatrix(matrix, value), "value=" + value);
            }
        }
    }

    private static int[][] officialMatrix() {
        return new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
    }

    private static int[][] affineMatrix(int rows, int columns, int base, int rowStep, int columnStep) {
        int[][] matrix = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                matrix[row][column] = base + row * rowStep + column * columnStep;
            }
        }
        return matrix;
    }

    private static boolean scan(int[][] matrix, int target) {
        for (int[] row : matrix) {
            for (int value : row) {
                if (value == target) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int[][] copy(int[][] matrix) {
        int[][] result = new int[matrix.length][];
        for (int row = 0; row < matrix.length; row++) {
            result[row] = matrix[row].clone();
        }
        return result;
    }
}

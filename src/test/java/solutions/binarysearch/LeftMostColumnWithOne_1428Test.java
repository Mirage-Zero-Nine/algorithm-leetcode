package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import library.BinaryMatrix;
import org.junit.jupiter.api.Test;

/**
 * Contract tests for the top-right staircase solution to LeetCode 1428.
 *
 * <p>The expected result is calculated by a direct scan of the backing array, independently of
 * the solution's traversal. The adapter also records calls to the restricted API so the tests
 * exercise the problem's 1,000-call limit rather than accidentally testing direct matrix access.
 */
public class LeftMostColumnWithOne_1428Test {

    private final LeftMostColumnWithOne_1428 solution = new LeftMostColumnWithOne_1428();

    @Test
    public void testOfficialExampleOne() {
        assertMatchesOracle(new int[][]{{0, 0}, {1, 1}});
    }

    @Test
    public void testOfficialExampleTwo() {
        assertMatchesOracle(new int[][]{{0, 0}, {0, 1}});
    }

    @Test
    public void testOfficialExampleThreeHasNoOne() {
        assertMatchesOracle(new int[][]{{0, 0}, {0, 0}});
    }

    @Test
    public void testSingleCellZeroIsNegativeCase() {
        assertMatchesOracle(new int[][]{{0}});
    }

    @Test
    public void testSingleCellOneIsLeftmostColumn() {
        assertMatchesOracle(new int[][]{{1}});
    }

    @Test
    public void testSingleRowAllZeros() {
        assertMatchesOracle(new int[][]{{0, 0, 0, 0, 0}});
    }

    @Test
    public void testSingleRowOneAtFirstColumn() {
        assertMatchesOracle(new int[][]{{1, 1, 1, 1, 1}});
    }

    @Test
    public void testSingleRowOneOnlyAtLastColumn() {
        assertMatchesOracle(new int[][]{{0, 0, 0, 0, 1}});
    }

    @Test
    public void testSingleRowTransitionAtEveryColumn() {
        for (int firstOne = 0; firstOne <= 7; firstOne++) {
            int[][] matrix = new int[1][8];
            for (int column = firstOne; column < matrix[0].length; column++) {
                matrix[0][column] = 1;
            }
            assertMatchesOracle(matrix);
        }
    }

    @Test
    public void testSingleColumnAllZeros() {
        assertMatchesOracle(new int[][]{{0}, {0}, {0}, {0}});
    }

    @Test
    public void testSingleColumnOneInFirstRow() {
        assertMatchesOracle(new int[][]{{1}, {0}, {0}, {0}});
    }

    @Test
    public void testSingleColumnOneInLastRow() {
        assertMatchesOracle(new int[][]{{0}, {0}, {0}, {1}});
    }

    @Test
    public void testSingleColumnAllOnes() {
        assertMatchesOracle(new int[][]{{1}, {1}, {1}, {1}});
    }

    @Test
    public void testAllOnesMatrix() {
        assertMatchesOracle(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}});
    }

    @Test
    public void testOnlyLastColumnContainsOnes() {
        assertMatchesOracle(new int[][]{{0, 0, 1}, {0, 0, 1}, {0, 0, 1}, {0, 0, 1}});
    }

    @Test
    public void testFirstColumnAppearsInMiddleRow() {
        assertMatchesOracle(new int[][]{{0, 0, 0}, {1, 1, 1}, {0, 1, 1}});
    }

    @Test
    public void testFirstColumnAppearsOnlyInLastRow() {
        assertMatchesOracle(new int[][]{{0, 0, 0, 0}, {0, 0, 1, 1}, {1, 1, 1, 1}});
    }

    @Test
    public void testRowsNeedNotBeSortedByTheirFirstOne() {
        assertMatchesOracle(new int[][]{
                {0, 0, 1, 1, 1},
                {0, 0, 0, 0, 1},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 0}
        });
    }

    @Test
    public void testRectangularWideMatrix() {
        assertMatchesOracle(new int[][]{
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1, 1, 1}
        });
    }

    @Test
    public void testRectangularTallMatrix() {
        assertMatchesOracle(new int[][]{
                {0, 0}, {0, 1}, {0, 0}, {1, 1}, {0, 1}, {0, 0}, {1, 1}, {0, 1}
        });
    }

    @Test
    public void testEveryTransitionInAStaircase() {
        int[][] matrix = new int[6][6];
        for (int row = 0; row < matrix.length; row++) {
            for (int column = row; column < matrix[row].length; column++) {
                matrix[row][column] = 1;
            }
        }
        assertMatchesOracle(matrix);
    }

    @Test
    public void testReverseStaircaseWithLastRowAllZeros() {
        int[][] matrix = new int[6][6];
        for (int row = 0; row < matrix.length - 1; row++) {
            for (int column = matrix[row].length - row - 1; column < matrix[row].length; column++) {
                matrix[row][column] = 1;
            }
        }
        assertMatchesOracle(matrix);
    }

    @Test
    public void testAllZeroMaximumMatrixReturnsNegative() {
        assertMatchesOracle(fillRows(100, 100, 100));
    }

    @Test
    public void testAllOneMaximumMatrixReturnsFirstColumn() {
        assertMatchesOracle(fillRows(100, 100, 0));
    }

    @Test
    public void testMaximumMatrixWithAnswerAtLastColumn() {
        assertMatchesOracle(fillRows(100, 100, 99));
    }

    @Test
    public void testMaximumMatrixWithAnswerAtFirstColumnOnLastRow() {
        int[][] matrix = fillRows(100, 100, 100);
        for (int column = 0; column < matrix[99].length; column++) {
            matrix[99][column] = 1;
        }
        assertMatchesOracle(matrix);
    }

    @Test
    public void testMaximumMatrixUsesAtMostOneThousandGetCalls() {
        int[][] matrix = new int[100][100];
        for (int row = 0; row < matrix.length; row++) {
            int firstOne = (row * 37) % (matrix[row].length + 1);
            fillFromFirstOne(matrix[row], firstOne);
        }

        TrackingBinaryMatrix api = new TrackingBinaryMatrix(matrix);
        assertEquals(leftmostColumnByDirectScan(matrix), solution.leftMostColumnWithOne(api));
        assertTrue(api.getCalls <= 1000, "get calls=" + api.getCalls);
    }

    @Test
    public void testExhaustiveSmallRowSortedMatricesAgainstOracle() {
        int rows = 3;
        int columns = 4;
        // A row is represented by its first-one index; columns means an all-zero row.
        int rowChoices = columns + 1;
        for (int firstRow = 0; firstRow < rowChoices; firstRow++) {
            for (int firstMiddle = 0; firstMiddle < rowChoices; firstMiddle++) {
                for (int firstLast = 0; firstLast < rowChoices; firstLast++) {
                    int[][] matrix = new int[rows][columns];
                    fillFromFirstOne(matrix[0], firstRow);
                    fillFromFirstOne(matrix[1], firstMiddle);
                    fillFromFirstOne(matrix[2], firstLast);
                    assertMatchesOracle(matrix);
                }
            }
        }
    }

    @Test
    public void testDeterministicGeneratedRectanglesAgainstOracle() {
        for (int caseNumber = 0; caseNumber < 100; caseNumber++) {
            int rows = 1 + (caseNumber * 17 % 12);
            int columns = 1 + (caseNumber * 23 % 13);
            int[][] matrix = new int[rows][columns];
            for (int row = 0; row < rows; row++) {
                int firstOne = (caseNumber * 11 + row * 7) % (columns + 1);
                fillFromFirstOne(matrix[row], firstOne);
            }
            assertMatchesOracle(matrix);
        }
    }

    @Test
    public void testBackingArrayIsNotMutated() {
        int[][] matrix = {{0, 0, 1}, {0, 1, 1}, {1, 1, 1}};
        int[][] original = copy(matrix);

        assertMatchesOracle(matrix);

        for (int row = 0; row < matrix.length; row++) {
            assertArrayEquals(original[row], matrix[row]);
        }
    }

    @Test
    public void testRepeatedCallsOnOneSolutionInstanceAreIndependent() {
        assertMatchesOracle(new int[][]{{0, 0, 1}, {0, 1, 1}});
        assertMatchesOracle(new int[][]{{0, 0, 0}, {0, 0, 0}});
        assertMatchesOracle(new int[][]{{1, 1}, {0, 1}});
    }

    @Test
    public void testDimensionsAreReadFromTheApi() {
        int[][] matrix = {{0, 0, 0, 1}, {0, 0, 1, 1}};
        TrackingBinaryMatrix api = new TrackingBinaryMatrix(matrix);

        assertEquals(2, solution.leftMostColumnWithOne(api));
        assertEquals(List.of(2, 4), api.dimensions());
    }

    private void assertMatchesOracle(int[][] matrix) {
        TrackingBinaryMatrix api = new TrackingBinaryMatrix(matrix);
        int expected = leftmostColumnByDirectScan(matrix);

        assertEquals(expected, solution.leftMostColumnWithOne(api));
        assertTrue(api.getCalls <= 1000, "get calls=" + api.getCalls);
    }

    private static int leftmostColumnByDirectScan(int[][] matrix) {
        for (int column = 0; column < matrix[0].length; column++) {
            for (int[] row : matrix) {
                if (row[column] == 1) {
                    return column;
                }
            }
        }
        return -1;
    }

    private static void fillFromFirstOne(int[] row, int firstOne) {
        for (int column = firstOne; column < row.length; column++) {
            row[column] = 1;
        }
    }

    private static int[][] fillRows(int rows, int columns, int firstOne) {
        int[][] matrix = new int[rows][columns];
        for (int[] row : matrix) {
            fillFromFirstOne(row, firstOne);
        }
        return matrix;
    }

    private static int[][] copy(int[][] matrix) {
        int[][] result = new int[matrix.length][];
        for (int row = 0; row < matrix.length; row++) {
            result[row] = matrix[row].clone();
        }
        return result;
    }

    private static final class TrackingBinaryMatrix implements BinaryMatrix {
        private final int[][] matrix;
        private int getCalls;

        private TrackingBinaryMatrix(int[][] matrix) {
            this.matrix = matrix;
        }

        @Override
        public int get(int row, int column) {
            getCalls++;
            return matrix[row][column];
        }

        @Override
        public List<Integer> dimensions() {
            return List.of(matrix.length, matrix[0].length);
        }
    }
}

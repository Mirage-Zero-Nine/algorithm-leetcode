package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Tests for the flattened-order binary search used by {@link SearchMatrix_74}.
 *
 * <p>The expected result is calculated by a direct scan in the helpers rather than by a second
 * binary-search implementation, so the tests can detect a shared boundary mistake.</p>
 */
public class SearchMatrix_74Test {

    private final SearchMatrix_74 solution = new SearchMatrix_74();

    @Test
    public void officialExampleFound() {
        int[][] matrix = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };

        assertTrue(solution.searchMatrix(matrix, 3));
    }

    @Test
    public void officialExampleMissing() {
        int[][] matrix = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };

        assertFalse(solution.searchMatrix(matrix, 13));
    }

    @Test
    public void singleElementFound() {
        assertTrue(solution.searchMatrix(new int[][]{{42}}, 42));
    }

    @Test
    public void singleElementMissingOnBothSides() {
        int[][] matrix = {{42}};

        assertFalse(solution.searchMatrix(matrix, 41));
        assertFalse(solution.searchMatrix(matrix, 43));
    }

    @Test
    public void oneRowSearchesEveryPositionAndGaps() {
        int[][] matrix = {{-8, -3, 0, 4, 9, 15}};

        assertMatchesOracle(matrix, -8, -3, 0, 4, 9, 15, -9, -7, -1, 5, 14, 16);
    }

    @Test
    public void oneColumnSearchesEveryPositionAndGaps() {
        int[][] matrix = {{-10}, {-4}, {-1}, {6}, {12}, {25}};

        assertMatchesOracle(matrix, -10, -4, -1, 6, 12, 25, -11, -9, -3, 0, 7, 24, 26);
    }

    @Test
    public void rowBoundaryValuesAreFound() {
        int[][] matrix = {
            {1, 3, 5},
            {8, 10, 12},
            {20, 25, 30}
        };

        assertMatchesOracle(matrix, 1, 5, 8, 12, 20, 30);
    }

    @Test
    public void valuesBetweenRowsAreMissing() {
        int[][] matrix = {
            {1, 3, 5},
            {8, 10, 12},
            {20, 25, 30}
        };

        assertMatchesOracle(matrix, 6, 7, 9, 11, 13, 19, 21, 24, 26);
    }

    @Test
    public void targetsOutsideTheFlattenedRangeAreMissing() {
        int[][] matrix = {{5, 6}, {10, 11}, {20, 21}};

        assertFalse(solution.searchMatrix(matrix, 4));
        assertFalse(solution.searchMatrix(matrix, 22));
        assertFalse(solution.searchMatrix(matrix, Integer.MIN_VALUE));
        assertFalse(solution.searchMatrix(matrix, Integer.MAX_VALUE));
    }

    @Test
    public void duplicateValuesWithinRowsRemainSearchable() {
        int[][] matrix = {
            {-5, -5, -3},
            {-1, 0, 0},
            {4, 8, 8}
        };

        assertMatchesOracle(matrix, -5, -3, -1, 0, 4, 8, -4, 1, 7, 9);
    }

    @Test
    public void signedAndJavaIntegerBoundaryValues() {
        int[][] matrix = {
            {Integer.MIN_VALUE, -10_000, -1},
            {0, 1, Integer.MAX_VALUE}
        };

        assertMatchesOracle(
            matrix,
            Integer.MIN_VALUE,
            -10_000,
            -1,
            0,
            1,
            Integer.MAX_VALUE,
            Integer.MIN_VALUE + 1,
            -9_999,
            -2,
            2,
            Integer.MAX_VALUE - 1);
    }

    @Test
    public void officialValueBoundaries() {
        int[][] matrix = {
            {-10_000, -9_999, -5_000},
            {0, 9_999, 10_000}
        };

        assertMatchesOracle(matrix, -10_000, -9_999, -5_000, 0, 9_999, 10_000, -10_001, 10_001);
    }

    @Test
    public void emptyMatrixIsMissing() {
        assertFalse(solution.searchMatrix(new int[][]{}, 0));
        assertFalse(solution.searchMatrix(new int[][]{}, Integer.MAX_VALUE));
    }

    @Test
    public void emptyRowIsMissingAsDocumentedByImplementation() {
        assertFalse(solution.searchMatrix(new int[][]{{}}, 0));
        assertFalse(solution.searchMatrix(new int[][]{{}}, Integer.MIN_VALUE));
    }

    @Test
    public void rectangularMatricesUseTheActualColumnCount() {
        int[][] wide = {{1, 2, 3, 4, 5, 6}, {10, 11, 12, 13, 14, 15}};
        int[][] tall = {{1, 2}, {5, 6}, {10, 11}, {20, 21}, {30, 31}, {40, 41}};

        assertMatchesOracle(wide, 1, 6, 10, 15, 7, 9, 16);
        assertMatchesOracle(tall, 1, 2, 5, 21, 41, 0, 3, 19, 42);
    }

    @Test
    public void exhaustiveSmallFlattenedOrderSweep() {
        int[][] matrix = {
            {-4, -3, -1, 0},
            {2, 3, 5, 8},
            {10, 13, 21, 34}
        };

        for (int target = -8; target <= 38; target++) {
            assertSearchMatchesOracle(matrix, target);
        }
    }

    @Test
    public void deterministicSeededMatricesMatchDirectScanOracle() {
        Random random = new Random(0x74B1A2L);

        for (int caseIndex = 0; caseIndex < 120; caseIndex++) {
            int rows = 1 + random.nextInt(10);
            int columns = 1 + random.nextInt(10);
            int[][] matrix = increasingMatrix(random, rows, columns);

            for (int value : flatten(matrix)) {
                assertSearchMatchesOracle(matrix, value);
            }
            for (int targetIndex = 0; targetIndex < 20; targetIndex++) {
                assertSearchMatchesOracle(matrix, random.nextInt(220) - 110);
            }
        }
    }

    @Test
    public void maximumOfficialMatrixBoundary() {
        int[][] matrix = new int[100][100];
        int value = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                matrix[row][column] = value++;
            }
        }

        assertTrue(solution.searchMatrix(matrix, 0));
        assertTrue(solution.searchMatrix(matrix, 9_999));
        assertTrue(solution.searchMatrix(matrix, 5_050));
        assertFalse(solution.searchMatrix(matrix, -1));
        assertFalse(solution.searchMatrix(matrix, 10_000));
    }

    @Test
    public void everyElementInMaximumOfficialMatrixIsFound() {
        int[][] matrix = new int[100][100];
        int value = -10_000;
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                matrix[row][column] = value++;
            }
        }

        for (int target : flatten(matrix)) {
            assertTrue(solution.searchMatrix(matrix, target), "target=" + target);
        }
    }

    @Test
    public void inputMatrixIsNotMutated() {
        int[][] matrix = {
            {-7, -2, 1},
            {5, 8, 13}
        };
        int[][] before = deepCopy(matrix);

        assertTrue(solution.searchMatrix(matrix, 8));
        assertFalse(solution.searchMatrix(matrix, 9));
        assertArrayEquals(before, matrix);
    }

    @Test
    public void repeatedCallsDoNotLeakPreviousTargetState() {
        int[][] first = {{1, 4}, {8, 12}};
        int[][] second = {{-100, -50}, {0, 50}};

        assertTrue(solution.searchMatrix(first, 4));
        assertFalse(solution.searchMatrix(first, 5));
        assertTrue(solution.searchMatrix(second, -50));
        assertFalse(solution.searchMatrix(second, 4));
        assertTrue(solution.searchMatrix(first, 12));
    }

    @Test
    public void searchesDoNotDependOnTheTargetCallOrder() {
        int[][] matrix = {
            {-20, -10, -1},
            {3, 7, 11},
            {20, 40, 80}
        };
        int[] targets = {80, -20, 12, 3, -21, 40, 81, 7, 0, 11};
        boolean[] expected = {true, true, false, true, false, true, false, true, false, true};

        for (int index = 0; index < targets.length; index++) {
            assertTrue(expected[index] == solution.searchMatrix(matrix, targets[index]),
                "target=" + targets[index]);
        }
    }

    @Test
    public void narrowRowGapsAndLargeJumpsAreHandled() {
        int[][] matrix = {
            {-1_000_000, -999_999, -999_998},
            {0, 1, 2},
            {1_000_000, 1_000_001, 1_000_002}
        };

        assertMatchesOracle(
            matrix,
            -1_000_000,
            -999_999,
            -999_997,
            -1,
            0,
            2,
            3,
            999_999,
            1_000_000,
            1_000_002,
            Integer.MIN_VALUE,
            Integer.MAX_VALUE);
    }

    private void assertMatchesOracle(int[][] matrix, int... targets) {
        for (int target : targets) {
            assertSearchMatchesOracle(matrix, target);
        }
    }

    private void assertSearchMatchesOracle(int[][] matrix, int target) {
        boolean expected = containsByDirectScan(matrix, target);
        assertTrue(expected == solution.searchMatrix(matrix, target), "target=" + target);
    }

    private boolean containsByDirectScan(int[][] matrix, int target) {
        for (int[] row : matrix) {
            for (int value : row) {
                if (value == target) {
                    return true;
                }
            }
        }
        return false;
    }

    private int[][] increasingMatrix(Random random, int rows, int columns) {
        int[][] matrix = new int[rows][columns];
        int value = -100;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                value += 1 + random.nextInt(4);
                matrix[row][column] = value;
            }
        }
        return matrix;
    }

    private int[] flatten(int[][] matrix) {
        return Arrays.stream(matrix).flatMapToInt(Arrays::stream).toArray();
    }

    private int[][] deepCopy(int[][] matrix) {
        int[][] copy = new int[matrix.length][];
        for (int row = 0; row < matrix.length; row++) {
            copy[row] = matrix[row].clone();
        }
        return copy;
    }
}

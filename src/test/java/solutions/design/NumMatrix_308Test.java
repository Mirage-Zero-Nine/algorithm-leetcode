package solutions.design;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * Stateful contract tests for {@link NumMatrix_308} (LeetCode 308).
 *
 * <p>Each expected region sum is calculated independently by scanning a mutable oracle matrix.
 * This keeps the tests useful even if an optimized implementation and a second implementation
 * share the same defect. Every test uses a fresh matrix and exercises the 2D segment tree through
 * update/query sequences rather than only isolated examples.</p>
 */
public class NumMatrix_308Test {

    @Test
    public void officialExample() {
        int[][] values = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        NumMatrix_308 matrix = new NumMatrix_308(copy(values));

        assertEquals(8, matrix.sumRegion(2, 1, 4, 3));
        matrix.update(3, 2, 2);
        assertEquals(10, matrix.sumRegion(2, 1, 4, 3));
    }

    @Test
    public void singletonCanBeReadAndUpdated() {
        int[][] values = {{42}};
        NumMatrix_308 matrix = new NumMatrix_308(copy(values));

        assertEquals(42, matrix.sumRegion(0, 0, 0, 0));
        matrix.update(0, 0, -7);
        values[0][0] = -7;
        assertEquals(-7, matrix.sumRegion(0, 0, 0, 0));
        assertEquals(-7, naiveSum(values, 0, 0, 0, 0));
    }

    @Test
    public void emptyAndNullInputsUseDocumentedImplementationGuard() {
        NumMatrix_308 nullMatrix = new NumMatrix_308(null);
        NumMatrix_308 emptyMatrix = new NumMatrix_308(new int[0][]);
        NumMatrix_308 zeroWidthMatrix = new NumMatrix_308(new int[][]{{}});

        assertEquals(0, nullMatrix.sumRegion(0, 0, 0, 0));
        assertEquals(0, emptyMatrix.sumRegion(0, 0, 0, 0));
        assertEquals(0, zeroWidthMatrix.sumRegion(0, 0, 0, 0));
        nullMatrix.update(0, 0, 10);
        emptyMatrix.update(0, 0, 10);
        zeroWidthMatrix.update(0, 0, 10);
        assertEquals(0, nullMatrix.sumRegion(0, 0, 0, 0));
    }

    @Test
    public void fullRectangleIncludesEveryCell() {
        int[][] values = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        NumMatrix_308 matrix = new NumMatrix_308(copy(values));

        assertEquals(45, matrix.sumRegion(0, 0, 2, 2));
        matrix.update(1, 1, 10);
        values[1][1] = 10;
        assertEquals(50, matrix.sumRegion(0, 0, 2, 2));
        assertEquals(naiveSum(values, 0, 0, 2, 2), matrix.sumRegion(0, 0, 2, 2));
    }

    @Test
    public void everySingletonRegionIsAddressable() {
        int[][] values = {{8, -1, 0, 14}, {-7, 3, 6, 2}, {5, 9, -4, 11}};
        NumMatrix_308 matrix = new NumMatrix_308(copy(values));

        for (int row = 0; row < values.length; row++) {
            for (int col = 0; col < values[0].length; col++) {
                assertEquals(values[row][col], matrix.sumRegion(row, col, row, col),
                        "cell (" + row + "," + col + ")");
            }
        }
    }

    @Test
    public void singleRowSupportsSubrangesAndUpdates() {
        int[][] values = {{1, 2, 3, 4, 5, 6, 7}};
        NumMatrix_308 matrix = new NumMatrix_308(copy(values));

        assertEquals(15, matrix.sumRegion(0, 0, 0, 4));
        assertEquals(18, matrix.sumRegion(0, 2, 0, 5));
        matrix.update(0, 2, -10);
        values[0][2] = -10;
        assertEquals(naiveSum(values, 0, 1, 0, 5), matrix.sumRegion(0, 1, 0, 5));
    }

    @Test
    public void singleColumnSupportsSubrangesAndUpdates() {
        int[][] values = {{1}, {2}, {3}, {4}, {5}, {6}};
        NumMatrix_308 matrix = new NumMatrix_308(copy(values));

        assertEquals(21, matrix.sumRegion(0, 0, 5, 0));
        assertEquals(9, matrix.sumRegion(1, 0, 3, 0));
        matrix.update(2, 0, -10);
        values[2][0] = -10;
        assertEquals(naiveSum(values, 1, 0, 4, 0), matrix.sumRegion(1, 0, 4, 0));
    }

    @Test
    public void interiorRectangleUsesOnlyItsCells() {
        int[][] values = {
                {10, 10, 10, 10, 10},
                {10, 1, 2, 3, 10},
                {10, 4, 5, 6, 10},
                {10, 7, 8, 9, 10},
                {10, 10, 10, 10, 10}
        };
        NumMatrix_308 matrix = new NumMatrix_308(copy(values));

        assertEquals(45, matrix.sumRegion(1, 1, 3, 3));
        matrix.update(2, 2, 100);
        values[2][2] = 100;
        assertEquals(140, matrix.sumRegion(1, 1, 3, 3));
        assertEquals(10, matrix.sumRegion(0, 0, 0, 0));
    }

    @Test
    public void boundaryRowsAndColumnsRemainIndependent() {
        int[][] values = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        NumMatrix_308 matrix = new NumMatrix_308(copy(values));

        assertEquals(14, matrix.sumRegion(0, 0, 1, 1));
        assertEquals(38, matrix.sumRegion(1, 2, 2, 3));
        assertEquals(15, matrix.sumRegion(0, 0, 2, 0));
        assertEquals(24, matrix.sumRegion(0, 3, 2, 3));
        assertEquals(naiveSum(values, 0, 1, 2, 2), matrix.sumRegion(0, 1, 2, 2));
    }

    @Test
    public void repeatedUpdatesToOneCellUseTheLatestValue() {
        int[][] values = {{5, 5, 5}, {5, 5, 5}};
        NumMatrix_308 matrix = new NumMatrix_308(copy(values));

        matrix.update(1, 1, 10);
        values[1][1] = 10;
        assertEquals(35, matrix.sumRegion(0, 0, 1, 2));
        matrix.update(1, 1, -5);
        values[1][1] = -5;
        assertEquals(20, matrix.sumRegion(0, 0, 1, 2));
        matrix.update(1, 1, -5);
        assertEquals(-5, matrix.sumRegion(1, 1, 1, 1));
        assertEquals(naiveSum(values, 0, 0, 1, 2), matrix.sumRegion(0, 0, 1, 2));
    }

    @Test
    public void updateToExistingValueIsAStatePreservingNoOp() {
        int[][] values = {{-4, 0, 12}, {3, 7, -8}};
        NumMatrix_308 matrix = new NumMatrix_308(copy(values));

        matrix.update(0, 2, 12);
        assertEquals(naiveSum(values, 0, 0, 1, 2), matrix.sumRegion(0, 0, 1, 2));
        matrix.update(1, 0, 3);
        assertEquals(naiveSum(values, 0, 1, 1, 2), matrix.sumRegion(0, 1, 1, 2));
    }

    @Test
    public void signedZeroAndDuplicateValuesAreHandled() {
        int[][] values = {{-1, 0, -1, 0}, {2, -2, 2, -2}, {0, 0, 0, 0}};
        NumMatrix_308 matrix = new NumMatrix_308(copy(values));

        assertEquals(-2, matrix.sumRegion(0, 0, 0, 3));
        assertEquals(0, matrix.sumRegion(1, 0, 1, 3));
        matrix.update(2, 1, -1000);
        values[2][1] = -1000;
        assertEquals(-1000, matrix.sumRegion(2, 0, 2, 3));
        assertEquals(naiveSum(values, 0, 0, 2, 3), matrix.sumRegion(0, 0, 2, 3));
    }

    @Test
    public void updateAtEveryPositionMaintainsFullAndPartialRanges() {
        int[][] values = {{2, 4, 6}, {8, 10, 12}, {14, 16, 18}};
        NumMatrix_308 matrix = new NumMatrix_308(copy(values));

        for (int row = 0; row < values.length; row++) {
            for (int col = 0; col < values[0].length; col++) {
                int value = (row + col) % 2 == 0 ? -(row * 3 + col + 1) : row * 10 + col;
                values[row][col] = value;
                matrix.update(row, col, value);
                assertEquals(naiveSum(values, 0, 0, 2, 2), matrix.sumRegion(0, 0, 2, 2));
                assertEquals(naiveSum(values, row, 0, row, 2), matrix.sumRegion(row, 0, row, 2));
            }
        }
    }

    @Test
    public void exhaustiveRectanglesMatchIndependentOracleBeforeAndAfterUpdates() {
        int[][] values = {
                {-3, 1, 4, -1, 5},
                {-9, 2, 6, 5, 3},
                {5, 8, 9, 7, 9},
                {3, 2, 3, 8, 4}
        };
        NumMatrix_308 matrix = new NumMatrix_308(copy(values));
        assertEveryRectangleMatches(values, matrix);

        values[0][4] = -1000;
        values[3][0] = 1000;
        matrix.update(0, 4, -1000);
        matrix.update(3, 0, 1000);
        assertEveryRectangleMatches(values, matrix);
    }

    @Test
    public void oddDimensionsSplitAcrossAllQuadrants() {
        int[][] values = {
                {0, 1, 2, 3, 4, 5, 6},
                {7, 8, 9, 10, 11, 12, 13},
                {14, 15, 16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25, 26, 27},
                {28, 29, 30, 31, 32, 33, 34}
        };
        NumMatrix_308 matrix = new NumMatrix_308(copy(values));

        assertEveryRectangleMatches(values, matrix);
        matrix.update(2, 3, -1000);
        values[2][3] = -1000;
        matrix.update(4, 6, 1000);
        values[4][6] = 1000;
        assertEveryRectangleMatches(values, matrix);
    }

    @Test
    public void repeatedQueriesDoNotChangeState() {
        int[][] values = {{6, -2, 9, 4}, {1, 3, -7, 8}};
        NumMatrix_308 matrix = new NumMatrix_308(copy(values));

        int expected = naiveSum(values, 0, 1, 1, 2);
        assertEquals(expected, matrix.sumRegion(0, 1, 1, 2));
        assertEquals(expected, matrix.sumRegion(0, 1, 1, 2));
        assertEquals(naiveSum(values, 1, 0, 1, 3), matrix.sumRegion(1, 0, 1, 3));
        assertEquals(expected, matrix.sumRegion(0, 1, 1, 2));
    }

    @Test
    public void queriesDoNotMutateTheCallerMatrix() {
        int[][] source = {{3, 1, 4}, {1, 5, 9}};
        int[][] snapshot = copy(source);
        NumMatrix_308 matrix = new NumMatrix_308(source);

        matrix.sumRegion(0, 0, 1, 2);
        matrix.sumRegion(0, 1, 1, 1);
        assertMatrixEquals(snapshot, source);
    }

    @Test
    public void separateInstancesKeepIndependentState() {
        NumMatrix_308 first = new NumMatrix_308(new int[][]{{1, 2}, {3, 4}});
        NumMatrix_308 second = new NumMatrix_308(new int[][]{{10, 20}, {30, 40}});

        first.update(0, 0, 100);
        assertEquals(109, first.sumRegion(0, 0, 1, 1));
        assertEquals(100, first.sumRegion(0, 0, 0, 0));
        assertEquals(100, second.sumRegion(0, 0, 1, 1));
        second.update(1, 1, -10);
        assertEquals(50, second.sumRegion(0, 0, 1, 1));
        assertEquals(109, first.sumRegion(0, 0, 1, 1));
    }

    @Test
    public void cornerUpdatesPropagateToAdjacentAndFullRanges() {
        int[][] values = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        NumMatrix_308 matrix = new NumMatrix_308(copy(values));

        int[][] corners = {{0, 0, -1000}, {0, 3, 1000}, {3, 0, 500}, {3, 3, -500}};
        for (int[] corner : corners) {
            values[corner[0]][corner[1]] = corner[2];
            matrix.update(corner[0], corner[1], corner[2]);
            assertEquals(naiveSum(values, 0, 0, 3, 3), matrix.sumRegion(0, 0, 3, 3));
        }
        assertEquals(naiveSum(values, 0, 0, 1, 1), matrix.sumRegion(0, 0, 1, 1));
        assertEquals(naiveSum(values, 2, 2, 3, 3), matrix.sumRegion(2, 2, 3, 3));
    }

    @Test
    public void seededSmallStatefulSequenceMatchesNaiveOracle() {
        runSeededScenario(308001, 1, 1, 300);
    }

    @Test
    public void seededRectangularStatefulSequenceMatchesNaiveOracle() {
        runSeededScenario(308002, 3, 7, 900);
    }

    @Test
    public void seededNonPowerOfTwoStatefulSequenceMatchesNaiveOracle() {
        runSeededScenario(308003, 9, 11, 1500);
    }

    @Test
    public void seededSignedBoundarySequenceMatchesNaiveOracle() {
        runSeededScenario(308004, 17, 5, 1800);
    }

    @Test
    public void maximumValueBoundsRemainCorrect() {
        int[][] values = new int[2][200];
        for (int col = 0; col < values[0].length; col++) {
            values[0][col] = col % 2 == 0 ? -1000 : 1000;
            values[1][col] = col % 3 == 0 ? 1000 : -1000;
        }
        NumMatrix_308 matrix = new NumMatrix_308(copy(values));

        assertEquals(naiveSum(values, 0, 0, 1, 199), matrix.sumRegion(0, 0, 1, 199));
        matrix.update(0, 199, -1000);
        values[0][199] = -1000;
        matrix.update(1, 0, 1000);
        values[1][0] = 1000;
        assertEquals(naiveSum(values, 0, 0, 0, 199), matrix.sumRegion(0, 0, 0, 199));
        assertEquals(naiveSum(values, 0, 100, 1, 199), matrix.sumRegion(0, 100, 1, 199));
    }

    @Test
    public void maximumDimensionsAndExactOperationBudgetAreSupported() {
        int rows = 200;
        int cols = 200;
        int[][] values = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                values[row][col] = ((row * 31 + col * 17) % 2001) - 1000;
            }
        }
        NumMatrix_308 matrix = new NumMatrix_308(copy(values));

        // 2,500 updates and 2,500 queries make exactly the allowed 5,000 calls.
        for (int operation = 0; operation < 4999; operation++) {
            int row = (operation * 73) % rows;
            int col = (operation * 97) % cols;
            if (operation % 2 == 0) {
                int value = ((operation * 41) % 2001) - 1000;
                values[row][col] = value;
                matrix.update(row, col, value);
            } else {
                int row1 = (operation * 11) % rows;
                int row2 = row1 + ((operation * 7) % (rows - row1));
                int col1 = (operation * 13) % cols;
                int col2 = col1 + ((operation * 5) % (cols - col1));
                assertEquals(naiveSum(values, row1, col1, row2, col2),
                        matrix.sumRegion(row1, col1, row2, col2), "operation " + operation);
            }
        }
        assertEquals(naiveSum(values, 0, 0, rows - 1, cols - 1),
                matrix.sumRegion(0, 0, rows - 1, cols - 1));
    }

    @Test
    public void constructorDoesNotAlterInputValues() {
        int[][] source = {{-1000, 0, 1000}, {7, -8, 9}};
        int[][] snapshot = copy(source);

        new NumMatrix_308(source);

        assertMatrixEquals(snapshot, source);
    }

    private static void runSeededScenario(int seed, int rows, int cols, int operations) {
        Random random = new Random(seed);
        int[][] values = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                values[row][col] = random.nextInt(2001) - 1000;
            }
        }
        NumMatrix_308 matrix = new NumMatrix_308(copy(values));

        for (int operation = 0; operation < operations; operation++) {
            if (random.nextBoolean()) {
                int row = random.nextInt(rows);
                int col = random.nextInt(cols);
                int value = random.nextInt(2001) - 1000;
                values[row][col] = value;
                matrix.update(row, col, value);
            } else {
                int row1 = random.nextInt(rows);
                int row2 = row1 + random.nextInt(rows - row1);
                int col1 = random.nextInt(cols);
                int col2 = col1 + random.nextInt(cols - col1);
                assertEquals(naiveSum(values, row1, col1, row2, col2),
                        matrix.sumRegion(row1, col1, row2, col2),
                        "seed " + seed + ", operation " + operation);
            }
        }
        assertEquals(naiveSum(values, 0, 0, rows - 1, cols - 1),
                matrix.sumRegion(0, 0, rows - 1, cols - 1));
    }

    private static void assertEveryRectangleMatches(int[][] values, NumMatrix_308 matrix) {
        for (int row1 = 0; row1 < values.length; row1++) {
            for (int row2 = row1; row2 < values.length; row2++) {
                for (int col1 = 0; col1 < values[0].length; col1++) {
                    for (int col2 = col1; col2 < values[0].length; col2++) {
                        assertEquals(naiveSum(values, row1, col1, row2, col2),
                                matrix.sumRegion(row1, col1, row2, col2),
                                "rectangle [" + row1 + "," + col1 + "] to ["
                                        + row2 + "," + col2 + "]");
                    }
                }
            }
        }
    }

    private static int naiveSum(int[][] values, int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int row = row1; row <= row2; row++) {
            for (int col = col1; col <= col2; col++) {
                sum += values[row][col];
            }
        }
        return sum;
    }

    private static int[][] copy(int[][] values) {
        if (values == null) {
            return null;
        }
        int[][] result = new int[values.length][];
        for (int row = 0; row < values.length; row++) {
            result[row] = values[row] == null ? null : values[row].clone();
        }
        return result;
    }

    private static void assertMatrixEquals(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row], "row " + row);
        }
    }
}

package solutions.design;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link SubrectangleQueries_1476}.
 */
public class SubrectangleQueries_1476Test {

    @Test
    public void testInitialValues() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(matrix);
        assertEquals(1, sq.getValue(0, 0));
        assertEquals(2, sq.getValue(0, 1));
        assertEquals(3, sq.getValue(0, 2));
        assertEquals(4, sq.getValue(1, 0));
        assertEquals(5, sq.getValue(1, 1));
        assertEquals(6, sq.getValue(1, 2));
        assertEquals(7, sq.getValue(2, 0));
        assertEquals(8, sq.getValue(2, 1));
        assertEquals(9, sq.getValue(2, 2));
    }

    @Test
    public void testSingleUpdate() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(matrix);
        sq.updateSubrectangle(0, 0, 2, 2, 5);
        assertEquals(5, sq.getValue(0, 0));
        assertEquals(5, sq.getValue(1, 1));
        assertEquals(5, sq.getValue(2, 2));
    }

    @Test
    public void testPartialUpdate() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(matrix);
        sq.updateSubrectangle(0, 0, 1, 1, 10);
        assertEquals(10, sq.getValue(0, 0));
        assertEquals(10, sq.getValue(1, 1));
        assertEquals(3, sq.getValue(0, 2));  // not updated
        assertEquals(7, sq.getValue(2, 0));  // not updated
    }

    @Test
    public void testMultipleUpdates() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(matrix);
        sq.updateSubrectangle(0, 0, 1, 1, 10);
        sq.updateSubrectangle(1, 1, 2, 2, 20);
        assertEquals(10, sq.getValue(0, 0));  // first update
        assertEquals(20, sq.getValue(1, 1));  // second update overrides
        assertEquals(20, sq.getValue(2, 2));  // second update
        assertEquals(3, sq.getValue(0, 2));   // not updated
    }

    @Test
    public void testUpdateOverlapping() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(matrix);
        sq.updateSubrectangle(0, 0, 2, 2, 1);
        sq.updateSubrectangle(0, 0, 1, 1, 2);
        assertEquals(2, sq.getValue(0, 0));  // latest update
        assertEquals(1, sq.getValue(2, 2));  // only in first update
    }

    @Test
    public void testSingleCellUpdate() {
        int[][] matrix = {
            {1, 2},
            {3, 4}
        };
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(matrix);
        sq.updateSubrectangle(0, 0, 0, 0, 99);
        assertEquals(99, sq.getValue(0, 0));
        assertEquals(2, sq.getValue(0, 1));
        assertEquals(3, sq.getValue(1, 0));
        assertEquals(4, sq.getValue(1, 1));
    }

    @Test
    public void testRowUpdate() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6}
        };
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(matrix);
        sq.updateSubrectangle(0, 0, 0, 2, 7);
        assertEquals(7, sq.getValue(0, 0));
        assertEquals(7, sq.getValue(0, 1));
        assertEquals(7, sq.getValue(0, 2));
        assertEquals(4, sq.getValue(1, 0));  // not updated
    }

    @Test
    public void testColumnUpdate() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6}
        };
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(matrix);
        sq.updateSubrectangle(0, 1, 1, 1, 8);
        assertEquals(1, sq.getValue(0, 0));  // not updated (col 0)
        assertEquals(8, sq.getValue(0, 1));  // updated
        assertEquals(3, sq.getValue(0, 2));  // not updated (col 2)
        assertEquals(4, sq.getValue(1, 0));  // not updated (col 0)
        assertEquals(8, sq.getValue(1, 1));  // updated
        assertEquals(6, sq.getValue(1, 2));  // not updated (col 2)
    }

    @Test
    public void testGetValueAfterNoUpdate() {
        int[][] matrix = {
            {10, 20},
            {30, 40}
        };
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(matrix);
        assertEquals(10, sq.getValue(0, 0));
        assertEquals(40, sq.getValue(1, 1));
    }

    @Test
    public void testMultipleOverlappingUpdates() {
        int[][] matrix = {
            {1, 2},
            {3, 4}
        };
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(matrix);
        sq.updateSubrectangle(0, 0, 1, 1, 1);
        sq.updateSubrectangle(0, 0, 0, 0, 2);
        sq.updateSubrectangle(1, 1, 1, 1, 3);
        assertEquals(2, sq.getValue(0, 0));
        assertEquals(1, sq.getValue(0, 1));
        assertEquals(1, sq.getValue(1, 0));
        assertEquals(3, sq.getValue(1, 1));
    }

    @Test
    public void testOfficialExampleOneSequence() {
        int[][] initial = {{1, 2, 1}, {4, 3, 4}, {3, 2, 1}, {1, 1, 1}};
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(initial);

        assertEquals(1, sq.getValue(0, 2));
        sq.updateSubrectangle(0, 0, 3, 2, 5);
        assertEquals(5, sq.getValue(0, 2));
        assertEquals(5, sq.getValue(3, 1));
        sq.updateSubrectangle(3, 0, 3, 2, 10);
        assertEquals(10, sq.getValue(3, 1));
        assertEquals(5, sq.getValue(0, 2));
    }

    @Test
    public void testOfficialExampleTwoSequence() {
        int[][] initial = {{1, 1, 1}, {2, 2, 2}, {3, 3, 3}};
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(initial);

        assertEquals(1, sq.getValue(0, 0));
        sq.updateSubrectangle(0, 0, 2, 2, 100);
        assertEquals(100, sq.getValue(0, 0));
        assertEquals(100, sq.getValue(2, 2));
        sq.updateSubrectangle(1, 1, 2, 2, 20);
        assertEquals(20, sq.getValue(2, 2));
        assertEquals(100, sq.getValue(0, 2));
    }

    @Test
    public void testFullUpdateIncludesEveryBoundaryCell() {
        int[][] initial = {{1, 2}, {3, 4}};
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(initial);
        sq.updateSubrectangle(0, 0, 1, 1, 1_000_000_000);

        assertRectangleValues(sq, filled(2, 2, 1_000_000_000));
    }

    @Test
    public void testCornerSubrectanglesLeaveMiddleAndOppositeCornersUntouched() {
        int[][] initial = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(initial);
        sq.updateSubrectangle(0, 0, 1, 1, 50);
        sq.updateSubrectangle(2, 2, 3, 3, 60);

        int[][] expected = {
            {50, 50, 3, 4},
            {50, 50, 7, 8},
            {9, 10, 60, 60},
            {13, 14, 60, 60}
        };
    }

    @Test
    public void testDisjointUpdatesRemainIndependent() {
        int[][] initial = {{1, 2, 3, 4}, {5, 6, 7, 8}};
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(initial);
        sq.updateSubrectangle(0, 0, 0, 0, 11);
        sq.updateSubrectangle(1, 3, 1, 3, 88);

        int[][] expected = {{11, 2, 3, 4}, {5, 6, 7, 88}};
        assertRectangleValues(sq, expected);
    }

    @Test
    public void testLaterNarrowUpdateWinsOnlyInsideItsRange() {
        int[][] initial = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(initial);
        sq.updateSubrectangle(0, 0, 2, 2, 10);
        sq.updateSubrectangle(1, 1, 1, 1, 20);

        int[][] expected = {{10, 10, 10}, {10, 20, 10}, {10, 10, 10}};
        assertRectangleValues(sq, expected);
    }

    @Test
    public void testLaterWideUpdateWinsOverEarlierNarrowUpdate() {
        int[][] initial = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(initial);
        sq.updateSubrectangle(1, 1, 1, 1, 20);
        sq.updateSubrectangle(0, 0, 2, 2, 30);

        assertRectangleValues(sq, filled(3, 3, 30));
    }

    @Test
    public void testRepeatedUpdatesToSameCellUseMostRecentValue() {
        int[][] initial = {{7}};
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(initial);
        sq.updateSubrectangle(0, 0, 0, 0, 8);
        sq.updateSubrectangle(0, 0, 0, 0, 9);
        sq.updateSubrectangle(0, 0, 0, 0, 10);

        assertEquals(10, sq.getValue(0, 0));
    }

    @Test
    public void testRowAndColumnUpdatesIntersectAtLatestValue() {
        int[][] initial = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(initial);
        sq.updateSubrectangle(1, 0, 1, 2, 40);
        sq.updateSubrectangle(0, 1, 2, 1, 50);

        int[][] expected = {{1, 50, 3}, {40, 50, 40}, {7, 50, 9}};
        assertRectangleValues(sq, expected);
    }

    @Test
    public void testZeroAndNegativeValuesArePreservedByImplementation() {
        int[][] initial = {{-3, 0}, {7, 11}};
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(initial);
        sq.updateSubrectangle(0, 0, 0, 1, -100);
        sq.updateSubrectangle(1, 0, 1, 1, 0);

        int[][] expected = {{-100, -100}, {0, 0}};
        assertRectangleValues(sq, expected);
    }

    @Test
    public void testJavaIntegerBoundariesAreNotTruncated() {
        int[][] initial = {{Integer.MIN_VALUE, Integer.MAX_VALUE}};
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(initial);
        sq.updateSubrectangle(0, 0, 0, 0, Integer.MAX_VALUE);
        sq.updateSubrectangle(0, 1, 0, 1, Integer.MIN_VALUE);

        assertEquals(Integer.MAX_VALUE, sq.getValue(0, 0));
        assertEquals(Integer.MIN_VALUE, sq.getValue(0, 1));
    }

    @Test
    public void testOneRowSupportsEveryRectangleWidth() {
        int[][] initial = {{1, 2, 3, 4, 5}};
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(initial);
        sq.updateSubrectangle(0, 1, 0, 3, 20);
        sq.updateSubrectangle(0, 2, 0, 2, 30);

        assertRectangleValues(sq, new int[][]{{1, 20, 30, 20, 5}});
    }

    @Test
    public void testOneColumnSupportsEveryRectangleHeight() {
        int[][] initial = {{1}, {2}, {3}, {4}, {5}};
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(initial);
        sq.updateSubrectangle(1, 0, 3, 0, 20);
        sq.updateSubrectangle(2, 0, 4, 0, 30);

        assertRectangleValues(sq, new int[][]{{1}, {20}, {30}, {30}, {30}});
    }

    @Test
    public void testQueriesAfterFullThenPartialUpdateKeepUntouchedCells() {
        int[][] initial = {{1, 2, 3}, {4, 5, 6}};
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(initial);
        sq.updateSubrectangle(0, 0, 1, 2, 70);
        sq.updateSubrectangle(0, 1, 0, 1, 80);

        int[][] expected = {{70, 80, 70}, {70, 70, 70}};
        assertRectangleValues(sq, expected);
    }

    @Test
    public void testConstructorSnapshotsEveryInputRow() {
        int[][] initial = {{1, 2}, {3, 4}};
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(initial);
        initial[0][0] = 99;
        initial[1] = new int[]{88, 77};

        assertRectangleValues(sq, new int[][]{{1, 2}, {3, 4}});
    }

    @Test
    public void testUpdatesDoNotMutateCallerMatrix() {
        int[][] initial = {{1, 2}, {3, 4}};
        int[][] before = deepCopy(initial);
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(initial);
        sq.updateSubrectangle(0, 0, 1, 1, 99);

        assertMatrixEquals(before, initial);
        assertRectangleValues(sq, new int[][]{{99, 99}, {99, 99}});
    }

    @Test
    public void testSeparateInstancesHaveIndependentUpdateHistory() {
        SubrectangleQueries_1476 first = new SubrectangleQueries_1476(new int[][]{{1, 2}});
        SubrectangleQueries_1476 second = new SubrectangleQueries_1476(new int[][]{{3, 4}});
        first.updateSubrectangle(0, 0, 0, 1, 10);

        assertEquals(10, first.getValue(0, 0));
        assertEquals(3, second.getValue(0, 0));
        assertEquals(4, second.getValue(0, 1));
    }

    @Test
    public void testInterleavedGetsDoNotChangeLaterUpdateResults() {
        int[][] initial = {{1, 2, 3}, {4, 5, 6}};
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(initial);
        sq.updateSubrectangle(0, 0, 0, 2, 10);
        assertEquals(10, sq.getValue(0, 1));
        assertEquals(4, sq.getValue(1, 0));
        sq.updateSubrectangle(0, 1, 1, 1, 20);

        assertRectangleValues(sq, new int[][]{{10, 20, 10}, {4, 20, 6}});
    }

    @Test
    public void testAllCellsCanBeUpdatedIndividually() {
        int[][] initial = {{1, 1, 1}, {1, 1, 1}};
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(initial);
        int value = 10;
        int[][] expected = deepCopy(initial);
        for (int row = 0; row < expected.length; row++) {
            for (int col = 0; col < expected[row].length; col++) {
                sq.updateSubrectangle(row, col, row, col, value);
                expected[row][col] = value++;
            }
        }

        assertRectangleValues(sq, expected);
    }

    @Test
    public void testMaximumLegalDimensionsAndBoundaryCoordinates() {
        int[][] initial = new int[100][100];
        for (int row = 0; row < initial.length; row++) {
            for (int col = 0; col < initial[row].length; col++) {
                initial[row][col] = row * 100 + col + 1;
            }
        }
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(initial);
        sq.updateSubrectangle(0, 0, 99, 99, 7);
        sq.updateSubrectangle(0, 0, 0, 0, 8);
        sq.updateSubrectangle(99, 99, 99, 99, 9);

        assertEquals(8, sq.getValue(0, 0));
        assertEquals(7, sq.getValue(0, 1));
        assertEquals(7, sq.getValue(50, 50));
        assertEquals(7, sq.getValue(99, 98));
        assertEquals(9, sq.getValue(99, 99));
    }

    @Test
    public void testExactlyFiveHundredOperationsMatchIndependentOracle() {
        int[][] initial = new int[8][9];
        for (int row = 0; row < initial.length; row++) {
            for (int col = 0; col < initial[row].length; col++) {
                initial[row][col] = row * initial[row].length + col + 1;
            }
        }
        int[][] expected = deepCopy(initial);
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(initial);
        Random random = new Random(1_476_500L);
        for (int operation = 0; operation < 500; operation++) {
            if (operation % 3 == 0) {
                int row1 = random.nextInt(initial.length);
                int row2 = row1 + random.nextInt(initial.length - row1);
                int col1 = random.nextInt(initial[0].length);
                int col2 = col1 + random.nextInt(initial[0].length - col1);
                int value = 1 + random.nextInt(1_000_000_000);
                sq.updateSubrectangle(row1, col1, row2, col2, value);
                applyUpdate(expected, row1, col1, row2, col2, value);
            } else {
                int row = random.nextInt(initial.length);
                int col = random.nextInt(initial[0].length);
                assertEquals(expected[row][col], sq.getValue(row, col));
            }
        }
    }

    @Test
    public void testSeededStatefulOracleCoversOverlapsAndAllValueBoundaries() {
        int[][] initial = {
            {1, 10, 100, 1_000},
            {10_000, 100_000, 1_000_000, 10_000_000},
            {100_000_000, 500_000_000, 999_999_999, 1_000_000_000}
        };
        int[][] expected = deepCopy(initial);
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(initial);
        int[][] updates = {
            {0, 0, 2, 3, 1_000_000_000},
            {1, 1, 2, 2, 1},
            {0, 2, 1, 3, 500_000_000},
            {2, 0, 2, 1, 2},
            {0, 0, 0, 3, 1}
        };
        for (int[] update : updates) {
            sq.updateSubrectangle(update[0], update[1], update[2], update[3], update[4]);
            applyUpdate(expected, update[0], update[1], update[2], update[3], update[4]);
            assertRectangleValues(sq, expected);
        }
    }

    private static void applyUpdate(int[][] matrix, int row1, int col1, int row2, int col2, int value) {
        for (int row = row1; row <= row2; row++) {
            for (int col = col1; col <= col2; col++) {
                matrix[row][col] = value;
            }
        }
    }

    private static int[][] filled(int rows, int columns, int value) {
        int[][] matrix = new int[rows][columns];
        for (int[] row : matrix) {
            java.util.Arrays.fill(row, value);
        }
        return matrix;
    }

    private static int[][] deepCopy(int[][] matrix) {
        int[][] copy = new int[matrix.length][];
        for (int row = 0; row < matrix.length; row++) {
            copy[row] = matrix[row].clone();
        }
        return copy;
    }

    private static void assertRectangleValues(SubrectangleQueries_1476 actual, int[][] expected) {
        for (int row = 0; row < expected.length; row++) {
            for (int col = 0; col < expected[row].length; col++) {
                assertEquals(expected[row][col], actual.getValue(row, col),
                    "unexpected value at (" + row + "," + col + ")");
            }
        }
    }

    private static void assertMatrixEquals(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row]);
        }
    }
}

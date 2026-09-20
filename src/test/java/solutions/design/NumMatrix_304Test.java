package solutions.design;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * Contract tests for LeetCode 304's immutable two-dimensional range sum.
 *
 * <p>Expected values are calculated independently by a direct rectangle scan
 * or by a {@code long} prefix oracle. The latter keeps the expected arithmetic
 * separate from the implementation and makes the legal maximum totals
 * explicit.</p>
 */
public class NumMatrix_304Test {

    @Test
    public void testOfficialExample() {
        NumMatrix_304 matrix = new NumMatrix_304(new int[][]{
            {3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5},
            {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}
        });

        assertEquals(8, matrix.sumRegion(2, 1, 4, 3));
        assertEquals(11, matrix.sumRegion(1, 1, 2, 2));
        assertEquals(12, matrix.sumRegion(1, 2, 2, 4));
    }

    @Test
    public void testPositiveSingleton() {
        assertEquals(1, new NumMatrix_304(new int[][]{{1}}).sumRegion(0, 0, 0, 0));
    }

    @Test
    public void testNegativeSingleton() {
        assertEquals(-10_000, new NumMatrix_304(new int[][]{{-10_000}}).sumRegion(0, 0, 0, 0));
    }

    @Test
    public void testZeroSingleton() {
        assertEquals(0, new NumMatrix_304(new int[][]{{0}}).sumRegion(0, 0, 0, 0));
    }

    @Test
    public void testSingleRow() {
        int[][] values = {{1, 2, 3, 4, 5}};
        NumMatrix_304 matrix = new NumMatrix_304(values);

        assertEquals(15, matrix.sumRegion(0, 0, 0, 4));
        assertEquals(9, matrix.sumRegion(0, 1, 0, 3));
        assertEquals(5, matrix.sumRegion(0, 4, 0, 4));
    }

    @Test
    public void testSingleColumn() {
        int[][] values = {{1}, {2}, {3}, {4}, {5}};
        NumMatrix_304 matrix = new NumMatrix_304(values);

        assertEquals(15, matrix.sumRegion(0, 0, 4, 0));
        assertEquals(9, matrix.sumRegion(1, 0, 3, 0));
        assertEquals(2, matrix.sumRegion(1, 0, 1, 0));
    }

    @Test
    public void testAllNegativeValues() {
        int[][] values = {{-1, -2}, {-3, -4}};
        NumMatrix_304 matrix = new NumMatrix_304(values);

        assertEquals(-10, matrix.sumRegion(0, 0, 1, 1));
        assertEquals(-1, matrix.sumRegion(0, 0, 0, 0));
        assertEquals(-6, matrix.sumRegion(0, 1, 1, 1));
        assertEquals(-4, matrix.sumRegion(1, 1, 1, 1));
    }

    @Test
    public void testAllZeros() {
        NumMatrix_304 matrix = new NumMatrix_304(new int[][]{
            {0, 0, 0}, {0, 0, 0}, {0, 0, 0}
        });

        assertEquals(0, matrix.sumRegion(0, 0, 2, 2));
        assertEquals(0, matrix.sumRegion(1, 1, 2, 2));
        assertEquals(0, matrix.sumRegion(0, 2, 2, 2));
    }

    @Test
    public void testTopLeftAndBottomRightBoundaries() {
        int[][] values = {
            {3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5},
            {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}
        };
        NumMatrix_304 matrix = new NumMatrix_304(values);

        assertEquals(3, matrix.sumRegion(0, 0, 0, 0));
        assertEquals(14, matrix.sumRegion(0, 0, 1, 1));
        assertEquals(5, matrix.sumRegion(4, 4, 4, 4));
        assertEquals(13, matrix.sumRegion(3, 3, 4, 4));
    }

    @Test
    public void testEntireMatrixAndInteriorRectangle() {
        int[][] values = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        NumMatrix_304 matrix = new NumMatrix_304(values);

        assertEquals(45, matrix.sumRegion(0, 0, 2, 2));
        assertEquals(5, matrix.sumRegion(1, 1, 1, 1));
        assertEquals(39, matrix.sumRegion(1, 0, 2, 2));
        assertEquals(33, matrix.sumRegion(0, 1, 2, 2));
    }

    @Test
    public void testRectangularMatrix() {
        int[][] values = {{2, -1, 4, 8}, {-3, 5, 0, 6}};
        NumMatrix_304 matrix = new NumMatrix_304(values);

        assertEquals(21, matrix.sumRegion(0, 0, 1, 3));
        assertEquals(22, matrix.sumRegion(0, 1, 1, 3));
        assertEquals(11, matrix.sumRegion(1, 1, 1, 3));
        assertEquals(-1, matrix.sumRegion(0, 1, 0, 1));
    }

    @Test
    public void testSignedValuesAndCancellation() {
        int[][] values = {
            {10_000, -10_000, 10_000, -10_000},
            {-10_000, 10_000, -10_000, 10_000},
            {10_000, 10_000, -10_000, -10_000}
        };
        NumMatrix_304 matrix = new NumMatrix_304(values);

        assertEquals(0, matrix.sumRegion(0, 0, 1, 3));
        assertEquals(10_000, matrix.sumRegion(0, 0, 2, 0));
        assertEquals(-20_000, matrix.sumRegion(1, 2, 2, 3));
        assertEquals(-10_000, matrix.sumRegion(0, 2, 2, 2));
    }

    @Test
    public void testContractValueBounds() {
        int[][] values = {
            {-10_000, -9_999, 9_999, 10_000},
            {10_000, 9_999, -9_999, -10_000}
        };
        NumMatrix_304 matrix = new NumMatrix_304(values);

        assertEquals(0, matrix.sumRegion(0, 0, 1, 3));
        assertEquals(-19_999, matrix.sumRegion(0, 0, 0, 1));
        assertEquals(19_999, matrix.sumRegion(1, 0, 1, 1));
        assertEquals(0, matrix.sumRegion(0, 3, 1, 3));
    }

    @Test
    public void testDuplicateValuesAndAdjacentRectangles() {
        int[][] values = {
            {7, 7, -2, 7, -2, 7},
            {0, 7, 7, -2, 7, -2}
        };
        NumMatrix_304 matrix = new NumMatrix_304(values);

        assertEquals(24, matrix.sumRegion(0, 0, 0, 5));
        assertEquals(10, matrix.sumRegion(0, 1, 0, 4));
        assertEquals(14, matrix.sumRegion(1, 0, 1, 2));
        assertEquals(10, matrix.sumRegion(0, 2, 1, 3));
    }

    @Test
    public void testEveryRangeOnSmallMatrixAgainstDirectOracle() {
        int[][] values = {
            {3, -1, 4}, {-1, 5, -9}, {2, 6, 5}
        };
        NumMatrix_304 matrix = new NumMatrix_304(values);

        for (int row1 = 0; row1 < values.length; row1++) {
            for (int col1 = 0; col1 < values[0].length; col1++) {
                for (int row2 = row1; row2 < values.length; row2++) {
                    for (int col2 = col1; col2 < values[0].length; col2++) {
                        assertEquals(directSum(values, row1, col1, row2, col2),
                                matrix.sumRegion(row1, col1, row2, col2),
                                "rectangle [" + row1 + "," + col1 + "] to ["
                                    + row2 + "," + col2 + "]");
                    }
                }
            }
        }
    }

    @Test
    public void testSeededRandomRectanglesAgainstDirectOracle() {
        Random random = new Random(304_2026L);
        int[][] values = new int[17][23];
        for (int row = 0; row < values.length; row++) {
            for (int col = 0; col < values[0].length; col++) {
                values[row][col] = random.nextInt(20_001) - 10_000;
            }
        }
        NumMatrix_304 matrix = new NumMatrix_304(values);

        for (int query = 0; query < 500; query++) {
            int row1 = random.nextInt(values.length);
            int col1 = random.nextInt(values[0].length);
            int row2 = row1 + random.nextInt(values.length - row1);
            int col2 = col1 + random.nextInt(values[0].length - col1);
            assertEquals(directSum(values, row1, col1, row2, col2),
                    matrix.sumRegion(row1, col1, row2, col2),
                    "seeded query " + query);
        }
    }

    @Test
    public void testRepeatedQueriesRemainStable() {
        int[][] values = {
            {3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5},
            {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}
        };
        NumMatrix_304 matrix = new NumMatrix_304(values);

        for (int iteration = 0; iteration < 25; iteration++) {
            assertEquals(8, matrix.sumRegion(2, 1, 4, 3));
            assertEquals(11, matrix.sumRegion(1, 1, 2, 2));
            assertEquals(58, matrix.sumRegion(0, 0, 4, 4));
        }
    }

    @Test
    public void testQueriesCanBeIssuedInAnyOrder() {
        int[][] values = {{10, -20, 30}, {-40, 50, -60}, {70, -80, 90}};
        NumMatrix_304 matrix = new NumMatrix_304(values);

        assertEquals(directSum(values, 1, 1, 2, 2), matrix.sumRegion(1, 1, 2, 2));
        assertEquals(directSum(values, 0, 0, 0, 0), matrix.sumRegion(0, 0, 0, 0));
        assertEquals(directSum(values, 0, 1, 2, 2), matrix.sumRegion(0, 1, 2, 2));
        assertEquals(directSum(values, 0, 0, 2, 2), matrix.sumRegion(0, 0, 2, 2));
    }

    @Test
    public void testConstructorTakesSnapshotBeforeCallerMutation() {
        int[][] values = {{1, 2, 3}, {4, 5, 6}};
        int[][] original = copy(values);
        NumMatrix_304 matrix = new NumMatrix_304(values);

        values[0][0] = 100;
        values[0][1] = -100;
        values[1][2] = 99_999;

        assertMatrixEquals(original, new int[][]{{1, 2, 3}, {4, 5, 6}});
        assertEquals(21, matrix.sumRegion(0, 0, 1, 2));
        assertEquals(7, matrix.sumRegion(0, 1, 1, 1));
    }

    @Test
    public void testConstructorDoesNotMutateCallerMatrix() {
        int[][] values = {{-4, 0, 8}, {3, 7, -2}};
        int[][] before = copy(values);

        new NumMatrix_304(values);

        assertMatrixEquals(before, values);
    }

    @Test
    public void testSeparateInstancesDoNotSharePrefixState() {
        NumMatrix_304 first = new NumMatrix_304(new int[][]{{1, 2, 3}});
        NumMatrix_304 second = new NumMatrix_304(new int[][]{{-10, 20}, {-30, 40}});

        assertEquals(6, first.sumRegion(0, 0, 0, 2));
        assertEquals(-40, second.sumRegion(0, 0, 1, 0));
        assertEquals(2, first.sumRegion(0, 1, 0, 1));
        assertEquals(10, second.sumRegion(1, 0, 1, 1));
    }

    @Test
    public void testMaximumLegalPositiveTotal() {
        int[][] values = filled(200, 200, 10_000);
        NumMatrix_304 matrix = new NumMatrix_304(values);

        assertEquals(400_000_000, matrix.sumRegion(0, 0, 199, 199));
        assertEquals(100_000_000, matrix.sumRegion(0, 0, 99, 99));
        assertEquals(10_000, matrix.sumRegion(199, 199, 199, 199));
    }

    @Test
    public void testMaximumLegalNegativeTotal() {
        int[][] values = filled(200, 200, -10_000);
        NumMatrix_304 matrix = new NumMatrix_304(values);

        assertEquals(-400_000_000, matrix.sumRegion(0, 0, 199, 199));
        assertEquals(-100_000_000, matrix.sumRegion(0, 0, 99, 99));
        assertEquals(-10_000, matrix.sumRegion(199, 199, 199, 199));
    }

    @Test
    public void testMaximumDimensionsMixedValuesAgainstLongOracle() {
        int[][] values = new int[200][200];
        for (int row = 0; row < values.length; row++) {
            for (int col = 0; col < values[0].length; col++) {
                values[row][col] = switch ((row + col) % 4) {
                    case 0 -> 10_000;
                    case 1 -> -10_000;
                    case 2 -> 1;
                    default -> -1;
                };
            }
        }
        NumMatrix_304 matrix = new NumMatrix_304(values);
        long[][] prefix = longPrefix(values);

        assertEquals(toIntExact(regionFromPrefix(prefix, 0, 0, 199, 199)),
                matrix.sumRegion(0, 0, 199, 199));
        assertEquals(toIntExact(regionFromPrefix(prefix, 1, 37, 198, 164)),
                matrix.sumRegion(1, 37, 198, 164));
        assertEquals(toIntExact(regionFromPrefix(prefix, 99, 99, 100, 100)),
                matrix.sumRegion(99, 99, 100, 100));
    }

    @Test
    public void testMaximumTenThousandQueriesAgainstLongOracle() {
        int[][] values = new int[200][200];
        for (int row = 0; row < values.length; row++) {
            for (int col = 0; col < values[0].length; col++) {
                values[row][col] = ((row * 31 + col * 17 + 7) % 20_001) - 10_000;
            }
        }
        NumMatrix_304 matrix = new NumMatrix_304(values);
        long[][] prefix = longPrefix(values);

        for (int query = 0; query < 10_000; query++) {
            int row1 = (query * 97) % values.length;
            int col1 = (query * 193) % values[0].length;
            int row2 = row1 + ((query * 53) % (values.length - row1));
            int col2 = col1 + ((query * 71) % (values[0].length - col1));
            assertEquals(toIntExact(regionFromPrefix(prefix, row1, col1, row2, col2)),
                    matrix.sumRegion(row1, col1, row2, col2), "query " + query);
        }
    }

    @Test
    public void testMaximumOneRowAndOneColumnBoundaries() {
        int[][] row = new int[1][200];
        int[][] column = new int[200][1];
        for (int index = 0; index < 200; index++) {
            row[0][index] = index - 100;
            column[index][0] = 100 - index;
        }
        NumMatrix_304 rowMatrix = new NumMatrix_304(row);
        NumMatrix_304 columnMatrix = new NumMatrix_304(column);

        assertEquals(directSum(row, 0, 0, 0, 199), rowMatrix.sumRegion(0, 0, 0, 199));
        assertEquals(directSum(row, 0, 73, 0, 149), rowMatrix.sumRegion(0, 73, 0, 149));
        assertEquals(directSum(column, 0, 0, 199, 0), columnMatrix.sumRegion(0, 0, 199, 0));
        assertEquals(directSum(column, 41, 0, 167, 0), columnMatrix.sumRegion(41, 0, 167, 0));
    }

    private static long directSum(int[][] values, int row1, int col1, int row2, int col2) {
        long result = 0;
        for (int row = row1; row <= row2; row++) {
            for (int col = col1; col <= col2; col++) {
                result += values[row][col];
            }
        }
        return result;
    }

    private static long[][] longPrefix(int[][] values) {
        long[][] prefix = new long[values.length + 1][values[0].length + 1];
        for (int row = 1; row <= values.length; row++) {
            for (int col = 1; col <= values[0].length; col++) {
                prefix[row][col] = prefix[row - 1][col] + prefix[row][col - 1]
                        - prefix[row - 1][col - 1] + values[row - 1][col - 1];
            }
        }
        return prefix;
    }

    private static long regionFromPrefix(long[][] prefix, int row1, int col1, int row2, int col2) {
        return prefix[row2 + 1][col2 + 1] - prefix[row1][col2 + 1]
                - prefix[row2 + 1][col1] + prefix[row1][col1];
    }

    private static int toIntExact(long value) {
        return Math.toIntExact(value);
    }

    private static int[][] filled(int rows, int columns, int value) {
        int[][] values = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                values[row][col] = value;
            }
        }
        return values;
    }

    private static int[][] copy(int[][] values) {
        int[][] copy = new int[values.length][];
        for (int row = 0; row < values.length; row++) {
            copy[row] = values[row].clone();
        }
        return copy;
    }

    private static void assertMatrixEquals(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row], "row " + row);
        }
    }
}

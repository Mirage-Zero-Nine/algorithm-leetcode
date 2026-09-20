package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * Tests for the multi-source BFS implementation of LeetCode 542, 01 Matrix.
 * Expected distances are calculated independently from the implementation by
 * taking the minimum Manhattan distance to any zero in the original matrix.
 */
public class UpdateMatrix_542Test {

    @Test
    public void officialExamples() {
        assertResult(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}});
        assertResult(new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}});
    }

    @Test
    public void singletonZeroRemainsZero() {
        assertResult(new int[][]{{0}});
    }

    @Test
    public void singletonOneHasNoZeroImplementationResult() {
        assertArrayEquals(new int[][]{{Integer.MAX_VALUE}},
            new UpdateMatrix_542().updateMatrix(new int[][]{{1}}));
    }

    @Test
    public void allZeroMatricesRemainZero() {
        assertResult(new int[][]{{0, 0}, {0, 0}});
        assertResult(new int[][]{{0, 0, 0, 0}});
        assertResult(new int[][]{{0}, {0}, {0}, {0}});
    }

    @Test
    public void singleRowDistances() {
        assertResult(new int[][]{{1, 1, 0, 1, 2, 3, 0, 1}});
        assertResult(new int[][]{{0, 1, 2, 3, 4, 5}});
        assertResult(new int[][]{{5, 4, 3, 2, 1, 0}});
    }

    @Test
    public void singleColumnDistances() {
        assertResult(new int[][]{{1}, {1}, {0}, {1}, {2}, {3}});
        assertResult(new int[][]{{0}, {1}, {2}, {3}, {4}});
    }

    @Test
    public void rectangularGridWithCornerSource() {
        assertResult(new int[][]{
            {0, 1, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1}
        });
        assertResult(new int[][]{
            {1, 1},
            {1, 1},
            {1, 1},
            {1, 1},
            {1, 1},
            {1, 1},
            {1, 0}
        });
    }

    @Test
    public void multipleSourcesChooseNearest() {
        assertResult(new int[][]{
            {0, 1, 1, 1, 0},
            {1, 1, 1, 1, 1},
            {1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1}
        });
        assertResult(new int[][]{
            {0, 1, 0},
            {1, 1, 1},
            {0, 1, 0}
        });
    }

    @Test
    public void diagonalCellsRequireTwoOrthogonalSteps() {
        assertResult(new int[][]{
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        });
        assertResult(new int[][]{
            {0, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 0}
        });
    }

    @Test
    public void zeroWallAndInteriorSources() {
        assertResult(new int[][]{
            {0, 0, 0, 0, 0},
            {1, 1, 1, 1, 0},
            {1, 1, 1, 1, 0},
            {1, 1, 1, 1, 0},
            {1, 1, 1, 1, 0}
        });
        assertResult(new int[][]{
            {1, 1, 1, 1, 1},
            {1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1}
        });
    }

    @Test
    public void allOnesHasDocumentedImplementationFallback() {
        int[][] matrix = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        assertArrayEquals(new int[][]{
            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}
        }, new UpdateMatrix_542().updateMatrix(matrix));
    }

    @Test
    public void nullAndEmptyInputsUseImplementationGuards() {
        UpdateMatrix_542 solution = new UpdateMatrix_542();
        assertEquals(null, solution.updateMatrix(null));
        assertArrayEquals(new int[][]{}, solution.updateMatrix(new int[][]{}));
        assertArrayEquals(new int[][]{{}}, solution.updateMatrix(new int[][]{{}}));
    }

    @Test
    public void exhaustiveThreeByThreeBinaryMatricesWithAtLeastOneZero() {
        for (int mask = 0; mask < (1 << 9) - 1; mask++) {
            int[][] matrix = new int[3][3];
            for (int index = 0; index < 9; index++) {
                matrix[index / 3][index % 3] = (mask >> index) & 1;
            }
            assertArrayEquals(nearestZeroDistances(matrix),
                new UpdateMatrix_542().updateMatrix(matrix), "mask=" + mask);
        }
    }

    @Test
    public void seededSmallRectanglesMatchIndependentOracle() {
        Random random = new Random(542L);
        for (int sample = 0; sample < 120; sample++) {
            int rows = 1 + random.nextInt(8);
            int columns = 1 + random.nextInt(8);
            int[][] matrix = new int[rows][columns];
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    matrix[row][column] = random.nextInt(2);
                }
            }
            matrix[random.nextInt(rows)][random.nextInt(columns)] = 0;
            int[][] expected = nearestZeroDistances(matrix);
            assertArrayEquals(expected, new UpdateMatrix_542().updateMatrix(matrix),
                "sample=" + sample + ", dimensions=" + rows + "x" + columns);
        }
    }

    @Test
    public void checkerboardHasOnlyZeroOrOneDistances() {
        int[][] matrix = new int[7][8];
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                matrix[row][column] = (row + column) % 2;
            }
        }
        assertResult(matrix);
    }

    @Test
    public void largeSquareAtMaximumCellCountMatchesOracle() {
        int[][] matrix = new int[100][100];
        for (int[] row : matrix) {
            Arrays.fill(row, 1);
        }
        matrix[0][0] = 0;
        assertArrayEquals(nearestZeroDistances(matrix), new UpdateMatrix_542().updateMatrix(matrix));
        assertEquals(0, matrix[0][0]);
        assertEquals(198, matrix[99][99]);
        assertEquals(99, matrix[0][99]);
    }

    @Test
    public void maximumOneRowDimensionMatchesOracle() {
        int[][] matrix = new int[1][10_000];
        Arrays.fill(matrix[0], 1);
        matrix[0][4_321] = 0;
        assertArrayEquals(nearestZeroDistances(matrix), new UpdateMatrix_542().updateMatrix(matrix));
        assertEquals(5_678, matrix[0][9_999]);
    }

    @Test
    public void maximumOneColumnDimensionMatchesOracle() {
        int[][] matrix = new int[10_000][1];
        for (int[] row : matrix) {
            row[0] = 1;
        }
        matrix[4_321][0] = 0;
        assertArrayEquals(nearestZeroDistances(matrix), new UpdateMatrix_542().updateMatrix(matrix));
        assertEquals(5_678, matrix[9_999][0]);
    }

    @Test
    public void inputIsUpdatedInPlaceAndReturnedByIdentity() {
        int[][] matrix = {{1, 1, 0}, {1, 1, 1}};
        int[][] returned = new UpdateMatrix_542().updateMatrix(matrix);
        assertSame(matrix, returned);
        assertArrayEquals(new int[][]{{2, 1, 0}, {3, 2, 1}}, matrix);
    }

    @Test
    public void sameInstanceDoesNotLeakStateBetweenCalls() {
        UpdateMatrix_542 solution = new UpdateMatrix_542();
        int[][] first = {{0, 1, 1}, {1, 1, 1}};
        int[][] second = {{1, 1, 0, 1}, {1, 1, 1, 1}};
        assertArrayEquals(nearestZeroDistances(first), solution.updateMatrix(first));
        assertArrayEquals(nearestZeroDistances(second), solution.updateMatrix(second));
    }

    @Test
    public void separateResultsRemainIndependentAfterAnotherCall() {
        UpdateMatrix_542 solution = new UpdateMatrix_542();
        int[][] first = {{0, 1}, {1, 1}};
        int[][] second = {{1, 1}, {1, 0}};
        int[][] firstResult = solution.updateMatrix(first);
        int[][] secondResult = solution.updateMatrix(second);
        assertArrayEquals(new int[][]{{0, 1}, {1, 2}}, firstResult);
        assertArrayEquals(new int[][]{{2, 1}, {1, 0}}, secondResult);
    }

    @Test
    public void sourceCellsRemainZeroAcrossShapes() {
        int[][] matrix = {
            {0, 1, 1, 0},
            {1, 1, 1, 1},
            {0, 1, 1, 0}
        };
        int[][] result = new UpdateMatrix_542().updateMatrix(matrix);
        assertEquals(0, result[0][0]);
        assertEquals(0, result[0][3]);
        assertEquals(0, result[2][0]);
        assertEquals(0, result[2][3]);
    }

    @Test
    public void nearestSourceTieIsHandledFromEitherDirection() {
        assertResult(new int[][]{
            {1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1},
            {0, 1, 1, 1, 0}
        });
        assertResult(new int[][]{
            {0, 1, 1, 1, 0},
            {1, 1, 1, 1, 1},
            {1, 1, 0, 1, 1}
        });
    }

    @Test
    public void distancesNearMaximumGridDiameterRemainExact() {
        int[][] matrix = new int[2][5_000];
        for (int[] row : matrix) {
            Arrays.fill(row, 1);
        }
        matrix[0][0] = 0;
        assertArrayEquals(nearestZeroDistances(matrix), new UpdateMatrix_542().updateMatrix(matrix));
        assertEquals(4_999, matrix[0][4_999]);
        assertEquals(5_000, matrix[1][4_999]);
    }

    private void assertResult(int[][] matrix) {
        int[][] expected = nearestZeroDistances(matrix);
        assertArrayEquals(expected, new UpdateMatrix_542().updateMatrix(matrix));
    }

    /**
     * Computes the exact answer directly from each zero, independently of BFS.
     * The all-ones fallback mirrors the implementation's documented extension
     * for inputs outside the LeetCode contract, which requires one zero.
     */
    private int[][] nearestZeroDistances(int[][] matrix) {
        int[][] result = new int[matrix.length][];
        boolean hasZero = false;
        for (int[] row : matrix) {
            for (int value : row) {
                hasZero |= value == 0;
            }
        }
        for (int row = 0; row < matrix.length; row++) {
            result[row] = new int[matrix[row].length];
            for (int column = 0; column < matrix[row].length; column++) {
                if (!hasZero) {
                    result[row][column] = Integer.MAX_VALUE;
                } else if (matrix[row][column] == 0) {
                    result[row][column] = 0;
                } else {
                    int nearest = Integer.MAX_VALUE;
                    for (int sourceRow = 0; sourceRow < matrix.length; sourceRow++) {
                        for (int sourceColumn = 0; sourceColumn < matrix[sourceRow].length; sourceColumn++) {
                            if (matrix[sourceRow][sourceColumn] == 0) {
                                nearest = Math.min(nearest,
                                    Math.abs(row - sourceRow) + Math.abs(column - sourceColumn));
                            }
                        }
                    }
                    result[row][column] = nearest;
                }
            }
        }
        return result;
    }
}

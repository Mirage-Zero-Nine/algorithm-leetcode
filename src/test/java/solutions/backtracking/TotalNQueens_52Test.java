package solutions.backtracking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TotalNQueens_52Test {
    private final TotalNQueens_52 solution = new TotalNQueens_52();

    @Test
    void testFour() {
        assertEquals(2, solution.totalNQueens(4));
    }

    @Test
    void testOne() {
        assertEquals(1, solution.totalNQueens(1));
    }

    @Test
    void testEight() {
        assertEquals(92, solution.totalNQueens(8));
    }

    @Test
    void testTwo() {
        assertEquals(0, solution.totalNQueens(2));
    }

    @Test
    void testThree() {
        assertEquals(0, solution.totalNQueens(3));
    }

    @Test
    void testFive() {
        assertEquals(10, solution.totalNQueens(5));
    }

    @Test
    void testSix() {
        assertEquals(4, solution.totalNQueens(6));
    }

    @Test
    void testSeven() {
        assertEquals(40, solution.totalNQueens(7));
    }

    @Test
    void testNine() {
        assertEquals(352, solution.totalNQueens(9));
    }

    @Test
    void everySupportedSizeMatchesIndependentPermutationOracle() {
        int[] expected = {0, 1, 0, 0, 2, 10, 4, 40, 92, 352};

        for (int n = 1; n <= 9; n++) {
            int oracleCount = countWithPermutationOracle(n);
            assertEquals(expected[n], oracleCount, "independent oracle for n=" + n);
            assertEquals(oracleCount, solution.totalNQueens(n), "solution for n=" + n);
        }
    }

    @Test
    void repeatedCallsDoNotLeakSearchState() {
        assertEquals(352, solution.totalNQueens(9));
        assertEquals(1, solution.totalNQueens(1));
        assertEquals(0, solution.totalNQueens(3));
        assertEquals(40, solution.totalNQueens(7));
        assertEquals(2, solution.totalNQueens(4));
    }

    @Test
    void emptyBoardHasOneEmptyArrangementUnderImplementationContract() {
        // LeetCode restricts n to 1..9; the implementation also handles n == 0.
        assertEquals(1, solution.totalNQueens(0));
    }

    @Test
    void bitmaskOracleMatchesProductionForEveryOfficialSize() {
        int[] expected = {0, 1, 0, 0, 2, 10, 4, 40, 92, 352};

        for (int n = 1; n <= 9; n++) {
            int oracleCount = countWithBitmaskOracle(n);
            assertEquals(expected[n], oracleCount, "bitmask oracle for n=" + n);
            assertEquals(oracleCount, solution.totalNQueens(n), "solution for n=" + n);
        }
    }

    @Test
    void permutationAndBitmaskOraclesAgreeOnEveryOfficialSize() {
        for (int n = 1; n <= 9; n++) {
            assertEquals(countWithPermutationOracle(n), countWithBitmaskOracle(n),
                    "independent oracles for n=" + n);
        }
    }

    @Test
    void freshSolutionInstancesDoNotShareSearchState() {
        assertEquals(92, new TotalNQueens_52().totalNQueens(8));
        assertEquals(2, new TotalNQueens_52().totalNQueens(4));
        assertEquals(352, new TotalNQueens_52().totalNQueens(9));
    }

    @Test
    void sameBoardSizeCanBeSolvedRepeatedly() {
        assertEquals(40, solution.totalNQueens(7));
        assertEquals(40, solution.totalNQueens(7));
        assertEquals(40, solution.totalNQueens(7));
    }

    @Test
    void interleavedBoardSizesRemainIndependent() {
        int[] sizes = {4, 8, 2, 9, 1, 5, 3, 6, 7};
        int[] expected = {2, 92, 0, 352, 1, 10, 0, 4, 40};

        for (int i = 0; i < sizes.length; i++) {
            assertEquals(expected[i], solution.totalNQueens(sizes[i]), "n=" + sizes[i]);
        }
    }

    @Test
    void countsAreNonnegativeAndDoNotExceedRowPermutationBound() {
        for (int n = 1; n <= 9; n++) {
            int count = solution.totalNQueens(n);
            assertTrue(count >= 0, "nonnegative count for n=" + n);
            assertTrue(count <= factorial(n), "permutation bound for n=" + n);
        }
    }

    @Test
    void independentOracleCoversTheNoSolutionSizes() {
        assertEquals(0, countWithBitmaskOracle(2));
        assertEquals(0, countWithBitmaskOracle(3));
        assertEquals(0, solution.totalNQueens(2));
        assertEquals(0, solution.totalNQueens(3));
    }

    @Test
    void maximumOfficialSizeRemainsCorrectAfterSmallerSearches() {
        assertEquals(1, solution.totalNQueens(1));
        assertEquals(0, solution.totalNQueens(2));
        assertEquals(10, solution.totalNQueens(5));
        assertEquals(352, solution.totalNQueens(9));
    }

    /**
     * Independently counts row permutations whose positions have no diagonal conflict. The
     * production implementation searches column-by-column on a board, while this oracle only
     * uses a row permutation and pairwise diagonal checks.
     */
    private int countWithPermutationOracle(int n) {
        boolean[] usedRows = new boolean[n];
        int[] rowsByColumn = new int[n];
        return countPermutations(0, rowsByColumn, usedRows);
    }

    private int countPermutations(int column, int[] rowsByColumn, boolean[] usedRows) {
        if (column == rowsByColumn.length) {
            return 1;
        }

        int count = 0;
        for (int row = 0; row < rowsByColumn.length; row++) {
            if (!usedRows[row] && hasNoDiagonalConflict(column, row, rowsByColumn)) {
                usedRows[row] = true;
                rowsByColumn[column] = row;
                count += countPermutations(column + 1, rowsByColumn, usedRows);
                usedRows[row] = false;
            }
        }
        return count;
    }

    private boolean hasNoDiagonalConflict(int column, int row, int[] rowsByColumn) {
        for (int previousColumn = 0; previousColumn < column; previousColumn++) {
            int previousRow = rowsByColumn[previousColumn];
            if (Math.abs(row - previousRow) == column - previousColumn) {
                return false;
            }
        }
        return true;
    }

    /**
     * Counts placements using occupied-row and diagonal bit masks rather than a board. This
     * gives the board-search implementation a materially different independent oracle.
     */
    private int countWithBitmaskOracle(int n) {
        return countBitmaskRows(n, 0, 0, 0, 0);
    }

    private int countBitmaskRows(int n, int row, int usedRows, int usedDownDiagonals,
                                 int usedUpDiagonals) {
        if (row == n) {
            return 1;
        }

        int count = 0;
        for (int column = 0; column < n; column++) {
            int rowBit = 1 << column;
            int downBit = 1 << (row - column + n - 1);
            int upBit = 1 << (row + column);
            if ((usedRows & rowBit) == 0
                    && (usedDownDiagonals & downBit) == 0
                    && (usedUpDiagonals & upBit) == 0) {
                count += countBitmaskRows(n, row + 1, usedRows | rowBit,
                        usedDownDiagonals | downBit, usedUpDiagonals | upBit);
            }
        }
        return count;
    }

    private int factorial(int n) {
        int result = 1;
        for (int value = 2; value <= n; value++) {
            result *= value;
        }
        return result;
    }
}

package solutions.backtracking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}

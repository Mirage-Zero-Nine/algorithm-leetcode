package solution.backtracking;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SolveNQueens_51Test {
    private final SolveNQueens_51 solution = new SolveNQueens_51();

    @Test
    void testFour() {
        List<List<String>> result = solution.solveNQueens(4);
        assertEquals(2, result.size());
    }

    @Test
    void testOne() {
        List<List<String>> result = solution.solveNQueens(1);
        assertEquals(1, result.size());
    }

    @Test
    void testEight() {
        List<List<String>> result = solution.solveNQueens(8);
        assertEquals(92, result.size());
    }

    @Test
    void testTwo() {
        List<List<String>> result = solution.solveNQueens(2);
        assertEquals(0, result.size());
    }

    @Test
    void testThree() {
        List<List<String>> result = solution.solveNQueens(3);
        assertEquals(0, result.size());
    }

    @Test
    void testFive() {
        List<List<String>> result = solution.solveNQueens(5);
        assertEquals(10, result.size());
    }

    @Test
    void testSix() {
        List<List<String>> result = solution.solveNQueens(6);
        assertEquals(4, result.size());
    }

    @Test
    void testSeven() {
        List<List<String>> result = solution.solveNQueens(7);
        assertEquals(40, result.size());
    }

    @Test
    void testBoardFormat() {
        List<List<String>> result = solution.solveNQueens(4);
        // each solution should have 4 rows of length 4
        for (List<String> board : result) {
            assertEquals(4, board.size());
            for (String row : board) {
                assertEquals(4, row.length());
            }
        }
    }

    @Test
    void testEachRowHasOneQueen() {
        List<List<String>> result = solution.solveNQueens(4);
        for (List<String> board : result) {
            for (String row : board) {
                long queens = row.chars().filter(c -> c == 'Q').count();
                assertEquals(1, queens);
            }
        }
    }

    @Test
    void testGiantCase() {
        // n=9 has 352 solutions
        List<List<String>> result = solution.solveNQueens(9);
        assertEquals(352, result.size());
    }

    @Test
    void testN1SolutionContent() {
        List<List<String>> result = solution.solveNQueens(1);
        assertEquals(List.of(List.of("Q")), result);
    }

    @Test
    void testNoQueensAttackEachOtherN8() {
        List<List<String>> result = solution.solveNQueens(8);
        for (List<String> board : result) {
            assertNoQueensAttack(board);
        }
    }

    @Test
    void testAllSolutionsUniqueN5() {
        List<List<String>> result = solution.solveNQueens(5);
        Set<List<String>> unique = new HashSet<>(result);
        assertEquals(result.size(), unique.size());
    }

    @Test
    void testBoardContainsOnlyQAndDot() {
        List<List<String>> result = solution.solveNQueens(6);
        for (List<String> board : result) {
            for (String row : board) {
                assertTrue(row.matches("[Q.]+"));
            }
        }
    }

    @Test
    void testEveryRowHasExactlyOneQueenN8() {
        List<List<String>> result = solution.solveNQueens(8);
        for (List<String> board : result) {
            assertEquals(8, board.size());
            for (String row : board) {
                assertEquals(8, row.length());
                assertEquals(1, row.chars().filter(c -> c == 'Q').count());
            }
        }
    }

    @Test
    void testEveryColumnHasExactlyOneQueenN8() {
        List<List<String>> result = solution.solveNQueens(8);
        for (List<String> board : result) {
            for (int col = 0; col < 8; col++) {
                int queens = 0;
                for (String row : board) {
                    if (row.charAt(col) == 'Q') queens++;
                }
                assertEquals(1, queens);
            }
        }
    }

    @Test
    void testN4SolutionContent() {
        List<List<String>> result = solution.solveNQueens(4);
        Set<List<String>> expected = Set.of(
                List.of(".Q..", "...Q", "Q...", "..Q."),
                List.of("..Q.", "Q...", "...Q", ".Q..")
        );
        assertEquals(expected, new HashSet<>(result));
    }

    @Test
    void testOEISSequenceA000170() {
        // OEIS A000170: number of solutions for n=1..9
        int[] expectedCounts = {1, 0, 0, 2, 10, 4, 40, 92, 352};
        for (int n = 1; n <= 9; n++) {
            assertEquals(expectedCounts[n - 1], solution.solveNQueens(n).size(),
                    "Mismatch for n=" + n);
        }
    }

    private void assertNoQueensAttack(List<String> board) {
        int n = board.size();
        List<int[]> queens = new ArrayList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board.get(r).charAt(c) == 'Q') queens.add(new int[]{r, c});
            }
        }
        for (int i = 0; i < queens.size(); i++) {
            for (int j = i + 1; j < queens.size(); j++) {
                int r1 = queens.get(i)[0], c1 = queens.get(i)[1];
                int r2 = queens.get(j)[0], c2 = queens.get(j)[1];
                assertFalse(r1 == r2, "Same row conflict");
                assertFalse(c1 == c2, "Same column conflict");
                assertFalse(Math.abs(r1 - r2) == Math.abs(c1 - c2), "Diagonal conflict");
            }
        }
    }
}

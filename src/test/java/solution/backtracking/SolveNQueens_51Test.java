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
}

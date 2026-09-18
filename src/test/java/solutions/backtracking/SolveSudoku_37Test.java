package solutions.backtracking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SolveSudoku_37Test {
    private static final String[] SOLVED = {
        "534678912", "672195348", "198342567", "859761423", "426853791",
        "713924856", "961537284", "287419635", "345286179"
    };
    private final SolveSudoku_37 solution = new SolveSudoku_37();

    @ParameterizedTest(name = "{0}")
    @MethodSource("puzzles")
    void solvesEveryValidUniquePuzzle(PuzzleCase puzzle) {
        char[][] board = parse(puzzle.rows);
        char[][] givens = copy(board);
        assertEquals(1, countSolutions(copy(board), 2),
            "Fixture must remain uniquely solvable: " + puzzle.name);
        solution.solveSudoku(board);
        assertSolved(board);
        assertGivensUnchanged(givens, board);
    }

    @Test
    void acceptsAnAlreadySolvedBoardWithoutChangingIt() {
        char[][] board = parse(SOLVED);
        char[][] expected = copy(board);
        solution.solveSudoku(board);
        assertArrayEquals(expected, board);
    }

    @Test
    void repeatedCallsUseFreshBoardState() {
        char[][] first = parse(puzzles().findFirst().orElseThrow().rows);
        char[][] second = parse(puzzles().skip(1).findFirst().orElseThrow().rows);
        solution.solveSudoku(first);
        solution.solveSudoku(second);
        assertSolved(first);
        assertSolved(second);
    }

    static Stream<PuzzleCase> puzzles() {
        List<PuzzleCase> cases = new ArrayList<>();
        cases.add(new PuzzleCase("classic LeetCode example", new String[] {
            "53..7....", "6..195...", ".98....6.", "8...6...3", "4..8.3..1",
            "7...2...6", ".6....28.", "...419..5", "....8..79"
        }));
        cases.add(new PuzzleCase("sparse unique puzzle", new String[] {
            ". . 9 7 4 8 . . .", "7 . . . . . . . .", ". 2 . 1 . 9 . . .",
            ". . 7 . . . 2 4 .", ". 6 4 . 1 . 5 9 .", ". 9 8 . . . 3 . .",
            ". . . 8 . 3 . 2 .", ". . . . . . . . 6", ". . . 2 7 5 9 . ."
        }));
        cases.add(new PuzzleCase("hard unique puzzle", new String[] {
            "8........", "..36.....", ".7..9.2..", ".5...7...", "....457..",
            "...1...3.", "..1....68", "..85...1.", ".9....4.."
        }));

        int[][] blanks = {
            {2}, {80}, {0},
            {0, 1}, {2, 8}, {20, 21}, {26, 35}, {54, 63}, {71, 80},
            {0, 4, 8}, {20, 24, 28}, {36, 40, 44}, {60, 67, 74},
            {3, 12}, {14, 23}, {30, 39}, {48, 57}, {6, 15}, {68, 77},
            {0, 1, 2, 3, 4, 5, 6, 7, 8},
            {0, 9, 18, 27, 36, 45, 54, 63, 72},
            {0, 1, 2, 9, 10, 11, 18, 19, 20}
        };
        for (int i = 0; i < blanks.length; i++) {
            cases.add(new PuzzleCase("generated unique variant " + (i + 1),
                withBlanks(blanks[i])));
        }
        return cases.stream();
    }

    private static String[] withBlanks(int[] cells) {
        char[][] board = parse(SOLVED);
        for (int cell : cells) board[cell / 9][cell % 9] = '.';
        String[] result = new String[9];
        for (int row = 0; row < 9; row++) result[row] = new String(board[row]);
        return result;
    }

    private static char[][] parse(String[] rows) {
        char[][] board = new char[9][9];
        for (int row = 0; row < 9; row++) {
            String compact = rows[row].replace(" ", "");
            assertEquals(9, compact.length(), "Every row must contain nine cells");
            board[row] = compact.toCharArray();
        }
        return board;
    }

    private static char[][] copy(char[][] board) {
        char[][] result = new char[9][];
        for (int row = 0; row < 9; row++) result[row] = board[row].clone();
        return result;
    }

    private static void assertGivensUnchanged(char[][] givens, char[][] solved) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (givens[row][col] != '.') {
                    assertEquals(givens[row][col], solved[row][col],
                        "Given changed at [" + row + "][" + col + "]");
                }
            }
        }
    }

    private static void assertSolved(char[][] board) {
        Set<Character> expected = new HashSet<>();
        for (char digit = '1'; digit <= '9'; digit++) expected.add(digit);
        for (int row = 0; row < 9; row++) {
            Set<Character> values = new HashSet<>();
            for (int col = 0; col < 9; col++) {
                assertTrue(board[row][col] >= '1' && board[row][col] <= '9');
                values.add(board[row][col]);
            }
            assertEquals(expected, values, "Invalid row " + row);
        }
        for (int col = 0; col < 9; col++) {
            Set<Character> values = new HashSet<>();
            for (int row = 0; row < 9; row++) values.add(board[row][col]);
            assertEquals(expected, values, "Invalid column " + col);
        }
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                Set<Character> values = new HashSet<>();
                for (int row = boxRow * 3; row < boxRow * 3 + 3; row++) {
                    for (int col = boxCol * 3; col < boxCol * 3 + 3; col++) {
                        values.add(board[row][col]);
                    }
                }
                assertEquals(expected, values, "Invalid box [" + boxRow + "][" + boxCol + "]");
            }
        }
    }

    /** Independent MRV oracle; stops after two solutions. */
    private static int countSolutions(char[][] board, int limit) {
        int bestRow = -1, bestCol = -1, bestCount = 10;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    int count = 0;
                    for (char digit = '1'; digit <= '9'; digit++) {
                        if (allowed(board, row, col, digit)) count++;
                    }
                    if (count < bestCount) {
                        bestCount = count;
                        bestRow = row;
                        bestCol = col;
                    }
                }
            }
        }
        if (bestRow < 0) return 1;
        if (bestCount == 0) return 0;
        int total = 0;
        for (char digit = '1'; digit <= '9' && total < limit; digit++) {
            if (allowed(board, bestRow, bestCol, digit)) {
                board[bestRow][bestCol] = digit;
                total += countSolutions(board, limit - total);
                board[bestRow][bestCol] = '.';
            }
        }
        return total;
    }

    private static boolean allowed(char[][] board, int row, int col, char digit) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == digit || board[i][col] == digit
                || board[row / 3 * 3 + i / 3][col / 3 * 3 + i % 3] == digit) return false;
        }
        return true;
    }

    record PuzzleCase(String name, String[] rows) { }
}

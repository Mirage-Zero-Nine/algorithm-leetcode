package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * Contract tests for LeetCode 1222, Queens That Can Attack the King.
 *
 * <p>The official contract uses a 0-indexed 8x8 board, unique queen positions, and asks for
 * the closest queen in each of the eight queen-movement directions. Expected values are derived
 * by an independent per-queen geometry oracle, and comparisons deliberately ignore output order.
 */
public class QueensAttacktheKing_1222Test {

    private final QueensAttacktheKing_1222 solution = new QueensAttacktheKing_1222();

    @Test
    void officialExampleOne() {
        int[][] queens = {
                {0, 1}, {1, 0}, {4, 0}, {0, 4}, {3, 3}, {2, 4}
        };
        assertMatchesOracle(queens, new int[]{0, 0});
    }

    @Test
    void officialExampleTwo() {
        int[][] queens = {
                {0, 0}, {1, 1}, {2, 2}, {3, 4}, {3, 5}, {4, 4}, {4, 5}
        };
        assertMatchesOracle(queens, new int[]{3, 3});
    }

    @Test
    void historicalDenseExample() {
        int[][] queens = {
                {5, 6}, {7, 7}, {2, 1}, {0, 7}, {1, 6}, {5, 1}, {3, 7}, {0, 3},
                {4, 0}, {1, 2}, {6, 3}, {5, 0}, {0, 4}, {2, 2}, {1, 1}, {6, 4},
                {5, 4}, {0, 0}, {2, 6}, {4, 5}, {5, 2}, {1, 4}, {7, 5}, {2, 3},
                {0, 5}, {4, 2}, {1, 0}, {2, 7}, {0, 1}, {4, 6}, {6, 1}, {0, 6},
                {4, 3}, {1, 7}
        };
        assertMatchesOracle(queens, new int[]{3, 4});
    }

    @Test
    void allEightAdjacentDirections() {
        int[][] queens = {
                {3, 3}, {3, 4}, {3, 5}, {4, 3}, {4, 5}, {5, 3}, {5, 4}, {5, 5}
        };
        assertMatchesOracle(queens, new int[]{4, 4});
    }

    @Test
    void everyDirectionReturnsClosestQueenWhenAllAreBlocked() {
        int[] king = {3, 3};
        int[][] queens = {
                {2, 2}, {1, 1}, {0, 0}, {2, 3}, {1, 3}, {0, 3}, {2, 4}, {1, 5}, {0, 6},
                {3, 2}, {3, 1}, {3, 0}, {3, 4}, {3, 5}, {3, 6}, {4, 2}, {5, 1}, {6, 0},
                {4, 3}, {5, 3}, {6, 3}, {4, 4}, {5, 5}, {6, 6}, {7, 7}
        };
        assertMatchesOracle(queens, king);
    }

    @Test
    void sameRowKeepsOnlyTheNearestQueen() {
        assertMatchesOracle(new int[][]{{4, 7}, {4, 5}, {4, 0}}, new int[]{4, 3});
    }

    @Test
    void sameColumnKeepsOnlyTheNearestQueen() {
        assertMatchesOracle(new int[][]{{7, 2}, {5, 2}, {0, 2}}, new int[]{3, 2});
    }

    @Test
    void diagonalKeepsOnlyTheNearestQueen() {
        assertMatchesOracle(new int[][]{{0, 0}, {2, 2}, {6, 6}, {5, 1}}, new int[]{3, 3});
    }

    @Test
    void nonAlignedQueensCannotAttack() {
        int[][] queens = {{0, 2}, {2, 5}, {6, 1}, {7, 4}, {1, 6}};
        assertMatchesOracle(queens, new int[]{4, 4});
    }

    @Test
    void cornerKingCanBeAttackedInItsThreeAvailableDirections() {
        assertMatchesOracle(new int[][]{{0, 1}, {1, 0}, {1, 1}}, new int[]{0, 0});
    }

    @Test
    void oppositeCornerKingHasThreeDirections() {
        assertMatchesOracle(new int[][]{{7, 6}, {6, 7}, {6, 6}, {0, 0}}, new int[]{7, 7});
    }

    @Test
    void topEdgeKingHasFiveAvailableDirections() {
        assertMatchesOracle(new int[][]{{0, 0}, {0, 7}, {1, 3}, {1, 2}, {1, 4}, {7, 3}},
                new int[]{0, 3});
    }

    @Test
    void bottomEdgeKingHasFiveAvailableDirections() {
        assertMatchesOracle(new int[][]{{7, 0}, {7, 7}, {6, 3}, {6, 2}, {6, 4}, {0, 3}},
                new int[]{7, 3});
    }

    @Test
    void farBoundaryQueenCanAttackOnLongDiagonal() {
        assertMatchesOracle(new int[][]{{7, 7}}, new int[]{0, 0});
    }

    @Test
    void farBoundaryQueenCanAttackOnLongRow() {
        assertMatchesOracle(new int[][]{{0, 7}}, new int[]{0, 0});
    }

    @Test
    void farBoundaryQueenCanAttackOnLongColumn() {
        assertMatchesOracle(new int[][]{{7, 0}}, new int[]{0, 0});
    }

    @Test
    void emptyQueenCollectionIsAnEmptyResult() {
        assertMatchesOracle(new int[][]{}, new int[]{4, 4});
    }

    @Test
    void oneQueenAtEachBoardCornerIsOnlyVisibleWhenAligned() {
        int[][] queens = {{0, 0}, {0, 7}, {7, 0}, {7, 7}};
        assertMatchesOracle(queens, new int[]{3, 3});
    }

    @Test
    void allSixtyThreeAllowedQueensStillReturnOnlyEightBlockers() {
        int[] king = {3, 3};
        int[][] queens = new int[63][2];
        int index = 0;
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (row != king[0] || column != king[1]) {
                    queens[index++] = new int[]{row, column};
                }
            }
        }
        assertEquals(63, queens.length);
        assertMatchesOracle(queens, king);
    }

    @Test
    void shuffledInputDoesNotImposeAnOutputOrder() {
        int[][] queens = {{5, 5}, {1, 1}, {3, 6}, {6, 3}, {2, 3}, {3, 1}, {7, 7}};
        assertMatchesOracle(queens, new int[]{3, 3});
    }

    @Test
    void inputArraysAreNotMutated() {
        int[][] queens = {{0, 1}, {1, 0}, {4, 0}, {3, 3}};
        int[][] originalQueens = deepCopy(queens);
        int[] king = {0, 0};
        int[] originalKing = king.clone();

        assertMatchesOracle(queens, king);

        assertTrue(Arrays.deepEquals(originalQueens, queens));
        assertTrue(Arrays.equals(originalKing, king));
    }

    @Test
    void repeatedCallsDoNotLeakPreviousBoardState() {
        int[][] firstQueens = {{0, 1}, {1, 0}, {1, 1}};
        int[] firstKing = {0, 0};
        int[][] secondQueens = {{7, 7}, {3, 3}, {3, 4}};
        int[] secondKing = {7, 0};

        assertMatchesOracle(firstQueens, firstKing);
        assertMatchesOracle(secondQueens, secondKing);
        assertMatchesOracle(firstQueens, firstKing);
    }

    @Test
    void returnedRowsAndOuterListAreFreshPerInvocation() {
        int[][] queens = {{0, 1}, {1, 0}, {1, 1}};
        int[] king = {0, 0};
        List<List<Integer>> first = solution.queensAttacktheKing(queens, king);
        assertFalse(first.isEmpty());
        List<Integer> firstRow = first.get(0);
        first.remove(0);
        firstRow.set(0, 7);

        List<List<Integer>> second = solution.queensAttacktheKing(queens, king);
        assertMatchesOracleResult(second, oracle(queens, king));
        assertNotSame(first, second);
        assertNotSame(firstRow, second.get(0));
    }

    @Test
    void deterministicArbitraryBoardsMatchIndependentOracle() {
        Random random = new Random(1222L);
        for (int caseNumber = 0; caseNumber < 100; caseNumber++) {
            int[] king = {random.nextInt(8), random.nextInt(8)};
            List<int[]> positions = new ArrayList<>();
            boolean[][] used = new boolean[8][8];
            used[king[0]][king[1]] = true;
            int queenCount = 1 + random.nextInt(63);
            while (positions.size() < queenCount) {
                int row = random.nextInt(8);
                int column = random.nextInt(8);
                if (!used[row][column]) {
                    used[row][column] = true;
                    positions.add(new int[]{row, column});
                }
            }
            int[][] queens = positions.toArray(new int[0][]);
            assertMatchesOracle(queens, king);
        }
    }

    private void assertMatchesOracle(int[][] queens, int[] king) {
        assertMatchesOracleResult(solution.queensAttacktheKing(queens, king), oracle(queens, king));
    }

    private void assertMatchesOracleResult(List<List<Integer>> actual, Set<List<Integer>> expected) {
        Set<List<Integer>> actualSet = new HashSet<>();
        for (List<Integer> coordinate : actual) {
            actualSet.add(List.copyOf(coordinate));
        }
        assertEquals(expected, actualSet);
        assertEquals(expected.size(), actual.size(), "a queen must be returned at most once");
    }

    /**
     * Independent oracle: inspect every queen, then walk from that queen toward the king to see
     * whether another queen lies strictly between them. This does not rely on the solution's
     * direction loop or board representation.
     */
    private Set<List<Integer>> oracle(int[][] queens, int[] king) {
        Set<List<Integer>> positions = new HashSet<>();
        for (int[] queen : queens) {
            positions.add(List.of(queen[0], queen[1]));
        }

        Set<List<Integer>> attacking = new HashSet<>();
        for (int[] queen : queens) {
            int rowDelta = queen[0] - king[0];
            int columnDelta = queen[1] - king[1];
            boolean aligned = rowDelta == 0 || columnDelta == 0
                    || Math.abs(rowDelta) == Math.abs(columnDelta);
            if (!aligned) {
                continue;
            }

            int rowStep = Integer.compare(queen[0], king[0]);
            int columnStep = Integer.compare(queen[1], king[1]);
            int row = king[0] + rowStep;
            int column = king[1] + columnStep;
            boolean blocked = false;
            while (row != queen[0] || column != queen[1]) {
                if (positions.contains(List.of(row, column))) {
                    blocked = true;
                    break;
                }
                row += rowStep;
                column += columnStep;
            }
            if (!blocked) {
                attacking.add(List.of(queen[0], queen[1]));
            }
        }
        return attacking;
    }

    private int[][] deepCopy(int[][] source) {
        int[][] copy = new int[source.length][];
        for (int index = 0; index < source.length; index++) {
            copy[index] = source[index].clone();
        }
        return copy;
    }
}

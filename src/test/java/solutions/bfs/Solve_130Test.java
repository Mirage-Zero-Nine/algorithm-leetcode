package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Contract tests for LeetCode 130. Expected boards come from an independent
 * component flood-fill, rather than from the production boundary-marking strategy.
 */
public class Solve_130Test {

    private static Stream<Arguments> solvers() {
        Solve_130 solution = new Solve_130();
        return Stream.of(
                Arguments.of("BFS", (Consumer<char[][]>) solution::solve),
                Arguments.of("DFS", (Consumer<char[][]>) solution::solveDfs)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void officialExample(String approach, Consumer<char[][]> solver) {
        assertSolved(approach, solver, new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        });
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void singletonXIsUnchanged(String approach, Consumer<char[][]> solver) {
        assertSolved(approach, solver, new char[][]{{'X'}});
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void singletonOIsBoundarySafe(String approach, Consumer<char[][]> solver) {
        assertSolved(approach, solver, new char[][]{{'O'}});
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void emptyBoardUsesImplementationGuard(String approach, Consumer<char[][]> solver) {
        assertSolved(approach, solver, new char[][]{});
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void zeroWidthBoardUsesImplementationGuard(String approach, Consumer<char[][]> solver) {
        assertSolved(approach, solver, new char[][]{{}});
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void allXHasNoRegionsToCapture(String approach, Consumer<char[][]> solver) {
        assertSolved(approach, solver, new char[][]{
                {'X', 'X', 'X'}, {'X', 'X', 'X'}, {'X', 'X', 'X'}
        });
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void allOBoundaryConnectedBoardIsUnchanged(String approach, Consumer<char[][]> solver) {
        assertSolved(approach, solver, new char[][]{
                {'O', 'O', 'O'}, {'O', 'O', 'O'}, {'O', 'O', 'O'}
        });
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void singleRowCannotContainASurroundedRegion(String approach, Consumer<char[][]> solver) {
        assertSolved(approach, solver, new char[][]{{'X', 'O', 'O', 'X', 'O', 'X'}});
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void singleColumnCannotContainASurroundedRegion(String approach, Consumer<char[][]> solver) {
        assertSolved(approach, solver, new char[][]{{'X'}, {'O'}, {'O'}, {'X'}, {'O'}});
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void isolatedInteriorCellIsCaptured(String approach, Consumer<char[][]> solver) {
        assertSolved(approach, solver, new char[][]{
                {'X', 'X', 'X'}, {'X', 'O', 'X'}, {'X', 'X', 'X'}
        });
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void connectedInteriorRegionIsCapturedAsAWhole(String approach, Consumer<char[][]> solver) {
        assertSolved(approach, solver, new char[][]{
                {'X', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'O', 'O', 'X'},
                {'X', 'X', 'X', 'X', 'X'}
        });
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void interiorRegionConnectedToTopBorderSurvives(String approach, Consumer<char[][]> solver) {
        assertSolved(approach, solver, new char[][]{
                {'X', 'O', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'O', 'X'},
                {'X', 'X', 'X', 'O', 'X'},
                {'X', 'X', 'X', 'X', 'X'}
        });
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void interiorRegionConnectedToLeftBorderSurvives(String approach, Consumer<char[][]> solver) {
        assertSolved(approach, solver, new char[][]{
                {'X', 'X', 'X', 'X', 'X'},
                {'O', 'O', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'O', 'X'},
                {'X', 'X', 'X', 'X', 'X'}
        });
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void diagonalContactDoesNotPreventCapture(String approach, Consumer<char[][]> solver) {
        assertSolved(approach, solver, new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'X', 'X', 'X'}
        });
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void diagonalBorderODoesNotSaveAnInteriorRegion(String approach, Consumer<char[][]> solver) {
        assertSolved(approach, solver, new char[][]{
                {'O', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'X', 'X', 'X'}
        });
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void mixedSafeAndCapturedRegionsAreHandledIndependently(String approach, Consumer<char[][]> solver) {
        assertSolved(approach, solver, new char[][]{
                {'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X', 'O', 'X'},
                {'X', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'O', 'O', 'X', 'X'},
                {'X', 'O', 'X', 'X', 'X', 'X'}
        });
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void narrowInteriorRingIsCaptured(String approach, Consumer<char[][]> solver) {
        assertSolved(approach, solver, new char[][]{
                {'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'O', 'O', 'X'},
                {'X', 'O', 'X', 'X', 'O', 'X'},
                {'X', 'O', 'O', 'O', 'O', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X'}
        });
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void rectangularWideBoardPreservesBoundaryAndCapturesInterior(String approach,
                                                                   Consumer<char[][]> solver) {
        assertSolved(approach, solver, new char[][]{
                {'O', 'X', 'X', 'X', 'X', 'X', 'O'},
                {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'X', 'O', 'O', 'O', 'X', 'X'}
        });
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void rectangularTallBoardPreservesBoundaryAndCapturesInterior(String approach,
                                                                   Consumer<char[][]> solver) {
        assertSolved(approach, solver, new char[][]{
                {'O', 'X', 'X'},
                {'X', 'O', 'X'},
                {'X', 'O', 'X'},
                {'X', 'X', 'X'},
                {'X', 'O', 'O'},
                {'X', 'X', 'X'},
                {'O', 'X', 'X'}
        });
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void duplicateDisconnectedInteriorRegionsAreBothCaptured(String approach, Consumer<char[][]> solver) {
        assertSolved(approach, solver, new char[][]{
                {'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X', 'X', 'O', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X'}
        });
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void checkerboardUsesOrthogonalConnectivityOnly(String approach, Consumer<char[][]> solver) {
        assertSolved(approach, solver, new char[][]{
                {'O', 'X', 'O', 'X', 'O'},
                {'X', 'O', 'X', 'O', 'X'},
                {'O', 'X', 'O', 'X', 'O'},
                {'X', 'O', 'X', 'O', 'X'},
                {'O', 'X', 'O', 'X', 'O'}
        });
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void fourCornersAndAnInteriorIslandAreDistinct(String approach, Consumer<char[][]> solver) {
        assertSolved(approach, solver, new char[][]{
                {'O', 'X', 'X', 'X', 'O'},
                {'X', 'O', 'O', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'O', 'O', 'X'},
                {'O', 'X', 'X', 'X', 'O'}
        });
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void repeatedCallsReuseSameSolverWithoutStateLeak(String approach, Consumer<char[][]> solver) {
        char[][] first = {{'X', 'O', 'X'}, {'X', 'O', 'X'}, {'X', 'X', 'X'}};
        char[][] second = {{'O', 'X', 'O'}, {'X', 'O', 'X'}, {'O', 'X', 'O'}};
        assertSolved(approach, solver, first);
        assertSolved(approach, solver, second);
        assertSolved(approach, solver, first);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void emptyAndNonemptyCallsCanBeInterleaved(String approach, Consumer<char[][]> solver) {
        assertSolved(approach, solver, new char[][]{});
        assertSolved(approach, solver, new char[][]{{'X', 'O'}, {'X', 'X'}});
        assertSolved(approach, solver, new char[][]{{}});
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void exhaustiveTwoByThreeBoardsMatchIndependentOracle(String approach, Consumer<char[][]> solver) {
        for (int mask = 0; mask < (1 << 6); mask++) {
            char[][] board = new char[2][3];
            for (int cell = 0; cell < 6; cell++) {
                board[cell / 3][cell % 3] = ((mask >>> cell) & 1) == 0 ? 'X' : 'O';
            }
            assertSolved(approach + " mask=" + mask, solver, board);
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void seededSmallBoardsMatchIndependentOracle(String approach, Consumer<char[][]> solver) {
        Random random = new Random(130_2026L);
        for (int sample = 0; sample < 120; sample++) {
            int rows = 1 + random.nextInt(8);
            int columns = 1 + random.nextInt(8);
            char[][] board = new char[rows][columns];
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    board[row][column] = random.nextBoolean() ? 'O' : 'X';
                }
            }
            assertSolved(approach + " sample=" + sample, solver, board);
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void maximumOfficialSingleRowIsUnchanged(String approach, Consumer<char[][]> solver) {
        char[][] board = new char[1][200];
        for (int column = 0; column < board[0].length; column++) {
            board[0][column] = column % 3 == 0 ? 'O' : 'X';
        }
        assertSolved(approach, solver, board);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void maximumOfficialSingleColumnIsUnchanged(String approach, Consumer<char[][]> solver) {
        char[][] board = new char[200][1];
        for (int row = 0; row < board.length; row++) {
            board[row][0] = row % 3 == 0 ? 'O' : 'X';
        }
        assertSolved(approach, solver, board);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    void maximumOfficialCheckerboardIsProcessed(String approach, Consumer<char[][]> solver) {
        char[][] board = new char[200][200];
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column] = (row + column) % 2 == 0 ? 'O' : 'X';
            }
        }
        assertSolved(approach, solver, board);
    }

    @Test
    void bothApproachesProduceTheSameIndependentResultOnAComplexBoard() {
        char[][] original = {
                {'O', 'X', 'X', 'X', 'X', 'O', 'X'},
                {'X', 'O', 'O', 'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'O', 'O', 'O', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X'}
        };
        char[][] expected = expectedByFloodFill(original);
        char[][] bfsBoard = copy(original);
        char[][] dfsBoard = copy(original);
        new Solve_130().solve(bfsBoard);
        new Solve_130().solveDfs(dfsBoard);
        assertArrayEquals(expected, bfsBoard);
        assertArrayEquals(expected, dfsBoard);
        assertArrayEquals(original, new char[][]{
                {'O', 'X', 'X', 'X', 'X', 'O', 'X'},
                {'X', 'O', 'O', 'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'O', 'O', 'O', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X'}
        });
    }

    private static void assertSolved(String approach, Consumer<char[][]> solver, char[][] original) {
        char[][] actual = copy(original);
        char[][] expected = expectedByFloodFill(original);
        solver.accept(actual);
        assertArrayEquals(expected, actual, approach);
    }

    /**
     * Marks each component independently and captures it only when no member is on an edge.
     * This oracle does not use the production algorithm's temporary marker or traversal order.
     */
    private static char[][] expectedByFloodFill(char[][] original) {
        char[][] expected = copy(original);
        if (expected.length == 0 || expected[0].length == 0) {
            return expected;
        }
        int rows = expected.length;
        int columns = expected[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (expected[row][column] != 'O' || visited[row][column]) {
                    continue;
                }
                Queue<int[]> queue = new ArrayDeque<>();
                Queue<int[]> component = new ArrayDeque<>();
                queue.add(new int[]{row, column});
                visited[row][column] = true;
                boolean touchesBoundary = false;
                while (!queue.isEmpty()) {
                    int[] cell = queue.remove();
                    component.add(cell);
                    int currentRow = cell[0];
                    int currentColumn = cell[1];
                    touchesBoundary |= currentRow == 0 || currentRow == rows - 1
                            || currentColumn == 0 || currentColumn == columns - 1;
                    for (int[] direction : directions) {
                        int nextRow = currentRow + direction[0];
                        int nextColumn = currentColumn + direction[1];
                        if (nextRow >= 0 && nextRow < rows && nextColumn >= 0 && nextColumn < columns
                                && !visited[nextRow][nextColumn] && expected[nextRow][nextColumn] == 'O') {
                            visited[nextRow][nextColumn] = true;
                            queue.add(new int[]{nextRow, nextColumn});
                        }
                    }
                }
                if (!touchesBoundary) {
                    while (!component.isEmpty()) {
                        int[] cell = component.remove();
                        expected[cell[0]][cell[1]] = 'X';
                    }
                }
            }
        }
        return expected;
    }

    private static char[][] copy(char[][] board) {
        char[][] result = new char[board.length][];
        for (int row = 0; row < board.length; row++) {
            result[row] = board[row].clone();
        }
        return result;
    }
}

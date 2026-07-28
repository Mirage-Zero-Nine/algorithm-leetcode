package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.function.Consumer;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
    public void testHappyCases(String approach, Consumer<char[][]> solver) {
        char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        solver.accept(board);
        assertArrayEquals(new char[]{'X', 'X', 'X', 'X'}, board[0]);
        assertArrayEquals(new char[]{'X', 'X', 'X', 'X'}, board[1]);
        assertArrayEquals(new char[]{'X', 'O', 'X', 'X'}, board[3]);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    public void testNegativeAndEdgeCases(String approach, Consumer<char[][]> solver) {
        char[][] board = {{'O'}};
        solver.accept(board);
        assertArrayEquals(new char[]{'O'}, board[0]);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    public void testLargeCase(String approach, Consumer<char[][]> solver) {
        char[][] board = {
            {'X', 'O', 'X', 'O', 'X'},
            {'O', 'X', 'O', 'X', 'O'},
            {'X', 'O', 'X', 'O', 'X'},
            {'O', 'X', 'O', 'X', 'O'},
            {'X', 'O', 'X', 'O', 'X'}
        };
        solver.accept(board);
        // Border O's remain, interior O's become X
        assertArrayEquals(new char[]{'X', 'O', 'X', 'O', 'X'}, board[0]);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    public void testEmptyBoardNoCrash(String approach, Consumer<char[][]> solver) {
        char[][] board = {};
        solver.accept(board);
        assertArrayEquals(new char[][]{}, board);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    public void testAllXUnchanged(String approach, Consumer<char[][]> solver) {
        char[][] board = {
                {'X', 'X'},
                {'X', 'X'}
        };
        solver.accept(board);
        assertArrayEquals(new char[][]{
                {'X', 'X'},
                {'X', 'X'}
        }, board);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    public void testAllOBorderConnectedRemain(String approach, Consumer<char[][]> solver) {
        char[][] board = {
                {'O', 'O', 'O'},
                {'O', 'O', 'O'},
                {'O', 'O', 'O'}
        };
        solver.accept(board);
        assertArrayEquals(new char[][]{
                {'O', 'O', 'O'},
                {'O', 'O', 'O'},
                {'O', 'O', 'O'}
        }, board);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    public void testSingleRowUnchanged(String approach, Consumer<char[][]> solver) {
        char[][] board = {
                {'X', 'O', 'O', 'X', 'O'}
        };
        solver.accept(board);
        assertArrayEquals(new char[][]{
                {'X', 'O', 'O', 'X', 'O'}
        }, board);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    public void testSingleColumnUnchanged(String approach, Consumer<char[][]> solver) {
        char[][] board = {
                {'X'},
                {'O'},
                {'O'},
                {'X'}
        };
        solver.accept(board);
        assertArrayEquals(new char[][]{
                {'X'},
                {'O'},
                {'O'},
                {'X'}
        }, board);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    public void testInteriorRegionCaptured(String approach, Consumer<char[][]> solver) {
        char[][] board = {
                {'X', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'O', 'O', 'X'},
                {'X', 'X', 'X', 'X', 'X'}
        };
        solver.accept(board);
        assertArrayEquals(new char[][]{
                {'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X'}
        }, board);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    public void testGiantCheckerboard(String approach, Consumer<char[][]> solver) {
        int n = 20;
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = ((i + j) % 2 == 0) ? 'O' : 'X';
            }
        }
        solver.accept(board);
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if ((i + j) % 2 == 0) {
                    org.junit.jupiter.api.Assertions.assertEquals('X', board[i][j]);
                }
            }
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("solvers")
    public void testMixedBoundaryAndInteriorRegions(String approach, Consumer<char[][]> solver) {
        char[][] board = {
                {'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X', 'O', 'X'},
                {'X', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'O', 'O', 'X', 'X'},
                {'X', 'O', 'X', 'X', 'X', 'X'}
        };

        solver.accept(board);

        assertArrayEquals(new char[][]{
                {'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X', 'X', 'X'},
                {'X', 'X', 'O', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'O', 'X', 'X'},
                {'X', 'O', 'X', 'X', 'X', 'X'}
        }, board);
    }
}

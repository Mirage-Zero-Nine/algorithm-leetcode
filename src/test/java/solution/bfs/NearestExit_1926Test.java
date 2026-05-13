package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/10/11 20:51
 * Created with IntelliJ IDEA
 */

public class NearestExit_1926Test {

    private final NearestExit_1926 test = new NearestExit_1926();

    @Test
    public void test() {
        char[][] maze = {{'+', '+', '.', '+'}, {'.', '.', '.', '+'}, {'+', '+', '+', '.'}};
        assertEquals(1, test.nearestExit(maze, new int[]{1, 2}));
        maze = new char[][]{{'+', '+', '+'}, {'.', '.', '.'}, {'+', '+', '+'}};
        assertEquals(2, test.nearestExit(maze, new int[]{1, 0}));
    }

    @Test
    public void testInvalid() {
        char[][] maze = {{'.', '+'}};
        assertEquals(-1, test.nearestExit(maze, new int[]{0, 0}));
    }

    @Test
    public void testSingleCellMaze() {
        char[][] maze = {{'.'}};
        assertEquals(-1, test.nearestExit(maze, new int[]{0, 0}));
    }

    @Test
    public void testNoExitReachable() {
        char[][] maze = {
            {'+', '+', '+'},
            {'+', '.', '+'},
            {'+', '+', '+'}
        };
        assertEquals(-1, test.nearestExit(maze, new int[]{1, 1}));
    }

    @Test
    public void testNearestExitWithMultipleChoices() {
        char[][] maze = {
            {'.', '.', '.'},
            {'+', '.', '+'},
            {'.', '.', '.'}
        };
        assertEquals(1, test.nearestExit(maze, new int[]{1, 1}));
    }

    @Test
    public void testEntranceOnBoundaryDoesNotCountAsExit() {
        char[][] maze = {
            {'.', '.', '+'},
            {'+', '.', '+'},
            {'+', '.', '.'}
        };
        assertEquals(1, test.nearestExit(maze, new int[]{0, 0}));
    }

    @Test
    public void testAllOpenLargeBorder() {
        char[][] maze = {
            {'.', '.', '.', '.'},
            {'.', '.', '.', '.'},
            {'.', '.', '.', '.'},
            {'.', '.', '.', '.'}
        };
        assertEquals(1, test.nearestExit(maze, new int[]{2, 2}));
    }

    @Test
    public void testOneRowMaze() {
        char[][] maze = {{'.', '.', '.', '.'}};
        assertEquals(1, test.nearestExit(maze, new int[]{0, 1}));
    }

    @Test
    public void testOneColumnMaze() {
        char[][] maze = {{'.'}, {'.'}, {'.'}, {'.'}};
        assertEquals(1, test.nearestExit(maze, new int[]{1, 0}));
    }

    @Test
    public void testGiantMazeCorridor() {
        int n = 40;
        char[][] maze = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maze[i][j] = '+';
            }
        }
        for (int i = 1; i < n - 1; i++) {
            maze[i][1] = '.';
        }
        maze[n - 1][1] = '.';

        assertEquals(19, test.nearestExit(maze, new int[]{20, 1}));
    }
}

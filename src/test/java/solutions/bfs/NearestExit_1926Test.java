package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;
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
    public void testOfficialExampleOne() {
        assertNearest(1,
                new char[][]{{'+', '+', '.', '+'}, {'.', '.', '.', '+'}, {'+', '+', '+', '.'}},
                new int[]{1, 2});
    }

    @Test
    public void testOfficialExampleTwoEntranceOnBorder() {
        assertNearest(2,
                new char[][]{{'+', '+', '+'}, {'.', '.', '.'}, {'+', '+', '+'}},
                new int[]{1, 0});
    }

    @Test
    public void testOfficialExampleThreeHasNoExit() {
        assertNearest(-1, new char[][]{{'.', '+'}}, new int[]{0, 0});
    }

    @Test
    public void testSingleRowWithNoOtherExit() {
        char[][] maze = {{'.', '+'}};
        assertEquals(-1, test.nearestExit(maze, new int[]{0, 0}));
    }

    @Test
    public void testSingleCellMaze() {
        char[][] maze = {{'.'}};
        assertEquals(-1, test.nearestExit(maze, new int[]{0, 0}));
    }

    @Test
    public void testTwoByTwoEntranceCornerUsesOtherCorner() {
        assertNearest(1, new char[][]{{'.', '.'}, {'+', '+'}}, new int[]{0, 0});
    }

    @Test
    public void testOneRowMazeChoosesNearestEnd() {
        assertNearest(1, new char[][]{{'.', '.', '.', '.', '.'}}, new int[]{0, 2});
    }

    @Test
    public void testOneColumnMazeChoosesNearestEnd() {
        assertNearest(1, new char[][]{{'.'}, {'.'}, {'.'}, {'.'}, {'.'}}, new int[]{2, 0});
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
    public void testOpenInteriorWithAllBorderWallsHasNoExit() {
        char[][] maze = {
            {'+', '+', '+', '+', '+'},
            {'+', '.', '.', '.', '+'},
            {'+', '.', '.', '.', '+'},
            {'+', '.', '.', '.', '+'},
            {'+', '+', '+', '+', '+'}
        };
        assertNearest(-1, maze, new int[]{2, 2});
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
    public void testEquidistantExitsReturnTheirCommonShortestDistance() {
        char[][] maze = {
            {'+', '.', '+', '.', '+'},
            {'+', '.', '+', '.', '+'},
            {'.', '.', '.', '.', '.'},
            {'+', '.', '+', '.', '+'},
            {'+', '.', '+', '.', '+'}
        };
        assertNearest(2, maze, new int[]{2, 2});
    }

    @Test
    public void testNearestExitIsFoundBeforeLongerRoute() {
        char[][] maze = {
            {'+', '+', '+', '+', '+'},
            {'.', '.', '.', '+', '+'},
            {'+', '+', '.', '+', '+'},
            {'+', '.', '.', '.', '.'},
            {'+', '+', '+', '+', '+'}
        };
        assertNearest(2, maze, new int[]{1, 2});
    }

    @Test
    public void testCycleIsVisitedOnlyOnce() {
        char[][] maze = {
            {'.', '.', '.', '.', '.'},
            {'.', '+', '+', '+', '.'},
            {'.', '+', '.', '+', '.'},
            {'.', '+', '.', '+', '.'},
            {'.', '.', '.', '.', '.'}
        };
        assertNearest(2, maze, new int[]{2, 2});
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
    public void testEntranceOnBorderCanReachOnlyOtherBorderThroughInterior() {
        char[][] maze = {
            {'+', '+', '.', '+', '+'},
            {'+', '.', '.', '.', '+'},
            {'+', '+', '+', '.', '+'},
            {'+', '+', '+', '.', '+'}
        };
        assertNearest(4, maze, new int[]{0, 2});
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
    public void testRectangularMazeUsesHorizontalExit() {
        char[][] maze = {
            {'+', '+', '+', '+', '+', '+', '+'},
            {'.', '.', '.', '.', '.', '.', '.'},
            {'+', '+', '+', '+', '+', '+', '+'}
        };
        assertNearest(3, maze, new int[]{1, 3});
    }

    @Test
    public void testMaximumOneByHundredMaze() {
        char[][] maze = new char[1][100];
        Arrays.fill(maze[0], '.');
        assertNearest(1, maze, new int[]{0, 50});
    }

    @Test
    public void testMaximumHundredByOneMaze() {
        char[][] maze = new char[100][1];
        for (char[] row : maze) {
            row[0] = '.';
        }
        assertNearest(1, maze, new int[]{50, 0});
    }

    @Test
    public void testMaximumAllOpenMaze() {
        char[][] maze = new char[100][100];
        for (char[] row : maze) {
            Arrays.fill(row, '.');
        }
        assertNearest(49, maze, new int[]{50, 50});
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

    @Test
    public void testMaximumMazeWithSingleInteriorEntrance() {
        char[][] maze = new char[100][100];
        for (char[] row : maze) {
            Arrays.fill(row, '+');
        }
        maze[50][50] = '.';
        assertNearest(-1, maze, new int[]{50, 50});
    }

    @Test
    public void testResultMatchesIndependentOracleAcrossExhaustiveThreeByThreeMazes() {
        for (int mask = 0; mask < (1 << 9); mask++) {
            char[][] maze = new char[3][3];
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    maze[r][c] = ((mask & (1 << (r * 3 + c))) != 0) ? '.' : '+';
                }
            }
            maze[1][1] = '.';
            assertEquals(oracle(maze, new int[]{1, 1}),
                    test.nearestExit(copy(maze), new int[]{1, 1}), "mask=" + mask);
        }
    }

    @Test
    public void testResultMatchesIndependentOracleAcrossSeededRectangularMazes() {
        Random random = new Random(1926L);
        for (int sample = 0; sample < 150; sample++) {
            int rows = 3 + random.nextInt(8);
            int columns = 3 + random.nextInt(8);
            char[][] maze = new char[rows][columns];
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    maze[r][c] = random.nextDouble() < 0.62 ? '.' : '+';
                }
            }
            int entranceRow = 1 + random.nextInt(rows - 2);
            int entranceColumn = 1 + random.nextInt(columns - 2);
            maze[entranceRow][entranceColumn] = '.';
            int[] entrance = {entranceRow, entranceColumn};
            assertEquals(oracle(maze, entrance), test.nearestExit(copy(maze), entrance),
                    "sample=" + sample);
        }
    }

    @Test
    public void testEntranceArrayIsNotModified() {
        char[][] maze = {{'+', '.', '+'}, {'.', '.', '.'}, {'+', '+', '+'}};
        int[] entrance = {1, 1};
        int[] original = entrance.clone();
        test.nearestExit(maze, entrance);
        assertArrayEquals(original, entrance);
    }

    @Test
    public void testBfsMarksVisitedCellsInTheInputMaze() {
        char[][] maze = {{'+', '.', '+'}, {'+', '.', '+'}, {'+', '.', '+'}};
        assertEquals(1, test.nearestExit(maze, new int[]{1, 1}));
        assertEquals('x', maze[1][1]);
        assertEquals('x', maze[0][1]);
        assertEquals('x', maze[2][1]);
    }

    @Test
    public void testRepeatedCallsUseFreshMazeState() {
        char[][] first = {{'+', '.', '+'}, {'.', '.', '+'}, {'+', '+', '+'}};
        char[][] second = {{'+', '+', '+'}, {'.', '.', '.'}, {'+', '+', '+'}};
        assertEquals(1, test.nearestExit(first, new int[]{1, 1}));
        assertEquals(2, test.nearestExit(second, new int[]{1, 0}));
    }

    @Test
    public void testRepeatedCallWithSameMazeReflectsItsMutation() {
        char[][] maze = {{'+', '.', '+'}, {'.', '.', '+'}, {'+', '+', '+'}};
        assertEquals(1, test.nearestExit(maze, new int[]{1, 1}));
        assertEquals(-1, test.nearestExit(maze, new int[]{1, 1}));
    }

    @Test
    public void testEntranceAtBorderWithNoOtherOpenBorderIsNegative() {
        char[][] maze = {
            {'+', '.', '+', '+'},
            {'+', '.', '.', '+'},
            {'+', '+', '+', '+'}
        };
        assertNearest(-1, maze, new int[]{0, 1});
    }

    private void assertNearest(int expected, char[][] maze, int[] entrance) {
        assertEquals(expected, test.nearestExit(copy(maze), entrance.clone()));
    }

    private static int oracle(char[][] maze, int[] entrance) {
        int rows = maze.length;
        int columns = maze[0].length;
        int[][] distance = new int[rows][columns];
        for (int[] row : distance) {
            Arrays.fill(row, -1);
        }
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{entrance[0], entrance[1]});
        distance[entrance[0]][entrance[1]] = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] cell = queue.remove();
            int row = cell[0];
            int column = cell[1];
            if (distance[row][column] > 0
                    && (row == 0 || row == rows - 1 || column == 0 || column == columns - 1)) {
                return distance[row][column];
            }
            for (int[] direction : directions) {
                int nextRow = row + direction[0];
                int nextColumn = column + direction[1];
                if (nextRow >= 0 && nextRow < rows && nextColumn >= 0 && nextColumn < columns
                        && maze[nextRow][nextColumn] == '.' && distance[nextRow][nextColumn] == -1) {
                    distance[nextRow][nextColumn] = distance[row][column] + 1;
                    queue.add(new int[]{nextRow, nextColumn});
                }
            }
        }
        return -1;
    }

    private static char[][] copy(char[][] maze) {
        char[][] result = new char[maze.length][];
        for (int row = 0; row < maze.length; row++) {
            result[row] = maze[row].clone();
        }
        return result;
    }
}

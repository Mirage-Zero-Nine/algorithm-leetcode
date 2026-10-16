package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;

@ExtendWith(PacificAtlantic_417Test.IsolatedTestExtension.class)
public class PacificAtlantic_417Test {

    private final PacificAtlantic_417 test = new PacificAtlantic_417();

    @Test
    public void testHappyCases() {
        assertExact(new int[][]{
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        }, Set.of("0,4", "1,3", "1,4", "2,2", "3,0", "3,1", "4,0"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(Set.of(), asSet(test.pacificAtlantic(new int[][]{})));
        assertEquals(Set.of(), asSet(test.pacificAtlantic(null)));
        assertEquals(Set.of(), asSet(test.pacificAtlantic(new int[][]{{}, {}})));
        assertExact(new int[][]{{0, 1}}, Set.of("0,0", "0,1"));
        assertExact(new int[][]{{1, 1}, {0, 1}}, Set.of("0,0", "0,1", "1,0", "1,1"));
        assertExact(new int[][]{{0}}, Set.of("0,0"));
    }

    @Test
    public void testSingleton() {
        assertExact(new int[][]{{0}}, Set.of("0,0"));
    }

    @Test
    public void testLargeCase() {
        assertExact(new int[][]{{1, 1}, {1, 1}}, Set.of("0,0", "0,1", "1,0", "1,1"));
    }

    @Test
    public void testSingleRowAllReachBoth() {
        assertExact(new int[][]{{0, 1, 2, 3, 4, 5}});
    }

    @Test
    public void testSingleColumnAllReachBoth() {
        assertExact(new int[][]{{5}, {4}, {3}, {2}, {1}});
    }

    @Test
    public void testStrictlyIncreasingGrid() {
        assertExact(new int[][]{{1, 2, 3}, {2, 3, 4}, {3, 4, 5}},
                Set.of("0,2", "1,2", "2,0", "2,1", "2,2"));
    }

    @Test
    public void testStrictlyDecreasingGrid() {
        assertExact(new int[][]{{9, 8, 7}, {8, 7, 6}, {7, 6, 5}},
                Set.of("0,0", "0,1", "0,2", "1,0", "2,0"));
    }

    @Test
    public void testPlateauGridAllCellsReachBoth() {
        assertExact(new int[][]{{5, 5, 5}, {5, 5, 5}, {5, 5, 5}});
    }

    @Test
    public void testMixedHeightsSpecificCells() {
        assertExact(new int[][]{{10, 1, 10}, {1, 1, 1}, {10, 1, 10}});
    }

    @Test
    public void testHighRimBlocksInteriorNegativeCase() {
        assertExact(new int[][]{{9, 9, 9}, {9, 0, 9}, {9, 9, 9}},
                Set.of("0,0", "0,1", "0,2", "1,0", "1,2", "2,0", "2,1", "2,2"));
        assertFalse(asSet(test.pacificAtlantic(new int[][]{{9, 9, 9}, {9, 0, 9}, {9, 9, 9}}))
                .contains("1,1"));
    }

    @Test
    public void testZeroHeightValley() {
        assertExact(new int[][]{{0, 2, 0}, {2, 0, 2}, {0, 2, 0}});
    }

    @Test
    public void testMaximumHeightValues() {
        assertExact(new int[][]{{100000, 0}, {0, 100000}});
    }

    @Test
    public void testRectangularTwoByFiveGrid() {
        assertExact(new int[][]{{1, 4, 2, 3, 5}, {6, 0, 7, 1, 8}});
    }

    @Test
    public void testRectangularFiveByTwoGrid() {
        assertExact(new int[][]{{5, 1}, {4, 2}, {3, 3}, {2, 4}, {1, 5}});
    }

    @Test
    public void testDuplicateHeightsRequireNonDecreasingFlow() {
        assertExact(new int[][]{{3, 3, 1, 3}, {2, 3, 2, 3}, {3, 2, 2, 3}});
    }

    @Test
    public void testCheckerboardHeights() {
        assertExact(new int[][]{
                {0, 9, 0, 9, 0},
                {9, 0, 9, 0, 9},
                {0, 9, 0, 9, 0},
                {9, 0, 9, 0, 9}
        });
    }

    @Test
    public void testInteriorDetourAroundHigherNeighbor() {
        assertExact(new int[][]{
                {1, 5, 5, 5, 1},
                {1, 1, 9, 1, 1},
                {1, 1, 1, 1, 1}
        });
    }

    @Test
    public void testBoundaryCellsCanStillNeedTheOppositeOcean() {
        assertExact(new int[][]{
                {8, 1, 8, 1},
                {1, 1, 1, 8},
                {8, 1, 8, 1}
        });
    }

    @Test
    public void testInputIsNotMutated() {
        int[][] original = {{4, 1, 3}, {2, 9, 0}, {7, 2, 5}};
        int[][] input = copy(original);
        assertExact(input);
        assertMatrixEquals(original, input);
    }

    @Test
    public void testRepeatedCallsDoNotLeakVisitedState() {
        int[][] first = {{1, 2}, {3, 4}};
        int[][] second = {{4, 3, 2}, {1, 0, 5}};
        assertExact(first);
        assertExact(second);
        assertExact(first);
    }

    @Test
    public void testReturnedCoordinatesAreUniqueAndFresh() {
        int[][] grid = {{1, 2}, {3, 4}};
        List<List<Integer>> first = test.pacificAtlantic(grid);
        Set<String> expected = expectedByForwardFlow(grid);
        assertEquals(expected, asSet(first));
        assertEquals(first.size(), asSet(first).size());
        assertNotNull(first.get(0));
        List<List<Integer>> second = test.pacificAtlantic(grid);
        assertNotSame(first, second);
        assertEquals(expected, asSet(second));
    }

    @Test
    public void testExhaustiveBinaryTwoByThreeGrids() {
        for (int mask = 0; mask < (1 << 6); mask++) {
            int[][] grid = new int[2][3];
            for (int cell = 0; cell < 6; cell++) {
                grid[cell / 3][cell % 3] = (mask >>> cell) & 1;
            }
            assertExact(grid);
        }
    }

    @Test
    public void testSeededSmallGridsAgainstIndependentOracle() {
        Random random = new Random(417L);
        for (int sample = 0; sample < 40; sample++) {
            int rows = 1 + random.nextInt(6);
            int columns = 1 + random.nextInt(6);
            int[][] grid = new int[rows][columns];
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    grid[row][column] = random.nextInt(11);
                }
            }
            assertExact(grid);
        }
    }

    @Test
    public void testGiantGrid() {
        int[][] grid = new int[200][200];
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                grid[row][column] = 100000;
            }
        }
        Set<String> expected = new HashSet<>();
        for (int row = 0; row < 200; row++) {
            for (int column = 0; column < 200; column++) {
                expected.add(row + "," + column);
            }
        }
        assertExact(grid, expected);
    }

    @Test
    public void testMaximumMonotoneGrid() {
        int[][] grid = new int[200][200];
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                grid[row][column] = row + column;
            }
        }
        Set<String> expected = new HashSet<>();
        for (int row = 0; row < 200; row++) {
            for (int column = 0; column < 200; column++) {
                if (row == 199 || column == 199) {
                    expected.add(row + "," + column);
                }
            }
        }
        assertExact(grid, expected);
    }

    @Test
    public void testExhaustiveBinaryGridsUpToThreeByThree() {
        // Exhausts all 2^(r*c) binary grids for every 1 <= r,c <= 3: 682 inputs.
        // Larger dimensions and height alphabets are covered by the focused and seeded cases below.
        for (int rows = 1; rows <= 3; rows++) {
            for (int columns = 1; columns <= 3; columns++) {
                int cells = rows * columns;
                for (int mask = 0; mask < (1 << cells); mask++) {
                    int[][] grid = new int[rows][columns];
                    for (int cell = 0; cell < cells; cell++) {
                        grid[cell / columns][cell % columns] = (mask >>> cell) & 1;
                    }
                    assertExact(grid);
                }
            }
        }
    }

    private void assertExact(int[][] grid) {
        assertExact(grid, expectedByForwardFlow(grid));
    }

    private void assertExact(int[][] grid, Set<String> expected) {
        int[][] input = copy(grid);
        List<List<Integer>> result = test.pacificAtlantic(input);
        String description = "grid=" + Arrays.deepToString(grid);
        Set<String> actual = asSet(result, description);
        assertEquals(expected, actual, description);
        assertEquals(result.size(), actual.size(), "result must not contain duplicate coordinates; " + description);
        assertCoordinatesWithin(result, grid.length, grid.length == 0 ? 0 : grid[0].length, description);
        assertMatrixEquals(grid, input, description);
    }

    /**
     * Directly simulates downhill flow from each cell; independent of reverse ocean BFS.
     */
    private Set<String> expectedByForwardFlow(int[][] heights) {
        Set<String> expected = new HashSet<>();
        if (heights.length == 0) {
            return expected;
        }
        int rows = heights.length;
        int columns = heights[0].length;
        for (int startRow = 0; startRow < rows; startRow++) {
            for (int startColumn = 0; startColumn < columns; startColumn++) {
                boolean[][] visited = new boolean[rows][columns];
                Queue<int[]> queue = new ArrayDeque<>();
                queue.add(new int[]{startRow, startColumn});
                visited[startRow][startColumn] = true;
                boolean pacific = false;
                boolean atlantic = false;
                while (!queue.isEmpty()) {
                    int[] cell = queue.remove();
                    int row = cell[0];
                    int column = cell[1];
                    pacific |= row == 0 || column == 0;
                    atlantic |= row == rows - 1 || column == columns - 1;
                    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                    for (int[] direction : directions) {
                        int nextRow = row + direction[0];
                        int nextColumn = column + direction[1];
                        if (nextRow >= 0 && nextRow < rows && nextColumn >= 0 && nextColumn < columns
                                && !visited[nextRow][nextColumn]
                                && heights[nextRow][nextColumn] <= heights[row][column]) {
                            visited[nextRow][nextColumn] = true;
                            queue.add(new int[]{nextRow, nextColumn});
                        }
                    }
                }
                if (pacific && atlantic) {
                    expected.add(startRow + "," + startColumn);
                }
            }
        }
        return expected;
    }

    /**
     * Converts unordered coordinates into a canonical set while checking coordinate shape and bounds.
     */
    private Set<String> asSet(List<List<Integer>> coordinates) {
        return asSet(coordinates, "coordinates");
    }

    /**
     * Converts coordinates while attaching the input description to every structural assertion.
     */
    private Set<String> asSet(List<List<Integer>> coordinates, String description) {
        assertNotNull(coordinates, description);
        Set<String> result = new HashSet<>();
        for (List<Integer> coordinate : coordinates) {
            assertNotNull(coordinate, description);
            assertEquals(2, coordinate.size(), description);
            assertNotNull(coordinate.get(0), description);
            assertNotNull(coordinate.get(1), description);
            assertTrue(coordinate.get(0) >= 0, description);
            assertTrue(coordinate.get(1) >= 0, description);
            result.add(coordinate.get(0) + "," + coordinate.get(1));
        }
        return result;
    }

    /**
     * Verifies every returned coordinate belongs to the exact input dimensions for this scenario.
     */
    private void assertCoordinatesWithin(List<List<Integer>> coordinates, int rows, int columns, String description) {
        for (List<Integer> coordinate : coordinates) {
            assertTrue(coordinate.get(0) < rows, description);
            assertTrue(coordinate.get(1) < columns, description);
        }
    }

    private int[][] copy(int[][] matrix) {
        int[][] copy = new int[matrix.length][];
        for (int row = 0; row < matrix.length; row++) {
            copy[row] = matrix[row].clone();
        }
        return copy;
    }

    private void assertMatrixEquals(int[][] expected, int[][] actual) {
        assertMatrixEquals(expected, actual, "matrix");
    }

    /**
     * Verifies nonmutation while retaining the triggering input in failure output.
     */
    private void assertMatrixEquals(int[][] expected, int[][] actual, String description) {
        assertEquals(expected.length, actual.length, description);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row], description);
        }
    }

    /**
     * Runs each test method in a killable child JVM so a nonterminating solution cannot hang the suite.
     */
    public static final class IsolatedTestExtension implements InvocationInterceptor {
        private static final Duration DEADLINE = Duration.ofSeconds(10);

        @Override
        public void interceptTestMethod(Invocation<Void> invocation,
                                        ReflectiveInvocationContext<Method> context,
                                        ExtensionContext extensionContext) throws Throwable {
            Path output = Files.createTempFile("pacific-atlantic-test-", ".log");
            Process process = null;
            try {
                process = new ProcessBuilder(javaCommand(), "-cp", testClasspath(),
                        PacificAtlantic_417Test.class.getName() + "$Worker", context.getExecutable().getName())
                        .redirectOutput(output.toFile()).redirectErrorStream(true).start();
                if (!process.waitFor(DEADLINE.toMillis(), TimeUnit.MILLISECONDS)) {
                    process.destroyForcibly();
                    boolean dead = process.waitFor(2, TimeUnit.SECONDS);
                    throw new AssertionError("Timed out: " + context.getExecutable().getName()
                            + ", child terminated=" + dead + "\n" + Files.readString(output));
                }
                if (process.exitValue() != 0) {
                    throw new AssertionError("Child test failed: " + context.getExecutable().getName()
                            + "\n" + Files.readString(output));
                }
                invocation.skip();
            } finally {
                if (process != null && process.isAlive()) {
                    process.destroyForcibly();
                }
                if (process != null && !process.waitFor(2, TimeUnit.SECONDS)) {
                    throw new AssertionError("Child process did not terminate: " + context.getExecutable().getName());
                }
                Files.deleteIfExists(output);
            }
        }

        private static String javaCommand() {
            return Path.of(System.getProperty("java.home"), "bin", "java").toString();
        }

        private static String testClasspath() {
            String surefireClasspath = System.getProperty("surefire.test.class.path");
            return surefireClasspath == null ? System.getProperty("java.class.path") : surefireClasspath;
        }
    }

    /**
     * Child entry point that invokes only the selected test method, avoiding extension recursion.
     */
    public static final class Worker {
        public static void main(String[] args) throws Exception {
            if (args.length != 1) {
                throw new IllegalArgumentException("Expected one test method name");
            }
            PacificAtlantic_417Test test = new PacificAtlantic_417Test();
            Method method = PacificAtlantic_417Test.class.getDeclaredMethod(args[0]);
            try {
                method.invoke(test);
            } catch (InvocationTargetException exception) {
                Throwable cause = exception.getCause();
                if (cause instanceof Exception checked) throw checked;
                if (cause instanceof Error error) throw error;
                throw exception;
            }
        }
    }
}

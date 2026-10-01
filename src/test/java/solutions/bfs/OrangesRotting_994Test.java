package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

/**
 * Contract and regression tests for {@link OrangesRotting_994}.
 */
@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class OrangesRotting_994Test {

    private final IsolatedSolution test = new IsolatedSolution();

    @AfterEach
    public void stopWorker() throws Exception {
        test.close();
    }

    @Test
    public void testOfficialExamples() {
        assertEquals(4, test.orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
        assertEquals(-1, test.orangesRotting(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}}));
        assertEquals(0, test.orangesRotting(new int[][]{{0, 2}}));
    }

    @Test
    public void testDocumentedInvalidInputs() {
        assertEquals(-1, test.orangesRotting(null));
        assertEquals(-1, test.orangesRotting(new int[][]{}));
        assertEquals(-1, test.orangesRotting(new int[][]{{}}));
    }

    @Test
    public void testSingleEmptyCell() {
        assertEquals(0, test.orangesRotting(new int[][]{{0}}));
    }

    @Test
    public void testSingleFreshCellHasNoSource() {
        assertEquals(-1, test.orangesRotting(new int[][]{{1}}));
    }

    @Test
    public void testSingleRottenCellIsAlreadyComplete() {
        assertEquals(0, test.orangesRotting(new int[][]{{2}}));
    }

    @Test
    public void testAllEmptyGrid() {
        assertEquals(0, test.orangesRotting(new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        }));
    }

    @Test
    public void testAllRottenGrid() {
        assertEquals(0, test.orangesRotting(new int[][]{
                {2, 2},
                {2, 2}
        }));
    }

    @Test
    public void testAllFreshGridHasNoSource() {
        assertEquals(-1, test.orangesRotting(new int[][]{
                {1, 1},
                {1, 1}
        }));
    }

    @Test
    public void testOneFreshOrangeToTheRight() {
        assertEquals(1, test.orangesRotting(new int[][]{{2, 1}}));
    }

    @Test
    public void testOneFreshOrangeBelow() {
        assertEquals(1, test.orangesRotting(new int[][]{{2}, {1}}));
    }

    @Test
    public void testDiagonalOrangeIsNotAdjacent() {
        assertEquals(-1, test.orangesRotting(new int[][]{{2, 0}, {0, 1}}));
    }

    @Test
    public void testFreshOrangeSeparatedByEmptyCells() {
        assertEquals(-1, test.orangesRotting(new int[][]{{2, 0, 1}}));
    }

    @Test
    public void testMultipleSourcesPropagateSimultaneously() {
        assertEquals(2, test.orangesRotting(new int[][]{
                {2, 1, 1},
                {1, 1, 1},
                {1, 1, 2}
        }));
    }

    @Test
    public void testCenterSourceReachesFourCardinalNeighborsInOneMinute() {
        assertEquals(1, test.orangesRotting(new int[][]{
                {0, 1, 0},
                {1, 2, 1},
                {0, 1, 0}
        }));
    }

    @Test
    public void testSingleRowRequiresOneMinutePerStep() {
        assertEquals(9, test.orangesRotting(new int[][]{{2, 1, 1, 1, 1, 1, 1, 1, 1, 1}}));
    }

    @Test
    public void testSingleColumnRequiresOneMinutePerStep() {
        assertEquals(9, test.orangesRotting(new int[][]{
                {2}, {1}, {1}, {1}, {1}, {1}, {1}, {1}, {1}, {1}
        }));
    }

    @Test
    public void testRectangularGridUsesFourDirectionsOnly() {
        assertEquals(10, test.orangesRotting(new int[][]{
                {2, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        }));
    }

    @Test
    public void testZerosCreateAnUnreachableComponent() {
        assertEquals(-1, test.orangesRotting(new int[][]{
                {2, 0, 0},
                {0, 0, 0},
                {0, 0, 1}
        }));
    }

    @Test
    public void testOpenRingAroundEmptyCenter() {
        assertEquals(6, test.orangesRotting(new int[][]{
                {2, 1, 1, 1},
                {1, 0, 0, 1},
                {1, 0, 0, 1},
                {1, 1, 1, 1}
        }));
    }

    @Test
    public void testCheckerboardSourcesRotInOneMinute() {
        assertEquals(1, test.orangesRotting(new int[][]{
                {2, 1, 2, 1, 2},
                {1, 2, 1, 2, 1},
                {2, 1, 2, 1, 2},
                {1, 2, 1, 2, 1},
                {2, 1, 2, 1, 2}
        }));
    }

    @Test
    public void testMaximumOfficialSquareWithCornerSource() {
        int size = 10;
        int[][] grid = new int[size][size];
        for (int row = 0; row < size; row++) {
            Arrays.fill(grid[row], 1);
        }
        grid[0][0] = 2;
        assertEquals(18, test.orangesRotting(grid));
    }

    @Test
    public void testMaximumOfficialSquareWithCenterSource() {
        int size = 10;
        int[][] grid = new int[size][size];
        for (int row = 0; row < size; row++) {
            Arrays.fill(grid[row], 1);
        }
        grid[5][5] = 2;
        assertEquals(10, test.orangesRotting(grid));
    }

    @Test
    public void testIndependentOracleOnSeededOfficialSizeGrids() {
        Random random = new Random(994L);
        for (int sample = 0; sample < 40; sample++) {
            int rows = 1 + random.nextInt(10);
            int columns = 1 + random.nextInt(10);
            int[][] grid = new int[rows][columns];
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    grid[row][column] = random.nextInt(3);
                }
            }
            assertEquals(independentOracle(grid), test.orangesRotting(copy(grid)),
                    "sample=" + sample);
        }
    }

    @Test
    public void testInputMutationMarksOnlyFreshOrangesAsRotten() {
        int[][] grid = {
                {2, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        };
        assertEquals(4, test.orangesRotting(grid));
        assertArrayEquals(new int[]{2, 2, 0}, grid[0]);
        assertArrayEquals(new int[]{2, 2, 2}, grid[1]);
        assertArrayEquals(new int[]{0, 2, 2}, grid[2]);
    }

    @Test
    public void testRepeatedCallsOnSameInstanceAndFreshInputs() {
        int[][] first = {{2, 1, 1}};
        assertEquals(2, test.orangesRotting(first));
        assertEquals(0, test.orangesRotting(first));
        assertEquals(-1, test.orangesRotting(new int[][]{{1, 1}}));
        assertEquals(1, test.orangesRotting(new int[][]{{2, 1}}));
    }

    @Test
    public void testSeededGridWithGuaranteedReachability() {
        int size = 10;
        int[][] grid = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                grid[row][column] = (row + column) % 3 == 0 ? 2 : 1;
            }
        }
        assertEquals(independentOracle(grid), test.orangesRotting(copy(grid)));
    }

    @Test
    public void testExhaustiveTwoByTwoTernaryGridsAgainstIndependentOracle() {
        // There are only 3^4 = 81 two-by-two grids. Exhausting this small
        // domain exercises every placement of empty, fresh, and rotten cells,
        // including all source-free and disconnected combinations.
        for (int encoded = 0; encoded < 81; encoded++) {
            int value = encoded;
            int[][] grid = new int[2][2];
            for (int row = 0; row < 2; row++) {
                for (int column = 0; column < 2; column++) {
                    grid[row][column] = value % 3;
                    value /= 3;
                }
            }
            int expected = independentOracle(grid);
            assertEquals(expected, test.orangesRotting(copy(grid)),
                    "encoded two-by-two grid=" + encoded);
        }
    }

    /**
     * Delegates every solver call to a killable persistent child process.
     */
    private static final class IsolatedSolution extends OrangesRotting_994 {
        private Process process;
        private BufferedWriter input;
        private BufferedReader output;

        @Override
        public synchronized int orangesRotting(int[][] grid) {
            try {
                ensureWorker();
                input.write(encode(grid));
                input.newLine();
                input.flush();
                var executor = Executors.newSingleThreadExecutor();
                Future<String> response = executor.submit(output::readLine);
                String line;
                try {
                    line = response.get(2, TimeUnit.SECONDS);
                } finally {
                    response.cancel(true);
                    executor.shutdownNow();
                }
                if (line == null) {
                    throw new IllegalStateException("isolated worker exited");
                }
                return applyResponse(grid, line);
            } catch (Exception exception) {
                killWorker();
                throw new AssertionError("isolated solver invocation failed", exception);
            }
        }

        private void ensureWorker() throws IOException {
            if (process != null && process.isAlive()) {
                return;
            }
            process = new ProcessBuilder(javaExecutable(), "-cp", System.getProperty("java.class.path"),
                    Worker.class.getName(), "persistent").redirectErrorStream(true).start();
            input = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            output = new BufferedReader(new InputStreamReader(process.getInputStream()));
        }

        private static String javaExecutable() {
            return System.getProperty("java.home") + java.io.File.separator + "bin"
                    + java.io.File.separator + "java";
        }

        private void killWorker() {
            if (process != null) {
                process.destroyForcibly();
                try {
                    process.waitFor(2, TimeUnit.SECONDS);
                } catch (InterruptedException interrupted) {
                    Thread.currentThread().interrupt();
                }
            }
            process = null;
        }

        void close() {
            killWorker();
        }
    }

    private static String encode(int[][] grid) {
        if (grid == null) return "N";
        StringBuilder result = new StringBuilder().append(grid.length).append(':');
        for (int row = 0; row < grid.length; row++) {
            if (row > 0) result.append(';');
            for (int column = 0; column < grid[row].length; column++) {
                if (column > 0) result.append(',');
                result.append(grid[row][column]);
            }
        }
        return result.toString();
    }

    private static int applyResponse(int[][] grid, String response) {
        int separator = response.indexOf('|');
        int result = Integer.parseInt(response.substring(0, separator));
        int[][] mutated = Worker.decode(response.substring(separator + 1));
        if (grid != null && mutated != null) {
            for (int row = 0; row < grid.length; row++) {
                for (int column = 0; column < grid[row].length; column++) {
                    grid[row][column] = mutated[row][column];
                }
            }
        }
        return result;
    }

    /**
     * Child-process entry point; each line is one independent solver request.
     */
    public static final class Worker {
        public static void main(String[] args) throws Exception {
            if (args.length == 0 || !"persistent".equals(args[0])) return;
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
            OrangesRotting_994 solver = new OrangesRotting_994();
            String request;
            while ((request = input.readLine()) != null) {
                int[][] grid = decode(request);
                int result = solver.orangesRotting(grid);
                output.write(result + "|" + encode(grid));
                output.newLine();
                output.flush();
            }
        }

        private static int[][] decode(String encoded) {
            if ("N".equals(encoded)) return null;
            int separator = encoded.indexOf(':');
            int rows = Integer.parseInt(encoded.substring(0, separator));
            String body = encoded.substring(separator + 1);
            if (rows == 0) return new int[0][];
            String[] encodedRows = body.split(";", -1);
            int[][] grid = new int[rows][];
            for (int row = 0; row < rows; row++) {
                if (encodedRows[row].isEmpty()) {
                    grid[row] = new int[0];
                } else {
                    String[] cells = encodedRows[row].split(",");
                    grid[row] = new int[cells.length];
                    for (int column = 0; column < cells.length; column++) {
                        grid[row][column] = Integer.parseInt(cells[column]);
                    }
                }
            }
            return grid;
        }
    }

    private static int independentOracle(int[][] grid) {
        int answer = 0;
        boolean hasFresh = false;
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[0].length; column++) {
                if (grid[row][column] == 1) {
                    hasFresh = true;
                    int distance = distanceToRotten(grid, row, column);
                    if (distance < 0) {
                        return -1;
                    }
                    answer = Math.max(answer, distance);
                }
            }
        }
        return hasFresh ? answer : 0;
    }

    private static int distanceToRotten(int[][] grid, int startRow, int startColumn) {
        int rows = grid.length;
        int columns = grid[0].length;
        int[][] distance = new int[rows][columns];
        for (int[] row : distance) {
            Arrays.fill(row, -1);
        }
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startRow, startColumn});
        distance[startRow][startColumn] = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] current = queue.remove();
            if (grid[current[0]][current[1]] == 2) {
                return distance[current[0]][current[1]];
            }
            for (int[] direction : directions) {
                int nextRow = current[0] + direction[0];
                int nextColumn = current[1] + direction[1];
                if (nextRow >= 0 && nextRow < rows && nextColumn >= 0 && nextColumn < columns
                        && grid[nextRow][nextColumn] != 0 && distance[nextRow][nextColumn] < 0) {
                    distance[nextRow][nextColumn] = distance[current[0]][current[1]] + 1;
                    queue.add(new int[]{nextRow, nextColumn});
                }
            }
        }
        return -1;
    }

    private static int[][] copy(int[][] grid) {
        int[][] result = new int[grid.length][];
        for (int row = 0; row < grid.length; row++) {
            result[row] = grid[row].clone();
        }
        return result;
    }
}

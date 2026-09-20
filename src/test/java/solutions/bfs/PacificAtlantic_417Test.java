package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;

/** Tests four-directional flow independently of the reverse-edge BFS implementation. */
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
        List<int[]> first = test.pacificAtlantic(grid);
        Set<String> expected = expectedByForwardFlow(grid);
        assertEquals(expected, asSet(first));
        assertEquals(first.size(), asSet(first).size());
        assertNotNull(first.get(0));
        first.get(0)[0] = 99;
        assertEquals(expected, asSet(test.pacificAtlantic(grid)));
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
        List<int[]> result = test.pacificAtlantic(grid);
        assertEquals(40000, result.size());
        assertEquals(40000, asSet(result).size());
        assertTrue(asSet(result).contains("199,199"));
    }

    @Test
    public void testMaximumMonotoneGrid() {
        int[][] grid = new int[200][200];
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                grid[row][column] = row + column;
            }
        }
        List<int[]> result = test.pacificAtlantic(grid);
        assertEquals(399, result.size());
        assertEquals(399, asSet(result).size());
        assertTrue(asSet(result).contains("0,199"));
        assertTrue(asSet(result).contains("199,0"));
        assertFalse(asSet(result).contains("100,100"));
    }

    private void assertExact(int[][] grid) {
        assertExact(grid, expectedByForwardFlow(grid));
    }

    private void assertExact(int[][] grid, Set<String> expected) {
        int[][] input = copy(grid);
        List<int[]> result = test.pacificAtlantic(input);
        Set<String> actual = asSet(result);
        assertEquals(expected, actual);
        assertEquals(result.size(), actual.size(), "result must not contain duplicate coordinates");
        assertMatrixEquals(grid, input);
    }

    /** Directly simulates downhill flow from each cell; independent of reverse ocean BFS. */
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

    private Set<String> asSet(List<int[]> coordinates) {
        Set<String> result = new HashSet<>();
        for (int[] coordinate : coordinates) {
            assertNotNull(coordinate);
            assertEquals(2, coordinate.length);
            assertTrue(coordinate[0] >= 0 && coordinate[0] < 200);
            assertTrue(coordinate[1] >= 0 && coordinate[1] < 200);
            result.add(coordinate[0] + "," + coordinate[1]);
        }
        return result;
    }

    private int[][] copy(int[][] matrix) {
        int[][] copy = new int[matrix.length][];
        for (int row = 0; row < matrix.length; row++) {
            copy[row] = matrix[row].clone();
        }
        return copy;
    }

    private void assertMatrixEquals(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row]);
        }
    }
}

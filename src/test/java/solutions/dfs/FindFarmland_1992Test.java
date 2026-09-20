package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link FindFarmland_1992}.
 *
 * <p>The independent oracle discovers four-directional components and computes their bounding
 * boxes. The generated contract-valid boards contain rectangular, non-adjacent components, so
 * those boxes are the required answers. Every assertion normalizes output because LeetCode does
 * not require a particular group order.</p>
 */
public class FindFarmland_1992Test {

    private final FindFarmland_1992 solution = new FindFarmland_1992();

    @Test
    void officialExampleOneIsReturned() {
        assertMatchesOracle(new int[][]{{1, 0, 0}, {0, 1, 1}, {0, 1, 1}});
    }

    @Test
    void officialExampleTwoAllFarmlandIsOneGroup() {
        assertMatchesOracle(new int[][]{{1, 1}, {1, 1}});
    }

    @Test
    void officialExampleThreeHasNoGroups() {
        assertMatchesOracle(new int[][]{{0}});
    }

    @Test
    void singletonFarmlandAtTheOnlyCellHasTheSameCorners() {
        assertMatchesOracle(new int[][]{{1}});
    }

    @Test
    void rectangularAllForestInputHasNoGroups() {
        assertMatchesOracle(new int[][]{{0, 0, 0}, {0, 0, 0}});
    }

    @Test
    void separatedGroupsOnOneRowHaveCorrectHorizontalBounds() {
        assertMatchesOracle(new int[][]{{1, 1, 0, 1, 0, 1, 1, 1, 0, 1}});
    }

    @Test
    void separatedGroupsOnOneColumnHaveCorrectVerticalBounds() {
        assertMatchesOracle(new int[][]{{1}, {1}, {0}, {1}, {0}, {1}, {1}, {1}, {0}, {1}});
    }

    @Test
    void severalRectanglesCanTouchDiagonallyWithoutMerging() {
        assertMatchesOracle(new int[][]{
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 1}
        });
    }

    @Test
    void groupsAlongEveryBorderAndCornerAreFound() {
        assertMatchesOracle(new int[][]{
                {1, 1, 0, 0, 1, 1},
                {1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0},
                {1, 1, 0, 0, 1, 1}
        });
    }

    @Test
    void mixedRectangleSizesAndSingleCellsRemainSeparate() {
        assertMatchesOracle(new int[][]{
                {1, 1, 1, 0, 0, 1, 1, 0},
                {1, 1, 1, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 1, 1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 1, 1}
        });
    }

    @Test
    void multipleSeparatedBlocksHaveIndependentCoordinates() {
        assertMatchesOracle(new int[][]{
                {1, 1, 0, 0, 1, 0, 0, 1},
                {1, 1, 0, 0, 1, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 1, 1, 0, 1, 1, 0},
                {1, 0, 1, 1, 0, 1, 1, 0}
        });
    }

    @Test
    void outputIsCheckedByCoordinatesRatherThanOnlyByGroupCount() {
        int[][] land = {{1, 0, 1, 1}, {0, 0, 0, 0}, {1, 1, 0, 1}};
        int[][] actual = solution.findFarmland(copy(land));
        assertRectanglesEqual(new int[][]{
                {0, 0, 0, 0}, {0, 2, 0, 3}, {2, 0, 2, 1}, {2, 3, 2, 3}
        }, actual);
    }

    @Test
    void visitedFarmlandIsConsumedAndForestIsLeftAsZero() {
        int[][] land = {{1, 1, 0}, {0, 1, 0}, {1, 0, 0}};
        solution.findFarmland(land);
        assertArrayEquals(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, land);
    }

    @Test
    void aSecondCallOnTheConsumedInputReturnsNoGroups() {
        int[][] land = {{1, 1}, {0, 0}};
        assertMatchesOracle(land);
        assertEquals(0, solution.findFarmland(land).length);
    }

    @Test
    void callsOnTheSameInstanceDoNotShareInputState() {
        assertMatchesOracle(new int[][]{{1, 0}, {0, 1}});
        assertMatchesOracle(new int[][]{{1, 1}, {1, 1}});
    }

    @Test
    void nullInputUsesTheImplementationSupportedEmptyResult() {
        assertEquals(0, solution.findFarmland(null).length);
    }

    @Test
    void zeroRowInputUsesTheImplementationSupportedEmptyResult() {
        assertEquals(0, solution.findFarmland(new int[0][]).length);
    }

    @Test
    void zeroColumnInputUsesTheImplementationSupportedEmptyResult() {
        assertEquals(0, solution.findFarmland(new int[][]{{}}).length);
    }

    @Test
    void maximumSingleRectangleFitsTheDocumentedThreeHundredByThreeHundredLimit() {
        int[][] land = new int[300][300];
        for (int row = 0; row < land.length; row++) {
            Arrays.fill(land[row], 1);
        }
        int[][] actual = solution.findFarmland(land);
        assertRectanglesEqual(new int[][]{{0, 0, 299, 299}}, actual);
        assertAllZero(land);
    }

    @Test
    void maximumOneRowSupportsManySeparatedGroups() {
        int[][] land = new int[1][300];
        for (int column = 0; column < 300; column += 2) {
            land[0][column] = 1;
        }
        assertMatchesOracle(land);
    }

    @Test
    void maximumOneColumnSupportsManySeparatedGroups() {
        int[][] land = new int[300][1];
        for (int row = 0; row < 300; row += 2) {
            land[row][0] = 1;
        }
        assertMatchesOracle(land);
    }

    @Test
    void exhaustiveThreeByThreeValidBoardsMatchTheComponentOracle() {
        int validBoards = 0;
        for (int mask = 0; mask < (1 << 9); mask++) {
            int[][] land = new int[3][3];
            for (int cell = 0; cell < 9; cell++) {
                land[cell / 3][cell % 3] = (mask >>> cell) & 1;
            }
            int[][] expected = oracle(land);
            if (hasOnlyRectangularComponents(land, expected)) {
                assertMatchesOracle(land);
                validBoards++;
            }
        }
        assertTrue(validBoards > 100, "the exhaustive filter should exercise many valid boards");
    }

    @Test
    void seededSeparatedRectangleBoardsMatchTheIndependentOracle() {
        Random random = new Random(1992L);
        for (int sample = 0; sample < 150; sample++) {
            int rows = 1 + random.nextInt(12);
            int columns = 1 + random.nextInt(12);
            assertMatchesOracle(randomSeparatedRectangles(rows, columns, random));
        }
    }

    @Test
    void rectangleAtTheMaximumCoordinatesHasInclusiveBottomRightCorner() {
        int[][] land = new int[4][5];
        land[3][4] = 1;
        assertRectanglesEqual(new int[][]{{3, 4, 3, 4}}, solution.findFarmland(land));
    }

    @Test
    void independentFreshInstancesProduceTheSameAnswer() {
        int[][] land = {{1, 1, 0, 0}, {1, 1, 0, 1}};
        int[][] expected = oracle(land);
        int[][] first = new FindFarmland_1992().findFarmland(copy(land));
        int[][] second = new FindFarmland_1992().findFarmland(copy(land));
        assertRectanglesEqual(expected, first);
        assertRectanglesEqual(expected, second);
    }

    private void assertMatchesOracle(int[][] land) {
        int[][] expected = oracle(land);
        int[][] actual = solution.findFarmland(land);
        assertRectanglesEqual(expected, actual);
        assertAllZero(land);
    }

    private static void assertRectanglesEqual(int[][] expected, int[][] actual) {
        int[][] sortedExpected = normalize(expected);
        int[][] sortedActual = normalize(actual);
        assertEquals(sortedExpected.length, sortedActual.length);
        for (int index = 0; index < sortedExpected.length; index++) {
            assertArrayEquals(sortedExpected[index], sortedActual[index]);
        }
    }

    private static int[][] normalize(int[][] rectangles) {
        int[][] copy = new int[rectangles.length][];
        for (int index = 0; index < rectangles.length; index++) {
            assertEquals(4, rectangles[index].length, "every group must have four coordinates");
            copy[index] = rectangles[index].clone();
        }
        Arrays.sort(copy, Comparator.comparingInt((int[] rectangle) -> rectangle[0])
                .thenComparingInt(rectangle -> rectangle[1])
                .thenComparingInt(rectangle -> rectangle[2])
                .thenComparingInt(rectangle -> rectangle[3]));
        return copy;
    }

    private static int[][] oracle(int[][] land) {
        if (land == null || land.length == 0 || land[0] == null || land[0].length == 0) {
            return new int[0][0];
        }
        int rows = land.length;
        int columns = land[0].length;
        boolean[][] visited = new boolean[rows][columns];
        List<int[]> rectangles = new ArrayList<>();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (land[row][column] != 1 || visited[row][column]) {
                    continue;
                }
                int minRow = row;
                int maxRow = row;
                int minColumn = column;
                int maxColumn = column;
                Queue<int[]> queue = new ArrayDeque<>();
                queue.add(new int[]{row, column});
                visited[row][column] = true;
                while (!queue.isEmpty()) {
                    int[] cell = queue.remove();
                    minRow = Math.min(minRow, cell[0]);
                    maxRow = Math.max(maxRow, cell[0]);
                    minColumn = Math.min(minColumn, cell[1]);
                    maxColumn = Math.max(maxColumn, cell[1]);
                    for (int[] direction : directions) {
                        int nextRow = cell[0] + direction[0];
                        int nextColumn = cell[1] + direction[1];
                        if (nextRow >= 0 && nextRow < rows && nextColumn >= 0
                                && nextColumn < columns && land[nextRow][nextColumn] == 1
                                && !visited[nextRow][nextColumn]) {
                            visited[nextRow][nextColumn] = true;
                            queue.add(new int[]{nextRow, nextColumn});
                        }
                    }
                }
                rectangles.add(new int[]{minRow, minColumn, maxRow, maxColumn});
            }
        }
        return rectangles.toArray(new int[0][]);
    }

    private static boolean hasOnlyRectangularComponents(int[][] land, int[][] rectangles) {
        for (int[] rectangle : rectangles) {
            for (int row = rectangle[0]; row <= rectangle[2]; row++) {
                for (int column = rectangle[1]; column <= rectangle[3]; column++) {
                    if (land[row][column] != 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static int[][] randomSeparatedRectangles(int rows, int columns, Random random) {
        int[][] land = new int[rows][columns];
        int targetGroups = random.nextInt(8);
        int placed = 0;
        for (int attempt = 0; attempt < 2_000 && placed < targetGroups; attempt++) {
            int height = 1 + random.nextInt(Math.min(4, rows));
            int width = 1 + random.nextInt(Math.min(4, columns));
            int top = random.nextInt(rows - height + 1);
            int left = random.nextInt(columns - width + 1);
            if (!canPlace(land, top, left, height, width)) {
                continue;
            }
            for (int row = top; row < top + height; row++) {
                Arrays.fill(land[row], left, left + width, 1);
            }
            placed++;
        }
        return land;
    }

    private static boolean canPlace(int[][] land, int top, int left, int height, int width) {
        for (int row = Math.max(0, top - 1); row <= Math.min(land.length - 1, top + height); row++) {
            for (int column = Math.max(0, left - 1);
                    column <= Math.min(land[0].length - 1, left + width); column++) {
                if (land[row][column] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int[][] copy(int[][] land) {
        int[][] copy = new int[land.length][];
        for (int row = 0; row < land.length; row++) {
            copy[row] = land[row].clone();
        }
        return copy;
    }

    private static void assertAllZero(int[][] land) {
        for (int[] row : land) {
            for (int cell : row) {
                assertEquals(0, cell, "the DFS consumes every farmland cell");
            }
        }
    }
}

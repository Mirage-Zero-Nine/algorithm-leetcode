package solutions.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class IslandPerimeter_463Test {

    private final IslandPerimeter_463 test = new IslandPerimeter_463();

    @Test
    public void testLeetCodeExamples() {
        assertPerimeter(16, new int[][]{
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        });
        assertPerimeter(4, new int[][]{
                {1, 0, 0, 0}
        });
    }

    @Test
    public void testNullAndEmptyInputs() {
        assertEquals(0, test.islandPerimeter(null));
        assertEquals(0, test.islandPerimeter(new int[][]{}));
        assertEquals(0, test.islandPerimeter(new int[][]{{}}));
        assertEquals(0, test.islandPerimeter(new int[][]{null}));
    }

    @Test
    public void testSingleCell() {
        assertPerimeter(4, new int[][]{{1}});
        assertPerimeter(0, new int[][]{{0}});
    }

    @Test
    public void testSingleRow() {
        assertPerimeter(6, new int[][]{{1, 1}});
        assertPerimeter(8, new int[][]{{1, 1, 1}});
        assertPerimeter(12, new int[][]{{1, 1, 1, 1, 1}});
        assertPerimeter(14, new int[][]{{1, 1, 1, 1, 0, 1}});
        assertPerimeter(12, new int[][]{{0, 1, 1, 1, 1, 1}});
    }

    @Test
    public void testSingleColumn() {
        assertPerimeter(6, new int[][]{{1}, {1}});
        assertPerimeter(8, new int[][]{{1}, {1}, {1}});
        assertPerimeter(12, new int[][]{{1}, {1}, {1}, {1}, {1}});
        assertPerimeter(14, new int[][]{{1}, {1}, {1}, {0}, {1}, {1}});
    }

    @Test
    public void testAllWater() {
        assertPerimeter(0, new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        });
    }

    @Test
    public void testSolidRectangles() {
        assertPerimeter(4, new int[][]{{1}});
        assertPerimeter(6, new int[][]{{1, 1}});
        assertPerimeter(8, new int[][]{{1}, {1}, {1}});
        // Regression case: the original DFS missed the fourth edge in this cycle.
        assertPerimeter(8, new int[][]{
                {1, 1},
                {1, 1}
        });
        assertPerimeter(10, new int[][]{
                {1, 1, 1},
                {1, 1, 1}
        });
        assertPerimeter(12, new int[][]{
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        });
    }

    @Test
    public void testDisconnectedIslandsAreAdded() {
        assertPerimeter(8, new int[][]{
                {1, 0, 1}
        });
        assertPerimeter(16, new int[][]{
                {1, 0, 1},
                {0, 0, 0},
                {1, 0, 1}
        });
        assertPerimeter(16, new int[][]{
                {1, 1, 0, 1, 1},
                {1, 1, 0, 1, 1}
        });
    }

    @Test
    public void testDiagonalCellsAreNotConnected() {
        assertPerimeter(8, new int[][]{
                {1, 0},
                {0, 1}
        });
        assertPerimeter(20, new int[][]{
                {1, 0, 1},
                {0, 1, 0},
                {1, 0, 1}
        });
    }

    @Test
    public void testIrregularConnectedShapes() {
        assertPerimeter(8, new int[][]{
                {1, 1},
                {1, 0}
        });
        assertPerimeter(12, new int[][]{
                {1, 1, 1},
                {0, 1, 0},
                {0, 1, 0}
        });
        assertPerimeter(12, new int[][]{
                {0, 1, 0},
                {1, 1, 1},
                {0, 1, 0}
        });
        assertPerimeter(16, new int[][]{
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        });
        assertPerimeter(14, new int[][]{
                {1, 1, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 1, 1}
        });
        assertPerimeter(20, new int[][]{
                {1, 1, 1, 1},
                {1, 0, 0, 1},
                {1, 1, 1, 1}
        });
    }

    @Test
    public void testInteriorWaterAndMultipleHoles() {
        assertPerimeter(32, new int[][]{
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        });
        assertPerimeter(32, new int[][]{
                {1, 1, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}
        });
    }

    @Test
    public void testLandTouchingGridEdges() {
        assertPerimeter(10, new int[][]{
                {1, 1, 1},
                {0, 1, 0}
        });
        assertPerimeter(12, new int[][]{
                {0, 1, 0},
                {1, 1, 1},
                {0, 1, 0}
        });
        assertPerimeter(20, new int[][]{
                {1, 1, 1, 1},
                {1, 0, 0, 1},
                {1, 1, 1, 1}
        });
    }

    @Test
    public void testInputIsNotMutatedAndInstanceIsReusable() {
        int[][] grid = {
                {1, 1, 0},
                {0, 1, 0}
        };
        int[][] original = copyGrid(grid);

        assertEquals(8, test.islandPerimeter(grid));
        assertGridEquals(original, grid);
        assertEquals(8, test.islandPerimeter(grid));
        assertGridEquals(original, grid);
    }

    @Test
    public void testLargeConnectedGrid() {
        int size = 100;
        int[][] grid = new int[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(grid[i], 1);
        }

        assertEquals(4 * size, test.islandPerimeter(grid));
    }

    @Test
    public void testExhaustiveAllBinaryGridsUpToFourByFour() {
        for (int rows = 1; rows <= 4; rows++) {
            for (int columns = 1; columns <= 4; columns++) {
                int cells = rows * columns;
                int combinations = 1 << cells;
                for (int mask = 0; mask < combinations; mask++) {
                    int[][] grid = fromMask(rows, columns, mask);
                    assertEquals(perimeterByDefinition(grid), test.islandPerimeter(grid),
                            "rows=" + rows + ", columns=" + columns + ", mask=" + mask);
                }
            }
        }
    }

    @Test
    public void testDeterministicRandomGrids() {
        Random random = new Random(463L);
        for (int caseNumber = 0; caseNumber < 500; caseNumber++) {
            int rows = 1 + random.nextInt(15);
            int columns = 1 + random.nextInt(15);
            int[][] grid = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    grid[i][j] = random.nextBoolean() ? 1 : 0;
                }
            }

            assertEquals(perimeterByDefinition(grid), test.islandPerimeter(grid),
                    "random case=" + caseNumber);
        }
    }

    @Test
    public void testBoundaryCasesWithDifferentLandDensity() {
        assertPerimeter(22, new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        });
        assertPerimeter(40, new int[][]{
                {1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1, 0, 1}
        });
        assertPerimeter(24, new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        });
    }

    private void assertPerimeter(int expected, int[][] grid) {
        assertEquals(expected, test.islandPerimeter(grid));
    }

    private int[][] fromMask(int rows, int columns, int mask) {
        int[][] grid = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int bit = i * columns + j;
                grid[i][j] = (mask & (1 << bit)) == 0 ? 0 : 1;
            }
        }
        return grid;
    }

    private int perimeterByDefinition(int[][] grid) {
        int perimeter = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }
                for (int[] direction : directions) {
                    int nextI = i + direction[0];
                    int nextJ = j + direction[1];
                    if (nextI < 0 || nextI >= grid.length || nextJ < 0 || nextJ >= grid[0].length
                            || grid[nextI][nextJ] == 0) {
                        perimeter++;
                    }
                }
            }
        }
        return perimeter;
    }

    private int[][] copyGrid(int[][] grid) {
        return Arrays.stream(grid).map(int[]::clone).toArray(int[][]::new);
    }

    private void assertGridEquals(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i]);
        }
    }
}

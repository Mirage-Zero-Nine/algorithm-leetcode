package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UniquePathsWithObstacles_63Test {

    private final UniquePathsWithObstacles_63 test = new UniquePathsWithObstacles_63();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.uniquePathsWithObstacles(new int[][]{{1}}));
        assertEquals(1, test.uniquePathsWithObstacles(new int[][]{{0}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.uniquePathsWithObstacles(new int[][]{{0, 0}, {1, 0}}));
    }

    @Test
    public void testNoObstacles() {
        assertEquals(6, test.uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
    }

    @Test
    public void testBlockedEnd() {
        assertEquals(0, test.uniquePathsWithObstacles(new int[][]{{0, 0}, {0, 1}}));
    }

    @Test
    public void testBlockedStart() {
        assertEquals(0, test.uniquePathsWithObstacles(new int[][]{{1, 0}, {0, 0}}));
    }

    @Test
    public void testSingleRow() {
        assertEquals(1, test.uniquePathsWithObstacles(new int[][]{{0, 0, 0, 0}}));
    }

    @Test
    public void testSingleRowBlocked() {
        assertEquals(0, test.uniquePathsWithObstacles(new int[][]{{0, 1, 0, 0}}));
    }

    @Test
    public void testSingleColumn() {
        assertEquals(1, test.uniquePathsWithObstacles(new int[][]{{0}, {0}, {0}}));
    }

    @Test
    public void testCompactMethod() {
        assertEquals(2, test.compact(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));
    }

    @Test
    public void testGiantGrid() {
        int[][] grid = new int[10][10];
        int result = test.uniquePathsWithObstacles(grid);
        assertEquals(48620, result);
    }

    @Test
    public void testFullyBlockedMiddle() {
        assertEquals(0, test.uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {1, 1, 1}, {0, 0, 0}}));
    }
}

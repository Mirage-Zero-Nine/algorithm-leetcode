package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

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

    @Test
    public void test1x1NotBlocked() {
        assertEquals(1, test.uniquePathsWithObstacles(new int[][]{{0}}));
    }

    @Test
    public void test1x1Blocked() {
        assertEquals(0, test.uniquePathsWithObstacles(new int[][]{{1}}));
    }

    @Test
    public void testStartBlocked3x3() {
        assertEquals(0, test.uniquePathsWithObstacles(new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
    }

    @Test
    public void testEndBlocked3x3() {
        assertEquals(0, test.uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 1}}));
    }

    @Test
    public void test1xNRowNoObstacles() {
        assertEquals(1, test.uniquePathsWithObstacles(new int[][]{{0, 0, 0, 0, 0, 0, 0}}));
    }

    @Test
    public void testMx1ColNoObstacles() {
        assertEquals(1, test.uniquePathsWithObstacles(new int[][]{{0}, {0}, {0}, {0}, {0}}));
    }

    @Test
    public void testLeetCodeExample2x2() {
        assertEquals(1, test.uniquePathsWithObstacles(new int[][]{{0, 1}, {0, 0}}));
    }

    @Test
    public void testAllClear4x4() {
        // C(4+4-2, 4-1) = C(6,3) = 20
        assertEquals(20, test.uniquePathsWithObstacles(new int[4][4]));
    }

    @Test
    public void testWallColumnBlockingEntirely() {
        // Column 1 is all blocked, no path from left to right
        int[][] grid = {
                {0, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
        };
        assertEquals(0, test.uniquePathsWithObstacles(grid));
    }

    @Test
    public void testLargeGrid50x50Seed42() {
        Random rand = new Random(42L);
        int[][] grid = new int[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                grid[i][j] = rand.nextDouble() < 0.2 ? 1 : 0;
            }
        }
        grid[0][0] = 0;
        grid[49][49] = 0;
        int result = test.uniquePathsWithObstacles(grid);
        assertTrue(result >= 0);
        // Cross-check with compact method
        assertEquals(test.compact(grid), result);
    }

    @Test
    public void testPropertyResultLeqUniquePathsWithoutObstacles() {
        // For any grid with obstacles, result <= result of same-size grid without obstacles
        Random rand = new Random(42L);
        int[][] grid = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grid[i][j] = rand.nextDouble() < 0.15 ? 1 : 0;
            }
        }
        grid[0][0] = 0;
        grid[7][7] = 0;
        int withObstacles = test.uniquePathsWithObstacles(grid);
        int withoutObstacles = test.uniquePathsWithObstacles(new int[8][8]); // C(14,7) = 3432
        assertTrue(withObstacles <= withoutObstacles,
                "Paths with obstacles (" + withObstacles + ") should be <= paths without (" + withoutObstacles + ")");
    }
}

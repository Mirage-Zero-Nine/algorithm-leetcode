package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class MinPathSum_64Test {

    private final MinPathSum_64 test = new MinPathSum_64();

    @Test
    public void testHappyCases() {
        assertEquals(7, test.minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        assertEquals(12, test.minPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.minPathSum(new int[][]{{1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(21, test.minPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }

    @Test
    public void testSingleRow() {
        assertEquals(6, test.minPathSum(new int[][]{{1, 2, 3}}));
    }

    @Test
    public void testSingleColumn() {
        assertEquals(6, test.minPathSum(new int[][]{{1}, {2}, {3}}));
    }

    @Test
    public void testAllZeros() {
        assertEquals(0, test.minPathSum(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
    }

    @Test
    public void testAllOnes() {
        assertEquals(5, test.minPathSum(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}));
    }

    @Test
    public void testTwoByTwo() {
        assertEquals(3, test.minPathSum(new int[][]{{1, 2}, {1, 1}}));
    }

    @Test
    public void testNonSquareWide() {
        assertEquals(5, test.minPathSum(new int[][]{{1, 2, 3, 4}, {1, 1, 1, 1}}));
    }

    @Test
    public void testNonSquareTall() {
        assertEquals(5, test.minPathSum(new int[][]{{1, 1}, {2, 1}, {3, 1}, {4, 1}}));
    }

    @Test
    public void testGiantCase() {
        int size = 100;
        int[][] grid = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = 1;
            }
        }
        // Path from (0,0) to (99,99) requires 99 right + 99 down + start = 199 cells
        assertEquals(199, test.minPathSum(grid));
    }

    @Test
    public void testTwoByTwoEqualPaths() {
        // Two paths: right-down = 1+2+4=7, down-right = 1+3+4=8 ... not equal
        // Use {{1,3},{3,1}}: right-down=1+3+1=5, down-right=1+3+1=5
        assertEquals(5, test.minPathSum(new int[][]{{1, 3}, {3, 1}}));
    }

    @Test
    public void testTwoByTwoDifferentPaths() {
        // right-down: 1+10+3=14, down-right: 1+2+3=6 -> min=6
        assertEquals(6, test.minPathSum(new int[][]{{1, 10}, {2, 3}}));
    }

    @Test
    public void testNegativeValues() {
        // Implementation uses int arithmetic, negative values should still work
        // right-down: -1+2+(-3)=-2, down-right: -1+(-4)+(-3)=-8 -> min=-8
        assertEquals(-8, test.minPathSum(new int[][]{{-1, 2}, {-4, -3}}));
    }

    @Test
    public void testLargeValuesOverflowRisk() {
        int half = Integer.MAX_VALUE / 2;
        // 1x3 row: sum = half + half + half. Check no overflow issues in path logic.
        // Since it's a single row, result = sum of all cells
        int[][] grid = {{half, 1, 1}};
        assertEquals(half + 2, test.minPathSum(grid));
    }

    @Test
    public void testSingleRowLarge() {
        int n = 200;
        int[][] grid = new int[1][n];
        int sum = 0;
        for (int j = 0; j < n; j++) {
            grid[0][j] = j + 1;
            sum += j + 1;
        }
        assertEquals(sum, test.minPathSum(grid));
    }

    @Test
    public void testSingleColumnLarge() {
        int m = 200;
        int[][] grid = new int[m][1];
        int sum = 0;
        for (int i = 0; i < m; i++) {
            grid[i][0] = i + 1;
            sum += i + 1;
        }
        assertEquals(sum, test.minPathSum(grid));
    }

    @Test
    public void testLargeGrid50x50CrossCheck() {
        int m = 50, n = 50;
        int[][] grid = new int[m][n];
        Random rng = new Random(42L);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = rng.nextInt(100);
            }
        }
        // Reference DP computation
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) dp[i][0] = dp[i - 1][0] + grid[i][0];
        for (int j = 1; j < n; j++) dp[0][j] = dp[0][j - 1] + grid[0][j];
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
        assertEquals(dp[m - 1][n - 1], test.minPathSum(grid));
    }

    @Test
    public void testPropertyResultLowerBound() {
        // result >= sum of first row values (since that's one valid path for 1-row slice)
        // More precisely: result >= grid[0][0] always, and result <= any single valid path sum
        int[][] grid = {{3, 7, 2}, {1, 8, 4}, {5, 6, 1}};
        int result = test.minPathSum(grid);
        // Lower bound: at minimum we must traverse m-1+n-1+1 = m+n-1 cells, each >= 0
        // Also result >= grid[0][0] + grid[m-1][n-1] (must include start and end)
        assertTrue(result >= grid[0][0] + grid[2][2]);
        // Upper bound: first-row-then-down path = 3+7+2+4+1=17
        int naivePath = 3 + 7 + 2 + 4 + 1;
        assertTrue(result <= naivePath);
    }

    @Test
    public void testAllZerosLarge() {
        int[][] grid = new int[20][20]; // default 0
        assertEquals(0, test.minPathSum(grid));
    }
}

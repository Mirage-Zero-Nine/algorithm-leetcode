package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class CalculateMinimumHP_174Test {

    private final CalculateMinimumHP_174 test = new CalculateMinimumHP_174();

    @Test
    public void testHappyCases() {
        assertEquals(7, test.calculateMinimumHP(new int[][]{{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.calculateMinimumHP(new int[][]{{0}}));
        assertEquals(2, test.calculateMinimumHP(new int[][]{{-1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.calculateMinimumHP(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }

    @Test
    public void testSinglePositiveCell() {
        assertEquals(1, test.calculateMinimumHP(new int[][]{{100}}));
    }

    @Test
    public void testSingleLargeNegativeCell() {
        assertEquals(101, test.calculateMinimumHP(new int[][]{{-100}}));
    }

    @Test
    public void testSingleRow() {
        assertEquals(6, test.calculateMinimumHP(new int[][]{{-2, -3, 5}}));
    }

    @Test
    public void testSingleColumn() {
        assertEquals(4, test.calculateMinimumHP(new int[][]{{-1}, {-2}, {5}}));
    }

    @Test
    public void testAllNegative() {
        assertEquals(8, test.calculateMinimumHP(new int[][]{{-1, -2}, {-3, -4}}));
    }

    @Test
    public void testAllPositive() {
        assertEquals(1, test.calculateMinimumHP(new int[][]{{1, 1}, {1, 1}}));
    }

    @Test
    public void testGiantCase() {
        int[][] dungeon = new int[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                dungeon[i][j] = -1;
            }
        }
        // Path of length 99 cells, each -1, so need 99 + 1 = 100 HP
        assertEquals(100, test.calculateMinimumHP(dungeon));
    }

    @Test
    public void testAllZeros() {
        assertEquals(1, test.calculateMinimumHP(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
    }

    @Test
    public void testSingleRowAllNegative() {
        // Row [-3, -4, -5]: must traverse all, need 3+4+5+1 = 13
        assertEquals(13, test.calculateMinimumHP(new int[][]{{-3, -4, -5}}));
    }

    @Test
    public void testSingleColumnAllNegative() {
        // Column [-2, -3, -4]: must traverse all, need 2+3+4+1 = 10
        assertEquals(10, test.calculateMinimumHP(new int[][]{{-2}, {-3}, {-4}}));
    }

    @Test
    public void testPrincessCellPositive() {
        // Bottom-right is positive, offsets damage: [[-5, -5], [-5, 100]]
        // Path right-down: -5 + -5 + 100 = 90 net, but need to survive each step
        // Path down-right: -5 + -5 + 100 = same
        // dp[1][1] = max(0 - 100, 0) = 0; dp[0][1] = max(0 - (-5), 0) = 5; dp[1][0] = max(0 - (-5), 0) = 5
        // dp[0][0] = max(min(5,5) - (-5), 0) = 10; answer = 11
        assertEquals(11, test.calculateMinimumHP(new int[][]{{-5, -5}, {-5, 100}}));
    }

    @Test
    public void testPrincessCellNegative() {
        // Bottom-right is very negative: [[0, 0], [0, -10]]
        // dp[1][1] = max(0 - (-10), 0) = 10; dp[0][1] = max(10 - 0, 0) = 10; dp[1][0] = max(10 - 0, 0) = 10
        // dp[0][0] = max(min(10,10) - 0, 0) = 10; answer = 11
        assertEquals(11, test.calculateMinimumHP(new int[][]{{0, 0}, {0, -10}}));
    }

    @Test
    public void testVeryLargeNegativeCell() {
        // Single cell with extreme negative value
        assertEquals(1000001, test.calculateMinimumHP(new int[][]{{-1000000}}));
    }

    @Test
    public void testLargeGrid50x50Random() {
        Random rand = new Random(42L);
        int[][] dungeon = new int[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                dungeon[i][j] = rand.nextInt(201) - 100; // range [-100, 100]
            }
        }
        int result = test.calculateMinimumHP(dungeon);
        assertTrue(result >= 1, "Result must be at least 1, got: " + result);
    }

    @Test
    public void testResultAlwaysAtLeastOne() {
        // Property: no matter the grid content, result >= 1
        int[][] cases = {
                {100, 200, 300},
                {0, 0, 0},
                {-1, -2, -3},
                {Integer.MAX_VALUE / 2}
        };
        for (int[] row : cases) {
            int result = test.calculateMinimumHP(new int[][]{row});
            assertTrue(result >= 1, "Result must be >= 1 for row, got: " + result);
        }
    }

    @Test
    public void testAllNegative3x3() {
        // 3x3 all -1: optimal path length 5 cells, need 5+1=6
        assertEquals(6, test.calculateMinimumHP(new int[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}}));
    }
}

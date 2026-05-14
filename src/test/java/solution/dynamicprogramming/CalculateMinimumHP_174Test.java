package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}

package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxKilledEnemies_361Test {

    private final MaxKilledEnemies_361 test = new MaxKilledEnemies_361();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.maxKilledEnemies(new char[][]{{'0', 'E', '0', '0'}, {'E', '0', 'W', 'E'}, {'0', 'E', '0', '0'}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxKilledEnemies(new char[][]{}));
        assertEquals(0, test.maxKilledEnemies(new char[][]{{'W'}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(0, test.maxKilledEnemies(new char[][]{{'E', 'E', 'E'}, {'E', 'W', 'E'}, {'E', 'E', 'E'}}));
    }

    @Test
    public void testSingleEmpty() {
        assertEquals(0, test.maxKilledEnemies(new char[][]{{'0'}}));
    }

    @Test
    public void testSingleEnemy() {
        assertEquals(0, test.maxKilledEnemies(new char[][]{{'E'}}));
    }

    @Test
    public void testRowOnly() {
        assertEquals(2, test.maxKilledEnemies(new char[][]{{'E', '0', 'E'}}));
    }

    @Test
    public void testColumnOnly() {
        assertEquals(2, test.maxKilledEnemies(new char[][]{{'E'}, {'0'}, {'E'}}));
    }

    @Test
    public void testWallBlocking() {
        // Wall blocks bomb from reaching enemies on the other side
        assertEquals(1, test.maxKilledEnemies(new char[][]{{'E', 'W', '0', 'E'}}));
    }

    @Test
    public void testAllWalls() {
        assertEquals(0, test.maxKilledEnemies(new char[][]{{'W', 'W'}, {'W', 'W'}}));
    }

    @Test
    public void testGiantGrid() {
        int n = 50;
        char[][] grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = 'E';
            }
        }
        // Place one empty cell in center
        grid[25][25] = '0';
        // From center, row has 50 enemies minus 1 (the cell itself), col same
        // But actually row enemies = enemies in same row without wall = 49, col = 49, total = 98
        assertEquals(98, test.maxKilledEnemies(grid));
    }
}

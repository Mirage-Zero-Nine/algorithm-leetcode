package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindFarmland_1992Test {

    private final FindFarmland_1992 test = new FindFarmland_1992();

    @Test
    public void testHappyCases() {
        int[][] result = test.findFarmland(new int[][]{{1, 0, 0}, {0, 1, 1}, {0, 1, 1}});
        assertEquals(2, result.length);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.findFarmland(new int[][]{{0}}).length);
        assertArrayEquals(new int[][]{{0, 0, 0, 0}}, test.findFarmland(new int[][]{{1}}));
    }

    @Test
    public void testLargeCase() {
        int[][] result = test.findFarmland(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}});
        assertEquals(2, result.length);
    }

    @Test
    public void testAllFarmland() {
        int[][] result = test.findFarmland(new int[][]{{1, 1}, {1, 1}});
        assertEquals(1, result.length);
        assertArrayEquals(new int[]{0, 0, 1, 1}, result[0]);
    }

    @Test
    public void testAllForest() {
        int[][] result = test.findFarmland(new int[][]{{0, 0, 0}, {0, 0, 0}});
        assertEquals(0, result.length);
    }

    @Test
    public void testSingleRow() {
        int[][] result = test.findFarmland(new int[][]{{1, 1, 0, 1}});
        assertEquals(2, result.length);
    }

    @Test
    public void testSingleColumn() {
        int[][] result = test.findFarmland(new int[][]{{1}, {1}, {0}, {1}});
        assertEquals(2, result.length);
    }

    @Test
    public void testMultipleDisjointFarms() {
        int[][] land = {
            {1, 0, 1, 0},
            {0, 0, 0, 0},
            {1, 0, 1, 0}
        };
        int[][] result = test.findFarmland(land);
        assertEquals(4, result.length);
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, test.findFarmland(new int[][]{}).length);
    }

    @Test
    public void testGiantGrid() {
        // 50x50 grid with a large farmland block
        int[][] land = new int[50][50];
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                land[i][j] = 1;
            }
        }
        // another small block
        land[30][30] = 1;
        int[][] result = test.findFarmland(land);
        assertEquals(2, result.length);
    }
}

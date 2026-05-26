package solutions.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinCostToSupplyWater_1168Test {

    private final MinCostToSupplyWater_1168 test = new MinCostToSupplyWater_1168();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.minCostToSupplyWater(3, new int[]{1, 2, 2}, new int[][]{{1, 2, 1}, {2, 3, 1}}));
        assertEquals(2, test.minCostToSupplyWater(2, new int[]{1, 1}, new int[][]{{1, 2, 100}}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.minCostToSupplyWater(1, new int[]{1}, new int[][]{}));
        assertEquals(2, test.minCostToSupplyWater(2, new int[]{1, 1}, new int[][]{{1, 2, 1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.minCostToSupplyWater(4, new int[]{2, 2, 2, 2}, new int[][]{{1, 2, 1}, {2, 3, 1}, {3, 4, 1}}));
    }

    @Test
    public void testSingleHouseNoPipes() {
        assertEquals(5, test.minCostToSupplyWater(1, new int[]{5}, new int[][]{}));
    }

    @Test
    public void testAllWellsCheaperThanPipes() {
        // Each well costs 1, pipe costs 100 => build all wells = 3
        assertEquals(3, test.minCostToSupplyWater(3, new int[]{1, 1, 1}, new int[][]{{1, 2, 100}, {2, 3, 100}}));
    }

    @Test
    public void testPipeCheaperThanWell() {
        // Well costs: [1, 100], pipe cost 1 => build well at house1 (1) + pipe to house2 (1) = 2
        assertEquals(2, test.minCostToSupplyWater(2, new int[]{1, 100}, new int[][]{{1, 2, 1}}));
    }

    @Test
    public void testMultiplePipesBetweenHouses() {
        // Multiple pipes, MST picks cheapest
        assertEquals(3, test.minCostToSupplyWater(3, new int[]{1, 10, 10}, new int[][]{{1, 2, 1}, {1, 3, 1}, {2, 3, 5}}));
    }

    @Test
    public void testNoPipesAllWells() {
        assertEquals(6, test.minCostToSupplyWater(3, new int[]{1, 2, 3}, new int[][]{}));
    }

    @Test
    public void testStarTopology() {
        // House 1 has cheap well, all others connect to house 1 cheaply
        assertEquals(4, test.minCostToSupplyWater(4, new int[]{1, 100, 100, 100}, new int[][]{{1, 2, 1}, {1, 3, 1}, {1, 4, 1}}));
    }

    @Test
    public void testGiantCase() {
        int n = 100;
        int[] wells = new int[n];
        for (int i = 0; i < n; i++) wells[i] = 1000;
        // Chain of pipes each costing 1
        int[][] pipes = new int[n - 1][3];
        for (int i = 0; i < n - 1; i++) {
            pipes[i] = new int[]{i + 1, i + 2, 1};
        }
        // MST: one well (1000) + 99 pipes (99) = 1099
        assertEquals(1099, test.minCostToSupplyWater(n, wells, pipes));
    }
}

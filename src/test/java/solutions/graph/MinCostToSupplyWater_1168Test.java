package solutions.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinCostToSupplyWater_1168Test {

    @Test
    public void testThreeHouseNetworksAgainstExhaustiveSpanningTrees() {
        java.util.Random random = new java.util.Random(11682026L);
        for (int sample = 0; sample < 100; sample++) {
            int[] wells = {random.nextInt(20), random.nextInt(20), random.nextInt(20)};
            int[][] pipes = {{1, 2, random.nextInt(20)}, {1, 3, random.nextInt(20)}, {2, 3, random.nextInt(20)}};
            int[][] edges = {{0, 1, wells[0]}, {0, 2, wells[1]}, {0, 3, wells[2]}, pipes[0], pipes[1], pipes[2]};
            int expected = Integer.MAX_VALUE;
            for (int mask = 0; mask < 64; mask++) {
                if (Integer.bitCount(mask) != 3) continue;
                boolean[] reached = {true, false, false, false};
                int cost = 0;
                for (int i = 0; i < 6; i++) if ((mask & (1 << i)) != 0) cost += edges[i][2];
                for (int pass = 0; pass < 4; pass++)
                    for (int i = 0; i < 6; i++)
                        if ((mask & (1 << i)) != 0 && (reached[edges[i][0]] || reached[edges[i][1]]))
                            reached[edges[i][0]] = reached[edges[i][1]] = true;
                if (reached[1] && reached[2] && reached[3]) expected = Math.min(expected, cost);
            }
            assertEquals(expected, test.minCostToSupplyWater(3, wells, pipes), "network " + sample);
        }
    }

    @Test
    public void testDisconnectedPipeComponentsNeedSeparateWells() {
        assertEquals(12, test.minCostToSupplyWater(4, new int[]{9, 2, 7, 8},
                new int[][]{{1, 2, 1}, {3, 4, 2}}));
    }

    @Test
    public void testParallelPipesWithDifferentCosts() {
        assertEquals(4, test.minCostToSupplyWater(2, new int[]{3, 100},
                new int[][]{{1, 2, 50}, {2, 1, 1}, {1, 2, 20}}));
    }


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

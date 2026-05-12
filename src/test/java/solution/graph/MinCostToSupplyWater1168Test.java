package solution.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinCostToSupplyWater1168Test {

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
}

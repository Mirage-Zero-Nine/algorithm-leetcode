package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinCost_256Test {

    private final MinCost_256 test = new MinCost_256();

    @Test
    public void testHappyCases() {
        assertEquals(10, test.minCost(new int[][]{{17, 2, 17}, {16, 16, 5}, {14, 3, 19}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.minCost(new int[][]{}));
        assertEquals(1, test.minCost(new int[][]{{1, 2, 3}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(13, test.minCost(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }

    @Test
    public void testSingleHouse() {
        assertEquals(5, test.minCost(new int[][]{{5, 10, 15}}));
        assertEquals(3, test.minCost(new int[][]{{10, 3, 7}}));
    }

    @Test
    public void testTwoHouses() {
        assertEquals(3, test.minCost(new int[][]{{1, 2, 3}, {1, 2, 3}})); // pick 1 then 2 (different color)
    }

    @Test
    public void testAllSameCost() {
        assertEquals(6, test.minCost(new int[][]{{2, 2, 2}, {2, 2, 2}, {2, 2, 2}}));
    }

    @Test
    public void testFirstColorCheapest() {
        assertEquals(3, test.minCost(new int[][]{{1, 100, 100}, {100, 1, 100}, {1, 100, 100}}));
    }

    @Test
    public void testAlternatingPattern() {
        assertEquals(4, test.minCost(new int[][]{{1, 5, 5}, {5, 1, 5}, {1, 5, 5}, {5, 1, 5}}));
    }

    @Test
    public void testHighCosts() {
        assertEquals(300, test.minCost(new int[][]{{100, 200, 300}, {300, 100, 200}, {200, 300, 100}}));
    }

    @Test
    public void testGiantCase() {
        int[][] costs = new int[1000][3];
        for (int i = 0; i < 1000; i++) {
            costs[i][0] = 1;
            costs[i][1] = 2;
            costs[i][2] = 3;
        }
        // Alternating between color 0 (cost 1) and color 1 (cost 2)
        // 500 * 1 + 500 * 2 = 1500
        int result = test.minCost(costs);
        assertEquals(1500, result);
    }
}

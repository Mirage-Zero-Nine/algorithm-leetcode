package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinCostII_265Test {

    private final MinCostII_265 test = new MinCostII_265();

    @Test
    public void testHappyCases() {
        assertEquals(5, test.minCostII(new int[][]{{1, 5, 3}, {2, 9, 4}}));
        assertEquals(2, test.minCostII(new int[][]{{1, 3}, {2, 1}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.minCostII(new int[][]{}));
        assertEquals(1, test.minCostII(new int[][]{{1, 2, 3}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(13, test.minCostII(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }

    @Test
    public void testSingleColor() {
        assertEquals(5, test.minCostII(new int[][]{{5}}));
    }

    @Test
    public void testTwoHousesTwoColors() {
        assertEquals(2, test.minCostII(new int[][]{{1, 2}, {3, 1}}));
    }

    @Test
    public void testThreeHousesFourColors() {
        assertEquals(4, test.minCostII(new int[][]{{1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}}));
    }

    @Test
    public void testAllSameCost() {
        assertEquals(6, test.minCostII(new int[][]{{2, 2, 2}, {2, 2, 2}, {2, 2, 2}}));
    }

    @Test
    public void testForcedAlternation() {
        assertEquals(3, test.minCostII(new int[][]{{1, 100}, {100, 1}, {1, 100}}));
    }

    @Test
    public void testSingleHouseManyColors() {
        assertEquals(1, test.minCostII(new int[][]{{5, 3, 1, 2, 4}}));
    }

    @Test
    public void testGiantCase() {
        int n = 100;
        int k = 50;
        int[][] costs = new int[n][k];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                costs[i][j] = (i + j) % k + 1;
            }
        }
        int result = test.minCostII(costs);
        assertEquals(true, result > 0);
    }
}

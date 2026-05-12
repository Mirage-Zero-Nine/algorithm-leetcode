package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinCostII265Test {

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
}

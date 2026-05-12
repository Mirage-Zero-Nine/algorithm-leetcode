package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinCost256Test {

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
}

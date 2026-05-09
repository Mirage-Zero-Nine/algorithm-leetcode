package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculateMinimumHP174Test {

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
}

package solution.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TrapRainWater_407Test {

    private final TrapRainWater_407 test = new TrapRainWater_407();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.trapRainWater(new int[][]{
            {1, 4, 3, 1, 3, 2},
            {3, 2, 1, 3, 2, 4},
            {2, 3, 3, 2, 3, 1}
        }));
        assertEquals(7, test.trapRainWater(new int[][]{
            {3, 3, 3, 3, 3},
            {3, 2, 2, 2, 3},
            {3, 2, 1, 2, 3},
            {3, 3, 3, 3, 3}
        }));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.trapRainWater(new int[][]{{1, 2, 3}}));
        assertEquals(0, test.trapRainWater(new int[][]{{1}, {2}, {3}}));
        assertEquals(0, test.trapRainWater(new int[][]{
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        }));
    }

    @Test
    public void testLargeCase() {
        assertEquals(48, test.trapRainWater(new int[][]{
            {5, 5, 5, 5, 5, 5},
            {5, 1, 1, 1, 1, 5},
            {5, 1, 5, 5, 1, 5},
            {5, 1, 5, 5, 1, 5},
            {5, 1, 1, 1, 1, 5},
            {5, 5, 5, 5, 5, 5}
        }));
    }
}

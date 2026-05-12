package solution.unionfind;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinimumCost1135Test {

    private final MinimumCost_1135 test = new MinimumCost_1135();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.minimumCost(3, new int[][]{{1, 2, 5}, {1, 3, 6}, {2, 3, 1}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.minimumCost(4, new int[][]{{1, 2, 3}, {3, 4, 4}}));
        assertEquals(0, test.minimumCost(1, new int[][]{}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.minimumCost(4, new int[][]{{1, 2, 1}, {2, 3, 2}, {3, 4, 1}, {1, 4, 10}}));
    }
}

package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxAreaOfIsland695Test {

    private final MaxAreaOfIsland_695 test = new MaxAreaOfIsland_695();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.maxAreaOfIsland(new int[][]{{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxAreaOfIsland(null));
        assertEquals(0, test.maxAreaOfIsland(new int[][]{{0, 0, 0}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.maxAreaOfIsland(new int[][]{{1, 1, 0, 0}, {1, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}}));
    }
}

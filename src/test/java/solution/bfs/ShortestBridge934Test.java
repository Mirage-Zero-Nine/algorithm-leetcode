package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShortestBridge934Test {

    @Test
    public void testHappyCases() {
        assertEquals(1, new ShortestBridge_934().shortestBridge(new int[][]{{0, 1}, {1, 0}}));
        assertEquals(2, new ShortestBridge_934().shortestBridge(new int[][]{{0, 1, 0}, {0, 0, 0}, {0, 0, 1}}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, new ShortestBridge_934().shortestBridge(new int[][]{{1, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 0, 1, 0, 1}, {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(7, new ShortestBridge_934().shortestBridge(new int[][]{{1, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 1}}));
    }
}

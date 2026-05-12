package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShortestDistance317Test {

    private final ShortestDistance_317 test = new ShortestDistance_317();

    @Test
    public void testHappyCases() {
        assertEquals(7, test.shortestDistance(new int[][]{{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.shortestDistance(null));
        assertEquals(-1, test.shortestDistance(new int[][]{{1, 2, 1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(16, test.shortestDistance(new int[][]{{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {1, 0, 0, 0, 1}}));
    }
}

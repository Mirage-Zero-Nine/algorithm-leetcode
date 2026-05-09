package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxDistance1162Test {

    private final MaxDistance_1162 test = new MaxDistance_1162();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.maxDistance(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}));
        assertEquals(4, test.maxDistance(new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.maxDistance(new int[][]{{1}}));
        assertEquals(-1, test.maxDistance(new int[][]{{1, 1}, {1, 1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.maxDistance(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 1}}));
    }
}

package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShortestPathBinaryMatrix1091Test {

    private final ShortestPathBinaryMatrix_1091 test = new ShortestPathBinaryMatrix_1091();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.shortestPathBinaryMatrix(new int[][]{{0, 1}, {1, 0}}));
        assertEquals(4, test.shortestPathBinaryMatrix(new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 0}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.shortestPathBinaryMatrix(new int[][]{{1, 0}, {0, 0}}));
        assertEquals(1, test.shortestPathBinaryMatrix(new int[][]{{0}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, test.shortestPathBinaryMatrix(new int[][]{{0, 0, 0, 0, 0}, {1, 1, 0, 1, 0}, {1, 0, 0, 0, 0}, {0, 1, 1, 1, 0}, {0, 0, 0, 0, 0}}));
    }
}

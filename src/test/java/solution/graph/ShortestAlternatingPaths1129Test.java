package solution.graph;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ShortestAlternatingPaths1129Test {

    private final ShortestAlternatingPaths_1129 test = new ShortestAlternatingPaths_1129();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{0, 1, 2}, test.shortestAlternatingPaths(3, new int[][]{{0, 1}}, new int[][]{{1, 2}}));
        assertArrayEquals(new int[]{0, 1, -1}, test.shortestAlternatingPaths(3, new int[][]{{0, 1}}, new int[][]{}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[]{0}, test.shortestAlternatingPaths(1, new int[][]{}, new int[][]{}));
        assertArrayEquals(new int[]{0, -1, -1}, test.shortestAlternatingPaths(3, new int[][]{}, new int[][]{}));
    }

    @Test
    public void testLargeCase() {
        assertArrayEquals(new int[]{0, 1, 2, 3},
            test.shortestAlternatingPaths(4, new int[][]{{0, 1}, {2, 3}}, new int[][]{{1, 2}}));
    }
}

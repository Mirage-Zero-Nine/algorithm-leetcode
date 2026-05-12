package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestIncreasingPath329Test {

    private final LongestIncreasingPath_329 test = new LongestIncreasingPath_329();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.longestIncreasingPath(new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}}));
        assertEquals(4, test.longestIncreasingPath(new int[][]{{3, 4, 5}, {3, 2, 6}, {2, 2, 1}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.longestIncreasingPath(new int[][]{}));
        assertEquals(1, test.longestIncreasingPath(new int[][]{{1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(9, test.longestIncreasingPath(new int[][]{{1, 2, 3}, {6, 5, 4}, {7, 8, 9}}));
    }
}

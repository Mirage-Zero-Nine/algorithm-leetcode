package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestIncreasingPath_329Test {

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

    @Test
    public void testAllSameValues() {
        assertEquals(1, test.longestIncreasingPath(new int[][]{{5, 5, 5}, {5, 5, 5}, {5, 5, 5}}));
    }

    @Test
    public void testSingleRow() {
        assertEquals(4, test.longestIncreasingPath(new int[][]{{1, 2, 3, 4}}));
    }

    @Test
    public void testSingleColumn() {
        assertEquals(4, test.longestIncreasingPath(new int[][]{{1}, {2}, {3}, {4}}));
    }

    @Test
    public void testDecreasingRow() {
        assertEquals(4, test.longestIncreasingPath(new int[][]{{4, 3, 2, 1}}));
    }

    @Test
    public void testTwoByTwo() {
        assertEquals(4, test.longestIncreasingPath(new int[][]{{1, 2}, {4, 3}}));
    }

    @Test
    public void testSnakePath() {
        // 1 2 3
        // 6 5 4
        // 7 8 9
        // path: 1->2->3->4->5->6->7->8->9
        assertEquals(9, test.longestIncreasingPath(new int[][]{{1, 2, 3}, {6, 5, 4}, {7, 8, 9}}));
    }

    @Test
    public void testGiantMatrix() {
        int n = 50;
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = i * n + j;
        // strictly increasing left-to-right, top-to-bottom; longest path is n + n - 1 = 99
        assertEquals(99, test.longestIncreasingPath(matrix));
    }
}

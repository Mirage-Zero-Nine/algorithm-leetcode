package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinPathSum64Test {

    private final MinPathSum_64 test = new MinPathSum_64();

    @Test
    public void testHappyCases() {
        assertEquals(7, test.minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        assertEquals(12, test.minPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.minPathSum(new int[][]{{1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(21, test.minPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }
}

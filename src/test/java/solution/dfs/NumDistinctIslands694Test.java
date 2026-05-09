package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumDistinctIslands694Test {

    private final NumDistinctIslands_694 test = new NumDistinctIslands_694();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.numDistinctIslands(new int[][]{{1, 1, 0, 1, 1}, {1, 0, 0, 0, 0}, {0, 0, 0, 0, 1}, {1, 1, 0, 1, 1}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.numDistinctIslands(new int[][]{{0, 0}, {0, 0}}));
        assertEquals(1, test.numDistinctIslands(new int[][]{{1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.numDistinctIslands(new int[][]{{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}}));
    }
}

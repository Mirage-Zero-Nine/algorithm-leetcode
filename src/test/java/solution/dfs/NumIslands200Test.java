package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumIslands200Test {

    private final NumIslands_200 test = new NumIslands_200();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.numIslands(new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}}));
        assertEquals(3, test.numIslands(new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.numIslands(null));
        assertEquals(0, test.numIslands(new char[][]{}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.numIslands(new char[][]{{'1', '1', '1'}, {'1', '1', '1'}, {'1', '1', '1'}}));
    }
}

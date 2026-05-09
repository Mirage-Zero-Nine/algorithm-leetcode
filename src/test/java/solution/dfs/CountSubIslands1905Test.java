package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountSubIslands1905Test {

    private final CountSubIslands_1905 test = new CountSubIslands_1905();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.countSubIslands(
            new int[][]{{1, 1, 1, 0, 0}, {0, 1, 1, 1, 1}, {0, 0, 0, 0, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 1, 1}},
            new int[][]{{1, 1, 1, 0, 0}, {0, 0, 1, 1, 1}, {0, 1, 0, 0, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 1, 0}}
        ));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.countSubIslands(new int[][]{{0}}, new int[][]{{1}}));
        assertEquals(1, test.countSubIslands(new int[][]{{1}}, new int[][]{{1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(2, test.countSubIslands(
            new int[][]{{1, 0, 1, 0, 1}, {1, 1, 1, 1, 1}, {0, 0, 0, 0, 0}, {1, 1, 1, 1, 1}, {1, 0, 1, 0, 1}},
            new int[][]{{0, 0, 0, 0, 0}, {1, 1, 1, 1, 1}, {0, 1, 0, 1, 0}, {0, 1, 0, 1, 0}, {1, 0, 0, 0, 1}}
        ));
    }
}

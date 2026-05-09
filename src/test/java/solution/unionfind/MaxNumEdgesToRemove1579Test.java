package solution.unionfind;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxNumEdgesToRemove1579Test {

    private final MaxNumEdgesToRemove_1579 test = new MaxNumEdgesToRemove_1579();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.maxNumEdgesToRemove(4, new int[][]{{3, 1, 2}, {3, 2, 3}, {1, 1, 3}, {1, 2, 4}, {1, 1, 2}, {2, 3, 4}}));
        assertEquals(0, test.maxNumEdgesToRemove(4, new int[][]{{3, 1, 2}, {3, 2, 3}, {1, 1, 4}, {2, 1, 4}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.maxNumEdgesToRemove(4, new int[][]{{3, 1, 2}, {3, 2, 3}, {1, 1, 4}}));
        assertEquals(2, test.maxNumEdgesToRemove(2, new int[][]{{1, 1, 2}, {2, 1, 2}, {3, 1, 2}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.maxNumEdgesToRemove(5, new int[][]{{3, 1, 2}, {3, 2, 3}, {3, 3, 4}, {3, 4, 5}, {1, 1, 2}, {2, 2, 3}, {1, 3, 4}}));
    }
}

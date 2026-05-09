package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OrangesRotting994Test {

    private final OrangesRotting_994 test = new OrangesRotting_994();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
        assertEquals(-1, test.orangesRotting(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.orangesRotting(new int[][]{{0, 2}}));
        assertEquals(0, test.orangesRotting(new int[][]{{0}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(8, test.orangesRotting(new int[][]{{2, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}}));
    }
}

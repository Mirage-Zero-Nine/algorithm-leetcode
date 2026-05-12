package solution.graph;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsBipartite785Test {

    private final IsBipartite_785 test = new IsBipartite_785();

    @Test
    public void testHappyCases() {
        assertTrue(test.isBipartite(new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}}));
        assertFalse(test.isBipartite(new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrue(test.isBipartite(new int[][]{{1}, {0}}));
        assertFalse(test.isBipartite(new int[][]{{1, 2}, {0, 2}, {0, 1}}));
    }

    @Test
    public void testLargeCase() {
        // Even cycle - bipartite
        assertTrue(test.isBipartite(new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}}));
        // Odd cycle - not bipartite
        assertFalse(test.isBipartite(new int[][]{{1, 2}, {0, 2}, {0, 1}}));
    }
}

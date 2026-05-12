package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class HasPath490Test {

    private final HasPath_490 test = new HasPath_490();

    @Test
    public void testHappyCases() {
        assertTrue(test.hasPath(
            new int[][]{{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}},
            new int[]{0, 4}, new int[]{4, 4}
        ));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.hasPath(new int[][]{}, new int[]{0, 0}, new int[]{0, 0}));
        assertFalse(test.hasPath(
            new int[][]{{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}},
            new int[]{0, 4}, new int[]{3, 2}
        ));
    }

    @Test
    public void testLargeCase() {
        assertFalse(test.hasPath(
            new int[][]{{0, 0, 0, 0, 0}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 1, 0, 0, 1}, {0, 1, 0, 0, 0}},
            new int[]{4, 3}, new int[]{0, 1}
        ));
    }
}

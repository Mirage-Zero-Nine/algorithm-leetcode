package solution.unionfind;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ValidTree261Test {

    private final ValidTree_261 test = new ValidTree_261();

    @Test
    public void testHappyCases() {
        assertTrue(test.validTree(5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}}));
        assertTrue(test.validTree(3, new int[][]{{0, 1}, {1, 2}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.validTree(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}}));
        assertFalse(test.validTree(5, new int[][]{{0, 1}, {0, 4}, {1, 4}, {2, 3}}));
        assertTrue(test.validTree(1, new int[][]{}));
        assertFalse(test.validTree(4, new int[][]{{0, 1}, {2, 3}}));
    }

    @Test
    public void testLargeCase() {
        int n = 100;
        int[][] edges = new int[n - 1][2];
        for (int i = 0; i < n - 1; i++) {
            edges[i] = new int[]{i, i + 1};
        }
        assertTrue(test.validTree(n, edges));
    }
}

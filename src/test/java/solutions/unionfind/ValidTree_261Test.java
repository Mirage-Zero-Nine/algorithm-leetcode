package solutions.unionfind;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ValidTree_261Test {

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

    @Test
    public void testTwoNodesConnected() {
        assertTrue(test.validTree(2, new int[][]{{0, 1}}));
    }

    @Test
    public void testTwoNodesDisconnected() {
        assertFalse(test.validTree(2, new int[][]{}));
    }

    @Test
    public void testTooManyEdges() {
        assertFalse(test.validTree(3, new int[][]{{0, 1}, {1, 2}, {0, 2}}));
    }

    @Test
    public void testStarTree() {
        assertTrue(test.validTree(5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {0, 4}}));
    }

    @Test
    public void testDisconnectedComponents() {
        // 6 nodes, only 4 edges (not n-1), so not a valid tree
        assertFalse(test.validTree(6, new int[][]{{0, 1}, {2, 3}, {4, 5}, {0, 2}}));
    }

    @Test
    public void testSelfLoopNotPossibleButExtraEdge() {
        // 4 nodes but 4 edges means cycle
        assertFalse(test.validTree(4, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}}));
    }

    @Test
    public void testGiantStarTree() {
        int n = 5000;
        int[][] edges = new int[n - 1][2];
        for (int i = 1; i < n; i++) {
            edges[i - 1] = new int[]{0, i};
        }
        assertTrue(test.validTree(n, edges));
    }
}

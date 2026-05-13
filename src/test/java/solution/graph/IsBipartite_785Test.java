package solution.graph;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsBipartite_785Test {

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

    @Test
    public void testDisconnectedBipartite() {
        // Two disconnected components, both bipartite
        assertTrue(test.isBipartite(new int[][]{{1}, {0}, {3}, {2}}));
    }

    @Test
    public void testDisconnectedNonBipartite() {
        // One component is a triangle (not bipartite)
        assertFalse(test.isBipartite(new int[][]{{1, 2}, {0, 2}, {0, 1}, {4}, {3}}));
    }

    @Test
    public void testSingleNode() {
        assertTrue(test.isBipartite(new int[][]{{}}));
    }

    @Test
    public void testStarGraph() {
        // Star: center 0 connected to 1,2,3,4 - bipartite
        assertTrue(test.isBipartite(new int[][]{{1, 2, 3, 4}, {0}, {0}, {0}, {0}}));
    }

    @Test
    public void testEvenCycleSix() {
        // 6-node cycle: 0-1-2-3-4-5-0 (even cycle, bipartite)
        assertTrue(test.isBipartite(new int[][]{{1, 5}, {0, 2}, {1, 3}, {2, 4}, {3, 5}, {4, 0}}));
    }

    @Test
    public void testOddCycleFive() {
        // 5-node cycle: 0-1-2-3-4-0 (odd cycle, not bipartite)
        assertFalse(test.isBipartite(new int[][]{{1, 4}, {0, 2}, {1, 3}, {2, 4}, {3, 0}}));
    }

    @Test
    public void testCompleteBipartiteK33() {
        // K3,3: nodes 0,1,2 connected to nodes 3,4,5
        assertTrue(test.isBipartite(new int[][]{{3, 4, 5}, {3, 4, 5}, {3, 4, 5}, {0, 1, 2}, {0, 1, 2}, {0, 1, 2}}));
    }

    @Test
    public void testGiantBipartiteGraph() {
        // Build a large even cycle (bipartite): 0-1-2-3-...-99-0
        int n = 100;
        int[][] graph = new int[n][];
        for (int i = 0; i < n; i++) {
            graph[i] = new int[]{(i + 1) % n, (i - 1 + n) % n};
        }
        assertTrue(test.isBipartite(graph));
    }
}

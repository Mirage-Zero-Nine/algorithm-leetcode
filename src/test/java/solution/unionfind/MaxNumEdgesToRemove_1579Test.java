package solution.unionfind;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxNumEdgesToRemove_1579Test {

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

    @Test
    public void testTwoNodesAllTypes() {
        assertEquals(2, test.maxNumEdgesToRemove(2, new int[][]{{1, 1, 2}, {2, 1, 2}, {3, 1, 2}}));
    }

    @Test
    public void testTwoNodesOnlyType3() {
        assertEquals(0, test.maxNumEdgesToRemove(2, new int[][]{{3, 1, 2}}));
    }

    @Test
    public void testImpossibleForBob() {
        assertEquals(-1, test.maxNumEdgesToRemove(3, new int[][]{{1, 1, 2}, {1, 2, 3}, {3, 1, 2}}));
    }

    @Test
    public void testImpossibleForAlice() {
        assertEquals(-1, test.maxNumEdgesToRemove(3, new int[][]{{2, 1, 2}, {2, 2, 3}, {3, 1, 2}}));
    }

    @Test
    public void testAllType3Edges() {
        assertEquals(0, test.maxNumEdgesToRemove(3, new int[][]{{3, 1, 2}, {3, 2, 3}}));
    }

    @Test
    public void testRedundantType3() {
        assertEquals(1, test.maxNumEdgesToRemove(3, new int[][]{{3, 1, 2}, {3, 2, 3}, {3, 1, 3}}));
    }

    @Test
    public void testGiantCase() {
        int n = 500;
        int[][] edges = new int[n - 1][3];
        for (int i = 0; i < n - 1; i++) {
            edges[i] = new int[]{3, i + 1, i + 2};
        }
        assertEquals(0, test.maxNumEdgesToRemove(n, edges));
    }
}

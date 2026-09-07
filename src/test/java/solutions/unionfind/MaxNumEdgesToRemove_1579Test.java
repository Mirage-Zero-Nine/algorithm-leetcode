package solutions.unionfind;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxNumEdgesToRemove_1579Test {

    @Test
    public void testAllTypedThreeVertexGraphsAgainstExhaustiveEdgeSubsets() {
        int[][] possible = new int[9][3];
        int index = 0;
        for (int type = 1; type <= 3; type++)
            for (int from = 1; from <= 3; from++)
                for (int to = from + 1; to <= 3; to++) possible[index++] = new int[]{type, from, to};
        for (int available = 1; available < 512; available++) {
            java.util.List<int[]> edges = new java.util.ArrayList<>();
            for (int bit = 0; bit < 9; bit++)
                if ((available & (1 << bit)) != 0) edges.add(possible[bit].clone());
            int expected = -1;
            for (int selected = available; selected > 0; selected = (selected - 1) & available) {
                boolean bothConnected = true;
                for (int person = 1; person <= 2; person++) {
                    boolean[] reached = {false, true, false, false};
                    for (int pass = 0; pass < 3; pass++)
                        for (int i = 0; i < 9; i++)
                            if ((selected & (1 << i)) != 0 && (possible[i][0] == person || possible[i][0] == 3)
                                    && (reached[possible[i][1]] || reached[possible[i][2]]))
                                reached[possible[i][1]] = reached[possible[i][2]] = true;
                    bothConnected &= reached[2] && reached[3];
                }
                if (bothConnected) expected = Math.max(expected, edges.size() - Integer.bitCount(selected));
            }
            assertEquals(expected, test.maxNumEdgesToRemove(3, edges.toArray(new int[0][])), "graph " + available);
        }
    }


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

package solution.unionfind;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class FindRedundantConnection_684Test {

    private final FindRedundantConnection_684 test = new FindRedundantConnection_684();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{2, 3}, test.findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}}));
        assertArrayEquals(new int[]{1, 4}, test.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}}));
    }

    @Test
    public void testEdgeCases() {
        assertArrayEquals(new int[]{1, 2}, test.findRedundantConnection(new int[][]{{1, 2}, {1, 2}}));
    }

    @Test
    public void testLargeCase() {
        // Chain 1-2-3-...-10 with extra edge 1-10
        int[][] edges = new int[10][2];
        for (int i = 0; i < 9; i++) {
            edges[i] = new int[]{i + 1, i + 2};
        }
        edges[9] = new int[]{1, 10};
        assertArrayEquals(new int[]{1, 10}, test.findRedundantConnection(edges));
    }

    @Test
    public void testTriangle() {
        assertArrayEquals(new int[]{2, 3}, test.findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}}));
    }

    @Test
    public void testSquareCycle() {
        // 1-2, 2-3, 3-4, 4-1 -> last edge forming cycle is {4,1}
        assertArrayEquals(new int[]{4, 1}, test.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 1}}));
    }

    @Test
    public void testStarWithExtraEdge() {
        // Star: 1-2, 1-3, 1-4, 1-5, extra: 2-3
        assertArrayEquals(new int[]{2, 3}, test.findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {1, 4}, {1, 5}, {2, 3}}));
    }

    @Test
    public void testLastEdgeRedundant() {
        assertArrayEquals(new int[]{3, 1}, test.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {3, 1}}));
    }

    @Test
    public void testFiveNodeCycle() {
        // 1-2, 2-3, 3-4, 4-5, 5-1
        assertArrayEquals(new int[]{5, 1}, test.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 1}}));
    }

    @Test
    public void testRedundantInMiddle() {
        // 1-2, 2-3, 1-3, 3-4 -> {1,3} creates cycle
        assertArrayEquals(new int[]{1, 3}, test.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {1, 3}, {3, 4}}));
    }

    @Test
    public void testGiantCase() {
        // Chain 1-2-3-...-100 with extra edge 50-100
        int[][] edges = new int[100][2];
        for (int i = 0; i < 99; i++) {
            edges[i] = new int[]{i + 1, i + 2};
        }
        edges[99] = new int[]{50, 100};
        assertArrayEquals(new int[]{50, 100}, test.findRedundantConnection(edges));
    }
}

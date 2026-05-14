package solution.graph;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ShortestAlternatingPaths_1129Test {

    private final ShortestAlternatingPaths_1129 test = new ShortestAlternatingPaths_1129();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{0, 1, 2}, test.shortestAlternatingPaths(3, new int[][]{{0, 1}}, new int[][]{{1, 2}}));
        assertArrayEquals(new int[]{0, 1, -1}, test.shortestAlternatingPaths(3, new int[][]{{0, 1}}, new int[][]{}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[]{0}, test.shortestAlternatingPaths(1, new int[][]{}, new int[][]{}));
        assertArrayEquals(new int[]{0, -1, -1}, test.shortestAlternatingPaths(3, new int[][]{}, new int[][]{}));
    }

    @Test
    public void testLargeCase() {
        assertArrayEquals(new int[]{0, 1, 2, 3},
            test.shortestAlternatingPaths(4, new int[][]{{0, 1}, {2, 3}}, new int[][]{{1, 2}}));
    }

    @Test
    public void testOnlyBlueEdges() {
        assertArrayEquals(new int[]{0, 1, -1}, test.shortestAlternatingPaths(3, new int[][]{}, new int[][]{{0, 1}}));
    }

    @Test
    public void testOnlyRedEdges() {
        assertArrayEquals(new int[]{0, 1, -1}, test.shortestAlternatingPaths(3, new int[][]{{0, 1}}, new int[][]{}));
    }

    @Test
    public void testBothColorsSameEdge() {
        // Both red and blue edge from 0 to 1
        assertArrayEquals(new int[]{0, 1}, test.shortestAlternatingPaths(2, new int[][]{{0, 1}}, new int[][]{{0, 1}}));
    }

    @Test
    public void testParallelPaths() {
        // Red: 0->1, Blue: 0->1, Red: 1->2, Blue: 1->2
        assertArrayEquals(new int[]{0, 1, 2}, test.shortestAlternatingPaths(3, new int[][]{{0, 1}, {1, 2}}, new int[][]{{0, 1}, {1, 2}}));
    }

    @Test
    public void testSelfLoop() {
        // Self-loop on node 0 (red)
        assertArrayEquals(new int[]{0, 1}, test.shortestAlternatingPaths(2, new int[][]{{0, 0}, {0, 1}}, new int[][]{}));
    }

    @Test
    public void testFiveNodesAlternating() {
        // 0 -red-> 1 -blue-> 2 -red-> 3 -blue-> 4
        assertArrayEquals(new int[]{0, 1, 2, 3, 4},
            test.shortestAlternatingPaths(5, new int[][]{{0, 1}, {2, 3}}, new int[][]{{1, 2}, {3, 4}}));
    }

    @Test
    public void testUnreachableNode() {
        // Node 2 has no incoming alternating path
        assertArrayEquals(new int[]{0, 1, -1}, test.shortestAlternatingPaths(3, new int[][]{{0, 1}}, new int[][]{{0, 1}}));
    }

    @Test
    public void testGiantCase() {
        // Chain of 50 nodes alternating red/blue
        int n = 50;
        int[][] red = new int[25][2];
        int[][] blue = new int[24][2];
        for (int i = 0; i < 25; i++) {
            red[i] = new int[]{i * 2, i * 2 + 1};
        }
        for (int i = 0; i < 24; i++) {
            blue[i] = new int[]{i * 2 + 1, i * 2 + 2};
        }
        int[] result = test.shortestAlternatingPaths(n, red, blue);
        // Node 0 = 0, node 1 = 1, node 2 = 2, ...
        for (int i = 0; i < n; i++) {
            assertArrayEquals(new int[]{i}, new int[]{result[i]});
        }
    }
}

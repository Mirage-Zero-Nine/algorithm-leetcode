package solutions.unionfind;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class FindRedundantConnection_684Test {

    @Test
    public void testSeededUnicyclicGraphsAgainstRemovingEachEdge() {
        java.util.Random random = new java.util.Random(6842026L);
        for (int n = 3; n <= 25; n++) {
            java.util.List<int[]> edges = new java.util.ArrayList<>();
            boolean[][] existing = new boolean[n + 1][n + 1];
            for (int child = 2; child <= n; child++) {
                int parent = 1 + random.nextInt(child - 1);
                edges.add(new int[]{parent, child});
                existing[parent][child] = existing[child][parent] = true;
            }
            int a, b;
            do {
                a = 1 + random.nextInt(n);
                b = 1 + random.nextInt(n);
            } while (a == b || existing[a][b]);
            edges.add(new int[]{Math.min(a, b), Math.max(a, b)});
            java.util.Collections.shuffle(edges, random);
            int[] expected = null;
            for (int omitted = 0; omitted < n; omitted++) {
                boolean[] reached = new boolean[n + 1];
                reached[1] = true;
                for (int pass = 0; pass < n; pass++)
                    for (int i = 0; i < n; i++)
                        if (i != omitted && (reached[edges.get(i)[0]] || reached[edges.get(i)[1]]))
                            reached[edges.get(i)[0]] = reached[edges.get(i)[1]] = true;
                boolean connected = true;
                for (int vertex = 1; vertex <= n; vertex++) connected &= reached[vertex];
                if (connected) expected = edges.get(omitted);
            }
            assertArrayEquals(expected, test.findRedundantConnection(edges.toArray(new int[0][])), "vertices " + n);
        }
    }


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

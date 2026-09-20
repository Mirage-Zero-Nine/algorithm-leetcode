package solutions.unionfind;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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

    @ParameterizedTest(name = "graph {index}")
    @MethodSource("additionalGraphs")
    public void testAdditionalLegalGraphShapes(int[][] edges, int[] expected) {
        int[][] input = java.util.Arrays.stream(edges)
                .map(int[]::clone)
                .toArray(int[][]::new);
        assertArrayEquals(expected, test.findRedundantConnection(input));
        assertArrayEquals(edges, input,
                "finding the redundant edge must not mutate the caller's graph");
    }

    private static Stream<Arguments> additionalGraphs() {
        return Stream.of(
                Arguments.of(new int[][]{{1, 2}, {2, 3}, {1, 3}}, new int[]{1, 3}),
                Arguments.of(new int[][]{{1, 2}, {1, 3}, {1, 4}, {3, 4}}, new int[]{3, 4}),
                Arguments.of(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {2, 5}}, new int[]{2, 5}),
                Arguments.of(new int[][]{{1, 2}, {1, 3}, {3, 4}, {3, 5}, {5, 6}, {4, 6}}, new int[]{4, 6}),
                Arguments.of(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {2, 6}, {6, 7}}, new int[]{2, 6}),
                Arguments.of(new int[][]{{1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 6}, {3, 7}, {4, 5}}, new int[]{4, 5}),
                Arguments.of(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 8}, {3, 8}}, new int[]{3, 8}),
                Arguments.of(new int[][]{{1, 2}, {1, 3}, {1, 4}, {4, 5}, {5, 6}, {2, 6}}, new int[]{2, 6}),
                Arguments.of(new int[][]{{1, 2}, {2, 3}, {2, 4}, {4, 5}, {5, 6}, {3, 6}}, new int[]{3, 6}),
                Arguments.of(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}, {5, 6}, {6, 7}, {7, 8}, {8, 9}, {9, 10}, {10, 11}, {11, 12}, {12, 13}, {13, 14}, {14, 15}, {4, 15}}, new int[]{1, 5}),
                Arguments.of(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 8}, {8, 9}, {9, 10}, {10, 11}, {11, 12}, {12, 13}, {13, 14}, {14, 15}, {15, 16}, {16, 17}, {17, 18}, {18, 19}, {19, 20}, {1, 20}}, new int[]{1, 20}),
                Arguments.of(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 8}, {8, 9}, {9, 10}, {10, 11}, {11, 12}, {12, 13}, {13, 14}, {14, 15}, {15, 16}, {16, 17}, {17, 18}, {18, 19}, {19, 20}, {10, 20}}, new int[]{10, 20})
        );
    }
}

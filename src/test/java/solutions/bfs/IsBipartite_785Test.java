package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class IsBipartite_785Test {

    private final IsBipartite_785 test = new IsBipartite_785();

    @Test
    public void testHappyCases() {
        assertBoth(new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}}, true);
        assertBoth(new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}}, false);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertBoth(new int[][]{{1}, {0}}, true);
        assertBoth(new int[][]{{1, 2}, {0, 2}, {0, 1}}, false);
    }

    @Test
    public void testLargeCase() {
        // Preserve the historical even-cycle and odd-cycle checks.
        assertBoth(new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}}, true);
        assertBoth(new int[][]{{1, 2}, {0, 2}, {0, 1}}, false);
    }

    @Test
    public void testDisconnectedBipartite() {
        assertBoth(new int[][]{{1}, {0}, {3}, {2}}, true);
    }

    @Test
    public void testDisconnectedNonBipartite() {
        assertBoth(new int[][]{{1, 2}, {0, 2}, {0, 1}, {4}, {3}}, false);
    }

    @Test
    public void testSingleNode() {
        assertBoth(new int[][]{{}}, true);
    }

    @Test
    public void testStarGraph() {
        assertBoth(new int[][]{{1, 2, 3, 4}, {0}, {0}, {0}, {0}}, true);
    }

    @Test
    public void testEvenCycleSix() {
        assertBoth(new int[][]{{1, 5}, {0, 2}, {1, 3}, {2, 4}, {3, 5}, {4, 0}}, true);
    }

    @Test
    public void testOddCycleFive() {
        assertBoth(new int[][]{{1, 4}, {0, 2}, {1, 3}, {2, 4}, {3, 0}}, false);
    }

    @Test
    public void testCompleteBipartiteK33() {
        assertBoth(new int[][]{{3, 4, 5}, {3, 4, 5}, {3, 4, 5}, {0, 1, 2}, {0, 1, 2}, {0, 1, 2}}, true);
    }

    @Test
    public void testGiantBipartiteGraph() {
        int n = 100;
        int[][] graph = new int[n][];
        for (int i = 0; i < n; i++) {
            graph[i] = new int[]{(i + 1) % n, (i - 1 + n) % n};
        }
        assertBoth(graph, true);
    }

    @Test
    public void testAllIsolatedVerticesAtMaximumSize() {
        assertBoth(new int[100][0], true);
    }

    @Test
    public void testLongPaths() {
        assertBoth(path(99), true);
        assertBoth(path(100), true);
    }

    @Test
    public void testCompleteBipartiteAtMaximumSize() {
        assertBoth(completeBipartite(50, 50), true);
    }

    @Test
    public void testCompleteGraphNegativeCases() {
        assertBoth(completeGraph(3), false);
        assertBoth(completeGraph(4), false);
        assertBoth(completeGraph(100), false);
    }

    @Test
    public void testDisconnectedComponentsWithIsolatedVertices() {
        int[][] graph = new int[12][];
        graph[0] = new int[]{1, 2};
        graph[1] = new int[]{0, 3};
        graph[2] = new int[]{0, 3};
        graph[3] = new int[]{1, 2};
        graph[4] = new int[]{5, 6};
        graph[5] = new int[]{4, 6};
        graph[6] = new int[]{4, 5};
        for (int i = 7; i < graph.length; i++) {
            graph[i] = new int[0];
        }
        assertBoth(graph, false);
    }

    @Test
    public void testDisconnectedBipartiteComponentsOfDifferentShapes() {
        int[][] graph = new int[15][];
        graph[0] = new int[]{1, 2, 3, 4};
        for (int i = 1; i <= 4; i++) {
            graph[i] = new int[]{0};
        }
        int[][] cycle = undirected(6, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 0}});
        for (int i = 0; i < cycle.length; i++) {
            graph[5 + i] = shift(cycle[i], 5);
        }
        graph[11] = new int[]{12};
        graph[12] = new int[]{11, 13};
        graph[13] = new int[]{12, 14};
        graph[14] = new int[]{13};
        assertBoth(graph, true);
    }

    @Test
    public void testSeededRandomBipartiteGraphs() {
        for (int i = 0; i < 30; i++) {
            int n = 1 + (i * 37 % 100);
            assertBoth(randomBipartite(n, 0x785L + i, 0.35), true);
        }
    }

    @Test
    public void testSeededGraphsAgainstIndependentOracle() {
        Random random = new Random(785_2026L);
        for (int sample = 0; sample < 50; sample++) {
            int n = 1 + random.nextInt(9);
            boolean[][] edges = new boolean[n][n];
            for (int u = 0; u < n; u++) {
                for (int v = u + 1; v < n; v++) {
                    edges[u][v] = edges[v][u] = random.nextBoolean();
                }
            }
            int[][] graph = fromEdges(edges);
            assertBoth(graph, bruteForceBipartite(graph));
        }
    }

    @Test
    public void testExhaustiveSimpleGraphsThroughFiveVertices() {
        for (int n = 1; n <= 5; n++) {
            int edgeCount = n * (n - 1) / 2;
            int graphCount = 1 << edgeCount;
            for (int mask = 0; mask < graphCount; mask++) {
                int[][] graph = graphFromMask(n, mask);
                assertBoth(graph, bruteForceBipartite(graph));
            }
        }
    }

    @Test
    public void testInputIsNotMutatedAndCallsAreIndependent() {
        int[][] graph = completeBipartite(4, 5);
        int[][] before = copy(graph);
        assertTrue(test.isBipartite(graph));
        assertArrayEquals(before, graph);
        assertTrue(test.isBipartiteBfs(graph));
        assertArrayEquals(before, graph);

        assertFalse(test.isBipartite(completeGraph(5)));
        assertTrue(test.isBipartite(graph));
        assertFalse(test.isBipartiteBfs(completeGraph(5)));
        assertTrue(test.isBipartiteBfs(graph));
    }

    @Test
    public void testDfsImplementationDefinedInvalidInputs() {
        assertFalse(test.isBipartite(null));
        assertFalse(test.isBipartite(new int[0][]));
        assertFalse(test.isBipartite(new int[][]{null}));
    }

    @Test
    public void testOneHundredNodeSparseBoundaryWithOddCycle() {
        int[][] graph = new int[100][];
        graph[0] = new int[]{1, 2};
        graph[1] = new int[]{0, 2};
        graph[2] = new int[]{0, 1, 3};
        graph[3] = new int[]{2, 4};
        graph[4] = new int[]{3};
        for (int i = 5; i < graph.length; i++) {
            graph[i] = new int[0];
        }
        assertBoth(graph, false);
    }

    private void assertBoth(int[][] graph, boolean expected) {
        int[][] original = copy(graph);
        int[][] dfsInput = copy(graph);
        int[][] bfsInput = copy(graph);
        assertEquals(expected, test.isBipartite(dfsInput));
        assertEquals(expected, test.isBipartiteBfs(bfsInput));
        assertArrayEquals(original, dfsInput);
        assertArrayEquals(original, bfsInput);
    }

    private static int[][] path(int n) {
        int[][] graph = new int[n][];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                graph[i] = new int[]{1};
            } else if (i == n - 1) {
                graph[i] = new int[]{n - 2};
            } else {
                graph[i] = new int[]{i - 1, i + 1};
            }
        }
        return graph;
    }

    private static int[][] completeGraph(int n) {
        int[][] graph = new int[n][];
        for (int i = 0; i < n; i++) {
            graph[i] = new int[n - 1];
            int p = 0;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    graph[i][p++] = j;
                }
            }
        }
        return graph;
    }

    private static int[][] completeBipartite(int left, int right) {
        int[][] graph = new int[left + right][];
        for (int i = 0; i < left; i++) {
            graph[i] = new int[right];
            for (int j = 0; j < right; j++) {
                graph[i][j] = left + j;
            }
        }
        for (int j = 0; j < right; j++) {
            graph[left + j] = new int[left];
            for (int i = 0; i < left; i++) {
                graph[left + j][i] = i;
            }
        }
        return graph;
    }

    private static int[][] randomBipartite(int n, long seed, double probability) {
        Random random = new Random(seed);
        int split = n / 2;
        boolean[][] edges = new boolean[n][n];
        for (int u = 0; u < split; u++) {
            for (int v = split; v < n; v++) {
                edges[u][v] = edges[v][u] = random.nextDouble() < probability;
            }
        }
        return fromEdges(edges);
    }

    private static int[][] graphFromMask(int n, int mask) {
        boolean[][] edges = new boolean[n][n];
        int bit = 0;
        for (int u = 0; u < n; u++) {
            for (int v = u + 1; v < n; v++) {
                if ((mask & (1 << bit++)) != 0) {
                    edges[u][v] = edges[v][u] = true;
                }
            }
        }
        return fromEdges(edges);
    }

    private static int[][] fromEdges(boolean[][] edges) {
        int[][] graph = new int[edges.length][];
        for (int u = 0; u < edges.length; u++) {
            List<Integer> neighbors = new ArrayList<>();
            for (int v = 0; v < edges.length; v++) {
                if (edges[u][v]) {
                    neighbors.add(v);
                }
            }
            graph[u] = neighbors.stream().mapToInt(Integer::intValue).toArray();
        }
        return graph;
    }

    private static boolean bruteForceBipartite(int[][] graph) {
        int n = graph.length;
        for (int assignment = 0; assignment < (1 << n); assignment++) {
            boolean valid = true;
            for (int u = 0; u < n && valid; u++) {
                for (int v : graph[u]) {
                    if (((assignment >>> u) & 1) == ((assignment >>> v) & 1)) {
                        valid = false;
                        break;
                    }
                }
            }
            if (valid) {
                return true;
            }
        }
        return false;
    }

    private static int[][] undirected(int n, int[][] edges) {
        boolean[][] matrix = new boolean[n][n];
        for (int[] edge : edges) {
            matrix[edge[0]][edge[1]] = matrix[edge[1]][edge[0]] = true;
        }
        return fromEdges(matrix);
    }

    private static int[] shift(int[] neighbors, int offset) {
        int[] shifted = new int[neighbors.length];
        for (int i = 0; i < neighbors.length; i++) {
            shifted[i] = neighbors[i] + offset;
        }
        return shifted;
    }

    private static int[][] copy(int[][] graph) {
        int[][] copy = new int[graph.length][];
        for (int i = 0; i < graph.length; i++) {
            copy[i] = graph[i] == null ? null : graph[i].clone();
        }
        return copy;
    }
}

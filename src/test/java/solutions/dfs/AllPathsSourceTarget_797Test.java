package solutions.dfs;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AllPathsSourceTarget_797Test {

    private final AllPathsSourceTarget_797 solution = new AllPathsSourceTarget_797();

    @Test
    void testLeetCodeExampleOne() {
        int[][] graph = {
                {1, 2},
                {3},
                {3},
                {}
        };

        assertPathsEqual(
                List.of(
                        List.of(0, 1, 3),
                        List.of(0, 2, 3)
                ),
                solution.allPathsSourceTarget(graph)
        );
    }

    @Test
    void testLeetCodeExampleTwo() {
        int[][] graph = {
                {4, 3, 1},
                {3, 2, 4},
                {3},
                {4},
                {}
        };

        assertPathsEqual(
                List.of(
                        List.of(0, 4),
                        List.of(0, 3, 4),
                        List.of(0, 1, 3, 4),
                        List.of(0, 1, 2, 3, 4),
                        List.of(0, 1, 4)
                ),
                solution.allPathsSourceTarget(graph)
        );
    }

    @Test
    void testEmptyGraph() {
        assertTrue(solution.allPathsSourceTarget(new int[0][]).isEmpty());
    }

    @Test
    void testTwoNodesWithOneDirectPath() {
        assertPathsEqual(
                List.of(List.of(0, 1)),
                solution.allPathsSourceTarget(new int[][]{{1}, {}})
        );
    }

    @Test
    void testSourceHasNoOutgoingEdges() {
        int[][] graph = {
                {},
                {}
        };

        assertTrue(solution.allPathsSourceTarget(graph).isEmpty());
    }

    @Test
    void testTargetIsReachableOnlyThroughSeveralIntermediateNodes() {
        int[][] graph = {
                {1},
                {2},
                {3},
                {4},
                {}
        };

        assertPathsEqual(
                List.of(List.of(0, 1, 2, 3, 4)),
                solution.allPathsSourceTarget(graph)
        );
    }

    @Test
    void testMultipleDirectAndIndirectPaths() {
        int[][] graph = {
                {1, 2, 3},
                {4},
                {4},
                {4},
                {}
        };

        assertPathsEqual(
                List.of(
                        List.of(0, 1, 4),
                        List.of(0, 2, 4),
                        List.of(0, 3, 4)
                ),
                solution.allPathsSourceTarget(graph)
        );
    }

    @Test
    void testPathsWithSharedPrefixAndSuffix() {
        int[][] graph = {
                {1, 2},
                {3},
                {3},
                {4, 5},
                {5},
                {}
        };

        assertPathsEqual(
                List.of(
                        List.of(0, 1, 3, 4, 5),
                        List.of(0, 1, 3, 5),
                        List.of(0, 2, 3, 4, 5),
                        List.of(0, 2, 3, 5)
                ),
                solution.allPathsSourceTarget(graph)
        );
    }

    @Test
    void testTargetWithOutgoingEdgesStopsThePathAtTarget() {
        int[][] graph = {
                {3},
                {},
                {},
                {1, 2}
        };

        assertPathsEqual(
                List.of(List.of(0, 3)),
                solution.allPathsSourceTarget(graph)
        );
    }

    @Test
    void testUnreachableTargetWithDeadEndBranch() {
        int[][] graph = {
                {1},
                {2},
                {},
                {}
        };

        assertTrue(solution.allPathsSourceTarget(graph).isEmpty());
    }

    @Test
    void testReachableTargetAndUnreachableNodes() {
        int[][] graph = {
                {1, 2},
                {5},
                {3},
                {4},
                {},
                {}
        };

        assertPathsEqual(
                List.of(List.of(0, 1, 5)),
                solution.allPathsSourceTarget(graph)
        );
    }

    @Test
    void testAllPathsAreUnique() {
        int[][] graph = {
                {1, 2},
                {3, 4},
                {3, 4},
                {5},
                {5},
                {}
        };

        List<List<Integer>> paths = solution.allPathsSourceTarget(graph);

        assertEquals(paths.size(), new HashSet<>(paths).size());
        assertEquals(4, paths.size());
    }

    @Test
    void testInputGraphIsNotMutated() {
        int[][] graph = {
                {1, 2},
                {3},
                {3},
                {}
        };
        int[][] original = deepCopy(graph);

        solution.allPathsSourceTarget(graph);

        assertTrue(Arrays.deepEquals(original, graph));
    }

    @Test
    void testRepeatedCallsDoNotLeakBacktrackingState() {
        int[][] graph = {
                {1, 2},
                {3},
                {3},
                {}
        };
        List<List<Integer>> expected = List.of(
                List.of(0, 1, 3),
                List.of(0, 2, 3)
        );

        assertPathsEqual(expected, solution.allPathsSourceTarget(graph));
        assertPathsEqual(expected, solution.allPathsSourceTarget(graph));
    }

    @Test
    void testReturnedPathsAreIndependentCopies() {
        int[][] graph = {
                {1, 2},
                {3},
                {3},
                {}
        };

        List<List<Integer>> firstResult = solution.allPathsSourceTarget(graph);
        firstResult.getFirst().add(99);

        assertPathsEqual(
                List.of(
                        List.of(0, 1, 3),
                        List.of(0, 2, 3)
                ),
                solution.allPathsSourceTarget(graph)
        );
    }

    @Test
    void testMaximumNumberOfPathsForFifteenNodes() {
        int nodeCount = 15;
        int[][] graph = completeForwardGraph(nodeCount);

        List<List<Integer>> paths = solution.allPathsSourceTarget(graph);

        // Every subset of the 13 intermediate nodes defines one path.
        assertEquals(1 << (nodeCount - 2), paths.size());
        assertEquals(paths.size(), new HashSet<>(paths).size());
        for (List<Integer> path : paths) {
            assertEquals(0, path.getFirst());
            assertEquals(nodeCount - 1, path.getLast());
            assertStrictlyIncreasing(path);
        }
    }

    @Test
    void testEveryForwardDagWithFiveNodes() {
        int nodeCount = 5;
        List<int[]> possibleEdges = new ArrayList<>();
        for (int from = 0; from < nodeCount; from++) {
            for (int to = from + 1; to < nodeCount; to++) {
                possibleEdges.add(new int[]{from, to});
            }
        }

        // There are 2^(5 choose 2) = 1024 DAGs whose edges point forward.
        int graphCount = 1 << possibleEdges.size();
        for (int mask = 0; mask < graphCount; mask++) {
            int[][] graph = graphFromMask(nodeCount, possibleEdges, mask);
            Set<List<Integer>> expected = expectedPathsBottomUp(graph);

            assertPathsEqual(expected, solution.allPathsSourceTarget(graph));
        }
    }

    private static void assertPathsEqual(
            List<List<Integer>> expected,
            List<List<Integer>> actual
    ) {
        assertPathsEqual(new HashSet<>(expected), actual);
    }

    private static void assertPathsEqual(
            Set<List<Integer>> expected,
            List<List<Integer>> actual
    ) {
        assertEquals(expected.size(), actual.size());
        assertEquals(expected, new HashSet<>(actual));
    }

    private static int[][] completeForwardGraph(int nodeCount) {
        int[][] graph = new int[nodeCount][];
        for (int from = 0; from < nodeCount; from++) {
            graph[from] = new int[nodeCount - from - 1];
            for (int index = 0; index < graph[from].length; index++) {
                graph[from][index] = from + index + 1;
            }
        }
        return graph;
    }

    private static int[][] graphFromMask(int nodeCount, List<int[]> edges, int mask) {
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int node = 0; node < nodeCount; node++) {
            adjacency.add(new ArrayList<>());
        }

        for (int edgeIndex = 0; edgeIndex < edges.size(); edgeIndex++) {
            if ((mask & (1 << edgeIndex)) != 0) {
                int[] edge = edges.get(edgeIndex);
                adjacency.get(edge[0]).add(edge[1]);
            }
        }

        int[][] graph = new int[nodeCount][];
        for (int node = 0; node < nodeCount; node++) {
            graph[node] = adjacency.get(node).stream().mapToInt(Integer::intValue).toArray();
        }
        return graph;
    }

    /**
     * Builds the expected paths bottom-up for a graph whose edges point from lower to
     * higher node numbers. The target is treated as the end of a path, even if it has
     * outgoing edges.
     */
    private static Set<List<Integer>> expectedPathsBottomUp(int[][] graph) {
        List<Set<List<Integer>>> pathsFrom = new ArrayList<>();
        for (int node = 0; node < graph.length; node++) {
            pathsFrom.add(new HashSet<>());
        }
        pathsFrom.get(graph.length - 1).add(List.of(graph.length - 1));

        for (int node = graph.length - 2; node >= 0; node--) {
            for (int next : graph[node]) {
                for (List<Integer> suffix : pathsFrom.get(next)) {
                    List<Integer> path = new ArrayList<>();
                    path.add(node);
                    path.addAll(suffix);
                    pathsFrom.get(node).add(path);
                }
            }
        }

        return pathsFrom.getFirst();
    }

    private static void assertStrictlyIncreasing(List<Integer> path) {
        for (int index = 1; index < path.size(); index++) {
            assertTrue(path.get(index - 1) < path.get(index), "Path is not strictly increasing: " + path);
        }
    }

    private static int[][] deepCopy(int[][] graph) {
        int[][] copy = new int[graph.length][];
        for (int node = 0; node < graph.length; node++) {
            copy[node] = graph[node].clone();
        }
        return copy;
    }
}

package solutions.unionfind;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class FindRedundantConnection_684Test {

    @Test
    @Timeout(10)
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

    @Test
    @Timeout(10)
    public void testExhaustiveConnectedSimpleUnicyclicGraphsThroughFiveVertices() {
        runIsolated("exhaustive");
    }

    private void runExhaustiveCheck() {
        // Enumerate every n-edge subset of the simple-graph edges for n=3,4,5; retain connected graphs.
        // Such a graph has exactly one cycle. Check every order through n=4 and forward/reverse order at n=5.
        for (int n = 3; n <= 5; n++) {
            final int vertexCount = n;
            List<int[]> possibleEdges = new ArrayList<>();
            for (int a = 1; a <= n; a++) {
                for (int b = a + 1; b <= n; b++) {
                    possibleEdges.add(new int[]{a, b});
                }
            }
            enumerateEdgeSets(vertexCount, possibleEdges, 0, new ArrayList<>(), edges -> {
                if (isConnected(vertexCount, edges)) {
                    if (vertexCount <= 4) {
                        enumerateOrders(edges, 0, "n=" + vertexCount);
                    } else {
                        assertMatchesIndependentOracle(edges, "ascending, n=" + vertexCount);
                        List<int[]> reversed = new ArrayList<>(edges);
                        java.util.Collections.reverse(reversed);
                        assertMatchesIndependentOracle(reversed, "descending, n=" + vertexCount);
                    }
                }
            });
        }
    }


    private final FindRedundantConnection_684 test = new FindRedundantConnection_684();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{2, 3}, test.findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}}));
        assertArrayEquals(new int[]{1, 4}, test.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}}));
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
    @Timeout(10)
    public void testGiantCase() {
        runIsolated("giant");
    }

    private void runGiantCheck() {
        // Maximum published n=1000, with a long chain and a cycle-closing edge well inside the vertex range.
        int[][] edges = new int[1000][2];
        for (int i = 0; i < 999; i++) {
            edges[i] = new int[]{i + 1, i + 2};
        }
        edges[999] = new int[]{500, 1000};
        assertArrayEquals(new int[]{500, 1000}, test.findRedundantConnection(edges));
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
                // The second component is larger, so union must attach the first (left) component beneath it.
                Arguments.of(new int[][]{{1, 2}, {3, 4}, {4, 5}, {2, 5}, {1, 3}}, new int[]{1, 3}),
                // The first component is larger, so union must attach the second (right) component beneath it.
                Arguments.of(new int[][]{{1, 2}, {2, 3}, {4, 5}, {3, 5}, {1, 4}}, new int[]{1, 4}),
                // Joining two size-two components covers the equal-size tie before the final cycle edge.
                Arguments.of(new int[][]{{1, 2}, {3, 4}, {2, 4}, {1, 3}}, new int[]{1, 3}),
                Arguments.of(new int[][]{{1, 2}, {1, 3}, {1, 4}, {4, 5}, {5, 6}, {2, 6}}, new int[]{2, 6}),
                Arguments.of(new int[][]{{1, 2}, {2, 3}, {2, 4}, {4, 5}, {5, 6}, {3, 6}}, new int[]{3, 6}),
                Arguments.of(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}, {5, 6}, {6, 7}, {7, 8}, {8, 9}, {9, 10}, {10, 11}, {11, 12}, {12, 13}, {13, 14}, {14, 15}, {4, 15}}, new int[]{1, 5}),
                Arguments.of(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 8}, {8, 9}, {9, 10}, {10, 11}, {11, 12}, {12, 13}, {13, 14}, {14, 15}, {15, 16}, {16, 17}, {17, 18}, {18, 19}, {19, 20}, {1, 20}}, new int[]{1, 20}),
                Arguments.of(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 8}, {8, 9}, {9, 10}, {10, 11}, {11, 12}, {12, 13}, {13, 14}, {14, 15}, {15, 16}, {16, 17}, {17, 18}, {18, 19}, {19, 20}, {10, 20}}, new int[]{10, 20})
        );
    }

    private static void enumerateEdgeSets(int n, List<int[]> possible, int next,
                                          List<int[]> chosen, java.util.function.Consumer<List<int[]>> check) {
        if (chosen.size() == n) {
            check.accept(new ArrayList<>(chosen));
            return;
        }
        int needed = n - chosen.size();
        for (int i = next; i <= possible.size() - needed; i++) {
            chosen.add(possible.get(i));
            enumerateEdgeSets(n, possible, i + 1, chosen, check);
            chosen.remove(chosen.size() - 1);
        }
    }

    private static void enumerateOrders(List<int[]> edges, int index, String context) {
        if (index == edges.size()) {
            assertMatchesIndependentOracle(edges, context + ", order=" + Arrays.deepToString(edges.toArray()));
            return;
        }
        for (int i = index; i < edges.size(); i++) {
            java.util.Collections.swap(edges, index, i);
            enumerateOrders(edges, index + 1, context);
            java.util.Collections.swap(edges, index, i);
        }
    }

    private static boolean isConnected(int n, List<int[]> edges) {
        boolean[] reached = new boolean[n + 1];
        reached[1] = true;
        for (int pass = 0; pass < n; pass++) {
            for (int[] edge : edges) {
                if (reached[edge[0]] || reached[edge[1]]) {
                    reached[edge[0]] = true;
                    reached[edge[1]] = true;
                }
            }
        }
        for (int vertex = 1; vertex <= n; vertex++) {
            if (!reached[vertex]) return false;
        }
        return true;
    }

    private static void assertMatchesIndependentOracle(List<int[]> edges, String context) {
        int[][] input = edges.stream().map(int[]::clone).toArray(int[][]::new);
        int[][] before = Arrays.stream(input).map(int[]::clone).toArray(int[][]::new);
        int[] expected = null;
        for (int omitted = 0; omitted < input.length; omitted++) {
            if (isConnectedWithoutEdge(input, omitted)) expected = input[omitted].clone();
        }
        assertArrayEquals(expected, new FindRedundantConnection_684().findRedundantConnection(input), context);
        assertArrayEquals(before, input, "input mutation: " + context);
    }

    private static boolean isConnectedWithoutEdge(int[][] edges, int omitted) {
        int n = edges.length;
        boolean[] reached = new boolean[n + 1];
        reached[1] = true;
        for (int pass = 0; pass < n; pass++) {
            for (int i = 0; i < edges.length; i++) {
                if (i != omitted && (reached[edges[i][0]] || reached[edges[i][1]])) {
                    reached[edges[i][0]] = true;
                    reached[edges[i][1]] = true;
                }
            }
        }
        for (int vertex = 1; vertex <= n; vertex++) {
            if (!reached[vertex]) return false;
        }
        return true;
    }

    private static void runIsolated(String mode) {
        String javaExecutable = System.getProperty("java.home") + "/bin/java";
        Process process;
        try {
            process = new ProcessBuilder(javaExecutable, "-cp", System.getProperty("java.class.path"),
                    Worker.class.getName(), mode).redirectErrorStream(true).start();
            if (!process.waitFor(10, java.util.concurrent.TimeUnit.SECONDS)) {
                process.destroyForcibly();
                if (!process.waitFor(2, java.util.concurrent.TimeUnit.SECONDS)) {
                    throw new AssertionError("worker did not terminate after forced stop: " + mode);
                }
                throw new AssertionError("isolated worker timed out: " + mode);
            }
            String output = new String(process.getInputStream().readAllBytes(), java.nio.charset.StandardCharsets.UTF_8);
            if (process.exitValue() != 0) {
                throw new AssertionError("isolated worker failed for " + mode + ": " + output);
            }
        } catch (java.io.IOException e) {
            throw new AssertionError("could not start isolated worker: " + mode, e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError("interrupted while waiting for isolated worker: " + mode, e);
        }
    }

    public static class Worker {
        public static void main(String[] args) {
            FindRedundantConnection_684Test test = new FindRedundantConnection_684Test();
            if ("exhaustive".equals(args[0])) {
                test.runExhaustiveCheck();
            } else if ("giant".equals(args[0])) {
                test.runGiantCheck();
            } else {
                throw new IllegalArgumentException("unknown mode: " + args[0]);
            }
        }
    }
}

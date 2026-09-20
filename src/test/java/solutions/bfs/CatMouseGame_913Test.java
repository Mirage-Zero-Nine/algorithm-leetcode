package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

/** Contract and regression tests for the finite-state Cat and Mouse game. */
public class CatMouseGame_913Test {
    private final CatMouseGame_913 solution = new CatMouseGame_913();

    @Test
    void officialExamplesAndOriginalFixtures() {
        assertKnownOutcome(0, new int[][]{{2, 5}, {3}, {0, 4, 5}, {1, 4, 5}, {2, 3}, {0, 2, 3}});
        assertKnownOutcome(1, new int[][]{{1, 3}, {0}, {3}, {0, 2}});
        assertOutcome(new int[][]{{1}, {0, 2}, {1}});
        assertKnownOutcome(2, new int[][]{{2}, {2, 3}, {0, 1, 3}, {1, 2}});
        assertOutcome(new int[][]{{1}, {0, 2}, {1, 3}, {2}});
        assertOutcome(new int[][]{{1, 3}, {0, 2, 3}, {1, 3}, {0, 1, 2}});
        assertOutcome(new int[][]{{2, 3}, {2}, {0, 1, 3}, {0, 2}});
        assertOutcome(new int[][]{{1}, {0, 2, 4}, {1, 3, 4}, {2}, {1, 2}});
        assertOutcome(new int[][]{{1, 2}, {0, 2}, {0, 1}});
    }

    @Test
    void handCraftedGraphsCoverWinsDrawsAndDifferentTopologies() {
        assertOutcome(new int[][]{{1}, {0, 2}, {1}});
        assertOutcome(new int[][]{{1, 2}, {0, 2}, {0, 1}});
        assertOutcome(new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}});
        assertOutcome(completeGraph(5));
        assertOutcome(completeGraph(6));
        assertOutcome(new int[][]{{1, 2}, {0, 2, 3}, {0, 1, 4}, {1, 4}, {2, 3}});
        assertOutcome(new int[][]{{1, 5}, {0, 2, 4}, {1, 3}, {2, 4}, {1, 3, 5}, {0, 4}});
        assertOutcome(new int[][]{{1, 2}, {0, 2, 3}, {0, 1, 3, 4}, {1, 2, 4}, {2, 3, 5}, {4}});
        assertOutcome(new int[][]{{1, 7}, {0, 2, 6}, {1, 3, 5}, {2, 4}, {3, 5}, {2, 4, 6}, {1, 5, 7}, {0, 6}});
    }

    @Test
    void deterministicSparseAndDenseGraphsUseIndependentGameOracle() {
        int[] sizes = {3, 4, 5, 6, 7, 8, 9, 10, 12, 15, 20, 30, 49, 50};
        for (int i = 0; i < sizes.length; i++) {
            assertOutcome(randomUndirectedGraph(sizes[i], 913L + i));
        }
        assertOutcome(randomUndirectedGraph(50, 20260918L));
        assertOutcome(completeGraph(50));
    }

    @Test
    void largeGeneratedGraphsExerciseEachOutcome() {
        int[] counts = new int[3];
        for (int i = 0; i < 40; i++) {
            counts[assertOutcome(randomUndirectedGraph(20 + (i % 31), 7000L + i))]++;
        }
        counts[assertOutcome(expandCore(new int[][]{{2}, {2, 3}, {0, 1, 3}, {1, 2}}, 50, 2, 3))]++;
        counts[assertOutcome(expandCore(new int[][]{{2, 5}, {3}, {0, 4, 5}, {1, 4, 5}, {2, 3}, {0, 2, 3}}, 50, 3, 4))]++;
        counts[assertOutcome(completeGraphWithout01(50))]++;
        assertTrue(counts[0] > 0 && counts[1] > 0 && counts[2] > 0,
                "large generated outcome counts=" + Arrays.toString(counts));
    }

    @Test
    void repeatedCallsDoNotShareState() {
        int[][] first = {{2, 5}, {3}, {0, 4, 5}, {1, 4, 5}, {2, 3}, {0, 2, 3}};
        int[][] second = {{1, 3}, {0}, {3}, {0, 2}};
        assertOutcome(first);
        assertOutcome(second);
        assertOutcome(first);
        assertOutcome(second);
    }

    @Test
    void oracleProducesAllThreePossibleResultsAcrossTheSuite() {
        int[][][] cases = {
                {{2, 5}, {3}, {0, 4, 5}, {1, 4, 5}, {2, 3}, {0, 2, 3}},
                {{1, 3}, {0}, {3}, {0, 2}},
                {{1, 2}, {0, 2}, {0, 1}},
                {{1, 3}, {0, 2}, {1, 3}, {0, 2}},
                {{2}, {2, 3}, {0, 1, 3}, {1, 2}},
                randomUndirectedGraph(8, 91),
                randomUndirectedGraph(10, 17),
                completeGraph(7)
        };
        boolean[] seen = new boolean[3];
        for (int[][] graph : cases) {
            int expected = independentOutcome(graph);
            assertTrue(expected >= 0 && expected <= 2);
            seen[expected] = true;
            assertEquals(expected, solution.catMouseGame(copy(graph)));
        }
        assertTrue(seen[0] && seen[1] && seen[2], "fixtures must exercise draw, mouse, and cat outcomes");
    }

    @Test
    void exhaustiveFourNodeGraphsMatchIndependentRetrogradeOracle() {
        int[] outcomeCounts = new int[3];
        int graphCount = 0;
        for (int[][] graph : allValidUndirectedGraphs(4)) {
            outcomeCounts[assertOutcome(graph)]++;
            graphCount++;
        }

        // This exercises every edge subset that satisfies the published graph contract,
        // including sparse terminal branches and dense cycle-heavy positions.
        assertTrue(graphCount > 20, "small-graph enumeration should be substantial");
        assertTrue(outcomeCounts[1] > 0 && outcomeCounts[2] > 0,
                "enumeration must contain both terminal outcomes: " + Arrays.toString(outcomeCounts));
    }

    @Test
    void exhaustiveFiveNodeGraphsCoverHoleAndCycleInteractions() {
        int[] outcomeCounts = new int[3];
        int graphCount = 0;
        for (int[][] graph : allValidUndirectedGraphs(5)) {
            outcomeCounts[assertOutcome(graph)]++;
            graphCount++;
        }

        // 1,024 edge subsets are still small enough to keep this test well below the
        // 15-second test limit while exercising many independent game-state graphs.
        assertTrue(graphCount > 100, "five-node enumeration should be substantial");
        assertTrue(outcomeCounts[0] > 0 && outcomeCounts[1] > 0 && outcomeCounts[2] > 0,
                "enumeration must contain all outcomes: " + Arrays.toString(outcomeCounts));
    }

    @Test
    void terminalNeighborsAndCatHoleRestrictionAreHandled() {
        // The mouse is forced onto the cat on its first move.
        assertOutcome(new int[][]{{3}, {2}, {1, 3}, {0, 2}});

        // The mouse has a direct hole move, which is immediately winning.
        assertOutcome(new int[][]{{1, 3}, {0, 2}, {1, 3, 4}, {0, 2, 4}, {2, 3}});

        // The cat is adjacent to the hole but must choose a non-hole edge.  This
        // specifically guards the rule that cat->0 is never a legal transition.
        assertOutcome(new int[][]{{2, 4}, {2}, {0, 1, 3}, {2, 4}, {0, 3}});

        // A sparse graph with a long cycle and no 0-1 shortcut exercises unresolved
        // repeated-position states rather than an immediate terminal move.
        assertOutcome(new int[][]{{3}, {2, 3}, {1, 3}, {0, 1, 2}});
    }

    @Test
    void independentOracleChecksSeededSmallGraphsAndInputIsolation() {
        for (int n = 3; n <= 8; n++) {
            for (int seed = 0; seed < 12; seed++) {
                int[][] graph = randomUndirectedGraph(n, 913_000L + n * 100L + seed);
                int[][] before = copy(graph);
                int expected = independentOutcome(graph);
                assertEquals(expected, solution.catMouseGame(graph),
                        "seeded graph n=" + n + ", seed=" + seed);
                assertTrue(Arrays.deepEquals(before, graph), "graph input was mutated");
            }
        }
    }

    @Test
    void pathWithHoleAtOneEnd() {
        assertOutcome(new int[][]{{5}, {2}, {1, 3}, {2, 4}, {3, 5}, {0, 4}});
    }

    @Test
    void cycleWithSeparateHoleBranch() {
        assertOutcome(new int[][]{{4}, {2, 3}, {1, 3}, {1, 2, 4}, {0, 3}});
    }

    @Test
    void mouseShortcutAndCatDetour() {
        assertOutcome(new int[][]{{3, 4}, {3}, {3, 4}, {0, 1, 2}, {0, 2}});
    }

    @Test
    void sparseFiveNodeCycle() {
        assertOutcome(new int[][]{{4}, {2}, {1, 3}, {2, 4}, {0, 3}});
    }

    @Test
    void denseSixNodeGraph() {
        assertOutcome(completeGraph(6));
    }

    @Test
    void denseGraphWithoutMouseHoleShortcut() {
        assertOutcome(completeGraphWithout01(7));
    }

    @Test
    void expandedCycleExercisesDrawStates() {
        assertOutcome(expandCore(new int[][]{{2}, {2, 3}, {0, 1, 3}, {1, 2}}, 12, 2, 3));
    }

    @Test
    void generatedSmallGraphOne() {
        assertOutcome(randomUndirectedGraph(9, 913_100L));
    }

    @Test
    void generatedSmallGraphTwo() {
        assertOutcome(randomUndirectedGraph(11, 913_101L));
    }

    @Test
    void generatedSmallGraphThree() {
        assertOutcome(randomUndirectedGraph(13, 913_102L));
    }

    private int assertOutcome(int[][] graph) {
        validateOfficialGraph(graph);
        int[][] before = copy(graph);
        int expected = independentOutcome(graph);
        int[][] actualInput = copy(graph);
        int actual = solution.catMouseGame(actualInput);
        assertEquals(expected, actual, "unexpected result for graph=" + Arrays.deepToString(graph));
        assertTrue(Arrays.deepEquals(before, actualInput), "solution must not mutate graph");
        return expected;
    }

    private void assertKnownOutcome(int expected, int[][] graph) {
        validateOfficialGraph(graph);
        assertEquals(expected, independentOutcome(graph), "independent oracle disagrees with known answer");
        int[][] before = copy(graph);
        int[][] actualInput = copy(graph);
        assertEquals(expected, solution.catMouseGame(actualInput));
        assertTrue(Arrays.deepEquals(before, actualInput), "solution must not mutate graph");
    }

    /** Least fixed point of the game equations; unresolved cycles remain draws. */
    private static int independentOutcome(int[][] graph) {
        int n = graph.length;
        int[][][] result = new int[n][n][2];
        for (int other = 0; other < n; other++) {
            for (int turn = 0; turn < 2; turn++) {
                result[0][other][turn] = 1;
                if (other > 0) {
                    result[other][other][turn] = 2;
                }
            }
        }
        boolean changed;
        do {
            changed = false;
            for (int mouse = 0; mouse < n; mouse++) {
                for (int cat = 1; cat < n; cat++) {
                    if (mouse == cat) continue;
                    if (result[mouse][cat][0] == 0) {
                        boolean allCat = true;
                        for (int nextMouse : graph[mouse]) {
                            if (result[nextMouse][cat][1] == 1) {
                                result[mouse][cat][0] = 1;
                                changed = true;
                                allCat = false;
                                break;
                            }
                            allCat &= result[nextMouse][cat][1] == 2;
                        }
                        if (result[mouse][cat][0] == 0 && allCat) {
                            result[mouse][cat][0] = 2;
                            changed = true;
                        }
                    }
                    if (result[mouse][cat][1] == 0) {
                        boolean allMouse = true;
                        for (int nextCat : graph[cat]) {
                            if (nextCat == 0) continue;
                            if (result[mouse][nextCat][0] == 2) {
                                result[mouse][cat][1] = 2;
                                changed = true;
                                allMouse = false;
                                break;
                            }
                            allMouse &= result[mouse][nextCat][0] == 1;
                        }
                        if (result[mouse][cat][1] == 0 && allMouse) {
                            result[mouse][cat][1] = 1;
                            changed = true;
                        }
                    }
                }
            }
        } while (changed);
        return result[1][2][0];
    }

    private static int[][] completeGraph(int n) {
        int[][] graph = new int[n][];
        for (int i = 0; i < n; i++) {
            graph[i] = new int[n - 1];
            int index = 0;
            for (int j = 0; j < n; j++) if (j != i) graph[i][index++] = j;
        }
        return graph;
    }

    /**
     * Enumerate all simple undirected graphs of a small size that satisfy LeetCode's
     * non-empty-adjacency and initial-cat-move guarantees.  Expected outcomes still come
     * from {@link #independentOutcome(int[][])}, never from graph construction.
     */
    private static List<int[][]> allValidUndirectedGraphs(int n) {
        List<int[][]> graphs = new ArrayList<>();
        int edgeCount = n * (n - 1) / 2;
        int totalMasks = 1 << edgeCount;
        for (int mask = 0; mask < totalMasks; mask++) {
            boolean[][] edges = new boolean[n][n];
            int bit = 0;
            for (int left = 0; left < n; left++) {
                for (int right = left + 1; right < n; right++) {
                    if ((mask & (1 << bit++)) != 0) {
                        edges[left][right] = true;
                        edges[right][left] = true;
                    }
                }
            }

            boolean valid = true;
            for (int node = 0; node < n; node++) {
                boolean hasNeighbor = false;
                for (int neighbor = 0; neighbor < n; neighbor++) hasNeighbor |= edges[node][neighbor];
                valid &= hasNeighbor;
            }
            boolean initialCatCanMove = false;
            for (int neighbor = 1; neighbor < n; neighbor++) initialCatCanMove |= edges[2][neighbor];
            if (!valid || !initialCatCanMove) continue;

            int[][] graph = new int[n][];
            for (int node = 0; node < n; node++) {
                int degree = 0;
                for (int neighbor = 0; neighbor < n; neighbor++) if (edges[node][neighbor]) degree++;
                graph[node] = new int[degree];
                int index = 0;
                for (int neighbor = 0; neighbor < n; neighbor++) {
                    if (edges[node][neighbor]) graph[node][index++] = neighbor;
                }
            }
            graphs.add(graph);
        }
        return graphs;
    }

    private static int[][] completeGraphWithout01(int n) {
        int[][] graph = completeGraph(n);
        graph[0] = Arrays.stream(graph[0]).filter(value -> value != 1).toArray();
        graph[1] = Arrays.stream(graph[1]).filter(value -> value != 0).toArray();
        return graph;
    }

    private static int[][] expandCore(int[][] core, int n, int anchorA, int anchorB) {
        int[][] graph = new int[n][];
        for (int i = 0; i < core.length; i++) graph[i] = core[i].clone();
        for (int i = core.length; i < n; i++) graph[i] = new int[]{anchorA, anchorB};
        graph[anchorA] = append(graph[anchorA], core.length, n);
        graph[anchorB] = append(graph[anchorB], core.length, n);
        return graph;
    }

    private static int[] append(int[] original, int from, int to) {
        int[] result = Arrays.copyOf(original, original.length + to - from);
        for (int i = from; i < to; i++) result[original.length + i - from] = i;
        return result;
    }

    private static int[][] randomUndirectedGraph(int n, long seed) {
        Random random = new Random(seed);
        boolean[][] edge = new boolean[n][n];
        // Keep 0-1 absent: otherwise every generated graph has the trivial 1 -> 0 mouse win.
        for (int i = 1; i < n - 1; i++) addEdge(edge, i, i + 1);
        addEdge(edge, 0, n - 1);
        double probability = n >= 40 ? 0.12 : 0.28;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((i != 0 || j != 1) && random.nextDouble() < probability) addEdge(edge, i, j);
            }
        }
        int[][] graph = new int[n][];
        for (int i = 0; i < n; i++) {
            List<Integer> neighbors = new ArrayList<>();
            for (int j = 0; j < n; j++) if (edge[i][j]) neighbors.add(j);
            graph[i] = neighbors.stream().mapToInt(Integer::intValue).toArray();
        }
        return graph;
    }

    private static void addEdge(boolean[][] edge, int a, int b) {
        edge[a][b] = true;
        edge[b][a] = true;
    }

    private static void validateOfficialGraph(int[][] graph) {
        int n = graph.length;
        assertTrue(n >= 3 && n <= 50, "node count outside official contract");
        for (int node = 0; node < n; node++) {
            assertTrue(graph[node].length >= 1 && graph[node].length < n, "invalid degree at node " + node);
            boolean[] seen = new boolean[n];
            for (int neighbor : graph[node]) {
                assertTrue(neighbor >= 0 && neighbor < n, "edge endpoint outside graph");
                assertTrue(neighbor != node, "self-loop");
                assertTrue(!seen[neighbor], "duplicate edge");
                seen[neighbor] = true;
                assertTrue(contains(graph[neighbor], node), "graph must be undirected");
            }
            // The published guarantee concerns the initial Cat position (node 2);
            // later Cat positions may have only the hole as an adjacent node.
            if (node == 2) {
                boolean canMoveWithoutHole = false;
                for (int neighbor : graph[node]) canMoveWithoutHole |= neighbor > 0;
                assertTrue(canMoveWithoutHole, "Cat must have a legal non-hole move");
            }
        }
    }

    private static boolean contains(int[] values, int target) {
        for (int value : values) if (value == target) return true;
        return false;
    }

    private static int[][] copy(int[][] graph) {
        int[][] copy = new int[graph.length][];
        for (int i = 0; i < graph.length; i++) copy[i] = graph[i].clone();
        return copy;
    }
}

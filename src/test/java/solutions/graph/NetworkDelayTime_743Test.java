package solutions.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;

@ExtendWith(NetworkDelayTime_743Test.IsolatedTestExtension.class)
public class NetworkDelayTime_743Test {

    @Test
    void reachesBothBranchesOfTheOfficialExample() {
        assertBoth(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2, 2);
    }

    @Test
    void reachesTheOtherNodeAcrossASingleDirectedEdge() {
        assertBoth(new int[][]{{1, 2, 1}}, 2, 1, 1);
    }

    @Test
    void cannotTraverseAnEdgeInReverse() {
        assertBoth(new int[][]{{1, 2, 1}}, 2, 2, -1);
    }

    @Test
    void reportsAnIsolatedReceiverAsUnreachable() {
        assertBoth(new int[][]{{1, 2, 4}}, 3, 1, -1);
    }

    @Test
    void followsAnUnorderedChainFromTheLargestNodeLabel() {
        assertBoth(new int[][]{{1, 3, 5}, {3, 2, 6}, {5, 4, 3}, {4, 1, 4}}, 5, 5, 18);
    }

    @Test
    void zeroWeightPathsCanReachEveryReceiverImmediately() {
        assertBoth(new int[][]{{1, 2, 0}, {2, 3, 0}, {3, 4, 0}}, 4, 1, 0);
    }

    @Test
    void zeroWeightCyclesTerminateWithoutRepeatedEqualRelaxations() {
        assertBoth(new int[][]{{1, 2, 0}, {2, 1, 0}, {2, 3, 0}, {3, 2, 0}, {3, 4, 9}}, 4, 1, 9);
    }

    @Test
    void positiveCyclesDoNotIncreaseShortestArrivalTimes() {
        assertBoth(new int[][]{{3, 1, 2}, {1, 2, 3}, {2, 3, 4}, {2, 4, 5}}, 4, 3, 10);
    }

    @Test
    void replacesLongDirectRoutesAndDiscardsStaleHeapEntries() {
        assertBoth(new int[][]{{1, 2, 50}, {1, 3, 2}, {3, 2, 2}, {2, 4, 3},
                {3, 4, 30}, {4, 5, 1}, {1, 5, 90}}, 5, 1, 8);
    }

    @Test
    void equalLengthAlternativesHaveTheSameArrivalTime() {
        assertBoth(new int[][]{{1, 2, 1}, {1, 3, 1}, {2, 4, 2}, {3, 4, 2}, {4, 5, 1}}, 5, 1, 4);
    }

    @Test
    void acceptsBothWeightBoundariesWithANonFirstSource() {
        assertBoth(new int[][]{{4, 1, 100}, {4, 2, 0}, {2, 3, 100}, {3, 1, 0}}, 4, 4, 100);
    }

    @Test
    void aDisconnectedZeroWeightCycleDoesNotBecomeReachable() {
        assertBoth(new int[][]{{1, 2, 0}, {2, 3, 0}, {4, 5, 0}, {5, 4, 0}}, 5, 1, -1);
    }

    @Test
    void incomingEdgesDoNotMakeTheirSourcesImmediatelyReachable() {
        assertBoth(new int[][]{{1, 2, 0}, {2, 3, 3}, {3, 4, 4}, {4, 1, 5}}, 4, 2, 12);
    }

    @Test
    void repeatedImprovementsReachTheFarthestLowNumberedNode() {
        assertBoth(new int[][]{{1, 2, 90}, {1, 3, 80}, {1, 4, 70}, {1, 5, 60},
                {1, 6, 1}, {6, 5, 1}, {5, 4, 1}, {4, 3, 1}, {3, 2, 1}}, 6, 1, 5);
    }

    @Test
    void aSourceAtTheEndOfAChainCannotReachPrecedingNodes() {
        assertBoth(new int[][]{{1, 2, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 4, -1);
    }

    @Test
    void anIsolatedSourceCannotReachAnotherStronglyConnectedComponent() {
        assertBoth(new int[][]{{1, 2, 1}, {2, 3, 1}, {3, 1, 1}}, 4, 4, -1);
    }

    @Test
    void anOutgoingStarUsesTheSlowestSpokeAsItsDelay() {
        assertBoth(new int[][]{{3, 1, 3}, {3, 2, 4}, {3, 4, 6}, {3, 5, 1}, {3, 6, 2}}, 6, 3, 6);
    }

    @Test
    void anIncomingStarCannotSendTheSignalBackToItsLeaves() {
        assertBoth(new int[][]{{1, 3, 3}, {2, 3, 4}, {4, 3, 6}, {5, 3, 1}, {6, 3, 2}}, 6, 3, -1);
    }

    @Test
    void aSourceInsideAOneWayChainCannotReachTheEarlierHalf() {
        assertBoth(new int[][]{{1, 2, 1}, {2, 3, 1}, {3, 4, 1}, {4, 5, 1}}, 5, 3, -1);
    }

    @Test
    void oppositeDirectedEdgesCanHaveDifferentTravelTimes() {
        assertBoth(new int[][]{{1, 2, 100}, {2, 1, 0}, {2, 3, 5}, {3, 2, 1},
                {1, 3, 20}, {3, 1, 90}}, 3, 1, 21);
    }

    @Test
    void aZeroWeightDetourCanReplaceAPositiveDirectEdge() {
        assertBoth(new int[][]{{1, 2, 3}, {1, 3, 0}, {3, 2, 0}}, 3, 1, 0);
    }

    @Test
    void selectionUsesDistanceInsteadOfNodeLabelOrder() {
        assertBoth(new int[][]{{4, 1, 9}, {4, 3, 1}, {3, 2, 1}, {2, 1, 1}}, 4, 4, 3);
    }

    @Test
    void aZeroWeightCycleContainingTheSourceReachesAllNodesAtTimeZero() {
        assertBoth(new int[][]{{2, 1, 0}, {1, 3, 0}, {3, 4, 0}, {4, 2, 0}}, 4, 2, 0);
    }

    @Test
    void connectedPositiveCyclesCanStillReachAnOutgoingTail() {
        assertBoth(new int[][]{{1, 2, 2}, {2, 3, 2}, {3, 1, 2}, {3, 4, 1},
                {4, 5, 1}, {5, 6, 1}, {6, 4, 1}, {6, 7, 1}}, 7, 1, 8);
    }

    @Test
    void aZeroWeightSuffixDoesNotIncreaseAnAccumulatedDelay() {
        assertBoth(new int[][]{{1, 2, 2}, {2, 3, 3}, {3, 4, 0}, {4, 5, 0}, {5, 6, 0}}, 6, 1, 5);
    }

    @Test
    void processingASinkDoesNotPreventExpansionOfOtherCandidates() {
        assertBoth(new int[][]{{1, 2, 1}, {1, 3, 2}, {3, 4, 3}}, 4, 1, 5);
    }

    @Test
    void aLongerPathByEdgeCountCanArriveEarlier() {
        assertBoth(new int[][]{{1, 2, 8}, {1, 3, 1}, {3, 4, 1}, {4, 5, 1},
                {5, 2, 1}, {2, 6, 1}, {1, 6, 9}}, 6, 1, 5);
    }

    @Test
    void aDirectEdgeCanBeatADetourWithIndividuallySmallerWeights() {
        assertBoth(new int[][]{{1, 2, 2}, {1, 3, 1}, {3, 4, 1}, {4, 2, 1}, {2, 5, 2}}, 5, 1, 4);
    }

    @Test
    void anUnusedMaximumWeightEdgeDoesNotDetermineTheDelay() {
        assertBoth(new int[][]{{1, 2, 100}, {1, 3, 1}, {3, 2, 1}, {2, 4, 1}}, 4, 1, 3);
    }

    @Test
    void theHighestLabelCanBeTheFastestReceiverAndAnIntermediateHub() {
        assertBoth(new int[][]{{1, 5, 0}, {5, 2, 7}, {5, 3, 2}, {3, 4, 1}}, 5, 1, 7);
    }

    @Test
    void tiesAtSeveralDistanceLevelsDoNotRequireASpecificVisitOrder() {
        assertBoth(new int[][]{{4, 1, 2}, {4, 7, 2}, {1, 2, 2}, {1, 3, 2},
                {7, 5, 2}, {7, 6, 2}, {2, 6, 0}, {3, 5, 0}}, 7, 4, 4);
    }

    @Test
    void directEdgesAreOptimalInACompleteUniformWeightGraph() {
        List<int[]> edges = new ArrayList<>();
        for (int from = 1; from <= 5; from++) {
            for (int to = 1; to <= 5; to++) {
                if (from != to) edges.add(new int[]{from, to, 100});
            }
        }
        assertBoth(edges.toArray(int[][]::new), 5, 3, 100);
    }

    @Test
    void aBridgeIntoTheSourceComponentCannotBeFollowedBackwards() {
        assertBoth(new int[][]{{1, 2, 1}, {2, 3, 1}, {3, 1, 1}, {3, 4, 1},
                {4, 5, 1}, {5, 6, 1}, {6, 4, 1}}, 6, 4, -1);
    }

    @Test
    void aBidirectionalChainAccumulatesDifferentDelaysOnEachSideOfTheSource() {
        List<int[]> edges = new ArrayList<>();
        for (int node = 1; node < 7; node++) {
            edges.add(new int[]{node, node + 1, 2});
            edges.add(new int[]{node + 1, node, 3});
        }
        assertBoth(edges.toArray(int[][]::new), 7, 4, 9);
    }

    @Test
    void preservesTheIntentionalNullAndEmptyEdgeListExtension() {
        // Published inputs require at least one edge. Both methods explicitly support this extension.
        assertBoth(null, 3, 2, -1);
        assertBoth(new int[0][], 3, 2, -1);
        assertBoth(null, 1, 1, -1);
        assertBoth(new int[0][], 1, 1, -1);
    }

    @Test
    void repeatedCallsOnOneInstanceKeepDistancesAndSettledFlagsLocal() {
        for (Approach approach : Approach.values()) {
            NetworkDelayTime_743 solution = new NetworkDelayTime_743();
            int[][] edges = {{1, 2, 2}, {2, 3, 3}};
            assertResult(approach, solution, edges, 3, 1, 5);
            assertResult(approach, solution, edges, 3, 3, -1);
            assertResult(approach, solution, new int[][]{{3, 2, 0}, {2, 1, 0}}, 3, 3, 0);
            assertResult(approach, solution, null, 3, 1, -1);
            assertResult(approach, solution, edges, 3, 1, 5);
        }
    }

    @Test
    void exhaustsEveryTwoNodeGraphAcrossAllPublishedWeightsAndSources() {
        // Each directed pair is absent or has weight 0..100: 102^2 - 1 nonempty graphs.
        // Testing both sources exhausts 20,806 valid graph/source inputs for n=2.
        for (int forward = -1; forward <= 100; forward++) {
            for (int backward = -1; backward <= 100; backward++) {
                if (forward == -1 && backward == -1) continue;
                List<int[]> edges = new ArrayList<>();
                if (forward >= 0) edges.add(new int[]{1, 2, forward});
                if (backward >= 0) edges.add(new int[]{2, 1, backward});
                int[][] graph = edges.toArray(int[][]::new);
                for (int source = 1; source <= 2; source++) {
                    // Captured output identifies the exact input if a solver stops making progress.
                    System.out.println("forward=" + forward + ", backward=" + backward + ", source=" + source);
                    assertBoth(graph, 2, source, expectedByFloydWarshall(graph, 2, source));
                }
            }
        }
    }

    @Test
    void exhaustsThreeNodeGraphsWithAbsentZeroOneOrTwoWeightEdges() {
        // Six ordered pairs, each absent or weighted 0/1/2: (4^6 - 1) * 3 = 12,285 inputs.
        // This exhausts the bounded topology/weight domain, not larger graphs or other weights.
        for (int encoding = 1; encoding < 4096; encoding++) {
            int digits = encoding;
            List<int[]> edges = new ArrayList<>();
            for (int from = 1; from <= 3; from++) {
                for (int to = 1; to <= 3; to++) {
                    if (from == to) continue;
                    int state = digits % 4;
                    digits /= 4;
                    if (state != 0) edges.add(new int[]{from, to, state - 1});
                }
            }
            int[][] graph = edges.toArray(int[][]::new);
            for (int source = 1; source <= 3; source++) {
                System.out.println("encoding=" + encoding + ", source=" + source);
                assertBoth(graph, 3, source, expectedByFloydWarshall(graph, 3, source));
            }
        }
    }

    @Test
    void checksSeededLargerGraphsAndShuffledEdgesAgainstAnIndependentOracle() {
        Random random = new Random(743L);
        for (int sample = 0; sample < 120; sample++) {
            int n = 4 + random.nextInt(17);
            int edgePercentage = new int[]{10, 50, 90}[sample % 3];
            List<int[]> edges = new ArrayList<>();
            for (int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    if (from != to && random.nextInt(100) < edgePercentage) {
                        edges.add(new int[]{from, to, random.nextInt(101)});
                    }
                }
            }
            if (edges.isEmpty()) edges.add(new int[]{1, 2, 100});
            Collections.shuffle(edges, random);
            int[][] graph = edges.toArray(int[][]::new);
            for (int source = 1; source <= n; source++) {
                System.out.println("seed=743, sample=" + sample + ", n=" + n + ", source=" + source);
                assertBoth(graph, n, source, expectedByFloydWarshall(graph, n, source));
            }
        }
    }

    @Test
    void maximumLengthMaximumWeightChainAccumulatesBeyondOneEdgeWeight() {
        int[][] graph = new int[99][3];
        for (int i = 0; i < graph.length; i++) graph[i] = new int[]{i + 1, i + 2, 100};
        // A shortest simple path has at most n-1 edges, so 9,900 is the largest finite delay.
        assertBoth(graph, 100, 1, 9900);
    }

    @Test
    void maximumEdgeCountWithAZeroWeightBackboneReachesEveryNodeAtTimeZero() {
        assertBoth(denseGraph(false, true), 100, 1, 0);
    }

    @Test
    void maximumEdgeCountCanStillLeaveOneReceiverUnreachable() {
        assertBoth(denseGraph(true, true), 100, 1, -1);
    }

    @Test
    void maximumMixedWeightGraphMatchesTheOracleFromBothBoundarySources() {
        int[][] graph = denseGraph(false, false);
        assertBoth(graph, 100, 1, expectedByFloydWarshall(graph, 100, 1));
        assertBoth(graph, 100, 100, expectedByFloydWarshall(graph, 100, 100));
    }

    private void assertBoth(int[][] times, int n, int source, int expected) {
        for (Approach approach : Approach.values()) {
            assertResult(approach, new NetworkDelayTime_743(), times, n, source, expected);
        }
    }

    private void assertResult(Approach approach, NetworkDelayTime_743 solution, int[][] times,
                              int n, int source, int expected) {
        int[][] input = copy(times);
        String context = approach + ", n=" + n + ", source=" + source + ", times=" + Arrays.deepToString(times);
        assertEquals(expected, approach.solve(solution, input, n, source), context);
        assertTrue(Arrays.deepEquals(times, input), "input mutation: " + context);
    }

    private static int expectedByFloydWarshall(int[][] times, int n, int source) {
        // All-pairs dynamic programming allows each intermediate node in turn. It does not use
        // Dijkstra's greedy selection, a heap, or either solution method to derive expected results.
        long infinity = Long.MAX_VALUE / 4;
        long[][] shortest = new long[n][n];
        for (int node = 0; node < n; node++) {
            Arrays.fill(shortest[node], infinity);
            shortest[node][node] = 0;
        }
        for (int[] edge : times) shortest[edge[0] - 1][edge[1] - 1] = edge[2];
        for (int via = 0; via < n; via++) {
            for (int from = 0; from < n; from++) {
                for (int to = 0; to < n; to++) {
                    shortest[from][to] = Math.min(shortest[from][to], shortest[from][via] + shortest[via][to]);
                }
            }
        }
        long delay = 0;
        for (long distance : shortest[source - 1]) {
            if (distance == infinity) return -1;
            delay = Math.max(delay, distance);
        }
        return (int) delay;
    }

    private static int[][] denseGraph(boolean isolateLastNode, boolean zeroBackbone) {
        int[][] graph = new int[6000][3];
        boolean[][] used = new boolean[101][101];
        int edgeCount = 0;
        int lastReceiver = isolateLastNode ? 99 : 100;
        for (int from = 1; from < lastReceiver; from++) {
            graph[edgeCount++] = new int[]{from, from + 1, zeroBackbone ? 0 : 100};
            used[from][from + 1] = true;
        }
        if (!isolateLastNode) {
            // Complete a directed ring so both boundary sources reach the entire giant graph.
            graph[edgeCount++] = new int[]{100, 1, zeroBackbone ? 0 : 100};
            used[100][1] = true;
        }
        for (int from = 1; from <= 100 && edgeCount < graph.length; from++) {
            for (int to = 1; to <= lastReceiver && edgeCount < graph.length; to++) {
                if (from != to && !used[from][to]) {
                    graph[edgeCount++] = new int[]{from, to, (from * 37 + to * 19) % 101};
                }
            }
        }
        assertEquals(6000, edgeCount);
        return graph;
    }

    private static int[][] copy(int[][] input) {
        if (input == null) return null;
        return Arrays.stream(input).map(int[]::clone).toArray(int[][]::new);
    }

    private enum Approach {
        HEAP, LINEAR_SCAN;

        int solve(NetworkDelayTime_743 solution, int[][] times, int n, int source) {
            return this == HEAP ? solution.networkDelayTime(times, n, source)
                    : solution.networkDelayTimeLinearScan(times, n, source);
        }
    }

    // Interruption cannot stop a tight solution loop. Every test body runs in a child JVM instead;
    // the parent enforces ten seconds, forces termination on timeout, and verifies bounded cleanup.
    public static final class IsolatedTestExtension implements InvocationInterceptor {
        @Override
        public void interceptTestMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> context,
                                        ExtensionContext extensionContext) throws Throwable {
            Path output = Files.createTempFile("network-delay-test-", ".log");
            Process process = null;
            try {
                String classPath = System.getProperty("surefire.test.class.path", System.getProperty("java.class.path"));
                process = new ProcessBuilder(Path.of(System.getProperty("java.home"), "bin", "java").toString(),
                        "-cp", classPath, Worker.class.getName(), context.getExecutable().getName())
                        .redirectErrorStream(true).redirectOutput(output.toFile()).start();
                if (!process.waitFor(10, TimeUnit.SECONDS)) {
                    process.destroyForcibly();
                    boolean terminated = process.waitFor(2, TimeUnit.SECONDS);
                    throw new AssertionError("Timed out: " + context.getExecutable().getName()
                            + ", child terminated=" + terminated + "\n" + outputTail(output));
                }
                if (process.exitValue() != 0) {
                    throw new AssertionError("Child test failed: " + context.getExecutable().getName()
                            + "\n" + outputTail(output));
                }
                invocation.skip();
            } finally {
                if (process != null && process.isAlive()) process.destroyForcibly();
                if (process != null && !process.waitFor(2, TimeUnit.SECONDS)) {
                    throw new AssertionError("Child did not terminate: " + context.getExecutable().getName());
                }
                Files.deleteIfExists(output);
            }
        }

        private static String outputTail(Path output) throws java.io.IOException {
            String text = Files.readString(output);
            return text.substring(Math.max(0, text.length() - 32000));
        }
    }

    // Invoke the selected body directly so its JUnit extension does not recursively spawn workers.
    public static final class Worker {
        public static void main(String[] args) throws Exception {
            if (args.length != 1) throw new IllegalArgumentException("Expected one test method name");
            Method method = NetworkDelayTime_743Test.class.getDeclaredMethod(args[0]);
            try {
                method.invoke(new NetworkDelayTime_743Test());
            } catch (InvocationTargetException exception) {
                Throwable cause = exception.getCause();
                if (cause instanceof Exception checked) throw checked;
                if (cause instanceof Error error) throw error;
                throw exception;
            }
        }
    }
}

package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;
import org.junit.jupiter.api.Test;

/** Tests for the alternating-color shortest-path BFS. */
public class ShortestAlternatingPaths_1129Test {

    private final ShortestAlternatingPaths_1129 solution = new ShortestAlternatingPaths_1129();

    @Test
    public void testOfficialExamples() {
        assertArrayEquals(new int[]{0, 1, -1},
            solution.shortestAlternatingPaths(3, new int[][]{{0, 1}, {1, 2}}, new int[][]{}));
        assertArrayEquals(new int[]{0, 1, -1},
            solution.shortestAlternatingPaths(3, new int[][]{{0, 1}}, new int[][]{{2, 1}}));
    }

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{0, 1, 2},
            solution.shortestAlternatingPaths(3, new int[][]{{0, 1}}, new int[][]{{1, 2}}));
        assertArrayEquals(new int[]{0, 1, -1},
            solution.shortestAlternatingPaths(3, new int[][]{{0, 1}}, new int[][]{}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[]{0}, solution.shortestAlternatingPaths(1, new int[][]{}, new int[][]{}));
        assertArrayEquals(new int[]{0, -1, -1},
            solution.shortestAlternatingPaths(3, new int[][]{}, new int[][]{}));
    }

    @Test
    public void testLargeCase() {
        assertArrayEquals(new int[]{0, 1, 2, 3},
            solution.shortestAlternatingPaths(4, new int[][]{{0, 1}, {2, 3}}, new int[][]{{1, 2}}));
    }

    @Test
    public void testAlternationCanStartWithBlue() {
        int[][] red = {{1, 2}, {3, 0}};
        int[][] blue = {{0, 1}, {2, 3}};
        assertArrayEquals(new int[]{0, 1, 2, 3}, solution.shortestAlternatingPaths(4, red, blue));
    }

    @Test
    public void testShortestPathUsesAlternatingRoute() {
        int[][] red = {{0, 1}, {1, 2}, {2, 3}, {0, 3}};
        int[][] blue = {{1, 2}, {2, 3}};
        assertArrayEquals(oracle(4, red, blue), solution.shortestAlternatingPaths(4, red, blue));
        assertArrayEquals(new int[]{0, 1, 2, 1}, solution.shortestAlternatingPaths(4, red, blue));
    }

    @Test
    public void testSameColorContinuationIsRejected() {
        int[][] red = {{0, 1}, {1, 2}, {1, 3}};
        int[][] blue = {{0, 2}, {2, 3}};
        assertArrayEquals(new int[]{0, 1, 1, -1}, solution.shortestAlternatingPaths(4, red, blue));
    }

    @Test
    public void testColorStateMustBeRetainedForLaterEdges() {
        int[][] red = {{0, 1}, {0, 2}, {2, 3}};
        int[][] blue = {{0, 1}, {1, 3}};
        // Node 1 is reachable in one step by either color; both resulting states matter.
        assertArrayEquals(new int[]{0, 1, 1, 2}, solution.shortestAlternatingPaths(4, red, blue));
    }

    @Test
    public void testDisconnectedComponentsAndUnreachableNodes() {
        int[][] red = {{2, 3}, {4, 5}, {5, 4}};
        int[][] blue = {{3, 2}};
        assertArrayEquals(new int[]{0, -1, -1, -1, -1, -1},
            solution.shortestAlternatingPaths(6, red, blue));
    }

    @Test
    public void testOnlyRedEdges() {
        assertArrayEquals(new int[]{0, 1, -1},
            solution.shortestAlternatingPaths(3, new int[][]{{0, 1}}, new int[][]{}));
        assertArrayEquals(new int[]{0, 1, -1, -1},
            solution.shortestAlternatingPaths(4, new int[][]{{0, 1}, {1, 2}, {2, 3}}, new int[][]{}));
    }

    @Test
    public void testOnlyBlueEdges() {
        assertArrayEquals(new int[]{0, 1, -1},
            solution.shortestAlternatingPaths(3, new int[][]{}, new int[][]{{0, 1}}));
        assertArrayEquals(new int[]{0, 1, -1, -1},
            solution.shortestAlternatingPaths(4, new int[][]{}, new int[][]{{0, 1}, {1, 2}, {2, 3}}));
    }

    @Test
    public void testDirectEdgesOfBothColors() {
        assertArrayEquals(new int[]{0, 1},
            solution.shortestAlternatingPaths(2, new int[][]{{0, 1}}, new int[][]{{0, 1}}));
    }

    @Test
    public void testSelfLoopsDoNotBreakAlternation() {
        int[][] red = {{0, 0}, {1, 1}, {0, 1}, {1, 2}};
        int[][] blue = {{0, 0}, {0, 1}, {1, 2}, {2, 3}};
        assertArrayEquals(oracle(4, red, blue), solution.shortestAlternatingPaths(4, red, blue));
        assertArrayEquals(new int[]{0, 1, 2, 3}, solution.shortestAlternatingPaths(4, red, blue));
    }

    @Test
    public void testSelfLoop() {
        assertArrayEquals(new int[]{0, 1}, solution.shortestAlternatingPaths(2,
            new int[][]{{0, 0}, {0, 1}}, new int[][]{}));
    }

    @Test
    public void testFiveNodesAlternating() {
        assertArrayEquals(new int[]{0, 1, 2, 3, 4},
            solution.shortestAlternatingPaths(5, new int[][]{{0, 1}, {2, 3}},
                new int[][]{{1, 2}, {3, 4}}));
    }

    @Test
    public void testCyclesTerminateAndRemainAlternating() {
        int[][] red = {{0, 1}, {2, 3}, {3, 0}, {1, 1}};
        int[][] blue = {{1, 2}, {3, 3}, {0, 2}, {2, 0}};
        assertArrayEquals(oracle(4, red, blue), solution.shortestAlternatingPaths(4, red, blue));
        assertArrayEquals(new int[]{0, 1, 1, 2}, solution.shortestAlternatingPaths(4, red, blue));
    }

    @Test
    public void testParallelEdges() {
        int[][] red = {{0, 1}, {0, 1}, {1, 2}, {1, 2}};
        int[][] blue = {{0, 1}, {0, 1}, {1, 2}, {1, 2}};
        assertArrayEquals(new int[]{0, 1, 2}, solution.shortestAlternatingPaths(3, red, blue));
    }

    @Test
    public void testZeroLengthPathForSource() {
        assertArrayEquals(new int[]{0, -1, -1, -1},
            solution.shortestAlternatingPaths(4, new int[][]{}, new int[][]{}));
    }

    @Test
    public void testSingleNodeWithAndWithoutLoops() {
        assertArrayEquals(new int[]{0}, solution.shortestAlternatingPaths(1, new int[][]{}, new int[][]{}));
        assertArrayEquals(new int[]{0}, solution.shortestAlternatingPaths(1,
            new int[][]{{0, 0}, {0, 0}}, new int[][]{{0, 0}}));
    }

    @Test
    public void testNodesAtMaximumIndex() {
        int[][] red = {{0, 4}, {4, 2}};
        int[][] blue = {{4, 3}, {3, 1}};
        assertArrayEquals(new int[]{0, -1, -1, 2, 1}, solution.shortestAlternatingPaths(5, red, blue));
    }

    @Test
    public void testAlternativeColorArrivalCanReachTarget() {
        int[][] red = {{0, 1}, {1, 2}, {2, 4}};
        int[][] blue = {{0, 1}, {1, 3}, {2, 4}};
        assertArrayEquals(new int[]{0, 1, 2, 2, 3}, solution.shortestAlternatingPaths(5, red, blue));
    }

    @Test
    public void testUnreachableDespiteOneColorPath() {
        int[][] red = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        int[][] blue = {{0, 4}, {2, 4}};
        assertArrayEquals(new int[]{0, 1, -1, -1, 1}, solution.shortestAlternatingPaths(5, red, blue));
    }

    @Test
    public void testUnreachableNode() {
        assertArrayEquals(new int[]{0, 1, -1}, solution.shortestAlternatingPaths(3,
            new int[][]{{0, 1}}, new int[][]{{0, 1}}));
    }

    @Test
    public void testInputArraysAreNotMutated() {
        int[][] red = {{0, 1}, {2, 3}, {3, 3}};
        int[][] blue = {{1, 2}, {3, 0}};
        int[][] redBefore = copy(red);
        int[][] blueBefore = copy(blue);
        solution.shortestAlternatingPaths(4, red, blue);
        assertArrayEquals(redBefore, red);
        assertArrayEquals(blueBefore, blue);
    }

    @Test
    public void testRepeatedCallsAreIsolated() {
        int[][] red = {{0, 1}, {2, 3}};
        int[][] blue = {{1, 2}};
        assertArrayEquals(new int[]{0, 1, 2, 3}, solution.shortestAlternatingPaths(4, red, blue));
        assertArrayEquals(new int[]{0, -1}, solution.shortestAlternatingPaths(2, new int[][]{}, new int[][]{}));
        assertArrayEquals(new int[]{0, 1, 2, 3}, solution.shortestAlternatingPaths(4, red, blue));
    }

    @Test
    public void testEnumeratedSmallGraphsAgainstIndependentOracle() {
        int[][] possible = {{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 2}, {2, 1}};
        for (int redMask = 0; redMask < (1 << possible.length); redMask++) {
            for (int blueMask = 0; blueMask < (1 << possible.length); blueMask++) {
                int[][] red = edgesFromMask(possible, redMask);
                int[][] blue = edgesFromMask(possible, blueMask);
                assertArrayEquals(oracle(3, red, blue), solution.shortestAlternatingPaths(3, red, blue),
                    "masks red=" + redMask + ", blue=" + blueMask);
            }
        }
    }

    @Test
    public void testSeededGraphsAgainstIndependentOracle() {
        Random random = new Random(1129L);
        for (int caseNumber = 0; caseNumber < 100; caseNumber++) {
            int n = 1 + random.nextInt(12);
            int redCount = random.nextInt(30);
            int blueCount = random.nextInt(30);
            int[][] red = randomEdges(random, n, redCount);
            int[][] blue = randomEdges(random, n, blueCount);
            assertArrayEquals(oracle(n, red, blue), solution.shortestAlternatingPaths(n, red, blue),
                "random case " + caseNumber);
        }
    }

    @Test
    public void testMaximumNodeBoundaryWithAlternatingChain() {
        int n = 100;
        int[][] red = new int[50][2];
        int[][] blue = new int[49][2];
        for (int i = 0; i < red.length; i++) {
            red[i] = new int[]{2 * i, 2 * i + 1};
        }
        for (int i = 0; i < blue.length; i++) {
            blue[i] = new int[]{2 * i + 1, 2 * i + 2};
        }
        int[] expected = new int[n];
        for (int i = 0; i < n; i++) {
            expected[i] = i;
        }
        assertArrayEquals(expected, solution.shortestAlternatingPaths(n, red, blue));
    }

    @Test
    public void testMaximumEdgeBoundary() {
        int n = 100;
        int[][] red = new int[400][2];
        int[][] blue = new int[400][2];
        for (int i = 0; i < 400; i++) {
            red[i] = new int[]{i % n, (i * 37 + 11) % n};
            blue[i] = new int[]{(i * 17 + 3) % n, (i * 53 + 7) % n};
        }
        assertArrayEquals(oracle(n, red, blue), solution.shortestAlternatingPaths(n, red, blue));
    }

    @Test
    public void testGiantCase() {
        int n = 50;
        int[][] red = new int[25][2];
        int[][] blue = new int[24][2];
        for (int i = 0; i < red.length; i++) {
            red[i] = new int[]{2 * i, 2 * i + 1};
        }
        for (int i = 0; i < blue.length; i++) {
            blue[i] = new int[]{2 * i + 1, 2 * i + 2};
        }
        int[] expected = new int[n];
        for (int i = 0; i < n; i++) {
            expected[i] = i;
        }
        assertArrayEquals(expected, solution.shortestAlternatingPaths(n, red, blue));
    }

    @Test
    public void testDenseParallelEdgesAtEdgeLimit() {
        int[][] red = new int[400][2];
        int[][] blue = new int[400][2];
        for (int i = 0; i < 400; i++) {
            red[i] = new int[]{0, 1};
            blue[i] = new int[]{1, 2};
        }
        assertArrayEquals(new int[]{0, 1, 2}, solution.shortestAlternatingPaths(3, red, blue));
    }

    private static int[] oracle(int n, int[][] redEdges, int[][] blueEdges) {
        int[][] distance = new int[n][3];
        for (int[] row : distance) {
            Arrays.fill(row, -1);
        }
        Queue<int[]> queue = new ArrayDeque<>();
        distance[0][2] = 0;
        queue.add(new int[]{0, 2});
        while (!queue.isEmpty()) {
            int[] state = queue.remove();
            int node = state[0];
            int previousColor = state[1];
            int nextDistance = distance[node][previousColor] + 1;
            if (previousColor != 0) {
                for (int[] edge : redEdges) {
                    if (edge[0] == node && distance[edge[1]][0] == -1) {
                        distance[edge[1]][0] = nextDistance;
                        queue.add(new int[]{edge[1], 0});
                    }
                }
            }
            if (previousColor != 1) {
                for (int[] edge : blueEdges) {
                    if (edge[0] == node && distance[edge[1]][1] == -1) {
                        distance[edge[1]][1] = nextDistance;
                        queue.add(new int[]{edge[1], 1});
                    }
                }
            }
        }
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        answer[0] = 0;
        for (int node = 1; node < n; node++) {
            if (distance[node][0] != -1 && distance[node][1] != -1) {
                answer[node] = Math.min(distance[node][0], distance[node][1]);
            } else if (distance[node][0] != -1) {
                answer[node] = distance[node][0];
            } else {
                answer[node] = distance[node][1];
            }
        }
        return answer;
    }

    private static int[][] copy(int[][] edges) {
        int[][] result = new int[edges.length][];
        for (int i = 0; i < edges.length; i++) {
            result[i] = edges[i].clone();
        }
        return result;
    }

    private static int[][] edgesFromMask(int[][] possible, int mask) {
        int count = Integer.bitCount(mask);
        int[][] edges = new int[count][2];
        int index = 0;
        for (int bit = 0; bit < possible.length; bit++) {
            if ((mask & (1 << bit)) != 0) {
                edges[index++] = possible[bit].clone();
            }
        }
        return edges;
    }

    private static int[][] randomEdges(Random random, int n, int count) {
        int[][] edges = new int[count][2];
        for (int i = 0; i < count; i++) {
            edges[i] = new int[]{random.nextInt(n), random.nextInt(n)};
        }
        return edges;
    }
}

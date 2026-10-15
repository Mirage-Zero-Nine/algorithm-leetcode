package solutions.graph;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class FindOrder_210Test {
    private final FindOrder_210 solution = new FindOrder_210();

    @Test
    public void returnsTheRequiredOrderForOfficialShapes() {
        assertArrayEquals(new int[]{0, 1}, solution.findOrder(2, new int[][]{{1, 0}}));
        assertValid(solution.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}),
                4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        assertValid(solution.findOrder(5, new int[][]{{2, 0}, {2, 1}, {3, 2}, {4, 2}}),
                5, new int[][]{{2, 0}, {2, 1}, {3, 2}, {4, 2}});
    }

    @Test
    public void handlesNoPrerequisitesAndSingletons() {
        assertValid(solution.findOrder(1, new int[][]{}), 1, new int[][]{});
        assertValid(solution.findOrder(5, new int[][]{}), 5, new int[][]{});
        assertArrayEquals(new int[0], solution.findOrder(0, new int[][]{}));
        assertArrayEquals(new int[0], solution.findOrder(3, null));
    }

    @Test
    public void handlesIndependentChainsAndDisconnectedCourses() {
        int[][] prerequisites = {{1, 0}, {3, 2}};
        assertValid(solution.findOrder(4, prerequisites), 4, prerequisites);
        prerequisites = new int[][]{{1, 0}, {2, 1}, {3, 2}};
        assertArrayEquals(new int[]{0, 1, 2, 3}, solution.findOrder(4, prerequisites));
    }

    @Test
    public void handlesManyPrerequisitesAndMerges() {
        int[][] prerequisites = {{3, 0}, {3, 1}, {3, 2}};
        assertValid(solution.findOrder(4, prerequisites), 4, prerequisites);
        prerequisites = new int[][]{{1, 0}, {2, 0}, {3, 1}, {4, 2}, {5, 3}, {5, 4}};
        assertValid(solution.findOrder(6, prerequisites), 6, prerequisites);
    }

    @Test
    public void detectsTwoCourseAndLongerCycles() {
        assertArrayEquals(new int[0], solution.findOrder(2, new int[][]{{1, 0}, {0, 1}}));
        assertArrayEquals(new int[0], solution.findOrder(3, new int[][]{{0, 1}, {1, 2}, {2, 0}}));
        assertArrayEquals(new int[0], solution.findOrder(4, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}}));
    }

    @Test
    public void detectsSelfCycleAndCycleWithAcyclicTail() {
        assertArrayEquals(new int[0], solution.findOrder(2, new int[][]{{0, 0}}));
        assertArrayEquals(new int[0], solution.findOrder(4,
                new int[][]{{1, 0}, {2, 1}, {0, 2}, {3, 2}}));
    }

    @Test
    public void preservesPrerequisiteInputAndSupportsRepeatedCalls() {
        int[][] prerequisites = {{1, 0}, {2, 1}, {2, 0}};
        int[][] before = copy(prerequisites);
        assertValid(solution.findOrder(3, prerequisites), 3, prerequisites);
        assertTrue(Arrays.deepEquals(before, prerequisites));
        assertValid(solution.findOrder(2, new int[][]{{1, 0}}), 2, new int[][]{{1, 0}});
        assertArrayEquals(new int[0], solution.findOrder(2, new int[][]{{0, 1}, {1, 0}}));
        assertValid(solution.findOrder(1, new int[][]{}), 1, new int[][]{});
    }

    @Test
    public void exhaustiveFourCourseGraphsAgreeWithIndependentCycleOracle() throws Exception {
        runWorker("exhaustive");
    }

    @Test
    public void handlesMaximumSizedDagAndCycleWithinBoundedChildProcess() throws Exception {
        runWorker("stress");
    }

    private void assertValid(int[] order, int numCourses, int[][] prerequisites) {
        assertEquals(numCourses, order.length);
        boolean[] seen = new boolean[numCourses];
        int[] position = new int[numCourses];
        for (int i = 0; i < order.length; i++) {
            assertTrue(order[i] >= 0 && order[i] < numCourses);
            assertFalse(seen[order[i]], "duplicate course " + order[i]);
            seen[order[i]] = true;
            position[order[i]] = i;
        }
        for (int[] prerequisite : prerequisites) {
            assertTrue(position[prerequisite[1]] < position[prerequisite[0]],
                    "invalid prerequisite " + Arrays.toString(prerequisite));
        }
    }

    private void runWorker(String mode) throws Exception {
        String java = System.getProperty("java.home") + "/bin/java";
        String classPath = System.getProperty("surefire.test.class.path", System.getProperty("java.class.path"));
        Process process = new ProcessBuilder(java, "-cp", classPath, FindOrder_210Worker.class.getName(), mode)
                .redirectErrorStream(true).start();
        if (!process.waitFor(10, TimeUnit.SECONDS)) {
            process.destroyForcibly();
            if (!process.waitFor(2, TimeUnit.SECONDS)) {
                fail("worker could not be terminated after timeout: " + mode);
            }
            fail("worker timed out: " + mode);
        }
        String output;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
            output = reader.lines().reduce("", (a, b) -> a + b);
        }
        assertEquals(0, process.exitValue(), mode + ": " + output);
        assertTrue(output.contains("OK"), mode + ": " + output);
    }

    private static int[][] copy(int[][] input) {
        int[][] copy = new int[input.length][];
        for (int i = 0; i < input.length; i++) copy[i] = input[i].clone();
        return copy;
    }

    public static final class FindOrder_210Worker {
        public static void main(String[] args) {
            if ("exhaustive".equals(args[0])) exhaustive();
            else stress();
            System.out.println("OK");
        }

        private static void exhaustive() {
            int n = 4;
            for (int mask = 0; mask < (1 << (n * n)); mask++) {
                List<int[]> edges = new ArrayList<>();
                int bit = 0;
                for (int prerequisite = 0; prerequisite < n; prerequisite++) {
                    for (int course = 0; course < n; course++) {
                        if ((mask & (1 << bit++)) != 0) edges.add(new int[]{course, prerequisite});
                    }
                }
                int[][] graph = edges.toArray(new int[0][]);
                int[] actual = new FindOrder_210().findOrder(n, graph);
                if (hasCycle(n, graph)) {
                    if (actual.length != 0) throw new AssertionError("cycle mask " + mask);
                } else {
                    validate(actual, n, graph, mask);
                }
            }
        }

        private static void stress() {
            int n = 2000;
            int[][] dag = new int[5000][2];
            int edge = 0;
            for (int prerequisite = 0; prerequisite < n && edge < dag.length; prerequisite++) {
                for (int course = prerequisite + 1; course < n && edge < dag.length; course++) {
                    dag[edge++] = new int[]{course, prerequisite};
                }
            }
            validate(new FindOrder_210().findOrder(n, dag), n, dag, 0);
            int[][] cycle = new int[n][2];
            for (int i = 0; i < n - 1; i++) cycle[i] = new int[]{i + 1, i};
            cycle[n - 1] = new int[]{0, n - 1};
            if (new FindOrder_210().findOrder(n, cycle).length != 0)
                throw new AssertionError("large cycle was not rejected");
        }

        private static void validate(int[] order, int n, int[][] edges, int caseId) {
            if (order.length != n) throw new AssertionError("case " + caseId + " length");
            int[] positions = new int[n];
            boolean[] seen = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (order[i] < 0 || order[i] >= n || seen[order[i]])
                    throw new AssertionError("case " + caseId + " permutation");
                seen[order[i]] = true;
                positions[order[i]] = i;
            }
            for (int[] edge : edges) {
                if (positions[edge[1]] >= positions[edge[0]])
                    throw new AssertionError("case " + caseId + " edge");
            }
        }

        private static boolean hasCycle(int n, int[][] edges) {
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
            for (int[] edge : edges) graph.get(edge[1]).add(edge[0]);
            int[] state = new int[n];
            for (int course = 0; course < n; course++) {
                if (state[course] == 0 && visit(course, graph, state)) return true;
            }
            return false;
        }

        private static boolean visit(int course, List<List<Integer>> graph, int[] state) {
            state[course] = 1;
            for (int next : graph.get(course)) {
                if (state[next] == 1 || (state[next] == 0 && visit(next, graph, state))) return true;
            }
            state[course] = 2;
            return false;
        }
    }
}

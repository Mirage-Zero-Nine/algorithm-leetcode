package solutions.graph;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FindOrder_210Test {

    @Test
    public void testAllFourCourseGraphsProduceValidPermutationsOrDetectCycles() {
        for (int mask = 0; mask < 4096; mask++) {
            java.util.List<int[]> edges = new java.util.ArrayList<>();
            boolean[][] reachable = new boolean[4][4];
            int bit = 0;
            for (int from = 0; from < 4; from++) {
                for (int to = 0; to < 4; to++) {
                    if (from != to && (mask & (1 << bit++)) != 0) {
                        edges.add(new int[]{to, from});
                        reachable[from][to] = true;
                    }
                }
            }
            for (int via = 0; via < 4; via++)
                for (int from = 0; from < 4; from++)
                    for (int to = 0; to < 4; to++)
                        reachable[from][to] |= reachable[from][via] && reachable[via][to];
            boolean cyclic = false;
            for (int i = 0; i < 4; i++) cyclic |= reachable[i][i];
            int[] actual = test.findOrder(4, edges.toArray(new int[0][]));
            assertEquals(cyclic ? 0 : 4, actual.length, "graph " + mask);
            if (!cyclic) {
                boolean[] seen = new boolean[4];
                int[] positions = new int[4];
                for (int i = 0; i < actual.length; i++) {
                    assertTrue(actual[i] >= 0 && actual[i] < 4);
                    assertTrue(!seen[actual[i]], "duplicate course");
                    seen[actual[i]] = true;
                    positions[actual[i]] = i;
                }
                for (int[] edge : edges) assertTrue(positions[edge[1]] < positions[edge[0]]);
            }
        }
    }


    private final FindOrder_210 test = new FindOrder_210();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{0, 1}, test.findOrder(2, new int[][]{{1, 0}}));
        int[] result = test.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        assertEquals(4, result.length);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[]{}, test.findOrder(2, new int[][]{{1, 0}, {0, 1}}));
        assertArrayEquals(new int[]{0}, test.findOrder(1, new int[][]{}));
    }

    @Test
    public void testLargeCase() {
        int[] result = test.findOrder(5, new int[][]{{1, 0}, {2, 1}, {3, 2}, {4, 3}});
        assertEquals(5, result.length);
        assertEquals(0, result[0]);
        assertEquals(4, result[4]);
    }

    @Test
    public void testNoPrerequisites() {
        int[] result = test.findOrder(3, new int[][]{});
        assertEquals(3, result.length);
    }

    @Test
    public void testCycleDetection() {
        // 3-node cycle
        assertArrayEquals(new int[]{}, test.findOrder(3, new int[][]{{0, 1}, {1, 2}, {2, 0}}));
    }

    @Test
    public void testLinearChain() {
        int[] result = test.findOrder(4, new int[][]{{1, 0}, {2, 1}, {3, 2}});
        assertArrayEquals(new int[]{0, 1, 2, 3}, result);
    }

    @Test
    public void testTwoIndependentChains() {
        int[] result = test.findOrder(4, new int[][]{{1, 0}, {3, 2}});
        assertEquals(4, result.length);
        // 0 before 1, 2 before 3
        assertTrue(indexOf(result, 0) < indexOf(result, 1));
        assertTrue(indexOf(result, 2) < indexOf(result, 3));
    }

    @Test
    public void testDiamondDependency() {
        // 0 -> 1, 0 -> 2, 1 -> 3, 2 -> 3
        int[] result = test.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        assertEquals(4, result.length);
        assertTrue(indexOf(result, 0) < indexOf(result, 1));
        assertTrue(indexOf(result, 0) < indexOf(result, 2));
        assertTrue(indexOf(result, 1) < indexOf(result, 3));
        assertTrue(indexOf(result, 2) < indexOf(result, 3));
    }

    @Test
    public void testSelfCycle() {
        // Self-loop
        assertArrayEquals(new int[]{}, test.findOrder(2, new int[][]{{0, 0}}));
    }

    @Test
    public void testZeroCourses() {
        assertArrayEquals(new int[]{}, test.findOrder(0, new int[][]{}));
    }

    @Test
    public void testGiantLinearChain() {
        int n = 500;
        int[][] prereqs = new int[n - 1][2];
        for (int i = 0; i < n - 1; i++) {
            prereqs[i] = new int[]{i + 1, i};
        }
        int[] result = test.findOrder(n, prereqs);
        assertEquals(n, result.length);
        for (int i = 0; i < n; i++) {
            assertEquals(i, result[i]);
        }
    }

    private int indexOf(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) return i;
        }
        return -1;
    }
}

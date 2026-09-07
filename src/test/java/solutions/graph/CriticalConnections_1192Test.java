package solutions.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class CriticalConnections_1192Test {

    @Test
    public void testAllConnectedFourServerGraphsAgainstEdgeRemoval() {
        int[][] possible = {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}, {2, 3}};
        CriticalConnections_1192 solution = new CriticalConnections_1192();
        for (int mask = 0; mask < 64; mask++) {
            List<List<Integer>> edges = new ArrayList<>();
            for (int i = 0; i < possible.length; i++)
                if ((mask & (1 << i)) != 0) edges.add(List.of(possible[i][0], possible[i][1]));
            if (!allServersReachable(edges, -1)) continue;
            Set<Set<Integer>> expected = new HashSet<>();
            for (int omitted = 0; omitted < edges.size(); omitted++)
                if (!allServersReachable(edges, omitted)) expected.add(new HashSet<>(edges.get(omitted)));
            List<List<Integer>> actual = solution.criticalConnections(4, edges);
            Set<Set<Integer>> normalized = new HashSet<>();
            for (List<Integer> edge : actual) normalized.add(new HashSet<>(edge));
            assertEquals(expected.size(), actual.size(), "graph " + mask);
            assertEquals(expected, normalized, "graph " + mask);
        }
    }

    private boolean allServersReachable(List<List<Integer>> edges, int omitted) {
        boolean[] reached = new boolean[4];
        reached[0] = true;
        for (int pass = 0; pass < 4; pass++) {
            for (int i = 0; i < edges.size(); i++) {
                if (i == omitted) continue;
                int a = edges.get(i).get(0), b = edges.get(i).get(1);
                if (reached[a] || reached[b]) reached[a] = reached[b] = true;
            }
        }
        for (boolean server : reached) if (!server) return false;
        return true;
    }


    @Test
    public void testHappyCases() {
        CriticalConnections_1192 test = new CriticalConnections_1192();
        List<List<Integer>> result = test.criticalConnections(4, List.of(List.of(0, 1), List.of(1, 2), List.of(2, 0), List.of(1, 3)));
        assertEquals(1, result.size());
        Set<Integer> edge = new HashSet<>(result.get(0));
        assertTrue(edge.contains(1) && edge.contains(3));
    }

    @Test
    public void testEdgeCases() {
        CriticalConnections_1192 test = new CriticalConnections_1192();
        List<List<Integer>> result = test.criticalConnections(2, List.of(List.of(0, 1)));
        assertEquals(1, result.size());
    }

    @Test
    public void testLargeCase() {
        CriticalConnections_1192 test = new CriticalConnections_1192();
        List<List<Integer>> result = test.criticalConnections(5,
            List.of(List.of(0, 1), List.of(1, 2), List.of(2, 0), List.of(1, 3), List.of(3, 4)));
        assertEquals(2, result.size());
    }

    @Test
    public void testNoCriticalEdges() {
        CriticalConnections_1192 test = new CriticalConnections_1192();
        // Complete cycle: 0-1-2-0, no critical edges
        List<List<Integer>> result = test.criticalConnections(3, List.of(List.of(0, 1), List.of(1, 2), List.of(2, 0)));
        assertEquals(0, result.size());
    }

    @Test
    public void testAllCriticalEdges() {
        CriticalConnections_1192 test = new CriticalConnections_1192();
        // Linear chain: 0-1-2-3, all edges are critical
        List<List<Integer>> result = test.criticalConnections(4,
            List.of(List.of(0, 1), List.of(1, 2), List.of(2, 3)));
        assertEquals(3, result.size());
    }

    @Test
    public void testTwoCyclesConnectedByBridge() {
        CriticalConnections_1192 test = new CriticalConnections_1192();
        // Cycle 0-1-2-0 and cycle 3-4-5-3, connected by edge 2-3
        List<List<Integer>> result = test.criticalConnections(6,
            List.of(List.of(0, 1), List.of(1, 2), List.of(2, 0), List.of(3, 4), List.of(4, 5), List.of(5, 3), List.of(2, 3)));
        assertEquals(1, result.size());
    }

    @Test
    public void testFourNodeCycle() {
        CriticalConnections_1192 test = new CriticalConnections_1192();
        List<List<Integer>> result = test.criticalConnections(4,
            List.of(List.of(0, 1), List.of(1, 2), List.of(2, 3), List.of(3, 0)));
        assertEquals(0, result.size());
    }

    @Test
    public void testStarGraph() {
        CriticalConnections_1192 test = new CriticalConnections_1192();
        // Star: 0 connected to 1,2,3,4 - all edges are critical
        List<List<Integer>> result = test.criticalConnections(5,
            List.of(List.of(0, 1), List.of(0, 2), List.of(0, 3), List.of(0, 4)));
        assertEquals(4, result.size());
    }

    @Test
    public void testTriangleWithTail() {
        CriticalConnections_1192 test = new CriticalConnections_1192();
        // Triangle 0-1-2-0 with tail 2-3
        List<List<Integer>> result = test.criticalConnections(4,
            List.of(List.of(0, 1), List.of(1, 2), List.of(2, 0), List.of(2, 3)));
        assertEquals(1, result.size());
    }

    @Test
    public void testTwoNodes() {
        CriticalConnections_1192 test = new CriticalConnections_1192();
        List<List<Integer>> result = test.criticalConnections(2, List.of(List.of(0, 1)));
        assertEquals(1, result.size());
    }

    @Test
    public void testGiantChain() {
        CriticalConnections_1192 test = new CriticalConnections_1192();
        int n = 50;
        List<List<Integer>> connections = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            connections.add(List.of(i, i + 1));
        }
        List<List<Integer>> result = test.criticalConnections(n, connections);
        assertEquals(n - 1, result.size());
    }
}

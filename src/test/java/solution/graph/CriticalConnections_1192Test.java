package solution.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class CriticalConnections_1192Test {

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

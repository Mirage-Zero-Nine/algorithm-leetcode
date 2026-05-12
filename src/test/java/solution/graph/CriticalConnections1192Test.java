package solution.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class CriticalConnections1192Test {

    private final CriticalConnections_1192 test = new CriticalConnections_1192();

    @Test
    public void testHappyCases() {
        List<List<Integer>> result = test.criticalConnections(4, List.of(List.of(0, 1), List.of(1, 2), List.of(2, 0), List.of(1, 3)));
        assertEquals(1, result.size());
        Set<Integer> edge = new HashSet<>(result.get(0));
        assertTrue(edge.contains(1) && edge.contains(3));
    }

    @Test
    public void testEdgeCases() {
        List<List<Integer>> result = test.criticalConnections(2, List.of(List.of(0, 1)));
        assertEquals(1, result.size());
    }

    @Test
    public void testLargeCase() {
        List<List<Integer>> result = test.criticalConnections(5,
            List.of(List.of(0, 1), List.of(1, 2), List.of(2, 0), List.of(1, 3), List.of(3, 4)));
        assertEquals(2, result.size());
    }
}

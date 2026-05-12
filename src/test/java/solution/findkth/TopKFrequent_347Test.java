package solution.findkth;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TopKFrequent_347Test {
    private final TopKFrequent_347 solver = new TopKFrequent_347();

    @Test public void testBucketSortBasic() {
        int[] res = solver.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        Arrays.sort(res);
        assertEquals("[1, 2]", Arrays.toString(res));
    }

    @Test public void testBucketSortSingleElement() {
        int[] res = solver.topKFrequent(new int[]{1}, 1);
        assertEquals("[1]", Arrays.toString(res));
    }

    @Test public void testHeapApproach() {
        int[] res = solver.heap(new int[]{1, 1, 1, 2, 2, 3}, 2);
        Set<Integer> resultSet = new HashSet<>();
        for (int n : res) resultSet.add(n);
        assertEquals(Set.of(1, 2), resultSet);
    }

    @Test public void testHeapK3() {
        int[] res = solver.heap(new int[]{4, 4, 4, 3, 3, 2, 2, 1}, 3);
        Set<Integer> resultSet = new HashSet<>();
        for (int n : res) resultSet.add(n);
        assertEquals(Set.of(2, 3, 4), resultSet);
    }

    @Test public void testTwoHashMap() {
        int[] res = solver.twoHashMap(new int[]{1, 1, 1, 2, 2, 3}, 2);
        Set<Integer> resultSet = new HashSet<>();
        for (int n : res) resultSet.add(n);
        assertEquals(Set.of(1, 2), resultSet);
    }
}
